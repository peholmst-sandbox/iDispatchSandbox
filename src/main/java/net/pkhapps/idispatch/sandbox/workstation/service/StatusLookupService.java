package net.pkhapps.idispatch.sandbox.workstation.service;

import net.pkhapps.idispatch.sandbox.workstation.service.dto.Status;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.StatusId;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.stream.Stream;

public interface StatusLookupService {

    @Nonnull
    Stream<Status> fetch();

    @Nonnull
    Optional<Status> fetch(@Nonnull StatusId statusId);
}
