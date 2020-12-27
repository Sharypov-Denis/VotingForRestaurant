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
- деплой в контейнер сервлетов Tomcat, в облачный сервис Heroku
