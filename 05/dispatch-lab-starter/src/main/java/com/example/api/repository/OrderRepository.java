package com.example.api.repository;

import com.example.api.model.Order;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread-safe in-memory order store.
 * Complete – do not modify.  Starts empty; orders are created in Lab 3.
 */
public class OrderRepository {

    private static final OrderRepository INSTANCE = new OrderRepository();
    public  static OrderRepository getInstance() { return INSTANCE; }

    private final Map<String, Order> store   = new ConcurrentHashMap<>();
    private final AtomicInteger      counter = new AtomicInteger(1);

    private OrderRepository() {}

    public Order save(Order order) {
        if (order.getId() == null || order.getId().isBlank())
            order.setId(String.format("ORD-%03d", counter.getAndIncrement()));
        store.put(order.getId(), order);
        return order;
    }

    public Order findById(String id) { return store.get(id); }
    public List<Order> findAll()     { return new ArrayList<>(store.values()); }
    public boolean delete(String id) { return store.remove(id) != null; }
    public boolean exists(String id) { return store.containsKey(id); }

    public Order update(String id, Order o) {
        if (!store.containsKey(id)) return null;
        o.setId(id); store.put(id, o); return o;
    }

    public void reset() { store.clear(); counter.set(1); }
}
