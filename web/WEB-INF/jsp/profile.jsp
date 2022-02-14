<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
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

            <div class="wrap-login100 p-t-50 p-b-90">
                <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/delete-user" method="post">

                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="password" name="password" placeholder="Password" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="container-login100-form-btn m-t-17">
                        <button class="login100-form-btn">Delete account</button>
                    </div>

                    <c:if test="${param.incorrectPassword != null}">
                        <div>
                            <span class="alert-message">Incorrect password</span>
                        </div>
                    </c:if>
                    <c:if test="${param.sqlexception != null}">
                        <div>
                            <span class="alert-message">Your account hasn't been deleted. Probably you have some lot in the market.</span>
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
