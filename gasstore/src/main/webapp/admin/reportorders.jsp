<%-- 
    Document   : reportorders
    Created on : Jun 8, 2023, 9:20:31 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>OrderId</th>
                    <th>CustomerName</th>
                    <th>OrderDate</th>
                    <th>requiredDate</th>
                    <th>shippedDate</th>
                    <th>shipAddress</th>
                    <th>status</th>
                    <th>shipVia</th>
                    <th>notes</th>
                    <th>payment</th>
                    <th>totalMoney</th>
                </tr>
            </thead>
            <tbody>
                <%
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition","inline; filename=order.xls");
                %>
                <c:forEach items="${requestScope.getProcessOrders}" var="o">
                    <tr>
                        <td>${o.orderId}</td>
                        <td>${o.customer.firstName} ${o.customer.lastName}</td>
                        <td>${o.orderDate}</td>
                        <td>${o.requiredDate}</td>
                        <td>${o.shippedDate}</td>
                        <td>${o.shipAddress}</td>
                        <td>
                            <c:choose>
                                <c:when test="${o.status == 1}">Thành công</c:when>
                                <c:otherwise>Thất bại</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${o.shipVia}</td>
                        <td>${o.notes}</td>
                        <td>
                            <c:choose>
                                <c:when test="${o.payment == 1 && o.status == 1 }">Đã thanh toán</c:when>
                                <c:when test="${o.payment == 2 && o.status == 1}">Thanh toán khi nhận hàng</c:when>
                                <c:otherwise>Thất bại</c:otherwise>
                            </c:choose>
                        </td>
                        <td><fmt:formatNumber value = "${Math.round((orderNew.totalMoney)/1000)*1000}" type = "currency"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
