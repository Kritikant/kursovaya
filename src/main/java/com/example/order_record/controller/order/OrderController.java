package com.example.order_record.controller.order;/**/

import com.example.order_record.config.ConnectionBuilder;/**/
import com.example.order_record.config.ConnectionBuilderImpl;/**/
import com.example.order_record.model.*;/**/
import com.example.order_record.repository.*;/**/
import jakarta.servlet.ServletException;/**/
import jakarta.servlet.annotation.WebServlet;/**/
import jakarta.servlet.http.HttpServlet;/**/
import jakarta.servlet.http.HttpServletRequest;/**/
import jakarta.servlet.http.HttpServletResponse;/**/

import java.io.IOException;/**/
import java.sql.Connection;/**/
import java.time.LocalDateTime;/**/
import java.util.List;/**/

/*В данном коде определен сервлет с аннотацией @WebServlet и URL-шаблоном "/order". Сервлет наследуется от
класса HttpServlet, что позволяет ему обрабатывать HTTP-запросы.

Также в коде определена константа serialVersionUID, которая используется для контроля версии сериализованного объекта.

Дальше определяем метод doGet(), который используется для обработки GET-запросов. Метод переопределен от класса
HttpServlet.
Вначале метода создаются экземпляры ConnectionBuilder для установления соединения с базой данных и репозиториев,
которые используют это соединение для взаимодействия с базой данных.

Точно также создаются экземпляры OrderRepository, ClientRepository, DeliveryManRepository, MenuRepository,
OrderDetailRepository.

Далее получается соединение с базой данных с использованием ConnectionBuilder.
- Создаются экземпляры репозиториев OrderRepository, ClientRepository, DeliveryManRepository, MenuRepository,
OrderDetailRepository с использованием полученного соединения.
- Вызываются методы findAll() у каждого репозитория для получения всех объектов из базы данных.
- Полученные списки объектов Order, Client, DeliveryMan, Menu и OrderDetail сохраняются в соответствующих переменных.
- Затем, через методы setAttribute(), устанавливаются атрибуты в объекте request для каждого списка объектов.
- В случае возникновения исключения, оно перехватывается и выводится в стандартный вывод через метод e.printStackTrace().

Таким образом, в данном коде происходит получение всех объектов заказа, клиента, доставщика, меню и деталей заказа из
базы данных через соответствующие репозитории. Полученные списки объектов устанавливаются в атрибуты объекта request
для использования в JSP-странице или других частях приложения.

Затем посредством кода
"request.getRequestDispatcher("/view/order.jsp").forward(request, response);" перенаправляем запрос на JSP-страницу
`/view/order.jsp`, где будет происходить отображение и обработка данных запроса, которые были установлены в атрибуты
объекта request.

*/
@WebServlet("/order")
public class OrderController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        OrderRepository orderRepository;
        ClientRepository clientRepository;
        DeliveryManRepository deliveryManRepository;
        MenuRepository menuRepository;
        OrderDetailRepository orderDetailRepository;
        try (Connection connection = connectionBuilder.getConnection()) {
            orderRepository = new OrderRepositoryImpl(connection);
            clientRepository = new ClientRepositoryImpl(connection);
            deliveryManRepository = new DeliveryManRepositoryImpl(connection);
            menuRepository = new MenuRepositoryImpl(connection);
            orderDetailRepository = new OrderDetailRepositoryImpl(connection);
            List<Order> orderList = orderRepository.findAll();
            List<Client> clientList = clientRepository.findAll();
            List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
            List<Menu> menuList = menuRepository.findAll();
            List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
            request.setAttribute("order", orderList);
            request.setAttribute("client", clientList);
            request.setAttribute("deliveryMan", deliveryManList);
            request.setAttribute("menu", menuList);
            request.setAttribute("orderDetail", orderDetailList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/order.jsp").forward(request, response);
    }

    /**/
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        OrderRepository orderRepository;
        OrderDetailRepository orderDetailRepository;
        MenuRepository menuRepository;
        try (Connection connection = connectionBuilder.getConnection()) {
            orderRepository = new OrderRepositoryImpl(connection);
            orderDetailRepository = new OrderDetailRepositoryImpl(connection);
            menuRepository = new MenuRepositoryImpl(connection);
            Order newOrder = new Order();
            newOrder.setClientId(Integer.valueOf(request.getParameter("clientId")));
            newOrder.setDeliveryManId(Integer.valueOf(request.getParameter("deliveryManId")));
            newOrder.setOrderDate(LocalDateTime.now().withSecond(0).withNano(0));
            newOrder.setWhenDeliver(LocalDateTime.now().plusHours(1).withSecond(0).withNano(0));
            newOrder.setNote(request.getParameter("note"));
            Order savedOrder = orderRepository.save(newOrder);

            OrderDetail newOrderDetail = new OrderDetail();
            newOrderDetail.setOrderId(savedOrder.getId());
            String foodName = request.getParameter("foodName");
            Menu findMenu = menuRepository.findByFoodName(foodName).get();
            newOrderDetail.setMenuId(findMenu.getId());
            newOrderDetail.setServingCount(Integer.valueOf(request.getParameter("servingCount")));
            orderDetailRepository.save(newOrderDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }

}
