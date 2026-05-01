package com.example.xml;

import com.example.model.Order;
import com.example.util.OrderFixtures;
import javax.xml.bind.*;
import org.junit.jupiter.api.*;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the JAXB annotation mapping (Lab 2) and OrderXmlUtil (Lab 3).
 *
 * LAB 3 — Task 3.3
 * Implement every TODO test method below.
 *
 * These tests act as the specification for your annotation work:
 *   - If a test fails, something is wrong with your annotations or adapters.
 *   - Run after completing Lab 2 to check your annotation work.
 *   - Run again after completing Lab 3 to check OrderXmlUtil.
 *
 * Run with:  mvn test -Dtest=OrderAnnotationTest
 */
class OrderAnnotationTest {

    private static JAXBContext CTX;
    private static Schema      SCHEMA;

    @BeforeAll
    static void setUpJaxb() throws Exception {
        CTX = JAXBContext.newInstance(Order.class);
        SchemaFactory sf = SchemaFactory.newInstance(
            XMLConstants.W3C_XML_SCHEMA_NS_URI);
        SCHEMA = sf.newSchema(
            OrderAnnotationTest.class.getResource("/xsd/order-schema.xsd"));
    }

    // ── Helper: marshal to String ─────────────────────────────────────────

    private String marshal(Order order) throws JAXBException {
        Marshaller m = CTX.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        m.marshal(order, sw);
        return sw.toString();
    }

    // ── Helper: unmarshal from String ─────────────────────────────────────

    private Order unmarshal(String xml) throws JAXBException {
        Unmarshaller u = CTX.createUnmarshaller();
        return (Order) u.unmarshal(new StringReader(xml));
    }

    // ═══════════════════════════════════════════════════════════════════════
    // LAB 3 — Task 3.3a: Implement buildSampleOrder()
    // (The fixture is already provided in OrderFixtures — wire it up here)
    // ═══════════════════════════════════════════════════════════════════════

    // ── Basic marshalling ─────────────────────────────────────────────────

    @Test
    void marshal_producesXmlDeclaration() throws Exception {
        // TODO: marshal OrderFixtures.createSampleOrder()
        // assert the output contains "<?xml"
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_rootElementIsNamedOrder() throws Exception {
        // TODO: assert output contains "<order " or "<order>"
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_orderIdIsAnAttribute() throws Exception {
        // TODO: assert output contains 'orderId="ORD-2024-001"'
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_statusIsAnAttribute() throws Exception {
        // TODO: assert output contains 'status="PROCESSING"'
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_orderDateFormattedAsIsoDate() throws Exception {
        // TODO: assert output contains "<orderDate>2024-06-15</orderDate>"
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_linesWrappedCorrectly() throws Exception {
        // TODO: assert output contains "<lines>" and "<line sku="
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_unitPriceFormattedToTwoDecimalPlaces() throws Exception {
        // TODO: assert output contains "<unitPrice>29.99</unitPrice>"
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_internalRefIsAbsent() throws Exception {
        // TODO: assert output does NOT contain "internalRef" or "INTERNAL-REF-001"
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_notesAbsentWhenNull() throws Exception {
        // TODO: create an order with notes=null
        // assert output does NOT contain "<notes"
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void marshal_notesIncludedWhenPresent() throws Exception {
        // TODO: use OrderFixtures.createOrderWithNotes()
        // assert output DOES contain "<notes>"
        fail("TODO — Lab 3 Task 3.3");
    }

    // ── Round-trip (marshal → unmarshal) ──────────────────────────────────

    @Test
    void roundTrip_preservesOrderId() throws Exception {
        // TODO: marshal then unmarshal; assert orderId equals "ORD-2024-001"
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void roundTrip_preservesLineCount() throws Exception {
        // TODO: marshal then unmarshal; assert getLines().size() == 2
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void roundTrip_preservesTotalAmount() throws Exception {
        // TODO: marshal then unmarshal; assert totalAmount == 99.97
        // Use compareTo for BigDecimal equality
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void roundTrip_preservesOrderDate() throws Exception {
        // TODO: assert getOrderDate() equals LocalDate.of(2024, 6, 15)
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void roundTrip_preservesStatus() throws Exception {
        // TODO: assert getStatus() == OrderStatus.PROCESSING
        fail("TODO — Lab 3 Task 3.3");
    }

    // ── Schema validation ─────────────────────────────────────────────────

    @Test
    void unmarshalWithSchema_acceptsValidXml() throws Exception {
        // TODO:
        //   String xml = marshal(OrderFixtures.createSampleOrder());
        //   Unmarshaller u = CTX.createUnmarshaller();
        //   u.setSchema(SCHEMA);
        //   assertDoesNotThrow(() -> u.unmarshal(new StringReader(xml)));
        fail("TODO — Lab 3 Task 3.3");
    }

    @Test
    void unmarshalWithSchema_rejectsMissingOrderId() throws Exception {
        // TODO: build XML without orderId attribute; assert JAXBException thrown
        // Hint: construct minimal invalid XML as a String
        String badXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<order xmlns=\"http://example.com/shop/orders\" status=\"NEW\">"
            + "<customerName>Test</customerName>"
            + "<customerEmail>t@t.com</customerEmail>"
            + "<orderDate>2024-01-01</orderDate>"
            + "<lines/>"
            + "<totalAmount>0.00</totalAmount>"
            + "</order>";

        Unmarshaller u = CTX.createUnmarshaller();
        u.setSchema(SCHEMA);
        // TODO: assert that unmarshalling badXml throws JAXBException
        fail("TODO — Lab 3 Task 3.3");
    }

    // ── OrderXmlUtil ──────────────────────────────────────────────────────

    @Test
    void orderXmlUtil_toXml_returnsNonEmptyString() throws Exception {
        // TODO (complete OrderXmlUtil first):
        //   String xml = OrderXmlUtil.toXml(OrderFixtures.createSampleOrder());
        //   assertNotNull(xml);
        //   assertFalse(xml.isBlank());
        fail("TODO — Lab 3 Task 3.1 then revisit");
    }

    @Test
    void orderXmlUtil_fromXml_roundTripPreservesOrderId() throws Exception {
        // TODO (complete OrderXmlUtil first):
        //   Order original = OrderFixtures.createSampleOrder();
        //   String xml = OrderXmlUtil.toXml(original);
        //   Order restored = OrderXmlUtil.fromXml(xml);
        //   assertEquals(original.getOrderId(), restored.getOrderId());
        fail("TODO — Lab 3 Task 3.1 then revisit");
    }
}
