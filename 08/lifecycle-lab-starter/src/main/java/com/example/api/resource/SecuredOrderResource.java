package com.example.api.resource;

import com.example.api.filter.Secured;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates selective @Secured name-binding on individual methods.
 *
 * LAB 4 — Task 4.2
 *
 * GET  /api/secured/orders  — public, no authentication required
 * POST /api/secured/orders  — protected with @Secured; requires valid Bearer token
 *
 * Returns TEXT_PLAIN so responses are readable without a JSON parser.
 */
@Path("/secured/orders")
@Produces(MediaType.TEXT_PLAIN)
public class SecuredOrderResource {

    // In-memory store — not thread-safe, sufficient for lab purposes
    private static final List<String> ORDERS = new ArrayList<>();

    // ── GET — public, no authentication ──────────────────────────────────
    @GET
    public String list() {
        if (ORDERS.isEmpty()) return "orders=(empty)\n";
        return "orders:\n" + String.join("\n", ORDERS) + "\n";
    }

    // ── POST — protected ─────────────────────────────────────────────────
    // TODO Lab 4 Task 4.2: add @Secured annotation here
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response create(String order,
                           @Context SecurityContext sc) {
        // TODO:
        //   String user = sc.getUserPrincipal().getName();
        //   ORDERS.add(user + ": " + order.trim());
        //   return Response.status(201)
        //       .entity("created=true\ncreatedBy=" + user + "\n").build();
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.2");
    }
}
