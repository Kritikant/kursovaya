package com.example.order_record.repository;

import com.example.order_record.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends AutoCloseable {

    OrderDetail save(OrderDetail orderDetail);

    boolean deleteById(Integer id);

    boolean update(OrderDetail orderDetail);

    List<OrderDetail> findAll();

    Optional<OrderDetail> findById(Integer id);

    Optional<OrderDetail> findByOrderId(Integer orderId);

    boolean deleteAll();

}
