package com.userService.userService.entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String phone;

    List<Contact> contacts = new ArrayList<>();

    public User(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public User(int id, String name, String phone, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
