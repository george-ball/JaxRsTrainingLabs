package com.example.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS Application — base URI: /api
 *
 * Currently uses auto-scanning. As you add resource classes in each lab,
 * they will be discovered automatically.
 *
 * To switch to manual registration (if needed), uncomment and complete getClasses().
 */
@ApplicationPath("/api")
public class ParamsApplication extends Application {
    // Empty body — auto-scan discovers @Path and @Provider classes
}
