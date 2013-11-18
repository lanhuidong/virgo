package com.nexusy.virgo.web.security;

import com.nexusy.virgo.data.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lan
 * @since 2013-11-16
 */
public class VirgoSecurityContext implements SecurityContext {

    private static final long serialVersionUID = -3548462167839567383L;

    /**
     * Get login user.
     */
    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication == null || authentication.getPrincipal() instanceof String) ? null : (User) authentication.getPrincipal();
    }

    @Override
    public Authentication getAuthentication() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        throw new UnsupportedOperationException();
    }
}
