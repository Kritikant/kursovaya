package com.example.order_record.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.order_record.model.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

class ClientRepositoryImplTest {

    private static ClientRepository clientRepository;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = ClientRepositoryImplTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            clientRepository = new ClientRepositoryImpl(connection);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() {
        try {
            clientRepository.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void wipeTable() {
        clientRepository.deleteAll();
    }

    @Test
    void whenSave() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);

        Client actualClient = clientRepository.findById(client.getId()).get();

        assertThat(actualClient).usingRecursiveComparison().isEqualTo(client);
    }

    @Test
    void whenUpdate() {
        Client oldClient = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(oldClient);
        Client clientForUpdate = new Client(oldClient.getId(), "Artem", "Ivanov", "Rizhskaya", "8-999-557-55-66");

        boolean isUpdated = clientRepository.update(clientForUpdate);
        Client actualClient = clientRepository.findById(oldClient.getId()).get();

        assertThat(isUpdated).isTrue();
        assertThat(actualClient).usingRecursiveComparison().isEqualTo(clientForUpdate);
    }

    @Test
    void whenFindAll() {
        Client client1 = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client1);
        Client client2 = new Client("Evgeniy", "Ivanov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client2);

        List<Client> clients = clientRepository.findAll();

        assertThat(clients).usingRecursiveComparison().isEqualTo(List.of(client1, client2));
    }

    @Test
    void whenDeleteAll() {
        Client client1 = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client1);
        Client client2 = new Client("Evgeniy", "Ivanov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client2);

        boolean isDeleted = clientRepository.deleteAll();

        assertThat(isDeleted).isTrue();
    }

    @Test
    void whenFindById() {
        Client client1 = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client1);

        Client actualClient = clientRepository.findById(client1.getId()).get();

        assertThat(actualClient).usingRecursiveComparison().isEqualTo(client1);
    }

    @Test
    void whenDeleteById() {
        Client client1 = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client1);

        boolean isDeleted = clientRepository.deleteById(client1.getId());
        Optional<Client> deletedClient = clientRepository.findById(client1.getId());

        assertThat(isDeleted).isTrue();
        assertThat(deletedClient).isEmpty();
    }

}