package com.example.order_record.controller.delivery;/**/

import com.example.order_record.config.ConnectionBuilder;/**/
import com.example.order_record.config.ConnectionBuilderImpl;/**/
import com.example.order_record.model.DeliveryMan;
import com.example.order_record.repository.DeliveryManRepository;/**/
import com.example.order_record.repository.DeliveryManRepositoryImpl;/**/
import jakarta.servlet.annotation.WebServlet;/**/
import jakarta.servlet.http.HttpServlet;/**/
import jakarta.servlet.http.HttpServletRequest;/**/
import jakarta.servlet.http.HttpServletResponse;/**/

import java.io.IOException;/**/
import java.sql.Connection;/**/
import java.util.Optional;/**/

/*В данном коде определен сервлет с аннотацией @WebServlet и URL-шаблоном "/deleteDeliveryMan". Сервлет наследуется
от класса HttpServlet, что позволяет обрабатывать HTTP-запросы.
В методе doGet() происходит обработка GET-запросов. Вначале создается экземпляр ConnectionBuilder для
установления соединения с базой данных. Затем создается экземпляр DeliveryManRepository, используя установленное
соединение.

В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:

- Получаем параметр "id" из запроса и пытаемся найти объект DeliveryMan с соответствующим идентификатором в
репозитории с помощью метода findById().
- Если объект DeliveryMan найден, используя метод ifPresent(), вызываем метод deleteById() для удаления объекта
DeliveryMan из базы данных.
- Если происходит исключение, оно перехватывается и выводится в стандартный вывод через метод e.printStackTrace().

В конце метода используется метод sendRedirect() с указанием пути "/deliveryMan". Это перенаправляет пользователя
на страницу с информацией о доставщиках после выполнения удаления.

Таким образом, данный код реализует обработку GET-запроса по адресу "/deleteDeliveryMan".
Он получает идентификатор DeliveryMan из параметра запроса, удаляет соответствующий объект DeliveryMan из
базы данных через репозиторий и выполняет перенаправление на GET-запрос для обновления страницы с информацией о
доставщиках.*/

@WebServlet("/deleteDeliveryMan")
public class DeliveryManDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        DeliveryManRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new DeliveryManRepositoryImpl(connection);
            Optional<DeliveryMan> deliveryManForDelete = repository.findById(Integer.valueOf(request.getParameter("id")));
            deliveryManForDelete.ifPresent(deliveryMan -> repository.deleteById(deliveryMan.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/deliveryMan");
    }

}
