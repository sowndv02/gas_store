<%-- 
    Document   : sample
    Created on : May 16, 2023, 9:28:49 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        <c:forEach var="i" begin="1" end="3">
            .bg-${i} {
                background-image: url('${requestScope.img1}');
            }
        </c:forEach>
    </style>
</head>
<body>
    <%-- Loop through the request attributes and create divs with corresponding CSS classes --%>
    <c:forEach var="i" begin="1" end="3">
        <div class="bg-${i}">
            ali
        </div>
    </c:forEach>
    <h3><<img src="${requestScope.img1}" alt="alt"/></h3>
</body>
</html>

