package com.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Root order domain class.
 *
 * LAB 3 — Tasks 3.1 and 3.3
 *
 * Add JAXB annotations to this class so it produces the following XML:
 *
 *   <order xmlns="http://example.com/shop/orders"
 *          orderId="ORD-2024-001" status="PROCESSING">
 *     <customerName>Jane Smith</customerName>
 *     <customerEmail>jane@example.com</customerEmail>
 *     <orderDate>2024-06-15</orderDate>
 *     <deliveryAddress>...</deliveryAddress>
 *     <lines>
 *       <line sku="BK-001">...</line>
 *     </lines>
 *     <totalAmount>99.97</totalAmount>
 *   </order>
 *
 * Annotations to add at class level:
 *   @XmlRootElement(name = "order", namespace = "http://example.com/shop/orders")
 *   @XmlType(propOrder = { "customerName","customerEmail","orderDate",
 *                          "deliveryAddress","lines","totalAmount","notes" })
 *   @XmlAccessorType(XmlAccessType.FIELD)
 *
 * Field-level annotations:
 *   orderId       → @XmlAttribute(name="orderId", required=true)
 *   status        → @XmlAttribute(name="status", required=true)
 *   orderDate     → @XmlElement(required=true) + @XmlJavaTypeAdapter(LocalDateAdapter.class)
 *   lines         → @XmlElementWrapper(name="lines") + @XmlElement(name="line")
 *   totalAmount   → @XmlElement(required=true) + @XmlJavaTypeAdapter(CurrencyAdapter.class)
 *   internalRef   → @XmlTransient
 */
public class Order {

    private String      orderId;
    private OrderStatus status;
    private String      customerName;
    private String      customerEmail;
    private LocalDate   orderDate;
    private Address     deliveryAddress;
    private List<OrderLine> lines = new ArrayList<>();
    private BigDecimal  totalAmount;
    private String      notes;
    private String      internalRef;   // must NOT appear in XML

    public Order() {}

    public String      getOrderId()                        { return orderId; }
    public void        setOrderId(String id)               { this.orderId = id; }
    public OrderStatus getStatus()                         { return status; }
    public void        setStatus(OrderStatus s)            { this.status = s; }
    public String      getCustomerName()                   { return customerName; }
    public void        setCustomerName(String n)           { this.customerName = n; }
    public String      getCustomerEmail()                  { return customerEmail; }
    public void        setCustomerEmail(String e)          { this.customerEmail = e; }
    public LocalDate   getOrderDate()                      { return orderDate; }
    public void        setOrderDate(LocalDate d)           { this.orderDate = d; }
    public Address     getDeliveryAddress()                { return deliveryAddress; }
    public void        setDeliveryAddress(Address a)       { this.deliveryAddress = a; }
    public List<OrderLine> getLines()                      { return lines; }
    public void        setLines(List<OrderLine> l)         { this.lines = l; }
    public BigDecimal  getTotalAmount()                    { return totalAmount; }
    public void        setTotalAmount(BigDecimal t)        { this.totalAmount = t; }
    public String      getNotes()                          { return notes; }
    public void        setNotes(String n)                  { this.notes = n; }
    public String      getInternalRef()                    { return internalRef; }
    public void        setInternalRef(String r)            { this.internalRef = r; }
}
