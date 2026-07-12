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
    private String role;

    public MainFrame(String role) {

        this.role = role;

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
        if(role.equalsIgnoreCase("ADMIN")){

            JButton usersButton = new JButton("Users");

            menuPanel.add(usersButton);

        }
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
        setTitle("Hotel Management System - " + role);

        setVisible(true);
    }

    private void initEvents() {

        clientButton.addActionListener(e -> showClients());

        roomButton.addActionListener(e -> showRooms());

        reservationButton.addActionListener(e -> showReservations());

        paymentButton.addActionListener(e -> showPayments());

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
    private void showRooms(){

        contentPanel.removeAll();

        contentPanel.add(new RoomPanel(), BorderLayout.CENTER);

        contentPanel.revalidate();

        contentPanel.repaint();

    }
    private void showReservations(){

        contentPanel.removeAll();

        contentPanel.add(new ReservationPanel(), BorderLayout.CENTER);

        contentPanel.revalidate();

        contentPanel.repaint();

    }
    private void showPayments() {

        contentPanel.removeAll();

        contentPanel.add(new PaymentPanel(), BorderLayout.CENTER);

        contentPanel.revalidate();

        contentPanel.repaint();

    }

}
