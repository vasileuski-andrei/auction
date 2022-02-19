<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <style><%@include file="/WEB-INF/css/normalize.css"%></style>
    <style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body>

<div class="page-wrapper">

    <%@include file="header.jsp"%>

    <div class="content-wrapper">

        <aside class="aside"><a href="#">О нас</a><a href="#">Гарантия и сервис</a><a href="#">Сотрудничество с нами</a></aside>

        <div class="first-section">

            <form action="${pageContext.request.contextPath}/add-new-lot" method="post">
                <label for="lot">Lot name:
                    <input type="text" name="lot" id="lot" required>
                </label><br>
                <label for="price">Start price:
                    <input type="text" name="price" id="price" required>
                </label><br>
                <label for="term">Sale term:
                    <input type="time" name="term" id="term" required>
                </label><br>
                <button type="submit">Submit</button>

                <c:if test="${not empty requestScope.errors}">
                    <spa>ERROR</spa>
<%--                    <div>--%>
<%--                        <c:forEach var="error" items="${requestScope.errors}">--%>
<%--                            <span>${error}</span>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
                </c:if>

            </form>

        </div>
    </div>

    <%@include file="footer.jsp"%>
</div>
</body>
</html>
