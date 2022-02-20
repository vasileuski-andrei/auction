<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header">

    <div class="common-header-wrapper">

        <div class="top-header-container">
            <!--logo--><a class="logo" href="index"><img src="img/logo.png"></a>

            <nav class="top-menu">
                <a href="${pageContext.request.contextPath}/market">market</a>
                <a href="${pageContext.request.contextPath}/add-new-lot">add new lot</a>
                <a href="#">cashbox</a>
                <c:if test="${sessionScope.user != null}" >
                    <a href="${pageContext.request.contextPath}/profile">profile</a>
                </c:if>


            </nav>

            <div class="contact-panel">

                <div class="cp-wrapper-elements">

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

                <form class="search-form" action="index.html">
                    <input class="search" type="text" name="info" placeholder="Search">
                    <input class="button" type="submit" name="sub" value="">
                </form>
            </div>
        </div>

        <div class="middle-header-container">

            <div class="header-title">Auction</div>
<%--            <div class="header-description">Информация о магазине</div>--%>
        </div>

        <div class="crumbs">
            <a href="${pageContext.request.contextPath}/index">main page</a>
            <a href="${pageContext.request.contextPath}/market">market</a>
<%--            <span>Гарантия и сервис</span>--%>
        </div>
    </div>
</header>
