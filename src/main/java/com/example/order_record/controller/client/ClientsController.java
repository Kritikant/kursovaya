package com.example.order_record.controller.client;/**/

import com.example.order_record.config.ConnectionBuilder;/**/
import com.example.order_record.config.ConnectionBuilderImpl;/**/
import com.example.order_record.model.Client;/**/
import com.example.order_record.repository.ClientRepository;/**/
import com.example.order_record.repository.ClientRepositoryImpl;/**/
import jakarta.servlet.ServletException;/**/
import jakarta.servlet.annotation.WebServlet;/**/
import jakarta.servlet.http.HttpServlet;/**/
import jakarta.servlet.http.HttpServletRequest;/**/
import jakarta.servlet.http.HttpServletResponse;/**/

import java.io.IOException;/**/
import java.sql.Connection;/**/
import java.util.List;/**/

/*В данном коде определен сервлет с аннотацией @WebServlet и URL-шаблоном "/clients". Он расширяет класс HttpServlet,
что делает его сервлетом веб-приложения и позволяет обрабатывать HTTP-запросы. В методе doGet() происходит обработка
GET-запросов. Вначале создается экземпляр ConnectionBuilder для установления соединения с базой данных. Далее
создается экземпляр ClientRepository, используя установленное соединение.
    Затем вызывается метод findAll() для получения списка всех клиентов из репозитория. Далее полученный список
клиентов устанавливается в атрибут "clients" объекта request, чтобы быть доступным в представлении JSP
(JavaServer Pages).
    Если при выполнении кода происходит исключение, оно перехватывается и выводится в стандартный вывод через метод
e.printStackTrace(). В конце метода используется объект requestDispatcher для перенаправления запроса на JSP-страницу
"/view/clients.jsp", где будет отображен список клиентов. Код реализует обработку GET-запроса на адрес "/clients".
    Он получает список всех клиентов из базы данных с использованием репозитория и передает этот список в JSP-страницу
для отображения.
    Затем создается экземпляр ClientRepository, используя установленное соединение.

В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:
- Создается новый объект Client (экземпляр класса), и его свойства (name, surname, address, phone) устанавливаются
на основе параметров запроса (request.getParameter()).

- Вызывается метод save() репозитория для сохранения нового объекта Client в базе данных.
- Если при выполнении кода возникает исключение, оно перехватывается и выводится в стандартный вывод с помощью
метода e.printStackTrace().

В конце метода вызывается метод doGet() для выполнения перенаправления на GET-запрос. Таким образом,
после сохранения нового объекта Client будет выполнен GET-запрос для обновления информации о клиентах.

Таким образом, данный код реализует обработку POST-запроса, где данные из параметров запроса используются для
создания нового объекта Client и сохранения его в базе данных с использованием репозитория.
Затем выполняется перенаправление на GET-запрос для обновления информации о клиентах.
*/


@WebServlet("/clients")
public class ClientsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        ClientRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new ClientRepositoryImpl(connection);
            List<Client> clients = repository.findAll();
            request.setAttribute("clients", clients);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/clients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        ClientRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new ClientRepositoryImpl(connection);
            Client newClient = new Client();
            newClient.setName(request.getParameter("name"));
            newClient.setSurname(request.getParameter("surname"));
            newClient.setAddress(request.getParameter("address"));
            newClient.setPhone(request.getParameter("phone"));
            repository.save(newClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }
}
