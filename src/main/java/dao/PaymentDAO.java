package dao;

import database.DBConnection;
import model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {


    public List<Payment> getAllPayments(){

        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT * FROM PLATA";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()){


            while(rs.next()){

                Payment payment = new Payment();


                payment.setIdPayment(
                        rs.getInt("id_plata")
                );


                payment.setIdReservation(
                        rs.getInt("id_rezervare")
                );


                payment.setSuma(
                        rs.getDouble("suma")
                );


                payment.setDataPlata(
                        rs.getString("data_plata")
                );


                payment.setMetoda(
                        rs.getString("metoda")
                );


                payments.add(payment);

            }


        }catch(SQLException e){
            e.printStackTrace();
        }


        return payments;

    }



    public void insertPayment(Payment payment){


        String sql =
                "INSERT INTO PLATA(id_rezervare,suma,data_plata,metoda) VALUES(?,?,?,?)";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){


            statement.setInt(1,payment.getIdReservation());

            statement.setDouble(2,payment.getSuma());

            statement.setString(3,payment.getDataPlata());

            statement.setString(4,payment.getMetoda());


            statement.executeUpdate();


        }catch(SQLException e){

            e.printStackTrace();

        }

    }



    public void deletePayment(int id){


        String sql =
                "DELETE FROM PLATA WHERE id_plata=?";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){


            statement.setInt(1,id);


            statement.executeUpdate();


        }catch(SQLException e){

            e.printStackTrace();

        }


    }

}