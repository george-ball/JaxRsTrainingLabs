package com.example.provider;

import com.example.model.Product;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * Custom entity provider that serialises a single Product to text/csv.
 *
 * LAB 1 — Task 1.1
 *
 * This writer is selected by the JAX-RS runtime when:
 *   1. The method return type is Product (or assignable to Product)
 *   2. The negotiated response media type is text/csv
 *
 * After implementing this class, the following request will route through here:
 *   GET /api/products/P001
 *   Accept: text/csv
 *
 * Expected output:
 *   id,name,category,price
 *   P001,Clean Code,books,39.99
 *
 * IMPORTANT: Never close the entityStream — the container manages its lifecycle.
 */
@Provider
@Produces("text/csv")
public class ProductCsvWriter implements MessageBodyWriter<Product> {

    /**
     * Guard method — called first by the runtime.
     * Return true ONLY when both conditions are true:
     *   1. type is Product (or a subclass)
     *   2. mediaType is compatible with text/csv
     */
    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        // TODO: check Product.class.isAssignableFrom(type)
        //       AND mediaType.isCompatible(new MediaType("text", "csv"))
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.1");
    }

    /**
     * Deprecated in JAX-RS 2.0 — always return -1.
     * The container will use chunked transfer encoding.
     */
    @Override
    public long getSize(Product product, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    /**
     * Write the product as CSV to the entity stream.
     *
     * Format:
     *   Line 1: id,name,category,price      (header)
     *   Line 2: P001,Clean Code,books,39.99  (data)
     *
     * Implementation steps:
     *   1. Create a PrintWriter wrapping entityStream with UTF-8 encoding
     *   2. Write the header line
     *   3. Write the data line using printf
     *   4. Flush the PrintWriter
     *   5. DO NOT close entityStream
     */
    @Override
    public void writeTo(Product product,
                        Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream)
            throws IOException, WebApplicationException {
        // TODO:
        //   PrintWriter pw = new PrintWriter(
        //       new OutputStreamWriter(entityStream, StandardCharsets.UTF_8));
        //   pw.println("id,name,category,price");
        //   pw.printf("%s,%s,%s,%.2f%n",
        //       product.getId(), product.getName(),
        //       product.getCategory(), product.getPrice());
        //   pw.flush();
        //   // NEVER close entityStream — the container owns it
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.1");
    }
}
