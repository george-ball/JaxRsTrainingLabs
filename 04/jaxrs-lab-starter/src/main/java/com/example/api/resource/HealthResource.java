package com.example.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Health check endpoint — provided complete and ready to use.
 *
 * LAB 1 — Task 1.2
 * Test with:  curl http://localhost:8080/jaxrs-lab/api/health
 *
 * Expected response:
 * {
 *   "status":  "UP",
 *   "service": "ShopAPI",
 *   "version": "1.0",
 *   "timestamp": "2024-06-15T10:30:00"
 * }
 */
@Path("/health")
public class HealthResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> check() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status",    "UP");
        response.put("service",   "ShopAPI");
        response.put("version",   "1.0");
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }
}
