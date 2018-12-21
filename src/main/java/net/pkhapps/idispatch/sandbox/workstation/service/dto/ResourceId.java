package net.pkhapps.idispatch.sandbox.workstation.service.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
public class ResourceId implements Serializable {
    private final long id;
}
