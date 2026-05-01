package com.example.web;

import com.example.model.Order;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 * Initialises the JAXBContext and compiled Schema ONCE when Tomcat
 * starts the application, and stores them as ServletContext attributes.
 *
 * LAB 4 — Task 4.1
 *
 * Why initialise here and not inside the Servlet?
 * Creating a JAXBContext is expensive (it scans annotations, builds internal
 * maps, etc.). If we created one inside doGet() or doPost(), every HTTP request
 * would pay that cost. The @WebListener fires once at startup; subsequent
 * requests retrieve the pre-built context from the ServletContext attribute map.
 *
 * Attribute names (use these constants — OrderServlet reads them by the same names):
 *   JAXB_CTX_ATTR    = "jaxbContext"
 *   JAXB_SCHEMA_ATTR = "jaxbSchema"
 *
 * Complete Task 4.1:
 *   1. Create JAXBContext for Order.class.
 *   2. Compile Schema from "/xsd/order-schema.xsd" on the classpath.
 *   3. Store both in sce.getServletContext() using the attribute name constants.
 *   4. If initialisation fails, throw RuntimeException to prevent deployment.
 */
@WebListener
public class JaxbContextListener implements ServletContextListener {

    /** ServletContext attribute name for the shared JAXBContext. */
    public static final String JAXB_CTX_ATTR    = "jaxbContext";

    /** ServletContext attribute name for the compiled Schema. */
    public static final String JAXB_SCHEMA_ATTR = "jaxbSchema";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // TODO (Task 4.1):
            // JAXBContext ctx = JAXBContext.newInstance(Order.class);
            // sce.getServletContext().setAttribute(JAXB_CTX_ATTR, ctx);
            //
            // SchemaFactory sf = SchemaFactory.newInstance(
            //     XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // Schema schema = sf.newSchema(
            //     getClass().getResource("/xsd/order-schema.xsd"));
            // sce.getServletContext().setAttribute(JAXB_SCHEMA_ATTR, schema);
            //
            // System.out.println("[JAXB] Context and schema initialised OK");

            System.out.println("[JAXB] TODO: JaxbContextListener not yet implemented");

        } catch (Exception e) {
            throw new RuntimeException("JAXB initialisation failed — cannot start application", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(JAXB_CTX_ATTR);
        sce.getServletContext().removeAttribute(JAXB_SCHEMA_ATTR);
    }
}
