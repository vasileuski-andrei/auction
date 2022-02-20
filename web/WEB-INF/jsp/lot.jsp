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
<%--                    <span>${requestScope.lotName}</span>--%>
                    <tr>
                        <th>LOT ID</th>
                        <th>USER</th>
                        <th>ALL BET</th>
                        <th>TIME</th>
                    </tr>
                    <c:forEach var="bet" items="${requestScope.bets}">

                        <tr>
                            <td>${bet.lotId}</td>
                            <td>${bet.userName}</td>
                            <td>${bet.userBet} $</td>
                            <td>-</td>
                        </tr>

                    </c:forEach>


                </table>
            </div>

            <div class="wrap-bet-form p-b-90">
                <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/lot" method="post">

                    <div class="wrap-input100 validate-input m-b-16">
                        <input class="input100" type="text" name="userBet" placeholder="Sum" required>
                        <span class="focus-input100"></span>
                    </div>

                    <div class="container-login100-form-btn m-t-17">
                        <button class="login100-form-btn">Bet</button>
                    </div>

                </form>
            </div>


<%--            <c:if test="${not empty requestScope.errors}">--%>
<%--                <div>--%>
<%--                    <c:forEach var="error" items="${requestScope.errors}">--%>
<%--                        <ul>--%>
<%--                            <li><span class="alert-message">${error.message}</span></li>--%>
<%--                        </ul>--%>

<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--            </c:if>--%>




        </div>
    </div>

    <%@include file="footer.jsp"%>
</div>
</body>
</html>
