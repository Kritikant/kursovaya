package com.example.order_record.repository;

import com.example.order_record.model.DeliveryMan;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeliveryManRepositoryImplTest {

    private static DeliveryManRepository deliveryManRepository;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = DeliveryManRepositoryImplTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            deliveryManRepository = new DeliveryManRepositoryImpl(connection);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() {
        try {
            deliveryManRepository.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void wipeTable() {
        deliveryManRepository.deleteAll();
    }

    @Test
    void whenSave() {
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);

        DeliveryMan actual = deliveryManRepository.findById(deliveryMan.getId()).get();

        assertThat(actual).usingRecursiveComparison().isEqualTo(deliveryMan);
    }

    @Test
    void whenUpdate() {
        DeliveryMan oldDeliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(oldDeliveryMan);
        DeliveryMan deliveryManForUpdate = new DeliveryMan(oldDeliveryMan.getId(), "Artem", "Ivanov", "8-999-557-55-66");

        boolean isUpdated = deliveryManRepository.update(deliveryManForUpdate);
        DeliveryMan actual = deliveryManRepository.findById(oldDeliveryMan.getId()).get();

        assertThat(isUpdated).isTrue();
        assertThat(actual).usingRecursiveComparison().isEqualTo(deliveryManForUpdate);
    }

    @Test
    void whenFindAll() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan1);
        DeliveryMan deliveryMan2 = new DeliveryMan("Evgeniy", "Ivanov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan2);

        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();

        assertThat(deliveryManList).usingRecursiveComparison().isEqualTo(List.of(deliveryMan1, deliveryMan2));
    }

    @Test
    void whenDeleteAll() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan1);
        DeliveryMan deliveryMan2 = new DeliveryMan("Evgeniy", "Ivanov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan2);

        boolean isDeleted = deliveryManRepository.deleteAll();

        assertThat(isDeleted).isTrue();
    }

    @Test
    void whenFindById() {
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);

        DeliveryMan actual = deliveryManRepository.findById(deliveryMan.getId()).get();

        assertThat(actual).usingRecursiveComparison().isEqualTo(deliveryMan);
    }

    @Test
    void whenDeleteById() {
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);

        boolean isDeleted = deliveryManRepository.deleteById(deliveryMan.getId());
        Optional<DeliveryMan> deletedDeliveryMan = deliveryManRepository.findById(deliveryMan.getId());

        assertThat(isDeleted).isTrue();
        assertThat(deletedDeliveryMan).isEmpty();
    }

}