package com.example.model;

/**
 * Delivery address — nested inside an Order.
 *
 * LAB 3 — also annotate this class with JAXB annotations so it maps to:
 *   <deliveryAddress>
 *     <street>...</street>
 *     <city>...</city>
 *     <postCode>...</postCode>
 *     <country>GB</country>
 *   </deliveryAddress>
 *
 * Required: @XmlAccessorType(XmlAccessType.FIELD)
 * Optional: @XmlType(propOrder = { "street", "city", "postCode", "country" })
 */
public class Address {
    private String street; private String city; private String postCode; private String country;

    public Address() {}
    public Address(String street, String city, String postCode, String country) {
        this.street=street; this.city=city; this.postCode=postCode; this.country=country;
    }
    public String getStreet(){return street;} public void setStreet(String s){this.street=s;}
    public String getCity(){return city;} public void setCity(String c){this.city=c;}
    public String getPostCode(){return postCode;} public void setPostCode(String p){this.postCode=p;}
    public String getCountry(){return country;} public void setCountry(String c){this.country=c;}
}
