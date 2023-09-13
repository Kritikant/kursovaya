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
import java.util.List;/**/

/*В данном коде определен сервлет с аннотацией @WebServlet и URL-шаблоном "/menu". Сервлет наследуется от класса

HttpServlet, что позволяет ему обрабатывать HTTP-запросы. В методе doGet() происходит обработка GET-запросов.
Вначале создается экземпляр ConnectionBuilder для установления соединения с базой данных. Затем создается экземпляр
MenuRepository, используя установленное соединение. В блоке try-with-resources получается соединение с базой данных.
Далее происходит следующее:

- Вызывается метод findAll() репозитория для получения списка всех объектов Menu из базы данных.
- Полученный список menuList устанавливается в атрибут "menu" объекта request, чтобы быть доступным в JSP-странице
для отображения.
- Если при выполнении кода возникает исключение, оно перехватывается и выводится в стандартный вывод через метод
e.printStackTrace().

Далее определяем метод doPost(), который используется для обработки POST-запросов. Метод переопределен от класса
HttpServlet. Вначале метода создается экземпляр ConnectionBuilder для установления соединения с базой данных.
Затем создается экземпляр MenuRepository, используя установленное соединение.
В блоке try-with-resources получается соединение с базой данных.

Далее происходит следующее:

- Создается новый объект Menu (экземпляр класса), и его свойства (foodName, weight, price) устанавливаются на
основе параметров запроса (request.getParameter()).
- Вызывается метод save() репозитория для сохранения нового объекта Menu в базе данных.
- Если при выполнении кода возникает исключение, оно перехватывается и выводится в стандартный вывод с помощью метода
 e.printStackTrace().

В конце метода вызывается метод doGet(), чтобы выполнить перенаправление на GET-запрос. В результате будет
выполнена обработка GET-запроса для обновления списка объектов Menu.
Таким образом, данный код реализует обработку POST-запроса, где данные из параметров запроса используются для
создания нового объекта Menu и сохранения его в базе данных с использованием репозитория.
Затем выполняется перенаправление на GET-запрос для обновления списка объектов Menu.

Затем реализуем обработку POST-запроса, где данные из параметров запроса используются для создания нового объекта
Menu и сохранения его в базе данных с использованием репозитория. Затем выполняется перенаправление на GET-запрос
для обновления информации о меню.
*/
@WebServlet("/menu")
public class MenuController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        MenuRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new MenuRepositoryImpl(connection);
            List<Menu> menuList = repository.findAll();
            request.setAttribute("menu", menuList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        MenuRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new MenuRepositoryImpl(connection);
            Menu newMenu = new Menu();
            newMenu.setFoodName(request.getParameter("foodName"));
            newMenu.setWeight(Integer.valueOf(request.getParameter("weight")));
            newMenu.setPrice(Integer.valueOf(request.getParameter("price")));
            repository.save(newMenu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }

}
