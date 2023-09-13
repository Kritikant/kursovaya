package com.example.order_record.repository;

import com.example.order_record.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends AutoCloseable {

    Order save(Order order);

    boolean deleteById(Integer id);

    boolean update(Order order);

    List<Order> findAll();

    Optional<Order> findById(Integer id);

    boolean deleteAll();

}
