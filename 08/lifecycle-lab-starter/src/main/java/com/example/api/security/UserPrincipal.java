package com.example.api.security;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

/**
 * Authenticated user principal — carries username and role set.
 * Complete – do not modify.
 */
public class UserPrincipal implements Principal {

    private final String      name;
    private final Set<String> roles;

    public UserPrincipal(String name, Set<String> roles) {
        this.name  = name;
        this.roles = Collections.unmodifiableSet(roles);
    }

    @Override
    public String getName() { return name; }

    public Set<String> getRoles() { return roles; }

    @Override
    public String toString() {
        return "UserPrincipal{name='" + name + "', roles=" + roles + "}";
    }
}
