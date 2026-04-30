package com.example.api.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OrderStatusType")
@XmlEnum
/** Order status enum — used in Lab 1 Task 1.2. Complete – do not modify. */
public enum OrderStatus { NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED }
