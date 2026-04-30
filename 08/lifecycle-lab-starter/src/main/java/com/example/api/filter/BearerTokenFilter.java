package com.example.api.filter;

import com.example.api.security.BearerSecurityContext;
import com.example.api.security.TokenStore;
import com.example.api.security.UserPrincipal;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;


/**
 * Bearer token authentication filter.
 *
 * LAB 4 — Task 4.1
 *
 * This filter runs ONLY on endpoints annotated with @Secured (name-binding).
 * It validates the Authorization: Bearer <token> header and either:
 *   a) installs a SecurityContext (success), or
 *   b) aborts with 401 Unauthorized (failure)
 *
 * Valid tokens (from TokenStore):
 *   "user-token"  → principal="alice",  roles=["user"]
 *   "admin-token" → principal="bob",    roles=["user","admin"]
 *
 * Algorithm:
 *   1. Read Authorization header
 *   2. If null or does not start with "Bearer " → abort(401, "Missing token")
 *   3. Extract the token string after "Bearer "
 *   4. Call TokenStore.validate(token)
 *   5. If null → abort(401, "Invalid or expired token")
 *   6. ctx.setSecurityContext(new BearerSecurityContext(principal, isSecure))
 */
@Provider
@Secured                              // name-binding — only fires for @Secured endpoints
@Priority(Priorities.AUTHENTICATION)  // runs before resource methods
public class BearerTokenFilter implements ContainerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext ctx) {
        // TODO:
        //   String auth = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
        //
        //   if (auth == null || !auth.startsWith(BEARER_PREFIX)) {
        //       abort(ctx, 401, "Missing or malformed Authorization header");
        //       return;
        //   }
        //
        //   String token = auth.substring(BEARER_PREFIX.length()).trim();
        //   UserPrincipal principal = TokenStore.validate(token);
        //
        //   if (principal == null) {
        //       abort(ctx, 401, "Invalid or expired token");
        //       return;
        //   }
        //
        //   ctx.setSecurityContext(new BearerSecurityContext(
        //       principal, ctx.getSecurityContext().isSecure()));
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.1");
    }

    /**
     * Aborts the request with the given status code and message.
     * Always includes WWW-Authenticate header for 401 responses.
     */
    private void abort(ContainerRequestContext ctx, int status, String message) {
        ctx.abortWith(
            Response.status(status)
                .entity("error=" + message + "\nstatus=" + status + "\n")
                .type(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.WWW_AUTHENTICATE, "Bearer realm=\"lifecycle-lab\"")
                .build()
        );
    }
}
