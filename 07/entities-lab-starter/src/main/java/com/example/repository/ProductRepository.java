package com.example.repository;

import com.example.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/** In-memory product store. Complete – do not modify. */
public class ProductRepository {
    private static final ProductRepository INSTANCE = new ProductRepository();
    public static ProductRepository getInstance() { return INSTANCE; }

    private final Map<String, Product> store = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    private ProductRepository() {
        save(new Product(null,"Clean Code","books",new BigDecimal("39.99"),50,true));
        save(new Product(null,"Effective Java","books",new BigDecimal("49.99"),30,true));
        save(new Product(null,"Java 11 Pocket Guide","books",new BigDecimal("24.99"),75,true));
        save(new Product(null,"Mechanical Keyboard","electronics",new BigDecimal("129.99"),15,true));
        save(new Product(null,"USB-C Hub","electronics",new BigDecimal("34.99"),200,true));
        save(new Product(null,"Old Java EE 5 Book","books",new BigDecimal("9.99"),5,false));
    }

    public Product save(Product p) {
        if (p.getId() == null || p.getId().isBlank()) p.setId(String.format("P%03d",counter.getAndIncrement()));
        store.put(p.getId(), p); return p;
    }
    public Product findById(String id) { return store.get(id); }
    public List<Product> findAll() { return new ArrayList<>(store.values()); }
    public boolean delete(String id) { return store.remove(id) != null; }
    public void reset() { store.clear(); counter.set(1);
        save(new Product(null,"Clean Code","books",new BigDecimal("39.99"),50,true));
        save(new Product(null,"Effective Java","books",new BigDecimal("49.99"),30,true));
        save(new Product(null,"Java 11 Pocket Guide","books",new BigDecimal("24.99"),75,true));
        save(new Product(null,"Mechanical Keyboard","electronics",new BigDecimal("129.99"),15,true));
        save(new Product(null,"USB-C Hub","electronics",new BigDecimal("34.99"),200,true));
        save(new Product(null,"Old Java EE 5 Book","books",new BigDecimal("9.99"),5,false));
    }
}
