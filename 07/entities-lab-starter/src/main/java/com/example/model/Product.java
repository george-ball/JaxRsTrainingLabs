package com.example.model;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Product domain model. Complete – do not modify.
 *
 * @XmlRootElement enables Jersey/JAXB to automatically marshal
 * this to/from XML for endpoints using @Produces(APPLICATION_XML).
 */
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "name", "category", "price", "stockLevel", "active" })
public class Product {
    @XmlAttribute
    private String id;
    private String name;
    private String category;
    private BigDecimal price;
    private int stockLevel;
    private boolean active;

    public Product() {}
    public Product(String id, String name, String cat,
                   BigDecimal price, int stock, boolean active) {
        this.id=id; this.name=name; this.category=cat;
        this.price=price; this.stockLevel=stock; this.active=active;
    }
    public String     getId()                   { return id; }
    public void       setId(String id)          { this.id = id; }
    public String     getName()                 { return name; }
    public void       setName(String n)         { this.name = n; }
    public String     getCategory()             { return category; }
    public void       setCategory(String c)     { this.category = c; }
    public BigDecimal getPrice()                { return price; }
    public void       setPrice(BigDecimal p)    { this.price = p; }
    public int        getStockLevel()           { return stockLevel; }
    public void       setStockLevel(int s)      { this.stockLevel = s; }
    public boolean    isActive()                { return active; }
    public void       setActive(boolean a)      { this.active = a; }
}
