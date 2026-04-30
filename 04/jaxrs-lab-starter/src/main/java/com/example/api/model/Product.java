package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * Product domain model.
 *
 * LAB NOTE: Do not add JAX-RS annotations to this class.
 * Your resource class (ProductResource) handles HTTP concerns.
 * Jackson handles JSON serialisation automatically.
 */
public class Product {

    private String     id;
    private String     name;
    private String     category;
    private BigDecimal price;
    private int        stockLevel;
    private boolean    active;

    // ── Constructors ──────────────────────────────────────────────────────

    public Product() {
        // Required by Jackson for deserialisation
    }

    public Product(String id, String name, String category,
                   BigDecimal price, int stockLevel, boolean active) {
        this.id         = id;
        this.name       = name;
        this.category   = category;
        this.price      = price;
        this.stockLevel = stockLevel;
        this.active     = active;
    }

    // ── Getters and Setters ───────────────────────────────────────────────

    public String getId()                   { return id; }
    public void   setId(String id)          { this.id = id; }

    public String getName()                 { return name; }
    public void   setName(String name)      { this.name = name; }

    public String getCategory()             { return category; }
    public void   setCategory(String cat)   { this.category = cat; }

    public BigDecimal getPrice()            { return price; }
    public void       setPrice(BigDecimal p){ this.price = p; }

    public int  getStockLevel()             { return stockLevel; }
    public void setStockLevel(int stock)    { this.stockLevel = stock; }

    @JsonProperty("active")
    public boolean isActive()               { return active; }
    public void    setActive(boolean active){ this.active = active; }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', category='%s', price=%s, stock=%d, active=%b}",
            id, name, category, price, stockLevel, active);
    }
}
