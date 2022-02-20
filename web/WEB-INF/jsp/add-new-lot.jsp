<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <style><%@include file="/WEB-INF/css/normalize.css"%></style>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
    <style><%@include file="/WEB-INF/css/util.css"%></style>

</head>
<body>

<div class="page-wrapper">

    <%@include file="header.jsp"%>

    <div class="content-wrapper">

        <aside class="aside"><a href="#">О нас</a><a href="#">Гарантия и сервис</a><a href="#">Сотрудничество с нами</a></aside>

        <div class="first-section">

<%--            <form action="${pageContext.request.contextPath}/add-new-lot" method="post">--%>
<%--                <label for="lot">Lot name:--%>
<%--                    <input type="text" name="lot" id="lot" required>--%>
<%--                </label><br>--%>
<%--                <label for="price">Start bet:--%>
<%--                    <input type="text" name="price" id="price" required>--%>
<%--                </label><br>--%>
<%--                <label for="term">Sale term:--%>
<%--                    <input type="time" name="term" id="term" required>--%>
<%--                </label><br>--%>
<%--                <button type="submit">Submit</button>--%>
<%--                <c:if test="${not empty requestScope.errors}">--%>
<%--                    <div>--%>
<%--                        <c:forEach var="error" items="${requestScope.errors}">--%>
<%--                            <span class="alert-message">${error.message}</span>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
<%--                </c:if>--%>

<%--            </form>--%>



    <div class="wrap-login100 p-t-50 p-b-90">
        <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/add-new-lot" method="post">

            <div class="wrap-input100 validate-input m-b-16">
                <input class="input100" type="text" name="lot" placeholder="Lot name" value="${param.email}" required>
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-16">
                <input class="input100" type="text" name="price" placeholder="Your bet" required>
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-16">
                <input class="input100" type="time" name="term" placeholder="Sale term" required>
                <span class="focus-input100"></span>
            </div>

            <div class="container-login100-form-btn m-t-17">
                <button class="login100-form-btn">Submit</button>
            </div>

            <c:if test="${not empty requestScope.errors}">
                <div>
                    <c:forEach var="error" items="${requestScope.errors}">
                        <span class="alert-message">${error.message}</span>
                    </c:forEach>
                </div>
            </c:if>


        </form>
    </div>

        </div>
    </div>

    <%@include file="footer.jsp"%>
</div>
</body>
</html>
