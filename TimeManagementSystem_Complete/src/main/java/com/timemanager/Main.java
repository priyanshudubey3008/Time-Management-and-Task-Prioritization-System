package com.timemanager;

import com.timemanager.gui.LoginFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseInitializer.initialize();
                new LoginFrame().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Database setup failed.\n"
                                + "Please check that MySQL is running, "
                                + "your credentials are correct, and "
                                + "your MySQL user has permission to create "
                                + "the database and tables.\n\n"
                                + "Details: " + e.getMessage(),
                        "Database Connection Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
