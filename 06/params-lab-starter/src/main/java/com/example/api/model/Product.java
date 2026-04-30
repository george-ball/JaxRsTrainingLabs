package com.example.api.model;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Product domain model. Complete – do not modify.
 *
 * @XmlRootElement allows Jersey/JAXB to marshal this to XML automatically
 * when resource methods use @Produces(MediaType.APPLICATION_XML).
 */
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "name", "category", "sku", "numericId",
    "price", "stockLevel", "active", "featured"
})
public class Product {

    @XmlAttribute
    private String id;
    private String name;
    private String category;
    private String sku;
    private int numericId;
    private BigDecimal price;
    private int stockLevel;
    private boolean active;
    private boolean featured;

    public Product() {}

    public Product(String id, int num, String sku, String name, String cat,
                   BigDecimal price, int stock, boolean active, boolean featured) {
        this.id=id; this.numericId=num; this.sku=sku; this.name=name;
        this.category=cat; this.price=price; this.stockLevel=stock;
        this.active=active; this.featured=featured;
    }

    public String     getId()                   { return id; }
    public void       setId(String id)          { this.id = id; }
    public int        getNumericId()            { return numericId; }
    public void       setNumericId(int n)       { this.numericId = n; }
    public String     getSku()                  { return sku; }
    public void       setSku(String s)          { this.sku = s; }
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
    public boolean    isFeatured()              { return featured; }
    public void       setFeatured(boolean f)    { this.featured = f; }

    @Override public String toString() {
        return String.format("Product{id='%s',name='%s'}", id, name);
    }
}
