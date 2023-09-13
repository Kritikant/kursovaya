package com.example.order_record.repository;

import com.example.order_record.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends AutoCloseable {

    Client save(Client client);

    boolean deleteById(Integer id);

    boolean update(Client client);

    List<Client> findAll();

    boolean deleteAll();

    Optional<Client> findById(Integer id);

}
