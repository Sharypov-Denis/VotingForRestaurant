<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<%--
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
--%>

<a href="/restaurants" class="btn btn-lg btn-warning"><span>Список ресторанов для голосования</span></a>

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
