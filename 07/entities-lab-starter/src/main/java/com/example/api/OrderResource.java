package com.example.api;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Order resource.
 *
 * Lab 3: GET /api/orders/{id} returns XML once Order is annotated with JAXB.
 * Lab 4: POST /api/orders validates XML via ValidatingOrderReader.
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class OrderResource {

    private final OrderRepository repo = OrderRepository.getInstance();

    /** GET /api/orders */
    @GET
    public List<Order> listAll() { return repo.findAll(); }

    /**
     * GET /api/orders/{id}
     * Lab 3: observe XML output before/after JAXB annotations on Order.
     */
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id) {
        Order o = repo.findById(id);
        if (o == null) return Response.status(404).build();
        return Response.ok(o).build();
    }

    /**
     * POST /api/orders
     * Accepts an XML Order body and returns the saved order as XML.
     * Lab 4: once ValidatingOrderReader is registered this endpoint
     * validates the body against order-schema.xsd before binding.
     *
     * curl -X POST http://localhost:8080/entities-lab/api/orders \
     *      -H 'Content-Type: application/xml' \
     *      -H 'Accept: application/xml' \
     *      -d '@valid-order.xml'
     */
    @POST
    public Response create(Order order, @Context UriInfo uriInfo) {
        Order saved = repo.save(order);
        return Response.created(
            uriInfo.getAbsolutePathBuilder().path(saved.getOrderId()).build()
        ).entity(saved).build();
    }
}
