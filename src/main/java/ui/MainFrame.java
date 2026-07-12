package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPanel;

    private JButton clientButton;
    private JButton roomButton;
    private JButton reservationButton;
    private JButton paymentButton;
    private JButton logoutButton;

    public MainFrame() {

        setTitle("Hotel Management System");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5,1,10,10));
        menuPanel.setPreferredSize(new Dimension(180,0));

        clientButton = new JButton("Clients");
        roomButton = new JButton("Rooms");
        reservationButton = new JButton("Reservations");
        paymentButton = new JButton("Payments");
        logoutButton = new JButton("Logout");

        menuPanel.add(clientButton);
        menuPanel.add(roomButton);
        menuPanel.add(reservationButton);
        menuPanel.add(paymentButton);
        menuPanel.add(logoutButton);

        contentPanel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel(
                "Welcome to Hotel Management System",
                SwingConstants.CENTER);

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26));

        contentPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        initEvents();

        setVisible(true);
    }

    private void initEvents() {

        clientButton.addActionListener(e -> showClients());

        roomButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Rooms module coming soon"));

        reservationButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Reservations module coming soon"));

        paymentButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Payments module coming soon"));

        logoutButton.addActionListener(e -> {

            dispose();

            new LoginFrame();

        });

    }

    private void showClients() {

        contentPanel.removeAll();

        contentPanel.add(new ClientPanel(),
                BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();

    }

}