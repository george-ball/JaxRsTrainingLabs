package com.example.api.resource;

import com.example.api.filter.ProductFilter;
import com.example.api.model.OrderStatus;
import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.*;

/**
 * Parameter demonstration resource. Students complete this in Labs 1, 3, and 4.
 * Base URI: /api/params
 *
 * Note on return types: diagnostic echo endpoints in Labs 1 and 3 return
 * TEXT_PLAIN so each value is visible as a plain key=value line.
 * Endpoints that return domain objects (Lab 4) use APPLICATION_XML.
 */
@Path("/params")
public class ParamTestResource {

    private final ProductRepository repo = ProductRepository.getInstance();

    // ══════════════════════════════════════════════════════════════════════
    // LAB 1 — Task 1.1: Primitive vs Boxed behaviour
    // ══════════════════════════════════════════════════════════════════════

    /** GET /api/params/primitive?count=5 — uses int (primitive) */
    @GET @Path("primitive")
    @Produces(MediaType.TEXT_PLAIN)
    public Response primitiveParam(@QueryParam("count") int count) {
        // TODO: return plain text: "count=" + count + "\ntype=int primitive"
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.1");
    }

    /** GET /api/params/boxed?count=5 — uses Integer (boxed) */
    @GET @Path("boxed")
    @Produces(MediaType.TEXT_PLAIN)
    public Response boxedParam(@QueryParam("count") Integer count) {
        // TODO: return plain text showing value and whether it is null
        // e.g. "count=" + count + "\nisNull=" + (count == null)
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.1");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 1 — Task 1.2: Enum parameter
    // ══════════════════════════════════════════════════════════════════════

    /** GET /api/params/orders?status=NEW */
    @GET @Path("orders")
    @Produces(MediaType.TEXT_PLAIN)
    public Response byStatus(@QueryParam("status") OrderStatus status) {
        // TODO: return "filterStatus=" + (status == null ? "(all)" : status.name())
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.2");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 1 — Task 1.3: UUID @PathParam
    // ══════════════════════════════════════════════════════════════════════

    /** GET /api/params/items/{uuid} */
    @GET @Path("items/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getByUuid(@PathParam("id") java.util.UUID id) {
        // TODO: return "uuid=" + id.toString()
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.3");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 1 — Task 1.4: LocalDate via custom ParamConverter
    // ══════════════════════════════════════════════════════════════════════

    /** GET /api/params/events?from=2024-01-01&to=2024-12-31 */
    @GET @Path("events")
    @Produces(MediaType.TEXT_PLAIN)
    public Response events(@QueryParam("from") LocalDate from,
                           @QueryParam("to")   LocalDate to) {
        // TODO: return "from=" + from + "\nto=" + to
        // (implement LocalDateConverterProvider first)
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.4");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.1: All param sources in one endpoint
    // ══════════════════════════════════════════════════════════════════════

    /**
     * GET /api/params/diagnostic/{id};version=2?filter=active
     * Headers: X-Request-ID, Accept-Language
     * Cookie:  THEME=dark
     */
    @GET @Path("diagnostic/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response diagnostic(
            @PathParam("id")                String   id,
            @QueryParam("filter")           String   filter,
            @HeaderParam("X-Request-ID")    String   requestId,
            @HeaderParam("Accept-Language") String   lang,
            @CookieParam("THEME")           String   theme,
            @MatrixParam("version")         String   version,
            @Context UriInfo                uriInfo) {
        // TODO: return all values as plain text, one per line:
        //   "pathId=" + id
        //   "query.filter=" + filter
        //   "header.X-Request-ID=" + requestId
        //   "header.Accept-Language=" + lang
        //   "cookie.THEME=" + theme
        //   "matrix.version=" + version
        //   "absoluteUri=" + uriInfo.getAbsolutePath()
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.1");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.2: @FormParam login
    // ══════════════════════════════════════════════════════════════════════

    /** POST /api/params/login  body: username=...&password=... */
    @POST @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password) {
        // TODO: if username=admin and password=secret return 200 "token=abc123"
        //       else return 401 "error=Invalid credentials"
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.2");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.3: Product list with @BeanParam
    // ══════════════════════════════════════════════════════════════════════

    /**
     * GET /api/params/products?category=books&page=0&size=20&sort=name
     *
     * In Task 3.3 you refactor this 4-parameter signature to use
     * @BeanParam ProductFilter instead.
     */
    @GET @Path("products")
    @Produces(MediaType.APPLICATION_XML)
    public List<Product> listProducts(
            @QueryParam("category")                      String category,
            @QueryParam("page")    @DefaultValue("0")    int    page,
            @QueryParam("size")    @DefaultValue("20")   int    size,
            @QueryParam("sort")    @DefaultValue("name") String sort) {
        // Returns XML list of products — JAXB marshals List<Product>
        return repo.find(category, page, size, true);
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 4 — Task 4.1: @DefaultValue test matrix
    // ══════════════════════════════════════════════════════════════════════

    /** GET /api/params/defaults */
    @GET @Path("defaults")
    @Produces(MediaType.TEXT_PLAIN)
    public Response defaults(
            @QueryParam("page")   @DefaultValue("0")    int         page,
            @QueryParam("size")   @DefaultValue("20")   Integer     size,
            @QueryParam("active") @DefaultValue("true") boolean     active,
            @QueryParam("status") @DefaultValue("NEW")  OrderStatus status) {
        // TODO: return all four values as plain text, one per line
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.1");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 4 — Task 4.2: Response builder patterns
    // ══════════════════════════════════════════════════════════════════════

    /** POST /api/params/products  body: <product>...</product>  → 201 Created */
    @POST @Path("products")
    @Consumes(MediaType.APPLICATION_XML)     // accepts XML body
    @Produces(MediaType.APPLICATION_XML)     // returns XML body
    public Response createProduct(Product product, @Context UriInfo uriInfo) {
        // TODO: repo.save(product); build Location; return 201
        // The Product bean is unmarshalled from XML by JAXB automatically.
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.2");
    }

    /** GET /api/params/products/{id}/etag  — conditional GET with ETag */
    @GET @Path("products/{id}/etag")
    @Produces(MediaType.APPLICATION_XML)
    public Response getWithEtag(@PathParam("id") String id, @Context Request req) {
        // TODO: find product; create EntityTag; call req.evaluatePreconditions(etag)
        //       if 304 applicable return that; else return 200 with ETag + Cache-Control
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.2");
    }

    /** GET /api/params/redirect?to={id}  → 302 Found */
    @GET @Path("redirect")
    public Response redirect(@QueryParam("to") String to) {
        // TODO: if 'to' absent return 400; else return 302 Location /api/params/products/{to}
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.2");
    }

    /** GET /api/params/typed  — GenericEntity for type-safe XML list */
    @GET @Path("typed")
    @Produces(MediaType.APPLICATION_XML)
    public Response typedCollection() {
        // TODO: wrap List<Product> in GenericEntity<List<Product>> and return 200
        // GenericEntity preserves the generic type so JAXB knows to marshal a list.
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.2");
    }
}
