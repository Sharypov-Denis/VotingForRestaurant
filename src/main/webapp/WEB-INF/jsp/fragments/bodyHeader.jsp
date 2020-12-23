<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%-- убрал с добавлением webjars
<fmt:setBundle basename="messages.app"/>
<header>
    <a href="/exercises"><fmt:message key="app.title"/></a> | <a href="/users"><fmt:message key="user.title"/></a> | <a href="${pageContext.request.contextPath}"><fmt:message key="app.home"/></a>
</header>--%>


<%--<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="restaurants" class="navbar-brand"><img src="resources/images/icon-meal.png">Голосование</a>
        <form class="form-inline my-2">
            <a class="btn btn-info mr-1" href="/admin/users">Пользователи</a>
            <a class="btn btn-primary" href="">
                <span class="fa fa-sign-in"></span>
            </a>
        </form>
    </div>
</nav>--%>


<nav class="navbar navbar-expand-md navbar-dark bg-dark py-0">
    <div class="container">
        <a href="restaurants" class="navbar-brand">Выпускной проект "Голосование"</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <sec:authorize access="isAuthenticated()">
                        <form:form class="form-inline my-2" action="logout" method="post">
                            <sec:authorize access="hasRole('ADMIN')">
                                <a class="btn btn-info mr-1" href="/admin/users">Пользователи</a>
                            </sec:authorize>
                            <%--
                            <a class="btn btn-info mr-1" href="profile"><sec:authentication property="principal.userTo.name"/> ПРОФИЛЬ</a>
                              --%>
                            <button class="btn btn-primary my-1" type="submit">
                                <span class="fa fa-sign-out">Выйти</span>
                            </button>

                        </form:form>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <form:form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
                            <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                            <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                            <button class="btn btn-success" type="submit">
                                <span class="fa fa-sign-in"></span>
                            </button>
                        </form:form>
                    </sec:authorize>
                </li>
            </ul>
        </div>
    </div>
</nav>
