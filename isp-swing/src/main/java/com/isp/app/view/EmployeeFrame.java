package com.isp.app.view;

import com.isp.app.dao.EmployeeDAO;
import com.isp.app.model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFrame extends JFrame {
    private final EmployeeDAO dao = new EmployeeDAO();
    private final JTable table = new JTable();
    private final JTextField tfId = new JTextField(8);
    private final JTextField tfName = new JTextField(12);
    private final JTextField tfPost = new JTextField(12);
    private final JTextField tfContact = new JTextField(12);
    private final JTextField tfSalary = new JTextField(8);

    public EmployeeFrame() {
        setTitle("Employee");
        setSize(900, 420);
        setLocationRelativeTo(null);
        init();
        loadEmployees();
    }

    private void init() {
        JPanel left = new JPanel(new GridLayout(6, 2, 6, 6));
        left.setBorder(BorderFactory.createTitledBorder("Employee Form"));
        left.add(new JLabel("ID")); left.add(tfId);
        left.add(new JLabel("Name")); left.add(tfName);
        left.add(new JLabel("Post")); left.add(tfPost);
        left.add(new JLabel("Contact")); left.add(tfContact);
        left.add(new JLabel("Salary")); left.add(tfSalary);

        JButton btnAdd = new JButton("ADD");
        JButton btnBack = new JButton("BACK");
        left.add(btnAdd);
        left.add(btnBack);

        btnAdd.addActionListener(e -> {
            try {
                Employee emp = new Employee(Integer.parseInt(tfId.getText().trim()), tfName.getText().trim(),
                        tfPost.getText().trim(), tfContact.getText().trim(), tfSalary.getText().trim());
                dao.addEmployee(emp);
                JOptionPane.showMessageDialog(this, "Employee added");
                loadEmployees();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID must be integer");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> dispose());

        JScrollPane sp = new JScrollPane(table);
        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(left, BorderLayout.WEST);
        getContentPane().add(sp, BorderLayout.CENTER);
    }

    private void loadEmployees() {
        List<Employee> list = dao.getAllEmployees();
        DefaultTableModel m = new DefaultTableModel(new Object[]{"ID", "Name", "Post", "Contact", "Salary"}, 0);
        for (Employee e : list) {
            m.addRow(new Object[]{e.getId(), e.getName(), e.getPost(), e.getContact(), e.getSalary()});
        }
        table.setModel(m);
    }
}
