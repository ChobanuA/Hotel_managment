package dao;

import database.DBConnection;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public List<Client> getAllClients() {

        List<Client> clients = new ArrayList<>();

        String sql = "SELECT * FROM CLIENT";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                Client client = new Client();

                client.setIdClient(rs.getInt("id_client"));
                client.setNume(rs.getString("nume"));
                client.setPrenume(rs.getString("prenume"));
                client.setTelefon(rs.getString("telefon"));
                client.setEmail(rs.getString("email"));

                clients.add(client);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
    public Client getClientById(int id) {

        String sql = "SELECT * FROM CLIENT WHERE id_client=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Client(
                        rs.getInt("id_client"),
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("telefon"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void insertClient(Client client) {

        String sql = "INSERT INTO CLIENT(nume, prenume, telefon, email) VALUES(?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, client.getNume());
            statement.setString(2, client.getPrenume());
            statement.setString(3, client.getTelefon());
            statement.setString(4, client.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteClient(int id) {

        String sql = "DELETE FROM CLIENT WHERE id_client=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateClient(Client client) {

        String sql = "UPDATE CLIENT SET nume=?, prenume=?, telefon=?, email=? WHERE id_client=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, client.getNume());
            statement.setString(2, client.getPrenume());
            statement.setString(3, client.getTelefon());
            statement.setString(4, client.getEmail());
            statement.setInt(5, client.getIdClient());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}