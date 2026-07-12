package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public String login(String username, String password) {

        String sql = "SELECT rol FROM UTILIZATOR WHERE username=? AND parola=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("rol");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



}