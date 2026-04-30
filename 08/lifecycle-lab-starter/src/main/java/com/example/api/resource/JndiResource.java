package com.example.api.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import javax.naming.*;

/**
 * JNDI lookup demonstration.
 *
 * LAB 2 — Tasks 2.1 and 2.2
 */
@Path("/jndi")
@Produces(MediaType.TEXT_PLAIN)
public class JndiResource {

    // ── GET /api/jndi/version ─────────────────────────────────────────────
    // Looks up the app.version Environment entry from JNDI (declared in context.xml)
    @GET
    @Path("version")
    @Produces(MediaType.TEXT_PLAIN)
    public Response jndiVersion() {
        // TODO:
        //   Context ic = new InitialContext();
        //   String version = (String) ic.lookup("java:comp/env/app.version");
        //   return Response.ok(version).build();
        // Handle NameNotFoundException (404) and NamingException (500)
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.1");
    }

    // ── GET /api/jndi/greeting ────────────────────────────────────────────
    // Looks up the greeting string bound at startup by AppLifecycleListener
    @GET
    @Path("greeting")
    @Produces(MediaType.TEXT_PLAIN)
    public Response jndiGreeting() {
        // TODO:
        //   Context ic = new InitialContext();
        //   String greeting = (String) ic.lookup("java:comp/env/app/greeting");
        //   return Response.ok(greeting).build();
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.2 (implement AppLifecycleListener first)");
    }
}
