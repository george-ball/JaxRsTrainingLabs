package com.example.api.resource;

import com.example.api.model.Review;
import com.example.api.model.ReviewList;
import com.example.api.repository.ReviewRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

/**
 * Review sub-resource — students complete this in Lab 3.
 *
 * No @Path at class level — reached via the sub-resource locator in ProductResource:
 *   @Path("{id}/reviews")
 *   public ReviewResource getReviewResource(@PathParam("id") String id) { ... }
 *
 * Jersey introspects this class to route the remainder of the request.
 *
 * Resulting URIs:
 *   GET  /api/products/{id}/reviews          -> listAll()
 *   GET  /api/products/{id}/reviews/{rid}    -> getById()
 *   POST /api/products/{id}/reviews          -> create()
 */
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class ReviewResource {

    private final String           productId;
    private final ReviewRepository reviewRepo = ReviewRepository.getInstance();

    /** Called by the sub-resource locator after validating the product exists. */
    public ReviewResource(String productId) {
        this.productId = productId;
    }

    // GET /api/products/{id}/reviews
    @GET
    public ReviewList listAll() {
        return new ReviewList(productId, reviewRepo.findByProduct(productId));
    }

    // =========================================================
    // LAB 3 - Task 3.3
    // GET /api/products/{id}/reviews/{rid}
    // =========================================================

    @GET
    @Path("{rid}")
    public Response getById(@PathParam("rid") String rid) {
        // TODO: find review by rid; return 200 or TEXT_PLAIN 404
        //       also check review.getProductId().equals(productId)
        throw new UnsupportedOperationException("TODO - Lab 3 Task 3.3");
    }

    // =========================================================
    // LAB 3 - Task 3.3
    // POST /api/products/{id}/reviews  ->  201 Created + Location header
    // =========================================================

    @POST
    public Response create(Review review, @Context UriInfo uriInfo) {
        // TODO:
        //   1. review.setProductId(productId)
        //   2. reviewRepo.save(review) - assigns ID and timestamp
        //   3. Build Location URI and return 201 with the saved review
        throw new UnsupportedOperationException("TODO - Lab 3 Task 3.3");
    }
}
