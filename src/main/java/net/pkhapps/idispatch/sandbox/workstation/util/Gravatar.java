package net.pkhapps.idispatch.sandbox.workstation.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Nonnull;
import java.net.URI;
import java.util.Objects;

public final class Gravatar {

    private Gravatar() {
    }

    @Nonnull
    public static URI getAvatarUri(@Nonnull String email) {
        Objects.requireNonNull(email, "email must not be null");
        var hash = DigestUtils.md5Hex(email.toLowerCase().trim());
        return URI.create(String.format("https://www.gravatar.com/avatar/%s", hash));
    }
}
