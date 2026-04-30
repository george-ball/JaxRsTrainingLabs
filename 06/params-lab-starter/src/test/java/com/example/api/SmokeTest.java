package com.example.api;

import com.example.api.model.OrderStatus;
import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import com.example.api.storage.FileStorage;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.*;

import javax.ws.rs.core.*;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Baseline smoke tests.
 *
 * ALL 10 tests must PASS before you start any lab.
 * Run with:  mvn test -Dtest=SmokeTest
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
    void reset() {
        ProductRepository.getInstance().reset();
        FileStorage.getInstance().reset();
    }

    // ── HealthResource ────────────────────────────────────────────────────

    @Test
    void healthEndpoint_returns200() {
        Response r = target("/health").request().get();
        assertEquals(200, r.getStatus(), "Health endpoint must return 200");
    }

    @Test
    void healthResponse_containsStatusUp() {
        String body = target("/health").request()
            .accept(MediaType.APPLICATION_XML).get(String.class);
        assertTrue(body.contains("UP"), "Response must contain 'UP'");
    }

    @Test
    void healthResponse_containsServiceName() {
        String body = target("/health").request()
            .accept(MediaType.APPLICATION_XML).get(String.class);
        assertTrue(body.contains("ParamsLab"), "Response must contain 'ParamsLab'");
    }

    // ── ProductRepository ─────────────────────────────────────────────────

    @Test
    void productRepo_hasSixSeedProducts() {
        assertEquals(6, ProductRepository.getInstance().findAll().size());
    }

    @Test
    void productRepo_findById_returnsCorrectProduct() {
        Product p = ProductRepository.getInstance().findById("P001");
        assertNotNull(p, "P001 must exist");
        assertEquals("Clean Code", p.getName());
    }

    @Test
    void productRepo_findBySku_works() {
        Product p = ProductRepository.getInstance().findBySku("BOOK-001");
        assertNotNull(p, "BOOK-001 must exist");
        assertEquals("Clean Code", p.getName());
    }

    @Test
    void productRepo_findByNumericId_works() {
        Product p = ProductRepository.getInstance().findByNumericId(1);
        assertNotNull(p, "Numeric id 1 must return a product");
    }

    // ── OrderStatus enum ──────────────────────────────────────────────────

    @Test
    void orderStatus_hasAllFiveValues() {
        assertEquals(5, OrderStatus.values().length);
        assertNotNull(OrderStatus.valueOf("NEW"));
        assertNotNull(OrderStatus.valueOf("CANCELLED"));
    }

    // ── FileStorage ───────────────────────────────────────────────────────

    @Test
    void fileStorage_baseDirExists() {
        assertTrue(Files.isDirectory(FileStorage.getInstance().getBaseDir()),
            "Base directory must exist");
    }

    @Test
    void fileStorage_seedFilesExist() throws IOException {
        var files = FileStorage.getInstance().listFiles();
        assertTrue(files.contains("hello.txt"), "hello.txt seed file must exist");
        assertTrue(files.contains("data.csv"),  "data.csv seed file must exist");
    }
}
