<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- убрал с добавлением webjars
<fmt:setBundle basename="messages.app"/>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<br>
<section>
    <form method="post" action="users">
        <b>Exercises of&nbsp;</b>
        <fmt:message key="app.login"/>: <select name="userId">
        <!--<select name="userId">-->
            <option value="100000" selected>User</option>
            <option value="100001">Admin</option>
        </select>
        <button type="submit"><fmt:message key="common.select"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
--%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<%--
<section>
    <form method="post" action="users">
        Login <select name="userId">
        <option value="100000" selected>User</option>
        <option value="100001">Admin</option>
    </select>
        <button type="submit">Выбрать</button>
    </form>
</section>
--%>
<div class="jumbotron">
    <div class="container">
        <form method="post" action="users" class="form-inline">
            Login </label>
            <select name="userId" class="form-control mx-3">
                <option value="100000" selected>User</option>
                <option value="100001">Admin</option>
            </select>
            <button type="submit" class="btn btn-primary">Выбрать</button>
        </form>
    </div>
</div>

<sec:authorize access="isAnonymous()">

    <div class="pt-4">
        <a class="btn btn-lg btn-success" href="profile/register">Registration</a>
        <button type="submit" class="btn btn-lg btn-primary" onclick="login('user@yandex.ru', 'password')">
            LOGIN User
        </button>
        <button type="submit" class="btn btn-lg btn-primary" onclick="login('admin@gmail.com', 'admin')">
            LOGIN Admin
        </button>
    </div>
</sec:authorize>

<a class="btn btn-primary" href="profile/register">
    <span class="fa fa-plus"></span>
    Добавить пользователя
</a>

<%--удалить--%>
<button class="btn btn-primary" onclick="add()">
    <span class="fa fa-plus"></span>
    ТЕСТОВОЕ ДОБАВЛЕНИЕ
</button>
<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label">name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="name">
                    </div>

                    <div class="form-group">
                        <label for="email" class="col-form-label">email</label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="email">
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-form-label">password</label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="password">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    cancel
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    save
                </button>
            </div>
        </div>
    </div>
</div>

<%--удалить--%>
<script type="text/javascript">
    var context, form;
function add() {
$("#modalTitle").html(i18n["addTitle"]);
form.find(":input").val("");
$("#editRow").modal();
}

</script>
<script type="text/javascript">
    const i18n = [];
    i18n["addTitle"] = 'add';
    i18n["editTitle"] = 'edit';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus","common.confirm"}%>'>
    i18n["${key}"] = ${key}";
    </c:forEach>
</script>
<%--удалить--%>


</body>
<jsp:include page="fragments/footer.jsp"/>
</html>
