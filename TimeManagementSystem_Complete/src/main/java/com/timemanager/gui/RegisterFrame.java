// package com.timemanager.gui;

// import com.timemanager.dao.UserDAO;

// import javax.swing.*;
// import java.awt.*;

// public class RegisterFrame extends JFrame {

//     private final JTextField name = new JTextField();
//     private final JTextField email = new JTextField();
//     private final JPasswordField password = new JPasswordField();
//     private final JPasswordField confirmPassword = new JPasswordField();

//     public RegisterFrame() {

//         setTitle("Create Account");
//         setSize(450, 350);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//         JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
//         panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//         panel.add(new JLabel("Name"));
//         panel.add(name);

//         panel.add(new JLabel("Email"));
//         panel.add(email);

//         panel.add(new JLabel("Password"));
//         panel.add(password);

//         panel.add(new JLabel("Confirm Password"));
//         panel.add(confirmPassword);

//         JButton register = new JButton("CREATE ACCOUNT");
//         JButton back = new JButton("BACK TO LOGIN");

//         panel.add(register);
//         panel.add(back);

//         register.addActionListener(e -> registerUser());

//         back.addActionListener(e -> {
//             dispose();
//             new LoginFrame().setVisible(true);
//         });

//         setContentPane(panel);
//     }

//     private void registerUser() {

//         String userName = name.getText().trim();
//         String userEmail = email.getText().trim();
//         String userPassword = new String(password.getPassword());
//         String confirm = new String(confirmPassword.getPassword());

//         if (userName.isEmpty() || userEmail.isEmpty()
//                 || userPassword.isEmpty() || confirm.isEmpty()) {

//             JOptionPane.showMessageDialog(
//                     this,
//                     "Please fill all fields."
//             );
//             return;
//         }

//         if (!userPassword.equals(confirm)) {

//             JOptionPane.showMessageDialog(
//                     this,
//                     "Passwords do not match."
//             );
//             return;
//         }

//         try {

//             boolean registered =
//                     new UserDAO().register(
//                             userName,
//                             userEmail,
//                             userPassword
//                     );

//             if (registered) {

//                 JOptionPane.showMessageDialog(
//                         this,
//                         "Account created successfully!"
//                 );

//                 dispose();
//                 new LoginFrame().setVisible(true);

//             } else {

//                 JOptionPane.showMessageDialog(
//                         this,
//                         "Account creation failed."
//                 );
//             }

//         } catch (Exception ex) {

//             JOptionPane.showMessageDialog(
//                     this,
//                     "Registration error: " + ex.getMessage()
//             );
//         }
//     }
// }

package com.timemanager.gui;

import com.timemanager.dao.UserDAO;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private final JTextField name = new JTextField();
    private final JTextField email = new JTextField();
    private final JPasswordField password = new JPasswordField();
    private final JPasswordField confirmPassword = new JPasswordField();

    private final JComboBox<String> role =
            new JComboBox<>(new String[]{"USER", "ADMIN"});

    public RegisterFrame() {

        setTitle("Create Account");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Name"));
        panel.add(name);

        panel.add(new JLabel("Email"));
        panel.add(email);

        panel.add(new JLabel("Password"));
        panel.add(password);

        panel.add(new JLabel("Confirm Password"));
        panel.add(confirmPassword);

        // Role
        panel.add(new JLabel("Role"));
        panel.add(role);

        JButton register = new JButton("CREATE ACCOUNT");
        JButton back = new JButton("BACK TO LOGIN");

        panel.add(register);
        panel.add(back);

        register.addActionListener(e -> registerUser());

        back.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        setContentPane(panel);
    }

    private void registerUser() {

        String userName = name.getText().trim();
        String userEmail = email.getText().trim();
        String userPassword = new String(password.getPassword());
        String confirm = new String(confirmPassword.getPassword());
        String selectedRole = (String) role.getSelectedItem();

        if (userName.isEmpty() || userEmail.isEmpty()
                || userPassword.isEmpty() || confirm.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );
            return;
        }

        if (!userPassword.equals(confirm)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match."
            );
            return;
        }

        try {

            boolean registered =
                    new UserDAO().register(
                            userName,
                            userEmail,
                            userPassword,
                            selectedRole
                    );

            if (registered) {

                JOptionPane.showMessageDialog(
                        this,
                        "Account created successfully!"
                );

                dispose();
                new LoginFrame().setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Account creation failed."
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration error: " + ex.getMessage()
            );
        }
    }
}