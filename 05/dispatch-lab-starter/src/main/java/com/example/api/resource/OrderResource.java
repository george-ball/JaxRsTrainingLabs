package com.example.api.resource;

import com.example.api.model.Order;
import com.example.api.repository.OrderRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

/**
 * Order REST resource skeleton.  Students implement this in Lab 3.
 *
 * Base URI: /api/orders
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * LAB 3 — Task 3.1: Implement full CRUD with correct status codes.
 *
 * Status code requirements:
 *   GET    (found)    →  200 OK       (body: order)
 *   GET    (missing)  →  404 Not Found
 *   POST   (create)   →  201 Created  (Location header: /api/orders/{id})
 *   PUT    (found)    →  200 OK       (body: updated order)
 *   PUT    (missing)  →  404 Not Found
 *   DELETE (found)    →  204 No Content  (no body)
 *   DELETE (missing)  →  404 Not Found
 *   HEAD              →  same headers as GET but no body
 *   PATCH  (not impl) →  405 Method Not Allowed (automatic — no method needed)
 * ─────────────────────────────────────────────────────────────────────────────
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class OrderResource {

    private final OrderRepository repo = OrderRepository.getInstance();

    // GET /api/orders
    @GET
    public List<Order> listAll() {
        // TODO: return repo.findAll()
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.1");
    }

    // GET /api/orders/{id}
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id) {
        // TODO: find by id; 200 or 404
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.1");
    }

    // POST /api/orders  →  201 Created + Location
    @POST
    public Response create(Order order, @Context UriInfo uriInfo) {
        // TODO: repo.save(order); build Location; return 201
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.1");
    }

    // PUT /api/orders/{id}  →  200 or 404
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, Order order) {
        // TODO: repo.update(id, order); 200 or 404
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.1");
    }

    // DELETE /api/orders/{id}  →  204 or 404
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        // TODO: repo.delete(id); 204 if deleted, 404 if not found
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.1");
    }

    // HEAD /api/orders/{id}  →  same as GET but no body
    @HEAD
    @Path("{id}")
    public Response head(@PathParam("id") String id) {
        // TODO: return same status + headers as getById() but with no entity body
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.1");
    }
}
