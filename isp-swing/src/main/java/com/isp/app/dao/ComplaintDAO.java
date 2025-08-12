package com.isp.app.dao;

import com.isp.app.model.Complaint;
import com.isp.app.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    public List<Complaint> getAllComplaints() {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT ID, Complaint, Date, Action FROM complain";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Complaint(
                        rs.getInt("ID"),
                        rs.getString("Complaint"),
                        rs.getString("Date"),
                        rs.getString("Action")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addComplaint(Complaint complaint) throws SQLException {
        String sql = "INSERT INTO complain (ID, Complaint, Date, Action) VALUES (?, ?, ?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, complaint.getId());
            ps.setString(2, complaint.getComplaint());
            ps.setString(3, complaint.getDate());
            ps.setString(4, complaint.getAction());
            ps.executeUpdate();
        }
    }

    public void markSolved(int id) throws SQLException {
        String sql = "UPDATE complain SET Action = 'Solved' WHERE ID = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
