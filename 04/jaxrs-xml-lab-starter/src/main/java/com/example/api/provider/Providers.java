package com.example.api.provider;

import com.example.api.model.Product;

import javax.annotation.Priority;
import javax.ws.rs.*;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;
import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

// =========================================================================
//  Provider skeletons for Lab 4.
//
//  SAFE DEFAULTS - the app works fully before Lab 4 is started:
//    CorsFilter        - no-op (headers absent; curl works fine)
//    NotFoundMapper    - re-throws (Tomcat shows its default page)
//    GlobalMapper      - re-throws (Tomcat shows its default 500 page)
//    BearerTokenFilter - no-op (DELETE unprotected until Task 4.3)
//    ProductCsvWriter  - isWriteable()=false (inactive until Task 4.4)
//
//  Implement each TODO block in order during Lab 4.
// =========================================================================


// ── @NameBinding annotation ───────────────────────────────────────────────

/**
 * @NameBinding annotation restricts BearerTokenFilter to @Secured endpoints.
 * LAB 4 - Task 4.3
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface Secured {}


// ── NotFoundExceptionMapper (Lab 4 Task 4.1) ─────────────────────────────

/**
 * Maps NotFoundException -> TEXT_PLAIN 404 response.
 *
 * DEFAULT: re-throws so Tomcat renders its default error page.
 *
 * LAB 4 - Task 4.1: replace the throw with:
 *   return Response
 *       .status(Response.Status.NOT_FOUND)
 *       .entity("error=Not Found\ndetail=" + ex.getMessage() + "\nstatus=404\n")
 *       .type(MediaType.TEXT_PLAIN)
 *       .build();
 */
@Provider
class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException ex) {
        // TODO (Task 4.1) - return TEXT_PLAIN 404:
        // return Response.status(Response.Status.NOT_FOUND)
        //     .entity("error=Not Found\ndetail=" + ex.getMessage() + "\nstatus=404\n")
        //     .type(MediaType.TEXT_PLAIN)
        //     .build();
        throw ex;   // safe default
    }
}


// ── GlobalExceptionMapper (Lab 4 Task 4.1) ───────────────────────────────

/**
 * Catch-all for any unhandled Exception -> TEXT_PLAIN 500.
 *
 * DEFAULT: re-throws so Tomcat renders its default 500 page.
 *
 * LAB 4 - Task 4.1: replace the throw with:
 *   System.err.println("[ERROR] Unhandled: " + ex.getMessage());
 *   return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
 *       .entity("error=Internal Server Error\nstatus=500\n")
 *       .type(MediaType.TEXT_PLAIN)
 *       .build();
 */
@Provider
class GlobalExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception ex) {
        // TODO (Task 4.1) - return TEXT_PLAIN 500
        if (ex instanceof RuntimeException) throw (RuntimeException) ex;
        throw new RuntimeException(ex);   // safe default
    }
}


// ── CorsFilter (Lab 4 Task 4.2) ──────────────────────────────────────────

/**
 * Response filter adding CORS headers to every response.
 * DEFAULT: no-op (CORS headers simply absent before Task 4.2).
 *
 * LAB 4 - Task 4.2: replace the empty body with:
 *   resp.getHeaders().add("Access-Control-Allow-Origin",  "*");
 *   resp.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
 *   resp.getHeaders().add("Access-Control-Allow-Headers", "Authorization, Content-Type");
 *   resp.getHeaders().add("Access-Control-Max-Age",       "86400");
 */
@Provider
@Priority(Priorities.HEADER_DECORATOR)
class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext resp) {
        // TODO (Task 4.2) - add CORS headers
    }
}


// ── BearerTokenFilter (Lab 4 Task 4.3) ───────────────────────────────────

/**
 * Auth filter requiring Bearer token on @Secured endpoints.
 * Valid token: "secret-token-123"
 * DEFAULT: no-op (DELETE is unprotected until Task 4.3).
 *
 * LAB 4 - Task 4.3: replace the empty body with:
 *   String auth = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
 *   if (auth == null || !auth.equals("Bearer " + VALID_TOKEN)) {
 *       ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
 *           .entity("error=Unauthorized\nstatus=401\n")
 *           .type(MediaType.TEXT_PLAIN)
 *           .build());
 *   }
 */
@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
class BearerTokenFilter implements ContainerRequestFilter {
    private static final String VALID_TOKEN = "secret-token-123";
    @Override
    public void filter(ContainerRequestContext ctx) {
        // TODO (Task 4.3) - enforce Bearer token
    }
}


// ── ProductCsvWriter (Lab 4 Task 4.4) ────────────────────────────────────

/**
 * Serialises List<Product> to text/csv.
 * Also add @Produces("text/csv") to ProductResource.listAll().
 * DEFAULT: isWriteable()=false so never selected until implemented.
 *
 * LAB 4 - Task 4.4: implement isWriteable() and writeTo().
 */
@Provider
@Produces("text/csv")
class ProductCsvWriter implements MessageBodyWriter<List<Product>> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               java.lang.annotation.Annotation[] annotations,
                               MediaType mediaType) {
        // TODO (Task 4.4):
        // if (!List.class.isAssignableFrom(type)) return false;
        // if (genericType instanceof ParameterizedType pt) {
        //     return Product.class.equals(pt.getActualTypeArguments()[0]);
        // }
        // return false;
        return false;   // safe default
    }

    @Override
    public void writeTo(List<Product> products,
                        Class<?> type, Type genericType,
                        java.lang.annotation.Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException {
        // TODO (Task 4.4):
        // PrintWriter pw = new PrintWriter(
        //     new OutputStreamWriter(entityStream, StandardCharsets.UTF_8));
        // pw.println("id,name,category,price,stockLevel,active");
        // for (Product p : products) {
        //     pw.printf("%s,%s,%s,%.2f,%d,%b%n",
        //         p.getId(), p.getName(), p.getCategory(),
        //         p.getPrice(), p.getStockLevel(), p.isActive());
        // }
        // pw.flush();  // do NOT close entityStream - container owns it
    }

    @Override
    public long getSize(List<Product> p, Class<?> t, Type g,
                        java.lang.annotation.Annotation[] a, MediaType m) {
        return -1;
    }
}
