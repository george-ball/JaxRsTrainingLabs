package com.example;

import com.example.adapter.CurrencyAdapter;
import com.example.adapter.LocalDateAdapter;
import com.example.model.*;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.*;

import javax.ws.rs.core.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Baseline smoke tests — all 8 must PASS before starting any lab.
 * Run with:  mvn test -Dtest=SmokeTest
 * Do NOT modify these tests.
 */
class SmokeTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(com.example.api.HealthResource.class)
            .register(org.glassfish.jersey.media.jaxb.JaxbFeature.class);
    }

    @BeforeEach
    void reset() {
        ProductRepository.getInstance().reset();
        OrderRepository.getInstance().reset();
    }

    // ── HealthResource ────────────────────────────────────────────────────
    @Test void health_returns200() {
        assertEquals(200, target("/health").request().get().getStatus());
    }

    @Test void health_containsServiceName() {
        assertTrue(target("/health").request().accept(MediaType.APPLICATION_XML)
            .get(String.class).contains("EntitiesLab"));
    }

    // ── ProductRepository ─────────────────────────────────────────────────
    @Test void productRepo_hasSixProducts() {
        assertEquals(6, ProductRepository.getInstance().findAll().size());
    }

    @Test void productRepo_P001_isCleanCode() {
        Product p = ProductRepository.getInstance().findById("P001");
        assertNotNull(p); assertEquals("Clean Code", p.getName());
    }

    // ── OrderRepository ───────────────────────────────────────────────────
    @Test void orderRepo_hasTwoSeedOrders() {
        assertEquals(2, OrderRepository.getInstance().findAll().size());
    }

    @Test void orderRepo_ORD001_hasCorrectCustomer() {
        Order o = OrderRepository.getInstance().findById("ORD-001");
        assertNotNull(o); assertEquals("Jane Smith", o.getCustomerName());
    }

    // ── Adapters (provided — must work immediately) ───────────────────────
    @Test void localDateAdapter_roundTrip() throws Exception {
        LocalDateAdapter a = new LocalDateAdapter();
        LocalDate d = LocalDate.of(2024, 6, 15);
        assertEquals("2024-06-15", a.marshal(d));
        assertEquals(d, a.unmarshal("2024-06-15"));
        assertNull(a.marshal(null));
        assertNull(a.unmarshal(null));
    }

    @Test void currencyAdapter_roundTrip() throws Exception {
        CurrencyAdapter a = new CurrencyAdapter();
        assertEquals("39.99", a.marshal(new BigDecimal("39.99")));
        assertEquals(new BigDecimal("39.99"), a.unmarshal("39.99"));
        assertNull(a.marshal(null));
        assertNull(a.unmarshal(null));
    }
}
