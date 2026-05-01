package com.example.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS application entry point.
 *
 * @ApplicationPath("/api") roots all resources under /api/*.
 * Empty body = Jersey scans classpath for @Path and @Provider classes.
 *
 * Full base URL: http://localhost:8080/jaxrs-xml-lab/api/
 *
 * Namespace reminder:
 *   JAX-RS:  javax.ws.rs.*          (NOT jakarta.ws.rs.*)
 *   JAXB:    javax.xml.bind.*       (NOT jakarta.xml.bind.*)
 *   Servlet: javax.servlet.*        (NOT jakarta.servlet.*)
 *   Both correct for Java 11 / Jersey 2.x / Tomcat 9.
 */
@ApplicationPath("/api")
public class ShopApplication extends Application {

    // LAB 1 Task 1.4 - uncomment to switch to manual registration:
    // @Override
    // public java.util.Set<Class<?>> getClasses() {
    //     return java.util.Set.of(
    //         com.example.api.resource.HealthResource.class,
    //         com.example.api.resource.ProductResource.class,
    //         com.example.api.resource.ReviewResource.class,
    //         com.example.api.provider.Providers.class
    //     );
    // }
}
