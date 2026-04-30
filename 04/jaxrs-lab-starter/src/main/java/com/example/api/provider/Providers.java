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
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

// ═══════════════════════════════════════════════════════════════════════════
//
//  Provider skeletons for Lab 4.
//  Each class is in its own file in a real project; they are grouped here
//  for distribution convenience. Students should split them when implementing.
//
// ═══════════════════════════════════════════════════════════════════════════


// ── @NameBinding annotation (Lab 4 Task 4.3) ─────────────────────────────

/**
 * Custom @NameBinding annotation.
 * Apply to a filter AND to a resource class or method to restrict
 * the filter to only those annotated endpoints.
 *
 * LAB 4 — Task 4.3: Move this to its own file Secured.java
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface Secured {}


// ── NotFoundExceptionMapper (Lab 4 Task 4.1) ─────────────────────────────

/**
 * Maps NotFoundException to a structured JSON 404 response.
 *
 * LAB 4 — Task 4.1
 * Without this mapper, Jersey returns a plain-text error page.
 * With it, every 404 becomes: { "status": 404, "error": "Not Found", "detail": "..." }
 */
@Provider
class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException ex) {
        // TODO: return 404 JSON body
        // Map.of("status", 404, "error", "Not Found", "detail", ex.getMessage())
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.1");
    }
}


// ── GlobalExceptionMapper (Lab 4 Task 4.1) ───────────────────────────────

/**
 * Catch-all mapper for any unhandled Exception.
 * Returns 500 Internal Server Error.
 *
 * JAX-RS always uses the most specific matching mapper, so this
 * only fires when no more-specific mapper matches.
 *
 * LAB 4 — Task 4.1
 */
@Provider
class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        // TODO: log ex.getMessage(); return 500 JSON body
        // Map.of("status", 500, "error", "Internal Server Error")
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.1");
    }
}


// ── CorsFilter (Lab 4 Task 4.2) ──────────────────────────────────────────

/**
 * Response filter that adds CORS headers to every response.
 * Applies globally — no @NameBinding, so it runs for all resources.
 *
 * LAB 4 — Task 4.2
 */
@Provider
@Priority(Priorities.HEADER_DECORATOR)
class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext  req,
                       ContainerResponseContext resp) {
        // TODO: add the four CORS headers:
        //   Access-Control-Allow-Origin:  *
        //   Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
        //   Access-Control-Allow-Headers: Authorization, Content-Type
        //   Access-Control-Max-Age:       86400
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.2");
    }
}


// ── BearerTokenFilter (Lab 4 Task 4.3) ───────────────────────────────────

/**
 * Authentication filter — requires a valid Bearer token.
 * Only applied to resource methods / classes annotated with @Secured.
 *
 * For this lab, any request with:
 *   Authorization: Bearer secret-token-123
 * is accepted. All others are rejected with 401.
 *
 * LAB 4 — Task 4.3
 */
@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
class BearerTokenFilter implements ContainerRequestFilter {

    private static final String VALID_TOKEN = "secret-token-123";

    @Override
    public void filter(ContainerRequestContext ctx) {
        // TODO:
        //   1. Read HttpHeaders.AUTHORIZATION header from ctx
        //   2. If null or not "Bearer secret-token-123":
        //        ctx.abortWith(Response.status(401)
        //            .entity(Map.of("error","Unauthorized","status",401))
        //            .type(MediaType.APPLICATION_JSON)
        //            .build());
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.3");
    }
}


// ── ProductCsvWriter (Lab 4 Task 4.4) ────────────────────────────────────

/**
 * Serialises List<Product> to text/csv.
 *
 * Triggered when a client sends:  Accept: text/csv
 * on any endpoint that @Produces includes "text/csv".
 *
 * LAB 4 — Task 4.4
 * You must also add @Produces("text/csv") to the listAll() method in
 * ProductResource for this writer to be invoked.
 */
@Provider
@Produces("text/csv")
class ProductCsvWriter implements MessageBodyWriter<List<Product>> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        if (!List.class.isAssignableFrom(type)) return false;
        if (genericType instanceof ParameterizedType) {
          ParameterizedType pt = (ParameterizedType) genericType;
          return Product.class.equals(pt.getActualTypeArguments()[0]);
        }
        return false;
    }

    @Override
    public void writeTo(List<Product> products,
                        Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException {
        // TODO:
        //   PrintWriter pw = new PrintWriter(new OutputStreamWriter(entityStream, UTF_8));
        //   pw.println("id,name,category,price,stockLevel,active");
        //   for each product: pw.printf("%s,%s,%s,%.2f,%d,%b%n", ...)
        //   pw.flush();
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.4");
    }

    @Override
    public long getSize(List<Product> products, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        return -1; // Unknown; Servlet container will use chunked transfer encoding
    }
}
