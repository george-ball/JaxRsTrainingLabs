package com.example.api.repository;

import com.example.api.model.Review;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Thread-safe in-memory review store.
 * Used in Lab 3 (sub-resource locator).
 */
public class ReviewRepository {

    private static final ReviewRepository INSTANCE = new ReviewRepository();

    public static ReviewRepository getInstance() {
        return INSTANCE;
    }

    private final Map<String, Review> store   = new ConcurrentHashMap<>();
    private final AtomicInteger       counter = new AtomicInteger(1);

    private ReviewRepository() {
        // Seed with two reviews against the first product (P001)
        save(new Review(null, "P001", 5, "Excellent — transformed how I write code.",
                "Alice Jones", LocalDateTime.now().minusDays(10)));
        save(new Review(null, "P001", 4, "Very good but assumes some prior experience.",
                "Bob Smith", LocalDateTime.now().minusDays(3)));
        save(new Review(null, "P002", 5, "The definitive Java reference.",
                "Carol White", LocalDateTime.now().minusDays(1)));
    }

    /** Save a review. Assigns a generated ID and timestamps it. */
    public Review save(Review review) {
        if (review.getId() == null || review.getId().isBlank()) {
            review.setId(String.format("R%03d", counter.getAndIncrement()));
        }
        if (review.getCreatedAt() == null) {
            review.setCreatedAt(LocalDateTime.now());
        }
        store.put(review.getId(), review);
        return review;
    }

    /** Find a review by its own ID. Returns null if not found. */
    public Review findById(String id) {
        return store.get(id);
    }

    /** Return all reviews for a given productId, sorted newest first. */
    public List<Review> findByProduct(String productId) {
        return store.values().stream()
            .filter(r -> productId.equals(r.getProductId()))
            .sorted(Comparator.comparing(Review::getCreatedAt).reversed())
            .collect(Collectors.toList());
    }

    /** Delete a review. Returns true if it existed. */
    public boolean delete(String id) {
        return store.remove(id) != null;
    }
}
