package ui;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton searchButton;

    private JTextField searchField;

    private ClientDAO clientDAO;

    public ClientPanel() {

        clientDAO = new ClientDAO();

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel title = new JLabel("Rooms");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.SOUTH);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        topPanel.add(new JLabel("Search:"));

        searchField = new JTextField(20);

        topPanel.add(searchField);

        searchButton = new JButton("Search");

        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{
                "ID",
                "Nume",
                "Prenume",
                "Telefon",
                "Email"
        });

        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadClients();

        addButton.addActionListener(e -> addClient());

        editButton.addActionListener(e -> editClient());

        deleteButton.addActionListener(e -> deleteClient());

        searchButton.addActionListener(e -> searchClient());
    }

    private void loadClients() {

        model.setRowCount(0);

        List<Client> clients = clientDAO.getAllClients();

        for (Client client : clients) {

            model.addRow(new Object[]{
                    client.getIdClient(),
                    client.getNume(),
                    client.getPrenume(),
                    client.getTelefon(),
                    client.getEmail()
            });

        }

    }
    private void addClient() {

        JTextField numeField = new JTextField();
        JTextField prenumeField = new JTextField();
        JTextField telefonField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] message = {
                "Nume:", numeField,
                "Prenume:", prenumeField,
                "Telefon:", telefonField,
                "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Add Client",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {

            Client client = new Client();

            client.setNume(numeField.getText());
            client.setPrenume(prenumeField.getText());
            client.setTelefon(telefonField.getText());
            client.setEmail(emailField.getText());

            clientDAO.insertClient(client);

            loadClients();
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

        }

    }

    private void editClient() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this,
                    "Select a client first!");

            return;
        }


        int id = (int) model.getValueAt(selectedRow,0);

        String nume = (String) model.getValueAt(selectedRow,1);
        String prenume = (String) model.getValueAt(selectedRow,2);
        String telefon = (String) model.getValueAt(selectedRow,3);
        String email = (String) model.getValueAt(selectedRow,4);


        JTextField numeField = new JTextField(nume);
        JTextField prenumeField = new JTextField(prenume);
        JTextField telefonField = new JTextField(telefon);
        JTextField emailField = new JTextField(email);


        Object[] message = {

                "Nume:", numeField,
                "Prenume:", prenumeField,
                "Telefon:", telefonField,
                "Email:", emailField

        };


        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Edit Client",
                JOptionPane.OK_CANCEL_OPTION
        );


        if(option == JOptionPane.OK_OPTION) {


            Client client = new Client();

            client.setIdClient(id);
            client.setNume(numeField.getText());
            client.setPrenume(prenumeField.getText());
            client.setTelefon(telefonField.getText());
            client.setEmail(emailField.getText());


            clientDAO.updateClient(client);


            loadClients();
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

        }

    }

    private void deleteClient() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this,
                    "Select a client first!");

            return;
        }


        int id = (int) model.getValueAt(selectedRow, 0);


        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this client?",
                "Confirm delete",
                JOptionPane.YES_NO_OPTION
        );


        if (confirm == JOptionPane.YES_OPTION) {

            clientDAO.deleteClient(id);

            loadClients();
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

        }

    }

    private void searchClient() {

        String text = searchField.getText().trim().toLowerCase();

        model.setRowCount(0);

        List<Client> clients = clientDAO.getAllClients();

        for (Client client : clients) {

            if (client.getNume().toLowerCase().contains(text)
                    || client.getPrenume().toLowerCase().contains(text)) {

                model.addRow(new Object[]{
                        client.getIdClient(),
                        client.getNume(),
                        client.getPrenume(),
                        client.getTelefon(),
                        client.getEmail()
                });

            }

        }

    }

}