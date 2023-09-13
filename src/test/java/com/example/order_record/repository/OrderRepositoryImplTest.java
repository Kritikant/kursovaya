package com.example.order_record.repository;

import com.example.order_record.model.Client;
import com.example.order_record.model.DeliveryMan;
import com.example.order_record.model.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiPredicate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderRepositoryImplTest {

    private static OrderRepository orderRepository;
    private static ClientRepository clientRepository;
    private static DeliveryManRepository deliveryManRepository;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = OrderRepositoryImplTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            orderRepository = new OrderRepositoryImpl(connection);
            clientRepository = new ClientRepositoryImpl(connection);
            deliveryManRepository = new DeliveryManRepositoryImpl(connection);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() {
        try {
            orderRepository.close();
            clientRepository.close();
            deliveryManRepository.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void wipeTable() {
        orderRepository.deleteAll();
        clientRepository.deleteAll();
        deliveryManRepository.deleteAll();
    }

    @Test
    void whenSave() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(order);

        Order actual = orderRepository.findById(order.getId()).get();

        assertThat(actual).usingRecursiveComparison()
                .withEqualsForFields(localDateTimeMatches, "orderDate", "whenDeliver")
                .isEqualTo(order);
    }

    @Test
    void whenUpdate() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order oldOrder = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(oldOrder);
        Order orderForUpdate = new Order(oldOrder.getId(), client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "newDescription");

        boolean isUpdated = orderRepository.update(orderForUpdate);
        Order actual = orderRepository.findById(oldOrder.getId()).get();

        assertThat(isUpdated).isTrue();
        assertThat(actual).usingRecursiveComparison()
                .withEqualsForFields(localDateTimeMatches, "orderDate", "whenDeliver")
                .isEqualTo(orderForUpdate);
    }

    @Test
    void whenFindAll() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order1 = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description1");
        orderRepository.save(order1);
        Order order2 = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), "description2");
        orderRepository.save(order2);

        List<Order> orders = orderRepository.findAll();

        assertThat(orders).usingRecursiveComparison()
                .withEqualsForFields(localDateTimeMatches, "orderDate", "whenDeliver")
                .isEqualTo(List.of(order1, order2));
    }

    @Test
    void whenDeleteAll() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order1 = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description1");
        orderRepository.save(order1);
        Order order2 = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(2), "description2");
        orderRepository.save(order2);

        boolean isDeleted = orderRepository.deleteAll();

        assertThat(isDeleted).isTrue();
    }

    @Test
    void whenFindById() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(order);

        Order actual = orderRepository.findById(order.getId()).get();

        assertThat(actual).usingRecursiveComparison()
                .withEqualsForFields(localDateTimeMatches, "orderDate", "whenDeliver")
                .isEqualTo(order);
    }

    @Test
    void whenDeleteById() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(order);

        boolean isDeleted = orderRepository.deleteById(order.getId());
        Optional<Order> deletedOrder = orderRepository.findById(order.getId());

        assertThat(isDeleted).isTrue();
        assertThat(deletedOrder).isEmpty();
    }

    private final BiPredicate<LocalDateTime, LocalDateTime> localDateTimeMatches = (first, second) ->
            first.truncatedTo(ChronoUnit.SECONDS).isEqual(second.truncatedTo(ChronoUnit.SECONDS));

}