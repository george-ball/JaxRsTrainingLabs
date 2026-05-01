package com.example.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * XmlAdapter that marshals BigDecimal to a 2-decimal-place string.
 *
 * PROVIDED — do not modify.
 *
 * Examples:
 *   marshal(new BigDecimal("9.9"))    →  "9.90"
 *   marshal(new BigDecimal("100"))    →  "100.00"
 *   marshal(null)                     →  null
 *   unmarshal("29.99")               →  BigDecimal("29.99")
 *   unmarshal(null)                   →  null
 *
 * Usage on a field:
 *   @XmlJavaTypeAdapter(CurrencyAdapter.class)
 *   private BigDecimal unitPrice;
 */
public class CurrencyAdapter extends XmlAdapter<String, BigDecimal> {

    private static final int          SCALE        = 2;
    private static final RoundingMode ROUNDING     = RoundingMode.HALF_UP;

    @Override
    public BigDecimal unmarshal(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return new BigDecimal(value.trim()).setScale(SCALE, ROUNDING);
    }

    @Override
    public String marshal(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.setScale(SCALE, ROUNDING).toPlainString();
    }
}
