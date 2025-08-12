package com.isp.app.model;

public class Employee {
    private int id;
    private String name;
    private String post;
    private String contact;
    private String salary;

    public Employee() {}

    public Employee(int id, String name, String post, String contact, String salary) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.contact = contact;
        this.salary = salary;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
}
