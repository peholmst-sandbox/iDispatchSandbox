package net.pkhapps.idispatch.sandbox.workstation.model;

import net.pkhapps.appmodel4flow.property.DefaultObservableValue;
import net.pkhapps.appmodel4flow.property.ObservableValue;
import net.pkhapps.idispatch.sandbox.workstation.util.Gravatar;
import net.pkhapps.idispatch.sandbox.workstation.util.SessionUtils;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.net.URI;

public class ApplicationHeaderModel implements Serializable {

    private final DefaultObservableValue<Integer> assignmentsInProgress = new DefaultObservableValue<>(0);
    private final DefaultObservableValue<Integer> assignmentsOnHold = new DefaultObservableValue<>(0);
    private final DefaultObservableValue<Integer> assignmentsWaitingForDispatch = new DefaultObservableValue<>(0);

    @Nonnull
    public ObservableValue<Integer> assignmentsInProgress() {
        return assignmentsInProgress;
    }

    @Nonnull
    public ObservableValue<Integer> assignmentsOnHold() {
        return assignmentsOnHold;
    }

    @Nonnull
    public ObservableValue<Integer> assignmentsWaitingForDispatch() {
        return assignmentsWaitingForDispatch;
    }

    @Nonnull
    public URI getAvatarUri() {
        return Gravatar.getAvatarUri("petter@vaadin.com"); // TODO Replace with real data
    }

    @Nonnull
    public static ApplicationHeaderModel instance() {
        return SessionUtils.getSessionScoped(ApplicationHeaderModel.class, ApplicationHeaderModel::new);
    }
}
