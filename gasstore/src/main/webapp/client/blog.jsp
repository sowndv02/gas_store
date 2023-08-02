<%-- 
    Document   : blog
    Created on : May 17, 2023, 2:07:31 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">

    <!-- blog-left-sidebar31:50-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Blog</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="images/favicon.png">
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
                        <li><a href="index.html">Home</a></li>
                        <li class="active">Blog Left Sidebar</li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Li's Breadcrumb Area End Here -->
        <!-- Begin Li's Main Blog Page Area -->
        <div class="li-main-blog-page pt-60 pb-55">
            <div class="container">
                <div class="row">
                    <!-- Begin Li's Blog Sidebar Area -->
                    <div class="col-lg-3 order-lg-1 order-2">
                        <div class="li-blog-sidebar-wrapper">
                            <div class="li-blog-sidebar">
                                <div class="li-sidebar-search-form">
                                    <form action="#">
                                        <input type="text" class="li-search-field" placeholder="search here">
                                        <button type="submit" class="li-search-btn"><i class="fa fa-search"></i></button>
                                    </form>
                                </div>
                            </div>
                            <div class="li-blog-sidebar pt-25">
                                <h4 class="li-blog-sidebar-title">Loại Blog</h4>
                                <ul class="li-blog-archive">
                                    <c:forEach items="${requestScope.typeblogs}" var ="typeblog">
                                        <c:if test="${typeNumber.get(typeblog.typeId) == null}">
                                            <li><a href="${pageContext.request.contextPath}/client/type/${typeblog.code}">${typeblog.typeName} (0)</a></li>
                                            </c:if>
                                            <c:if test="${typeNumber.get(typeblog.typeId) != null}">
                                            <li><a href="${pageContext.request.contextPath}/client/type/${typeblog.code}">${typeblog.typeName} (${typeNumber.get(typeblog.typeId)})</a></li>
                                            </c:if>
                                        </c:forEach>
                                </ul>
                            </div>
                            <div class="li-blog-sidebar">
                                <h4 class="li-blog-sidebar-title">Blog theo tháng</h4>
                                <ul class="li-blog-archive">
                                    <c:forEach items="${months}" var="month">
                                        <li><a href="blog?month=${month}">${monthNumberText.get(month)} (${dateNumberBlog.get(month)})</a></li>
                                        </c:forEach>
                            </div>
                            <div class="li-blog-sidebar">
                                <h4 class="li-blog-sidebar-title">Blog gần đây</h4>
                                <c:forEach items="${recentBlogs}" var="recentBlog">
                                    <div class="li-recent-post pb-30">
                                        <div class="li-recent-post-thumb">
                                            <a href="blogdetails/${recentBlog.code}">
                                                <img class="img-full" style="width: 50px" src="${pageContext.request.contextPath}/${recentBlog.base64Image}" alt="Li's Product Image">
                                            </a>
                                        </div>
                                        <div class="li-recent-post-des">
                                            <span><a href="blogdetails/${recentBlog.code}">${recentBlog.title}</a></span>
                                            <span class="li-post-date">${recentBlog.datePost}</span>
                                        </div>
                                    </div>

                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!-- Li's Blog Sidebar Area End Here -->
                    <!-- Begin Li's Main Content Area -->

                    <div class="col-lg-9 order-lg-2 order-1">
                        <div class="row li-main-content">

                            <c:forEach items="${blogs}" var="blog">
                                <div class="col-lg-6 col-md-6">
                                    <div class="li-blog-single-item pb-25">
                                        <div class="li-blog-banner">
                                            <a href="blogdetails/${blog.code}"><img class="img-full" src="${pageContext.request.contextPath}/${blog.base64Image}" alt=""></a>
                                        </div>
                                        <div class="li-blog-content">
                                            <div class="li-blog-details">
                                                <h3 class="li-blog-heading pt-25"><a href="blogdetails/${blog.code}">${blog.title}</a></h3>
                                                <div class="li-blog-meta">
                                                    <a class="author" href="#"><i class="fa fa-user"></i>${mapAdministratorName.get(blog.adminId)}</a>
                                                        <c:if test="${mapCommentNumber.get(blog.blogId) == null}">
                                                        <a class="comment" href="#"><i class="fa fa-comment-o"></i> 0 COMMENT</a>
                                                    </c:if>
                                                    <c:if test="${mapCommentNumber.get(blog.blogId) != null}">
                                                        <a class="comment" href="#"><i class="fa fa-comment-o"></i> ${mapCommentNumber.get(blog.blogId)} COMMENT</a>
                                                    </c:if>
                                                    <a class="post-time" href="#"><i class="fa fa-calendar"></i> ${blog.datePost}</a>
                                                </div>
                                                <p>${blog.description}</p>
                                                <a class="read-more" href="blogdetails/${blog.code}">Read More...</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>        
                            </c:forEach>

                            <!-- Begin Li's Pagination Area -->
                            <c:if test="${typeId == null && month == null}">
                                <div class="col-lg-12">
                                    <div class="li-paginatoin-area text-center pt-25">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <ul class="li-pagination-box">
                                                    <c:if test="${page > 1}">
                                                        <li><a class="Previous" href="blog?page=${page-1}">Previous</a></li>
                                                        </c:if>
                                                        <c:forEach begin="1" end="${numberOfPage}" var="i">
                                                            <c:if test="${page == i}">
                                                            <li class="active"><a href="blog?page=${i}">${i}</a></li>
                                                            </c:if>
                                                            <c:if test="${page != i}">
                                                            <li><a href="blog?page=${i}">${i}</a></li>
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${page < numberOfPage}">
                                                        <li><a class="Next" href="blog?page=${page+1}">Next</a></li>
                                                        </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${typeId != null && month == null}">
                                <div class="col-lg-12">
                                    <div class="li-paginatoin-area text-center pt-25">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <ul class="li-pagination-box">
                                                    <c:if test="${page > 1}">
                                                        <li><a class="Previous" href="blog?typeid=${typeId}&page=${page-1}">Previous</a></li>
                                                        </c:if>
                                                        <c:forEach begin="1" end="${numberOfPage}" var="i">
                                                            <c:if test="${page == i}">
                                                            <li class="active"><a href="blog?typeid=${typeId}&page=${i}">${i}</a></li>
                                                            </c:if>
                                                            <c:if test="${page != i}">
                                                            <li><a href="blog?typeid=${typeId}&page=${i}">${i}</a></li>
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${page < numberOfPage}">
                                                        <li><a class="Next" href="blog?typeid=${typeId}&page=${page+1}">Next</a></li>
                                                        </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${month != null && typeId == null}">
                                <div class="col-lg-12">
                                    <div class="li-paginatoin-area text-center pt-25">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <ul class="li-pagination-box">
                                                    <c:if test="${page > 1}">
                                                        <li><a class="Previous" href="blog?month=${month}&page=${page-1}">Previous</a></li>
                                                        </c:if>
                                                        <c:forEach begin="1" end="${numberOfPage}" var="i">
                                                            <c:if test="${page == i}">
                                                            <li class="active"><a href="blog?month=${month}&page=${i}">${i}</a></li>
                                                            </c:if>
                                                            <c:if test="${page != i}">
                                                            <li><a href="blog?month=${month}&page=${i}">${i}</a></li>
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${page < numberOfPage}">
                                                        <li><a class="Next" href="blog?month=${month}&page=${page+1}">Next</a></li>
                                                        </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>


                            <!-- Li's Pagination End Here Area -->
                        </div>
                    </div>
                    <!-- Li's Main Content Area End Here -->
                </div>
            </div>
        </div>
        <!-- Li's Main Blog Page Area End Here -->
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
                                        <img src="images/shipping-icon/1.png" alt="Shipping Icon">
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
                                        <img src="images/shipping-icon/2.png" alt="Shipping Icon">
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
                                        <img src="images/shipping-icon/3.png" alt="Shipping Icon">
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
                                        <img src="images/shipping-icon/4.png" alt="Shipping Icon">
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
                                    <img src="images/menu/logo/1.jpg" alt="Footer Logo">
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
                                            <a href="https://www.plus.google.com/discover" data-toggle="tooltip" target="_blank" title="Google Plus">
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

<!-- blog-left-sidebar31:55-->
</html>

