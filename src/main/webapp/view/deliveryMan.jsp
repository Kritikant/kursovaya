<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>Все курьеры</title>
</head>
<body>
<div id="main">
    <jsp:include page="/jspf/header.jsp"/>
    <div class="sections">
        <section class="list-section">
            <h3>Список курьеров</h3>
            <table class="entity-list">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилмя</th>
                    <th scope="col">Номер телефона</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="deliveryMan" items="${deliveryMan}">
                    <tr class="Client$">
                        <td>${deliveryMan.id}</td>
                        <td>${deliveryMan.name}</td>
                        <td>${deliveryMan.surname}</td>
                        <td>${deliveryMan.phone}</td>
                        <td>
                            <a href="/editDeliveryMan?id=${deliveryMan.id}">Ред.</a>
                        </td>
                        <td>
                            <a href='/deleteDeliveryMan?id=${deliveryMan.id}&redirectPage=deliveryMan'
                               onclick="return confirm('Вы уверены, что хотите это сделать?')" style="color: red">X</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <section class="add-section">
            <article>
                <h3>Добавить нового курьера</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-3 col-form-label">Имя</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="staticFirstname"
                                       name="name"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="surname" class="col-sm-3 col-form-label">Фамилия</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="staticLastname"
                                       name="surname"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="phone" class="col-sm-3 col-form-label">Телефон</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="staticPhone"
                                       name="phone"/>
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
