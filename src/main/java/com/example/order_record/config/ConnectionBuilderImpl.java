package com.example.order_record.config;/**/

import java.io.InputStream;/**/
import java.sql.Connection;/**/
import java.sql.DriverManager;/**/
import java.util.Properties;/**/

/*В данном коде определен класс ConnectionBuilderImpl, который является реализацией интерфейса ConnectionBuilder.

В конструкторе класса ConnectionBuilderImpl происходит установка соединения с базой данных.
Сначала происходит чтение файла конфигурации "config.properties". С помощью метода
getResourceAsStream("config.properties") из класса ConnectionBuilderImpl.class.getClassLoader() получается поток
ввода (InputStream) для доступа к файлу "config.properties".
    Затем создается объект Properties, который используетсядля загрузки содержимого файла конфигурации с
помощью метода load(in). Файл конфигурации содержит информацию о драйвере базы данных, URL базы данных, логине и
пароле.
    Далее с помощью Class.forName(config.getProperty("driver-class-name")) загружается драйвер базы данных, указанный
в конфигурационном файле. Затем создается объект Connection с использованием

DriverManager.getConnection(url, login, password), где url, login и password берутся из конфигурационного файла.
Если происходит исключение при выполнении кода в блоке try, оно будет перехвачено, и выбрасывается
IllegalStateException с указанием причины исключения. Таким образом, класс ConnectionBuilderImpl отвечает за установку
соединения с базой данных, используя данные из конфигурационного файла. Выполнение этого кода позволяет получить
готовый объект Connection для работы с базой данных.
*/
public class ConnectionBuilderImpl implements ConnectionBuilder {

    private final Connection connection;

    public ConnectionBuilderImpl() {
        try (InputStream in = ConnectionBuilderImpl.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            String url = config.getProperty("url");
            String login = config.getProperty("username");
            String password = config.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    /**/
    @Override
    public Connection getConnection() {
        return connection;
    }

}
