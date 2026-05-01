package com.example.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * Health check endpoint — provided complete, ready to use from Lab 1.
 *
 * GET /api/health  →  application/xml
 *
 * Expected response:
 *   <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 *   <health>
 *     <status>UP</status>
 *     <service>ShopAPI</service>
 *     <version>1.0</version>
 *     <timestamp>2024-06-15T10:30:00.123</timestamp>
 *   </health>
 *
 * curl http://localhost:8080/jaxrs-xml-lab/api/health
 */
@Path("/health")
public class HealthResource {

    @XmlRootElement(name = "health")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class HealthStatus {
        @XmlElement public String status;
        @XmlElement public String service;
        @XmlElement public String version;
        @XmlElement public String timestamp;
        public HealthStatus() {}
        public HealthStatus(String status, String service,
                            String version, String timestamp) {
            this.status = status; this.service = service;
            this.version = version; this.timestamp = timestamp;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public HealthStatus check() {
        return new HealthStatus(
            "UP", "ShopAPI", "1.0", LocalDateTime.now().toString());
    }
}
