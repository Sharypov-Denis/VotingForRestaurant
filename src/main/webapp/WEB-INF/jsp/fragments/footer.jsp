<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:setBundle basename="messages.app"/>

<div class="jumbotron py-0">
    <div class="container">
        <div class="lead py-4">
            <span class="text-muted">Проект Шарыпова Д.А.(Проект в разработке).</span>
            <br>
            <span class="text-muted">Администратор:user=admin@gmail.com, password = admin</span>
        </div>
    </div>
</div>
<%--
<div class="container lead">

    <h4>Для тестирования REST API через Postman:</h4>

    <h5>AdminRestController</h5>
    <h6>get All Users:</h6> GET http://sharypovvote.herokuapp.com/rest/admin/users --user admin@gmail.com:admin
    <br><br>
    <h6>get Users with id = 100001:</h6>  GET http://sharypovvote.herokuapp.com/rest/admin/users/get/100001 --user admin@gmail.com:admin
    <br><br>
        <h6>delete User with id = 100000:</h6>  DELETE http://sharypovvote.herokuapp.com/rest/admin/users/delete/100000 --user admin@gmail.com:admin
    <br><br>
            <h6>register User:</h6>  POST http://sharypovvote.herokuapp.com/rest/users/register' --header 'Content-Type: application/json' --data-raw
    '{"name":"NewUser","email":"test@mail.ru","password":"12345"} --user admin@gmail.com:admin
    <br><br>
    <h5>MenuRestController</h5>
    <h6>get All menu:</h6> GET http://sharypovvote.herokuapp.com/rest/menu
    <br><br>
        <h6>get MenuForRestaurant with id = 100001 and menu id = 10001:</h6> GET http://sharypovvote.herokuapp.com/rest/menu/10001/100001
    <br><br>
            <h6>get MenuForRestaurant with id = 100001:</h6> GET http://sharypovvote.herokuapp.com/rest/menu/getAll/100001
    <br><br>
                <h6>delete menu with id = 100001:</h6> DELETE http://sharypovvote.herokuapp.com/rest/menu/delete/10001
    <br><br>
                    <h6>create menu for Restaurant id = 100001:</h6> POST http://sharypovvote.herokuapp.com/rest/menu/create/100001' --header 'Content-Type: application/json' --data-raw
    '{"name":"testmenu","price":"111","dateTime":"2020-12-29T00:00:00","restaurant":{"id":100001}}' --user
    admin@gmail.com:admin
    <br><br>
    <h5>RestaurantRestController</h5>
                        <h6>get All Restaurants:</h6> GET http://sharypovvote.herokuapp.com/rest/restaurants
    <br><br>
                            <h6>get Restaurants with id = 100002:</h6> GET http://sharypovvote.herokuapp.com/rest/restaurants/100002
    <br><br>
                                <h6>create Restaurant:</h6> POST http://sharypovvote.herokuapp.com/rest/restaurants/create' --header 'Content-Type: application/json' --data-raw
    '{"name":"New restoran","numberOfVotes":"10"}
    <br><br>
    <h5>VotingRestController:</h5>
                                    <h6>get All Votes for User id = 100001:</h6> GET http://sharypovvote.herokuapp.com/rest/vote/getAllByUser/100001 --user admin@gmail.com:admin
    <br><br>
                                        <h6>delete Vote id = 2:</h6> DELETE http://sharypovvote.herokuapp.com/rest/vote/delete/2 --user admin@gmail.com:admin
    <br><br>
                                            <h6>create Vote for Restaurant id = 100004 for User id = 100001:</h6> POST 'http://sharypovvote.herokuapp.com/rest/vote/create/100004' --header 'Content-Type: application/json' --data-raw '{"registered":"2021-01-03T00:01:00"}' --user admin@gmail.com:admin

</div>
--%>


