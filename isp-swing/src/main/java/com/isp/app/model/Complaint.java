package com.isp.app.model;

public class Complaint {
    private int id;
    private String complaint;
    private String date; // yyyy-MM-dd
    private String action; // e.g. "Pending", "Solved"

    public Complaint() {}

    public Complaint(int id, String complaint, String date, String action) {
        this.id = id;
        this.complaint = complaint;
        this.date = date;
        this.action = action;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getComplaint() { return complaint; }
    public void setComplaint(String complaint) { this.complaint = complaint; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}

