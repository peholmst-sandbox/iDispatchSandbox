package net.pkhapps.idispatch.sandbox.workstation.secondarymonitor;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.pkhapps.idispatch.sandbox.workstation.components.ApplicationHeader;
import net.pkhapps.idispatch.sandbox.workstation.util.Gravatar;

import java.util.Locale;
import java.util.Random;

@Route("secondary")
@PageTitle("iDispatch Workstation Monitor #2")
public class SecondaryMonitor extends Div {

    private final ApplicationHeader header;

    public SecondaryMonitor() {
        header = new ApplicationHeader(2);

        // DUMMY DATA:
        header.setAssignmentsInProgress(10);
        header.setAssignmentsOnHold(3);
        header.setAssignmentsWaitingForDispatch(2);
        header.setAvatar(Gravatar.getAvatarUri("petter@vaadin.com"));
        header.setLocale(new Locale("sv"));

        add(header);

        Random rnd = new Random();
        add(new Button("Change Statistics", evt -> {
            if (rnd.nextBoolean()) {
                header.setAssignmentsInProgress(rnd.nextInt(100));
            }
            if (rnd.nextBoolean()) {
                header.setAssignmentsOnHold(rnd.nextInt(100));
            }
            if (rnd.nextBoolean()) {
                header.setAssignmentsWaitingForDispatch(rnd.nextInt(100));
            }
        }));
    }
}
