package com.example.api.resource;

import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Product REST resource — students complete this in Labs 2 and 3.
 *
 * Base URI: /api/products
 *
 * ─────────────────────────────────────────────────────────────────
 * LAB 2 — Implement the five CRUD methods below.
 * LAB 3 — Add sub-resource methods (stock, activate/deactivate)
 *          and a sub-resource locator for reviews.
 * ─────────────────────────────────────────────────────────────────
 *
 * Available repository methods:
 *   repo.find(category, page, size, activeOnly) → List<Product>
 *   repo.findById(id)                           → Product (null if missing)
 *   repo.save(product)                          → Product (with generated ID)
 *   repo.update(id, product)                    → Product (null if missing)
 *   repo.delete(id)                             → boolean
 *   repo.exists(id)                             → boolean
 */
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductRepository repo = ProductRepository.getInstance();

    // ══════════════════════════════════════════════════════════════════════
    // LAB 2 — Task 2.1
    // GET /api/products
    // GET /api/products?category=books&page=0&size=10&active=true
    // ══════════════════════════════════════════════════════════════════════

    @GET
    public Response listAll(
            @QueryParam("category")                       String  category,
            @QueryParam("page")    @DefaultValue("0")     int     page,
            @QueryParam("size")    @DefaultValue("20")    int     size,
            @QueryParam("active")  @DefaultValue("true")  boolean activeOnly) {

        // TODO: call repo.find(...) and return 200 with the list
        // Hint: an empty list should return 200 OK, not 404
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.1");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 2 — Task 2.2
    // GET /api/products/{id}
    // ══════════════════════════════════════════════════════════════════════

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id) {
        // TODO: find by id; return 200 with product or 404 if null
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.2");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 2 — Task 2.3
    // POST /api/products  →  201 Created + Location header
    // ══════════════════════════════════════════════════════════════════════

    @POST
    public Response create(Product product, @Context UriInfo uriInfo) {
        // TODO:
        //   1. Validate product.getName() is not blank → 400 if invalid
        //   2. repo.save(product) — assigns the generated ID
        //   3. Build Location URI: uriInfo.getAbsolutePathBuilder().path(saved.getId()).build()
        //   4. Return Response.created(location).entity(saved).build()
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.3");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 2 — Task 2.4
    // PUT /api/products/{id}  →  200 with updated product (or 404)
    // ══════════════════════════════════════════════════════════════════════

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, Product product) {
        // TODO: repo.update(id, product) returns null if not found → 404
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.4");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 2 — Task 2.4
    // DELETE /api/products/{id}  →  204 No Content (or 404)
    // ══════════════════════════════════════════════════════════════════════

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        // TODO: repo.delete(id) returns false if not found → 404
        // LAB 4: add @Secured annotation to require Bearer token
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.4");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 2 — Task 2.5
    // GET /api/products/{id}/summary
    // Produces JSON or text/plain depending on Accept header
    // ══════════════════════════════════════════════════════════════════════

    @GET
    @Path("{id}/summary")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getSummary(@PathParam("id") String id) {
        // TODO: find product; 404 if missing
        // TODO: return appropriate representation based on negotiated media type
        // Hint: inject @Context Request and use request.selectVariant(variants)
        // OR simply check the Accept header with @HeaderParam
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.5");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.1
    // GET  /api/products/{id}/stock
    // PUT  /api/products/{id}/stock  body: { "quantity": 50 }
    // ══════════════════════════════════════════════════════════════════════

    // TODO: implement getStock() and updateStock() sub-resource methods here

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.2
    // PUT /api/products/{id}/activate
    // PUT /api/products/{id}/deactivate
    // ══════════════════════════════════════════════════════════════════════

    // TODO: implement activate() and deactivate() sub-resource methods here

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.3
    // Sub-resource LOCATOR for reviews:
    //   /api/products/{id}/reviews  and  /api/products/{id}/reviews/{rid}
    // ══════════════════════════════════════════════════════════════════════

    // TODO: implement the sub-resource locator method here
    //   @Path("{id}/reviews")
    //   public ReviewResource getReviewResource(@PathParam("id") String id) { ... }
}
