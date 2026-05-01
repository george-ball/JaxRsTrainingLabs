package com.example.api.model;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * Review domain model annotated for JAXB XML serialisation.
 * Used in Lab 3 (sub-resource locator).
 *
 * Marshals to:
 *   <review id="R001">
 *     <productId>P001</productId>
 *     <rating>5</rating>
 *     <comment>Excellent book</comment>
 *     <reviewerName>Alice</reviewerName>
 *     <createdAt>2024-06-15T10:30:00</createdAt>
 *   </review>
 */
@XmlRootElement(name = "review")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"productId", "rating", "comment", "reviewerName", "createdAt"})
public class Review {

    @XmlAttribute
    private String id;

    @XmlElement(required = true)
    private String productId;

    @XmlElement(required = true)
    private int rating;

    @XmlElement
    private String comment;

    @XmlElement(required = true)
    private String reviewerName;

    @XmlElement
    private String createdAt;

    public Review() {}

    public Review(String id, String productId, int rating,
                  String comment, String reviewerName, LocalDateTime createdAt) {
        this.id           = id;
        this.productId    = productId;
        this.rating       = rating;
        this.comment      = comment;
        this.reviewerName = reviewerName;
        this.createdAt    = createdAt != null ? createdAt.toString() : null;
    }

    public String getId()                      { return id; }
    public void   setId(String id)             { this.id = id; }
    public String getProductId()               { return productId; }
    public void   setProductId(String pid)     { this.productId = pid; }
    public int    getRating()                  { return rating; }
    public void   setRating(int r)             { this.rating = r; }
    public String getComment()                 { return comment; }
    public void   setComment(String c)         { this.comment = c; }
    public String getReviewerName()            { return reviewerName; }
    public void   setReviewerName(String n)    { this.reviewerName = n; }
    public String getCreatedAt()               { return createdAt; }
    public void   setCreatedAt(String t)       { this.createdAt = t; }
}
