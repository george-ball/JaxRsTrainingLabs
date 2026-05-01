package com.example.api.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JAXB wrapper for a list of products.
 *
 * Needed because JAXB cannot marshal a bare List<Product> as a root element.
 *
 * Marshals to:
 *   <products count="5">
 *     <product id="P001" active="true">...</product>
 *     <product id="P002" active="true">...</product>
 *   </products>
 */
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductList {

    @XmlAttribute
    private int count;

    @XmlElement(name = "product")
    private List<Product> products = new ArrayList<>();

    public ProductList() {}

    public ProductList(List<Product> products) {
        this.products = products;
        this.count    = products.size();
    }

    public List<Product> getProducts() { return products; }
    public int           getCount()    { return count; }
}
