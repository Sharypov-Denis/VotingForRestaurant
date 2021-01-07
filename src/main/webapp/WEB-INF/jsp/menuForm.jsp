<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Exercise</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <div class="row">
            <div class="col-5 offset-3">
                <a class="col-form-label"></a>
                <hr>
                <form name="myForm" method="post" action="/menu" onsubmit="return validateForm()">
                    <input type="hidden" name="menuid" value="${menu.id}">
                    <dl>
                        <div class="form-group">
                            <input type="text" class="form-control" id="newMenu" name="newMenu"
                                   placeholder="Название блюда" required>
                        </div>
                    </dl>
                    <dl>
                        <div class="form-group">
                            <input type="number" class="form-control" id="price" name="price" placeholder="Цена блюда"
                                   required>
                        </div>
                    </dl>

                    <dl>
                        <div class="form-group">
                            <input type="datetime-local" class="form-control" id="date" name="date" placeholder="Дата меню"
                                   required>
                        </div>
                    </dl>
                    <button type="submit">Save</button>
                    <button onclick="window.history.back()" type="button">Cancel</button>
                    </dl>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function validateForm() {
        var element = document.forms["myForm"]["foodOne"].value;
        if (element > 100 || element < 11) {
            alert("Количество не может быть меньше 1 и больше 10");
            return false;
        }

        var element1 = document.forms["myForm"]["foodOne"].value;
        if (element1 == "") {
            alert("Имя не может быть пустым");
            return false;
        }
    }
</script>
</body>
</html>
