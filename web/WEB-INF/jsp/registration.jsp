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

<div class="page-wrapper">

    <%@include file="header.jsp"%>

    <div class="content-wrapper">

        <aside class="aside"><a href="#">About us</a><a href="#">Delivery</a><a href="#">FAQ</a></aside>

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
                        <input class="input100" type="password" name="password-confirm" placeholder="Confirmation" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="container-login100-form-btn m-t-17">
                        <button class="login100-form-btn" type="submit">Sign up</button>
                        <c:if test="${not empty requestScope.errors}">
                            <div>
                                <c:forEach var="error" items="${requestScope.errors}">
                                    <ul>
                                        <li><span class="alert-message">${error.message}</span></li>
                                    </ul>

                                </c:forEach>
                            </div>
                        </c:if>
                        <c:if test="${param.invalidCreateUser!= null}">
                            <div>
                                <span class="alert-message">Username or email is already taken.</span>
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
