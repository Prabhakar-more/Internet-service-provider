package com.isp.app.view;

import com.isp.app.dao.ComplaintDAO;
import com.isp.app.model.Complaint;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ComplaintFrame extends JFrame {
    private final ComplaintDAO dao = new ComplaintDAO();
    private final JTable table = new JTable();
    private final JTextField tfId = new JTextField(8);
    private final JTextArea taComplaint = new JTextArea(4, 20);
    private final JTextField tfDate = new JTextField(10);

    public ComplaintFrame() {
        setTitle("Complaints");
        setSize(900, 420);
        setLocationRelativeTo(null);
        init();
        loadComplaints();
    }

    private void init() {
        JPanel left = new JPanel(new GridLayout(6, 1, 6, 6));
        left.setBorder(BorderFactory.createTitledBorder("Complaint"));
        JPanel idp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idp.add(new JLabel("ID:"));
        idp.add(tfId);
        left.add(idp);

        JPanel cp = new JPanel(new BorderLayout());
        cp.add(new JLabel("Complaint:"), BorderLayout.NORTH);
        cp.add(new JScrollPane(taComplaint), BorderLayout.CENTER);
        left.add(cp);

        JPanel dp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dp.add(new JLabel("Date:"));
        dp.add(tfDate);
        left.add(dp);

        JButton btnAdd = new JButton("ADD COMPLAINT");
        JButton btnSolve = new JButton("MARK SOLVED");
        JButton btnBack = new JButton("BACK");
        left.add(btnAdd);
        left.add(btnSolve);
        left.add(btnBack);

        tfDate.setEditable(false);
        tfDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        btnAdd.addActionListener(e -> {
            try {
                Complaint c = new Complaint(Integer.parseInt(tfId.getText().trim()),
                        taComplaint.getText().trim(),
                        tfDate.getText().trim(),
                        "Pending");
                dao.addComplaint(c);
                JOptionPane.showMessageDialog(this, "Complaint added");
                loadComplaints();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID must be a number");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "DB error: " + ex.getMessage());
            }
        });

        btnSolve.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel < 0) {
                JOptionPane.showMessageDialog(this, "Select a complaint in table");
                return;
            }
            int id = Integer.parseInt(table.getValueAt(sel, 0).toString());
            try {
                dao.markSolved(id);
                JOptionPane.showMessageDialog(this, "Marked solved");
                loadComplaints();
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

    private void loadComplaints() {
        List<Complaint> list = dao.getAllComplaints();
        DefaultTableModel m = new DefaultTableModel(new Object[]{"ID", "Complaint", "Date", "Action"}, 0);
        for (Complaint c : list) {
            m.addRow(new Object[]{c.getId(), c.getComplaint(), c.getDate(), c.getAction()});
        }
        table.setModel(m);
    }
}
