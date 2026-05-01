package com.example.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * XmlAdapter that bridges LocalDate (java.time) and a plain date String.
 *
 * LAB 2 — Task 2.5
 *
 * Why is this needed?
 * JAXB 2.3 maps xs:date to XMLGregorianCalendar by default — a verbose,
 * pre-Java-8 type. This adapter lets us use the cleaner java.time.LocalDate
 * instead, converting to/from "yyyy-MM-dd" strings during binding.
 *
 * Implementation rules:
 *   - Use DateTimeFormatter.ISO_LOCAL_DATE (formats as yyyy-MM-dd)
 *   - marshal(null)    must return null safely
 *   - unmarshal(null)  must return null safely
 *   - unmarshal("")    must return null safely (blank string)
 *
 * Usage on an Order field:
 *   @XmlElement(required = true)
 *   @XmlJavaTypeAdapter(LocalDateAdapter.class)
 *   private LocalDate orderDate;
 *
 * Expected behaviour:
 *   marshal(LocalDate.of(2024, 6, 15))   →  "2024-06-15"
 *   unmarshal("2024-06-15")              →  LocalDate.of(2024, 6, 15)
 *   marshal(null)                         →  null
 *   unmarshal(null)                       →  null
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    // Hint: DateTimeFormatter.ISO_LOCAL_DATE formats as "yyyy-MM-dd"
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Convert XML string value → Java LocalDate.
     * Called during unmarshalling (XML → Java).
     *
     * @param value the string read from the XML document (may be null or blank)
     * @return the parsed LocalDate, or null if value is null/blank
     */
    @Override
    public LocalDate unmarshal(String value) {
        // TODO: implement
        // if value is null or blank → return null
        // otherwise → LocalDate.parse(value.trim(), FMT)
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.5");
    }

    /**
     * Convert Java LocalDate → XML string value.
     * Called during marshalling (Java → XML).
     *
     * @param value the LocalDate to format (may be null)
     * @return the formatted string, or null if value is null
     */
    @Override
    public String marshal(LocalDate value) {
        // TODO: implement
        // if value is null → return null
        // otherwise → value.format(FMT)
        throw new UnsupportedOperationException("TODO — Lab 2 Task 2.5");
    }
}
