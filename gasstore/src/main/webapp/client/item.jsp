<%-- 
    Document   : products
    Created on : May 22, 2023, 9:37:12 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html class="no-js" lang="zxx">



    <!-- single-product31:30-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>${requestScope.product.name}</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="${pageContext.request.contextPath}/client/image/x-icon" href="images/favicon.png">
        <!-- Material Design Iconic Font-V2.2.0 -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/material-design-iconic-font.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/font-awesome.min.css">
        <!-- Font Awesome Stars-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/fontawesome-stars.css">
        <!-- Meanmenu CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/meanmenu.css">
        <!-- owl carousel CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/owl.carousel.min.css">
        <!-- Slick Carousel CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/slick.css">
        <!-- Animate CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/animate.css">
        <!-- Jquery-ui CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/jquery-ui.min.css">
        <!-- Venobox CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/venobox.css">
        <!-- Nice Select CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/nice-select.css">
        <!-- Magnific Popup CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/magnific-popup.css">
        <!-- Bootstrap V4.1.3 Fremwork CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/bootstrap.min.css">
        <!-- Helper CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/helper.css">
        <!-- Main Style CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/style.css">
        <!-- Responsive CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/responsive.css">
        <!-- Modernizr js -->
        <script src="${pageContext.request.contextPath}/client/js/vendor/modernizr-2.8.3.min.js"></script>


    </head>
    <body>
        <fmt:setLocale value = "vi_VN"/>
        <c:set var="o" value="${sessionScope.cart}"/>
        <c:set var="size" value="${sessionScope.size}"/>
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
                            <li><a href="${pageContext.request.contextPath}/client/index">Trang chủ</a></li>
                            <li class="active">Sản phẩm(${requestScope.product.name})</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- Li's Breadcrumb Area End Here -->
            <!-- content-wraper start -->
            <div class="content-wraper">
                <div class="container">
                    <div class="row single-product-area">
                        <div class="col-lg-5 col-md-6">
                            <!-- Product Details Left -->
                            <div class="product-details-left">
                                <div class="product-details-images slider-navigation-1">
                                    <div class="lg-image">
                                        <img src="${pageContext.request.contextPath}/${requestScope.product.base64Image}" alt="Preview" />
                                    </div>
                                </div>
                                <div class="product-details-thumbs slider-thumbs-1 mt-10">                                        
                                    <div class="sm-image"><img src="${pageContext.request.contextPath}/${requestScope.product.base64Image}" alt="Preview" /></div>
                                </div>
                            </div>
                            <!--// Product Details Left -->
                        </div>

                        <div class="col-lg-7 col-md-6">
                            <div class="product-details-view-content pt-60">
                                <div class="product-info">
                                    <h2>${requestScope.product.name}</h2>
                                    <span class="product-details-ref">Reference: ${requestScope.product.keyword}</span>
                                    <fmt:formatNumber value="${requestScope.rateAvg - (requestScope.rateAvg % 1 == 0 ? 0 : 0.5)}"  var="star" type="number" pattern="#" />
                                    <div class="rating-box pt-20">
                                        <span>(<fmt:formatNumber value="${requestScope.rateAvg}" pattern="#0.00" />)</span>
                                        <ul class="rating rating-with-review-item">
                                            <c:forEach begin="1" end="${star}" var="r">
                                                <li><i class="fa fa-star-o"></i></li>
                                                </c:forEach>
                                                <c:forEach begin="1" end="${5-star}">
                                                <li class="no-star"><i class="fa fa-star-o"></i></li>
                                                </c:forEach>
                                            <span>(${requestScope.totalReview} đánh giá)</span>
                                        </ul>
                                    </div>
                                    <div class="price-box pt-20">
                                        <span class="new-price new-price-2"><fmt:formatNumber value = "${Math.round((product.unitPrice - product.unitPrice * product.discount.discount)/10000)*10000}" type = "currency"/></span>
                                    </div>
                                    <div class="product-desc">
                                        <p>
                                            <span>${requestScope.product.shortDescription}
                                            </span>
                                        </p>
                                    </div>
                                    <div class="product-variants">
                                        <div class="produt-variants-size">
                                        </div>
                                    </div>
                                    <div class="single-add-to-cart">
                                        <form action="cartput" method="get" class="cart-quantity">
                                            <div class="quantity">
                                                <label>Quantity</label>
                                                <div class="cart-cong-tru">
                                                    <input class="cart-plus-minus-box" id="num" max="${requestScope.product.stockQuantity}" value="1" min ="1" type="number">
                                                    <div class="dec qtybutton hih"><i class="fa fa-angle-down"></i></div>
                                                    <div class="inc qtybutton hix"><i class="fa fa-angle-up"></i></div>
                                                </div>

                                            </div>
                                            <button onclick="e(${requestScope.product.productId})" class ="add-to-cart"  type="button">Thêm vào giỏ hàng</button>
                                        </form>
                                    </div>
                                    <div class="block-reassurance">
                                        <ul>
                                            <li>
                                                <div class="reassurance-item">
                                                    <div class="reassurance-icon">
                                                        <i class="fa fa-check-square-o"></i>
                                                    </div>
                                                    <p>Security policy (edit with Customer reassurance module)</p>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="reassurance-item">
                                                    <div class="reassurance-icon">
                                                        <i class="fa fa-truck"></i>
                                                    </div>
                                                    <p>Delivery policy (edit with Customer reassurance module)</p>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="reassurance-item">
                                                    <div class="reassurance-icon">
                                                        <i class="fa fa-exchange"></i>
                                                    </div>
                                                    <p> Return policy (edit with Customer reassurance module)</p>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </div>
                </div>
            </div>
            <!-- content-wraper end -->
            <!-- Begin Product Area -->
            <div class="product-area pt-35">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="li-product-tab">
                                <ul class="nav li-product-menu">
                                    <li><a class="active" data-toggle="tab" href="#description"><span>Description</span></a></li>
                                    <li><a data-toggle="tab" href="#product-details"><span>Product Details</span></a></li>
                                    <li><a data-toggle="tab" href="#reviews"><span>Reviews</span></a></li>
                                </ul>               
                            </div>
                            <!-- Begin Li's Tab Menu Content Area -->
                        </div>
                    </div>
                    <div class="tab-content">
                        <div id="description" class="tab-pane active show" role="tabpanel">
                            <div class="product-description">
                                <span>Code: #${requestScope.product.code}<br>${requestScope.product.shortDescription}<br>Keyword: ${requestScope.product.keyword}</span>
                            </div>
                        </div>
                        <div id="product-details" class="tab-pane" role="tabpanel">
                            <div class="product-details-manufacturer">
                                <p>${requestScope.descriptionView}</p>
                                <p><span>Reference</span> ${requestScope.product.keyword}</p>
                            </div>
                        </div>
                        <div id="reviews" class="tab-pane" role="tabpanel">
                            <div class="product-reviews">
                                <div class="product-details-comment-block">
                                    <div class="comment-review">
                                        <span>Đánh giá:</span>
                                        <ul class="rating">
                                            <c:forEach begin="1" end="${star}" var="r">
                                                <li><i class="fa fa-star-o"></i></li>
                                                </c:forEach>
                                                <c:forEach begin="1" end="${5-star}">
                                                <li class="no-star"><i class="fa fa-star-o"></i></li>
                                                </c:forEach>
                                            <span>(${requestScope.totalReview} đánh giá)</span>
                                        </ul>
                                    </div>
                                    <hr>
                                    <div class="review-btn">
                                        <a class="review-links" href="#" data-toggle="modal" data-target="#mymodal">Write Your Review!</a>
                                    </div>
                                    <hr>
                                    <c:if test="${not empty sessionScope.account}">
                                        <h5 class="title">Đánh giá của bạn về sản phẩm </h5>
                                        <hr>
                                        <c:forEach items="${requestScope.reviewCustomer}" var="rc">

                                            <div class="media review container row" style="margin-bottom: 40px;">

                                                <div class="col-md-10">
                                                    <div class="media-left mr-10">
                                                        <img src="data:image/jpg;base64,${rc.customer.base64Image}" class="media-object" style="width:60px">
                                                    </div>
                                                    <div class="media-body">
                                                        <h4 class="media-heading">${rc.customer.firstName } ${rc.customer.lastName}</h4><span>${rc.dateRate}</span>
                                                        <p>${rc.content}</p>
                                                        <ul class="rating">
                                                            <c:forEach begin="1" end="${rc.rate}" var="i">
                                                                <li><i class="fa fa-star-o"></i></li>
                                                                </c:forEach>
                                                                <c:forEach begin="1" end="${5-rc.rate}">
                                                                <li class="no-star"><i class="fa fa-star-o"></i></li>
                                                                </c:forEach>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mt-50">
                                                    <button class="review-button btn btn-primary" onclick="openUpdateReviewModals(${rc.reviewId})">Update</button>

                                                    <button type="button" class="btn btn-dark" onclick="DeleteReview(${rc.reviewId})">Delete</button>
                                                </div>
                                            </div>
                                            <hr>
                                        </c:forEach>
                                    </c:if>

                                    <h5 class="title">Đánh giá của người dùng khác về sản phẩm </h5>
                                    <hr>
                                    <c:forEach items="${requestScope.reviews}" var="review">

                                        <div class="media review container" style="margin-bottom: 40px;">
                                            <div class="media-left mr-10">
                                                <img src="data:image/jpg;base64,${review.customer.base64Image}" class="media-object" style="width:60px">
                                            </div>
                                            <div class="media-body">
                                                <h4 class="media-heading">${review.customer.firstName } ${review.customer.lastName}</h4><span>${review.dateRate}</span>
                                                <p>${review.content}</p>
                                                <ul class="rating">
                                                    <c:forEach begin="1" end="${review.rate}" var="i">
                                                        <li><i class="fa fa-star-o"></i></li>
                                                        </c:forEach>
                                                        <c:forEach begin="1" end="${5-review.rate}">
                                                        <li class="no-star"><i class="fa fa-star-o"></i></li>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <hr>
                                    </c:forEach>

                                    <!-- Begin Quick View | Modal Area -->
                                    <div class="modal fade modal-wrapper" id="mymodal" >
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-body">
                                                    <h3 class="review-page-title">Write Your Review</h3>
                                                    <div class="modal-inner-area row">
                                                        <div class="col-lg-6">
                                                            <div class="li-review-product">
                                                                <img src="${pageContext.request.contextPath}/${requestScope.product.base64Image}" alt="Preview" />
                                                                <div class="li-review-product-desc">
                                                                    <p class="li-product-name">${requestScope.product.name}</p>
                                                                    <p>
                                                                        <span>${requestScope.descriptionView}</span>
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="li-review-content">
                                                                <!-- Begin Feedback Area -->
                                                                <div class="feedback-area">
                                                                    <div class="feedback">
                                                                        <h3 class="feedback-title">Our Feedback</h3>
                                                                        <form action="${pageContext.request.contextPath}/client/updatereview" method="POST">
                                                                            <input type="hidden" name="pid" value="${requestScope.product.productId}"/>
                                                                            <input type="hidden" name="type" value="add"/>
                                                                            <input type="hidden" name="url" value="" id="urlInput"/>
                                                                            <p class="your-opinion">
                                                                                <label>Your Rating</label>
                                                                                <span>
                                                                                    <select name="rate" class="star-rating">
                                                                                        <option value="1">1</option>
                                                                                        <option value="2">2</option>
                                                                                        <option value="3">3</option>
                                                                                        <option value="4">4</option>
                                                                                        <option value="5">5</option>
                                                                                    </select>
                                                                                </span>
                                                                            </p>
                                                                            <p class="feedback-form">
                                                                                <label for="feedback">Your Review</label>
                                                                                <textarea id="feedback" name="comment" cols="45" rows="8" aria-required="true"></textarea>
                                                                            </p>
                                                                            <c:choose>
                                                                                <c:when test="${sessionScope.account.getClass().getName() eq customerClass}">
                                                                                    <div class="feedback-input">
                                                                                        <p class="feedback-form-author">
                                                                                            <label for="author">UserName<span class="required">*</span>
                                                                                            </label>
                                                                                            <input id="author" value="${sessionScope.account.family_name} ${sessionScope.account.given_name}" name="author" value="" size="30" aria-required="true" type="text">
                                                                                        </p>
                                                                                        <p class="feedback-form-author feedback-form-email">
                                                                                            <label for="email">Email<span class="required">*</span>
                                                                                            </label>
                                                                                            <input id="email" value="${sessionScope.account.email}" name="email" value="" size="30" aria-required="true" type="text">
                                                                                            <span class="required"><sub>*</sub> Required fields</span>
                                                                                        </p>
                                                                                        <div class="feedback-btn pb-15">
                                                                                            <a href="#" class="close" data-dismiss="modal" aria-label="Close">Close</a>
                                                                                            <button class="btn btn-primary">Submit</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <div class="feedback-input">
                                                                                        <p class="feedback-form-author">
                                                                                            <label for="author">Name<span class="required">*</span>
                                                                                            </label>
                                                                                            <input id="author" value="${sessionScope.account.firstName} ${sessionScope.account.lastName}" name="author" value="" size="30" aria-required="true" type="text">
                                                                                        </p>
                                                                                        <p class="feedback-form-author feedback-form-email">
                                                                                            <label for="email">Email<span class="required">*</span>
                                                                                            </label>
                                                                                            <input id="email" value="${sessionScope.account.email}" name="email" value="" size="30" aria-required="true" type="text">
                                                                                            <span class="required"><sub>*</sub> Required fields</span>
                                                                                        </p>
                                                                                        <div class="feedback-btn pb-15">
                                                                                            <a href="#" class="close" data-dismiss="modal" aria-label="Close">Close</a>
                                                                                            <button class="btn btn-primary">Submit</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </form>
                                                                    </div>
                                                                </div>
                                                                <!-- Feedback Area End Here -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>   


                                    <div class="modal fade modal-wrapper" id="updateReviewModal">
                                        <!-- Modal content -->
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-body">
                                                    <h3 class="review-page-title">Update Your Review</h3>
                                                    <div class="modal-inner-area row">
                                                        <div class="col-lg-6">
                                                            <div class="li-review-product">
                                                                <img src="${pageContext.request.contextPath}/${requestScope.product.base64Image}" alt="Preview" />
                                                                <div class="li-review-product-desc">
                                                                    <p class="li-product-name">${requestScope.product.name}</p>
                                                                    <p>
                                                                        <span>${requestScope.descriptionView}</span>
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-6">
                                                            <div class="li-review-content">
                                                                <!-- Begin Feedback Area -->
                                                                <div class="feedback-area">
                                                                    <div class="feedback">
                                                                        <h3 class="feedback-title">Our Feedback</h3>
                                                                        <form action="${pageContext.request.contextPath}/client/updatereview" method="POST">
                                                                            <input type="hidden" name="type" value="update"/>
                                                                            <input type="hidden" name="url" value="" id="url"/>
                                                                            <input type="hidden" name="rid" value="" id="rid"/>
                                                                            <p class="your-opinion">
                                                                                <label>Your Rating</label>
                                                                                <span>
                                                                                    <select name="rate2" class="star-rating">
                                                                                        <option value="1">1</option>
                                                                                        <option value="2">2</option>
                                                                                        <option value="3">3</option>
                                                                                        <option value="4">4</option>
                                                                                        <option selected value="5">5</option>
                                                                                    </select>
                                                                                </span>
                                                                            </p>
                                                                            <p class="feedback-form">
                                                                                <label for="feedback">Your Review</label>
                                                                                <textarea id="feedback" name="comment2" cols="45" rows="8" aria-required="true"></textarea>
                                                                            </p>
                                                                            <c:choose>
                                                                                <c:when test="${sessionScope.account.getClass().getName() eq customerClass}">
                                                                                    <div class="feedback-input">
                                                                                        <p class="feedback-form-author">
                                                                                            <label for="author">UserName<span class="required">*</span>
                                                                                            </label>
                                                                                            <input id="author" value="${sessionScope.account.family_name} ${sessionScope.account.given_name}" name="author" value="" size="30" aria-required="true" type="text">
                                                                                        </p>
                                                                                        <p class="feedback-form-author feedback-form-email">
                                                                                            <label for="email">Email<span class="required">*</span>
                                                                                            </label>
                                                                                            <input id="email" value="${sessionScope.account.email}" name="email" value="" size="30" aria-required="true" type="text">
                                                                                            <span class="required"><sub>*</sub> Required fields</span>
                                                                                        </p>
                                                                                        <div class="feedback-btn pb-15">
                                                                                            <a href="#" class="close" data-dismiss="modal" aria-label="Close">Close</a>
                                                                                            <button class="btn btn-primary">Submit</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <div class="feedback-input">
                                                                                        <p class="feedback-form-author">
                                                                                            <label for="author">Name<span class="required">*</span>
                                                                                            </label>
                                                                                            <input id="author" value="${sessionScope.account.firstName} ${sessionScope.account.lastName}" name="author" value="" size="30" aria-required="true" type="text">
                                                                                        </p>
                                                                                        <p class="feedback-form-author feedback-form-email">
                                                                                            <label for="email">Email<span class="required">*</span>
                                                                                            </label>
                                                                                            <input id="email" value="${sessionScope.account.email}" name="email" value="" size="30" aria-required="true" type="text">
                                                                                            <span class="required"><sub>*</sub> Required fields</span>
                                                                                        </p>
                                                                                        <div class="feedback-btn pb-15">
                                                                                            <a href="#" class="close" data-dismiss="modal" aria-label="Close">Close</a>
                                                                                            <button type="submit" class="btn btn-primary">Submit</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </form>
                                                                    </div>
                                                                </div>
                                                                <!-- Feedback Area End Here -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Quick View | Modal Area End Here -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Product Area End Here -->
            <!-- Begin Li's Laptop Product Area -->
            <section class="product-area li-laptop-product Special-product pt-60 pb-45">
                <div class="container">
                    <div class="row">
                        <!-- Begin Li's Section Area -->
                        <div class="col-lg-12">
                            <div class="li-section-title">
                                <h2>
                                    <span>Sản phẩm tương tự</span>
                                </h2>
                            </div>
                            <div class="row">
                                <div class="special-product-active owl-carousel">
                                    <c:forEach items="${requestScope.sameProduct}" var="binh">
                                        <div class="col-lg-12">
                                            <!-- single-product-wrap start -->
                                            <div class="single-product-wrap">
                                                <div class="product-image">
                                                    <a href="${pageContext.request.contextPath}/client/item/${binh.code}">
                                                        <img  src="${pageContext.request.contextPath}/${binh.base64Image}" alt="Preview" />
                                                    </a>
                                                </div>
                                                <div class="product_desc">
                                                    <div class="product_desc_info">
                                                        <div class="product-review">
                                                            <h5 class="manufacturer">
                                                                <a href="${pageContext.request.contextPath}/client/category/${binh.category.code}">${binh.category.name}</a>
                                                            </h5>
                                                            <div class="rating-box">
                                                                <ul class="rating">
                                                                    <li><i class="fa fa-star-o"></i></li>
                                                                    <li><i class="fa fa-star-o"></i></li>
                                                                    <li><i class="fa fa-star-o"></i></li>
                                                                    <li class="no-star"><i class="fa fa-star-o"></i></li>
                                                                    <li class="no-star"><i class="fa fa-star-o"></i></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <h4><a class="product_name" href="${pageContext.request.contextPath}/client/item/${binh.code}">${binh.name}</a></h4>
                                                        <div class="price-box">
                                                            <span class="new-price new-price-2"><fmt:formatNumber value = "${Math.round((binh.unitPrice - binh.unitPrice * binh.discount.discount)/10000)*10000}" type = "currency"/></span>
                                                            <span class="old-price"><fmt:formatNumber value = "${binh.unitPrice}" type = "currency"/></span>
                                                            <span class="discount-percentage"><fmt:formatNumber value = "${binh.discount.discount}" type = "percent"/></span>
                                                        </div>
                                                    </div>
                                                    <div class="add-actions">
                                                        <ul class="add-actions-link">
                                                            <li onclick="d(${binh.productId})" class="add-cart active">Thêm vào giỏ hàng</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- single-product-wrap end -->
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <!-- Li's Section Area End Here -->
                    </div>
                </div>
            </section>
            <!-- Li's Laptop Product Area End Here -->
            <!-- Begin Footer Area -->
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
                                            <img src="${pageContext.request.contextPath}/client/images/shipping-icon/1.png" alt="Shipping Icon">
                                        </div>
                                        <div class="shipping-text">
                                            <h2>Free Delivery</h2>
                                            <p>And free returns. See checkout for delivery dates.</p>
                                        </div>
                                    </div>
                                </div>
                                <!-- Li's Shipping Inner Box Area End Here -->
                                <!-- Begin Li's Shipping Inner Box Area -->
                                <div class="col-lg-3 col-md-6 col-sm-6 pb-sm-55 pb-xs-55">
                                    <div class="li-shipping-inner-box">
                                        <div class="shipping-icon">
                                            <img src="${pageContext.request.contextPath}/client/images/shipping-icon/2.png" alt="Shipping Icon">
                                        </div>
                                        <div class="shipping-text">
                                            <h2>Safe Payment</h2>
                                            <p>Pay with the world's most popular and secure payment methods.</p>
                                        </div>
                                    </div>
                                </div>
                                <!-- Li's Shipping Inner Box Area End Here -->
                                <!-- Begin Li's Shipping Inner Box Area -->
                                <div class="col-lg-3 col-md-6 col-sm-6 pb-xs-30">
                                    <div class="li-shipping-inner-box">
                                        <div class="shipping-icon">
                                            <img src="${pageContext.request.contextPath}/client/images/shipping-icon/3.png" alt="Shipping Icon">
                                        </div>
                                        <div class="shipping-text">
                                            <h2>Shop with Confidence</h2>
                                            <p>Our Buyer Protection covers your purchasefrom click to delivery.</p>
                                        </div>
                                    </div>
                                </div>
                                <!-- Li's Shipping Inner Box Area End Here -->
                                <!-- Begin Li's Shipping Inner Box Area -->
                                <div class="col-lg-3 col-md-6 col-sm-6 pb-xs-30">
                                    <div class="li-shipping-inner-box">
                                        <div class="shipping-icon">
                                            <img src="${pageContext.request.contextPath}/client/images/shipping-icon/4.png" alt="Shipping Icon">
                                        </div>
                                        <div class="shipping-text">
                                            <h2>24/7 Help Center</h2>
                                            <p>Have a question? Call a Specialist or chat online.</p>
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
                                        <img src="${pageContext.request.contextPath}/client/images/menu/logo/1.jpg" alt="Footer Logo">
                                        <p class="info">
                                            We are a team of designers and developers that create high quality HTML Template & Woocommerce, Shopify Theme.
                                        </p>
                                    </div>
                                    <ul class="des">
                                        <li>
                                            <span>Address: </span>
                                            6688Princess Road, London, Greater London BAS 23JK, UK
                                        </li>
                                        <li>
                                            <span>Phone: </span>
                                            <a href="#">(+123) 123 321 345</a>
                                        </li>
                                        <li>
                                            <span>Email: </span>
                                            <a href="mailto://info@yourdomain.com">info@yourdomain.com</a>
                                        </li>
                                    </ul>
                                </div>
                                <!-- Footer Logo Area End Here -->
                                <!-- Begin Footer Block Area -->
                                <div class="col-lg-2 col-md-3 col-sm-6">
                                    <div class="footer-block">
                                        <h3 class="footer-block-title">Product</h3>
                                        <ul>
                                            <li><a href="#">Prices drop</a></li>
                                            <li><a href="#">New products</a></li>
                                            <li><a href="#">Best sales</a></li>
                                            <li><a href="#">Contact us</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- Footer Block Area End Here -->
                                <!-- Begin Footer Block Area -->
                                <div class="col-lg-2 col-md-3 col-sm-6">
                                    <div class="footer-block">
                                        <h3 class="footer-block-title">Our company</h3>
                                        <ul>
                                            <li><a href="#">Delivery</a></li>
                                            <li><a href="#">Legal Notice</a></li>
                                            <li><a href="#">About us</a></li>
                                            <li><a href="#">Contact us</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- Footer Block Area End Here -->
                                <!-- Begin Footer Block Area -->
                                <div class="col-lg-4">
                                    <div class="footer-block">
                                        <h3 class="footer-block-title">Follow Us</h3>
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
                                    <!-- Begin Footer Newsletter Area -->
                                    <div class="footer-newsletter">
                                        <h4>Sign up to newsletter</h4>
                                        <form action="#" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="footer-subscribe-form validate" target="_blank" novalidate>
                                            <div id="mc_embed_signup_scroll">
                                                <div id="mc-form" class="mc-form subscribe-form form-group" >
                                                    <input id="mc-email" type="email" autocomplete="off" placeholder="Enter your email" />
                                                    <button  class="btn" id="mc-submit">Subscribe</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <!-- Footer Newsletter Area End Here -->
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
                                        <li><a href="#">Online Shopping</a></li>
                                        <li><a href="#">Promotions</a></li>
                                        <li><a href="#">My Orders</a></li>
                                        <li><a href="#">Help</a></li>
                                        <li><a href="#">Customer Service</a></li>
                                        <li><a href="#">Support</a></li>
                                        <li><a href="#">Most Populars</a></li>
                                        <li><a href="#">New Arrivals</a></li>
                                        <li><a href="#">Special Products</a></li>
                                        <li><a href="#">Manufacturers</a></li>
                                        <li><a href="#">Our Stores</a></li>
                                        <li><a href="#">Shipping</a></li>
                                        <li><a href="#">Payments</a></li>
                                        <li><a href="#">Warantee</a></li>
                                        <li><a href="#">Refunds</a></li>
                                        <li><a href="#">Checkout</a></li>
                                        <li><a href="#">Discount</a></li>
                                        <li><a href="#">Refunds</a></li>
                                        <li><a href="#">Policy Shipping</a></li>
                                    </ul>
                                </div>
                                <!-- Footer Links Area End Here -->
                                <!-- Begin Footer Payment Area -->
                                <div class="copyright text-center">
                                    <a href="#">
                                        <img style="width: 25%" src="${pageContext.request.contextPath}/client/images/payment/1.png" alt="">
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
            <!-- Begin Quick View | Modal Area -->
            <!-- Quick View | Modal Area End Here -->
        </div>
        <!-- Body Wrapper End Here -->
        <!-- jQuery-V1.12.4 -->
        <script src="${pageContext.request.contextPath}/client/js/vendor/jquery-1.12.4.min.js"></script>
        <!-- Popper js -->
        <script src="${pageContext.request.contextPath}/client/js/vendor/popper.min.js"></script>
        <!-- Bootstrap V4.1.3 Fremwork js -->
        <script src="${pageContext.request.contextPath}/client/js/bootstrap.min.js"></script>
        <!-- Ajax Mail js -->
        <script src="${pageContext.request.contextPath}/client/js/ajax-mail.js"></script>
        <!-- Meanmenu js -->
        <script src="${pageContext.request.contextPath}/client/js/jquery.meanmenu.min.js"></script>
        <!-- Wow.min js -->
        <script src="${pageContext.request.contextPath}/client/js/wow.min.js"></script>
        <!-- Slick Carousel js -->
        <script src="${pageContext.request.contextPath}/client/js/slick.min.js"></script>
        <!-- Owl Carousel-2 js -->
        <script src="${pageContext.request.contextPath}/client/js/owl.carousel.min.js"></script>
        <!-- Magnific popup js -->
        <script src="${pageContext.request.contextPath}/client/js/jquery.magnific-popup.min.js"></script>
        <!-- Isotope js -->
        <script src="${pageContext.request.contextPath}/client/js/isotope.pkgd.min.js"></script>
        <!-- Imagesloaded js -->
        <script src="${pageContext.request.contextPath}/client/js/imagesloaded.pkgd.min.js"></script>
        <!-- Mixitup js -->
        <script src="${pageContext.request.contextPath}/client/js/jquery.mixitup.min.js"></script>
        <!-- Countdown -->
        <script src="${pageContext.request.contextPath}/client/js/jquery.countdown.min.js"></script>
        <!-- Counterup -->
        <script src="${pageContext.request.contextPath}/client/js/jquery.counterup.min.js"></script>
        <!-- Waypoints -->
        <script src="${pageContext.request.contextPath}/client/js/waypoints.min.js"></script>
        <!-- Barrating -->
        <script src="${pageContext.request.contextPath}/client/js/jquery.barrating.min.js"></script>
        <!-- Jquery-ui -->
        <script src="${pageContext.request.contextPath}/client/js/jquery-ui.min.js"></script>
        <!-- Venobox -->
        <script src="${pageContext.request.contextPath}/client/js/venobox.min.js"></script>
        <!-- Nice Select js -->
        <script src="${pageContext.request.contextPath}/client/js/jquery.nice-select.min.js"></script>
        <!-- ScrollUp js -->
        <script src="${pageContext.request.contextPath}/client/js/scrollUp.min.js"></script>
        <!-- Main/Activator js -->
        <script src="${pageContext.request.contextPath}/client/js/main.js"></script>

        <script>

                                                                function d(a) {
                                                                    var url = window.location.href;
                                                                    window.location.replace("${pageContext.request.contextPath}/client/cartput?id=" + a + "&url=" + url);
                                                                }

                                                                function e(a) {
                                                                    var url = window.location.href;
                                                                    var num = document.getElementById("num").value;
                                                                    window.location.replace("${pageContext.request.contextPath}/client/cartput?id=" + a + "&num=" + num + "&url=" + url);
                                                                }

                                                                document.getElementById("urlInput").value = window.location.href;
                                                                document.getElementById("url").value = window.location.href;

                                                                function DeleteReview(reviewId) {
                                                                    var currentURL = window.location.href;
                                                                    if (confirm("Are you sure you want to delete this review?")) {
                                                                        window.location.href = "${pageContext.request.contextPath}/client/updatereview?type=delete&id=" + reviewId + "&url=" + currentURL;
                                                                    }
                                                                }


                                                                function openUpdateReviewModals(reviewId) {
                                                                    document.getElementById("rid").value = reviewId;
            <c:forEach items="${requestScope.reviewCustomer}" var="c">
                                                                    var currentReviewId = ${c.reviewId};
                                                                    if (currentReviewId == reviewId) {
                                                                        var rate = ${c.rate};

                                                                        var content = "${c.content}";

                                                                    }
            </c:forEach>

                                                                    $('#updateReviewModal select[name="rate2"]').val(rate);
                                                                    $('#updateReviewModal textarea[name="comment2"]').val(content);
                                                                    $('#updateReviewModal').modal('show');
                                                                }

        </script>
    </body>

    <!-- single-product31:32-->

</html>

