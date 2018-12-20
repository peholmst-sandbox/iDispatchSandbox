package net.pkhapps.idispatch.sandbox.workstation.secondarymonitor;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import net.pkhapps.idispatch.sandbox.workstation.components.Accordion;
import net.pkhapps.idispatch.sandbox.workstation.components.ApplicationHeader;
import net.pkhapps.idispatch.sandbox.workstation.util.Gravatar;

import java.util.Locale;

@Route("secondary")
@PageTitle("iDispatch Workstation Monitor #2")
@Tag("secondary-monitor")
@HtmlImport("frontend://src/monitors/secondary/secondary-monitor.html")
public class SecondaryMonitor extends PolymerTemplate<TemplateModel> {

    @Id("header")
    private ApplicationHeader header;

    @Id("sidebar")
    private Accordion sidebar;

    public SecondaryMonitor() {
        // DUMMY DATA:
        {
            header.setAssignmentsInProgress(10);
            header.setAssignmentsOnHold(3);
            header.setAssignmentsWaitingForDispatch(2);
            header.setAvatar(Gravatar.getAvatarUri("petter@vaadin.com"));
            header.setLocale(new Locale("sv"));
        }

        sidebar.addItem("Resurser", new Text("Resurser"));
        sidebar.addItem("Uppdrag", new Text("Uppdrag"));
        sidebar.addItem("Stationsplatser", new Text("Stationsplatser"));
    }
}
