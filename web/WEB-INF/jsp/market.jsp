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

        <aside class="aside"><a href="#">About us</a><a href="#">Delivery</a><a href="#">FAQ</a></aside>

        <div class="first-section">

            <div id="content">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>LOT</th>
                        <th>OWNER</th>
                        <th>START BID</th>
                        <th>LAST BID</th>
                        <th>STATUS</th>
                        <th>TIME LEFT</th>
                    </tr>
                    <c:forEach var="lot" items="${requestScope.lots}">
                        <tr>
                            <td>${lot.id}</td>
                            <td><a href="${pageContext.request.contextPath}/lot?lotId=${lot.id}&lotName=${lot.lotName}&startBid=${lot.startBid}&lastBid=${lot.lastBid}&lotOwner=${lot.owner}">${lot.lotName}</a></td>
                            <td>${lot.owner}</td>
                            <td>${lot.startBid} $</td>
                            <c:choose>
                                <c:when test="${lot.lastBid != 'null'}">
                                    <td>
                                        <div>${lot.lastBid} $</div>
                                        <div class="name-of-user-in-last-bid-column">${lot.userWhoMadeLastBid}</div>

                                    </td>

                                </c:when>
                                <c:otherwise>
                                    <td>-</td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${lot.lotStatus eq 'SOLD'}">
                                    <td><span class="lot-status-sold">${lot.lotStatus}</span></td>
                                </c:when>
                                <c:when test="${lot.lotStatus eq 'NOT_SOLD'}">
                                    <td><span class="lot-status-not-sold">NOT SOLD</span></td>
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
