package com.example.api.security;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.*;

/**
 * SecurityContext implementation installed by BearerTokenFilter.
 * Provides the authenticated principal and role-check support.
 * Complete – do not modify.
 */
public class BearerSecurityContext implements SecurityContext {

    private final UserPrincipal principal;
    private final boolean       secure;

    public BearerSecurityContext(UserPrincipal principal, boolean secure) {
        this.principal = principal;
        this.secure    = secure;
    }

    @Override
    public Principal getUserPrincipal() { return principal; }

    @Override
    public boolean isUserInRole(String role) {
        return principal != null && principal.getRoles().contains(role);
    }

    @Override
    public boolean isSecure() { return secure; }

    @Override
    public String getAuthenticationScheme() { return "Bearer"; }
}
