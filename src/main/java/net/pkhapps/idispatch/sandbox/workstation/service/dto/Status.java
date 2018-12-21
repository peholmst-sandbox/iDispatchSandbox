package net.pkhapps.idispatch.sandbox.workstation.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Builder
public class Status implements Serializable {

    private final StatusId id;
    private final String nameSwe;
    private final String nameFin;
    private final String primaryColor;
}
