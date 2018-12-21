package net.pkhapps.idispatch.sandbox.workstation.service.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class StatusId implements Serializable {
    private final long id;
}
