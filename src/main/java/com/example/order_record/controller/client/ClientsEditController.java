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
import java.util.Optional;/**/

/*В данном коде определен сервлет с аннотацией @WebServlet и URL-шаблоном "/editClient". Сервлет наследуется от класса
HttpServlet, что позволяет ему обрабатывать HTTP-запросы. serialVersionUID - это идентификатор версии класса,
используемый для сериализации и десериализации объектов сервлета. Потом создается экземпляр ConnectionBuilder

для установления соединения с базой данных. Затем создается экземпляр ClientRepository, используя установленное
соединение. В блоке try-with-resources получается соединение с базой данных.

Далее происходит следующее:

- Получаем параметр "id" из запроса и пытаемся найти клиента с соответствующим идентификатором в репозитории с
помощью метода findById().

- Если клиент найден, его значения свойств (id, name, surname, address, phone)
устанавливаются в атрибуты объекта request. Таким образом, данные о клиенте будут доступны в JSP-странице
для редактирования.

- Если происходит исключение, оно перехватывается и выводится в стандартный вывод через метод e.printStackTrace().
В конце метода используется объект requestDispatcher для перенаправления запроса на JSP-страницу "/view/editClient.jsp",
где будет отображена форма для редактирования клиента с предзаполненными данными из базы данных.
Таким образом, данный код реализует обработку GET-запроса по адресу "/editClient". Он получает данные о клиенте из
базы данных, передает их в JSP-страницу для редактирования и отображает эту страницу пользователю.

Затем определен метод `doPost()`, который используется для обработки POST-запросов. Метод переопределен от класса
`HttpServlet`.
В начале метода создается экземпляр `ConnectionBuilder` для установления соединения с базой данных.
Затем создается экземпляр `ClientRepository`, используя установленное соединение.
В блоке `try-with-resources` получается соединение с базой данных. Далее происходит следующее:

- Создается новый объект `Client` и его свойства (id, name, surname, address, phone) устанавливаются на основе значений,
 полученных из запроса через метод `request.getParameter()`.
- Вызывается метод `update()` у репозитория `ClientRepository` для обновления данных в базе данных на основе переданного
 объекта `Client`.
- В случае возникновения исключения, оно перехватывается и выводится в стандартный вывод через метод `e.printStackTrace
()`.

Таким образом, данный код реализует обработку POST-запроса, при котором выполняется обновление данных объекта `Client`
 в базе данных на основе переданных значений из запроса. Если происходит исключение, оно будет перехвачено и отображено
  в стандартном выводе.

*/
@WebServlet("/editClient")
public class ClientsEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        ClientRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new ClientRepositoryImpl(connection);
            Optional<Client> oldOptClient = repository.findById(Integer.valueOf(request.getParameter("id")));
            if (oldOptClient.isPresent()) {
                Client oldClient = oldOptClient.get();
                request.setAttribute("id", oldClient.getId());
                request.setAttribute("name", oldClient.getName());
                request.setAttribute("surname", oldClient.getSurname());
                request.setAttribute("address", oldClient.getAddress());
                request.setAttribute("phone", oldClient.getPhone());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/editClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        ClientRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new ClientRepositoryImpl(connection);
            Client clientForUpdate = new Client();
            clientForUpdate.setId(Integer.valueOf(request.getParameter("id")));
            clientForUpdate.setName(request.getParameter("name"));
            clientForUpdate.setSurname(request.getParameter("surname"));
            clientForUpdate.setAddress(request.getParameter("address"));
            clientForUpdate.setPhone(request.getParameter("phone"));
            repository.update(clientForUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/clients");
    }
}
