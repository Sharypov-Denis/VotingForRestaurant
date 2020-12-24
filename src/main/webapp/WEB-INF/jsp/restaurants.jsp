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
        <div class="text-center text-right mt-4">
            <a href="restaurants/create" class="btn btn-lg btn-warning"><span>Add Restaurant</span></a>
        </div>
    </sec:authorize>

    <div class="container">
        <c:forEach items="${restaurants}" var="restaurant">
            <div class="card mb-4 box-shadow">
                <div class="card-header">
                    <h4 class="my-0 font-weight-normal">${restaurant.name}</h4>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>Количество голосов на ${time} <a
                                class="btn btn-lg btn-outline-primary hero__btn"><span>${restaurant.numberOfVotes}</span></a>
                        </li>
                        <li>
                            <c:forEach var="num" items="${restaurant.menus}">
                                <tr>
                                    <br>
                                    <th>Блюдо: ${num.name},</th>
                                    <th>Цена: ${num.price} руб.</th>
                                </tr>
                                <tr>
                                    <sec:authorize access="hasRole('ADMIN')">
                                        <td><a href="/menu/delete?id=${num.id}">Delete</a></td>
                                    </sec:authorize>
                                </tr>
                            </c:forEach>
                        </li>
                    </ul>
                    <a class="btn btn-lg btn-warning" href="restaurants/voting?id=${restaurant.id}">
                        ПРОГОЛОСОВАТЬ</a>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a class="btn btn-lg btn-block btn-primary"
                               href="restaurants/delete?id=${restaurant.id}">Delete Restaurant</a></td>
                        <td><a class="btn btn-lg btn-block btn-primary"
                               href="restaurants/update?id=${restaurant.id}">Update Restaurant</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="btn btn-lg btn-block btn-primary" href="/menu/createMenu?id=${restaurant.id}">
                            <span class="fa fa-plus"></span>
                            ADD MENU</a>
                    </sec:authorize>
                </div>
            </div>
        </c:forEach>
    </div>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>