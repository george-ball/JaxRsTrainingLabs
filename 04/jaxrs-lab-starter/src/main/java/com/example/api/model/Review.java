package com.example.api.model;

import java.time.LocalDateTime;

/**
 * Review domain model — used in Lab 3 (sub-resource locator).
 */
public class Review {

    private String        id;
    private String        productId;
    private int           rating;       // 1–5
    private String        comment;
    private String        reviewerName;
    private LocalDateTime createdAt;

    public Review() {}

    public Review(String id, String productId, int rating,
                  String comment, String reviewerName, LocalDateTime createdAt) {
        this.id           = id;
        this.productId    = productId;
        this.rating       = rating;
        this.comment      = comment;
        this.reviewerName = reviewerName;
        this.createdAt    = createdAt;
    }

    public String        getId()                       { return id; }
    public void          setId(String id)              { this.id = id; }

    public String        getProductId()                { return productId; }
    public void          setProductId(String pid)      { this.productId = pid; }

    public int           getRating()                   { return rating; }
    public void          setRating(int rating)         { this.rating = rating; }

    public String        getComment()                  { return comment; }
    public void          setComment(String comment)    { this.comment = comment; }

    public String        getReviewerName()             { return reviewerName; }
    public void          setReviewerName(String name)  { this.reviewerName = name; }

    public LocalDateTime getCreatedAt()                { return createdAt; }
    public void          setCreatedAt(LocalDateTime t) { this.createdAt = t; }
}
