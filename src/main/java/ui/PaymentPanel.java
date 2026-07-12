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

    private PaymentDAO paymentDAO;



    public PaymentPanel(){


        paymentDAO = new PaymentDAO();


        setLayout(new BorderLayout());



        JPanel topPanel = new JPanel(new FlowLayout());


        addButton = new JButton("Add");

        deleteButton = new JButton("Delete");


        topPanel.add(addButton);

        topPanel.add(deleteButton);


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



        JScrollPane scrollPane = new JScrollPane(table);


        add(scrollPane, BorderLayout.CENTER);



        loadPayments();



        addButton.addActionListener(e -> addPayment());

        deleteButton.addActionListener(e -> deletePayment());


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

        }

    }

}