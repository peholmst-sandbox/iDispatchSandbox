package net.pkhapps.idispatch.sandbox.workstation.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Location implements Serializable {

    private final String crs;
    private final Double x;
    private final Double y;
}
