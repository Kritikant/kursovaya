package com.example.order_record.repository;

import com.example.order_record.model.DeliveryMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryManRepositoryImpl implements DeliveryManRepository {

    private final Connection connection;

    public DeliveryManRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public DeliveryMan save(DeliveryMan deliveryMan) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO delivery_man (name, surname, phone) VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, deliveryMan.getName());
            ps.setString(2, deliveryMan.getSurname());
            ps.setString(3, deliveryMan.getPhone());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                deliveryMan.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryMan;
    }

    @Override
    public boolean update(DeliveryMan deliveryMan) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE delivery_man SET name = ?, surname = ?, phone = ? WHERE id = ?;")) {
            ps.setString(1, deliveryMan.getName());
            ps.setString(2, deliveryMan.getSurname());
            ps.setString(3, deliveryMan.getPhone());
            ps.setInt(4, deliveryMan.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public List<DeliveryMan> findAll() {
        List<DeliveryMan> deliveryManList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM delivery_man;")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                DeliveryMan findDeliveryMan = setDeliveryMan(resultSet);
                deliveryManList.add(findDeliveryMan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryManList;
    }

    @Override
    public boolean deleteAll() {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM delivery_man;")) {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public Optional<DeliveryMan> findById(Integer id) {
        Optional<DeliveryMan> deliveryMan = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM delivery_man WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                DeliveryMan findDeliveryMan = setDeliveryMan(resultSet);
                deliveryMan = Optional.of(findDeliveryMan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryMan;
    }

    @Override
    public boolean deleteById(Integer id) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM delivery_man WHERE id = ?;")) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    private DeliveryMan setDeliveryMan(ResultSet resultSet) throws SQLException {
        DeliveryMan deliveryMan = new DeliveryMan();
        deliveryMan.setId(resultSet.getInt("id"));
        deliveryMan.setName(resultSet.getString("name"));
        deliveryMan.setSurname(resultSet.getString("surname"));
        deliveryMan.setPhone(resultSet.getString("phone"));
        return deliveryMan;
    }

}
