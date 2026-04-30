package com.example.api.repository;

import com.example.api.model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Thread-safe in-memory product store.
 *
 * LAB NOTE: This is intentionally simple. It uses a Singleton so every
 * servlet request shares the same data. Do NOT replace with a database —
 * the labs are designed to run with zero external infrastructure.
 *
 * Reset data between lab runs by calling reset() or redeploying the WAR.
 */
public class ProductRepository {

    // ── Singleton ─────────────────────────────────────────────────────────
    private static final ProductRepository INSTANCE = new ProductRepository();

    public static ProductRepository getInstance() {
        return INSTANCE;
    }

    // ── State ─────────────────────────────────────────────────────────────
    private final Map<String, Product> store   = new ConcurrentHashMap<>();
    private final AtomicInteger        counter = new AtomicInteger(1);

    // ── Seed data ─────────────────────────────────────────────────────────
    private ProductRepository() {
        save(new Product(null, "Clean Code",          "books",       new BigDecimal("39.99"),  50,  true));
        save(new Product(null, "Effective Java",      "books",       new BigDecimal("49.99"),  30,  true));
        save(new Product(null, "Java 11 Pocket Guide","books",       new BigDecimal("24.99"),  75,  true));
        save(new Product(null, "Mechanical Keyboard", "electronics", new BigDecimal("129.99"), 15,  true));
        save(new Product(null, "USB-C Hub",           "electronics", new BigDecimal("34.99"),  200, true));
        save(new Product(null, "Old Java EE 5 Book",  "books",       new BigDecimal("9.99"),   5,   false));
    }

    // ── CRUD operations ───────────────────────────────────────────────────

    /**
     * Persist a product. Assigns a generated ID if none is set.
     * Returns the saved product (with generated ID populated).
     */
    public Product save(Product product) {
        if (product.getId() == null ||  product.getId().trim().isEmpty()){
            product.setId(String.format("P%03d", counter.getAndIncrement()));
        }
        store.put(product.getId(), product);
        return product;
    }

    /**
     * Find a product by ID. Returns null if not found.
     */
    public Product findById(String id) {
        return store.get(id);
    }

    /**
     * Find products with optional filtering and pagination.
     *
     * @param category   filter by category (case-insensitive); null = all categories
     * @param page       zero-based page number
     * @param size       page size (max results)
     * @param activeOnly if true, return only active products
     */
    public List<Product> find(String category, int page, int size, boolean activeOnly) {
        return store.values().stream()
            .filter(p -> !activeOnly || p.isActive())
            .filter(p -> category == null || category.isBlank()
                      || p.getCategory().equalsIgnoreCase(category))
            .sorted(Comparator.comparing(Product::getId))
            .skip((long) page * size)
            .limit(size)
            .collect(Collectors.toList());
    }

    /**
     * Update an existing product. Returns null if the ID does not exist.
     */
    public Product update(String id, Product updated) {
        if (!store.containsKey(id)) return null;
        updated.setId(id);
        store.put(id, updated);
        return updated;
    }

    /**
     * Delete a product by ID. Returns true if it existed, false otherwise.
     */
    public boolean delete(String id) {
        return store.remove(id) != null;
    }

    /**
     * Returns true if a product with the given ID exists.
     */
    public boolean exists(String id) {
        return store.containsKey(id);
    }

    /**
     * Returns all products (unsorted, unfiltered) — useful in tests.
     */
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * Resets the store to the original seed data.
     * Call this in test teardown to avoid state leaking between tests.
     */
    public void reset() {
        store.clear();
        counter.set(1);
        save(new Product(null, "Clean Code",          "books",       new BigDecimal("39.99"),  50,  true));
        save(new Product(null, "Effective Java",      "books",       new BigDecimal("49.99"),  30,  true));
        save(new Product(null, "Java 11 Pocket Guide","books",       new BigDecimal("24.99"),  75,  true));
        save(new Product(null, "Mechanical Keyboard", "electronics", new BigDecimal("129.99"), 15,  true));
        save(new Product(null, "USB-C Hub",           "electronics", new BigDecimal("34.99"),  200, true));
        save(new Product(null, "Old Java EE 5 Book",  "books",       new BigDecimal("9.99"),   5,   false));
    }
}
