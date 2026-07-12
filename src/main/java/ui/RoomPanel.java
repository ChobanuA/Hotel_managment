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
    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;

    private RoomDAO roomDAO;



    public RoomPanel() {

        roomDAO = new RoomDAO();

        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new FlowLayout());

        JLabel title = new JLabel("Clients");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.SOUTH);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        searchField = new JTextField(15);
        searchButton = new JButton("Search");
        resetButton = new JButton("Reset");



        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(resetButton);



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
        table.setDefaultEditor(Object.class, null);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane scrollPane = new JScrollPane(table);


        add(scrollPane, BorderLayout.CENTER);



        loadRooms();



        addButton.addActionListener(e -> addRoom());

        deleteButton.addActionListener(e -> deleteRoom());

        editButton.addActionListener(e -> editRoom());
        searchButton.addActionListener(e -> searchRoom());

        resetButton.addActionListener(e -> loadRooms());

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
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

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
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

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
            JOptionPane.showMessageDialog(this,
                    "Operation completed successfully!");

        }

    }
    private void searchRoom() {

        String text = searchField.getText().toLowerCase();

        model.setRowCount(0);

        for (Room room : roomDAO.getAllRooms()) {

            if (String.valueOf(room.getNumar()).contains(text)
                    || room.getTip().toLowerCase().contains(text)
                    || room.getStatus().toLowerCase().contains(text)) {

                model.addRow(new Object[]{
                        room.getIdRoom(),
                        room.getNumar(),
                        room.getTip(),
                        room.getPret(),
                        room.getStatus()
                });

            }

        }

    }

}