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
    <style><%@include file="/WEB-INF/css/table-style.css"%></style>

</head>
<body>

<div class="page-wrapper">

    <%@include file="header.jsp"%>

    <div class="content-wrapper">

        <aside class="aside"><a href="#">О нас</a><a href="#">Гарантия и сервис</a><a href="#">Сотрудничество с нами</a></aside>

        <div class="first-section">

            <div id="content">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>LOT</th>
                        <th>OWNER</th>
                        <th>START PRICE</th>
                        <th>LAST BET</th>
                        <th>STATUS</th>
                    </tr>
<%--                    <c:forEach var="lot" items="${requestScope.lots}">--%>

<%--                        <tr>--%>
<%--                            <td>${lot.id}</td>--%>
<%--                            <td><a href="${pageContext.request.contextPath}/lot?lotId=${lot.id}">${lot.lotName}</a></td>--%>
<%--                            <td>${lot.owner}</td>--%>
<%--                            <td>${lot.startPrice} $</td>--%>
<%--                            <td>-</td>--%>
<%--                            <td>${lot.lotStatus}</td>--%>
<%--                        </tr>--%>

<%--                    </c:forEach>--%>


                </table>
            </div>

            <div class="wrap-bet-form p-b-90">
                <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/lot" method="post">

                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="text" name="bet" placeholder="Sum" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="container-login100-form-btn m-t-17">
                        <button class="login100-form-btn">Bet</button>
                    </div>

<%--                    <c:if test="${param.incorrectPassword != null}">--%>
<%--                        <div>--%>
<%--                            <span class="alert-message">Incorrect password</span>--%>
<%--                        </div>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${param.sqlexception != null}">--%>
<%--                        <div>--%>
<%--                            <span class="alert-message">Your account hasn't been deleted. Probably you have some lot in the market.</span>--%>
<%--                        </div>--%>
<%--                    </c:if>--%>

                </form>
            </div>




        </div>
    </div>

    <%@include file="footer.jsp"%>
</div>
</body>
</html>
