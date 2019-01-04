package net.pkhapps.idispatch.sandbox.workstation.secondarymonitor;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.TemplateModel;
import net.pkhapps.idispatch.sandbox.workstation.dummy.DummyStatusService;
import net.pkhapps.idispatch.sandbox.workstation.encoders.InstantEncoder;
import net.pkhapps.idispatch.sandbox.workstation.encoders.StatusIdEncoder;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Resource;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Status;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.Objects;

@Tag("resource-card")
@HtmlImport("frontend://src/monitors/secondary/resource-card/resource-card.html")
public class ResourceCard extends PolymerTemplate<ResourceCard.Model> {

    // TODO Implement me
    private Resource resource;

    @Id("status")
    private Div status;

    private ContextMenu changeStatusMenu;

    public interface Model extends TemplateModel {

        void setCallSign(String callSign);

        @Encode(value = StatusIdEncoder.class, path = "id")
        void setStatus(Status status);

        @Encode(value = InstantEncoder.class)
        void setLastCheckIn(Instant lastCheckIn);
    }

    public ResourceCard(@Nonnull Resource resource) {
        changeStatusMenu = new ContextMenu(status);
        changeStatusMenu.setOpenOnClick(true);
        DummyStatusService.getInstance().fetch().filter(Status::isUserAssignable).forEach(s -> {
            changeStatusMenu.addItem(s.getNameSwe(), null);
        });
        update(resource);
    }

    public void update(@Nonnull Resource resource) {
        this.resource = Objects.requireNonNull(resource, "resource must not be null");

        getModel().setCallSign(resource.getCallSign());
        getModel().setStatus(resource.getStatus());
        getModel().setLastCheckIn(resource.getLastCheckIn());
    }

    @Nonnull
    public Resource getResource() {
        return resource;
    }
}
