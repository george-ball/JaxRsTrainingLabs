package com.example.api.model;

import javax.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Order domain model.  Complete – do not modify.
 */
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    private String    id;
    private String    customerId;
    private String    status;       // NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    private LocalDate orderDate;
    private List<OrderLine> lines  = new ArrayList<>();
    private BigDecimal totalAmount;

    public Order() {}

    public String    getId()                         { return id; }
    public void      setId(String id)                { this.id = id; }
    public String    getCustomerId()                 { return customerId; }
    public void      setCustomerId(String cid)       { this.customerId = cid; }
    public String    getStatus()                     { return status; }
    public void      setStatus(String status)        { this.status = status; }
    public LocalDate getOrderDate()                  { return orderDate; }
    public void      setOrderDate(LocalDate d)       { this.orderDate = d; }
    public List<OrderLine> getLines()               { return lines; }
    public void      setLines(List<OrderLine> lines) { this.lines = lines; }
    public BigDecimal getTotalAmount()               { return totalAmount; }
    public void      setTotalAmount(BigDecimal t)    { this.totalAmount = t; }
}
