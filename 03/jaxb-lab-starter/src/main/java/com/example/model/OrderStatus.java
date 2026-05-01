package com.example.model;

/**
 * Order status enumeration.
 *
 * LAB 2 — Task 2.4
 * Add the two JAXB annotations needed to map this enum to the XSD
 * OrderStatusType enumeration in order-schema.xsd.
 *
 * The enum constant names must match the XSD enumeration values exactly:
 *   NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED
 *
 * Annotations to add:
 *   @XmlType(name = "OrderStatusType")
 *   @XmlEnum
 *
 * After adding annotations, the marshaller will serialise:
 *   OrderStatus.NEW  →  <status>NEW</status>  (or attribute value NEW)
 */
public enum OrderStatus {
    NEW,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
