package com.example.web;

import com.example.model.Order;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Servlet that exposes an XML order API over HTTP.
 *
 * LAB 4 — Tasks 4.2 and 4.3
 *
 * Endpoint: /api/orders/*
 *
 * POST /api/orders
 *   - Reads XML from the request body
 *   - Validates against the schema (using the shared Schema from JaxbContextListener)
 *   - Stores the Order in the in-memory map
 *   - Returns 201 Created with body: "Created: {orderId}"
 *   - Returns 400 Bad Request if the XML is invalid
 *
 * GET /api/orders/{orderId}
 *   - Looks up the Order by its orderId path parameter
 *   - Marshals it to XML and writes to the response
 *   - Returns 200 OK with Content-Type: application/xml
 *   - Returns 404 Not Found if the orderId is unknown
 *
 * Threading: The store map is ConcurrentHashMap — safe for concurrent access.
 * JAXBContext and Schema come from ServletContext (initialised by JaxbContextListener).
 * Marshaller and Unmarshaller are created per-request — they are NOT thread-safe.
 */
@WebServlet("/api/orders/*")
public class OrderServlet extends HttpServlet {

    /** In-memory order store — keyed by orderId. */
    private final Map<String, Order> store = new ConcurrentHashMap<>();

    // ── POST — receive and store an order ─────────────────────────────────

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // Retrieve the shared context and schema from ServletContext
        JAXBContext ctx    = (JAXBContext) getServletContext().getAttribute(
                                JaxbContextListener.JAXB_CTX_ATTR);
        Schema      schema = (Schema)      getServletContext().getAttribute(
                                JaxbContextListener.JAXB_SCHEMA_ATTR);

        // TODO (Task 4.2):
        // try {
        //     Unmarshaller u = ctx.createUnmarshaller();
        //     u.setSchema(schema);                      // enable validation
        //     Order order = (Order) u.unmarshal(req.getInputStream());
        //     store.put(order.getOrderId(), order);
        //     resp.setStatus(HttpServletResponse.SC_CREATED);  // 201
        //     resp.setContentType("text/plain;charset=UTF-8");
        //     resp.getWriter().write("Created: " + order.getOrderId());
        // } catch (JAXBException e) {
        //     resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
        //                    "Invalid XML: " + e.getMessage());
        // }

        resp.sendError(501, "TODO — Lab 4 Task 4.2: implement doPost()");
    }

    // ── GET — retrieve and return an order ───────────────────────────────

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // Extract orderId from path: /api/orders/{orderId}
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                           "Please specify an orderId: /api/orders/{orderId}");
            return;
        }
        String orderId = pathInfo.substring(1);   // strip leading /

        Order order = store.get(orderId);
        if (order == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                           "Order not found: " + orderId);
            return;
        }

        JAXBContext ctx = (JAXBContext) getServletContext().getAttribute(
                              JaxbContextListener.JAXB_CTX_ATTR);

        // TODO (Task 4.3):
        // try {
        //     Marshaller m = ctx.createMarshaller();
        //     // NOTE: do NOT set JAXB_FORMATTED_OUTPUT in production
        //     m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //     resp.setContentType("application/xml;charset=UTF-8");
        //     m.marshal(order, resp.getOutputStream());
        // } catch (JAXBException e) {
        //     resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
        //                    e.getMessage());
        // }

        resp.sendError(501, "TODO — Lab 4 Task 4.3: implement doGet()");
    }
}
