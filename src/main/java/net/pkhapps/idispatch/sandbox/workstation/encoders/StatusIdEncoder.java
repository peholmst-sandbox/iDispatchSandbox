package net.pkhapps.idispatch.sandbox.workstation.encoders;

import com.vaadin.flow.templatemodel.ModelEncoder;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.StatusId;

public class StatusIdEncoder implements ModelEncoder<StatusId, String> {

    @Override
    public String encode(StatusId value) {
        return value == null ? null : Long.toString(value.getId());
    }

    @Override
    public StatusId decode(String value) {
        return value == null ? null : new StatusId(Long.valueOf(value));
    }
}
