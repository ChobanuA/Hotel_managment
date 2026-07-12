package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Hotel Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));
        menuPanel.setPreferredSize(new Dimension(180, 0));

        JButton clientButton = new JButton("Clients");
        JButton roomButton = new JButton("Rooms");
        JButton reservationButton = new JButton("Reservations");
        JButton paymentButton = new JButton("Payments");
        JButton logoutButton = new JButton("Logout");

        menuPanel.add(clientButton);
        menuPanel.add(roomButton);
        menuPanel.add(reservationButton);
        menuPanel.add(paymentButton);
        menuPanel.add(logoutButton);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Hotel Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        contentPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}