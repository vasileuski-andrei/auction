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
            <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/login" method="post">

            <div class="wrap-input100 validate-input m-b-16">
                <input class="input100" type="text" name="email" placeholder="Email" value="${param.email}" required>
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-16">
                    <input class="input100" type="password" name="password" placeholder="Password" required>
                    <span class="focus-input100"></span>
            </div>

            <div class="flex-sb-m w-full p-t-3 p-b-24">
<%--                <div class="contact100-form-checkbox">--%>
<%--                    <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">--%>
<%--                    <label class="label-checkbox100" for="ckb1">--%>
<%--                        Remember me--%>
<%--                    </label>--%>
<%--                </div>--%>

                <div>
                    <a href="${pageContext.request.contextPath}/registration">Sign up</a>

                </div>
            </div>

            <div class="container-login100-form-btn m-t-17">
                <button class="login100-form-btn">Sign in</button>
            </div>

            <c:if test="${param.incorrectAuthData != null}">
                <div>
                    <span class="alert-message">Email or password is incorrect. Try it again.</span>
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
