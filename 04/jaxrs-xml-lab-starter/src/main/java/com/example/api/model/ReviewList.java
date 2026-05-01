package com.example.api.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JAXB wrapper for a list of reviews.
 *
 * Marshals to:
 *   <reviews count="2" productId="P001">
 *     <review id="R001">...</review>
 *     <review id="R002">...</review>
 *   </reviews>
 */
@XmlRootElement(name = "reviews")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReviewList {

    @XmlAttribute
    private int count;

    @XmlAttribute
    private String productId;

    @XmlElement(name = "review")
    private List<Review> reviews = new ArrayList<>();

    public ReviewList() {}

    public ReviewList(String productId, List<Review> reviews) {
        this.productId = productId;
        this.reviews   = reviews;
        this.count     = reviews.size();
    }

    public List<Review> getReviews()   { return reviews; }
    public int          getCount()     { return count; }
    public String       getProductId() { return productId; }
}
