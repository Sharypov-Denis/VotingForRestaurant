Выпускной проект "Система голосования", выполненый после прохождения стажировки Java Enterprise Online Project.

2 типа пользователей: админ и обычные пользователи. Администратор может ввести ресторан и его обеденное меню дня.
Меню меняется каждый день (обновления делают админы).
Пользователи могут голосовать, в каком ресторане они хотят пообедать.
Только один голос засчитывается для каждого пользователя.

Если пользователь голосует снова в тот же день:
Если это раньше 11:00, мы предполагаем, что он передумал.
Если после 11:00, значит, уже поздно, голосование изменить нельзя.
Каждый ресторан предлагает новое меню каждый день.

Технологии и инструменты, используемые в проекте:
 Maven, Spring Security, Spring MVC, Spring Data JPA, Hibernate ORM, REST(Jackson), JSP, PostgreSQL, HSQLDB, Bootstrap(CSS).
- хранение данных реализовано в HSQLDB
- регистрация/авторизация и права доступа на основе ролей (USER, ADMIN)
- деплой в контейнер сервлетов Tomcat, в облачный сервис Heroku(http://sharypovvote.herokuapp.com/).

Use curl for test:
AdminRestController


get All Users: 

curl -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin


get Users with id = 100001

curl -s http://localhost:8080/rest/admin/users/get/100001 --user admin@gmail.com:admin


delete User with id = 100000

curl -s -X DELETE http://localhost:8080/rest/admin/users/delete/100000 --user admin@gmail.com:admin


MenuRestController:

get All menu 

curl -s http://localhost:8080/rest/menu


get MenuForRestaurant with id = 100001 and menu id = 10001 

curl -s http://localhost:8080/rest/menu/10001/100001


get MenuForRestaurant with id = 100001 

curl -s http://localhost:8080/rest/menu/getAll/100001


delete menu with id = 100001

curl -s -X DELETE http://localhost:8080/rest/menu/delete/10001


RestaurantRestController:

get All Restaurants 

curl -s http://localhost:8080/rest/restaurants


get Restaurants with id = 100002 

curl -s http://localhost:8080/rest/restaurants/100002


delete Restaurant with id = 100001 

curl -s -X DELETE http://localhost:8080/rest/restaurants/delete/100001 --user admin@gmail.com:admin


Планируемые доработки:
- Кеширование данных с Spring Cache/Hibernate cache
- Локализация
- Перевод проекта на Spring Boot(отдельный проект)
