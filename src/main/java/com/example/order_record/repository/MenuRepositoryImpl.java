package com.example.order_record.repository;

import com.example.order_record.model.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuRepositoryImpl implements MenuRepository {

    private final Connection connection;

    public MenuRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Menu save(Menu menu) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO menu (food_name, weight, price) VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, menu.getFoodName());
            ps.setInt(2, menu.getWeight());
            ps.setInt(3, menu.getPrice());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                menu.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public boolean update(Menu menu) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE menu SET food_name = ?, weight = ?, price = ? WHERE id = ?;")) {
            ps.setString(1, menu.getFoodName());
            ps.setInt(2, menu.getWeight());
            ps.setInt(3, menu.getPrice());
            ps.setInt(4, menu.getId());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public List<Menu> findAll() {
        List<Menu> menuList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM menu;")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Menu findMenu = setMenu(resultSet);
                menuList.add(findMenu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public boolean deleteAll() {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM menu;")) {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public Optional<Menu> findById(Integer id) {
        Optional<Menu> menu = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM menu WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Menu findMenu = setMenu(resultSet);
                menu = Optional.of(findMenu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public Optional<Menu> findByFoodName(String foodName) {
        Optional<Menu> menu = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM menu WHERE food_name = ?;")) {
            ps.setString(1, foodName);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Menu findMenu = setMenu(resultSet);
                menu = Optional.of(findMenu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public boolean deleteById(Integer id) {
        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM menu WHERE id = ?;")) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    private Menu setMenu(ResultSet resultSet) throws SQLException {
        Menu menu = new Menu();
        menu.setId(resultSet.getInt("id"));
        menu.setFoodName(resultSet.getString("food_name"));
        menu.setWeight(resultSet.getInt("weight"));
        menu.setPrice(resultSet.getInt("price"));
        return menu;
    }

}
