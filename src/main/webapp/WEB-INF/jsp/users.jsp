<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- убрал с добавлением webjars
<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><fmt:message key="user.title"/></h3>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="user.name"/></th>
            <th><fmt:message key="user.email"/></th>
            <th><fmt:message key="user.roles"/></th>
            <th><fmt:message key="user.active"/></th>
            <th><fmt:message key="user.registered"/></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="pro.den.model.User"/>
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td>${user.roles}</td>
                <td><%=user.isEnabled()%>
                </td>
                <td><fmt:formatDate value="${user.registered}" pattern="dd-MM-yyyy"/></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
--%>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Пользователи</h3>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>Имя</th>
                <th>E-mail</th>
                <th>Роль</th>
                <th>Статус</th>
                <th>Дата регистрации</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" type="den.graduation.model.User"/>
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td><a href="mailto:${user.email}">${user.email}</a></td>
                    <td>${user.roles}</td>
                    <td><input type="checkbox"
                               <c:if test="${user.enabled}">checked</c:if> id="${user.id}"/></td>
                    <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                        <%-- <td><a><span class="fa fa-pencil"></span></a></td>
                         <td><a class="/admin/delete" id="${user.id}"><span class="fa fa-remove"></span></a></td>--%>

                    <td><a class="delete" href="/admin/delete?id=${user.id}"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>