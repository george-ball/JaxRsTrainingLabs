package com.example.api.resource;

import com.example.api.model.Product;
import com.example.api.model.ProductList;
import com.example.api.repository.ProductRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Map;

/**
 * Product REST resource — students complete this in Labs 2 and 3.
 *
 * Base URI: /api/products
 * All responses use application/xml (JAXB-annotated beans).
 *
 * LAB 2 — implement the five CRUD methods.
 * LAB 3 — add stock/activate/deactivate sub-resource methods
 *          and a sub-resource locator for reviews.
 *
 * Error responses: TEXT_PLAIN "error=...\nstatus=...\n"
 * (Plain-text errors avoid needing a JAXB-annotated error bean
 *  and are easy to read with curl.)
 *
 * Repository methods available:
 *   repo.find(category, page, size, activeOnly) -> List<Product>
 *   repo.findById(id)                           -> Product (null if missing)
 *   repo.save(product)                          -> Product (with generated ID)
 *   repo.update(id, product)                    -> Product (null if missing)
 *   repo.delete(id)                             -> boolean
 *   repo.exists(id)                             -> boolean
 */
@Path("/products")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class ProductResource {

    private final ProductRepository repo = ProductRepository.getInstance();

    // =========================================================
    // LAB 2 - Task 2.1
    // GET /api/products
    // GET /api/products?category=books&page=0&size=10&active=true
    // Returns: <products count="N"><product ...>...</product></products>
    // =========================================================

    @GET
    public Response listAll(
            @QueryParam("category")                       String  category,
            @QueryParam("page")    @DefaultValue("0")     int     page,
            @QueryParam("size")    @DefaultValue("20")    int     size,
            @QueryParam("active")  @DefaultValue("true")  boolean activeOnly) {

        // TODO: call repo.find(...), wrap in ProductList, return 200
        // Note: empty list -> 200 OK with <products count="0"/>, NOT 404
        throw new UnsupportedOperationException("TODO - Lab 2 Task 2.1");
    }

    // =========================================================
    // LAB 2 - Task 2.2
    // GET /api/products/{id}
    // =========================================================

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id) {
        // TODO: find by id; 200 with <product> or TEXT_PLAIN 404
        throw new UnsupportedOperationException("TODO - Lab 2 Task 2.2");
    }

    // =========================================================
    // LAB 2 - Task 2.3
    // POST /api/products  ->  201 Created + Location header
    // =========================================================

    @POST
    public Response create(Product product, @Context UriInfo uriInfo) {
        // TODO:
        //   1. Validate product.getName() not blank -> TEXT_PLAIN 400 if invalid
        //   2. repo.save(product) - assigns the generated ID
        //   3. Build Location: uriInfo.getAbsolutePathBuilder().path(saved.getId()).build()
        //   4. Return Response.created(location).entity(saved).build()
        throw new UnsupportedOperationException("TODO - Lab 2 Task 2.3");
    }

    // =========================================================
    // LAB 2 - Task 2.4
    // PUT /api/products/{id}  ->  200 with updated product (or 404)
    // =========================================================

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, Product product) {
        // TODO: repo.update(id, product) returns null if not found -> TEXT_PLAIN 404
        throw new UnsupportedOperationException("TODO - Lab 2 Task 2.4");
    }

    // =========================================================
    // LAB 2 - Task 2.4
    // DELETE /api/products/{id}  ->  204 No Content (or 404)
    // =========================================================

    @DELETE
    @Path("{id}")
    // @Secured  <- add in Lab 4
    public Response delete(@PathParam("id") String id) {
        // TODO: repo.delete(id) returns false if not found -> TEXT_PLAIN 404
        //       on success: Response.noContent().build()  (204)
        throw new UnsupportedOperationException("TODO - Lab 2 Task 2.4");
    }

    // =========================================================
    // LAB 3 - Task 3.1
    // GET  /api/products/{id}/stock
    // PUT  /api/products/{id}/stock   body: <stock><quantity>50</quantity></stock>
    // =========================================================

    // TODO: implement getStock() and updateStock() here

    // =========================================================
    // LAB 3 - Task 3.2
    // PUT /api/products/{id}/activate
    // PUT /api/products/{id}/deactivate
    // =========================================================

    // TODO: implement activate() and deactivate() here

    // =========================================================
    // LAB 3 - Task 3.3
    // Sub-resource LOCATOR (no HTTP verb annotation):
    //   /api/products/{id}/reviews  ->  ReviewResource
    // =========================================================

    // TODO:
    // @Path("{id}/reviews")
    // public ReviewResource getReviewResource(@PathParam("id") String id) { ... }
}
