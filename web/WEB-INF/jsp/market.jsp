<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <style><%@include file="/WEB-INF/css/normalize.css"%></style>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <style><%@include file="/WEB-INF/css/table-style.css"%></style>

</head>
<body>
<!--page-wrapper-->
<div class="page-wrapper">
    <!--HEADER-->
    <%@include file="header.jsp"%>
    <!--CONTENT-->
    <div class="content-wrapper">
        <!--aside-->
        <aside class="aside"><a href="#">О нас</a><a href="#">Гарантия и сервис</a><a href="#">Сотрудничество с нами</a></aside>
        <!--first-section-->
        <div class="first-section">

            <div id="content">
                <table cellspacing="0">
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>OWNER</th>
                        <th>STATUS</th>
                    </tr>
                    <c:forEach var="lot" items="${requestScope.lots}">

                        <tr>
                            <td>${lot.id}</td>
                            <td>${lot.name}</td>
                            <td>100%</td>
                            <td>Нет</td>
                        </tr>

                    </c:forEach>


                </table>
            </div>


        </div>

    </div>
    <!--FOOTER-->
    <%@include file="footer.jsp"%>
</div>
</body>
</html>