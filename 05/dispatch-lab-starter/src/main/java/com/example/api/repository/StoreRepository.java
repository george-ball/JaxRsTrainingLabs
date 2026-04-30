package com.example.api.repository;

import com.example.api.model.InventoryItem;
import com.example.api.model.Store;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe in-memory store repository.
 * Complete – do not modify.
 *
 * Seed data: 3 stores, each with 3 inventory items.
 */
public class StoreRepository {

    private static final StoreRepository INSTANCE = new StoreRepository();
    public  static StoreRepository getInstance() { return INSTANCE; }

    private final Map<String, Store> stores = new ConcurrentHashMap<>();

    private StoreRepository() { seed(); }

    private void seed() {
        Store s1 = new Store("STORE-001", "London Flagship", "London");
        s1.getInventory().add(new InventoryItem("BOOK-001", "Clean Code",          42));
        s1.getInventory().add(new InventoryItem("BOOK-002", "Effective Java",       18));
        s1.getInventory().add(new InventoryItem("ELEC-001", "Mechanical Keyboard",   5));
        stores.put(s1.getId(), s1);

        Store s2 = new Store("STORE-002", "Manchester Branch", "Manchester");
        s2.getInventory().add(new InventoryItem("BOOK-001", "Clean Code",           12));
        s2.getInventory().add(new InventoryItem("ELEC-002", "USB-C Hub",            50));
        s2.getInventory().add(new InventoryItem("BOOK-003", "Java 11 Pocket Guide", 25));
        stores.put(s2.getId(), s2);

        Store s3 = new Store("STORE-003", "Edinburgh Outlet", "Edinburgh");
        s3.getInventory().add(new InventoryItem("BOOK-002", "Effective Java",        8));
        s3.getInventory().add(new InventoryItem("ELEC-001", "Mechanical Keyboard",   2));
        s3.getInventory().add(new InventoryItem("ELEC-002", "USB-C Hub",            30));
        stores.put(s3.getId(), s3);
    }

    public Store findById(String id)          { return stores.get(id); }
    public List<Store> findAll()              { return new ArrayList<>(stores.values()); }
    public boolean exists(String id)          { return stores.containsKey(id); }

    /** Update the quantity of an inventory item by SKU within a store. Returns null if store or SKU not found. */
    public InventoryItem updateInventory(String storeId, String sku, InventoryItem updated) {
        Store store = stores.get(storeId);
        if (store == null) return null;
        for (int i = 0; i < store.getInventory().size(); i++) {
            if (sku.equals(store.getInventory().get(i).getSku())) {
                updated.setSku(sku);
                store.getInventory().set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public void reset() { stores.clear(); seed(); }
}
