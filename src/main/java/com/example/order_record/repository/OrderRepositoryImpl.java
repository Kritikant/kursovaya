package com.example.order_record.repository;

import com.example.order_record.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    private final Connection connection;

    public OrderRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Order save(Order order) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO \"order\" (client_id, delivery_man_id, order_date, when_deliver, note) VALUES (?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getDeliveryManId());
            ps.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
            ps.setTimestamp(4, Timestamp.valueOf(order.getWhenDeliver()));
            ps.setString(5, order.getNote());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean update(Order order) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE \"order\" SET client_id = ?, delivery_man_id = ?, order_date = ?, when_deliver = ?, note = ? WHERE id = ?;")) {
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getDeliveryManId());
            ps.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
            ps.setTimestamp(4, Timestamp.valueOf(order.getWhenDeliver()));
            ps.setString(5, order.getNote());
            ps.setInt(6, order.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"order\";")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Order findOrder = setOrder(resultSet);
                orderList.add(findOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public boolean deleteAll() {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM \"order\";")) {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public Optional<Order> findById(Integer id) {
        Optional<Order> order = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM \"order\" WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Order findOrder = setOrder(resultSet);
                order = Optional.of(findOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean deleteById(Integer id) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM \"order\" WHERE id = ?;")) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    private Order setOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setClientId(resultSet.getInt("client_id"));
        order.setDeliveryManId(resultSet.getInt("delivery_man_id"));
        order.setOrderDate(resultSet.getTimestamp("order_date").toLocalDateTime());
        order.setWhenDeliver(resultSet.getTimestamp("when_deliver").toLocalDateTime());
        order.setNote(resultSet.getString("note"));
        return order;
    }

}
