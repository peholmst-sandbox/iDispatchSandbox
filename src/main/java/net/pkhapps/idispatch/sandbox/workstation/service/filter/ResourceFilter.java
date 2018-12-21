package net.pkhapps.idispatch.sandbox.workstation.service.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class ResourceFilter implements Serializable {
    private final String callSignFilter;
}
