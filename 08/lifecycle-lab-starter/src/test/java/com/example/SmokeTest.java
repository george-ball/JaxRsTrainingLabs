package com.example;

import com.example.api.security.TokenStore;
import com.example.api.security.UserPrincipal;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.*;

import javax.ws.rs.core.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Baseline smoke tests — all 8 must PASS before starting any lab.
 * Run with:  mvn test -Dtest=SmokeTest
 * Do NOT modify these tests.
 */
class SmokeTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(com.example.api.resource.HealthResource.class)
            .register(org.glassfish.jersey.media.jaxb.JaxbFeature.class);
    }

    // ── HealthResource ────────────────────────────────────────────────────
    @Test void health_returns200() {
        assertEquals(200, target("/health").request().get().getStatus());
    }

    @Test void health_containsServiceName() {
        String body = target("/health").request().accept(MediaType.APPLICATION_XML).get(String.class);
        assertTrue(body.contains("LifecycleLab"), "Response must contain 'LifecycleLab'");
    }

    @Test void health_containsStatusUp() {
        String body = target("/health").request().accept(MediaType.APPLICATION_XML).get(String.class);
        assertTrue(body.contains("UP"));
    }

    // ── TokenStore ────────────────────────────────────────────────────────
    @Test void tokenStore_validUserToken() {
        UserPrincipal p = TokenStore.validate("user-token");
        assertNotNull(p, "user-token must resolve to a principal");
        assertEquals("alice", p.getName());
        assertTrue(p.getRoles().contains("user"));
    }

    @Test void tokenStore_validAdminToken() {
        UserPrincipal p = TokenStore.validate("admin-token");
        assertNotNull(p, "admin-token must resolve to a principal");
        assertEquals("bob", p.getName());
        assertTrue(p.getRoles().contains("admin"));
        assertTrue(p.getRoles().contains("user"));
    }

    @Test void tokenStore_invalidToken_returnsNull() {
        assertNull(TokenStore.validate("fake-token"), "Unknown token must return null");
    }

    @Test void tokenStore_nullToken_returnsNull() {
        assertNull(TokenStore.validate(null), "Null token must return null");
    }

    @Test void tokenStore_blankToken_returnsNull() {
        assertNull(TokenStore.validate("   "), "Blank token must return null");
    }
}
