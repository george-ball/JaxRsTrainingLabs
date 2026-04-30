package com.example.api;

import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.*;

import javax.ws.rs.core.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Smoke tests — verify the project builds and the repository works.
 *
 * These tests use Jersey's in-memory test container (no Tomcat required).
 *
 * Run with:  mvn test
 *
 * ─────────────────────────────────────────────────────────────────────────
 * IMPORTANT: Do NOT modify these tests — they are your green baseline.
 * If they pass before you start coding, your environment is working.
 * If they fail after Lab 2, you may have broken the HealthResource.
 * ─────────────────────────────────────────────────────────────────────────
 */
class StarterProjectSmokeTest extends JerseyTest {

    @Override
    protected Application configure() {
        // Register only HealthResource for these baseline tests
        return new ResourceConfig(
            com.example.api.resource.HealthResource.class
        ).register(
            org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider.class
        );
    }

    @BeforeEach
    void resetRepo() {
        ProductRepository.getInstance().reset();
    }

    // ── Baseline: HealthResource ──────────────────────────────────────────

    @Test
    void healthEndpointReturns200() {
        Response response = target("/health").request().get();
        assertEquals(200, response.getStatus(), "Health endpoint should return 200 OK");
    }

    @Test
    void healthResponseContainsStatusUp() {
        String body = target("/health").request()
            .accept(MediaType.APPLICATION_JSON)
            .get(String.class);
        assertTrue(body.contains("UP"), "Health response should contain 'UP'");
    }

    // ── Repository: seed data ─────────────────────────────────────────────

    @Test
    void repositoryHasSixSeedProducts() {
        ProductRepository repo = ProductRepository.getInstance();
        assertEquals(6, repo.findAll().size(), "Repository should have 6 seed products");
    }

    @Test
    void repositoryFindByIdReturnsProduct() {
        ProductRepository repo = ProductRepository.getInstance();
        Product p = repo.findById("P001");
        assertNotNull(p, "P001 should exist in the seed data");
        assertEquals("Clean Code", p.getName());
    }

    @Test
    void repositoryFilterByCategory() {
        ProductRepository repo = ProductRepository.getInstance();
        var books = repo.find("books", 0, 20, true);
        assertTrue(books.size() >= 3, "Should find at least 3 active books");
        books.forEach(p -> assertEquals("books", p.getCategory(),
            "All results should be in the books category"));
    }

    @Test
    void repositorySaveAssignsId() {
        ProductRepository repo = ProductRepository.getInstance();
        Product p = new Product(null, "Test Product", "test",
            new BigDecimal("9.99"), 10, true);
        Product saved = repo.save(p);
        assertNotNull(saved.getId(), "Saved product should have a generated ID");
        assertFalse(saved.getId().isBlank(), "Generated ID should not be blank");
    }

    @Test
    void repositoryDeleteReturnsTrueForExisting() {
        ProductRepository repo = ProductRepository.getInstance();
        assertTrue(repo.delete("P001"), "Deleting P001 should return true");
        assertNull(repo.findById("P001"), "P001 should be gone after deletion");
    }

    @Test
    void repositoryDeleteReturnsFalseForMissing() {
        ProductRepository repo = ProductRepository.getInstance();
        assertFalse(repo.delete("DOES_NOT_EXIST"),
            "Deleting a non-existent product should return false");
    }

    @Test
    void repositoryResetRestoresSeedData() {
        ProductRepository repo = ProductRepository.getInstance();
        repo.delete("P001");
        repo.delete("P002");
        repo.reset();
        assertNotNull(repo.findById("P001"), "P001 should be restored after reset");
        assertEquals(6, repo.findAll().size(), "All 6 seed products should be restored");
    }
}
