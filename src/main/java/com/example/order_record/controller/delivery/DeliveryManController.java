package com.example.order_record.controller.delivery;/**/

import com.example.order_record.config.ConnectionBuilder;/**/
import com.example.order_record.config.ConnectionBuilderImpl;/**/
import com.example.order_record.model.DeliveryMan;/**/
import com.example.order_record.repository.DeliveryManRepository;/**/
import com.example.order_record.repository.DeliveryManRepositoryImpl;/**/
import jakarta.servlet.ServletException;/**/
import jakarta.servlet.annotation.WebServlet;/**/
import jakarta.servlet.http.HttpServlet;/**/
import jakarta.servlet.http.HttpServletRequest;/**/
import jakarta.servlet.http.HttpServletResponse;/**/

import java.io.IOException;/**/
import java.sql.Connection;/**/
import java.util.List;/**/

/*В методе doGet() происходит обработка GET-запросов. Вначале создается экземпляр ConnectionBuilder для установления
соединения с базой данных. Затем создается экземпляр DeliveryManRepository, используя установленное соединение.

В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:

- Вызывается метод findAll() репозитория для получения списка всех объектов DeliveryMan из базы данных.
- Полученный список deliveryManList устанавливается в атрибут "deliveryMan" объекта request, чтобы быть доступным в
JSP-странице для отображения.
- Если при выполнении кода возникает исключение, оно перехватывается и выводится в стандартный вывод через метод
e.printStackTrace(). В конце метода используется объект requestDispatcher для
перенаправления запроса на JSP-страницу "/view/deliveryMan.jsp", где будет отображена информация о распределении.

Таким образом, данный код реализует обработку GET-запроса по адресу "/deliveryMan". Он получает информацию об
объектах DeliveryMan из базы данных с использованием репозитория и передает эту информацию в JSP-страницу для
отображения.

Затем Создается новый объект DeliveryMan (экземпляр класса), и его свойства (name, surname, phone)
устанавливаются на основе параметров запроса (request.getParameter()).
- Вызывается метод save() репозитория для сохранения нового объекта DeliveryMan в базе данных.
- Если при выполнении кода возникает исключение, оно перехватывается и выводится в стандартный вывод с помощью метода
e.printStackTrace().

В конце метода вызывается метод doGet() для выполнения перенаправления на GET-запрос. Таким образом, после сохранения
нового объекта DeliveryMan будет выполнен GET-запрос для обновления списка объектов DeliveryMan.
Этот код реализует обработку POST-запросов, где данные из параметров запроса используются для создания нового объекта
DeliveryMan и сохранения его в базе данных с использованием репозитория. Затем выполняется перенаправление на
GET-запрос для обновления списка объектов DeliveryMan.
*/


@WebServlet("/deliveryMan")
public class DeliveryManController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        DeliveryManRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new DeliveryManRepositoryImpl(connection);
            List<DeliveryMan> deliveryManList = repository.findAll();
            request.setAttribute("deliveryMan", deliveryManList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/deliveryMan.jsp").forward(request, response);
    }
    /*Далее определяем метод doPost(), который используется для обработки POST-запросов. Метод переопределен
от класса HttpServlet.

Вначале метода создается экземпляр ConnectionBuilder для установления соединения с базой данных.

Затем создается экземпляр DeliveryManRepository, используя установленное соединение.

В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:

- Создается новый объект DeliveryMan (экземпляр класса).
- Значение свойства id устанавливается на основе параметра запроса "id" с приведением типа в Integer.
- Значения остальных свойств объекта DeliveryMan (name, surname, phone) устанавливаются на основе соответствующих
араметров запроса.
- Вызывается метод update() репозитория для обновления объекта DeliveryMan в базе данных.
- Если при выполнении кода возникает исключение, оно перехватывается и выводится в стандартный вывод с помощью
метода e.printStackTrace().

В конце метода используется метод sendRedirect() с указанием пути "/deliveryMan". Это перенаправляет пользователя на
страницу с информацией о доставщиках после выполнения обновления.

Таким образом, данный код реализует обработку POST-запроса, где данные из параметров запроса используются для
обновления объекта DeliveryMan в базе данных с использованием репозитория. Затем выполняется перенаправление
на страницу с информацией о доставщиках.*/

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        DeliveryManRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new DeliveryManRepositoryImpl(connection);
            DeliveryMan newDeliveryMan = new DeliveryMan();
            newDeliveryMan.setName(request.getParameter("name"));
            newDeliveryMan.setSurname(request.getParameter("surname"));
            newDeliveryMan.setPhone(request.getParameter("phone"));
            repository.save(newDeliveryMan);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }

}
