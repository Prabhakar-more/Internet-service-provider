package com.isp.app.view;

import com.isp.app.dao.PlanDAO;
import com.isp.app.model.Plan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CreatePlanFrame extends JFrame {
    private final PlanDAO dao = new PlanDAO();
    private final JTable table = new JTable();
    private final JTextField tfId = new JTextField(8);
    private final JTextField tfName = new JTextField(12);
    private final JTextField tfSpeed = new JTextField(10);
    private final JTextField tfCost = new JTextField(8);
    private final JTextField tfDuration = new JTextField(6);

    public CreatePlanFrame() {
        setTitle("Create Plan");
        setSize(800, 420);
        setLocationRelativeTo(null);
        init();
        loadPlans();
    }

    private void init() {
        JPanel form = new JPanel(new GridLayout(6, 2, 6, 6));
        form.setBorder(BorderFactory.createTitledBorder("Plan Details"));
        form.add(new JLabel("ID"));
        form.add(tfId);
        form.add(new JLabel("Plan Name"));
        form.add(tfName);
        form.add(new JLabel("Speed"));
        form.add(tfSpeed);
        form.add(new JLabel("Cost"));
        form.add(tfCost);
        form.add(new JLabel("Duration"));
        form.add(tfDuration);

        JButton btnAdd = new JButton("ADD");
        JButton btnDelete = new JButton("DELETE");
        JButton btnBack = new JButton("BACK");
        form.add(btnAdd);
        form.add(btnDelete);

        btnAdd.addActionListener(e -> {
            try {
                Plan p = new Plan(Integer.parseInt(tfId.getText().trim()),
                        tfName.getText().trim(),
                        tfSpeed.getText().trim(),
                        Double.parseDouble(tfCost.getText().trim()),
                        Integer.parseInt(tfDuration.getText().trim()));
                dao.addPlan(p);
                JOptionPane.showMessageDialog(this, "Plan added");
                loadPlans();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Number format error: " + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            }
        });

        btnDelete.addActionListener(e -> {
            String name = tfName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter PlanName to delete");
                return;
            }
            try {
                dao.deleteByName(name);
                JOptionPane.showMessageDialog(this, "Plan deleted if existed");
                loadPlans();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> dispose());

        JScrollPane sp = new JScrollPane(table);
        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(form, BorderLayout.WEST);
        getContentPane().add(sp, BorderLayout.CENTER);
    }

    private void loadPlans() {
        List<Plan> list = dao.getAllPlans();
        DefaultTableModel m = new DefaultTableModel(new Object[]{"Id", "PlanName", "Speed", "Cost", "Duration"}, 0);
        for (Plan p : list) {
            m.addRow(new Object[]{p.getId(), p.getPlanName(), p.getSpeed(), p.getCost(), p.getDuration()});
        }
        table.setModel(m);
    }
}

