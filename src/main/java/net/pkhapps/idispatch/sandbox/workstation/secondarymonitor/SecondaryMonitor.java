package net.pkhapps.idispatch.sandbox.workstation.secondarymonitor;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import net.pkhapps.idispatch.sandbox.workstation.AbstractMonitor;
import net.pkhapps.idispatch.sandbox.workstation.components.Accordion;

@Route("secondary")
@PageTitle("iDispatch Workstation Monitor #2")
@Tag("secondary-monitor")
@HtmlImport("frontend://src/monitors/secondary/secondary-monitor.html")
public class SecondaryMonitor extends AbstractMonitor<TemplateModel> {

    @Id("sidebar")
    private Accordion sidebar;

    public SecondaryMonitor() {
        sidebar.addItem("Resurser", new Text("Resurser"));
        sidebar.addItem("Uppdrag", new Text("Uppdrag"));
        sidebar.addItem("Stationsplatser", new Text("Stationsplatser"));
    }
}
