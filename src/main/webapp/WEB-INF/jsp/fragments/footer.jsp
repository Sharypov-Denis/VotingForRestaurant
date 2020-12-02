<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:setBundle basename="messages.app"/>

<%-- убрал с добавлением webjars
<hr>
<footer><fmt:message key="app.footer"/></footer>--%>


<%--https://getbootstrap.com/docs/4.0/examples/sticky-footer/--%>
<footer class="footer">
    <div class="container">
        <span class="text-muted">Выпускной проект Шарыпова Д.А.</span>
    </div>
</footer>
