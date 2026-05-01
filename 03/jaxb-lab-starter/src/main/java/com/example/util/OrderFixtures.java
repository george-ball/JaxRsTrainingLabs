package com.example.util;

import com.example.model.Address;
import com.example.model.Order;
import com.example.model.OrderLine;
import com.example.model.OrderStatus;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Factory for building test Order objects.
 *
 * PROVIDED — do not modify.
 *
 * Used by:
 *   - Lab 2 quick marshal test (Task 2.6)
 *   - Lab 3 JUnit tests (Task 3.3)
 *   - Lab 4 batch file generation (Task 4.5)
 *
 * All created orders pass schema validation against order-schema.xsd
 * provided you have added the correct JAXB annotations to the domain classes.
 */
public class OrderFixtures {

    /** Cannot be instantiated — static factory only. */
    private OrderFixtures() {}

    /**
     * Creates a standard two-line Order for use in tests.
     *
     * Produces:
     *   orderId        = "ORD-2024-001"
     *   status         = PROCESSING
     *   customerName   = "Jane Smith"
     *   customerEmail  = "jane@example.com"
     *   orderDate      = 2024-06-15
     *   deliveryAddress = { 10 Downing Street, London, SW1A 2AA, GB }
     *   lines[0]       = { sku=BK-001, Clean Code, qty=2, £29.99, total=£59.98 }
     *   lines[1]       = { sku=BK-002, Effective Java, qty=1, £39.99, total=£39.99 }
     *   totalAmount    = 99.97
     *   internalRef    = "INTERNAL-REF-001"  ← must NOT appear in XML
     *
     * @return a fully populated Order ready for marshalling
     */
    public static Order createSampleOrder() {
        Order order = new Order();
        order.setOrderId("ORD-2024-001");
        order.setStatus(OrderStatus.PROCESSING);
        order.setCustomerName("Jane Smith");
        order.setCustomerEmail("jane@example.com");
        order.setOrderDate(LocalDate.of(2024, 6, 15));
        order.setInternalRef("INTERNAL-REF-001");   // must be excluded from XML

        Address addr = new Address(
            "10 Downing Street", "London", "SW1A 2AA", "GB");
        order.setDeliveryAddress(addr);

        OrderLine line1 = new OrderLine(
            "BK-001", "Clean Code",
            BigInteger.valueOf(2), new BigDecimal("29.99"));
        OrderLine line2 = new OrderLine(
            "BK-002", "Effective Java",
            BigInteger.valueOf(1), new BigDecimal("39.99"));

        order.getLines().add(line1);
        order.getLines().add(line2);
        order.setTotalAmount(new BigDecimal("99.97"));

        return order;
    }

    /**
     * Creates a minimal one-line Order using only required fields.
     * Useful for testing validation — missing optional fields should not fail.
     *
     * @param orderId the orderId to assign (e.g. "ORD-2024-002")
     */
    public static Order createMinimalOrder(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(OrderStatus.NEW);
        order.setCustomerName("Test Customer");
        order.setCustomerEmail("test@example.com");
        order.setOrderDate(LocalDate.now());

        OrderLine line = new OrderLine(
            "SKU-001", "Test Product",
            BigInteger.ONE, new BigDecimal("9.99"));
        order.getLines().add(line);
        order.setTotalAmount(new BigDecimal("9.99"));

        return order;
    }

    /**
     * Creates an Order with a notes field populated.
     * Used to verify optional field marshalling.
     */
    public static Order createOrderWithNotes() {
        Order order = createMinimalOrder("ORD-2024-NOTES");
        order.setNotes("Please leave with neighbour if not home.");
        return order;
    }
}
