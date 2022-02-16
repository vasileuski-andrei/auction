<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <style><%@include file="/WEB-INF/css/normalize.css"%></style>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <style><%@include file="/WEB-INF/css/table-style.css"%></style>

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
                    <c:forEach var="lot" items="${requestScope.lots}">

                        <tr>
                            <td>${lot.id}</td>
                            <td><a href="${pageContext.request.contextPath}/lot?lotId=${lot.id}&lotName=${lot.lotName}&startBet=${lot.startPrice}">${lot.lotName}</a></td>
                            <td>${lot.owner}</td>
                            <td>${lot.startPrice} $</td>

<%--                            <c:choose>--%>
<%--                                <c:when test="${not empty lot.lastPrice}">--%>
<%--                                    <td>${lot.lastPrice}</td>--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
<%--                                    <td>-</td>--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>

                            <c:choose>
                                <c:when test="${lot.lastPrice eq null}">
                                    <td>-</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${lot.lastPrice}</td>
                                </c:otherwise>
                            </c:choose>

<%--                            <c:if test="${lot.lastPrice != null}">--%>
<%--                                <td>lot.lastPrice</td>--%>
<%--                            </c:if>--%>
                            <td>${lot.lotStatus}</td>
                        </tr>

                    </c:forEach>


                </table>
            </div>


        </div>

    </div>
    <!--FOOTER-->
    <%@include file="footer.jsp"%>
</div>
</body>
</html>
