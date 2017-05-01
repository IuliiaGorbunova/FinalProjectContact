package com.example.iuliia.contact;

public class Contact {
    private int id;
    private String contactName;
    private long phoneNumber;

    public Contact() {

    }

    public Contact(int id, String contactName, long phoneNumber) {
        this.id = id;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public Contact(String contactName, long phoneNumber) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setPnoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }
}
