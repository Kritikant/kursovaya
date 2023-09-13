<%@ page language="java" contentType="text/html"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Редактирование данных блюда</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/jspf/header.jsp"/>
<div id="main">
    <section>
        <article>
            <h3>Редактирование данных блюда</h3>
            <div class="text-article">
                <form method="POST" action="">
                    <div class="mb-3 row">
                        <label for="id" class="col-sm-4 col-form-label">Код блюда</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" readonly
                                   value="${id}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="foodName" class="col-sm-4 col-form-label">Название</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="staticFoodName"
                                   name=foodName value="${foodName}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="weight" class="col-sm-4 col-form-label">Вес</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="staticWeight"
                                   name="weight" value="${weight}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="price" class="col-sm-4 col-form-label">Цена</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="staticPrice"
                                   name="price" value="${price}"/>
                        </div>
                    </div>
                    <p>
                        <button type="submit" class="btn btn-primary">Редактировать</button>
                        <a role="button" class="btn btn-secondary">Отменить/Возврат</a>
                    </p>
                </form>
            </div>
        </article>
    </section>
</div>
<jsp:include page="/jspf/footer.jsp"/>
</body>
</html>
