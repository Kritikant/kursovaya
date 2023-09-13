package com.example.order_record.repository;

import com.example.order_record.model.Menu;
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

class MenuRepositoryImplTest {

    private static MenuRepository menuRepository;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = MenuRepositoryImplTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            menuRepository = new MenuRepositoryImpl(connection);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() {
        try {
            menuRepository.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void wipeTable() {
        menuRepository.deleteAll();
    }

    @Test
    void whenSave() {
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);

        Menu actual = menuRepository.findById(menu.getId()).get();

        assertThat(actual).usingRecursiveComparison().isEqualTo(menu);
    }

    @Test
    void whenUpdate() {
        Menu oldMenu = new Menu("Borsh", 100, 150);
        menuRepository.save(oldMenu);
        Menu menuForUpdate = new Menu(oldMenu.getId(), "Soup", 50, 200);

        boolean isUpdated = menuRepository.update(menuForUpdate);
        Menu actual = menuRepository.findById(oldMenu.getId()).get();

        assertThat(isUpdated).isTrue();
        assertThat(actual).usingRecursiveComparison().isEqualTo(menuForUpdate);
    }

    @Test
    void whenFindAll() {
        Menu menu1 = new Menu("Borsh", 100, 150);
        menuRepository.save(menu1);
        Menu menu2 = new Menu("Soup", 50, 200);
        menuRepository.save(menu2);

        List<Menu> menuList = menuRepository.findAll();

        assertThat(menuList).usingRecursiveComparison().isEqualTo(List.of(menu1, menu2));
    }

    @Test
    void whenDeleteAll() {
        Menu menu1 = new Menu("Borsh", 100, 150);
        menuRepository.save(menu1);
        Menu menu2 = new Menu("Soup", 50, 200);
        menuRepository.save(menu2);

        boolean isDeleted = menuRepository.deleteAll();

        assertThat(isDeleted).isTrue();
    }

    @Test
    void whenFindById() {
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);

        Menu actual = menuRepository.findById(menu.getId()).get();

        assertThat(actual).usingRecursiveComparison().isEqualTo(menu);
    }

    @Test
    void whenDeleteById() {
        Menu menu = new Menu("Borsh", 100, 150);
        menuRepository.save(menu);

        boolean isDeleted = menuRepository.deleteById(menu.getId());
        Optional<Menu> deletedMenu = menuRepository.findById(menu.getId());

        assertThat(isDeleted).isTrue();
        assertThat(deletedMenu).isEmpty();
    }

}