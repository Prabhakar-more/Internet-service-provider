package com.isp.app.model;

public class Customer {
    private int id;
    private String name;
    private String contact;
    private String sex;
    private String purpose;
    private String address;
    private String plan;

    public Customer() {}

    public Customer(int id, String name, String contact, String sex, String purpose, String address, String plan) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.sex = sex;
        this.purpose = purpose;
        this.address = address;
        this.plan = plan;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
}
