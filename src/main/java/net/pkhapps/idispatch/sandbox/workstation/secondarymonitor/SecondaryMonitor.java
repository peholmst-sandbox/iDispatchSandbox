package net.pkhapps.idispatch.sandbox.workstation.secondarymonitor;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import net.pkhapps.idispatch.sandbox.workstation.AbstractMonitor;
import net.pkhapps.idispatch.sandbox.workstation.components.Accordion;
import net.pkhapps.idispatch.sandbox.workstation.dummy.DummyResourceService;
import net.pkhapps.idispatch.sandbox.workstation.model.ResourcesModel;

@Route("secondary")
@PageTitle("iDispatch Workstation Monitor #2")
@Tag("secondary-monitor")
@HtmlImport("frontend://src/monitors/secondary/secondary-monitor.html")
@HtmlImport("frontend://styles/monitors/secondary/secondary-monitor-styles.html")
@Push
public class SecondaryMonitor extends AbstractMonitor<TemplateModel> {

    @Id("sidebar")
    private Accordion sidebar;

    private final ResourcesView resourcesView;
    private final ResourcesModel resourcesModel;

    public SecondaryMonitor() {
        resourcesModel = new ResourcesModel(DummyResourceService.getInstance());
        resourcesView = new ResourcesView(resourcesModel);

        // TODO Localize strings
        sidebar.addItem("Resurser", resourcesView);
        sidebar.addItem("Uppdrag", new Text("Uppdrag"));
        sidebar.addItem("Stationsplatser", new Text("Stationsplatser"));
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        resourcesModel.attach(attachEvent.getUI());
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        resourcesModel.detach();
        super.onDetach(detachEvent);
    }
}
