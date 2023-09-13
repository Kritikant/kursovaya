package com.example.order_record.repository;

import com.example.order_record.model.Client;
import com.example.order_record.model.DeliveryMan;
import com.example.order_record.model.Menu;
import com.example.order_record.model.Order;
import com.example.order_record.model.OrderDetail;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderDetailRepositoryImplTest {

    private static OrderDetailRepository orderDetailRepository;
    private static OrderRepository orderRepository;
    private static ClientRepository clientRepository;
    private static DeliveryManRepository deliveryManRepository;
    private static MenuRepository menuRepository;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = OrderDetailRepositoryImplTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            orderDetailRepository = new OrderDetailRepositoryImpl(connection);
            orderRepository = new OrderRepositoryImpl(connection);
            clientRepository = new ClientRepositoryImpl(connection);
            deliveryManRepository = new DeliveryManRepositoryImpl(connection);
            menuRepository = new MenuRepositoryImpl(connection);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() {
        try {
            orderDetailRepository.close();
            orderRepository.close();
            clientRepository.close();
            deliveryManRepository.close();
            menuRepository.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void wipeTable() {
        orderDetailRepository.deleteAll();
        orderRepository.deleteAll();
        clientRepository.deleteAll();
        deliveryManRepository.deleteAll();
        menuRepository.deleteAll();
    }

    @Test
    void whenSave() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(order);
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);
        OrderDetail orderDetail = new OrderDetail(order.getId(), menu.getId(), 2);
        orderDetailRepository.save(orderDetail);

        OrderDetail actual = orderDetailRepository.findById(orderDetail.getId()).get();

        assertThat(actual).usingRecursiveComparison().isEqualTo(orderDetail);
    }

    @Test
    void whenUpdate() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(order);
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);
        OrderDetail oldOrderDetail = new OrderDetail(order.getId(), menu.getId(), 2);
        orderDetailRepository.save(oldOrderDetail);
        OrderDetail orderDetailForUpdate = new OrderDetail(oldOrderDetail.getId(), order.getId(), menu.getId(), 10);

        boolean isUpdated = orderDetailRepository.update(orderDetailForUpdate);
        OrderDetail actual = orderDetailRepository.findById(oldOrderDetail.getId()).get();

        assertThat(isUpdated).isTrue();
        assertThat(actual).usingRecursiveComparison().isEqualTo(orderDetailForUpdate);
    }

    @Test
    void whenFindAll() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(order);
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);
        OrderDetail orderDetail1 = new OrderDetail(order.getId(), menu.getId(), 2);
        orderDetailRepository.save(orderDetail1);
        OrderDetail orderDetail2 = new OrderDetail(order.getId(), menu.getId(), 10);
        orderDetailRepository.save(orderDetail2);

        List<OrderDetail> orders = orderDetailRepository.findAll();

        assertThat(orders).usingRecursiveComparison().isEqualTo(List.of(orderDetail1, orderDetail2));
    }

    @Test
    void whenDeleteAll() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(order);
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);
        OrderDetail orderDetail1 = new OrderDetail(order.getId(), menu.getId(), 2);
        orderDetailRepository.save(orderDetail1);
        OrderDetail orderDetail2 = new OrderDetail(order.getId(), menu.getId(), 10);
        orderDetailRepository.save(orderDetail2);

        boolean isDeleted = orderDetailRepository.deleteAll();

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
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);
        OrderDetail orderDetail = new OrderDetail(order.getId(), menu.getId(), 2);
        orderDetailRepository.save(orderDetail);

        OrderDetail actual = orderDetailRepository.findById(orderDetail.getId()).get();

        assertThat(actual).usingRecursiveComparison().isEqualTo(orderDetail);
    }

    @Test
    void whenDeleteById() {
        Client client = new Client("Andrey", "Petrov", "Novoslobodskaya", "8-390-587-55-66");
        clientRepository.save(client);
        DeliveryMan deliveryMan = new DeliveryMan("Andrey", "Petrov", "8-390-587-55-66");
        deliveryManRepository.save(deliveryMan);
        Order order = new Order(client.getId(), deliveryMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusHours(1), "description");
        orderRepository.save(order);
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);
        OrderDetail orderDetail = new OrderDetail(order.getId(), menu.getId(), 2);
        orderDetailRepository.save(orderDetail);

        boolean isDeleted = orderDetailRepository.deleteById(orderDetail.getId());
        Optional<OrderDetail> deletedOrderDetail = orderDetailRepository.findById(orderDetail.getId());

        assertThat(isDeleted).isTrue();
        assertThat(deletedOrderDetail).isEmpty();
    }

}