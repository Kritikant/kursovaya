<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>Меню</title>
</head>
<body>
<div id="main">
    <jsp:include page="/jspf/header.jsp"/>
    <div class="sections">
        <section class="list-section">
            <h3>Список блюд</h3>
            <table class="entity-list">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Название</th>
                    <th scope="col">Вес</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="menu" items="${menu}">
                    <tr class="Client$">
                        <td>${menu.id}</td>
                        <td>${menu.foodName}</td>
                        <td>${menu.weight}</td>
                        <td>${menu.price}</td>
                        <td>
                            <a href="/editMenu?id=${menu.id}">Ред.</a>
                        </td>
                        <td>
                            <a href='/deleteMenu?id=${menu.id}&redirectPage=menu'
                               onclick="return confirm('Вы уверены, что хотите это сделать?')" style="color: red">X</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <section class="add-section">
            <article>
                <h3>Добавить новое блюдо</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <div class="mb-3 row">
                            <label for="foodName" class="col-sm-3 col-form-label">Название</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="staticFoodName"
                                       name="foodName"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="weight" class="col-sm-3 col-form-label">Вес</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="staticWeight"
                                       name="weight"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="price" class="col-sm-3 col-form-label">Цена</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="staticPrice"
                                       name="price"/>
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
