package com.owly.delivery.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Address implements Serializable {
    private static final long serialVersionUID = 8123440534986496243L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int addressId;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String addressType;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    private String phone;

    public int getAddressId() {
        return addressId;
    }
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    @Override
//    public String toString() {
//        return "Address{" +
//                "addressId=" + addressId +
//                ", user=" + user +
//                ", addressType='" + addressType + '\'' +
//                ", street='" + street + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", zipcode=" + zipcode +
//                ", phone='" + phone + '\'' +
//                '}';
//    }
}
