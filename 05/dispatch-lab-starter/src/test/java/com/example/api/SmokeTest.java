package com.example.api;

import com.example.api.model.*;
import com.example.api.repository.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.*;

import javax.ws.rs.core.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Baseline smoke tests.
 *
 * ALL 10 tests must PASS before you start any lab.
 * Run with:  mvn test -Dtest=SmokeTest
 *
 * These tests verify:
 *   - HealthResource responds correctly  (Lab 1 baseline)
 *   - ProductRepository seed data is correct
 *   - StoreRepository seed data is correct
 *   - OrderRepository starts empty
 *
 * Do NOT modify these tests.
 */
class SmokeTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(
            com.example.api.resource.HealthResource.class

        );
    }

    @BeforeEach
    void resetRepos() {
        ProductRepository.getInstance().reset();
        StoreRepository.getInstance().reset();
        OrderRepository.getInstance().reset();
    }

    // ── HealthResource ────────────────────────────────────────────────────

    @Test
    void healthEndpoint_returns200() {
        Response r = target("/health").request().get();
        assertEquals(200, r.getStatus());
    }

    @Test
    void healthResponse_containsStatusUp() {
        String body = target("/health").request()
            .accept(MediaType.APPLICATION_XML)
            .get(String.class);
        assertTrue(body.contains("UP"), "Health body must contain 'UP'");
    }

    @Test
    void healthResponse_containsServiceName() {
        String body = target("/health").request()
            .accept(MediaType.APPLICATION_XML)
            .get(String.class);
        assertTrue(body.contains("DispatchLab"));
    }

    // ── ProductRepository ──────────────────────────────────────────────────

    @Test
    void productRepo_hasSixSeedProducts() {
        assertEquals(6, ProductRepository.getInstance().findAll().size());
    }

    @Test
    void productRepo_findById_P001_returnsProduct() {
        Product p = ProductRepository.getInstance().findById("P001");
        assertNotNull(p);
        assertEquals("Clean Code", p.getName());
    }

    @Test
    void productRepo_findBySku_returnsCorrectProduct() {
        Product p = ProductRepository.getInstance().findBySku("BOOK-001");
        assertNotNull(p, "BOOK-001 should exist");
        assertEquals("Clean Code", p.getName());
    }

    @Test
    void productRepo_findByNumericId_returnsProduct() {
        Product p = ProductRepository.getInstance().findByNumericId(1);
        assertNotNull(p, "Numeric id 1 should return a product");
    }

    @Test
    void productRepo_findFeatured_returnsThreeProducts() {
        long count = ProductRepository.getInstance().findAll().stream()
            .filter(Product::isFeatured).count();
        assertEquals(3, count, "Should have exactly 3 featured products");
    }

    // ── StoreRepository ───────────────────────────────────────────────────

    @Test
    void storeRepo_hasThreeSeedStores() {
        assertEquals(3, StoreRepository.getInstance().findAll().size());
    }

    @Test
    void storeRepo_store001_hasThreeInventoryItems() {
        com.example.api.model.Store s = StoreRepository.getInstance().findById("STORE-001");
        assertNotNull(s);
        assertEquals(3, s.getInventory().size());
    }
}
