package com.example.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * XmlAdapter for LocalDate ↔ "yyyy-MM-dd" string.
 * Complete – do not modify.
 * Used in Lab 3 via @XmlJavaTypeAdapter(LocalDateAdapter.class).
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate unmarshal(String value) {
        return (value == null || value.isBlank()) ? null : LocalDate.parse(value.trim(), FMT);
    }

    @Override
    public String marshal(LocalDate value) {
        return value == null ? null : value.format(FMT);
    }
}
