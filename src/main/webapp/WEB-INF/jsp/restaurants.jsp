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
    <div class="container">
        <br>
        <sec:authorize access="hasRole('ADMIN')">
            <a href="restaurants/create" class="btn btn-lg btn-primary"><span>Добавить ресторан</span></a>
        </sec:authorize>
        <br>
        <a class="btn btn-lg btn-outline-primary hero__btn">${status}</a>
        <br>
        <br>
        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="request" class="den.graduation.model.Restaurant"/>
            <div class="card mb-4 box-shadow">
                <div class="card-header">
                    <h4 class="my-0 font-weight-normal">${restaurant.name}</h4>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>Количество голосов на ${time}: <a
                                class="btn btn-lg btn-outline-primary hero__btn"><span>${restaurant.numberOfVotes}</span></a>
                        </li>

                        <li>
                            <c:forEach var="num" items="${restaurant.menus}">
                                <tr>
                                    <br>
                                    <th>Меню на дату: ${num.dateTime.toLocalDate()},</th>
                                    <th>Блюдо: ${num.name},</th>
                                    <th>Цена: ${num.price} руб.</th>

                                </tr>
                                <tr>
                                    <sec:authorize access="hasRole('ADMIN')">
                                        <td><a href="/menu/delete?id=${num.id}">Удалить меню</a></td>
                                    </sec:authorize>
                                </tr>
                            </c:forEach>
                        </li>
                    </ul>
                    <a class="btn btn-lg btn-warning" href="restaurants/voting?id=${restaurant.id}">
                        Хочу обедать в этом ресторане(Проголосовать)</a>
                    <sec:authorize access="hasRole('ADMIN')">
                        <%--  <td><a class="btn btn-lg btn-block btn-primary"
                         href="restaurants/delete?id=${restaurant.id}">Удалить ресторан</a></td>
                   href="restaurants/sorry">Удалить ресторан</a></td>--%>
                        <td><a class="btn btn-lg btn-block btn-primary"
                               href="restaurants/update?id=${restaurant.id}">Редактировать ресторан</a></td>
                        <%--    href="restaurants/sorry">Редактировать ресторан</a></td>--%>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="btn btn-lg btn-block btn-primary" href="/menu/createMenu?id=${restaurant.id}">
                            <span class="fa fa-plus"></span>
                            Добавить меню</a>
                    </sec:authorize>
                </div>
            </div>
        </c:forEach>
    </div>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>