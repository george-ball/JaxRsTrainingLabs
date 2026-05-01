package com.example.api;

import com.example.api.model.Product;
import com.example.api.model.ProductList;
import com.example.api.model.Review;
import com.example.api.model.ReviewList;
import com.example.api.repository.ProductRepository;
import com.example.api.repository.ReviewRepository;
import com.example.api.resource.HealthResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Smoke tests — all must PASS before starting any lab.
 *
 * Run with:  mvn test
 *
 * Uses Jersey's in-memory Grizzly test container — no Tomcat required.
 * The tests cover model construction, JAXB marshalling, and repository operations.
 * They do NOT test the resource TODO methods (those are empty stubs until the labs).
 *
 * Do NOT modify these tests. They are your green baseline.
 */
class SmokeTest extends JerseyTest {

    @Override
    protected Application configure() {
        // Register only HealthResource (the one complete resource in the starter)
        // plus the jersey-media-jaxb feature so APPLICATION_XML works in tests.
        ResourceConfig config = new ResourceConfig(HealthResource.class);
        // jersey-media-jaxb is on the classpath via pom.xml dependency;
        // Jersey auto-discovers it, no explicit registration needed.
        return config;
    }

    @BeforeEach
    void reset() {
        ProductRepository.getInstance().reset();
    }

    // ── Health endpoint ───────────────────────────────────────────────────

    @Test
    void health_returns200() {
        Response r = target("/health").request(MediaType.APPLICATION_XML).get();
        assertEquals(200, r.getStatus(),
            "GET /health must return 200 OK");
    }

    @Test
    void health_returnsXml() {
        String body = target("/health")
            .request(MediaType.APPLICATION_XML)
            .get(String.class);
        assertTrue(body.contains("<status>UP</status>"),
            "Health response must contain <status>UP</status>");
        assertTrue(body.contains("<service>ShopAPI</service>"),
            "Health response must contain <service>ShopAPI</service>");
    }

    @Test
    void health_contentTypeIsXml() {
        Response r = target("/health").request(MediaType.APPLICATION_XML).get();
        String ct = r.getHeaderString("Content-Type");
        assertNotNull(ct, "Content-Type header must be present");
        assertTrue(ct.contains("application/xml"),
            "Content-Type must be application/xml, got: " + ct);
    }

    // ── Model construction ────────────────────────────────────────────────

    @Test
    void product_canBeConstructed() {
        Product p = new Product("P001", "Clean Code", "books",
            new BigDecimal("39.99"), 50, true);
        assertEquals("P001",       p.getId());
        assertEquals("Clean Code", p.getName());
        assertEquals("books",      p.getCategory());
        assertEquals(0, new BigDecimal("39.99").compareTo(p.getPrice()));
        assertEquals(50,           p.getStockLevel());
        assertTrue(p.isActive());
    }

    @Test
    void product_noArgConstructorWorks() {
        Product p = new Product();
        assertNull(p.getId());
        assertNull(p.getName());
    }

    @Test
    void productList_wrapsCorrectly() {
        ProductList list = new ProductList(
            ProductRepository.getInstance().findAll());
        assertEquals(6, list.getCount());
        assertEquals(6, list.getProducts().size());
    }

    @Test
    void review_canBeConstructed() {
        Review r = new Review();
        r.setId("R001");
        r.setProductId("P001");
        r.setRating(5);
        r.setReviewerName("Alice");
        r.setComment("Great book");
        assertEquals("R001",  r.getId());
        assertEquals("P001",  r.getProductId());
        assertEquals(5,       r.getRating());
        assertEquals("Alice", r.getReviewerName());
    }

    @Test
    void reviewList_wrapsCorrectly() {
        ReviewList list = new ReviewList("P001",
            ReviewRepository.getInstance().findByProduct("P001"));
        assertEquals("P001", list.getProductId());
        assertEquals(2,      list.getCount());
        assertEquals(2,      list.getReviews().size());
    }

    // ── JAXB marshalling ──────────────────────────────────────────────────

    @Test
    void product_marshalsToXml() throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(Product.class);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        m.marshal(new Product("P001", "Clean Code", "books",
            new BigDecimal("39.99"), 50, true), sw);
        String xml = sw.toString();
        assertTrue(xml.contains("id=\"P001\""),
            "id must be an XML attribute");
        assertTrue(xml.contains("active=\"true\""),
            "active must be an XML attribute");
        assertTrue(xml.contains("<name>Clean Code</name>"),
            "name must be an XML element");
        assertTrue(xml.contains("<category>books</category>"),
            "category must be an XML element");
        assertFalse(xml.contains("<id>"),
            "id must NOT appear as a child element");
    }

    @Test
    void productList_marshalsToXml() throws Exception {
        ProductList list = new ProductList(
            ProductRepository.getInstance().findAll());
        JAXBContext ctx = JAXBContext.newInstance(ProductList.class, Product.class);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        m.marshal(list, sw);
        String xml = sw.toString();
        assertTrue(xml.contains("<products"),
            "Root element must be <products");
        assertTrue(xml.contains("count=\"6\""),
            "count attribute must equal 6");
        assertTrue(xml.contains("<product "),
            "Must contain <product> children");
    }

    @Test
    void reviewList_marshalsToXml() throws Exception {
        ReviewList list = new ReviewList("P001",
            ReviewRepository.getInstance().findByProduct("P001"));
        JAXBContext ctx = JAXBContext.newInstance(ReviewList.class, Review.class);
        Marshaller m = ctx.createMarshaller();
        StringWriter sw = new StringWriter();
        m.marshal(list, sw);
        String xml = sw.toString();
        assertTrue(xml.contains("<reviews"),      "Root must be <reviews");
        assertTrue(xml.contains("productId=\"P001\""), "productId attribute must be P001");
        assertTrue(xml.contains("<review "),      "Must contain <review> children");
    }

    // ── Repository ────────────────────────────────────────────────────────

    @Test
    void repository_hasSixSeedProducts() {
        assertEquals(6, ProductRepository.getInstance().findAll().size());
    }

    @Test
    void repository_p001IsCleanCode() {
        Product p = ProductRepository.getInstance().findById("P001");
        assertNotNull(p, "P001 must exist");
        assertEquals("Clean Code", p.getName());
        assertTrue(p.isActive());
    }

    @Test
    void repository_filterByCategory() {
        var books = ProductRepository.getInstance().find("books", 0, 20, true);
        assertFalse(books.isEmpty(), "Must find at least one book");
        books.forEach(p ->
            assertEquals("books", p.getCategory(), "All results must be books"));
    }

    @Test
    void repository_inactiveProductExcludedByDefault() {
        // P006 (Old Java EE 5 Book) is seeded as active=false
        var active = ProductRepository.getInstance().find(null, 0, 20, true);
        boolean hasInactive = active.stream().anyMatch(p -> !p.isActive());
        assertFalse(hasInactive,
            "activeOnly=true must exclude inactive products");
    }

    @Test
    void repository_saveAssignsId() {
        Product p = new Product(null, "New Book", "books",
            new BigDecimal("19.99"), 5, true);
        Product saved = ProductRepository.getInstance().save(p);
        assertNotNull(saved.getId(), "Saved product must have a generated ID");
        assertFalse(saved.getId().isBlank());
    }

    @Test
    void repository_deleteReturnsTrueForExisting() {
        assertTrue(ProductRepository.getInstance().delete("P001"));
        assertNull(ProductRepository.getInstance().findById("P001"));
    }

    @Test
    void repository_deleteReturnsFalseForMissing() {
        assertFalse(ProductRepository.getInstance().delete("DOES_NOT_EXIST"));
    }

    @Test
    void repository_resetRestoresSeedData() {
        ProductRepository.getInstance().delete("P001");
        ProductRepository.getInstance().reset();
        assertNotNull(ProductRepository.getInstance().findById("P001"),
            "P001 must be restored after reset");
        assertEquals(6, ProductRepository.getInstance().findAll().size());
    }

    @Test
    void reviewRepository_hasTwoSeedReviewsForP001() {
        var reviews = ReviewRepository.getInstance().findByProduct("P001");
        assertEquals(2, reviews.size());
        reviews.forEach(r ->
            assertEquals("P001", r.getProductId()));
    }

    @Test
    void reviewRepository_hasOneReviewForP002() {
        var reviews = ReviewRepository.getInstance().findByProduct("P002");
        assertEquals(1, reviews.size());
        assertEquals("P002", reviews.get(0).getProductId());
    }
}
