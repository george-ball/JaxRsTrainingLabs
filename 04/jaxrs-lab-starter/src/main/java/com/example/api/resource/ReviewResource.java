package com.example.api.resource;

import com.example.api.model.Review;
import com.example.api.repository.ReviewRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.util.List;

/**
 * Review sub-resource — students complete this in Lab 3.
 *
 * This class does NOT have a @Path annotation at the class level.
 * It is returned by the sub-resource LOCATOR in ProductResource:
 *
 *   @Path("{id}/reviews")
 *   public ReviewResource getReviewResource(@PathParam("id") String id) { ... }
 *
 * The JAX-RS runtime then introspects THIS class to route the remainder
 * of the request, using the @GET / @POST / @Path methods below.
 *
 * Resulting URIs (handled by this class):
 *   GET  /api/products/{id}/reviews         → listAll()
 *   GET  /api/products/{id}/reviews/{rid}   → getById(rid)
 *   POST /api/products/{id}/reviews         → create(review)
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

    private final String           productId;
    private final ReviewRepository reviewRepo = ReviewRepository.getInstance();

    /**
     * Constructor called by the sub-resource locator in ProductResource.
     * The productId is passed in after the locator validates the product exists.
     */
    public ReviewResource(String productId) {
        this.productId = productId;
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.3
    // GET /api/products/{id}/reviews
    // ══════════════════════════════════════════════════════════════════════

    @GET
    public List<Review> listAll() {
        return reviewRepo.findByProduct(productId);
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.3
    // GET /api/products/{id}/reviews/{rid}
    // ══════════════════════════════════════════════════════════════════════

    @GET
    @Path("{rid}")
    public Response getById(@PathParam("rid") String rid) {
        // TODO: find review by rid; return 200 or 404
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.3");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.3
    // POST /api/products/{id}/reviews  →  201 Created + Location header
    // ══════════════════════════════════════════════════════════════════════

    @POST
    public Response create(Review review, @Context UriInfo uriInfo) {
        // TODO:
        //   1. Set review.setProductId(productId)
        //   2. reviewRepo.save(review) — assigns ID and timestamp
        //   3. Build Location URI and return 201
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.3");
    }
}
