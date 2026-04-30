package com.example.api.repository;

import com.example.api.model.Product;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/** In-memory product store. Complete – do not modify. */
public class ProductRepository {
    private static final ProductRepository INSTANCE = new ProductRepository();
    public static ProductRepository getInstance() { return INSTANCE; }

    private final Map<String, Product> byId = new ConcurrentHashMap<>();
    private final AtomicInteger idSeq = new AtomicInteger(1);
    private final AtomicInteger numSeq = new AtomicInteger(1);

    private ProductRepository() { seed(); }

    private void seed() {
        add("Clean Code","books","BOOK-001",new BigDecimal("39.99"),50,true,true);
        add("Effective Java","books","BOOK-002",new BigDecimal("49.99"),30,true,true);
        add("Java 11 Pocket Guide","books","BOOK-003",new BigDecimal("24.99"),75,true,false);
        add("Mechanical Keyboard","electronics","ELEC-001",new BigDecimal("129.99"),15,true,true);
        add("USB-C Hub","electronics","ELEC-002",new BigDecimal("34.99"),200,true,false);
        add("Old Java EE 5 Book","books","BOOK-004",new BigDecimal("9.99"),5,false,false);
    }
    private void add(String name,String cat,String sku,BigDecimal price,int stock,boolean active,boolean featured){
        int num=numSeq.getAndIncrement();
        String id=String.format("P%03d",idSeq.getAndIncrement());
        byId.put(id,new Product(id,num,sku,name,cat,price,stock,active,featured));
    }

    public Product findById(String id) { return byId.get(id); }
    public Product findByNumericId(int n) { return byId.values().stream().filter(p->p.getNumericId()==n).findFirst().orElse(null); }
    public Product findBySku(String sku) { return byId.values().stream().filter(p->sku.equals(p.getSku())).findFirst().orElse(null); }
    public List<Product> findAll() { return new ArrayList<>(byId.values()); }
    public List<Product> search(String q, String sort) {
        String lq = q==null?"":q.toLowerCase();
        List<Product> r = byId.values().stream().filter(p->p.isActive())
            .filter(p->p.getName().toLowerCase().contains(lq)||p.getCategory().toLowerCase().contains(lq))
            .collect(Collectors.toList());
        if("price".equals(sort)) r.sort(Comparator.comparing(Product::getPrice));
        else r.sort(Comparator.comparing(Product::getName));
        return r;
    }
    public List<Product> find(String category,int page,int size,boolean activeOnly){
        return byId.values().stream()
            .filter(p->!activeOnly||p.isActive())
            .filter(p->category==null||category.isBlank()||p.getCategory().equalsIgnoreCase(category))
            .sorted(Comparator.comparing(Product::getId))
            .skip((long)page*size).limit(size).collect(Collectors.toList());
    }
    public Product save(Product p) {
        if(p.getId()==null||p.getId().isBlank()) p.setId(String.format("P%03d",idSeq.getAndIncrement()));
        byId.put(p.getId(),p); return p;
    }
    public boolean delete(String id) { return byId.remove(id)!=null; }
    public void reset() { byId.clear(); idSeq.set(1); numSeq.set(1); seed(); }
}
