<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:setBundle basename="messages.app"/>

<footer class="footer">
    <div class="container">
        <span class="text-muted">Проект Шарыпова Д.А.(Проект в разработке).</span>
        <br>
        <span class="text-muted">Администратор:user=admin@gmail.com, password = admin</span>

    </div>
</footer>
<div class="container lead">

    <h2>Для тестирования REST API через Postman:</h2>
    <h3>AdminRestController</h3>
    get All Users:
    <br>GET http://sharypovvote.herokuapp.com/rest/admin/users --user admin@gmail.com:admin
    <br>get Users with id = 100001:
    <br>
    GET http://sharypovvote.herokuapp.com/rest/admin/users/get/100001 --user admin@gmail.com:admin
    <br>
    delete User with id = 100000:
    <br>
    DELETE http://sharypovvote.herokuapp.com/rest/admin/users/delete/100000 --user admin@gmail.com:admin
    <br>
    register User:
    <br>
    POST http://sharypovvote.herokuapp.com/rest/users/register' --header 'Content-Type: application/json' --data-raw
    '{"name":"NewUser","email":"test@mail.ru","password":"12345"} --user admin@gmail.com:admin
    <br>
    <h3>MenuRestController</h3>
    get All menu:
    <br>
    GET http://sharypovvote.herokuapp.com/rest/menu
    <br>
    get MenuForRestaurant with id = 100001 and menu id = 10001:
    <br>
    GET http://sharypovvote.herokuapp.com/rest/menu/10001/100001
    <br>
    get MenuForRestaurant with id = 100001:
    <br>
    GET http://sharypovvote.herokuapp.com/rest/menu/getAll/100001
    <br>
    delete menu with id = 100001:
    <br>
    DELETE http://sharypovvote.herokuapp.com/rest/menu/delete/10001
    <br>
    create menu for Restaurant id = 100001:
    <br>
    POST http://sharypovvote.herokuapp.com/rest/menu/create/100001' --header 'Content-Type: application/json' --data-raw
    '{"name":"testmenu","price":"111","dateTime":"2020-12-29T00:00:00","restaurant":{"id":100001}}' --user
    admin@gmail.com:admin
    <br>
    <h3>RestaurantRestController</h3>
    get All Restaurants:
    <br>
    GET http://sharypovvote.herokuapp.com/rest/restaurants
    <br>
    get Restaurants with id = 100002:
    <br>
    GET http://sharypovvote.herokuapp.com/rest/restaurants/100002
    <br>
    create Restaurant:
    <br>
    POST http://sharypovvote.herokuapp.com/rest/restaurants/create' --header 'Content-Type: application/json' --data-raw
    '{"name":"New restoran","numberOfVotes":"10"}
</div>


