package com.example.order_record.repository;

import com.example.order_record.model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    private final Connection connection;

    public OrderDetailRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO order_detail (order_id, menu_id, serving_count) VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, orderDetail.getOrderId());
            ps.setInt(2, orderDetail.getMenuId());
            ps.setInt(3, orderDetail.getServingCount());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                orderDetail.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE order_detail SET order_id = ?, menu_id = ?, serving_count = ? WHERE id = ?;")) {
            ps.setInt(1, orderDetail.getOrderId());
            ps.setInt(2, orderDetail.getMenuId());
            ps.setInt(3, orderDetail.getServingCount());
            ps.setInt(4, orderDetail.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public List<OrderDetail> findAll() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM order_detail;")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                OrderDetail findOrderDetail = setOrderDetail(resultSet);
                orderDetailList.add(findOrderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetailList;
    }

    @Override
    public boolean deleteAll() {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM order_detail;")) {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public Optional<OrderDetail> findById(Integer id) {
        Optional<OrderDetail> orderDetail = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM order_detail WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                OrderDetail findOrderDetail = setOrderDetail(resultSet);
                orderDetail = Optional.of(findOrderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    @Override
    public Optional<OrderDetail> findByOrderId(Integer orderId) {
        Optional<OrderDetail> orderDetail = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM order_detail WHERE order_id = ?;")) {
            ps.setInt(1, orderId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                OrderDetail findOrderDetail = setOrderDetail(resultSet);
                orderDetail = Optional.of(findOrderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    @Override
    public boolean deleteById(Integer id) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM order_detail WHERE id = ?;")) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    private OrderDetail setOrderDetail(ResultSet resultSet) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(resultSet.getInt("id"));
        orderDetail.setOrderId(resultSet.getInt("order_id"));
        orderDetail.setMenuId(resultSet.getInt("menu_id"));
        orderDetail.setServingCount(resultSet.getInt("serving_count"));
        return orderDetail;
    }

}
