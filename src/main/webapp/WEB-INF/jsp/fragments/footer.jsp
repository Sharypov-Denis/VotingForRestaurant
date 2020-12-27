<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:setBundle basename="messages.app"/>

<footer class="footer">
    <div class="container">
        <span class="text-muted">Выпускной проект Шарыпова Д.А.(Проект в разработке).</span>
        <br>
        <span class="text-muted">Для тестирования: curl -s http://localhost:8080/admin/rest/users --user admin@gmail.com:admin</span>
        <br>
        <span class="text-muted">Администратор:user=admin@gmail.com, password = admin</span>
    </div>
</footer>
