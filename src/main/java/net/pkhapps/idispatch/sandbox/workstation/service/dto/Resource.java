package net.pkhapps.idispatch.sandbox.workstation.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Builder
public class Resource {

    private final ResourceId id;
    private final String callSign;
    private final Instant lastCheckIn;
    private final Status status;
    private final Location location;
    private final AssignmentSummary assignment;
    private final StationId station;
}
