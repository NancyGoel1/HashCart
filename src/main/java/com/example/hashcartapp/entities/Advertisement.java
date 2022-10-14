package com.example.Demo_func1.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long advertisement_id;

    @Column(length=500)
    private String description;

    private String type;

    private String category;

    private String location;

    private long price_range_lower;

    private String price_range_higher;

    private Date creationDate;

    private Date closedDate;

    @ManyToOne
    private User user;

    public long getId() {
        return advertisement_id;
    }

    public void setId(long id) {
        this.advertisement_id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getPrice_range_lower() {
        return price_range_lower;
    }

    public void setPrice_range_lower(long price_range_lower) {
        this.price_range_lower = price_range_lower;
    }

    public String getPrice_range_higher() {
        return price_range_higher;
    }

    public void setPrice_range_higher(String price_range_higher) {
        this.price_range_higher = price_range_higher;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

