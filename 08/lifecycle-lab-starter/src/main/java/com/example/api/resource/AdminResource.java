package com.example.api.resource;

import com.example.api.filter.Secured;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Demonstrates class-level @Secured with mixed method-level role annotations.
 *
 * LAB 4 — Task 4.3
 *
 * All methods require authentication (class-level @Secured).
 * Individual methods further restrict access with @RolesAllowed / @PermitAll.
 *
 * Returns TEXT_PLAIN so responses are readable without a JSON parser.
 *
 * Registered tokens (from TokenStore):
 *   "user-token"  → principal="alice",  roles=["user"]
 *   "admin-token" → principal="bob",    roles=["user","admin"]
 */
@Path("/admin")
@Secured
@Produces(MediaType.TEXT_PLAIN)
public class AdminResource {

    // GET /api/admin/stats — requires "admin" role
    // TODO Lab 4 Task 4.3: add @RolesAllowed("admin")
    @GET
    @Path("stats")
    public Response stats(@Context SecurityContext sc) {
        String caller = sc.getUserPrincipal() != null
            ? sc.getUserPrincipal().getName() : "anonymous";
        return Response.ok(
            "calledBy=" + caller + "\nproductCount=6\nmessage=Admin statistics\n"
        ).build();
    }

    // GET /api/admin/ping — overrides @Secured, no auth needed
    // TODO Lab 4 Task 4.3: add @PermitAll
    @GET
    @Path("ping")
    public Response ping() {
        return Response.ok("status=pong\n").build();
    }

    // GET /api/admin/locked — always 403 regardless of role
    // TODO Lab 4 Task 4.3: add @DenyAll
    @GET
    @Path("locked")
    public Response locked() {
        return Response.ok("status=accessible\n").build();
    }

    // GET /api/admin/reports — accessible by "admin" or "manager"
    // TODO Lab 4 Task 4.3: add @RolesAllowed({"admin","manager"})
    @GET
    @Path("reports")
    public Response reports(@Context SecurityContext sc) {
        return Response.ok(
            "calledBy=" + sc.getUserPrincipal().getName() +
            "\nreports=Q1-2024,Q2-2024\n"
        ).build();
    }
}
