package com.example.order_record.controller.order;/**/

import com.example.order_record.config.ConnectionBuilder;/**/
import com.example.order_record.config.ConnectionBuilderImpl;/**/
import com.example.order_record.model.Order;/**/
import com.example.order_record.model.OrderDetail;/**/
import com.example.order_record.repository.*;/**/
import jakarta.servlet.annotation.WebServlet;/**/
import jakarta.servlet.http.HttpServlet;/**/
import jakarta.servlet.http.HttpServletRequest;/**/
import jakarta.servlet.http.HttpServletResponse;/**/

import java.io.IOException;/**/
import java.sql.Connection;/**/
import java.util.Optional;/**/

/* данном коде определен сервлет с аннотацией @WebServlet и URL-шаблоном "/deleteOrder". Сервлет наследуется от класса
HttpServlet, что позволяет обрабатывать HTTP-запросы.

Также в коде определена константа serialVersionUID, которая используется для контроля версии сериализованного объекта.
Затем создаем экземпляр ConnectionBuilder для установления соединения с базой данных.
Затем создаются экземпляры OrderRepository и OrderDetailRepository, используя установленное соединение.

В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:
- Получаем параметр "id" из запроса и пытаемся найти объект Order с соответствующим идентификатором в репозитории OrderRepository
с помощью метода findById().
- Получаем параметр "id" из запроса и пытаемся найти объект OrderDetail с соответствующим идентификатором в репозитории
OrderDetailRepository с помощью метода findByOrderId().
- Если объект OrderDetail найден, используя метод ifPresent(), вызываем метод deleteById() у репозитория
OrderDetailRepository для удаления объекта OrderDetail из базы данных.
- Если объект Order найден, используя метод ifPresent(), вызываем метод deleteById() у репозитория OrderRepository для
удаления объекта Order из базы данных.
- Если при выполнении кода возникает исключение, оно перехватывается и выводится в стандартный вывод через метод
e.printStackTrace().

Таким образом, данный код реализует обработку GET-запроса, при котором выполняется поиск объектов Order и OrderDetail
по их идентификаторам в соответствующих репозиториях. Если объекты найдены, они удаляются из базы данных. Если
происходит исключение, оно будет перехвачено и отображено в стандартном выводе, после чего пишется
"response.sendRedirect("/order");" для выполнения перенаправления на страницу "/order".
*/

@WebServlet("/deleteOrder")
public class OrderDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        OrderRepository orderRepository;
        OrderDetailRepository orderDetailRepository;
        try (Connection connection = connectionBuilder.getConnection()) {
            orderRepository = new OrderRepositoryImpl(connection);
            orderDetailRepository = new OrderDetailRepositoryImpl(connection);
            Optional<Order> orderForDelete = orderRepository.findById(Integer.valueOf(request.getParameter("id")));
            Optional<OrderDetail> orderDetailForDelete = orderDetailRepository.findByOrderId(Integer.valueOf(request.getParameter("id")));
            orderDetailForDelete.ifPresent(orderDetail -> orderDetailRepository.deleteById(orderDetail.getId()));
            orderForDelete.ifPresent(order -> orderRepository.deleteById(order.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/order");
    }

}
