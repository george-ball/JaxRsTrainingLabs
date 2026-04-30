package com.example.api.converter;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Custom ParamConverter for java.time.LocalDate.
 *
 * LAB 1 — Task 1.4
 *
 * JAX-RS has no built-in support for java.time.LocalDate. This @Provider
 * is discovered at startup and enables LocalDate to be used as a @QueryParam,
 * @PathParam, @HeaderParam, etc.
 *
 * After implementing this class, the following will work automatically:
 *
 *   @GET @Path("/events")
 *   public Response events(
 *       @QueryParam("from") LocalDate from,
 *       @QueryParam("to")   LocalDate to) { ... }
 *
 *   // GET /api/params/events?from=2024-01-01&to=2024-12-31
 *
 * Conversion rules to implement:
 *   fromString(null)     → return null
 *   fromString("")       → return null (blank)
 *   fromString("2024-01-15") → LocalDate.parse("2024-01-15")
 *   fromString("invalid")    → throw BadRequestException
 *
 *   toString(null)       → return null
 *   toString(LocalDate)  → value.toString()  (ISO format: "2024-01-15")
 */
@Provider
public class LocalDateConverterProvider implements ParamConverterProvider {

    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(
            Class<T> rawType,
            Type genericType,
            Annotation[] annotations) {

        // Only handle LocalDate — return null for all other types
        if (!LocalDate.class.equals(rawType)) {
            return null;
        }

        return (ParamConverter<T>) new ParamConverter<LocalDate>() {

            /**
             * Convert a String from the HTTP request to a LocalDate.
             *
             * @param value the string value from the request (may be null or blank)
             * @return the parsed LocalDate, or null if value is null or blank
             * @throws BadRequestException if the string cannot be parsed as a date
             */
            @Override
            public LocalDate fromString(String value) {
                // TODO:
                //   1. if value == null || value.isBlank() return null
                //   2. try { return LocalDate.parse(value.trim()); }
                //   3. catch DateTimeParseException →
                //        throw new BadRequestException("Invalid date: " + value)
                throw new UnsupportedOperationException("TODO — Lab 1 Task 1.4");
            }

            /**
             * Convert a LocalDate to a String for use in outbound requests.
             * (Used by client-side proxies — rarely called server-side.)
             *
             * @param value the LocalDate to format (may be null)
             * @return ISO formatted date string, or null if value is null
             */
            @Override
            public String toString(LocalDate value) {
                // TODO: return value == null ? null : value.toString()
                throw new UnsupportedOperationException("TODO — Lab 1 Task 1.4");
            }
        };
    }
}
