package net.pkhapps.idispatch.sandbox.workstation.secondarymonitor;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;
import net.pkhapps.idispatch.sandbox.workstation.encoders.StatusIdEncoder;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Resource;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Status;

import javax.annotation.Nonnull;
import java.util.Objects;

@Tag("resource-card")
@HtmlImport("frontend://src/monitors/secondary/resource-card/resource-card.html")
public class ResourceCard extends PolymerTemplate<ResourceCard.Model> {

    // TODO Implement me
    private Resource resource;

    public interface Model extends TemplateModel {

        void setCallSign(String callSign);

        @Encode(value = StatusIdEncoder.class, path = "id")
        void setStatus(Status status);
    }

    public ResourceCard(@Nonnull Resource resource) {
        update(resource);
    }

    public void update(@Nonnull Resource resource) {
        this.resource = Objects.requireNonNull(resource, "resource must not be null");

        getModel().setCallSign(resource.getCallSign());
        getModel().setStatus(resource.getStatus());
    }

    @Nonnull
    public Resource getResource() {
        return resource;
    }
}
