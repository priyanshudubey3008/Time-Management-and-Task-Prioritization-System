package com.timemanager.gui;

import com.timemanager.dao.CategoryDAO;
import com.timemanager.dao.TaskDAO;
import com.timemanager.dao.TimeLogDAO;
import com.timemanager.model.Category;
import com.timemanager.model.Task;
import com.timemanager.service.TaskService;
import com.timemanager.util.Session;
import com.timemanager.util.TimeFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class UserFrame extends JFrame {

    private final TaskDAO taskDAO = new TaskDAO();
    private final JTable table = new JTable();

    private final JLabel totalTime = new JLabel("Total time: 0");

    private int activeLogId = -1;

    // Multithreading variables
    private Thread timerThread;
    private volatile boolean timerRunning = false;
    private long sessionSeconds = 0;

    public UserFrame() {

        setTitle("User Dashboard - " + Session.getUser().getName());
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(10, 10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton add = new JButton("Add Task");
        JButton refresh = new JButton("Refresh");
        JButton update = new JButton("Update Status");
        JButton start = new JButton("Start Timer");
        JButton stop = new JButton("Stop Timer");
        JButton logout = new JButton("Logout");

        top.add(add);
        top.add(refresh);
        top.add(update);
        top.add(start);
        top.add(stop);
        top.add(totalTime);
        top.add(logout);

        root.add(top, BorderLayout.NORTH);
        root.add(new JScrollPane(table), BorderLayout.CENTER);

        addAction(add);

        refresh.addActionListener(e -> loadTasks());

        update.addActionListener(e -> updateStatus());

        start.addActionListener(e -> startTimer());

        stop.addActionListener(e -> stopTimer());

        logout.addActionListener(e -> {
            stopTimerSilently();
            Session.logout();
            dispose();
            new LoginFrame().setVisible(true);
        });

        setContentPane(root);

        loadTasks();
        updateTime();
    }

    // =========================
    // ADD TASK
    // =========================

    private void addAction(JButton b) {

        b.addActionListener(e -> {

            JTextField name = new JTextField();
            JTextField desc = new JTextField();
            JTextField date = new JTextField("2026-10-15");

            JComboBox<String> imp =
                    new JComboBox<>(new String[]{"HIGH", "MEDIUM", "LOW"});

            JComboBox<Category> cat = new JComboBox<>();

            try {
                new CategoryDAO().findAll().forEach(cat::addItem);
            } catch (Exception ex) {
                show(ex);
            }
             
            // JPanel p = new JPanel(new GridLayout(0, 2, 5, 5));
           JPanel p = new JPanel(new GridLayout(5, 2, 10, 10));
            // JLabel title = new JLabel("👤 USER DASHBOARD", SwingConstants.CENTER);
            // title.setFont(new Font("Arial", Font.BOLD, 24));
            // p.add(title, BorderLayout.NORTH);

            p.add(new JLabel("Task Name"));
            p.add(name);

            p.add(new JLabel("Description"));
            p.add(desc);

            p.add(new JLabel("Deadline yyyy-mm-dd"));
            p.add(date);

            p.add(new JLabel("Importance"));
            p.add(imp);

            p.add(new JLabel("Category"));
            p.add(cat);

            if (JOptionPane.showConfirmDialog(
                    this,
                    p,
                    "Add Task",
                    JOptionPane.OK_CANCEL_OPTION
            ) == JOptionPane.OK_OPTION) {

                try {

                    Category c = (Category) cat.getSelectedItem();

                    Task t = new Task();

                    t.setUserId(Session.getUser().getUserId());
                    t.setTaskName(name.getText());
                    t.setDescription(desc.getText());
                    t.setDeadline(LocalDate.parse(date.getText()));
                    t.setImportance((String) imp.getSelectedItem());
                    t.setCategoryId(c == null ? null : c.getCategoryId());
                    t.setStatus("PENDING");

                    new TaskService().create(t);

                    loadTasks();

                } catch (Exception ex) {
                    show(ex);
                }
            }
        });
    }

    // =========================
    // LOAD TASKS
    // =========================

    private void loadTasks() {

    try {

        List<Task> tasks =
                taskDAO.findByUser(Session.getUser().getUserId());

        String[] cols = {
                "ID",
                "Task",
                "Deadline",
                "Importance",
                "Priority",
                "Status",
                "Time Spent"
        };

        DefaultTableModel m =
                new DefaultTableModel(cols, 0);

        for (Task t : tasks) {

            long taskSeconds =
                    new TimeLogDAO().totalSecondsForTask(t.getTaskId());

            m.addRow(new Object[]{
                    t.getTaskId(),
                    t.getTaskName(),
                    t.getDeadline(),
                    t.getImportance(),
                    String.format("%.1f", t.getPriorityScore()),
                    t.getStatus(),
                    TimeFormatter.format(taskSeconds)
            });
        }

        table.setModel(m);

    } catch (Exception ex) {
        show(ex);
    }
}

    // =========================
    // START TIMER
    // =========================

    private void startTimer() {

        int row = table.getSelectedRow();

        if (row < 0) {
            showMsg("Select a task.");
            return;
        }

        if (timerRunning) {
            showMsg("Timer is already running.");
            return;
        }

        try {

            int taskId = (int) table.getValueAt(row, 0);

            // Start database timer
            activeLogId = new TimeLogDAO().start(taskId);

            // Synchronization
            synchronized (this) {
                sessionSeconds = 0;
                timerRunning = true;
            }

            // Create background thread
            timerThread = new Thread(() -> {

                while (timerRunning) {

                    try {

                        Thread.sleep(1000);

                        // Synchronization
                        synchronized (this) {
                            sessionSeconds++;
                        }

                        // Update Swing GUI safely
                        SwingUtilities.invokeLater(() -> {

                            totalTime.setText(
                                    "Current session: "
                                            + TimeFormatter.format(sessionSeconds)
                            );

                        });

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();
                        break;
                    }
                }

            });

            timerThread.start();

            JOptionPane.showMessageDialog(
                    this,
                    "Timer started."
            );

        } catch (Exception ex) {
            show(ex);
        }
    }

    // =========================
    // STOP TIMER
    // =========================

    private void stopTimer() {

        if (activeLogId < 0) {
            showMsg("No active timer.");
            return;
        }

        try {

            // Stop background thread
            synchronized (this) {
                timerRunning = false;
            }

            if (timerThread != null) {

                timerThread.interrupt();
                timerThread = null;
            }

            // Save final duration into database
            new TimeLogDAO().stop(activeLogId);

            activeLogId = -1;

            // Show total database time
            updateTime();

            JOptionPane.showMessageDialog(
                    this,
                    "Timer stopped."
            );

        } catch (Exception ex) {
            show(ex);
        }
    }

    // =========================
    // STOP TIMER WITHOUT MESSAGE
    // =========================

    private void stopTimerSilently() {

        if (activeLogId < 0) {
            return;
        }

        try {

            synchronized (this) {
                timerRunning = false;
            }

            if (timerThread != null) {

                timerThread.interrupt();
                timerThread = null;
            }

            new TimeLogDAO().stop(activeLogId);

            activeLogId = -1;

        } catch (Exception ignored) {
        }
    }

    // =========================
    // UPDATE STATUS
    // =========================

    private void updateStatus() {

        int row = table.getSelectedRow();

        if (row < 0) {
            showMsg("Select a task.");
            return;
        }

        int taskId =
                (int) table.getValueAt(row, 0);

        String[] options = {
                "PENDING",
                "COMPLETED"
        };

        String status = (String) JOptionPane.showInputDialog(
                this,
                "Select Task Status:",
                "Update Status",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (status == null) {
            return;
        }

        try {

            boolean updated =
                    taskDAO.updateStatus(taskId, status);

            if (updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Task status updated successfully."
                );

                loadTasks();
            }

        } catch (Exception ex) {
            show(ex);
        }
    }

    // =========================
    // UPDATE TOTAL TIME
    // =========================

    private void updateTime() {

        try {

            long sec =
                    new TimeLogDAO()
                            .totalSecondsForUser(
                                    Session.getUser().getUserId()
                            );

            totalTime.setText(
                    "Total time: "
                            + TimeFormatter.format(sec)
            );

        } catch (Exception ignored) {
        }
    }

    // =========================
    // ERROR MESSAGE
    // =========================

    private void show(Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void showMsg(String s) {

        JOptionPane.showMessageDialog(
                this,
                s
        );
    }
}