package com.timemanager.gui;

import javax.swing.*;
import java.awt.*;

public class PerformancePanel extends JPanel {

    private final int completed;
    private final int pending;

    public PerformancePanel(int completed, int pending) {
        this.completed = completed;
        this.pending = pending;

        setPreferredSize(new Dimension(500, 350));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(new Font("Arial", Font.BOLD, 18));

        g2.drawString("Task Performance", 170, 35);

        int max = Math.max(completed, pending);

        if (max == 0) {
            g2.setFont(new Font("Arial", Font.PLAIN, 16));
            g2.drawString("No task data available", 170, 180);
            return;
        }

        int chartHeight = 200;
        int barWidth = 100;

        int completedHeight =
                (int) ((completed / (double) max) * chartHeight);

        int pendingHeight =
                (int) ((pending / (double) max) * chartHeight);

        // Completed Tasks Bar
        g2.fillRect(
                120,
                280 - completedHeight,
                barWidth,
                completedHeight
        );

        // Pending Tasks Bar
        g2.fillRect(
                300,
                280 - pendingHeight,
                barWidth,
                pendingHeight
        );

        g2.setFont(new Font("Arial", Font.BOLD, 14));

        g2.drawString(
                "Completed",
                125,
                305
        );

        g2.drawString(
                "Pending",
                315,
                305
        );

        g2.drawString(
                String.valueOf(completed),
                160,
                270 - completedHeight
        );

        g2.drawString(
                String.valueOf(pending),
                340,
                270 - pendingHeight
        );
    }
}
