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
import java.util.Optional;/**/

/*В данном коде определен метод doGet(), который используется для обработки GET-запросов. Метод переопределен от
класса HttpServlet. В начале метода создается экземпляр ConnectionBuilder для установления соединения с базой данных.
Затем создается экземпляр DeliveryManRepository, используя установленное соединение.

Далее, получается соединение с базой данных. Происходит следующее:
- Получаем параметр "id" из запроса и пытаемся найти объект DeliveryMan с соответствующим идентификатором в
репозитории с помощью метода findById().
- Если объект DeliveryMan найден, устанавливаются значения его свойств (id, name, surname, phone) в атрибуты
объекта request.
- Если происходит исключение, оно перехватывается и выводится в стандартный вывод через метод
e.printStackTrace().

Этот код реализует обработку GET-запроса, при котором выполняется поиск объекта DeliveryMan по его идентификатору
в репозитории. Если объект найден, его свойства устанавливаются в атрибуты запроса, чтобы быть доступными для
отображения в представлении или в других частях приложения. Если происходит исключение, оно будет перехвачено и
отображено в стандартном выводе.*/
@WebServlet("/editDeliveryMan")
public class DeliveryManEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        DeliveryManRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new DeliveryManRepositoryImpl(connection);
            Optional<DeliveryMan> oldOptDeliveryMan = repository.findById(Integer.valueOf(request.getParameter("id")));
            if (oldOptDeliveryMan.isPresent()) {
                DeliveryMan oldDeliveryMan = oldOptDeliveryMan.get();
                request.setAttribute("id", oldDeliveryMan.getId());
                request.setAttribute("name", oldDeliveryMan.getName());
                request.setAttribute("surname", oldDeliveryMan.getSurname());
                request.setAttribute("phone", oldDeliveryMan.getPhone());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/editDeliveryMan.jsp").forward(request, response);
    }
    /*Далее метод doPost(), который используется для обработки POST-запросов. Метод переопределен от класса
HttpServlet.

Вначале метода создается экземпляр ConnectionBuilder для установления соединения с базой данных.
Затем создается экземпляр DeliveryManRepository, используя установленное соединение.
В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:

- Создается новый объект DeliveryMan (экземпляр класса).
- Значение свойства id устанавливается на основе параметра запроса "id" с приведением типа в Integer.
- Значения остальных свойств объекта DeliveryMan (name, surname, phone) устанавливаются на основе соответствующих
параметров запроса.
- Вызывается метод update() репозитория для обновления объекта DeliveryMan в базе данных.
- Если при выполнении кода возникает исключение, оно перехватывается и выводится в стандартный вывод с помощью метода
e.printStackTrace().

В конце метода используется метод sendRedirect() с указанием пути "/deliveryMan". Это перенаправляет пользователя на
страницу с информацией о доставщиках после выполнения обновления.

Таким образом, данный код реализует обработку POST-запроса, где данные из параметров запроса используются для
обновления объекта DeliveryMan в базе данных с использованием репозитория.
Затем выполняется перенаправление на страницу с информацией о доставщиках.
*/
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        DeliveryManRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new DeliveryManRepositoryImpl(connection);
            DeliveryMan deliveryManForUpdate = new DeliveryMan();
            deliveryManForUpdate.setId(Integer.valueOf(request.getParameter("id")));
            deliveryManForUpdate.setName(request.getParameter("name"));
            deliveryManForUpdate.setSurname(request.getParameter("surname"));
            deliveryManForUpdate.setPhone(request.getParameter("phone"));
            repository.update(deliveryManForUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/deliveryMan");
    }

}
