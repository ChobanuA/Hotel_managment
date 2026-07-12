package dao;

import database.DBConnection;
import model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {


    public List<Reservation> getAllReservations(){

        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM REZERVARE";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()){


            while(rs.next()){

                Reservation reservation = new Reservation();


                reservation.setIdReservation(
                        rs.getInt("id_rezervare")
                );


                reservation.setIdClient(
                        rs.getInt("id_client")
                );


                reservation.setIdCamera(
                        rs.getInt("id_camera")
                );


                reservation.setDataCheckIn(
                        rs.getString("data_checkin")
                );


                reservation.setDataCheckOut(
                        rs.getString("data_checkout")
                );


                reservations.add(reservation);

            }


        }catch(SQLException e){
            e.printStackTrace();
        }


        return reservations;

    }



    public void insertReservation(Reservation reservation){


        String sql =
                "INSERT INTO REZERVARE(id_client,id_camera,data_checkin,data_checkout) VALUES(?,?,?,?)";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){


            statement.setInt(1,reservation.getIdClient());

            statement.setInt(2,reservation.getIdCamera());

            statement.setString(3,reservation.getDataCheckIn());

            statement.setString(4,reservation.getDataCheckOut());


            statement.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }

    }



    public void deleteReservation(int id){


        String sql =
                "DELETE FROM REZERVARE WHERE id_rezervare=?";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){


            statement.setInt(1,id);

            statement.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }


    }

}