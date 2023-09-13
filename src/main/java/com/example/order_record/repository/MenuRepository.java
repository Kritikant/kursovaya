package com.example.order_record.repository;

import com.example.order_record.model.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends AutoCloseable {

    Menu save(Menu menu);

    boolean deleteById(Integer id);

    boolean update(Menu menu);


    List<Menu> findAll();

    Optional<Menu> findById(Integer id);

    Optional<Menu> findByFoodName(String foodName);

    boolean deleteAll();

}
