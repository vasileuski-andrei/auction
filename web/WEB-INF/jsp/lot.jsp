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

        <aside class="aside"><a href="#">About us</a><a href="#">Delivery</a><a href="#">FAQ</a></aside>

        <div class="first-section">

            <div id="content">

                <div class="lot-info">
                    <h2>Lot name: ${requestScope.lotName}</h2>
                    <h2>Lot owner: ${requestScope.lotOwner}</h2>
                    <h2>Start bid: ${requestScope.startBid}</h2>
                    <h2>Last bid: ${requestScope.lastBid}</h2>

                </div>

                <div class="lot-info">
                    <table>

                        <tr>
                            <th>USER</th>
                            <th>ALL BET</th>
                            <th>TIME</th>
                        </tr>
                        <c:forEach var="bid" items="${requestScope.bids}">

                            <tr>
                                <td>${bid.userName}</td>
                                <td>${bid.userBid} $</td>
                                <td>-</td>
                            </tr>

                        </c:forEach>

                    </table>

                </div>

                <div class="wrap-bet-form">
                    <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/lot" method="post">

                        <div class="wrap-input100 validate-input m-b-16">
                            <input class="input100" type="text" name="userBid" placeholder="Sum" required>
                            <span class="focus-input100"></span>
                        </div>

                        <div class="container-login100-form-btn m-t-17">
                            <button class="login100-form-btn">Bid</button>
                        </div>

                    </form>
                </div>

            </div>

<%--            <div class="wrap-bet-form p-b-90">--%>
<%--                <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/lot" method="post">--%>

<%--                    <div class="wrap-input100 validate-input m-b-16">--%>
<%--                        <input class="input100" type="text" name="userBid" placeholder="Sum" required>--%>
<%--                        <span class="focus-input100"></span>--%>
<%--                    </div>--%>

<%--                    <div class="container-login100-form-btn m-t-17">--%>
<%--                        <button class="login100-form-btn">Bid</button>--%>
<%--                    </div>--%>

<%--                </form>--%>
<%--            </div>--%>

        </div>
    </div>

    <%@include file="footer.jsp"%>
</div>
</body>
</html>
