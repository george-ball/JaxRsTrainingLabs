package com.example.api.resource;

import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

/**
 * Content negotiation demonstration resource.  Students implement this in Lab 2.
 * Base URI: /api/content
 *
 * LAB 2 — Tasks 2.1, 2.2, 2.3
 */
@Path("/content")
@Produces(MediaType.APPLICATION_XML)   // class-level default — XML
public class ContentResource {

    private final ProductRepository repo = ProductRepository.getInstance();

    // ── LAB 2 Task 2.1 ────────────────────────────────────────────────────

    /**
     * GET /api/content/xml  — inherits @Produces(APPLICATION_XML) from class.
     * Returns a simple XML response confirming the format.
     *
     * curl -H 'Accept: application/xml' http://localhost:8080/params-lab/api/content/xml
     */
    @GET @Path("xml")
    public Response getXml() {
        // TODO: return a Response with a simple XML string body, e.g.:
        //   Response.ok("<format>xml</format>", MediaType.APPLICATION_XML).build()
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.1");
    }

    /**
     * GET /api/content/text  — overrides class default to TEXT_PLAIN.
     *
     * curl -H 'Accept: text/plain' http://localhost:8080/params-lab/api/content/text
     */
    @GET @Path("text")
    @Produces(MediaType.TEXT_PLAIN)     // overrides class @Produces
    public String getText() {
        // TODO: return "This is plain text"
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.1");
    }

    // ── LAB 2 Task 2.2 ────────────────────────────────────────────────────

    /**
     * POST /api/content/ingest  — accepts only APPLICATION_XML.
     * Test with wrong Content-Type to trigger 415 Unsupported Media Type.
     *
     * # Correct Content-Type → 200
     * curl -X POST http://.../api/content/ingest \
     *      -H 'Content-Type: application/xml' \
     *      -d '<?xml version="1.0"?><data><x>1</x></data>'
     *
     * # Wrong Content-Type → 415
     * curl -X POST http://.../api/content/ingest \
     *      -H 'Content-Type: text/plain' -d 'hello'
     */
    @POST @Path("ingest")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Response ingest(String rawXml) {
        // TODO: return 200 with body "received=" + rawXml.length() + " chars"
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.2");
    }

    // ── LAB 2 Task 2.3 ────────────────────────────────────────────────────

    /**
     * GET /api/content/products
     *   Accept: application/xml  → XML product list (JAXB marshalled)
     *   Accept: text/csv         → CSV download with Content-Disposition
     *   Accept: text/html        → 406 Not Acceptable
     *
     * Use Request.selectVariant() for standards-compliant negotiation.
     */
    @GET @Path("products")
    @Produces({MediaType.APPLICATION_XML, "text/csv"})
    public Response products(@Context Request req) {
        // TODO:
        //   1. Build Variant list for APPLICATION_XML_TYPE and text/csv MediaType
        //   2. req.selectVariant(variants) — if null return notAcceptable(variants)
        //   3. If CSV: build CSV string, return with:
        //        Content-Type: text/csv
        //        Content-Disposition: attachment; filename="products.csv"
        //   4. If XML: return repo.findAll() — JAXB marshals List<Product>
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.3");
    }
}
