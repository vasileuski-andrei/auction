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

            <form action="${pageContext.request.contextPath}/login" method="post">
                <label for="email">Email:
                    <input type="text" name="email" id="email" value="${param.email}" required>
                </label><br>
                <label for="password">Password:
                    <input type="password" name="password" id="password" required>
                </label><br>
                <button type="submit">Login</button>
                <a href="${pageContext.request.contextPath}/registration">Sign up</a>

                <c:if test="${param.fail != null}">
                    <div>
                        <span>Email or password is incorrect. Try it again.</span>
                    </div>
                </c:if>
            </form>

        </div>
    </div>
    <!--FOOTER-->
    <%@include file="footer.jsp"%>
</div>
</body>
</html>
