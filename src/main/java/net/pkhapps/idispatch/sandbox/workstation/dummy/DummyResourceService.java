package net.pkhapps.idispatch.sandbox.workstation.dummy;

import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.shared.Registration;
import net.pkhapps.idispatch.sandbox.workstation.service.ResourceLookupService;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Resource;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.ResourceId;
import net.pkhapps.idispatch.sandbox.workstation.service.filter.ResourceFilter;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ThreadSafe
public class DummyResourceService implements ResourceLookupService {

    private static final DummyResourceService INSTANCE = new DummyResourceService();

    private final Map<ResourceId, Resource> resourceMap = new ConcurrentHashMap<>();

    private DummyResourceService() {
        addResource("RVS2");
        addResource("RVSIT3");
        addResource("RVSIT30");
        addResource("RVSLÄ3");
        addResource("RVSLÄ30");
        addResource("RVS10");
        addResource("RVST11");
        addResource("RVST13");
        addResource("RVST15");
        addResource("RVST16");

        addResource("RVST51");
        addResource("RVST57");

        addResource("RVST61");
        addResource("RVST62");
        addResource("RVST63");
        addResource("RVST67");

        addResource("RVST71");
        addResource("RVST73");
        addResource("RVST77");

        addResource("RVST81");
        addResource("RVST83");
        addResource("RVST87");
        addResource("RVST88");

        addResource("RVSL11");
        addResource("RVSL13");
        addResource("RVSL16");
        addResource("RVSL17");

        addResource("RVSL21");
        addResource("RVSL27");

        addResource("RVSR11");

        addResource("RVSR21");

        addResource("RVSN11");
        addResource("RVSN181");
        addResource("RVSN182");
        addResource("RVSN17");

        addResource("RVSN21");
        addResource("RVSN23");
        addResource("RVSN27");

        addResource("RVSPG11");
        addResource("RVSPG13");
        addResource("RVSPG16");
        addResource("RVSPG17");

        addResource("RVSPG21");
        addResource("RVSPG23");
        addResource("RVSPG27");
        addResource("RVSPG29");

        addResource("RVSPG31");
        addResource("RVSPG371");

        addResource("RVSNA11");
        addResource("RVSNA13");
        addResource("RVSNA17");

        addResource("RVSKP11");
        addResource("RVSKP17");

        addResource("RVSKP21");

        addResource("RVSHO11");
        addResource("RVSHO13");
        addResource("RVSHO17");

        addResource("RVSI11");

        addResource("RVSK11");
        addResource("RVSK17");

        addResource("RVSK21");
        addResource("RVSK24");
        addResource("RVSK26");
        addResource("RVSK27");

        addResource("RVSK31");
        addResource("RVSK37");

        addResource("RVSPK11");
        addResource("RVSPK13");
        addResource("RVSPK17");

        addResource("RVSPA11");
        addResource("RVSPA13");
        addResource("RVSPA17");

        addResource("RVSH11");
        addResource("RVSH12");
        addResource("RVSH13");
        addResource("RVSH17");

        addResource("RVSSA11");
        addResource("RVSSA13");
        addResource("RVSSA17");

        addResource("RVSKI11");
        addResource("RVSKI13");
        addResource("RVSKI15");
        addResource("RVSKI17");

        addResource("RVSD11");
        addResource("RVSD13");
        addResource("RVSD17");

        addResource("RVSD41");
        addResource("RVSD47");

        addResource("RVSVÄ11");
        addResource("RVSVÄ17");

        addResource("RVSS11");
        addResource("RVSS12");
        addResource("RVSS16");
        addResource("RVSS17");

        addResource("RVSS21");
        addResource("RVSS22");
        addResource("RVSS23");
        addResource("RVSS26");
        addResource("RVSS27");

        addResource("RVSTA11");
        addResource("RVSTA17");

        addResource("RVSP11");
        addResource("RVSP13");
        addResource("RVSP17");

        addResource("RVSP21");
        addResource("RVSP23");
        addResource("RVSP27");

        addResource("RVSA11");
        addResource("RVSA13");
        addResource("RVSA17");
    }

    private void addResource(String callSign) {
        var resource = Resource.builder()
                .id(new ResourceId(resourceMap.size() + 1))
                .callSign(callSign)
                .status(DummyStatusService.getInstance().random())
                .lastCheckIn(Instant.now())
                .build();
        resourceMap.put(resource.getId(), resource);
    }

    @Nonnull
    public static DummyResourceService getInstance() {
        return INSTANCE;
    }

    @Nonnull
    @Override
    public Stream<Resource> fetch(@Nonnull Query<Resource, ResourceFilter> query) {
        var foundResources = resourceMap.values()
                .stream()
                .filter(toPredicate(query))
                .sorted(toComparator(query))
                .collect(Collectors.toList());

        int fromIndex = query.getOffset();
        int toIndex = fromIndex + query.getLimit();
        if (toIndex > foundResources.size()) {
            toIndex = foundResources.size();
        }

        return foundResources.subList(fromIndex, toIndex).stream();
    }

    @Nonnull
    private Predicate<Resource> toPredicate(@Nonnull Query<Resource, ResourceFilter> query) {
        return query.getFilter().map(this::toPredicate).orElse(resource -> true);
    }

    @Nonnull
    private Predicate<Resource> toPredicate(@Nonnull ResourceFilter filter) {
        return resource -> resource.getCallSign().toLowerCase().contains(filter.getCallSignFilter().toLowerCase());
    }

    @Nonnull
    private Comparator<Resource> toComparator(@Nonnull Query<Resource, ResourceFilter> query) {
        var comparators = query.getSortOrders().stream().map(this::toComparator).collect(Collectors.toList());
        return (o1, o2) -> {
            var result = 0;
            for (var comparator : comparators) {
                result = comparator.compare(o1, o2);
                if (result != 0) {
                    return result;
                }
            }
            return result;
        };
    }

    @Nonnull
    private Comparator<Resource> toComparator(@Nonnull QuerySortOrder sortOrder) {
        if (sortOrder.getSorted().equals("callSign")) {
            return withCorrectOrder(Comparator.comparing(Resource::getCallSign), sortOrder.getDirection());
        } else if (sortOrder.getSorted().equals("lastCheckIn")) {
            return withCorrectOrder(Comparator.comparing(Resource::getLastCheckIn), sortOrder.getDirection());
        } else {
            return (o1, o2) -> 0;
        }
    }

    @Nonnull
    private Comparator<Resource> withCorrectOrder(@Nonnull Comparator<Resource> comparator,
                                                  @Nonnull SortDirection sortDirection) {
        if (sortDirection == SortDirection.ASCENDING) {
            return comparator;
        } else {
            return comparator.reversed();
        }
    }

    @Override
    public int count(@Nonnull Query<Resource, ResourceFilter> query) {
        return (int) resourceMap.values()
                .stream()
                .filter(toPredicate(query))
                .count();
    }

    @Nonnull
    @Override
    public Registration addResourcesChangedListener(@Nonnull ResourcesChangedListener resourcesChangedListener) {
        return new Registration() {
            @Override
            public void remove() {

            }
        };
    }
}
