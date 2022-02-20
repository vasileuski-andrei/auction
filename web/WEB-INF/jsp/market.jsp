<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Market</title>
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
                        <th>TIME LEFT</th>
                    </tr>
                    <c:forEach var="lot" items="${requestScope.lots}">
                        <tr>
                            <td>${lot.id}</td>
                            <td><a href="${pageContext.request.contextPath}/lot?lotId=${lot.id}&lotName=${lot.lotName}&startBet=${lot.startPrice}&lastBet=${lot.lastBet}&lotOwner=${lot.owner}">${lot.lotName}</a></td>
                            <td>${lot.owner}</td>
                            <td>${lot.startPrice} $</td>
                            <c:choose>
                                <c:when test="${lot.lastBet != 'null'}">
                                    <td>${lot.lastBet} $</td>
                                </c:when>
                                <c:otherwise>
                                    <td>-</td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${lot.lotStatus eq 'SOLD'}">
                                    <td><span class="lot-status-sold">${lot.lotStatus}</span></td>
                                </c:when>
                                <c:otherwise>
                                    <td><span class="lot-status-sell">${lot.lotStatus}</span></td>
                                </c:otherwise>
                            </c:choose>
                            <td>${lot.time}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${not empty requestScope.errors}">
                        <div>
                            <c:forEach var="error" items="${requestScope.errors}">
                                <ul>
                                    <li><span class="alert-message">${error.message}</span></li>
                                </ul>

                            </c:forEach>
                        </div>
                    </c:if>
                </table>
            </div>

        </div>

    </div>
    <%@include file="footer.jsp"%>
</div>
</body>
</html>
