package com.heelstrike.meal.domain.dto;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class HumanDTO {
    private Long id;
    private UUID uuid;
    private String username;
    private String firstname;
    private List<String> middlename;
    private String surname;
    private Date dob;
    private double[] homeCoords;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<String> getMiddlename() {
        return middlename;
    }

    public void setMiddlename(List<String> middlename) {
        this.middlename = middlename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double[] getHomeCoords() {
        return homeCoords;
    }

    public void setHomeCoords(double[] homeCoords) {
        this.homeCoords = homeCoords;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
