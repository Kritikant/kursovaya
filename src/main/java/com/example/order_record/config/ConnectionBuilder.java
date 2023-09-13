package com.example.order_record.config;

import java.sql.Connection;
import java.sql.SQLException;


/*В данном коде определен интерфейс ConnectionBuilder, который находится в пакете com.example.order_record.config.
Интерфейс объявляет один метод getConnection(), который предназначен для получения объекта Connection (соединения) с
базой данных. Этот метод может выбросить исключение SQLException. Интерфейс ConnectionBuilder определяет контракт,
который должны реализовать классы, чтобы обеспечить возможность установки соединения с базой данных.

Конкретные реализации этого интерфейса будут содержать реализацию метода getConnection(),
возвращая объект Connection для конкретного типа базы данных.*/

public interface ConnectionBuilder {
    Connection getConnection() throws SQLException;
}
