package net.pkhapps.idispatch.sandbox.workstation.encoders;

import com.vaadin.flow.templatemodel.ModelEncoder;

import java.time.Instant;

public class InstantEncoder implements ModelEncoder<Instant, String> {

    @Override
    public String encode(Instant value) {
        return value == null ? null : value.toString();
    }

    @Override
    public Instant decode(String value) {
        return value == null ? null : Instant.parse(value);
    }
}
