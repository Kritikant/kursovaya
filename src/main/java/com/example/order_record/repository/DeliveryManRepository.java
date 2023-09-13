package com.example.order_record.repository;

import com.example.order_record.model.DeliveryMan;

import java.util.List;
import java.util.Optional;

public interface DeliveryManRepository extends AutoCloseable {

    DeliveryMan save(DeliveryMan deliveryMan);

    boolean deleteById(Integer id);

    boolean update(DeliveryMan deliveryMan);

    List<DeliveryMan> findAll();

    Optional<DeliveryMan> findById(Integer id);

    boolean deleteAll();

}
