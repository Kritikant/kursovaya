<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>Все заказы</title>
</head>
<body>
<div id="main">
    <jsp:include page="/jspf/header.jsp"/>
    <div class="sections">
        <section class="list-section">
            <h3>Список заказов</h3>
            <table class="entity-list">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">ID клиента</th>
                    <th scope="col">ID курьера</th>
                    <th scope="col">Дата заказа</th>
                    <th scope="col">Примерное время доставки</th>
                    <th scope="col">Примечание</th>
                    <th scope="col">Удалить</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${order}">
                    <tr class="Client$">
                        <td>${order.id}</td>
                        <td>${order.clientId}</td>
                        <td>${order.deliveryManId}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.whenDeliver}</td>
                        <td>${order.note}</td>
                        <td>
                            <a href='/deleteOrder?id=${order.id}&redirectPage=order'
                               onclick="return confirm('Вы уверены, что хотите это сделать?')" style="color: red">X</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <h3>Детали заказов</h3>
            <table class="entity-list">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">ID заказа</th>
                    <th scope="col">ID блюда</th>
                    <th scope="col">Количество персон</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="orderDetail" items="${orderDetail}">
                    <tr class="Client$">
                        <td>${orderDetail.id}</td>
                        <td>${orderDetail.orderId}</td>
                        <td>${orderDetail.menuId}</td>
                        <td>${orderDetail.servingCount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>

        <section class="add-section">
            <article>
                <h3>Добавить новый заказ</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <p class="input-form">
                            <label>ID клиента
                                <select name="clientId">
                                    <option disabled>Выберите ID клиента</option>
                                    <c:forEach var="client" items="${client}">
                                        <option>
                                                ${client.id}
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label>ID курьера
                                <select name="deliveryManId">
                                    <option disabled>Выберите ID курьера</option>
                                    <c:forEach var="deliveryMan" items="${deliveryMan}">
                                        <option>
                                                ${deliveryMan.id}
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                            <label>Название блюда
                                <select name="foodName">
                                    <option disabled>Выберите название блюда</option>
                                    <c:forEach var="menu" items="${menu}">
                                        <option>
                                                ${menu.foodName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                        <div class="mb-3 row">
                            <label for="servingCount" class="col-sm-4 col-form-label">Количество персон</label>
                            <div class="col-sm-8">
                                <input type="number" class="form-control" id="staticServingCount"
                                       name="servingCount"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="note" class="col-sm-4 col-form-label">Примечание</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="staticNote"
                                       name="note"/>
                            </div>
                        </div>
                        <p>
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </p>
                    </form>
                </div>
            </article>
        </section>
    </div>
    <jsp:include page="/jspf/footer.jsp"/>
</div>
</body>
<script>

</script>
</html>
