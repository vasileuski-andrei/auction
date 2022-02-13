<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
    <!--common-header-wrapper-->
    <div class="common-header-wrapper">
        <!--top-header-container-->
        <div class="top-header-container">
            <!--logo--><a class="logo" href="index"><img src="img/logo.png"></a>
            <!--top-menu-->
            <nav class="top-menu"><a href="${pageContext.request.contextPath}/market">market</a><a href="#">cashbox</a><a href="${pageContext.request.contextPath}/add-new-lot">add new lot</a><a href="#">дилеры</a><a href="#">контакты</a></nav>
            <!--contact-panel-->
            <div class="contact-panel">
                <!--cp-wrapper-elements-->
                <div class="cp-wrapper-elements">

                    <div class="contact-numbers">
                        <div><span class="before-number">+7(499)</span>963-30-46</div>
                        <div><span class="before-number">+7(964)</span>761-43-29</div>
                    </div>
                    <div class="contact-links">
                        <c:if test="${sessionScope.user == null}" >
                            <div><a href="${pageContext.request.contextPath}/registration">Sign up</a></div>
                            <div><a href="${pageContext.request.contextPath}/login">Sign in</a></div>
                        </c:if>

                        <c:if test="${sessionScope.user != null}" >
                            <div>
                                <form action="${pageContext.request.contextPath}/logout" method="post">
                                    <button type="submit">Sign out</button>
                                </form>
                            </div>

                        </c:if>

                    </div>

                </div>
                <!--search-form-->
                <form class="search-form" action="index.html">
                    <input class="search" type="text" name="info" placeholder="Поиск по сайту...">
                    <input class="button" type="submit" name="sub" value="">
                </form>
            </div>
        </div>
        <!--middle-header-container-->
        <div class="middle-header-container">
            <!--header-title-->
            <div class="header-title">Auction</div>
            <div class="header-description">Информация о магазине</div>
        </div>
        <!--crumbs-->
        <div class="crumbs"><a href="#">Главная</a><a href="#">О компании</a><span>Гарантия и сервис</span></div>
    </div>
</header>
