package com.example.order_record.controller.client;

import com.example.order_record.config.ConnectionBuilder; /**/
import com.example.order_record.config.ConnectionBuilderImpl; /**/
import com.example.order_record.model.Client; /**/
import com.example.order_record.repository.ClientRepository; /**/
import com.example.order_record.repository.ClientRepositoryImpl; /**/
import jakarta.servlet.annotation.WebServlet; /**/
import jakarta.servlet.http.HttpServlet; /**/
import jakarta.servlet.http.HttpServletRequest; /**/
import jakarta.servlet.http.HttpServletResponse; /**/

import java.io.IOException; /**/
import java.sql.Connection; /**/
import java.util.Optional; /**/

/*Веб-сайт на платформе Java EE обычно включает в себя несколько компонентов, которые взаимодействуют друг с другом для
отображения данных и сохранения введенной информации в базе данных. Ниже представлена типичная схема взаимодействия
компонентов веб-сайта на Java EE:

1. Браузер клиента: Пользователь взаимодействует с веб-сайтом через браузер, отправляя запросы и получая ответы.
2. HTTP-сервер (например, Apache Tomcat): Сервер принимает запросы от браузера и передает их соответствующим компонентам
веб-приложения для обработки.
3. Servlet: Сервлеты являются основной единицей обработки запросов и формирования ответов на стороне сервера в Java EE.
Они принимают HTTP-запросы от сервера и осуществляют логику обработки запроса, включая получение данных из базы данных,
обработку введенных данных и создание ответа для отправки обратно на клиентскую сторону.
4. JavaServer Pages (JSP): JSP - это технология, которая позволяет разработчикам включать встроенный Java-код в
HTML-страницы. JSP страницы используются для отображения данных, полученных из сервлетов, и формирования ответа на
стороне сервера.
5. База данных: Данные, которые нужно сохранить или получить, хранятся в базе данных. Веб-приложение может использовать
Java Database Connectivity API (JDBC) или ORM-фреймворки (например, Hibernate) для взаимодействия с базой данных и
выполнения операций CRUD (создание, чтение, обновление, удаление) с данными.

6. Java-компоненты: Java-компоненты, такие как классы моделей, репозитории или сервисы, используются для управления
бизнес-логикой и доступом к данным. Они могут выполнять операции, такие как сохранение или получение данных из базы
данных, обработку введенных данных и предоставление данных для отображения на JSP страницах или для других компонентов.
В общем, пользовательский ввод передается сервлетам, которые обрабатывают запросы, вызывая соответствующие
Java-компоненты для выполнения операций с базой данных или другими операциями по обработке данных. Затем данные могут
быть переданы на JSP страницу для отображения результата на стороне клиента.

Для внесения изменений на сайт, например, сохранения новых данных, пользовательский ввод обрабатывается на сервере,
где соответствующие Java-компоненты обновляют базу данных с помощью запросов к базе данных. После обновления данных,
ответ отправляется обратно на клиентскую сторону, и, при необходимости, результат отображается на странице.
Все компоненты взаимодействуют через HTTP протокол, который обеспечивает передачу запросов и ответов, и
сервлет контейнер, который управляет жизненным циклом сервлетов и обрабатывает входящие запросы и ответы.

***********************************************************************************************************************

Класс ClientDeleteController наследуется от HttpServlet, что позволяет ему быть сервлетом веб-приложения и
обрабатывать HTTP-запросы. В методе doGet() происходит обработка GET-запросов.
    Создается экземпляр ConnectionBuilder для установления соединения с базой данных.
    Далее создается экземпляр ClientRepository, используя соединение.
В блоке try-with-resources получается соединение с базой данных и выполняются следующие действия:

- Получаем параметр "id" из запроса и пытаемся найти клиента с соответствующим идентификатором в репозитории;

- Если клиент найден, используя метод findById(), вызываем метод deleteById() для удаления клиента из базы данных.

- Если происходит исключение, оно перехватывается и печатается стек вызовов через метод e.printStackTrace().

В конце метода вызывается метод sendRedirect() с указанием пути "/clients". Это перенаправляет пользователя на
страницу со списком клиентов после выполнения удаления. Код реализует удаление клиента из базы данных на основе
значения параметра "id", полученного из GET-запроса, и перенаправляет пользователя на другую страницу после выполнения
удаления.
*/
@WebServlet("/deleteClient")
public class ClientDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        ConnectionBuilder connectionBuilder = new ConnectionBuilderImpl();
        ClientRepository repository;
        try (Connection connection = connectionBuilder.getConnection()) {
            repository = new ClientRepositoryImpl(connection);
            Optional<Client> clientForDelete = repository.findById(Integer.valueOf(request.getParameter("id")));
            clientForDelete.ifPresent(client -> repository.deleteById(client.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/clients");
    }

}
