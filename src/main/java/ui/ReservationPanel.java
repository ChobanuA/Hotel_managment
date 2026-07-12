package ui;

import dao.ReservationDAO;
import model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReservationPanel extends JPanel {


    private JTable table;

    private DefaultTableModel model;

    private JButton addButton;
    private JButton deleteButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;

    private ReservationDAO reservationDAO;



    public ReservationPanel(){


        reservationDAO = new ReservationDAO();


        setLayout(new BorderLayout());



        JPanel topPanel = new JPanel(new FlowLayout());

        JLabel title = new JLabel("Reservations");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.SOUTH);
        addButton = new JButton("Add");

        deleteButton = new JButton("Delete");
        searchField = new JTextField(15);

        searchButton = new JButton("Search");

        resetButton = new JButton("Reset");



        topPanel.add(addButton);

        topPanel.add(deleteButton);
        topPanel.add(searchField);

        topPanel.add(searchButton);

        topPanel.add(resetButton);



        add(topPanel, BorderLayout.NORTH);




        model = new DefaultTableModel();


        model.setColumnIdentifiers(new Object[]{

                "ID",
                "Client ID",
                "Room ID",
                "Check In",
                "Check Out"

        });



        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        JScrollPane scrollPane = new JScrollPane(table);


        add(scrollPane, BorderLayout.CENTER);



        loadReservations();



        addButton.addActionListener(e -> addReservation());

        deleteButton.addActionListener(e -> deleteReservation());
        searchButton.addActionListener(e -> searchReservation());

        resetButton.addActionListener(e -> loadReservations());


    }




    private void loadReservations(){


        model.setRowCount(0);



        List<Reservation> reservations =
                reservationDAO.getAllReservations();



        for(Reservation reservation : reservations){


            model.addRow(new Object[]{


                    reservation.getIdReservation(),

                    reservation.getIdClient(),

                    reservation.getIdCamera(),

                    reservation.getDataCheckIn(),

                    reservation.getDataCheckOut()


            });


        }


    }





    private void addReservation(){


        JTextField clientField = new JTextField();

        JTextField roomField = new JTextField();

        JTextField checkInField = new JTextField();

        JTextField checkOutField = new JTextField();



        Object[] message = {


                "Client ID:",
                clientField,


                "Room ID:",
                roomField,


                "Check In:",
                checkInField,


                "Check Out:",
                checkOutField


        };



        int option = JOptionPane.showConfirmDialog(

                this,

                message,

                "Add Reservation",

                JOptionPane.OK_CANCEL_OPTION

        );



        if(option == JOptionPane.OK_OPTION){


            Reservation reservation = new Reservation();



            reservation.setIdClient(

                    Integer.parseInt(clientField.getText())

            );



            reservation.setIdCamera(

                    Integer.parseInt(roomField.getText())

            );



            reservation.setDataCheckIn(

                    checkInField.getText()

            );



            reservation.setDataCheckOut(

                    checkOutField.getText()

            );



            reservationDAO.insertReservation(reservation);



            loadReservations();
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

        }


    }
    private void deleteReservation(){

        int selectedRow = table.getSelectedRow();


        if(selectedRow == -1){

            JOptionPane.showMessageDialog(this,
                    "Select a reservation first!");

            return;
        }


        int id = (int) model.getValueAt(selectedRow,0);



        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this reservation?",
                "Confirm delete",
                JOptionPane.YES_NO_OPTION
        );


        if(confirm == JOptionPane.YES_OPTION){


            reservationDAO.deleteReservation(id);


            loadReservations();
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

        }

    }
    private void searchReservation() {

        String text = searchField.getText().toLowerCase();

        model.setRowCount(0);

        for (Reservation reservation : reservationDAO.getAllReservations()) {

            if (String.valueOf(reservation.getIdReservation()).contains(text)
                    || String.valueOf(reservation.getIdClient()).contains(text)
                    || String.valueOf(reservation.getIdCamera()).contains(text)
                    || reservation.getDataCheckIn().toLowerCase().contains(text)
                    || reservation.getDataCheckOut().toLowerCase().contains(text)) {

                model.addRow(new Object[]{

                        reservation.getIdReservation(),
                        reservation.getIdClient(),
                        reservation.getIdCamera(),
                        reservation.getDataCheckIn(),
                        reservation.getDataCheckOut()

                });

            }

        }

    }

}