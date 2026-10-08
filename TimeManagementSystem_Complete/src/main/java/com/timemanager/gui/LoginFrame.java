// package com.timemanager.gui;

// import com.timemanager.dao.UserDAO;
// import com.timemanager.model.User;
// import com.timemanager.util.Session;
// import javax.swing.*;
// import java.awt.*;

// public class LoginFrame extends JFrame {
//     private final JTextField email=new JTextField();
//     private final JPasswordField password=new JPasswordField();

//     public LoginFrame(){
//         setTitle("Time Management System - Login");
//         setSize(430,300); setLocationRelativeTo(null); setDefaultCloseOperation(EXIT_ON_CLOSE);
//         JPanel p=new JPanel(new GridBagLayout());
//         GridBagConstraints g=new GridBagConstraints();
//         g.insets=new Insets(8,8,8,8); g.fill=GridBagConstraints.HORIZONTAL;
//         JLabel title=new JLabel("TIME MANAGEMENT SYSTEM",SwingConstants.CENTER);
//         title.setFont(new Font("Arial",Font.BOLD,20));
//         g.gridx=0;g.gridy=0;g.gridwidth=2;p.add(title,g);
//         g.gridwidth=1;g.gridy=1;g.gridx=0;p.add(new JLabel("Email"),g);
//         g.gridx=1;p.add(email,g);
//         g.gridy=2;g.gridx=0;p.add(new JLabel("Password"),g);
//         g.gridx=1;p.add(password,g);
//         JButton login=new JButton("LOGIN");
//         g.gridy=3;g.gridx=0;g.gridwidth=2;p.add(login,g);
//         login.addActionListener(e->doLogin());
//         setContentPane(p);
//     }

//     private void doLogin(){
//         try{
//             User user=new UserDAO().login(email.getText().trim(),new String(password.getPassword()));
//             if(user==null){ JOptionPane.showMessageDialog(this,"Invalid login."); return; }
//             Session.setUser(user); dispose();
//             if("ADMIN".equals(user.getRole())) new AdminFrame().setVisible(true);
//             else new UserFrame().setVisible(true);
//         }catch(Exception ex){
//             JOptionPane.showMessageDialog(this,"Database error: "+ex.getMessage());
//         }
//     }
// }



// package com.timemanager.gui;

// import com.timemanager.dao.UserDAO;
// import com.timemanager.model.User;
// import com.timemanager.util.Session;

// import javax.swing.*;
// import java.awt.*;

// public class LoginFrame extends JFrame {

//     private final JTextField email = new JTextField();
//     private final JPasswordField password = new JPasswordField();
//     private final JComboBox<String> role =
//         new JComboBox<>(new String[]{"USER", "ADMIN"});

//     public LoginFrame() {

//         setTitle("Time Management System - Login");
//         setSize(430, 330);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(EXIT_ON_CLOSE);

//         JPanel p = new JPanel(new GridBagLayout());
//         GridBagConstraints g = new GridBagConstraints();

//         g.insets = new Insets(8, 8, 8, 8);
//         g.fill = GridBagConstraints.HORIZONTAL;

//         JLabel title = new JLabel(
//                 "TIME MANAGEMENT SYSTEM",
//                 SwingConstants.CENTER
//         );

//         title.setFont(new Font("Arial", Font.BOLD, 20));

//         g.gridx = 0;
//         g.gridy = 0;
//         g.gridwidth = 2;
//         p.add(title, g);

//         g.gridwidth = 1;

//         g.gridy = 1;
//         g.gridx = 0;
//         p.add(new JLabel("Email"), g);

//         g.gridx = 1;
//         p.add(email, g);

//         g.gridy = 2;
//         g.gridx = 0;
//         p.add(new JLabel("Password"), g);

//         g.gridx = 1;
//         p.add(password, g);

//         // LOGIN button
//         JButton login = new JButton("LOGIN");

//         g.gridy = 4;
//         g.gridx = 0;
//         g.gridwidth = 2;
//         p.add(login, g);

//         // CREATE ACCOUNT button
//         JButton register = new JButton("CREATE ACCOUNT");

//         g.gridy = 5;
//         p.add(register, g);

//         login.addActionListener(e -> doLogin());

//         register.addActionListener(e -> {
//             dispose();
//             new RegisterFrame().setVisible(true);
//         });

//         setContentPane(p);
//     }

//     private void doLogin() {

//         try {

//             User user = new UserDAO().login(
//                     email.getText().trim(),
//                     new String(password.getPassword())
//             );
//             String selectedRole = (String) role.getSelectedItem();

//             if (user != null && !selectedRole.equals(user.getRole())) {
//              JOptionPane.showMessageDialog(this, "Selected role does not match this account.");
//              return;
//          }

//             if (user == null) {

//                 JOptionPane.showMessageDialog(
//                         this,
//                         "Invalid login."
//                 );

//                 return;
//             }

//             Session.setUser(user);
//             dispose();

//             if ("ADMIN".equals(user.getRole())) {
//                 new AdminFrame().setVisible(true);
//             } else {
//                 new UserFrame().setVisible(true);
//             }

//         } catch (Exception ex) {

//             JOptionPane.showMessageDialog(
//                     this,
//                     "Database error: " + ex.getMessage()
//             );
//         }
//     }
// }




package com.timemanager.gui;

import com.timemanager.dao.UserDAO;
import com.timemanager.model.User;
import com.timemanager.util.Session;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final JTextField email = new JTextField();
    private final JPasswordField password = new JPasswordField();

    private final JComboBox<String> role =
            new JComboBox<>(new String[]{"USER", "ADMIN"});

    public LoginFrame() {

        setTitle("Time Management System - Login");
        setSize(430, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel(
                "TIME MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 20));

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        p.add(title, g);

        g.gridwidth = 1;

        // Email
        g.gridy = 1;
        g.gridx = 0;
        p.add(new JLabel("Email"), g);

        g.gridx = 1;
        p.add(email, g);

        // Password
        g.gridy = 2;
        g.gridx = 0;
        p.add(new JLabel("Password"), g);

        g.gridx = 1;
        p.add(password, g);

        // Role
        g.gridy = 3;
        g.gridx = 0;
        p.add(new JLabel("Role"), g);

        g.gridx = 1;
        p.add(role, g);

        // LOGIN button
        JButton login = new JButton("LOGIN");

        g.gridy = 4;
        g.gridx = 0;
        g.gridwidth = 2;
        p.add(login, g);

        // CREATE ACCOUNT button
        JButton register = new JButton("CREATE ACCOUNT");

        g.gridy = 5;
        p.add(register, g);

        login.addActionListener(e -> doLogin());

        register.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });

        setContentPane(p);
    }

    private void doLogin() {

        try {

            User user = new UserDAO().login(
                    email.getText().trim(),
                    new String(password.getPassword())
            );

            // String selectedRole = (String) role.getSelectedItem();
            String selectedRole = String.valueOf(role.getSelectedItem())
            .trim()
            .toUpperCase();

            if (user == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid email or password."
                );
                return;
            }

            if (!selectedRole.equals(user.getRole())) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selected role does not match this account."
                );
                return;
            }

            Session.setUser(user);
            dispose();

            if ("ADMIN".equals(user.getRole())) {
                new AdminFrame().setVisible(true);
            } else {
                new UserFrame().setVisible(true);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error: " + ex.getMessage()
            );
        }
    }
}
