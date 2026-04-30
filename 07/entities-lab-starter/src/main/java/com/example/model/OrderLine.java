package com.example.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Order line item.
 *
 * LAB 3 — add JAXB annotations so this maps to:
 *   <line sku="BK-001">
 *     <productName>Clean Code</productName>
 *     <quantity>2</quantity>
 *     <unitPrice>29.99</unitPrice>
 *   </line>
 *
 * Note: sku is an XML attribute, not a child element.
 * Required annotations:
 *   @XmlAccessorType(XmlAccessType.FIELD)
 *   @XmlType(propOrder = { "productName", "quantity", "unitPrice" })
 *   sku field:        @XmlAttribute(name = "sku", required = true)
 *   other fields:     @XmlElement(required = true)
 *   unitPrice:        @XmlElement + @XmlJavaTypeAdapter(CurrencyAdapter.class)
 */
public class OrderLine {
    private String     sku;
    private String     productName;
    private BigInteger quantity;
    private BigDecimal unitPrice;

    public OrderLine() {}
    public OrderLine(String sku, String productName, int quantity, BigDecimal unitPrice) {
        this.sku=sku; this.productName=productName;
        this.quantity=BigInteger.valueOf(quantity); this.unitPrice=unitPrice;
    }
    public String     getSku()               { return sku; }         public void setSku(String s)         { this.sku=s; }
    public String     getProductName()       { return productName; } public void setProductName(String n)  { this.productName=n; }
    public BigInteger getQuantity()          { return quantity; }    public void setQuantity(BigInteger q) { this.quantity=q; }
    public BigDecimal getUnitPrice()         { return unitPrice; }   public void setUnitPrice(BigDecimal p){ this.unitPrice=p; }
}
