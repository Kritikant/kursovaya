package com.example.order_record.controller.menu;/**/

import com.example.order_record.config.ConnectionBuilder;/**/
import com.example.order_record.config.ConnectionBuilderImpl;/**/
import com.example.order_record.model.Menu;/**/
import com.example.order_record.repository.MenuRepository;/**/
import com.example.order_record.repository.MenuRepositoryImpl;/**/
import jakarta.servlet.annotation.WebServlet;/**/
import jakarta.servlet.http.HttpServlet;/**/
import jakarta.servlet.http.HttpServletRequest;/**/
import jakarta.servlet.http.HttpServletResponse;/**/

import java.io.IOException;/**/
import java.sql.Connection;/**/
import java.util.Optional;/**/

/*В данном коде определен сервлет с аннотацией @WebServlet и URL-шаблоном "/deleteMenu". Сервлет наследуется от
класса HttpServlet, что позволяет ему обрабатывать HTTP-запросы.

В методе doGet() происходит обработка GET-запросов. Вначале создается экземпляр ConnectionBuilder для установления
соединения с базой данных.

Затем создается экземпляр MenuRepository, используя установленное соединение
В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:

- Получаем параметр "id" из запроса и пытаемся найти объект Menu с соответствующим идентификатором в репозитории с
помощью метода findById().
- Если объект Menu найден, используя метод ifPresent(), вызываем метод deleteById() для удаления объекта Menu из
базы данных.
- Если происходит исключение, оно перехватывается и выводится в стандартный вывод через метод e.printStackTrace().

В конце метода используется метод sendRedirect() с указанием пути "/menu". Это перенаправляет пользователя на
страницу с информацией о меню после выполнения удаления.

Таким образом, данный код реализует обработку GET-запроса по адресу "/deleteMenu". Он получает идентификатор Menu
из параметра запроса, удаляет соответствующий объект Menu из базы данных через репозиторий и выполняет
перенаправление на GET-запрос для обновления страницы с информацией о меню.*/

@WebServlet("/deleteMenu")
public class MenuDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        MenuRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new MenuRepositoryImpl(connection);
            Optional<Menu> menuForDelete = repository.findById(Integer.valueOf(request.getParameter("id")));
            menuForDelete.ifPresent(menu -> repository.deleteById(menu.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/menu");
    }

}
