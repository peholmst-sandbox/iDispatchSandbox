package net.pkhapps.idispatch.sandbox.workstation.service;

import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.shared.Registration;
import net.pkhapps.idispatch.sandbox.workstation.service.dto.Resource;
import net.pkhapps.idispatch.sandbox.workstation.service.filter.ResourceFilter;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.EventListener;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public interface ResourceLookupService extends Serializable {

    @Nonnull
    Stream<Resource> fetch(@Nonnull Query<Resource, ResourceFilter> query);

    int count(@Nonnull Query<Resource, ResourceFilter> query);

    @Nonnull
    Registration addResourcesChangedListener(@Nonnull ResourcesChangedListener resourcesChangedListener);

    @FunctionalInterface
    interface ResourcesChangedListener extends Serializable, EventListener {

        void onResourcesChanged(@Nonnull ResourcesChangedEvent event);
    }

    class ResourcesChangedEvent implements Serializable {

        private final Set<Resource> addedResources;
        private final Set<Resource> changedResources;
        private final Set<Resource> deletedResources;

        public ResourcesChangedEvent(@Nonnull Collection<Resource> addedResources,
                                     @Nonnull Collection<Resource> changedResources,
                                     @Nonnull Collection<Resource> deletedResources) {
            Objects.requireNonNull(addedResources, "addedResources must not be null");
            Objects.requireNonNull(changedResources, "changedResources must not be null");
            Objects.requireNonNull(deletedResources, "deletedResources must not be null");

            this.addedResources = Set.copyOf(addedResources);
            this.changedResources = Set.copyOf(changedResources);
            this.deletedResources = Set.copyOf(deletedResources);
        }

        @Nonnull
        public Stream<Resource> getAddedResources() {
            return addedResources.stream();
        }

        public boolean hasAddedResources() {
            return addedResources.size() > 0;
        }

        @Nonnull
        public Stream<Resource> getChangedResources() {
            return changedResources.stream();
        }

        public boolean hasChangedResources() {
            return changedResources.size() > 0;
        }

        @Nonnull
        public Stream<Resource> getDeletedResources() {
            return deletedResources.stream();
        }

        public boolean hasDeletedResources() {
            return deletedResources.size() > 0;
        }
    }
}
