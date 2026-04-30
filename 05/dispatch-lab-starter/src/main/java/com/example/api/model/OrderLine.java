package com.example.api.model;

import javax.xml.bind.annotation.*;

import java.math.BigDecimal;

/** Order line item.  Complete – do not modify. */
@XmlRootElement(name = "orderLine")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderLine {
    private String     sku;
    private String     productName;
    private int        quantity;
    private BigDecimal unitPrice;

    public OrderLine() {}
    public OrderLine(String sku, String productName, int quantity, BigDecimal unitPrice) {
        this.sku = sku; this.productName = productName;
        this.quantity = quantity; this.unitPrice = unitPrice;
    }
    public String     getSku()              { return sku; }
    public void       setSku(String sku)    { this.sku = sku; }
    public String     getProductName()      { return productName; }
    public void       setProductName(String n) { this.productName = n; }
    public int        getQuantity()         { return quantity; }
    public void       setQuantity(int q)    { this.quantity = q; }
    public BigDecimal getUnitPrice()        { return unitPrice; }
    public void       setUnitPrice(BigDecimal p) { this.unitPrice = p; }
}
