<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Checkout</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png">
    <!-- Material Design Iconic Font-V2.2.0 -->
    <link rel="stylesheet" href="css/material-design-iconic-font.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- Font Awesome Stars-->
    <link rel="stylesheet" href="css/fontawesome-stars.css">
    <!-- Meanmenu CSS -->
    <link rel="stylesheet" href="css/meanmenu.css">
    <!-- owl carousel CSS -->
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <!-- Slick Carousel CSS -->
    <link rel="stylesheet" href="css/slick.css">
    <!-- Animate CSS -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- Jquery-ui CSS -->
    <link rel="stylesheet" href="css/jquery-ui.min.css">
    <!-- Venobox CSS -->
    <link rel="stylesheet" href="css/venobox.css">
    <!-- Nice Select CSS -->
    <link rel="stylesheet" href="css/nice-select.css">
    <!-- Magnific Popup CSS -->
    <link rel="stylesheet" href="css/magnific-popup.css">
    <!-- Bootstrap V4.1.3 Fremwork CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Helper CSS -->
    <link rel="stylesheet" href="css/helper.css">
    <!-- Main Style CSS -->
    <link rel="stylesheet" href="style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Modernizr js -->
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
</head>
<body>
    <c:set var="o" value="${sessionScope.cart}"/>
    <fmt:setLocale value = "vi_VN"/>    
    <c:set var="customerClass" value="com.se1715.group4.gasstore.dto.UserGoogle" />
    <!--[if lt IE 8]>
                <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
    <!-- Begin Body Wrapper -->
    <div class="body-wrapper">
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
                                        <li><a href="${pageContext.request.contextPath}/client/index">Trang chủ</a></li>
                                        <li><a href="${pageContext.request.contextPath}/client/category/binh-gas-1">Bình Gas</a></li>
                                        <li><a href="${pageContext.request.contextPath}/client/category/bep-gas-2">Bếp Gas</a></li>
                                        <li><a href="${pageContext.request.contextPath}/client/category/phu-kien-3">Phụ kiện</a></li>
                                        <li><a href="${pageContext.request.contextPath}/client/blog">tin tức</a></li>
                                        <li><a href="${pageContext.request.contextPath}/client/aboutus">Gas petro</a></li>
                                        <li><a href="${pageContext.request.contextPath}/client/contact">Liên hệ</a></li>
                                        <li><a href="${pageContext.request.contextPath}/client/faq">FAQ</a></li>
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
        <!-- Begin Li's Breadcrumb Area -->
        <div class="breadcrumb-area">
            <div class="container">
                <div class="breadcrumb-content">
                    <ul>
                        <li><a href="index-2.html">Home</a></li>
                        <li class="active">Checkout</li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Li's Breadcrumb Area End Here -->
        <!--Checkout Area Strat-->
        <div class="checkout-area pt-60 pb-30">
            <div class="container">
                <form action="${pageContext.request.contextPath}/client/payments" method="post">
                    <div class="row">
                        <div class="col-lg-6 col-12">
                            <div class="checkbox-form">
                                <h3 class="alert-danger">${requestScope.msg}</h3>
                                <h3>Chi tiết thanh toán</h3>
                                <c:choose>
                                    <c:when test="${sessionScope.account.getClass().getName() eq customerClass}">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>UserName <span class="required">*</span></label>
                                                    <input type="text" readonly value="${sessionScope.account.id}">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Tên <span class="required">*</span></label>
                                                    <input type="text" readonly placeholder="Son" value="${sessionScope.account.family_name}">
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Họ <span class="required">*</span></label>
                                                    <input type="text" readonly placeholder="Dao" value="${sessionScope.account.given_name}">
                                                </div>
                                            </div>



                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Email <span class="required">*</span></label>
                                                    <input type="email" readonly value="${sessionScope.account.email}">
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Địa chỉ <span class="required">*</span></label>
                                                    <input type="text" required name="address">
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Số điện thoại <span class="required">*</span></label>
                                                    <input type="text" required name="phone">
                                                </div>
                                            </div>

                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Tên <span class="required">*</span></label>
                                                    <input type="text" readonly value="${sessionScope.account.firstName}">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Họ <span class="required">*</span></label>
                                                    <input type="text" readonly value="${sessionScope.account.lastName}">
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <div class="checkout-form-list">
                                                    <label>Địa chỉ <span class="required">*</span></label>
                                                    <input type="text" readonly value="${sessionScope.account.address}">
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Email <span class="required">*</span></label>
                                                    <input type="email" readonly value="${sessionScope.account.email}">
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="checkout-form-list">
                                                    <label>Phone  <span class="required">*</span></label>
                                                    <input type="text" readonly value="${sessionScope.account.phone}">
                                                </div>
                                            </div>

                                        </div>
                                    </c:otherwise>
                                </c:choose>

                                <div class="different-address">
                                    <div class="ship-different-title">
                                        <h3>
                                            <label>Thay đổi thông tin đơn hàng ?</label>
                                            <input id="ship-box" type="checkbox">
                                        </h3>
                                    </div>
                                    <c:choose>
                                        <c:when test="${sessionScope.account.getClass().getName() ne customerClass}">
                                            <div id="ship-box-info" class="row">
                                                <div class="col-md-12">
                                                    <div class="checkout-form-list">
                                                        <label>Tên <span class="required">*</span></label>
                                                        <input type="text" name="firstName" value="${sessionScope.account.firstName}">
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="checkout-form-list">
                                                        <label>Họ <span class="required">*</span></label>
                                                        <input type="text" name="lastName" value="${sessionScope.account.lastName}">
                                                    </div>
                                                </div>

                                                <div class="col-md-12">
                                                    <div class="checkout-form-list">
                                                        <label>Địa chỉ <span class="required">*</span></label>
                                                        <input type="text" name="address" value="${sessionScope.account.address}">
                                                    </div>
                                                </div>

                                                <div class="col-md-12">
                                                    <div class="checkout-form-list">
                                                        <label>Email <span class="required">*</span></label>
                                                        <input type="email" name="email" value="${sessionScope.account.email}">
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="checkout-form-list">
                                                        <label>Điện thoại  <span class="required">*</span></label>
                                                        <input type="text" name="phone" value="${sessionScope.account.phone}">
                                                    </div>
                                                </div>
                                            </div>

                                        </c:when>
                                    </c:choose>
                                    <div class="checkout-form-list">
                                        <label>Đơn vị vận chuyến <span class="required">*</span></label>
                                        <select required id="id" name="shipper">
                                            <c:forEach items="${requestScope.shippers}" var="ship">
                                                <option value="${ship.shipmentId}">${ship.companyName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="checkout-form-list">
                                        <label>RequiredDate<span class="required">*</span></label>
                                        <input type="date" required id="dateInput" name="req" onchange="validateDate()" />
                                    </div>
                                    <div class="order-notes">
                                        <div class="checkout-form-list">
                                            <label>Ghi chú</label>
                                            <textarea name="notes" id="checkout-mess" cols="30" rows="10" placeholder="Notes about your order, e.g. special notes for delivery."></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-6 col-12">
                            <div class="your-order">
                                <h3>Đơn hàng</h3>
                                <div class="your-order-table table-responsive">
                                    <table class="table">
                                        <c:set var="o" value="${sessionScope.cart}"/>
                                        <c:forEach items="${o.items}" var="i">
                                            <tbody>
                                                <tr class="cart_item">
                                                    <td class="cart-product-name">${i.product.name}<strong class="product-quantity"> x ${i.quantity}</strong></td>
                                                    <td class="cart-product-total"><span class="amount"><fmt:formatNumber value = "${Math.round(((i.unitPrice - i.unitPrice * i.product.discount.discount)* i.quantity)/1000)*1000}" type = "currency"/></span></td>  
                                                </tr>
                                            </tbody>
                                        </c:forEach>
                                        <tfoot>
                                            <tr class="order-total">
                                                <th>Order Total</th>
                                                <td><strong><span class="amount"><fmt:formatNumber value = "${Math.round((o.totalMoney)/1000)*1000}" type = "currency"/></span></strong></td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <div class="payment-method">
                                    <div class="payment-accordion">
                                        <div id="accordion">

                                            <div class="card">
                                                <h5 class="panel-title row" >
                                                    <input class="col-md-2 mt-10" required style="height: 25px;" id="pay1" value="vnpay" type="radio" name="method"><label for="pay1" col-md-6><span style="font-size: 20px; font-weight: bold; color: black">VNPay</span>
                                                        <p>Thanh toán bằng ví VNPay hoặc thẻ Ngân hàng</p></label>
                                                </h5>

                                            </div>
                                        </div>

                                        <div class="card">
                                            <h5 class="panel-title row" >
                                                <input required class="col-md-2 mt-10" style="height: 25px;" id="pay2" value="delivery" type="radio" name="method"><label for="pay2" col-md-6><span style="font-size: 20px; font-weight: bold; color: black">Thanh toán khi nhận hàng</span>
                                                    <p>Thanh toán khi bạn nhận được sản phẩm</p></label>
                                            </h5>
                                        </div> 
                                    </div>
                                </div>
                                <div class="order-button-payment">
                                    <input value="Place order" type="submit"> 
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--Checkout Area End-->
    <div class="footer">
        <!-- Begin Footer Static Top Area -->
        <div class="footer-static-top">
            <div class="container">
                <!-- Begin Footer Shipping Area -->
                <div class="footer-shipping pt-60 pb-55 pb-xs-25">
                    <div class="row">
                        <!-- Begin Li's Shipping Inner Box Area -->
                        <div class="col-lg-3 col-md-6 col-sm-6 pb-sm-55 pb-xs-55">
                            <div class="li-shipping-inner-box">
                                <div class="shipping-icon">
                                    <img src="images/shipping-icon/1.png" alt="Shipping Icon">
                                </div>
                                <div class="shipping-text">
                                    <h2>Vận chuyển miễn phí</h2>
                                    <p>Và trả hàng miễn phí. Xem ngày vận chuyển trên hóa đơn.</p>
                                </div>
                            </div>
                        </div>
                        <!-- Li's Shipping Inner Box Area End Here -->
                        <!-- Begin Li's Shipping Inner Box Area -->
                        <div class="col-lg-3 col-md-6 col-sm-6 pb-sm-55 pb-xs-55">
                            <div class="li-shipping-inner-box">
                                <div class="shipping-icon">
                                    <img src="images/shipping-icon/2.png" alt="Shipping Icon">
                                </div>
                                <div class="shipping-text">
                                    <h2>Trả phí an toàn</h2>
                                    <p>Chi trả với phương thức thanh toán an toàn nhất tại Việt Nam.</p>
                                </div>
                            </div>
                        </div>
                        <!-- Li's Shipping Inner Box Area End Here -->
                        <!-- Begin Li's Shipping Inner Box Area -->
                        <div class="col-lg-3 col-md-6 col-sm-6 pb-xs-30">
                            <div class="li-shipping-inner-box">
                                <div class="shipping-icon">
                                    <img src="images/shipping-icon/3.png" alt="Shipping Icon">
                                </div>
                                <div class="shipping-text">
                                    <h2>Vận chuyển chắc chắn</h2>
                                    <p>Chính sách bảo vệ khách hàng bao gồm từ lúc bạn ấn đến lúc đơn hàng đến tận tay</p>
                                </div>
                            </div>
                        </div>
                        <!-- Li's Shipping Inner Box Area End Here -->
                        <!-- Begin Li's Shipping Inner Box Area -->
                        <div class="col-lg-3 col-md-6 col-sm-6 pb-xs-30">
                            <div class="li-shipping-inner-box">
                                <div class="shipping-icon">
                                    <img src="images/shipping-icon/4.png" alt="Shipping Icon">
                                </div>
                                <div class="shipping-text">
                                    <h2>Trung tâm trợ giúp 24/7</h2>
                                    <p>Có câu hỏi? Gọi chuyên gia hoặc chat trực tuyến!</p>
                                </div>
                            </div>
                        </div>
                        <!-- Li's Shipping Inner Box Area End Here -->
                    </div>
                </div>
                <!-- Footer Shipping Area End Here -->
            </div>
        </div>
        <!-- Footer Static Top Area End Here -->
        <!-- Begin Footer Static Middle Area -->
        <div class="footer-static-middle">
            <div class="container">
                <div class="footer-logo-wrap pt-50 pb-35">
                    <div class="row">
                        <!-- Begin Footer Logo Area -->
                        <div class="col-lg-4 col-md-6">
                            <div class="footer-logo">
                                <img src="images/menu/logo/1.jpg" alt="Footer Logo">
                                <p class="info">
                                    Chúng tôi cung cấp những bình gas bền, an toàn và hiệu quả vì khách hàng
                                </p>
                            </div>
                            <ul class="des">
                                <li>
                                    <span>Địa chỉ: </span>
                                    555 Đống Đa, Cầu Giấy, Hà Nội
                                </li>
                                <li>
                                    <span>Phone: </span>
                                    <a href="#">(+123) 123 321 345</a>
                                </li>
                                <li>
                                    <span>Email: </span>
                                    <a href="mailto://gaspetro@mail.com">gaspetri@mail.com</a>
                                </li>
                            </ul>
                        </div>
                        <!-- Footer Logo Area End Here -->
                        <!-- Begin Footer Block Area -->
                        <div class="col-lg-2 col-md-3 col-sm-6">
                            <div class="footer-block">
                                <h3 class="footer-block-title">Product</h3>
                                <ul>
                                    <li><a href="#">Hạ giá</a></li>
                                    <li><a href="#">Sản phẩm mới</a></li>
                                    <li><a href="#">Giá tốt nhất</a></li>
                                    <li><a href="#">Liên lạc</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- Footer Block Area End Here -->
                        <!-- Begin Footer Block Area -->
                        <div class="col-lg-2 col-md-3 col-sm-6">
                            <div class="footer-block">
                                <h3 class="footer-block-title">Công ty chúng tôi</h3>
                                <ul>
                                    <li><a href="#">Vận chuyển</a></li>
                                    <li><a href="#">Lưu ý chấp hành</a></li>
                                    <li><a href="#">Về chúng tối</a></li>
                                    <li><a href="#">Liên lạc</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- Footer Block Area End Here -->
                        <!-- Begin Footer Block Area -->
                        <div class="col-lg-4">
                            <div class="footer-block">
                                <h3 class="footer-block-title">Theo dõi chúng tôi</h3>
                                <ul class="social-link">
                                    <li class="twitter">
                                        <a href="https://twitter.com/" data-toggle="tooltip" target="_blank" title="Twitter">
                                            <i class="fa fa-twitter"></i>
                                        </a>
                                    </li>
                                    <li class="rss">
                                        <a href="https://rss.com/" data-toggle="tooltip" target="_blank" title="RSS">
                                            <i class="fa fa-rss"></i>
                                        </a>
                                    </li>
                                    <li class="google-plus">
                                        <a href="https://www.plus.google.com/discover" data-toggle="tooltip" target="_blank" title="Google +">
                                            <i class="fa fa-google-plus"></i>
                                        </a>
                                    </li>
                                    <li class="facebook">
                                        <a href="https://www.facebook.com/" data-toggle="tooltip" target="_blank" title="Facebook">
                                            <i class="fa fa-facebook"></i>
                                        </a>
                                    </li>
                                    <li class="youtube">
                                        <a href="https://www.youtube.com/" data-toggle="tooltip" target="_blank" title="Youtube">
                                            <i class="fa fa-youtube"></i>
                                        </a>
                                    </li>
                                    <li class="instagram">
                                        <a href="https://www.instagram.com/" data-toggle="tooltip" target="_blank" title="Instagram">
                                            <i class="fa fa-instagram"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- Begin Footer Mớisletter Area -->
                            <div class="footer-newsletter">
                                <h4>Đăng ký tin tức hàng ngày</h4>
                                <form action="#" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="footer-subscribe-form validate" target="_blank" novalidate>
                                    <div id="mc_embed_signup_scroll">
                                        <div id="mc-form" class="mc-form subscribe-form form-group" >
                                            <input id="mc-email" type="email" autocomplete="off" placeholder="Nhập email của bạn" />
                                            <button  class="btn" id="mc-submit">Đăng ký</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- Footer Mớisletter Area End Here -->
                        </div>
                        <!-- Footer Block Area End Here -->
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer Static Middle Area End Here -->
        <!-- Begin Footer Static Bottom Area -->
        <div class="footer-static-bottom pt-55 pb-55">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <!-- Begin Footer Links Area -->
                        <div class="footer-links">
                            <ul>
                                <li><a href="#">Mua sắm online</a></li>
                                <li><a href="#">Khuyến mãi</a></li>
                                <li><a href="#">Đơn của tôi</a></li>
                                <li><a href="#">Trợ giúp</a></li>
                                <li><a href="#">Dịch vụ khách hàng</a></li>
                                <li><a href="#">Hỗ trợ</a></li>
                                <li><a href="#">Nổi tiếng nhất</a></li>
                                <li><a href="#">Hàng mới nhập</a></li>
                                <li><a href="#">Sản phẩm đặc biệt</a></li>
                                <li><a href="#">Cửa hàng chúng tôi</a></li>
                                <li><a href="#">Vận chuyển</a></li>
                                <li><a href="#">Thanh toán</a></li>
                                <li><a href="#">Bảo hành</a></li>
                                <li><a href="#">Hoàn tiền</a></li>
                                <li><a href="#">Giảm giá</a></li>
                                <li><a href="#">Hoàn tiền</a></li>
                                <li><a href="#">Chính sách vận chuyển</a></li>
                            </ul>
                        </div>
                        <!-- Footer Links Area End Here -->
                        <!-- Begin Footer Payment Area -->
                        <div class="copyright text-center">
                            <a href="#">
                                <img style="width: 20%" src="images/payment/1.png" alt="">
                            </a>
                        </div>
                        <!-- Footer Payment Area End Here -->
                        <!-- Begin Copyright Area -->
                        <div class="copyright text-center pt-25">
                            <span><a target="_blank" href="https://www.templateshub.net">Templates Hub</a></span>
                        </div>
                        <!-- Copyright Area End Here -->
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer Static Bottom Area End Here -->
    </div>
    <!-- Footer Area End Here -->
</div>
<!-- Body Wrapper End Here -->
<!-- jQuery-V1.12.4 -->
<script src="js/vendor/jquery-1.12.4.min.js"></script>
<!-- Popper js -->
<script src="js/vendor/popper.min.js"></script>
<!-- Bootstrap V4.1.3 Fremwork js -->
<script src="js/bootstrap.min.js"></script>
<!-- Ajax Mail js -->
<script src="js/ajax-mail.js"></script>
<!-- Meanmenu js -->
<script src="js/jquery.meanmenu.min.js"></script>
<!-- Wow.min js -->
<script src="js/wow.min.js"></script>
<!-- Slick Carousel js -->
<script src="js/slick.min.js"></script>
<!-- Owl Carousel-2 js -->
<script src="js/owl.carousel.min.js"></script>
<!-- Magnific popup js -->
<script src="js/jquery.magnific-popup.min.js"></script>
<!-- Isotope js -->
<script src="js/isotope.pkgd.min.js"></script>
<!-- Imagesloaded js -->
<script src="js/imagesloaded.pkgd.min.js"></script>
<!-- Mixitup js -->
<script src="js/jquery.mixitup.min.js"></script>
<!-- Countdown -->
<script src="js/jquery.countdown.min.js"></script>
<!-- Counterup -->
<script src="js/jquery.counterup.min.js"></script>
<!-- Waypoints -->
<script src="js/waypoints.min.js"></script>
<!-- Barrating -->
<script src="js/jquery.barrating.min.js"></script>
<!-- Jquery-ui -->
<script src="js/jquery-ui.min.js"></script>
<!-- Venobox -->
<script src="js/venobox.min.js"></script>
<!-- Nice Select js -->
<script src="js/jquery.nice-select.min.js"></script>
<!-- ScrollUp js -->
<script src="js/scrollUp.min.js"></script>
<!-- Main/Activator js -->
<script src="js/main.js"></script>

<script type="text/javascript">
                                            function validateDate() {
                                                var inputDate = new Date(document.getElementById("dateInput").value);
                                                var currentDate = new Date();

                                                if (inputDate < currentDate) {
                                                    alert("Input date cannot be before the current date.");
                                                    document.getElementById("dateInput").value = "";
                                                }
                                            }
</script>
<script>
    function incProduct(a) {
        window.location.href = "${pageContext.request.contextPath}/client/process?num=inc&id=" + a;
    }
    function decProduct(a) {
        window.location.href = "${pageContext.request.contextPath}/client/process?num=dec&id=" + a;
    }
    function delProduct(a) {
        window.location.href = "${pageContext.request.contextPath}/client/delcart?id=" + a;
    }
</script>
</body>

<!-- checkout31:27-->
</html>
