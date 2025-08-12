package com.isp.app.dao;

import com.isp.app.model.Plan;
import com.isp.app.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDAO {

    public List<Plan> getAllPlans() {
        List<Plan> list = new ArrayList<>();
        String sql = "SELECT Id, PlanName, Speed, Cost, Duration FROM plan";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Plan(
                        rs.getInt("Id"),
                        rs.getString("PlanName"),
                        rs.getString("Speed"),
                        rs.getDouble("Cost"),
                        rs.getInt("Duration")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addPlan(Plan p) throws SQLException {
        String sql = "INSERT INTO plan (Id, PlanName, Speed, Cost, Duration) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            ps.setString(2, p.getPlanName());
            ps.setString(3, p.getSpeed());
            ps.setDouble(4, p.getCost());
            ps.setInt(5, p.getDuration());
            ps.executeUpdate();
        }
    }

    public void deleteByName(String planName) throws SQLException {
        String sql = "DELETE FROM plan WHERE PlanName = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, planName);
            ps.executeUpdate();
        }
    }
}
