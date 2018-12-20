package net.pkhapps.idispatch.sandbox.workstation.model;

import com.vaadin.flow.server.VaadinSession;
import net.pkhapps.appmodel4flow.model.SessionScopedModel;
import net.pkhapps.appmodel4flow.property.DefaultObservableValue;
import net.pkhapps.appmodel4flow.property.ObservableValue;
import net.pkhapps.idispatch.sandbox.workstation.util.Gravatar;

import javax.annotation.Nonnull;
import java.net.URI;

public class ApplicationHeaderModel extends SessionScopedModel {

    private final DefaultObservableValue<Integer> assignmentsInProgress = new DefaultObservableValue<>(0);
    private final DefaultObservableValue<Integer> assignmentsOnHold = new DefaultObservableValue<>(0);
    private final DefaultObservableValue<Integer> assignmentsWaitingForDispatch = new DefaultObservableValue<>(0);

    private ApplicationHeaderModel(@Nonnull VaadinSession session) {
        super(session);
    }

    @Nonnull
    public static ApplicationHeaderModel instance() {
        return getInstance(ApplicationHeaderModel.class, ApplicationHeaderModel::new);
    }

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

    public void update(int assignmentsInProgress, int assignmentsOnHold, int assignmentsWaitingForDispatch) {
        access(() -> {
            this.assignmentsInProgress.setValue(assignmentsInProgress);
            this.assignmentsOnHold.setValue(assignmentsOnHold);
            this.assignmentsWaitingForDispatch.setValue(assignmentsWaitingForDispatch);
        });
    }
}
