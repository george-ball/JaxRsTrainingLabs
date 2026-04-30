package com.example.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS Application entry point — base URI: /api
 *
 * LAB 1 — Tasks 1.2 and 1.3
 * ─────────────────────────────────────────────────────────────────────────────
 * Currently uses auto-scanning (empty body).
 *
 * Task 1.2: Add getClasses() to switch to manual registration.
 *           Register only HealthResource initially, then add more per lab.
 *
 * Task 1.3: Create ApiV2Application.java alongside this file at /api/v2.
 *
 * Full URI anatomy:
 *   http://localhost:8080  /dispatch-lab  /api  /health
 *                           context root   app    resource
 *                           (WAR name)     path   @Path
 */
@ApplicationPath("/api")
public class ShopApplication extends Application {

    // ── LAB 1 Task 1.2 — uncomment and complete ───────────────────────────
    //
    // @Override
    // public java.util.Set<Class<?>> getClasses() {
    //     return java.util.Set.of(
    //         com.example.api.resource.HealthResource.class
    //         // Add more resource and provider classes here as you progress:
    //         //   Lab 2: ProductResource.class
    //         //   Lab 3: OrderResource.class
    //         //   Lab 4: StoreResource.class
    //     );
    // }
}
