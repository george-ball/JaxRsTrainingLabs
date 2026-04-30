package com.example.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * Health endpoint. Complete – do not modify.
 *
 * GET /api/health  →  XML:
 * <?xml version="1.0" encoding="UTF-8"?>
 * <health>
 *   <status>UP</status>
 *   <service>LifecycleLab</service>
 *   <version>1.0</version>
 *   <timestamp>2024-06-15T10:30:00</timestamp>
 * </health>
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
        public HealthStatus(String s, String svc, String v, String t) {
            status=s; service=svc; version=v; timestamp=t;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public HealthStatus check() {
        return new HealthStatus("UP", "LifecycleLab", "1.0",
                LocalDateTime.now().toString());
    }
}
