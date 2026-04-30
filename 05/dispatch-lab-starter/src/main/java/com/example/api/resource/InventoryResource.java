package com.example.api.resource;

import com.example.api.model.InventoryItem;
import com.example.api.model.Store;
import com.example.api.repository.StoreRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

/**
 * Inventory sub-resource.  Students implement this in Lab 4.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * KEY RULE: This class does NOT have a @Path annotation at class level.
 *
 * The base path is established by the sub-resource LOCATOR in StoreResource:
 *   @Path("{storeId}/inventory")   ← this is the locator's path
 *
 * The runtime then uses THIS class's method-level @Path annotations
 * to route the remaining URI segments.
 *
 * Methods handled by this class (after locator delegation):
 *   GET  /api/stores/{storeId}/inventory          → listAll()
 *   GET  /api/stores/{storeId}/inventory/{sku}    → getBySku()
 *   PUT  /api/stores/{storeId}/inventory/{sku}    → update()
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * LAB 4 — Task 4.3
 */
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class InventoryResource {

    private final Store store;
    private final StoreRepository storeRepo = StoreRepository.getInstance();

    /**
     * Constructor called by the locator in StoreResource.
     * The store has already been validated (non-null) by the locator.
     */
    public InventoryResource(Store store) {
        this.store = store;
    }

    // GET /api/stores/{storeId}/inventory
    @GET
    public List<InventoryItem> listAll() {
        // TODO: return store.getInventory()
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.3");
    }

    // GET /api/stores/{storeId}/inventory/{sku}
    @GET
    @Path("{sku}")
    public Response getBySku(@PathParam("sku") String sku) {
        // TODO: find item in store.getInventory() matching sku
        //       return 200 with item, or 404 if not found
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.3");
    }

    // PUT /api/stores/{storeId}/inventory/{sku}
    @PUT
    @Path("{sku}")
    public Response update(@PathParam("sku") String sku, InventoryItem item) {
        // TODO: storeRepo.updateInventory(store.getId(), sku, item)
        //       return 200 with updated item, or 404 if store/sku not found
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.3");
    }
}
