package com.example.api.service;

import com.example.api.model.Product;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

/**
 * REST contract interface for the Product resource.
 *
 * LAB 5 — Task 5.1: Complete this interface by adding JAX-RS annotations.
 *
 * The interface should declare:
 *   - @Path("/products") at interface level
 *   - @Produces(APPLICATION_XML) at interface level
 *   - @Consumes(APPLICATION_XML) at interface level
 *   - @GET, @GET @Path("{id}"), @POST, @PUT @Path("{id}"), @DELETE @Path("{id}")
 *     on the respective methods
 *   - @PathParam, @QueryParam, @DefaultValue on method parameters
 *   - @Context UriInfo on the create() method
 *
 * The implementing class (ProductResourceV2) must NOT redeclare any of
 * these method-level annotations — they are inherited from this interface.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * The JAX-RS specification guarantees that annotations on interface methods
 * are inherited by implementing classes, as long as the implementing class
 * does NOT add any JAX-RS annotations to that method (all-or-nothing rule).
 * ─────────────────────────────────────────────────────────────────────────────
 */
// TODO Lab 5 Task 5.1 — add @Path("/products"), @Produces, @Consumes here
public interface ProductService {

    // TODO: add @GET, @QueryParam, @DefaultValue
    List<Product> listAll(
        /* TODO: @QueryParam("category") */       String category,
        /* TODO: @QueryParam("page") @DefaultValue("0") */ int page,
        /* TODO: @QueryParam("size") @DefaultValue("20") */ int size);

    // TODO: add @GET @Path("{id}")
    Response getById(/* TODO: @PathParam("id") */ String id);

    // TODO: add @POST
    Response create(Product product, /* TODO: @Context */ UriInfo uriInfo);

    // TODO: add @PUT @Path("{id}")
    Response update(/* TODO: @PathParam("id") */ String id, Product product);

    // TODO: add @DELETE @Path("{id}")
    Response delete(/* TODO: @PathParam("id") */ String id);
}
