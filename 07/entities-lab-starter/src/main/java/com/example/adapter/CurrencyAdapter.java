package com.example.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * XmlAdapter for BigDecimal ↔ 2-decimal-place string.
 * Complete – do not modify.
 */
public class CurrencyAdapter extends XmlAdapter<String, BigDecimal> {
    @Override
    public BigDecimal unmarshal(String v) {
        return (v == null || v.isBlank()) ? null
            : new BigDecimal(v.trim()).setScale(2, RoundingMode.HALF_UP);
    }
    @Override
    public String marshal(BigDecimal v) {
        return v == null ? null : v.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
