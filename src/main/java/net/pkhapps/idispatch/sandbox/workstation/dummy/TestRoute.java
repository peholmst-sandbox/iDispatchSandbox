package net.pkhapps.idispatch.sandbox.workstation.dummy;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import net.pkhapps.idispatch.sandbox.workstation.model.ApplicationHeaderModel;

import java.util.Random;

@Route("test")
public class TestRoute extends Div {

    private final Random rnd = new Random();

    public TestRoute() {
        add(new Button("Update Application Header Model", event -> updateApplicationHeaderModel()));
    }

    private void updateApplicationHeaderModel() {
        var model = ApplicationHeaderModel.instance();
        model.update(rnd.nextInt(50), rnd.nextInt(20), rnd.nextInt(10));
    }
}
