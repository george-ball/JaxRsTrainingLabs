package com.example.api.resource;

import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;
import java.util.Map;

/**
 * Product REST resource.  Students implement this in Labs 2 and 3.
 *
 * Base URI (via ShopApplication @ApplicationPath("/api")):
 *   http://localhost:8080/dispatch-lab/api/products
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * LAB 2 — @Path Templates
 *   Task 2.2  listAll() and getById()
 *   Task 2.3  getByNumericId() and getBySku() — regex-constrained paths
 *   Task 2.4  getFeatured() — literal path beats template
 *   Task 2.5  search() and archive() — query params + multi-segment path
 *
 * LAB 3 — HTTP Methods & Content Negotiation
 *   Task 3.1  create(), update(), delete(), head()
 *   Task 3.3  summary() — content negotiation (JSON and text/plain)
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * DO NOT add @Path at class level here if implementing the interface in Lab 5.
 * For Labs 2-4 use @Path("/products") directly on this class.
 * For Lab 5 the interface takes over.
 */
@Path("/products")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class ProductResource {

    private final ProductRepository repo = ProductRepository.getInstance();

    // ── LAB 2 Task 2.2 ────────────────────────────────────────────────────
    // GET /api/products
    // GET /api/products?category=books&page=0&size=10
    @GET
    public List<Product> listAll(
            @QueryParam("category")                      String category,
            @QueryParam("page")    @DefaultValue("0")    int    page,
            @QueryParam("size")    @DefaultValue("20")   int    size) {
        // TODO: return repo.find(category, page, size, true)
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.2");
    }

    // ── LAB 2 Task 2.2 ────────────────────────────────────────────────────
    // GET /api/products/{id}   (plain string — fallback for unmatched patterns)
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id) {
        // TODO: repo.findById(id); 200 or 404
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.2");
    }

    // ── LAB 2 Task 2.3 ────────────────────────────────────────────────────
    // GET /api/products/42      (numeric id — regex: \d+)
    // TODO: implement @GET @Path("{dbId: \\d+}")
    // Hint: @PathParam("dbId") int dbId  — type conversion!

    // ── LAB 2 Task 2.3 ────────────────────────────────────────────────────
    // GET /api/products/BOOK-001  (SKU code — regex: [A-Z]+-\d+)
    // TODO: implement @GET @Path("{sku: [A-Z]+-\\d+}")

    // ── LAB 2 Task 2.4 ────────────────────────────────────────────────────
    // GET /api/products/featured  (literal path — must beat {id} template)
    // TODO: implement @GET @Path("featured")
    // Returns: products where isFeatured() == true

    // ── LAB 2 Task 2.5 ────────────────────────────────────────────────────
    // GET /api/products/search?q=java&sort=price
    // TODO: implement @GET @Path("search")

    // GET /api/products/archive/{year}/{quarter}
    // TODO: implement @GET @Path("archive/{year}/{quarter}")
    //   year as int (observe what happens with non-numeric input)

    // ── LAB 3 Task 3.1 ────────────────────────────────────────────────────
    // POST /api/products  →  201 Created + Location header
    // TODO: implement @POST  (use @Context UriInfo for Location)

    // PUT /api/products/{id}  →  200 or 404
    // TODO: implement @PUT @Path("{id}")

    // DELETE /api/products/{id}  →  204 or 404
    // TODO: implement @DELETE @Path("{id}")

    // HEAD /api/products/{id}  →  same headers as GET, no body
    // TODO: implement @HEAD @Path("{id}")

    // ── LAB 3 Task 3.3 ────────────────────────────────────────────────────
    // GET /api/products/{id}/summary
    //   Accept: application/xml  → JSON Product object
    //   Accept: text/plain        → "id | name | price" string
    //   Accept: text/html         → 406 Not Acceptable
    // TODO: implement using Request.selectVariant()
}
