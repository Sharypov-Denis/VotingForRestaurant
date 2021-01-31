# My project "Voting system"
| Technology | Badge |
|:-----------:|:-----:|
| Travis CI | [![Build Status](https://travis-ci.com/Sharypov-Denis/VotingForRestaurant.svg?branch=master)](https://travis-ci.com/Sharypov-Denis/VotingForRestaurant) |

# Technology stack
* Java 14
* Spring(Security, MVC, Data Jpa)
* Hibernate
* HSQLDB/PostgreSQL
* Jackson
* Maven
* SLF4J, Logback
* JUnit
* REST(Jackson)
* Bootstrap (css)
* JSP/JSTL/Html
* Tomcat
* Swagger

Developing in IntelliJ IDEA

Deploy to Heroku cloud service http://sharypovvote.herokuapp.com

# Documentation
Swagger http://localhost:8080/swagger-ui.html
# The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed
As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.

# Use curl for test REST API:
user: admin@gmail.com:
password: admin
## AdminRestController
### Get All Users: 
```sh
curl -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin
```
### Get Users with id = 100001
```sh
curl -s http://localhost:8080/rest/admin/users/get/100001 --user admin@gmail.com:admin
```
### Delete User with id = 100000
```sh
curl -s -X DELETE http://localhost:8080/rest/admin/users/delete/100000 --user admin@gmail.com:admin
```
### Register User
```sh
curl --location --request POST 'http://localhost:8080/rest/users/register' --header 'Content-Type: application/json' --data-raw '{"name":"NewUser","email":"test@mail.ru","password":"12345"} --user admin@gmail.com:admin
```
## MenuRestController:
### Get All menu 
```sh
curl -s http://localhost:8080/rest/menu
```
### Get MenuForRestaurant with id = 100001 and menu id = 10001 
```sh
curl -s http://localhost:8080/rest/menu/10001/100001
```
### Get MenuForRestaurant with id = 100001 
```sh
curl -s http://localhost:8080/rest/menu/getAll/100001
```
### Delete menu with id = 100001
```sh
curl -s -X DELETE http://localhost:8080/rest/menu/delete/10001
```
### Create menu for Restaurant id = 100001
```sh
curl --location --request POST 'http://localhost:8080/rest/menu/create/100001' --header 'Content-Type: application/json' --data-raw '{"name":"testmenu","price":"111","dateTime":"2020-12-29T00:00:00","restaurant":{"id":100001}}' --user admin@gmail.com:admin
```
## RestaurantRestController:
### Get All Restaurants 
```sh
curl -s http://localhost:8080/rest/restaurants
```
### Get Restaurants with id = 100002 
```sh
curl -s http://localhost:8080/rest/restaurants/100002
```
### Create Restaurant
```sh
curl --location --request POST 'http://localhost:8080/rest/restaurants/create' --header 'Content-Type: application/json' --data-raw '{"name":"New restaurant","numberOfVotes":"10"}
```
## VotingRestController:
### Get All Votes for User id = 100001
```sh
curl -s http://localhost:8080/rest/vote/getAllByUser/100001 --user admin@gmail.com:admin
```
### Delete Vote id = 2
```sh
curl -s -X DELETE http://localhost:8080/rest/vote/delete/2 --user admin@gmail.com:admin
```
### Create Vote for Restaurant id = 100004 for User id = 100001
```sh
curl --location --request POST 'http://localhost:8080/rest/vote/create/100004' --header 'Content-Type: application/json' --data-raw '{"registered":"2021-01-03T00:01:00"}' --user admin@gmail.com:admin
```
-----------------------------------------------------------------------------------------------------
Проект "Система голосования", выполненный после прохождения стажировки Java Enterprise Online Project. 

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