<%@ page language="java" contentType="text/html"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Редактирование данных клиента</title>
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
            <h3>Редактирование данных клиента</h3>
            <div class="text-article">
                <form method="POST" action="">
                    <div class="mb-3 row">
                        <label for="id" class="col-sm-4 col-form-label">Код клиента</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" readonly
                                   value="${id}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="name" class="col-sm-4 col-form-label">Имя</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="staticName"
                                   name=name value="${name}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="surname" class="col-sm-4 col-form-label">Фамилия</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="staticSurname"
                                   name="surname" value="${surname}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="address" class="col-sm-4 col-form-label">Адрес</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="staticAddress"
                                   name="address" value="${address}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="phone" class="col-sm-4 col-form-label">Номер телефона</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="staticPhone"
                                   name="phone" value="${phone}"/>
                        </div>
                        <p>
                            <button type="submit" class="btn btn-primary">Редактировать</button>
                            <a role="button" class="btn btn-secondary">Отменить/Возврат</a>
                        </p>
                    </div>
                </form>
            </div>
        </article>
    </section>
</div>
<jsp:include page="/jspf/footer.jsp"/>
</body>
</html>
