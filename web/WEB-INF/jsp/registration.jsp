<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Auction</title>
    <style><%@include file="/WEB-INF/css/normalize.css"%></style>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
    <style><%@include file="/WEB-INF/css/util.css"%></style>

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

            <div class="wrap-login100 p-t-50 p-b-90">
                <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/registration" method="post">

                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="text" name="name" placeholder="Username" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="date" name="birthday" placeholder="Date" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="text" name="email" placeholder="Email" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="password" name="password" placeholder="Password" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" >
                        <input class="input100" type="password" name="password" placeholder="Confirm" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="container-login100-form-btn m-t-17">
                        <button class="login100-form-btn" type="submit">Sign up</button>
                        <c:if test="${not empty requestScope.errors}">
                            <div>
                                <c:forEach var="error" items="${requestScope.errors}">
                                    <span>${error.message}</span>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>

                </form>

            </div>

        </div>

    </div>

    <%@include file="footer.jsp"%>
</div>
</body>
</html>
