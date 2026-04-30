package com.example.api.model;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/** Store domain model.  Complete – do not modify. */
@XmlRootElement(name = "store")
@XmlAccessorType(XmlAccessType.FIELD)
public class Store {
    private String id;
    private String name;
    private String city;
    private List<InventoryItem> inventory = new ArrayList<>();

    public Store() {}
    public Store(String id, String name, String city) {
        this.id = id; this.name = name; this.city = city;
    }
    public String getId()                           { return id; }
    public void   setId(String id)                  { this.id = id; }
    public String getName()                         { return name; }
    public void   setName(String name)              { this.name = name; }
    public String getCity()                         { return city; }
    public void   setCity(String city)              { this.city = city; }
    public List<InventoryItem> getInventory()       { return inventory; }
    public void setInventory(List<InventoryItem> inv){ this.inventory = inv; }
}
