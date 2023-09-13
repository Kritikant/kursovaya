package com.example.order_record.repository;

import com.example.order_record.config.ConnectionBuilderImpl;
import com.example.order_record.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Client save(Client client) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO client (name, surname, address, phone) VALUES (?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getPhone());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                client.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public boolean update(Client client) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE client SET name = ?, surname = ?, address = ?, phone = ? WHERE id = ?;")) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getPhone());
            ps.setInt(5, client.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM client;")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Client findClient = setClient(resultSet);
                clients.add(findClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public boolean deleteAll() {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM client;")) {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<Client> client = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM client WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Client findClient = setClient(resultSet);
                client = Optional.of(findClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public boolean deleteById(Integer id) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM client WHERE id = ?;")) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    private Client setClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("id"));
        client.setName(resultSet.getString("name"));
        client.setSurname(resultSet.getString("surname"));
        client.setAddress(resultSet.getString("address"));
        client.setPhone(resultSet.getString("phone"));
        return client;
    }
}
