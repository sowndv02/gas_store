<%-- 
    Document   : inputotp
    Created on : May 16, 2023, 10:37:21 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hỗ trợ mật khẩu</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
        <!--<link href="../css/inputotp.css" rel="stylesheet" type="text/css"/>-->
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
        <link href="dist/css/inputotp.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>

        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
//prevents caching at the proxy server
%>
        <div class="row" style="margin-top: 10rem">
            <div class="col-md-5"></div>

            <form class="form" action="${pageContext.request.contextPath}/admin/authenticationotpadmin" method="POST">
                <input type="hidden" value="${requestScope.otpSend}" name="otpSend">
                ${requestScope.error}
                <div class="mario"></div>
                <label for="email">E-mail:</label>
                <input type="email" id="email" value="${requestScope.email}" readonly name="email">
                <label for="nome">OTP:</label>
                <input type="text" class="infos" required id="nome" name="otp">
                <button type="submit">Send</button>
                <button type="reset" id="limpar">Clear</button>

            </form>

        </div>

    </body>
</html>