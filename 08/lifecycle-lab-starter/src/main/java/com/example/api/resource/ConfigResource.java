package com.example.api.resource;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Web resource access demonstration.
 *
 * LAB 1 — Tasks 1.1, 1.2, 1.3
 *
 * This resource reads:
 *   - Context init-params from web.xml (Task 1.1)
 *   - A static HTML file from /WEB-INF/templates/ (Task 1.2)
 *   - A file from the Java classpath (Task 1.3)
 */
@Path("/config")
public class ConfigResource {

    // ── LAB 1 Task 1.1 — inject the ServletContext ────────────────────────
    // TODO: add @Context annotation and private ServletContext field
    // @Context
    // private ServletContext servletContext;

    // ── GET /api/config/version ───────────────────────────────────────────
    // Returns the "app.version" context-param value as plain text
    @GET
    @Path("version")
    @Produces(MediaType.TEXT_PLAIN)
    public String version() {
        // TODO: return servletContext.getInitParameter("app.version")
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.1");
    }

    // ── GET /api/config ───────────────────────────────────────────────────
    // Returns all three init-params as plain text key=value lines:
    //   appVersion=2.4.1
    //   paymentsEnabled=true
    //   maxUploadBytes=10485760
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String allParams() {
        // TODO: read all three init-params from servletContext
        //   String version  = servletContext.getInitParameter("app.version");
        //   String payments = servletContext.getInitParameter("feature.payments.enabled");
        //   String maxBytes = servletContext.getInitParameter("upload.max.bytes");
        //   return "appVersion=" + version + "\n" +
        //          "paymentsEnabled=" + payments + "\n" +
        //          "maxUploadBytes=" + maxBytes;
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.1");
    }

    // ── GET /api/config/template ──────────────────────────────────────────
    // Reads /WEB-INF/templates/welcome.html from the WAR root and returns it
    @GET
    @Path("template")
    @Produces(MediaType.TEXT_HTML)
    public Response template() throws IOException {
        // TODO:
        //   InputStream is = servletContext
        //       .getResourceAsStream("/WEB-INF/templates/welcome.html");
        //   if (is == null) return Response.status(404).build();
        //   String html = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        //   return Response.ok(html).build();
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.2");
    }

    // ── GET /api/config/classpath ─────────────────────────────────────────
    // Reads messages.properties from the Java classpath
    @GET
    @Path("classpath")
    @Produces(MediaType.TEXT_PLAIN)
    public Response classpathResource() throws IOException {
        // TODO:
        //   InputStream is = getClass().getResourceAsStream("/messages.properties");
        //   if (is == null) return Response.status(404).build();
        //   return Response.ok(new String(is.readAllBytes(), StandardCharsets.UTF_8)).build();
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.3");
    }
}
