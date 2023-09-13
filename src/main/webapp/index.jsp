<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="css/style.css">
    <meta http-equiv="Content-Type" content="text/html"
          charset="UTF-8">
    <title>Главная страница</title>
</head>
<body>
<jsp:include page="jspf/header.jsp" />
<div id="main">
    <h2>Функции системы</h2>
    <ul>
        <li><a href="/clients">Клиенты</a>
        <li><a href="/deliveryMan">Курьеры</a>
        <li><a href="/menu">Меню</a>
        <li><a href="/order">Заказы</a>
    </ul>
</div>
<jsp:include page="jspf/footer.jsp" />
</body>
</html>