package net.pkhapps.idispatch.sandbox.workstation.secondarymonitor;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import net.pkhapps.appmodel4flow.action.Action;
import net.pkhapps.appmodel4flow.property.DefaultProperty;
import net.pkhapps.idispatch.sandbox.workstation.components.CardList;
import net.pkhapps.idispatch.sandbox.workstation.model.ResourcesModel;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Resource;
import net.pkhapps.idispatch.sandbox.workstation.service.filter.ResourceFilter;

import javax.annotation.Nonnull;
import java.util.Objects;

import static net.pkhapps.appmodel4flow.AppModel.asAction;
import static net.pkhapps.appmodel4flow.AppModel.bind;

class ResourcesView extends Composite<Div> {

    private final Toolbar toolbar;
    private final DefaultProperty<String> resourceFilterString = new DefaultProperty<>();
    private final Action<Void> showTable = asAction(this::showTable);
    private final Action<Void> showList = asAction(this::showList);
    private final Action<Void> showGrid = asAction(this::showGrid);
    private ResourcesModel resourcesModel;
    private Component mainComponent;

    ResourcesView(@Nonnull ResourcesModel resourcesModel) {
        this.resourcesModel = Objects.requireNonNull(resourcesModel, "resourcesModel must not be null");
        toolbar = new Toolbar();
        bind(showTable, toolbar.showAsTable);
        bind(showList, toolbar.showAsList);
        bind(showGrid, toolbar.showAsGrid);
        bind(resourceFilterString, toolbar.search);

        resourceFilterString.addValueChangeListener(event -> {
            var filterString = event.getValue();
            if (filterString == null || filterString.isEmpty()) {
                resourcesModel.getDataProvider().setFilter(null);
            } else {
                resourcesModel.getDataProvider().setFilter(new ResourceFilter(filterString));
            }
        });
        showList();
    }

    @Override
    protected Div initContent() {
        var div = new Div(toolbar);
        div.getClassNames().set("resources-view", true);
        return div;
    }

    private void showTable() {
        var table = new Grid<Resource>();
        table.addColumn(Resource::getCallSign)
                .setHeader("Anropsnamn");
        table.addColumn(Resource::getStatus)
                .setHeader("Status");
        table.addColumn(Resource::getLastCheckIn)
                .setHeader("Senast uppdaterad");
        table.addColumn(Resource::getAssignment)
                .setHeader("Uppdrag");
        table.addColumn(Resource::getLocation)
                .setHeader("Koordinater");
        table.addColumn(Resource::getStation)
                .setHeader("Stationsplats");
        table.setDataProvider(resourcesModel.getDataProvider());
        setMainComponent(table);
    }

    private void showList() {
        var list = new CardList<>(ResourceCard::new, ResourceCard::update);
        list.setDataProvider(resourcesModel.getDataProvider());
        setMainComponent(list);
    }

    private void showGrid() {
        Notification.show("The resource grid is not implemented yet");
    }

    private void setMainComponent(@Nonnull Component component) {
        Objects.requireNonNull(component, "component must not be null");
        if (mainComponent != null) {
            getContent().remove(mainComponent);
        }
        mainComponent = component;
        getContent().add(mainComponent);
    }

    private class Toolbar extends Composite<Div> {

        final TextField search;
        final Button showAsTable;
        final Button showAsList;
        final Button showAsGrid;

        Toolbar() {
            search = new TextField();
            search.setPlaceholder("Sök resurs");
            search.getStyle().set("flex-grow", "1");
            search.addThemeVariants(TextFieldVariant.LUMO_SMALL);
            search.setValueChangeMode(ValueChangeMode.EAGER);
            showAsTable = createButton(VaadinIcon.TABLE);
            showAsList = createButton(VaadinIcon.LIST);
            showAsGrid = createButton(VaadinIcon.GRID_BIG);
        }


        @Nonnull
        private Button createButton(@Nonnull VaadinIcon icon) {
            var button = new Button(new Icon(icon));
            button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE, ButtonVariant.LUMO_SMALL,
                    ButtonVariant.LUMO_CONTRAST);
            return button;
        }

        @Override
        protected Div initContent() {
            var div = new Div(search, showAsList, showAsGrid, showAsTable);
            div.getClassNames().set("toolbar", true);
            return div;
        }
    }
}
