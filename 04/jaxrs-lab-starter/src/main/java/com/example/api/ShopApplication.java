package com.example.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS Application entry point.
 *
 * LAB 1 — Task 1.2
 * ─────────────────────────────────────────────────────────────────
 * This class currently has an empty body, which tells the Jersey
 * runtime to scan the classpath for all @Path and @Provider classes.
 *
 * Tasks:
 *   1. Verify that the @ApplicationPath value is correct for your deployment.
 *   2. Run the app and confirm the /health endpoint responds.
 *   3. In Task 1.4, add getClasses() to switch to manual registration.
 *   4. In Task 1.5, create a SECOND Application class at /api/v2.
 *
 * Full URI for a resource:
 *   http://localhost:8080/jaxrs-lab   ← Tomcat context root (WAR name)
 *                        /api         ← @ApplicationPath (this annotation)
 *                            /health  ← @Path on HealthResource
 */
@ApplicationPath("/api")
public class ShopApplication extends Application {

    // ── LAB 1 TASK 1.4 ──────────────────────────────────────────────────
    // Uncomment and complete this to switch to manual registration.
    // WARNING: returning ANY non-empty Set here disables classpath scanning.
    //
    // @Override
    // public java.util.Set<Class<?>> getClasses() {
    //     return java.util.Set.of(
    //         // TODO: list every resource and provider class you want registered
    //     );
    // }

    // ── LAB 1 TASK 1.5 (getSingletons) ──────────────────────────────────
    // Uncomment to register a shared, configured Jackson ObjectMapper.
    //
    // @Override
    // public java.util.Set<Object> getSingletons() {
    //     com.fasterxml.jackson.databind.ObjectMapper mapper =
    //         new com.fasterxml.jackson.databind.ObjectMapper()
    //             .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
    //             .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    //     return java.util.Set.of(
    //         new org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider(mapper)
    //     );
    // }
}
