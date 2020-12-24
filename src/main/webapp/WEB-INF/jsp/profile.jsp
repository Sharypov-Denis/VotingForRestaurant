<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<style>
    .error {
        color: red;
        font-size: 0.9em;
        font-weight: bold;
    }
</style>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <div class="row">
            <div class="col-5 offset-3">
                <jsp:useBean id="user" type="den.graduation.model.User" scope="request"/>
                <hr>
                <form:form name="profileForm" class="form-group" method="post" modelAttribute="user"
                           action="/profile/register" onsubmit="return validateUser()">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="Имя пользователя">
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="E-mail">
                        <form:errors path="email" cssClass="error"/>
                    </div>
                    </dl>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Пароль">
                    </div>
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