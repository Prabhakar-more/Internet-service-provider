package com.isp.app.model;

public class Plan {
    private int id;
    private String planName;
    private String speed;
    private double cost;
    private int duration; // in months or days depending on DB

    public Plan() {}

    public Plan(int id, String planName, String speed, double cost, int duration) {
        this.id = id;
        this.planName = planName;
        this.speed = speed;
        this.cost = cost;
        this.duration = duration;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }
    public String getSpeed() { return speed; }
    public void setSpeed(String speed) { this.speed = speed; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
}
