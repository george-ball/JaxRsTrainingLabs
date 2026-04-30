package com.example.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS Application — base URI: /api
 *
 * LAB 4 Task 4.3: Register RolesAllowedDynamicFeature here when
 * implementing @RolesAllowed support.
 */
@ApplicationPath("/api")
public class LifecycleApplication extends Application {
    // Auto-scanning — all @Path and @Provider classes are discovered

    // LAB 4 Task 4.3 — uncomment when ready for @RolesAllowed:
    // @Override
    // public Set<Class<?>> getClasses() {
    //     return Set.of(
    //         resource.HealthResource.class,
    //         resource.ConfigResource.class,
    //         resource.JndiResource.class,
    //         resource.ProductResource.class,
    //         resource.SecuredOrderResource.class,
    //         resource.AdminResource.class,
    //         filter.BearerTokenFilter.class,
    //         org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature.class
    //     );
    // }
}
