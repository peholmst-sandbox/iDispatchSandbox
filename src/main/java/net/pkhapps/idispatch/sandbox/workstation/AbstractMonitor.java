package net.pkhapps.idispatch.sandbox.workstation;

import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import net.pkhapps.idispatch.sandbox.workstation.components.ApplicationHeader;
import net.pkhapps.idispatch.sandbox.workstation.model.ApplicationHeaderModel;

public abstract class AbstractMonitor<M extends TemplateModel> extends PolymerTemplate<M> {

    @Id("header")
    private ApplicationHeader header;

    protected AbstractMonitor() {
        var applicationHeaderModel = ApplicationHeaderModel.instance();
        header.setAvatar(applicationHeaderModel.getAvatarUri());

        // DUMMY DATA:
        {
            header.setAssignmentsInProgress(10);
            header.setAssignmentsOnHold(3);
            header.setAssignmentsWaitingForDispatch(2);
        }
    }
}
