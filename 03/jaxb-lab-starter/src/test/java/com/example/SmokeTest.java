package com.example;

import com.example.adapter.CurrencyAdapter;
import com.example.model.*;
import com.example.model.Order;
import com.example.util.OrderFixtures;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Baseline smoke tests — these must all pass before you start the labs.
 *
 * Run with:  mvn test
 *
 * If any of these tests fail before you write any code, your environment
 * is not set up correctly. Check the README for troubleshooting steps.
 *
 * ─────────────────────────────────────────────────────────────────────────
 * Do NOT modify these tests. They are your green baseline.
 * After completing Lab 2, all tests in OrderAnnotationTest should also pass.
 * ─────────────────────────────────────────────────────────────────────────
 */
class SmokeTest {

    // ── CurrencyAdapter (provided — should pass immediately) ──────────────

    @Test
    void currencyAdapter_marshal_formatsToTwoDecimalPlaces() throws Exception {
        CurrencyAdapter adapter = new CurrencyAdapter();
        assertEquals("29.99", adapter.marshal(new BigDecimal("29.99")));
        assertEquals("9.90",  adapter.marshal(new BigDecimal("9.9")));
        assertEquals("100.00",adapter.marshal(new BigDecimal("100")));
    }

    @Test
    void currencyAdapter_marshal_null_returnsNull() throws Exception {
        assertNull(new CurrencyAdapter().marshal(null));
    }

    @Test
    void currencyAdapter_unmarshal_parsesString() throws Exception {
        CurrencyAdapter adapter = new CurrencyAdapter();
        assertEquals(new BigDecimal("29.99"), adapter.unmarshal("29.99"));
        assertEquals(new BigDecimal("9.90"),  adapter.unmarshal("9.9"));
    }

    @Test
    void currencyAdapter_unmarshal_null_returnsNull() throws Exception {
        assertNull(new CurrencyAdapter().unmarshal(null));
    }

    @Test
    void currencyAdapter_unmarshal_blank_returnsNull() throws Exception {
        assertNull(new CurrencyAdapter().unmarshal("  "));
    }

    // ── Domain model construction ─────────────────────────────────────────

    @Test
    void order_canBeConstructedAndPopulated() {
        Order order = new Order();
        order.setOrderId("ORD-TEST-001");
        order.setStatus(OrderStatus.NEW);
        order.setCustomerName("Test User");
        order.setOrderDate(LocalDate.of(2024, 1, 1));
        order.setTotalAmount(new BigDecimal("49.99"));

        assertEquals("ORD-TEST-001",     order.getOrderId());
        assertEquals(OrderStatus.NEW,    order.getStatus());
        assertEquals("Test User",        order.getCustomerName());
        assertEquals(LocalDate.of(2024, 1, 1), order.getOrderDate());
        assertEquals(new BigDecimal("49.99"),   order.getTotalAmount());
    }

    @Test
    void orderLine_computesLineTotalFromConstructor() {
        OrderLine line = new OrderLine(
            "SKU-001", "Widget",
            BigInteger.valueOf(3), new BigDecimal("10.00"));

        assertEquals("SKU-001",  line.getSku());
        assertEquals("Widget",   line.getProductName());
        assertEquals(BigInteger.valueOf(3),       line.getQuantity());
        assertEquals(new BigDecimal("10.00"),     line.getUnitPrice());
        assertEquals(new BigDecimal("30.00"),     line.getLineTotal());
    }

    @Test
    void order_linesListIsInitialisedEmpty() {
        Order order = new Order();
        assertNotNull(order.getLines(), "Lines list should be initialised (not null)");
        assertTrue(order.getLines().isEmpty(), "Lines list should start empty");
    }

    @Test
    void order_linesCanBeAdded() {
        Order order = new Order();
        order.getLines().add(new OrderLine("S1","P1", BigInteger.ONE, BigDecimal.TEN));
        order.getLines().add(new OrderLine("S2","P2", BigInteger.TWO, BigDecimal.TEN));
        assertEquals(2, order.getLines().size());
    }

    // ── OrderFixtures ─────────────────────────────────────────────────────

    @Test
    void fixtures_sampleOrderHasTwoLines() {
        Order order = OrderFixtures.createSampleOrder();
        assertEquals("ORD-2024-001",      order.getOrderId());
        assertEquals(OrderStatus.PROCESSING, order.getStatus());
        assertEquals(2,                   order.getLines().size());
        assertEquals("INTERNAL-REF-001", order.getInternalRef());
    }

    @Test
    void fixtures_minimalOrderIsValid() {
        Order order = OrderFixtures.createMinimalOrder("ORD-MIN-001");
        assertNotNull(order.getOrderId());
        assertNotNull(order.getStatus());
        assertFalse(order.getLines().isEmpty());
        assertNotNull(order.getTotalAmount());
    }

    @Test
    void orderStatus_allValuesExist() {
        // Verify the enum was not accidentally modified
        assertEquals(5, OrderStatus.values().length);
        assertNotNull(OrderStatus.valueOf("NEW"));
        assertNotNull(OrderStatus.valueOf("PROCESSING"));
        assertNotNull(OrderStatus.valueOf("SHIPPED"));
        assertNotNull(OrderStatus.valueOf("DELIVERED"));
        assertNotNull(OrderStatus.valueOf("CANCELLED"));
    }
}
