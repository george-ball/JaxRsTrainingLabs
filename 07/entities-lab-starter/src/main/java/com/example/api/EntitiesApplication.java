package com.example.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS Application — base URI: /api
 * Auto-scanning discovers all @Path and @Provider classes.
 */
@ApplicationPath("/api")
public class EntitiesApplication extends Application { }
