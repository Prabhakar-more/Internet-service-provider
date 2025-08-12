package com.isp.app.view;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    public HomeFrame() {
        setTitle("Home");
        setSize(400, 250);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        JLabel lbl = new JLabel("Welcome to ISP Management");
        lbl.setFont(lbl.getFont().deriveFont(18f));
        JButton btnLogin = new JButton("Open Admin Panel");
        btnLogin.addActionListener(e -> {
            dispose();
            new AdminFrame().setVisible(true);
        });
        getContentPane().setLayout(new BorderLayout(8, 8));
        JPanel p = new JPanel();
        p.add(lbl);
        p.add(btnLogin);
        getContentPane().add(p, BorderLayout.CENTER);
    }
}
