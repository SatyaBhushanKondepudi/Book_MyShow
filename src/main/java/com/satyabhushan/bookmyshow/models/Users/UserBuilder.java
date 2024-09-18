package com.satyabhushan.bookmyshow.models.Users;

import com.satyabhushan.bookmyshow.models.ENUMS.UserType;

public class UserBuilder {
    private String name;
    private String email;
    private String userName;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private UserType role;


    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public UserBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public UserBuilder setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public UserBuilder setRole(UserType role) {
        this.role = role;
        return this;
    }

    public User build() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setAddress(this.address);
        user.setCity(this.city);
        user.setState(this.state);
        user.setCountry(this.country);
        user.setZip(this.zip);
        user.setRole(this.role);
        return user;
    }
}
