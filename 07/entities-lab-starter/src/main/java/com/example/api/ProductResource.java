package com.example.api;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Product resource.
 *
 * Lab 1: GET /api/products/{id} with Accept:text/csv routes to ProductCsvWriter.
 *        POST /api/products/csv with Content-Type:text/csv routes to ProductCsvReader.
 * Lab 3: GET /api/products/{id} with Accept:application/xml returns JAXB XML.
 */
@Path("/products")
@Produces({MediaType.APPLICATION_XML, "text/csv"})
@Consumes({MediaType.APPLICATION_XML, "text/csv"})
public class ProductResource {

    private final ProductRepository repo = ProductRepository.getInstance();

    /**
     * GET /api/products
     * Returns the full product list as XML.
     * JAXB marshals List<Product> automatically via jersey-media-jaxb.
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Product> listAll() { return repo.findAll(); }

    /**
     * GET /api/products/{id}
     * Accept: application/xml → JAXB marshals Product to XML
     * Accept: text/csv        → ProductCsvWriter handles response (Lab 1)
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, "text/csv"})
    public Response getById(@PathParam("id") String id) {
        Product p = repo.findById(id);
        if (p == null) return Response.status(404).build();
        return Response.ok(p).build();
    }

    /**
     * POST /api/products
     * Accepts XML body, returns 201 Created with XML body.
     *
     * curl -X POST http://localhost:8080/entities-lab/api/products \
     *      -H 'Content-Type: application/xml' \
     *      -H 'Accept: application/xml' \
     *      -d '<?xml version="1.0"?><product><name>New Book</name>...</product>'
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response create(Product product, @Context UriInfo uriInfo) {
        Product saved = repo.save(product);
        return Response.created(
            uriInfo.getAbsolutePathBuilder().path(saved.getId()).build()
        ).entity(saved).build();
    }

    /**
     * POST /api/products/csv — creates from CSV body (Lab 1 Task 1.2)
     * Works only after ProductCsvReader is implemented.
     *
     * curl -X POST http://localhost:8080/entities-lab/api/products/csv \
     *      -H 'Content-Type: text/csv' \
     *      -H 'Accept: application/xml' \
     *      -d $'id,name,category,price\nPNEW,Refactoring,books,44.99'
     */
    @POST
    @Path("csv")
    @Consumes("text/csv")
    @Produces(MediaType.APPLICATION_XML)
    public Response createFromCsv(Product product, @Context UriInfo uriInfo) {
        Product saved = repo.save(product);
        return Response.created(
            uriInfo.getBaseUriBuilder().path("/api/products").path(saved.getId()).build()
        ).entity(saved).build();
    }
}
