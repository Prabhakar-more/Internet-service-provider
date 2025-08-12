package com.isp.app.view;

import com.isp.app.dao.CustomerDAO;
import com.isp.app.dao.PlanDAO;
import com.isp.app.model.Customer;
import com.isp.app.model.Plan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CustomerFrame extends JFrame {
    private final CustomerDAO dao = new CustomerDAO();
    private final PlanDAO planDAO = new PlanDAO();
    private final JTable table = new JTable();
    private final JTable planTable = new JTable();
    private final JTextField tfId = new JTextField(8);
    private final JTextField tfName = new JTextField(12);
    private final JTextField tfContact = new JTextField(12);
    private final JComboBox<String> cbSex = new JComboBox<>(new String[]{"MALE", "FEMALE"});
    private final JComboBox<String> cbPurpose = new JComboBox<>(new String[]{"BUSINESS", "INDIVIDUAL"});
    private final JTextField tfAddress = new JTextField(12);
    private final JTextField tfPlan = new JTextField(12);
    private final JTextField tfSearchContact = new JTextField(10);

    public CustomerFrame() {
        setTitle("Customer");
        setSize(1000, 480);
        setLocationRelativeTo(null);
        init();
        loadCustomers();
        loadPlans();
    }

    private void init() {
        JPanel left = new JPanel(new GridLayout(9, 2, 6, 6));
        left.setBorder(BorderFactory.createTitledBorder("Customer Form"));
        left.add(new JLabel("ID"));
        left.add(tfId);
        left.add(new JLabel("Name"));
        left.add(tfName);
        left.add(new JLabel("Contact"));
        left.add(tfContact);
        left.add(new JLabel("Sex"));
        left.add(cbSex);
        left.add(new JLabel("Purpose"));
        left.add(cbPurpose);
        left.add(new JLabel("Address"));
        left.add(tfAddress);
        left.add(new JLabel("Plan"));
        left.add(tfPlan);

        JButton btnAdd = new JButton("ADD");
        JButton btnUpdatePlan = new JButton("UPDATE PLAN");
        JButton btnBack = new JButton("BACK");
        left.add(btnAdd);
        left.add(btnUpdatePlan);
        left.add(btnBack);

        btnAdd.addActionListener(e -> {
            try {
                Customer c = new Customer(Integer.parseInt(tfId.getText().trim()),
                        tfName.getText().trim(),
                        tfContact.getText().trim(),
                        (String) cbSex.getSelectedItem(),
                        (String) cbPurpose.getSelectedItem(),
                        tfAddress.getText().trim(),
                        tfPlan.getText().trim());
                dao.addCustomer(c);
                JOptionPane.showMessageDialog(this, "Customer added");
                loadCustomers();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Number format: " + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            }
        });

        btnUpdatePlan.addActionListener(e -> {
            try {
                dao.updateCustomerPlanByContact(tfContact.getText().trim(), tfPlan.getText().trim());
                JOptionPane.showMessageDialog(this, "Plan updated");
                loadCustomers();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> dispose());

        JPanel right = new JPanel(new BorderLayout(6, 6));
        right.setBorder(BorderFactory.createTitledBorder("Customer List"));

        JScrollPane sp = new JScrollPane(table);
        right.add(sp, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnSearch = new JButton("SEARCH by Contact");
        searchPanel.add(tfSearchContact);
        searchPanel.add(btnSearch);
        right.add(searchPanel, BorderLayout.NORTH);

        btnSearch.addActionListener(e -> {
            String contact = tfSearchContact.getText().trim();
            if (contact.isEmpty()) {
                loadCustomers();
                return;
            }
            List<Customer> results = dao.searchByContact(contact);
            DefaultTableModel m = new DefaultTableModel(new Object[]{"ID", "Name", "Contact", "Sex", "Purpose", "Address", "Plan"}, 0);
            for (Customer c : results) {
                m.addRow(new Object[]{c.getId(), c.getName(), c.getContact(), c.getSex(), c.getPurpose(), c.getAddress(), c.getPlan()});
            }
            table.setModel(m);
        });

        planTable.setPreferredScrollableViewportSize(new Dimension(300, 120));
        JScrollPane planScroll = new JScrollPane(planTable);
        planScroll.setBorder(BorderFactory.createTitledBorder("Plans"));
        loadPlans();
        JSplitPane centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, right, planScroll);
        centerSplit.setResizeWeight(0.7);

        getContentPane().setLayout(new BorderLayout(8, 8));
        getContentPane().add(left, BorderLayout.WEST);
        getContentPane().add(centerSplit, BorderLayout.CENTER);

        // table click fills form
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int r = table.getSelectedRow();
                if (r >= 0) {
                    tfId.setText(table.getValueAt(r, 0).toString());
                    tfName.setText(table.getValueAt(r, 1).toString());
                    tfContact.setText(table.getValueAt(r, 2).toString());
                    tfAddress.setText(table.getValueAt(r, 5).toString());
                    tfPlan.setText(table.getValueAt(r, 6).toString());
                }
            }
        });

        planTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int r = planTable.getSelectedRow();
                if (r >= 0) {
                    tfPlan.setText(planTable.getValueAt(r, 1).toString());
                }
            }
        });
    }

    private void loadCustomers() {
        List<Customer> list = dao.getAllCustomers();
        DefaultTableModel m = new DefaultTableModel(new Object[]{"ID", "Name", "Contact", "Sex", "Purpose", "Address", "Plan"}, 0);
        for (Customer c : list) {
            m.addRow(new Object[]{c.getId(), c.getName(), c.getContact(), c.getSex(), c.getPurpose(), c.getAddress(), c.getPlan()});
        }
        table.setModel(m);
    }

    private void loadPlans() {
        List<Plan> list = planDAO.getAllPlans();
        DefaultTableModel m = new DefaultTableModel(new Object[]{"Id", "PlanName", "Speed", "Cost", "Duration"}, 0);
        for (Plan p : list) {
            m.addRow(new Object[]{p.getId(), p.getPlanName(), p.getSpeed(), p.getCost(), p.getDuration()});
        }
        planTable.setModel(m);
    }
}
