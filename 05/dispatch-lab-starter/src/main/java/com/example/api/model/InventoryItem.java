package com.example.api.model;

import javax.xml.bind.annotation.*;

/** Inventory item within a store.  Complete – do not modify. */
@XmlRootElement(name = "inventoryItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryItem {
    private String sku;
    private String productName;
    private int    quantity;

    public InventoryItem() {}
    public InventoryItem(String sku, String productName, int quantity) {
        this.sku = sku; this.productName = productName; this.quantity = quantity;
    }
    public String getSku()                   { return sku; }
    public void   setSku(String sku)         { this.sku = sku; }
    public String getProductName()           { return productName; }
    public void   setProductName(String n)   { this.productName = n; }
    public int    getQuantity()              { return quantity; }
    public void   setQuantity(int quantity)  { this.quantity = quantity; }
}
