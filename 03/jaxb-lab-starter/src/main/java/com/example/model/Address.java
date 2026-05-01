package com.example.model;

/**
 * Delivery address — nested inside an Order.
 *
 * LAB 2 — Task 2.3
 * Add JAXB annotations to this class so it marshals to:
 *
 *   <deliveryAddress>
 *     <street>10 Downing Street</street>
 *     <city>London</city>
 *     <postCode>SW1A 2AA</postCode>
 *     <country>GB</country>
 *   </deliveryAddress>
 *
 * Annotations needed on the class:
 *   @XmlAccessorType(XmlAccessType.FIELD)
 *   @XmlType(propOrder = { "street", "city", "postCode", "country" })
 *
 * Note: Address does NOT need @XmlRootElement — it is always embedded
 * inside an Order and is referenced by the Order's @XmlElement.
 */
public class Address {

    private String street;
    private String city;
    private String postCode;
    private String country;

    // ── Constructors ──────────────────────────────────────────────────────

    public Address() {}

    public Address(String street, String city, String postCode, String country) {
        this.street   = street;
        this.city     = city;
        this.postCode = postCode;
        this.country  = country;
    }

    // ── Getters and Setters ───────────────────────────────────────────────

    public String getStreet()               { return street; }
    public void   setStreet(String street)  { this.street = street; }

    public String getCity()                 { return city; }
    public void   setCity(String city)      { this.city = city; }

    public String getPostCode()                  { return postCode; }
    public void   setPostCode(String postCode)   { this.postCode = postCode; }

    public String getCountry()                   { return country; }
    public void   setCountry(String country)     { this.country = country; }
}
