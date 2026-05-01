package com.example.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A single line item within an Order.
 *
 * LAB 2 — Task 2.3
 * Add JAXB annotations so this class marshals to:
 *
 *   <line sku="BK-001">
 *     <productName>Clean Code</productName>
 *     <quantity>2</quantity>
 *     <unitPrice>29.99</unitPrice>
 *     <lineTotal>59.98</lineTotal>
 *   </line>
 *
 * Key decisions:
 *   - The element wrapping each line is named "line" (not "orderLine")
 *     — this is controlled by @XmlElement(name="line") in the Order class.
 *   - "sku" is an XML ATTRIBUTE on the <line> element.
 *   - All BigDecimal fields use the provided CurrencyAdapter.
 *   - quantity is BigInteger (maps to xs:positiveInteger in the schema).
 *
 * Annotations needed on the class:
 *   @XmlAccessorType(XmlAccessType.FIELD)
 *   @XmlType(propOrder = { "productName", "quantity", "unitPrice", "lineTotal" })
 *
 * Annotations needed on individual fields:
 *   sku          → @XmlAttribute(name = "sku", required = true)
 *   productName  → @XmlElement(required = true)
 *   quantity     → @XmlElement(required = true)
 *   unitPrice    → @XmlElement(required = true) + @XmlJavaTypeAdapter(CurrencyAdapter.class)
 *   lineTotal    → @XmlElement(required = true) + @XmlJavaTypeAdapter(CurrencyAdapter.class)
 */
public class OrderLine {

    private String     sku;
    private String     productName;
    private BigInteger quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;

    // ── Constructors ──────────────────────────────────────────────────────

    public OrderLine() {}

    public OrderLine(String sku, String productName,
                     BigInteger quantity, BigDecimal unitPrice) {
        this.sku         = sku;
        this.productName = productName;
        this.quantity    = quantity;
        this.unitPrice   = unitPrice;
        this.lineTotal   = unitPrice.multiply(new BigDecimal(quantity));
    }

    // ── Getters and Setters ───────────────────────────────────────────────

    public String     getSku()                        { return sku; }
    public void       setSku(String sku)              { this.sku = sku; }

    public String     getProductName()                { return productName; }
    public void       setProductName(String n)        { this.productName = n; }

    public BigInteger getQuantity()                   { return quantity; }
    public void       setQuantity(BigInteger qty)     { this.quantity = qty; }

    public BigDecimal getUnitPrice()                  { return unitPrice; }
    public void       setUnitPrice(BigDecimal price)  { this.unitPrice = price; }

    public BigDecimal getLineTotal()                  { return lineTotal; }
    public void       setLineTotal(BigDecimal total)  { this.lineTotal = total; }
}
