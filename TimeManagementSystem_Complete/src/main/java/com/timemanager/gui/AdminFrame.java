package com.timemanager.gui;
import com.timemanager.dao.PerformanceDAO;
import com.timemanager.dao.CategoryDAO;
import com.timemanager.dao.RuleDAO;
import com.timemanager.util.Session;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {

    public AdminFrame() {

        setTitle("Admin Dashboard");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel(
                "👑 ADMIN DASHBOARD",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));

        // =========================
        // MAIN PANEL
        // =========================

        JPanel p = new JPanel(new BorderLayout(15, 15));

        p.add(title, BorderLayout.NORTH);

        // =========================
        // BUTTONS
        // =========================

        JButton rules =
                new JButton("Configure Prioritization Rules");

        JButton categories =
                new JButton("Manage Task Categories");

        JButton performance =
                new JButton("Monitor System Performance");

        JButton logout =
                new JButton("Logout");

        // 5 buttons
        JPanel buttons =
                new JPanel(new GridLayout(2, 3, 15, 15));

        buttons.add(rules);
        buttons.add(categories);
        
        buttons.add(performance);
        buttons.add(logout);

        p.add(buttons, BorderLayout.CENTER);

        // =========================
        // BUTTON ACTIONS
        // =========================

        rules.addActionListener(e -> configureRules());

        categories.addActionListener(e -> manageCategories());


      performance.addActionListener(e -> showPerformanceGraph());

        logout.addActionListener(e -> {
            Session.logout();
            dispose();
            new LoginFrame().setVisible(true);
        });

        setContentPane(p);
    }

    // =====================================================
    // CONFIGURE PRIORITIZATION RULES
    // =====================================================

    private void configureRules() {

        JTextField name =
                new JTextField("Default Rule");

        JTextField importance =
                new JTextField("60");

        JTextField deadline =
                new JTextField("40");

        JPanel panel = new JPanel(
                new GridLayout(3, 2, 10, 10)
        );

        panel.add(new JLabel("Rule Name"));
        panel.add(name);

        panel.add(new JLabel("Importance Weight"));
        panel.add(importance);

        panel.add(new JLabel("Deadline Weight"));
        panel.add(deadline);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Configure Prioritization Rules",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            try {

                int importanceWeight =
                        Integer.parseInt(importance.getText().trim());

                int deadlineWeight =
                        Integer.parseInt(deadline.getText().trim());

                if (importanceWeight < 0 ||
                        deadlineWeight < 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Weights cannot be negative."
                    );

                    return;
                }

                if (importanceWeight + deadlineWeight != 100) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Importance + Deadline weight must equal 100."
                    );

                    return;
                }

                boolean saved =
                        new RuleDAO().save(
                                name.getText().trim(),
                                importanceWeight,
                                deadlineWeight
                        );

                if (saved) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Prioritization rule saved successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Rule could not be saved."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid numeric weights."
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        }
    }

    // =====================================================
    // MANAGE TASK CATEGORIES
    // =====================================================

    private void manageCategories() {

        JTextField categoryName =
                new JTextField();

        JTextField description =
                new JTextField();

        JPanel panel = new JPanel(
                new GridLayout(2, 2, 10, 10)
        );

        panel.add(new JLabel("Category Name"));
        panel.add(categoryName);

        panel.add(new JLabel("Description"));
        panel.add(description);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Manage Task Categories",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            String name =
                    categoryName.getText().trim();

            String desc =
                    description.getText().trim();

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Category name is required."
                );

                return;
            }

            try {

                boolean added =
                        new CategoryDAO().add(
                                name,
                                desc
                        );

                if (added) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Category created successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Category could not be created."
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        }
    }

    // =====================================================
    // MONITOR SYSTEM PERFORMANCE
    // =====================================================
  private void showPerformanceGraph() {

        try {

            PerformanceDAO dao =
                    new PerformanceDAO();

            int completed =
                    dao.getCompletedTasks();

            int pending =
                    dao.getPendingTasks();

            JFrame graphFrame =
                    new JFrame("System Performance Graph");

            graphFrame.setSize(550, 400);

            graphFrame.setLocationRelativeTo(this);

            graphFrame.setDefaultCloseOperation(
                    JFrame.DISPOSE_ON_CLOSE
            );

            graphFrame.add(
                    new PerformancePanel(
                            completed,
                            pending
                    )
            );

            graphFrame.setVisible(true);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load performance graph.\n"
                    + "Error: " + ex.getMessage(),
                    "System Performance",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}