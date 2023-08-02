<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>   
<!-- Begin Header Area -->
        <header>
            <!-- Begin Header Middle Area -->
            <div class="header-middle pl-sm-0 pr-sm-0 pl-xs-0 pr-xs-0">
                <div class="container">
                    <div class="row">
                        <!-- Begin Header Logo Area -->
                        <div class="col-lg-2">
                            <div class="logo pb-sm-30 pb-xs-30">
                                <a href="${pageContext.request.contextPath}/client/index">
                                    <img src="${pageContext.request.contextPath}/client/images/menu/logo/1.jpg" alt="">
                                </a>
                            </div>
                        </div>
                        <!-- Header Logo Area End Here -->
                        <!-- Begin Header Middle Right Area -->
                        <div class="col-lg-10 pl-0 ml-sm-15 ml-xs-15">
                            <!-- Begin Header Middle Searchbox Area -->
                            <form action="${pageContext.request.contextPath}/client/index" method="POST" class="hm-searchbox">
                                <input name="key" type="text" placeholder="Enter your search key ...">
                                <button class="li-btn" type="submit"><i class="fa fa-search"></i></button>
                            </form>
                            <!-- Header Middle Searchbox Area End Here -->
                            <!-- Begin Header Middle Right Area -->
                            <div class="header-middle-right">
                                <ul class="hm-menu">
                                    <!-- Begin Header Mini Cart Area -->
                                    <li class="hm-minicart">
                                        <div class="hm-minicart-trigger">
                                            <span class="item-icon"></span>
                                            <span class="item-text">Giỏ hàng
                                                <span class="cart-item-count">${size}</span>
                                            </span>
                                        </div>
                                        <span></span>
                                        <div class="minicart">
                                            <c:choose>
                                                <c:when test="${size != 0}">
                                                    <ul class="minicart-product-list">
                                                        <c:forEach items="${o.items}" var="i">
                                                            <li class="del${i.product.productId}">
                                                                <a href="${pageContext.request.contextPath}/client/products?id=${i.product.productId}" class="minicart-product-image"><img src="${pageContext.request.contextPath}/${i.product.base64Image}" alt="Preview" />
                                                                </a>
                                                                <div class="minicart-product-details">
                                                                    <h6>${i.product.name}</h6>
                                                                    <span class="qty${i.product.productId}">Số lượng: ${i.quantity}</span>
                                                                </div>
                                                                <span><fmt:formatNumber value = "${Math.round((i.product.unitPrice - i.product.unitPrice * i.product.discount.discount)/1000)*1000}" type = "currency"/>  </span>

                                                                <button onclick="a(${i.product.productId})"class="close" title="Remove">
                                                                    <i class="fa fa-close"></i>
                                                                </button>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                    <p class="minicart-total">TỔNG PHỤ: <span><fmt:formatNumber value="${Math.round((o.totalMoney)/1000)*1000}" type="currency"/></span></p>
                                                    </c:when>
                                                    <c:otherwise><img style="width:250px" src="images/menu/logo/no_cart.png" alt="alt"/></c:otherwise>
                                            </c:choose>
                                            <div class="minicart-button">
                                                <a href="${pageContext.request.contextPath}/client/cartshow" class="li-button li-button-fullwidth li-button-dark">
                                                    <span>Giỏ hàng</span>
                                                </a>
                                                <a href="${pageContext.request.contextPath}/client/checkout" class="li-button li-button-fullwidth">
                                                    <span>Thanh toán</span>
                                                </a>
                                            </div>

                                        </div>
                                    </li>
                                    <!-- Header Mini Cart Area End Here -->
                                    <li class="hm-minicart">
                                        <div class="hm-minicart-trigger li-button-dark">
                                            <span class="something"></span>
                                            <span class="item-text">Tài khoản</span>
                                        </div>
                                        <span></span>
                                        <div class="minicart">
                                            <ul class="minicart-product-list">
                                                <li style="flex-direction: column; text-align: center;">
                                                    <c:choose>
                                                        <c:when test="${sessionScope.account != null}">
                                                            <a class="margin-bottom-10" href="${pageContext.request.contextPath}/client/information">Tài khoản</a>
                                                            <a class="margin-bottom-10" href="${pageContext.request.contextPath}/client/logout">Đăng xuất</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a class="margin-bottom-10" href="${pageContext.request.contextPath}/client/login">Đăng nhập</a>
                                                            <a class="margin-bottom-10" href="${pageContext.request.contextPath}/client/register">Đăng ký</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <!-- Header Middle Right Area End Here -->
                        </div>
                        <!-- Header Middle Right Area End Here -->
                    </div>
                </div>
            </div>
            <!-- Header Middle Area End Here -->
            <!-- Begin Header Bottom Area -->
            <div class="header-bottom mb-0 header-sticky stick d-none d-lg-block d-xl-block">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <!-- Begin Header Bottom Menu Area -->
                            <div class="hb-menu">
                                <nav>
                                    <ul>
                                        <c:forEach items="${headers}" var="c">
                                            <li><a href="${c.href}">${c.name}</a></li>
                                        </c:forEach>
                                       
                                    </ul>
                                </nav>
                            </div>
                            <!-- Header Bottom Menu Area End Here -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header Bottom Area End Here -->
            <!-- Begin Mobile Menu Area -->
            <div class="mobile-menu-area d-lg-none d-xl-none col-12">
                <div class="container"> 
                    <div class="row">
                        <div class="mobile-menu">
                        </div>
                    </div>
                </div>
            </div>
            <!-- Mobile Menu Area End Here -->
        </header>
        <!-- Header Area End Here -->   