<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Soulfit</title>
    <style><%@include file="/WEB-INF/css/normalize.css"%></style>
    <style><%@include file="/WEB-INF/css/style.css"%></style>

</head>
<body>
<!--page-wrapper-->
<div class="page-wrapper">
    <!--HEADER-->
    <header class="header">
        <!--common-header-wrapper-->
        <div class="common-header-wrapper">
            <!--top-header-container-->
            <div class="top-header-container">
                <a class="logo" href="/index"><img file="/WEB-INF/img/logo.png"></a>
<%--                <a class="logo" href="#"><img src="/WEB-INF/img/logo.png"></a>--%>
                <!--top-menu-->
                <nav class="top-menu"><a href="#">о компании</a><a href="#">бренды</a><a href="#">доставка</a><a href="#">дилеры</a><a href="#">контакты</a></nav>

                <div class="contact-panel">

                    <div class="cp-wrapper-elements">
                        <div class="contact-numbers">
                            <div><span class="before-number">+7(499)</span>963-30-46</div>
                            <div><span class="before-number">+7(964)</span>761-43-29</div>
                        </div>
                        <div class="contact-links">
                            <div><a href="/registration">Sign up</a></div>
                            <div><a href="/login">Sign in</a></div>
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
    <!--CONTENT-->
    <div class="content-wrapper">
        <!--aside-->
        <aside class="aside"><a href="#">О нас</a><a href="#">Гарантия и сервис</a><a href="#">Сотрудничество с нами</a></aside>
        <!--first-section-->
        <div class="first-section">
            <h1>Гарантия и сервис</h1>
            <p>Сервисный центр выполняет техническое обслуживание и ремонт спортивного оборудования DIADORA, Champion, MARBO, Jimspor,HEGEN, AND1. С полным списком оборудования (наименования и модели) можно ознакомиться в каталоге сайта или непосредственно в Сервисном центре.</p>
            <h2>Заголовок</h2>
            <p>Сервисный центр выполняет техническое обслуживание и ремонт спортивного оборудования DIADORA, Champion, MARBO, Jimspor,HEGEN, AND1. С полным списком оборудования (наименования и модели) можно ознакомиться в каталоге сайта или непосредственно в Сервисном центре.</p>
            <h3>Заголовок</h3>
            <p>Сервисный центр выполняет техническое обслуживание и ремонт спортивного оборудования DIADORA, Champion, MARBO, Jimspor,HEGEN, AND1. С полным списком оборудования (наименования и модели) можно ознакомиться в каталоге сайта или непосредственно в Сервисном центре.</p>
            <h4>Заголовок</h4>
            <p>Сервисный центр выполняет техническое обслуживание и ремонт спортивного оборудования DIADORA, Champion, MARBO, Jimspor,HEGEN, AND1. С полным списком оборудования (наименования и модели) можно ознакомиться в каталоге сайта или непосредственно в Сервисном центре.</p>
            <ul>
                <li>Пример списка</li>
                <li>Пример списка</li>
                <li>Пример списка</li>
            </ul>
            <p>Сервисный центр выполняет техническое обслуживание и ремонт спортивного оборудования DIADORA, Champion, MARBO, Jimspor,HEGEN, AND1. С полным списком оборудования (наименования и модели) можно ознакомиться в каталоге сайта или непосредственно в Сервисном центре.</p>
        </div>
        <!--info-panel-->
        <div class="info-panel">
            <!--time-->
            <div class="time block-properties"><span class="caption-properties">Время работы</span><span class="line-properties"><span class="word-exception">пн-пт</span> 09:00-18:00</span><span class="line-properties"><span class="word-exception">сб-вс</span> выходные</span></div>
            <!--telephone-->
            <div class="telephone block-properties"><span class="caption-properties">телефоны</span><span class="line-properties">+7 (499) 963-30-46</span><span class="line-properties">+7 (964) 761-43-29</span></div>
            <!--email-->
            <div class="email block-properties"><span class="caption-properties">email</span><span class="line-properties">soulfitnes@yandex.ru</span><span class="line-properties">soulfitnes@mail.ru</span><span class="line-properties">info@soulfitnes.ru</span></div>
            <!--back-call-->
            <div class="back-call block-properties"><span class="caption-properties">остались вопросы?</span><a href="#"><img file="/WEB-INF/img/ip-back-call.png"></a></div>
        </div>
    </div>
    <!--FOOTER-->
    <%@include file="footer.jsp"%>
</div>
</body>
</html>
