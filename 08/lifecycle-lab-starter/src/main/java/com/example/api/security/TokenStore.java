package com.example.api.security;

import java.util.Map;
import java.util.Set;

/**
 * Simple in-memory token registry.
 * Complete – do not modify.
 *
 * In a real application this would verify JWT signatures, check expiry,
 * and look up user roles from a database. For this lab, two static tokens
 * are registered at class loading time.
 *
 * Valid tokens:
 *   "user-token"  → username="alice",  roles=["user"]
 *   "admin-token" → username="bob",    roles=["user","admin"]
 *
 * Usage in BearerTokenFilter:
 *   UserPrincipal principal = TokenStore.validate(token);
 *   if (principal == null) → invalid / expired token
 */
public final class TokenStore {

    private static final Map<String, UserPrincipal> TOKENS = Map.of(
        "user-token",  new UserPrincipal("alice", Set.of("user")),
        "admin-token", new UserPrincipal("bob",   Set.of("user", "admin"))
    );

    private TokenStore() {}

    /**
     * Validates a token string.
     *
     * @param token the raw token (without "Bearer " prefix)
     * @return the associated UserPrincipal, or null if the token is not recognised
     */
    public static UserPrincipal validate(String token) {
        if (token == null || token.isBlank()) return null;
        return TOKENS.get(token.trim());
    }
}
