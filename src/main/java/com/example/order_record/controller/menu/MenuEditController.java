package com.example.order_record.controller.menu;/**/

import com.example.order_record.config.ConnectionBuilder;/**/
import com.example.order_record.config.ConnectionBuilderImpl;/**/
import com.example.order_record.model.Menu;/**/
import com.example.order_record.repository.MenuRepository;/**/
import com.example.order_record.repository.MenuRepositoryImpl;/**/
import jakarta.servlet.ServletException;/**/
import jakarta.servlet.annotation.WebServlet;/**/
import jakarta.servlet.http.HttpServlet;/**/
import jakarta.servlet.http.HttpServletRequest;/**/
import jakarta.servlet.http.HttpServletResponse;/**/

import java.io.IOException;/**/
import java.sql.Connection;/**/
import java.util.Optional;/**/

/*В данном коде определен сервлет с аннотацией @WebServlet и URL-шаблоном "/editMenu". Сервлет наследуется от класса

HttpServlet, что позволяет ему обрабатывать HTTP-запросы. В данном коде также определена константа serialVersionUID,
спользуемая для контроля версии сериализованного объекта. Затем опредндяем метод doGet(), который используется

для обработки GET-запросов. Метод переопределен от класса HttpServlet.
Вначале метода создается экземпляр ConnectionBuilder для установления соединения с базой данных.

Затем создается экземпляр MenuRepository, используя установленное соединение.

В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:

- Получаем параметр "id" из запроса и пытаемся найти объект Menu с соответствующим идентификатором в репозитории.
- Если объект Menu найден, используя метод ifPresent(), устанавливаем значения его свойств (id, foodName, weight, price)
в атрибуты объекта request. Это делает информацию об объекте Menu доступной для отображения или использования в
JSP-странице или других частях приложения.
- Если происходит исключение, оно перехватывается и выводится в стандартный вывод через метод e.printStackTrace().

Таким образом, данный код реализует обработку GET-запроса, при котором выполняется поиск объекта Menu по его
идентификатору в репозитории. Если объект найден, его свойства устанавливаются в атрибуты запроса для использования
в отображении или других частях приложения. Если происходит исключение, оно будет перехвачено и отображено в
стандартном выводе.

После этого определяем метод doPost(), который используется для обработки POST-запросов.
Метод переопределен от класса HttpServlet.

В начале метода создается экземпляр ConnectionBuilder для установления соединения с базой данных.
Затем создается экземпляр MenuRepository, используя установленное соединение.

В блоке try-with-resources получается соединение с базой данных. Далее происходит следующее:

- Создается новый объект Menu и устанавливаются его свойства (id, foodName, weight и price) на основе значений,
полученных из запроса через метод request.getParameter().
- Вызывается метод update() у репозитория MenuRepository для обновления данных в базе данных на основе переданного
объекта Menu.
- В случае возникновения исключения, оно перехватывается и выводится в стандартный вывод через
метод e.printStackTrace().

После обновления данных редирект на страницу "/menu" осуществляется с помощью метода sendRedirect() у объекта response.

Таким образом, данный код реализует обработку POST-запроса, при котором выполняется обновление данных объекта Menu в
базе данных на основе переданных значений из запроса. После обновления данных происходит перенаправление на
страницу "/menu".
*/
@WebServlet("/editMenu")
public class MenuEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        MenuRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new MenuRepositoryImpl(connection);
            Optional<Menu> oldOptMenu = repository.findById(Integer.valueOf(request.getParameter("id")));
            if (oldOptMenu.isPresent()) {
                Menu oldMenu = oldOptMenu.get();
                request.setAttribute("id", oldMenu.getId());
                request.setAttribute("foodName", oldMenu.getFoodName());
                request.setAttribute("weight", oldMenu.getWeight());
                request.setAttribute("price", oldMenu.getPrice());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/editMenu.jsp").forward(request, response);
    }

    /**/
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        MenuRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new MenuRepositoryImpl(connection);
            Menu menuForUpdate = new Menu();
            menuForUpdate.setId(Integer.valueOf(request.getParameter("id")));
            menuForUpdate.setFoodName(request.getParameter("foodName"));
            menuForUpdate.setWeight(Integer.valueOf(request.getParameter("weight")));
            menuForUpdate.setPrice(Integer.valueOf(request.getParameter("price")));
            repository.update(menuForUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/menu");
    }

}
