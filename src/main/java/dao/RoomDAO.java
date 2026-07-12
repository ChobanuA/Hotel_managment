package dao;

import database.DBConnection;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {


    public List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();

        String sql = "SELECT * FROM CAMERA";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {


            while(rs.next()) {

                Room room = new Room();

                room.setIdRoom(rs.getInt("id_camera"));
                room.setNumar(rs.getInt("numar_camera"));
                room.setTip(rs.getString("tip"));
                room.setPret(rs.getDouble("pret"));
                room.setStatus(rs.getString("status"));

                rooms.add(room);

            }


        }catch(SQLException e){
            e.printStackTrace();
        }


        return rooms;
    }



    public void insertRoom(Room room){

        String sql="INSERT INTO CAMERA(numar,tip,pret,status) VALUES(?,?,?,?)";


        try(Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)){


            statement.setInt(1,room.getNumar());
            statement.setString(2,room.getTip());
            statement.setDouble(3,room.getPret());
            statement.setString(4,room.getStatus());


            statement.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }

    }



    public void deleteRoom(int id){

        String sql="DELETE FROM CAMERA WHERE id_camera=?";


        try(Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)){


            statement.setInt(1,id);

            statement.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }

    }



    public void updateRoom(Room room){

        String sql="UPDATE CAMERA SET numar=?, tip=?, pret=?, status=? WHERE id_camera=?";


        try(Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)){


            statement.setInt(1,room.getNumar());
            statement.setString(2,room.getTip());
            statement.setDouble(3,room.getPret());
            statement.setString(4,room.getStatus());
            statement.setInt(5,room.getIdRoom());


            statement.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }

    }

}