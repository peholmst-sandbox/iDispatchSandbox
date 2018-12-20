package net.pkhapps.idispatch.sandbox.workstation.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

import javax.annotation.Nonnull;
import java.net.URI;
import java.util.Locale;
import java.util.Objects;

@Tag("application-header")
@HtmlImport("frontend://src/components/application-header/application-header.html")
public class ApplicationHeader extends PolymerTemplate<ApplicationHeader.Model> {

    public interface Model extends TemplateModel {

        void setMonitorNumber(int monitorNumber);

        void setAssignmentsInProgress(int assignmentsInProgress);

        void setAssignmentsOnHold(int assignmentsOnHold);

        void setAssignmentsWaitingForDispatch(int assignmentsWaitingForDispatch);

        void setAvatarUri(String avatarUri);

        void setLanguage(String language);
    }

    public ApplicationHeader(int monitorNumber) {
        getModel().setMonitorNumber(monitorNumber);
    }
    
    public void setAvatar(@Nonnull URI avatarUri) {
        Objects.requireNonNull(avatarUri, "avatarUri must not be null");
        getModel().setAvatarUri(avatarUri.toString());
    }

    public void setAssignmentsInProgress(int assignmentsInProgress) {
        getModel().setAssignmentsInProgress(assignmentsInProgress);
    }

    public void setAssignmentsOnHold(int assignmentsOnHold) {
        getModel().setAssignmentsOnHold(assignmentsOnHold);
    }

    public void setAssignmentsWaitingForDispatch(int assignmentsWaitingForDispatch) {
        getModel().setAssignmentsWaitingForDispatch(assignmentsWaitingForDispatch);
    }

    public void setLocale(@Nonnull Locale locale) {
        Objects.requireNonNull(locale, "locale must not be null");
        getModel().setLanguage(locale.getLanguage());
    }
}
