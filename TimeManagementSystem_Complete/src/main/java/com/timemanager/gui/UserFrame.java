package com.timemanager.gui;
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
