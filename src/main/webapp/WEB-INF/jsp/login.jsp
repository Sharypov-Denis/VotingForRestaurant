<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<style>
    p {
        text-indent: 25px;
    }
</style>
<div class="jumbotron py-0">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message">${param.message}</div>
        </c:if>
        <sec:authorize access="isAnonymous()">

            <div class="pt-4">
                <a class="btn btn-lg btn-success" href="profile/register">Зарегистрироваться</a>
                <button type="submit" class="btn btn-lg btn-primary" onclick="login('user@yandex.ru', 'password')">
                    Зайти как пользователь USER
                </button>
                <button type="submit" class="btn btn-lg btn-primary" onclick="login('admin@gmail.com', 'admin')">
                    Зайти как пользователь Admin
                </button>
            </div>
        </sec:authorize>
        <div class="lead py-4">
            <span class="text-muted">
                <p>Проект "Голосование за ресторан" с регистрацией/авторизацией и правами доступа на основе ролей (USER, ADMIN). Есть 2 типа пользователей: админ и обычные пользователи. Администратор может ввести ресторан и его обеденное меню дня. Меню меняется каждый день (обновления делают админы). Пользователи могут голосовать, в каком ресторане они хотят пообедать. Только один голос засчитывается для каждого пользователя.
                Если пользователь голосует снова в тот же день: Если это раньше 11:00, мы предполагаем, что он передумал. Если после 11:00, значит, уже поздно, голосование изменить нельзя. Каждый ресторан предлагает новое меню каждый день.
                <p>Технологии и инструменты, используемые в проекте: Maven, Spring Security, Spring MVC, Spring Data JPA, Hibernate ORM, REST(Jackson), JSP, PostgreSQL, HSQLDB, Bootstrap(CSS).
            </span>
            <br>
            <span class="text-muted">Администратор:user=admin@gmail.com, password = admin</span>
        </div>

    </div>

</div>
<div class="container lead">

    <h5>На данный момент, для отображения данных во View используются традиционные @Controller's. Но в проекте,
        параллельно также имеются REST контроллеры. Для тестирования REST API через Postman, используйте данные
        ниже:</h5>

    <h5>AdminRestController</h5>
    <h6>get All Users:</h6> GET http://sharypovvote.herokuapp.com/rest/admin/users --user admin@gmail.com:admin
    <br><br>
    <h6>get Users with id = 100001:</h6> GET http://sharypovvote.herokuapp.com/rest/admin/users/get/100001 --user
    admin@gmail.com:admin
    <br><br>
    <h6>delete User with id = 100000:</h6> DELETE http://sharypovvote.herokuapp.com/rest/admin/users/delete/100000
    --user admin@gmail.com:admin
    <br><br>
    <h6>register User:</h6> POST http://sharypovvote.herokuapp.com/rest/users/register' --header 'Content-Type:
    application/json' --data-raw
    '{"name":"NewUser","email":"test@mail.ru","password":"12345"} --user admin@gmail.com:admin
    <br><br>
    <h5>MenuRestController</h5>
    <h6>get All menu:</h6> GET http://sharypovvote.herokuapp.com/rest/menu
    <br><br>
    <h6>get MenuForRestaurant with id = 100001 and menu id = 10001:</h6> GET
    http://sharypovvote.herokuapp.com/rest/menu/10001/100001
    <br><br>
    <h6>get MenuForRestaurant with id = 100001:</h6> GET http://sharypovvote.herokuapp.com/rest/menu/getAll/100001
    <br><br>
    <h6>delete menu with id = 100001:</h6> DELETE http://sharypovvote.herokuapp.com/rest/menu/delete/10001
    <br><br>
    <h6>create menu for Restaurant id = 100001:</h6> POST http://sharypovvote.herokuapp.com/rest/menu/create/100001'
    --header 'Content-Type: application/json' --data-raw
    '{"name":"testmenu","price":"111","dateTime":"2020-12-29T00:00:00","restaurant":{"id":100001}}' --user
    admin@gmail.com:admin
    <br><br>
    <h5>RestaurantRestController</h5>
    <h6>get All Restaurants:</h6> GET http://sharypovvote.herokuapp.com/rest/restaurants
    <br><br>
    <h6>get Restaurants with id = 100002:</h6> GET http://sharypovvote.herokuapp.com/rest/restaurants/100002
    <br><br>
    <h6>create Restaurant:</h6> POST http://sharypovvote.herokuapp.com/rest/restaurants/create' --header 'Content-Type:
    application/json' --data-raw
    '{"name":"New restoran","numberOfVotes":"10"}
    <br><br>
    <h5>VotingRestController:</h5>
    <h6>get All Votes for User id = 100001:</h6> GET http://sharypovvote.herokuapp.com/rest/vote/getAllByUser/100001
    --user admin@gmail.com:admin
    <br><br>
    <h6>delete Vote id = 2:</h6> DELETE http://sharypovvote.herokuapp.com/rest/vote/delete/2 --user
    admin@gmail.com:admin
    <br><br>
    <h6>create Vote for Restaurant id = 100004 for User id = 100001:</h6> POST
    'http://sharypovvote.herokuapp.com/rest/vote/create/100004' --header 'Content-Type: application/json' --data-raw
    '{"registered":"2021-01-03T00:01:00"}' --user admin@gmail.com:admin

</div>

<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>

    function login(username, password) {
        setCredentials(username, password);
        $("#login_form").submit();
    }

    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>

<%--<jsp:include page="fragments/footer.jsp"/>--%>
</body>
<div class="jumbotron py-0">
    <div class="container">
        <div class="lead py-4">
            <span class="text-muted">Проект Шарыпова Д.А.(Проект в разработке).</span>
        </div>
    </div>
</div>

</html>