package com.example.api.model;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Product domain model annotated for JAXB XML serialisation.
 *
 * All JAXB imports use javax.xml.bind.annotation.*
 * (NOT jakarta.xml.bind.annotation.*) - correct for Java 11 / Tomcat 9.
 *
 * Marshals to:
 *   <product id="P001" active="true">
 *     <name>Clean Code</name>
 *     <category>books</category>
 *     <price>39.99</price>
 *     <stockLevel>50</stockLevel>
 *   </product>
 */
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "category", "price", "stockLevel"})
public class Product {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private boolean active;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String category;

    @XmlElement(required = true)
    private BigDecimal price;

    @XmlElement(required = true)
    private int stockLevel;

    /** No-arg constructor required by JAXB. */
    public Product() {}

    public Product(String id, String name, String category,
                   BigDecimal price, int stockLevel, boolean active) {
        this.id = id; this.name = name; this.category = category;
        this.price = price; this.stockLevel = stockLevel; this.active = active;
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
    public void       setActive(boolean active) { this.active = active; }
}
