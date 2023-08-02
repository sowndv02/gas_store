<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment Notification</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/modal.css">
        <script>
            window.onload = function () {
                var content = document.querySelector('.content');
                content.style.visibility = 'visible';

                var counter = 5; // 5 seconds countdown
                var countdownElement = document.getElementById('countdown');

                var timer = setInterval(function () {
                    counter--;
                    countdownElement.textContent = counter;

                    if (counter <= 0) {
                        clearInterval(timer);
                        redirectToIndex();
                    }
                }, 1000);

                var closeButton = document.querySelector('.close-btn');
                closeButton.addEventListener('click', redirectToIndex);
            };

            function redirectToIndex() {
                window.location.href = '${pageContext.request.contextPath}/client/index'; // Redirect to index page
            }
        </script>
    </head>
    <body>
        <div class="center">
            <div class="content">
                <c:choose>
                    <c:when test="${requestScope.status == 0}">
                        <span class="material-symbols-outlined close">task_alt</span>
                    </c:when>
                    <c:otherwise>
                        <span class="material-symbols-outlined check">task_alt</span>
                    </c:otherwise>
                </c:choose>
                <div class="text">
                    <h2>Awesome!</h2>
                    <p>${requestScope.msg}</p>
                    <p>Chuyển hướng đến trang chủ trong <span id="countdown">5</span> giây...</p>
                </div>
                <label for="click" class="close-btn">Go Home</label>
            </div>
        </div>
    </body>
</html>
