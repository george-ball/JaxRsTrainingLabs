package com.example.api.resource;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Database access demonstration.
 *
 * LAB 3 — Tasks 3.1, 3.2
 *
 * Looks up the jdbc/shopDB DataSource from JNDI, then uses it to:
 *   Task 3.1 — list products from the H2 in-memory database (plain text)
 *   Task 3.2 — create a product inside a manual transaction
 *
 * Returns TEXT_PLAIN (CSV-style rows) — JDBC results are plain tabular data,
 * not domain objects, so they do not benefit from XML marshalling.
 */
@Path("/db")
@Produces(MediaType.TEXT_PLAIN)
public class ProductResource {

    private DataSource ds;

    /**
     * Called once by JAX-RS when the resource instance is created.
     * Looks up the DataSource and stores it for all subsequent requests.
     */
    @javax.ws.rs.core.Context
    public void setServletContext(ServletContext ctx) {
        try {
            javax.naming.Context ic = new InitialContext();
            this.ds = (DataSource) ic.lookup("java:comp/env/jdbc/shopDB");
        } catch (NamingException e) {
            throw new RuntimeException("DataSource not found: " + e.getMessage(), e);
        }
    }

    // ── GET /api/db/products — Lab 3 Task 3.1 ────────────────────────────
    // Returns one product per line:  id,name,category,price
    @GET
    @Path("products")
    public String list() {
        // TODO: use try-with-resources to open Connection, PreparedStatement, ResultSet
        //   String sql = "SELECT id, name, category, price FROM products ORDER BY id";
        //   StringBuilder sb = new StringBuilder("id,name,category,price\n");
        //   try (Connection c = ds.getConnection();
        //        PreparedStatement ps = c.prepareStatement(sql);
        //        ResultSet rs = ps.executeQuery()) {
        //     while (rs.next()) {
        //       sb.append(rs.getString("id")).append(",")
        //         .append(rs.getString("name")).append(",")
        //         .append(rs.getString("category")).append(",")
        //         .append(rs.getBigDecimal("price")).append("\n");
        //     }
        //   } catch (SQLException e) {
        //     throw new InternalServerErrorException("DB error: " + e.getMessage(), e);
        //   }
        //   return sb.toString();
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.1");
    }

    // ── POST /api/db/products — Lab 3 Task 3.2 ───────────────────────────
    // Accepts plain-text body:  id=PNEW,name=Design Patterns,category=books,price=44.99
    // Creates a product AND inserts an audit log row in a single transaction.
    // If either insert fails, the entire transaction is rolled back.
    @POST
    @Path("products")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response create(String body) {
        // Parse the plain-text body: id=X,name=Y,category=Z,price=N
        // TODO:
        //   1. Parse key=value pairs from body
        //   2. try (Connection conn = ds.getConnection())
        //   3. conn.setAutoCommit(false)  — begin transaction
        //   4. try {
        //        insert INTO products ...
        //        insert INTO audit_log ...
        //        conn.commit();
        //        return Response.status(201).entity("created=" + id).build();
        //      }
        //   5. catch { conn.rollback(); throw InternalServerErrorException }
        throw new UnsupportedOperationException("TODO — Lab 3 Task 3.2");
    }

    // ── GET /api/db/audit — for verifying Lab 3 Task 3.2 ─────────────────
    @GET
    @Path("audit")
    public String auditLog() {
        StringBuilder sb = new StringBuilder("id,action,entityId,createdAt\n");
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(
                 "SELECT id, action, entity_id, created_at FROM audit_log ORDER BY id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                sb.append(rs.getInt("id")).append(",")
                  .append(rs.getString("action")).append(",")
                  .append(rs.getString("entity_id")).append(",")
                  .append(rs.getTimestamp("created_at")).append("\n");
            }
        } catch (SQLException e) {
            throw new InternalServerErrorException("Database error: " + e.getMessage(), e);
        }
        return sb.toString();
    }
}
