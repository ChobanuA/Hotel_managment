package ui;

import javax.swing.*;
import java.awt.*;
import dao.LoginDAO;


public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {

        setTitle("Hotel Management - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel());

        loginButton = new JButton("Login");
        panel.add(loginButton);

        add(panel);

        setVisible(true);
        loginButton.addActionListener(e -> login());
    }
    private void login() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        LoginDAO loginDAO = new LoginDAO();

        if (loginDAO.login(username, password)) {

            new MainFrame();
            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid username or password!");

        }

    }
}