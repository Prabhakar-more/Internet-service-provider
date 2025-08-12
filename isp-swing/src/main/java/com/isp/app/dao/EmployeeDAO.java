package com.isp.app.dao;

import com.isp.app.model.Employee;
import com.isp.app.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT ID, Name, Post, Contact, Salary FROM employee";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Post"),
                        rs.getString("Contact"),
                        rs.getString("Salary")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employee (ID, Name, Post, Contact, Salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getPost());
            ps.setString(4, emp.getContact());
            ps.setString(5, emp.getSalary());
            ps.executeUpdate();
        }
    }
}
