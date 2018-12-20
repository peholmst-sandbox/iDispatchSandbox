package net.pkhapps.idispatch.sandbox.workstation;

import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import net.pkhapps.appmodel4flow.binding.ComponentBinding;
import net.pkhapps.appmodel4flow.binding.group.BindingGroup;
import net.pkhapps.idispatch.sandbox.workstation.components.ApplicationHeader;
import net.pkhapps.idispatch.sandbox.workstation.model.ApplicationHeaderModel;

public abstract class AbstractMonitor<M extends TemplateModel> extends PolymerTemplate<M> {

    private final BindingGroup bindingGroup = new BindingGroup();
    @Id("header")
    private ApplicationHeader header;

    protected AbstractMonitor() {
        var applicationHeaderModel = ApplicationHeaderModel.instance();
        header.setAvatar(applicationHeaderModel.getAvatarUri());

        bindingGroup
                .withBinding(new ComponentBinding<>(applicationHeaderModel.assignmentsInProgress(),
                        header, ApplicationHeader::setAssignmentsInProgress))
                .withBinding(new ComponentBinding<>(applicationHeaderModel.assignmentsOnHold(),
                        header, ApplicationHeader::setAssignmentsOnHold))
                .withBinding(new ComponentBinding<>(applicationHeaderModel.assignmentsWaitingForDispatch(),
                        header, ApplicationHeader::setAssignmentsWaitingForDispatch));
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        bindingGroup.dispose();
    }
}
