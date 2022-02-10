<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Auction</title>
    <style><%@include file="/WEB-INF/css/normalize.css"%></style>
    <style><%@include file="/WEB-INF/css/style.css"%></style>

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

            <form action="/registration" method="post">
                <label for="name">Name:
                    <input type="text" name="name" id="name">
                </label><br>
                <label for="birthday">Birthday:
                    <input type="date" name="birthday" id="birthday">
                </label><br>
                <label for="email">Email:
                    <input type="text" name="email" id="email">
                </label><br>
                <label for="password">Password:
                    <input type="password" name="password" id="password">
                </label><br>
                <label for="password-confirm">Password confirm:
                    <input type="text" name="password-confirm" id="password-confirm">
                </label><br>
                <button type="submit">Send</button>
                <c:if test="${not empty requestScope.errors}">
                    <div>
                        <c:forEach var="error" items="${requestScope.errors}">
                            <span>${error.message}</span>
                        </c:forEach>
                    </div>
                </c:if>
            </form>

        </div>

        <!--info-panel-->
<%--        <div class="info-panel">--%>
<%--            <!--time-->--%>
<%--            <div class="time block-properties"><span class="caption-properties">Время работы</span><span class="line-properties"><span class="word-exception">пн-пт</span> 09:00-18:00</span><span class="line-properties"><span class="word-exception">сб-вс</span> выходные</span></div>--%>
<%--            <!--telephone-->--%>
<%--            <div class="telephone block-properties"><span class="caption-properties">телефоны</span><span class="line-properties">+7 (499) 963-30-46</span><span class="line-properties">+7 (964) 761-43-29</span></div>--%>
<%--            <!--email-->--%>
<%--            <div class="email block-properties"><span class="caption-properties">email</span><span class="line-properties">soulfitnes@yandex.ru</span><span class="line-properties">soulfitnes@mail.ru</span><span class="line-properties">info@soulfitnes.ru</span></div>--%>
<%--            <!--back-call-->--%>
<%--            <div class="back-call block-properties"><span class="caption-properties">остались вопросы?</span><a href="#"><img src="img/ip-back-call.png"></a></div>--%>
<%--        </div>--%>
    </div>
    <!--FOOTER-->
    <%@include file="footer.jsp"%>
</div>
</body>
</html>
