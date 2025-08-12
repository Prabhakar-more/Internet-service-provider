package com.isp.app;
import com.isp.app.*;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;
import com.isp.app.view.AdminFrame;
//this is Main class
public class Main {
    public static void main(String[] args) {
        // Setup modern look & feel
        FlatLightLaf.install();

        SwingUtilities.invokeLater(() -> {
            AdminFrame frame = new AdminFrame();
            frame.setVisible(true);
        });
    }
}


