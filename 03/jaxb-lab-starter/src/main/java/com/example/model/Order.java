package com.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Root order domain class.
 *
 * LAB 2 — Tasks 2.2, 2.3, 2.5
 * Add JAXB annotations so this class marshals to the following XML:
 *
 * ┌─────────────────────────────────────────────────────────────────┐
 * │ <?xml version="1.0" encoding="UTF-8"?>                         │
 * │ <order xmlns="http://example.com/shop/orders"                  │
 * │        orderId="ORD-2024-001"                                  │
 * │        status="PROCESSING">                                     │
 * │   <customerName>Jane Smith</customerName>                      │
 * │   <customerEmail>jane@example.com</customerEmail>              │
 * │   <orderDate>2024-06-15</orderDate>                            │
 * │   <deliveryAddress>                                            │
 * │     <street>10 Downing Street</street>                         │
 * │     <city>London</city>                                        │
 * │     <postCode>SW1A 2AA</postCode>                              │
 * │     <country>GB</country>                                      │
 * │   </deliveryAddress>                                           │
 * │   <lines>                                                      │
 * │     <line sku="BK-001">                                        │
 * │       <productName>Clean Code</productName>                    │
 * │       <quantity>2</quantity>                                   │
 * │       <unitPrice>29.99</unitPrice>                             │
 * │       <lineTotal>59.98</lineTotal>                             │
 * │     </line>                                                    │
 * │   </lines>                                                     │
 * │   <totalAmount>59.98</totalAmount>                             │
 * │ </order>                                                       │
 * └─────────────────────────────────────────────────────────────────┘
 *
 * Key decisions (derive the correct annotations from these):
 *   - Root element name is "order", namespace "http://example.com/shop/orders"
 *   - orderId and status are XML ATTRIBUTES (not child elements)
 *   - orderDate is a LocalDate → requires LocalDateAdapter
 *   - lines are wrapped: <lines><line sku="...">...</line></lines>
 *   - totalAmount uses CurrencyAdapter (BigDecimal → 2dp string)
 *   - internalRef must NOT appear in the XML output
 *   - Use @XmlAccessorType(XmlAccessType.FIELD) — annotate fields directly
 *
 * Annotations to add at class level:
 *   @XmlRootElement(name = "order", namespace = "http://example.com/shop/orders")
 *   @XmlType(propOrder = { "customerName","customerEmail","orderDate",
 *                          "deliveryAddress","lines","totalAmount","notes" })
 *   @XmlAccessorType(XmlAccessType.FIELD)
 */
public class Order {

    // ── Fields ────────────────────────────────────────────────────────────
    // Add JAXB annotations above each field as directed in the lab tasks.

    /** XML ATTRIBUTE — orderId="ORD-2024-001" */
    private String orderId;

    /** XML ATTRIBUTE — status="PROCESSING" */
    private OrderStatus status;

    /** Required child element */
    private String customerName;

    /** Required child element */
    private String customerEmail;

    /**
     * Required child element.
     * Use @XmlJavaTypeAdapter(LocalDateAdapter.class)
     * to marshal LocalDate as "yyyy-MM-dd" string.
     */
    private LocalDate orderDate;

    /** Optional nested element — whole <deliveryAddress> block */
    private Address deliveryAddress;

    /**
     * The list of line items.
     * Wrap with @XmlElementWrapper(name = "lines")
     * and name each element with @XmlElement(name = "line")
     */
    private List<OrderLine> lines = new ArrayList<>();

    /**
     * Required child element.
     * Use @XmlJavaTypeAdapter(CurrencyAdapter.class)
     */
    private BigDecimal totalAmount;

    /** Optional — omit from XML when null */
    private String notes;

    /**
     * Internal reference — must NOT appear in XML.
     * Use @XmlTransient
     */
    private String internalRef;

    // ── Constructors ──────────────────────────────────────────────────────

    public Order() {}

    // ── Getters and Setters ───────────────────────────────────────────────

    public String      getOrderId()                         { return orderId; }
    public void        setOrderId(String orderId)           { this.orderId = orderId; }

    public OrderStatus getStatus()                          { return status; }
    public void        setStatus(OrderStatus status)        { this.status = status; }

    public String      getCustomerName()                    { return customerName; }
    public void        setCustomerName(String name)         { this.customerName = name; }

    public String      getCustomerEmail()                   { return customerEmail; }
    public void        setCustomerEmail(String email)       { this.customerEmail = email; }

    public LocalDate   getOrderDate()                       { return orderDate; }
    public void        setOrderDate(LocalDate orderDate)    { this.orderDate = orderDate; }

    public Address     getDeliveryAddress()                 { return deliveryAddress; }
    public void        setDeliveryAddress(Address addr)     { this.deliveryAddress = addr; }

    public List<OrderLine> getLines()                       { return lines; }
    public void            setLines(List<OrderLine> lines)  { this.lines = lines; }

    public BigDecimal  getTotalAmount()                     { return totalAmount; }
    public void        setTotalAmount(BigDecimal total)     { this.totalAmount = total; }

    public String      getNotes()                           { return notes; }
    public void        setNotes(String notes)               { this.notes = notes; }

    public String      getInternalRef()                     { return internalRef; }
    public void        setInternalRef(String ref)           { this.internalRef = ref; }
}
