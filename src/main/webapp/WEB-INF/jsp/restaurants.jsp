<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <sec:authorize access="hasRole('ADMIN')">
        <a class="btn btn-primary" href="restaurants/create">
            <span class="fa fa-plus"></span>
            Add Exercise
        </a>
    </sec:authorize>



    <table class="table table-striped" id="datatable">
        <thead>
        <tr>
            <th><H1>Название ресторана</H1></th>
            <th>Количество голосов на ${time}</th>
            <th></th>
        </tr>

        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" type="den.graduation.model.Restaurant"/>
        <tr>
            <td><H2 style="background-color:LightGray;">${restaurant.name}
                <a class="btn btn-primary" href="restaurants/voting?id=${restaurant.id}">
                    ПРОГОЛОСОВАТЬ</a>
                <sec:authorize access="hasRole('ADMIN')">
                    <a class="btn btn-primary" href="/menu/createMenu?id=${restaurant.id}">
                        <span class="fa fa-plus"></span>
                        ADD MENU</a>
                </sec:authorize>
            </H2>
            </td>

            <td>${restaurant.numberOfVotes}</td>
            <sec:authorize access="hasRole('ADMIN')">
            <td><a href="restaurants/delete?id=${restaurant.id}">Delete</a></td>
            <td><a href="restaurants/update?id=${restaurant.id}">Update</a></td>
            </sec:authorize>

            <c:forEach var="num" items="${restaurant.menus}">
        <thead>
        <tr>
            <th>Название блюда</th>
            <th>Цена блюда</th>
        </tr>
        <tr>
            <td>${num.name}</td>
            <td>${num.price} руб.</td>
            <sec:authorize access="hasRole('ADMIN')">
                <td><a href="/menu/delete?id=${num.id}">Delete</a></td>
            </sec:authorize>

        </tr>
        </thead>
        </c:forEach>
        </tr>
        </c:forEach>
        </thead>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>