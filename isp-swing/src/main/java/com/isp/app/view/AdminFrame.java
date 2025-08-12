package com.isp.app.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminFrame extends JFrame {
    private final JLabel dateLabel = new JLabel();
    private final JLabel timeLabel = new JLabel();

    public AdminFrame() {
        setTitle("Admin Panel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel title = new JLabel("ADMIN PANEL");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        top.add(title);

        JPanel info = new JPanel(new GridLayout(2, 2, 6, 6));
        info.add(new JLabel("Date:"));
        info.add(dateLabel);
        info.add(new JLabel("Time:"));
        info.add(timeLabel);
        top.add(info);

        showDate();
        showTime();

        JPanel center = new JPanel(new GridLayout(3, 2, 20, 20));
        JButton btnPlan = new JButton("CREATE PLAN");
        JButton btnCustomer = new JButton("CUSTOMER");
        JButton btnComplaint = new JButton("COMPLAINT");
        JButton btnEmployee = new JButton("EMPLOYEE");
        JButton btnLogout = new JButton("LOG OUT");

        btnPlan.addActionListener(e -> {
            CreatePlanFrame f = new CreatePlanFrame();
            f.setVisible(true);
        });
        btnCustomer.addActionListener(e -> new CustomerFrame().setVisible(true));
        btnComplaint.addActionListener(e -> new ComplaintFrame().setVisible(true));
        btnEmployee.addActionListener(e -> new EmployeeFrame().setVisible(true));
        btnLogout.addActionListener(e -> {
            dispose();
            new HomeFrame().setVisible(true);
        });

        center.add(btnPlan);
        center.add(btnCustomer);
        center.add(btnComplaint);
        center.add(btnEmployee);
        center.add(btnLogout);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
    }

    private void showDate() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        dateLabel.setText(sf.format(d));
    }

    private void showTime() {
        Timer t = new Timer(1000, e -> {
            Date d = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss a");
            timeLabel.setText(sf.format(d));
        });
        t.start();
    }
}
