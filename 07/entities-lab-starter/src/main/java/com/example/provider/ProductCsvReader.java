package com.example.provider;

import com.example.model.Product;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * Custom entity provider that reads a text/csv request body into a Product.
 *
 * LAB 1 — Task 1.2
 *
 * This reader is selected when:
 *   1. The method parameter type is Product
 *   2. The request Content-Type is text/csv
 *
 * Expected CSV format (two lines):
 *   id,name,category,price        (header — skip this line)
 *   PNEW,Refactoring,books,44.99  (data)
 *
 * Test:
 *   curl -X POST http://localhost:8080/entities-lab/api/products/csv \
 *        -H 'Content-Type: text/csv' \
 *        -d $'id,name,category,price\nPNEW,Refactoring,books,44.99'
 *
 * IMPORTANT: Never close the entityStream.
 */
@Provider
@Consumes("text/csv")
public class ProductCsvReader implements MessageBodyReader<Product> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
                              Annotation[] annotations, MediaType mediaType) {
        // TODO: same guards as ProductCsvWriter.isWriteable()
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.2");
    }

    @Override
    public Product readFrom(Class<Product> type, Type genericType,
                            Annotation[] annotations, MediaType mediaType,
                            MultivaluedMap<String, String> httpHeaders,
                            InputStream entityStream)
            throws IOException, WebApplicationException {
        // TODO:
        //   BufferedReader reader = new BufferedReader(
        //       new InputStreamReader(entityStream, StandardCharsets.UTF_8));
        //   reader.readLine();               // skip header
        //   String data = reader.readLine(); // data row
        //   if (data == null) throw new BadRequestException("Empty CSV body");
        //   String[] parts = data.split(",");
        //   Product p = new Product();
        //   p.setId(parts[0].trim());
        //   p.setName(parts[1].trim());
        //   p.setCategory(parts[2].trim());
        //   p.setPrice(new BigDecimal(parts[3].trim()));
        //   return p;
        //   // NEVER close entityStream
        throw new UnsupportedOperationException("TODO — Lab 1 Task 1.2");
    }
}
