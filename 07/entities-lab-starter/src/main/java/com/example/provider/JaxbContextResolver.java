package com.example.provider;

import com.example.model.Address;
import com.example.model.Order;
import com.example.model.OrderLine;
import com.example.model.OrderStatus;
import com.example.model.Product;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Supplies a shared JAXBContext to the Jersey JAXB media provider.
 *
 * LAB 2 — Task 2.1
 *
 * Why is this needed?
 * Jersey's built-in JAXB provider creates a new JAXBContext for each
 * class it encounters. This works but is expensive. Registering a
 * ContextResolver<JAXBContext> lets you supply a pre-built, pre-warmed
 * context that covers all your domain classes at once.
 *
 * Additionally, without this resolver:
 *   - LocalDate fields cannot be marshalled (no built-in JAXB support)
 *   - CurrencyAdapter is not applied (BigDecimal marshals as raw number)
 *   - Namespace declarations may be missing from the XML output
 *
 * After implementing this class:
 *   - All model classes share one JAXBContext
 *   - LocalDate → "2024-06-15" (via LocalDateAdapter registered in package-info.java)
 *   - BigDecimal → "39.99" (2 decimal places, via CurrencyAdapter)
 *   - XML output matches the target format in Lab 3
 *
 * JAXBContext is thread-safe — create ONE instance and reuse it.
 * @Provider causes auto-discovery by Jersey's classpath scan.
 */
@Provider
public class JaxbContextResolver implements ContextResolver<JAXBContext> {

    private final JAXBContext ctx;

    public JaxbContextResolver() throws JAXBException {
        // TODO (Task 2.1):
        // Create one JAXBContext covering ALL model classes:
        //   ctx = JAXBContext.newInstance(
        //       Order.class, OrderLine.class, Address.class,
        //       OrderStatus.class, Product.class);
        //
        // Note: JAXBContext.newInstance(Class...) registers all passed classes
        // and any classes reachable from their fields. Passing Order.class alone
        // would suffice because Order references Address and OrderLine, but
        // listing all classes explicitly is clearer and more robust.
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.1");
    }

    /**
     * Return the shared JAXBContext for the given type.
     *
     * Jersey calls this before marshalling or unmarshalling.
     * Return null to tell Jersey to use its default JAXBContext for this type
     * (useful if you want to handle only specific classes).
     *
     * For this lab: always return the shared ctx.
     */
    @Override
    public JAXBContext getContext(Class<?> type) {
        // TODO: return ctx;
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.1");
    }
}
