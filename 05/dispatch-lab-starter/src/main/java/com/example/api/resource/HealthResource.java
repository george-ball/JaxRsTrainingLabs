package com.example.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

/**
 * Health check endpoint.  Complete – do not modify.
 *
 * URI:  GET /api/health
 * Used in Lab 1 Task 1.1 to confirm the application deployed correctly.
 *
 * Expected XML response:
 * <?xml version="1.0" encoding="UTF-8"?>
 * <health>
 *   <status>UP</status>
 *   <service>DispatchLab</service>
 *   <version>1.0</version>
 *   <timestamp>2024-06-15T10:30:00</timestamp>
 * </health>
 */
@Path("/health")
public class HealthResource {

    /**
     * Inner JAXB response bean — only used by this endpoint.
     */
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
            this.status    = status;
            this.service   = service;
            this.version   = version;
            this.timestamp = timestamp;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public HealthStatus check() {
        return new HealthStatus("UP", "DispatchLab", "1.0",
                                LocalDateTime.now().toString());
    }
}
