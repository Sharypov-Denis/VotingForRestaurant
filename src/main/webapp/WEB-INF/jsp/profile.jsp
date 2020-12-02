<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <%--@elvariable id="userTo" type="ru.javawebinar.topjava.to.UserTo"--%>
        <div class="row">
            <div class="col-5 offset-3">
                <%--<h3>${user.name} <spring:message code="${register ? 'app.register' : 'app.profile'}"/></h3>
                <form:form class="form-group" modelAttribute="user" method="post" action="${register ? 'profile/register' : 'profile'}"                           charset="utf-8" accept-charset="UTF-8">

                    <input name="id" value="${user.id}" type="hidden">
                    <topjava:inputField labelCode="user.name" name="name"/>
                    <topjava:inputField labelCode="user.email" name="email"/>
                    <topjava:inputField labelCode="user.password" name="password" inputType="password"/>

                    <div class="text-right">
                        <a class="btn btn-secondary" href="#" onclick="window.history.back()">
                            <span class="fa fa-close"></span>
                            <spring:message code="common.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <span class="fa fa-check"></span>
                            <spring:message code="common.save"/>
                        </button>
                    </div>
                </form:form> --%>

                <jsp:useBean id="user" type="den.graduation.model.User" scope="request"/>
                <hr>
                    <form:form name="profileForm" class="form-group" method="post" action="/profile/register" onsubmit="return validateUser()">
               <!-- <form name="myForm" method="post" action="/profile/register" onsubmit="return validateForm()">-->
                    <input type="hidden" name="id" value="${user.id}">
                    <dl>
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="Имя пользователя">
                        </div>
                    </dl>
                        <%--<dt>EMAIL:</dt>
                        <dd><input type="email" value="${user.email}" name="email" required></dd>--%>
                        <div class="form-group">
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="E-mail">
                        </div>
                    </dl>
                    <dl>
                        <%-- <dt>Password:</dt>
                        <dd><input type="password" value="${user.password}" name="password" required></dd>--%>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Пароль">
                        </div>
                    </dl>
                    <!--<button type="submit">Save</button>
                    <button onclick="window.history.back()" type="button">Cancel</button>-->

                    <div class="text-right">
                        <a class="btn btn-secondary" href="#" onclick="window.history.back()">
                            <span class="fa fa-close"></span>
                            Назад
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <span class="fa fa-check"></span>
                            Сохранить
                        </button>
                    </div>
               <!-- </form>-->
                    </form:form>
            </div>
        </div>
    </div>
</div>
<script>
    function validateUser() {
        var element = document.forms["profileForm"]["name"].value;
        if (element == "") {
            alert("имя не может быть пустым");
            return false;
        }
    }
</script>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>