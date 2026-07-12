package ui;

import dao.PaymentDAO;
import model.Payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentPanel extends JPanel {


    private JTable table;

    private DefaultTableModel model;

    private JButton addButton;
    private JButton deleteButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;
    private JButton editButton;

    private PaymentDAO paymentDAO;



    public PaymentPanel(){


        paymentDAO = new PaymentDAO();


        setLayout(new BorderLayout());



        JPanel topPanel = new JPanel(new FlowLayout());

        JLabel title = new JLabel("Payments");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.SOUTH);
        addButton = new JButton("Add");

        deleteButton = new JButton("Delete");
        searchField = new JTextField(15);

        searchButton = new JButton("Search");

        resetButton = new JButton("Reset");
        editButton = new JButton("Edit");


        topPanel.add(addButton);

        topPanel.add(deleteButton);
        topPanel.add(searchField);

        topPanel.add(searchButton);

        topPanel.add(resetButton);
        topPanel.add(editButton);


        add(topPanel, BorderLayout.NORTH);




        model = new DefaultTableModel();


        model.setColumnIdentifiers(new Object[]{

                "ID",
                "Reservation ID",
                "Suma",
                "Data plata",
                "Metoda"

        });



        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane scrollPane = new JScrollPane(table);


        add(scrollPane, BorderLayout.CENTER);



        loadPayments();



        addButton.addActionListener(e -> addPayment());

        deleteButton.addActionListener(e -> deletePayment());
        searchButton.addActionListener(e -> searchPayment());

        resetButton.addActionListener(e -> loadPayments());
        editButton.addActionListener(e -> editPayment());


    }





    private void loadPayments(){


        model.setRowCount(0);



        List<Payment> payments =
                paymentDAO.getAllPayments();



        for(Payment payment : payments){


            model.addRow(new Object[]{


                    payment.getIdPayment(),

                    payment.getIdReservation(),

                    payment.getSuma(),

                    payment.getDataPlata(),

                    payment.getMetoda()


            });


        }


    }





    private void addPayment(){


        JTextField reservationField = new JTextField();

        JTextField sumaField = new JTextField();

        JTextField dataField = new JTextField();

        JTextField metodaField = new JTextField();



        Object[] message = {


                "Reservation ID:",
                reservationField,


                "Suma:",
                sumaField,


                "Data plata:",
                dataField,


                "Metoda:",
                metodaField


        };



        int option = JOptionPane.showConfirmDialog(

                this,

                message,

                "Add Payment",

                JOptionPane.OK_CANCEL_OPTION

        );



        if(option == JOptionPane.OK_OPTION){



            Payment payment = new Payment();



            payment.setIdReservation(

                    Integer.parseInt(reservationField.getText())

            );



            payment.setSuma(

                    Double.parseDouble(sumaField.getText())

            );



            payment.setDataPlata(

                    dataField.getText()

            );



            payment.setMetoda(

                    metodaField.getText()

            );



            paymentDAO.insertPayment(payment);



            loadPayments();
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");


        }


    }
    private void deletePayment() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this,
                    "Select a payment first!");

            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this payment?",
                "Confirm delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            paymentDAO.deletePayment(id);

            loadPayments();
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

        }

    }
    private void searchPayment() {

        String text = searchField.getText().toLowerCase();

        model.setRowCount(0);

        for (Payment payment : paymentDAO.getAllPayments()) {

            if (String.valueOf(payment.getIdPayment()).contains(text)
                    || String.valueOf(payment.getIdReservation()).contains(text)
                    || payment.getMetoda().toLowerCase().contains(text)
                    || payment.getDataPlata().toLowerCase().contains(text)) {

                model.addRow(new Object[]{

                        payment.getIdPayment(),
                        payment.getIdReservation(),
                        payment.getSuma(),
                        payment.getDataPlata(),
                        payment.getMetoda()

                });

            }

        }

    }
    private void editPayment() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this,
                    "Select a payment first!");

            return;

        }

        int id = (int) model.getValueAt(selectedRow, 0);

        JTextField reservationField = new JTextField(
                model.getValueAt(selectedRow, 1).toString());

        JTextField sumaField = new JTextField(
                model.getValueAt(selectedRow, 2).toString());

        JTextField dataField = new JTextField(
                model.getValueAt(selectedRow, 3).toString());

        JTextField metodaField = new JTextField(
                model.getValueAt(selectedRow, 4).toString());


        Object[] message = {

                "Reservation ID:", reservationField,
                "Suma:", sumaField,
                "Data plata:", dataField,
                "Metoda:", metodaField

        };


        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Edit Payment",
                JOptionPane.OK_CANCEL_OPTION
        );


        if (option == JOptionPane.OK_OPTION) {

            Payment payment = new Payment();

            payment.setIdPayment(id);
            payment.setIdReservation(
                    Integer.parseInt(reservationField.getText()));
            payment.setSuma(
                    Double.parseDouble(sumaField.getText()));
            payment.setDataPlata(
                    dataField.getText());
            payment.setMetoda(
                    metodaField.getText());

            paymentDAO.updatePayment(payment);

            loadPayments();

        }

    }

}