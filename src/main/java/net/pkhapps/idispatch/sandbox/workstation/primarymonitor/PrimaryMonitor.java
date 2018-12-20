package net.pkhapps.idispatch.sandbox.workstation.primarymonitor;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import net.pkhapps.idispatch.sandbox.workstation.AbstractMonitor;

@Route("primary")
@PageTitle("iDispatch Workstation Monitor #1")
@Tag("primary-monitor")
@HtmlImport("frontend://src/monitors/primary/primary-monitor.html")
public class PrimaryMonitor extends AbstractMonitor<TemplateModel> {

    public PrimaryMonitor() {
    }
}
