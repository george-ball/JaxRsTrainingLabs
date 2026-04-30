package com.example.api.resource;

import com.example.api.model.Store;
import com.example.api.repository.StoreRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

/**
 * Store REST resource skeleton.  Students implement this in Lab 4.
 *
 * Base URI: /api/stores
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * LAB 4 — Sub-resource Locator Pattern
 *
 * This class contains:
 *   - Two normal resource methods (GET /stores  and  GET /stores/{storeId})
 *   - ONE sub-resource locator:  @Path("{storeId}/inventory")
 *     The locator has @Path but NO HTTP verb annotation.
 *     It returns an InventoryResource instance.
 *     The JAX-RS runtime introspects the returned object and
 *     dispatches the remainder of the request to it.
 *
 * URI structure after implementation:
 *   GET /api/stores                                → listAll()
 *   GET /api/stores/{storeId}                      → getById()
 *   GET /api/stores/{storeId}/inventory            → locator → InventoryResource.listAll()
 *   GET /api/stores/{storeId}/inventory/{sku}      → locator → InventoryResource.getBySku()
 *   PUT /api/stores/{storeId}/inventory/{sku}      → locator → InventoryResource.update()
 * ─────────────────────────────────────────────────────────────────────────────
 */
@Path("/stores")
@Produces(MediaType.APPLICATION_XML)
public class StoreResource {

    private final StoreRepository storeRepo = StoreRepository.getInstance();

    // GET /api/stores  — list all stores (provided complete)
    @GET
    public List<Store> listAll() {
        return storeRepo.findAll();
    }

    // GET /api/stores/{storeId}  — get one store (provided complete)
    @GET
    @Path("{storeId}")
    public Response getById(@PathParam("storeId") String id) {
        Store s = storeRepo.findById(id);
        if (s == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(s).build();
    }

    // ── LAB 4 Task 4.2 — Sub-resource LOCATOR ────────────────────────────
    //
    // @Path("{storeId}/inventory")          ← @Path but NO HTTP verb
    // public InventoryResource getInventory(
    //         @PathParam("storeId") String storeId) {
    //
    //     // 1. Look up the store — throw NotFoundException if not found
    //     //    (this is the 404 guard: callers of /inventory get 404 if store is missing)
    //     // 2. Return new InventoryResource(store)
    //     //    The runtime will then dispatch GET/PUT/etc to InventoryResource
    // }
    //
    // TODO: implement the locator above
}
