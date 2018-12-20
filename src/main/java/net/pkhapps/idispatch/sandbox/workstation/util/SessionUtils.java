package net.pkhapps.idispatch.sandbox.workstation.util;

import com.vaadin.flow.server.VaadinSession;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class SessionUtils {

    private SessionUtils() {
    }

    @Nonnull
    public static <T> T getSessionScoped(@Nonnull Class<T> type, @Nonnull Supplier<T> factory) {
        var session = VaadinSession.getCurrent();
        if (session == null) {
            throw new IllegalStateException("No VaadinSession bound to current thread");
        }
        var object = session.getAttribute(type);
        if (object == null) {
            object = factory.get();
            session.setAttribute(type, object);
        }
        return object;
    }
}
