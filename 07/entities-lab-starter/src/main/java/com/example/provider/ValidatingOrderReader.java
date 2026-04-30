package com.example.provider;

import com.example.model.Order;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Validates and unmarshals an XML request body into an Order.
 *
 * LAB 4 — Task 4.4
 *
 * This reader:
 *   1. Compiles order-schema.xsd once at class loading (static initialiser)
 *   2. Creates a JAXBContext for Order once at class loading
 *   3. On each request: creates an Unmarshaller, attaches the Schema,
 *      attaches a validation event handler, and unmarshals the body
 *   4. Returns 400 Bad Request if schema validation fails
 *   5. Prevents XXE by using a hardened XMLInputFactory
 *
 * Threading:
 *   JAXBContext and Schema are thread-safe — create ONCE in static initialiser.
 *   Unmarshaller is NOT thread-safe — create a new one per request.
 *
 * XSD location on classpath: /xsd/order-schema.xsd
 * (src/main/resources/xsd/order-schema.xsd is packaged to WEB-INF/classes/xsd/)
 */
@Provider
@Consumes(MediaType.APPLICATION_XML)
public class ValidatingOrderReader implements MessageBodyReader<Order> {

    // ── Static singletons (thread-safe) ──────────────────────────────────
    private static final JAXBContext CTX;
    private static final Schema      SCHEMA;

    static {
        try {
            // TODO (Task 4.4 Step 1): Create JAXBContext for Order.class
            //   CTX = JAXBContext.newInstance(Order.class);
            //
            // TODO (Task 4.4 Step 2): Compile Schema from classpath
            //   SchemaFactory sf = SchemaFactory.newInstance(
            //       XMLConstants.W3C_XML_SCHEMA_NS_URI);
            //   SCHEMA = sf.newSchema(
            //       ValidatingOrderReader.class.getResource("/xsd/order-schema.xsd"));

            CTX    = null; // replace with your implementation
            SCHEMA = null; // replace with your implementation

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    // ── isReadable ────────────────────────────────────────────────────────

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
                              Annotation[] annotations, MediaType mediaType) {
        // Handles Order bodies with Content-Type: application/xml
        return Order.class.isAssignableFrom(type)
            && mediaType.isCompatible(MediaType.APPLICATION_XML_TYPE);
    }

    // ── readFrom ──────────────────────────────────────────────────────────

    @Override
    public Order readFrom(Class<Order> type, Type genericType,
                          Annotation[] annotations, MediaType mediaType,
                          MultivaluedMap<String, String> httpHeaders,
                          InputStream entityStream)
            throws IOException, WebApplicationException {
        // TODO (Task 4.4 Step 3):
        //   1. Build a safe StAX source with XXE disabled (see buildSafeSource)
        //   2. Create an Unmarshaller from CTX
        //   3. Attach SCHEMA to the Unmarshaller
        //   4. Set a ValidationEventHandler that throws RuntimeException on ERROR+
        //   5. Unmarshal the source and return the Order
        //   6. Catch JAXBException / RuntimeException → throw BadRequestException

        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.4");
    }

    // ── XXE Prevention ────────────────────────────────────────────────────

    /**
     * Creates a StAX Source from the given InputStream with external entity
     * processing DISABLED.
     *
     * LAB 4 — Task 4.4 (security requirement)
     *
     * The two mandatory XMLInputFactory property settings are:
     *   IS_SUPPORTING_EXTERNAL_ENTITIES = false   → prevents external entity expansion
     *   SUPPORT_DTD                     = false   → prevents DOCTYPE declarations
     *
     * Both must be set. Setting only one leaves a partial attack surface.
     */
    private Source buildSafeSource(InputStream is)
            throws IOException, XMLStreamException {
        // TODO:
        //   XMLInputFactory xif = XMLInputFactory.newInstance();
        //   xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        //   xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        //   return new StAXSource(xif.createXMLStreamReader(is));
        throw new UnsupportedOperationException("TODO — Lab 4 Task 4.4 (XXE prevention)");
    }
}
