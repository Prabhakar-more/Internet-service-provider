package com.isp.app.dao;

import com.isp.app.model.Customer;
import com.isp.app.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT ID, Name, Contact, Sex, Purpose, Address, Plan FROM customer";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Contact"),
                        rs.getString("Sex"),
                        rs.getString("Purpose"),
                        rs.getString("Address"),
                        rs.getString("Plan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer(ID, Name, Contact, Sex, Purpose, Address, Plan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getContact());
            ps.setString(4, customer.getSex());
            ps.setString(5, customer.getPurpose());
            ps.setString(6, customer.getAddress());
            ps.setString(7, customer.getPlan());
            ps.executeUpdate();
        }
    }

    public void updateCustomerPlanByContact(String contact, String plan) throws SQLException {
        String sql = "UPDATE customer SET Plan = ? WHERE Contact = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, plan);
            ps.setString(2, contact);
            ps.executeUpdate();
        }
    }

    public List<Customer> searchByContact(String contact) {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT ID, Name, Contact, Sex, Purpose, Address, Plan FROM customer WHERE Contact = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, contact);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Customer(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Contact"),
                            rs.getString("Sex"),
                            rs.getString("Purpose"),
                            rs.getString("Address"),
                            rs.getString("Plan")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

