package net.pkhapps.idispatch.sandbox.workstation.model;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.shared.Registration;
import net.pkhapps.idispatch.sandbox.workstation.service.ResourceLookupService;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Resource;
import net.pkhapps.idispatch.sandbox.workstation.service.filter.ResourceFilter;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Objects;

public class ResourcesModel implements Serializable {

    private final ResourceLookupService resourceLookupService;
    private final ConfigurableFilterDataProvider<Resource, Void, ResourceFilter> dataProvider;
    private Registration serviceRegistration;
    private UI ui;

    public ResourcesModel(@Nonnull ResourceLookupService resourceLookupService) {
        this.resourceLookupService = Objects.requireNonNull(resourceLookupService, "resourceLookupService must not be null");
        this.dataProvider = DataProvider
                .fromFilteringCallbacks(resourceLookupService::fetch, resourceLookupService::count)
                .withConfigurableFilter();
    }

    public void attach(@Nonnull UI ui) {
        Objects.requireNonNull(ui, "ui must not be null");
        this.ui = ui;
        serviceRegistration = resourceLookupService.addResourcesChangedListener(this::onResourcesChangedEvent);
    }

    public void detach() {
        serviceRegistration.remove();
        this.ui = null;
    }

    private void onResourcesChangedEvent(@Nonnull ResourceLookupService.ResourcesChangedEvent event) {
        if (event.hasAddedResources() || event.hasDeletedResources()) {
            access(dataProvider::refreshAll);
        } else if (event.hasChangedResources()) {
            access(() -> event.getChangedResources().forEach(dataProvider::refreshItem));
        }
    }

    private void access(@Nonnull Command command) {
        if (ui == null || ui.getSession().hasLock()) {
            command.execute();
        } else {
            ui.access(command);
        }
    }

    @Nonnull
    public ConfigurableFilterDataProvider<Resource, Void, ResourceFilter> getDataProvider() {
        return dataProvider;
    }
}
