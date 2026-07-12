package ui;

import dao.RoomDAO;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    private RoomDAO roomDAO;


    public RoomPanel() {

        roomDAO = new RoomDAO();

        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new FlowLayout());


        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");


        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);


        add(topPanel, BorderLayout.NORTH);



        model = new DefaultTableModel();


        model.setColumnIdentifiers(new Object[]{
                "ID",
                "Numar",
                "Tip",
                "Pret",
                "Status"
        });


        table = new JTable(model);


        JScrollPane scrollPane = new JScrollPane(table);


        add(scrollPane, BorderLayout.CENTER);



        loadRooms();



        addButton.addActionListener(e -> addRoom());

        deleteButton.addActionListener(e -> deleteRoom());

        editButton.addActionListener(e -> editRoom());

    }



    private void loadRooms(){

        model.setRowCount(0);


        List<Room> rooms = roomDAO.getAllRooms();


        for(Room room : rooms){


            model.addRow(new Object[]{

                    room.getIdRoom(),
                    room.getNumar(),
                    room.getTip(),
                    room.getPret(),
                    room.getStatus()

            });


        }

    }




    private void addRoom(){


        JTextField numarField = new JTextField();

        JTextField tipField = new JTextField();

        JTextField pretField = new JTextField();

        JTextField statusField = new JTextField();



        Object[] message = {

                "Numar:",
                numarField,

                "Tip:",
                tipField,

                "Pret:",
                pretField,

                "Status:",
                statusField

        };



        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Add Room",
                JOptionPane.OK_CANCEL_OPTION
        );



        if(option == JOptionPane.OK_OPTION){


            Room room = new Room();


            room.setNumar(
                    Integer.parseInt(numarField.getText())
            );


            room.setTip(
                    tipField.getText()
            );


            room.setPret(
                    Double.parseDouble(pretField.getText())
            );


            room.setStatus(
                    statusField.getText()
            );



            roomDAO.insertRoom(room);


            loadRooms();

        }


    }
    private void deleteRoom(){

        int selectedRow = table.getSelectedRow();


        if(selectedRow == -1){

            JOptionPane.showMessageDialog(this,
                    "Select a room first!");

            return;
        }


        int id = (int) model.getValueAt(selectedRow,0);



        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this room?",
                "Confirm delete",
                JOptionPane.YES_NO_OPTION
        );


        if(confirm == JOptionPane.YES_OPTION){


            roomDAO.deleteRoom(id);


            loadRooms();

        }

    }



    private void editRoom(){

        int selectedRow = table.getSelectedRow();


        if(selectedRow == -1){

            JOptionPane.showMessageDialog(this,
                    "Select a room first!");

            return;
        }



        int id = (int) model.getValueAt(selectedRow,0);


        JTextField numarField = new JTextField(
                model.getValueAt(selectedRow,1).toString()
        );


        JTextField tipField = new JTextField(
                model.getValueAt(selectedRow,2).toString()
        );


        JTextField pretField = new JTextField(
                model.getValueAt(selectedRow,3).toString()
        );


        JTextField statusField = new JTextField(
                model.getValueAt(selectedRow,4).toString()
        );



        Object[] message = {

                "Numar:",
                numarField,

                "Tip:",
                tipField,

                "Pret:",
                pretField,

                "Status:",
                statusField

        };



        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Edit Room",
                JOptionPane.OK_CANCEL_OPTION
        );



        if(option == JOptionPane.OK_OPTION){


            Room room = new Room();


            room.setIdRoom(id);


            room.setNumar(
                    Integer.parseInt(numarField.getText())
            );


            room.setTip(
                    tipField.getText()
            );


            room.setPret(
                    Double.parseDouble(pretField.getText())
            );


            room.setStatus(
                    statusField.getText()
            );



            roomDAO.updateRoom(room);



            loadRooms();

        }

    }


}