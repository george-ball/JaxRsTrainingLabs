package com.example.repository;

import com.example.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/** In-memory order store with seed data. Complete – do not modify. */
public class OrderRepository {
    private static final OrderRepository INSTANCE = new OrderRepository();
    public static OrderRepository getInstance() { return INSTANCE; }

    private final Map<String, Order> store = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    private OrderRepository() { seed(); }

    private void seed() {
        Order o1 = new Order();
        o1.setOrderId("ORD-001");
        o1.setStatus(OrderStatus.PROCESSING);
        o1.setCustomerName("Jane Smith");
        o1.setCustomerEmail("jane@example.com");
        o1.setOrderDate(LocalDate.of(2024, 6, 15));
        o1.setTotalAmount(new BigDecimal("99.97"));
        o1.setInternalRef("INTERNAL-REF-001");
        Address addr = new Address("10 Downing Street", "London", "SW1A 2AA", "GB");
        o1.setDeliveryAddress(addr);
        OrderLine line1 = new OrderLine("BK-001", "Clean Code", 2, new BigDecimal("29.99"));
        OrderLine line2 = new OrderLine("BK-002", "Effective Java", 1, new BigDecimal("39.99"));
        o1.getLines().add(line1);
        o1.getLines().add(line2);
        store.put(o1.getOrderId(), o1);

        Order o2 = new Order();
        o2.setOrderId("ORD-002");
        o2.setStatus(OrderStatus.NEW);
        o2.setCustomerName("Bob Jones");
        o2.setCustomerEmail("bob@example.com");
        o2.setOrderDate(LocalDate.of(2024, 6, 20));
        o2.setTotalAmount(new BigDecimal("34.99"));
        o2.getLines().add(new OrderLine("ELEC-002", "USB-C Hub", 1, new BigDecimal("34.99")));
        store.put(o2.getOrderId(), o2);
    }

    public Order save(Order o) {
        if (o.getOrderId() == null || o.getOrderId().isBlank())
            o.setOrderId(String.format("ORD-%03d", counter.getAndIncrement()));
        store.put(o.getOrderId(), o); return o;
    }
    public Order findById(String id)  { return store.get(id); }
    public List<Order> findAll()      { return new ArrayList<>(store.values()); }
    public boolean delete(String id)  { return store.remove(id) != null; }
    public void reset()               { store.clear(); counter.set(1); seed(); }
}
