package com.userService.userService.entities;

public class Contact {
    private int id;
    private String email;
    private String contactName;

    private int userId;

    public Contact() {
    }

    public Contact(int id, String email, String contactName, int userId) {
        this.id = id;
        this.email = email;
        this.contactName = contactName;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
