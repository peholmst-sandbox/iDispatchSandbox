package net.pkhapps.idispatch.sandbox.workstation.dummy;

import net.pkhapps.idispatch.sandbox.workstation.service.StatusLookupService;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Status;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.StatusId;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@ThreadSafe
public class DummyStatusService implements StatusLookupService {

    private static final DummyStatusService INSTANCE = new DummyStatusService();

    @Nonnull
    public static DummyStatusService getInstance() {
        return INSTANCE;
    }

    private final Map<StatusId, Status> statusMap = new HashMap<>();
    private final Random rnd = new Random();

    private DummyStatusService() {
        addStatus("reserverad", "varattu", "#ffff00");
        addStatus("alarmerad", "hälytetty", "#ff5f5f");
        addStatus("på väg", "matkalla", "#00c3ff");
        addStatus("vid objektet", "kohteessa", "#0064ff");
        addStatus("ledig på radio", "vapaa radiolla", "#c8ff3c");
        addStatus("ledig på stationen", "vapaa asemalla", "#64ff00");
        addStatus("ur bruk", "ei hälytettävissä", "#ffa500");
    }

    private void addStatus(String nameSwe, String nameFin, String primaryColor) {
        var status = new Status(new StatusId(statusMap.size() + 1), nameSwe, nameFin, primaryColor);
        statusMap.put(status.getId(), status);
    }

    @Nonnull
    @Override
    public Stream<Status> fetch() {
        return statusMap.values().stream();
    }

    @Nonnull
    @Override
    public Optional<Status> fetch(@Nonnull StatusId statusId) {
        return Optional.ofNullable(statusMap.get(statusId));
    }

    @Nonnull
    public Status random() {
        return statusMap.get(randomId());
    }

    @Nonnull
    public StatusId randomId() {
        return new StatusId(rnd.nextInt(statusMap.size()) + 1);
    }
}
