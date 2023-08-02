<%-- 
    Document   : index-2
    Created on : May 15, 2023, 9:43:20 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<script>
    function a(a) {
        $.get("delcart?id=" + a, function (data) {
            $(".del" + a).html(data);
        });
        $.get("process1?id=" + a, function (data) {
            $(".minicart-total").html(data);
        });
        $.get("process3?id=" + a, function (data) {
            $(".cart-item-count").html(data);
        });

    }
    function b(a) {
        $.get("process?num=inc&id=" + a, function (data) {
            $(".ad" + a).html(data);
        });
        $.get("process1?id=" + a, function (data) {
            $(".minicart-total").html(data);
        });
        $.get("process2?id=" + a, function (data) {
            $(".qty" + a).html(data);
        });

    }
    function c(a) {
        $.get("process?num=dec&id=" + a, function (data) {
            $(".ad" + a).html(data);
        });
        $.get("process1?id=" + a, function (data) {
            $(".minicart-total").html(data);
        });
        $.get("process2?id=" + a, function (data) {
            $(".qty" + a).html(data);
        });
    }

    function d(a) {
        var url = window.location.href;
        window.location.replace("cartput?id=" + a + "&url=" + url);
    }
    function e(a) {
        var url = window.location.href;
        var num = document.getElementById("num").value;
        window.location.replace("cartput?id=" + a + "&num=" + num + "&url=" + url);
    }
</script>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Home</title>
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


    <!--[if lt IE 8]>
        <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <!-- Begin Body Wrapper -->
    <div class="body-wrapper">
        <!-- Begin Header Area -->
        <%@include file="header.jsp"%>
        <!-- Header Area End Here -->
        <!-- Begin Slider With Category Menu Area -->
        <div class="slider-with-banner">
            <div class="container">
                <div class="row">
                    <!-- Begin Category Menu Area -->
                    <div class="col-lg-3">
                        <!--Category Menu Start-->
                        <div class="category-menu">
                            <div class="category-heading">
                                <h2 class="categories-toggle"><span>PHÂN MỤC</span></h2>
                            </div>
                            <div id="cate-toggle" class="category-menu-list">
                                <ul>
                                    <c:forEach items="${requestScope.categories}" var="category">
                                        <li><a href="${pageContext.request.contextPath}/client/category/${category.code}">${category.name}</a></li>
                                        </c:forEach>
                                    <li><a href="${pageContext.request.contextPath}/client/category/giam-gia-0">Sản phẩm giảm giá</a></li>

                                </ul>
                            </div>
                        </div>
                        <!--Category Menu End-->
                    </div>
                    <!-- Category Menu Area End Here -->
                    <!-- Begin Slider Area -->
                    <div class="col-lg-9">
                        <div class="slider-area pt-sm-30 pt-xs-30">
                            <div class="slider-active owl-carousel">
                                <!-- Begin Single Slide Area -->
                                <div class="single-slide align-center-left animation-style-02 bg-3">
                                    <div class="slider-progress"></div>
                                    <div class="slider-content">
                                        <h5 style="background: black" class="text-light">Chính sách bảo hành</h5>
                                        <div class="default-btn slide-btn">
                                            <a class="links text-white " href="${pageContext.request.contextPath}/client/contact">Liên hệ</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- Single Slide Area End Here -->
                                <!-- Begin Single Slide Area -->
                                <div class="single-slide align-center-left animation-style-01 bg-1">
                                    <div class="slider-progress"></div>
                                    <div class="slider-content">
                                        <h5 style="background: black;" class="text-white">Ưu đãi Hấp dẫn</h5>
                                        <br>
                                        <br>
                                        <br>
                                        <br>
                                        <div class="default-btn slide-btn">
                                            <a  class="text-white links" href="tel:+84969986884">Gọi</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- Single Slide Area End Here -->
                                <!-- Begin Single Slide Area -->
                                <div class="single-slide align-center-left animation-style-02 bg-2">
                                    <div class="slider-progress"></div>
                                    <div class="slider-content">
                                        <h5 class="text-light">Tư vấn miễn phí chất lượng</h5>
                                        <div class="default-btn slide-btn">
                                            <a class="links text-white " href="tel:+84969986884">Mua ngay</a>
                                        </div>
                                    </div>
                                </div>
                                <!-- Single Slide Area End Here -->
                            </div>
                        </div>
                    </div>
                    <!-- Slider Area End Here -->
                </div>
            </div>
        </div>
        <!-- Slider With Category Menu Area End Here -->
        <!-- Begin Li's Static Banner Area -->


        <!-- Li's Static Banner Area End Here -->
        <!-- Begin Li's Special Product Area -->
        <section class="product-area li-laptop-product Special-product pt-60 pb-45">
            <div class="container">
                <div class="row">
                    <!-- Begin Li's Section Area -->
                    <div class="col-lg-12">
                        <div class="li-section-title">
                            <h2>
                                <span>Bình Gas</span>
                            </h2>
                        </div>
                        <div class="row">
                            <div class="special-product-active owl-carousel">
                                <c:forEach items="${requestScope.binhgas}" var="binh">
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
        <!-- Li's Special Product Area End Here -->
        <!-- Begin Li's Laptops Product | Home V2 Area -->
        <section class="product-area li-laptop-product Special-product pt-60 pb-45">
            <div class="container">
                <div class="row">
                    <!-- Begin Li's Section Area -->
                    <div class="col-lg-12">
                        <div class="li-section-title">
                            <h2>
                                <span>Bếp Gas</span>
                            </h2>
                        </div>
                        <div class="row">
                            <div class="special-product-active owl-carousel">
                                <c:forEach items="${requestScope.bepgas}" var="bep">
                                    <div class="col-lg-12">
                                        <!-- single-product-wrap start -->
                                        <div class="single-product-wrap">
                                            <div class="product-image">
                                                <a href="${pageContext.request.contextPath}/client/item/${bep.code}">
                                                    <img  src="${pageContext.request.contextPath}/${bep.base64Image}" alt="Preview" />
                                                </a>
                                            </div>
                                            <div class="product_desc">
                                                <div class="product_desc_info">
                                                    <div class="product-review">
                                                        <h5 class="manufacturer">
                                                            <a href="${pageContext.request.contextPath}/client/category/${bep.category.code}">${bep.category.name}</a>
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
                                                    <h4><a class="product_name" href="${pageContext.request.contextPath}/client/item/${bep.code}">${bep.name}</a></h4>
                                                    <div class="price-box">
                                                        <span class="new-price new-price-2"><fmt:formatNumber value = "${Math.round((bep.unitPrice - bep.unitPrice * bep.discount.discount)/10000)*10000}" type = "currency"/></span>
                                                        <span class="old-price"><fmt:formatNumber value = "${bep.unitPrice}" type = "currency"/></span>
                                                        <span class="discount-percentage"><fmt:formatNumber value = "${bep.discount.discount}" type = "percent"/></span>
                                                    </div>
                                                </div>
                                                <div class="add-actions">
                                                    <ul class="add-actions-link">
                                                        <li onclick="d(${bep.productId})" class="add-cart active">Thêm vào giỏ hàng</li>
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

        <section class="product-area li-laptop-product Special-product pt-60 pb-45">
            <div class="container">
                <div class="row">
                    <!-- Begin Li's Section Area -->
                    <div class="col-lg-12">
                        <div class="li-section-title">
                            <h2>
                                <span>Phụ kiện</span>
                            </h2>
                        </div>
                        <div class="row">
                            <div class="special-product-active owl-carousel">
                                <c:forEach items="${requestScope.phukien}" var="pk">
                                    <div class="col-lg-12">
                                        <!-- single-product-wrap start -->
                                        <div class="single-product-wrap">
                                            <div class="product-image">
                                                <a href="${pageContext.request.contextPath}/client/item/${pk.code}">
                                                    <img  src="${pageContext.request.contextPath}/${pk.base64Image}" alt="Preview" />
                                                </a>
                                            </div>
                                            <div class="product_desc">
                                                <div class="product_desc_info">
                                                    <div class="product-review">
                                                        <h5 class="manufacturer">
                                                            <a href="${pageContext.request.contextPath}/client/category/${pk.category.code}">${pk.category.name}</a>
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
                                                    <h4><a class="product_name" href="${pageContext.request.contextPath}/client/item/${pk.code}">${pk.name}</a></h4>
                                                    <div class="price-box">
                                                        <span class="new-price new-price-2"><fmt:formatNumber value = "${Math.round((pk.unitPrice - pk.unitPrice*pk.discount.discount)/10000)*10000}" type = "currency"/></span>
                                                        <span class="old-price"><fmt:formatNumber value = "${pk.unitPrice}" type = "currency"/></span>
                                                        <span class="discount-percentage"><fmt:formatNumber value = "${pk.discount.discount}" type = "percent"/></span>
                                                    </div>
                                                </div>
                                                <div class="add-actions">
                                                    <ul class="add-actions-link">
                                                        <li onclick="d(${pk.productId})" class="add-cart active">Thêm vào giỏ hàng</li>
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
        <!-- Li's Trending Product | Home V2 Area End Here -->
        <!-- Begin Footer Area -->
        <%@include file="footer.jsp"%>
        <!-- Footer Area End Here -->
        <!-- Begin Quick View | Modal Area -->
        <div class="modal fade modal-wrapper" id="exampleModalCenter" >
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <div class="modal-inner-area row">
                            <div class="col-lg-5 col-md-6 col-sm-6">
                                <!-- Product Details Left -->
                                <div class="product-details-left">
                                    <div class="product-details-images slider-navigation-1">
                                        <div class="lg-image">
                                            <img src="images/product/large-size/1.jpg" alt="product image">
                                        </div>
                                        <div class="lg-image">
                                            <img src="images/product/large-size/2.jpg" alt="product image">
                                        </div>
                                        <div class="lg-image">
                                            <img src="images/product/large-size/3.jpg" alt="product image">
                                        </div>
                                        <div class="lg-image">
                                            <img src="images/product/large-size/4.jpg" alt="product image" wi>
                                        </div>
                                        <div class="lg-image">
                                            <img src="images/product/large-size/5.jpg" alt="product image">
                                        </div>
                                        <div class="lg-image">
                                            <img src="images/product/large-size/6.jpg" alt="product image">
                                        </div>
                                    </div>
                                    <div class="product-details-thumbs slider-thumbs-1">                                        
                                        <div class="sm-image"><img src="images/product/small-size/1.jpg" alt="product image thumb"></div>
                                        <div class="sm-image"><img src="images/product/small-size/2.jpg" alt="product image thumb"></div>
                                        <div class="sm-image"><img src="images/product/small-size/3.jpg" alt="product image thumb"></div>
                                        <div class="sm-image"><img src="images/product/small-size/4.jpg" alt="product image thumb"></div>
                                        <div class="sm-image"><img src="images/product/small-size/5.jpg" alt="product image thumb"></div>
                                        <div class="sm-image"><img src="images/product/small-size/6.jpg" alt="product image thumb"></div>
                                    </div>
                                </div>
                                <!--// Product Details Left -->
                            </div>

                            <div class="col-lg-7 col-md-6 col-sm-6">
                                <div class="product-details-view-content pt-60">
                                    <div class="product-info">
                                        <h2>Today is a good day Framed poster</h2>
                                        <span class="product-details-ref">Reference: demo_15</span>
                                        <div class="rating-box pt-20">
                                            <ul class="rating rating-with-review-item">
                                                <li><i class="fa fa-star-o"></i></li>
                                                <li><i class="fa fa-star-o"></i></li>
                                                <li><i class="fa fa-star-o"></i></li>
                                                <li class="no-star"><i class="fa fa-star-o"></i></li>
                                                <li class="no-star"><i class="fa fa-star-o"></i></li>
                                                <li class="review-item"><a href="#">Đọc đánh giá</a></li>
                                                <li class="review-item"><a href="#">Viết đánh giá</a></li>
                                            </ul>
                                        </div>
                                        <div class="price-box pt-20">
                                            <span class="new-price new-price-2">$57.98</span>
                                        </div>
                                        <div class="product-desc">
                                            <p>
                                                <span>100% cotton double printed dress. Black and white striped top and orange high waisted skater skirt bottom. Lorem ipsum dolor sit amet, consectetur adipisicing elit. quibusdam corporis, earum facilis et nostrum dolorum accusamus similique eveniet quia pariatur.
                                                </span>
                                            </p>
                                        </div>
                                        <div class="product-variants">
                                            <div class="produt-variants-size">
                                                <label>Kích thước</label>
                                                <select class="nice-select">
                                                    <option value="1" title="S" selected="selected">40x60cm</option>
                                                    <option value="2" title="M">60x90cm</option>
                                                    <option value="3" title="L">80x120cm</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="single-add-to-cart">
                                            <form action="#" class="cart-quantity">
                                                <div class="quantity">
                                                    <label>Số lượng</label>
                                                    <div class="cart-plus-minus">
                                                        <input class="cart-plus-minus-box" value="1" type="text">
                                                        <div class="dec qtybutton"><i class="fa fa-angle-down"></i></div>
                                                        <div class="inc qtybutton"><i class="fa fa-angle-up"></i></div>
                                                    </div>
                                                </div>
                                                <button class="add-to-cart" type="submit">Thêm vào giỏ hàng</button>
                                            </form>
                                        </div>
                                        <div class="product-additional-info pt-25">
                                            <a class="wishlist-btn" href="wishlist.html"><i class="fa fa-heart-o"></i>Add to wishlist</a>
                                            <div class="product-social-sharing pt-25">
                                                <ul>
                                                    <li class="facebook"><a href="#"><i class="fa fa-facebook"></i>Facebook</a></li>
                                                    <li class="twitter"><a href="#"><i class="fa fa-twitter"></i>Twitter</a></li>
                                                    <li class="google-plus"><a href="#"><i class="fa fa-google-plus"></i>Google +</a></li>
                                                    <li class="instagram"><a href="#"><i class="fa fa-instagram"></i>Instagram</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>   
        <!-- Quick View | Modal Area End Here -->
    </div>
    <div class="floating-button">
        <a href="tel:+84969986884"><button type="button" class="btn btn-danger btn-circle btn-xl"><i class="fa fa-phone"></i></button></a>
        <div class="messenger-button">
            <a href="https://www.facebook.com/profile.php?id=100092159031191" target="_blank">
                <button type="button" class="btn btn-primary btn-circle btn-xl"><i class="fa fa-facebook"></i></button>
            </a>
        </div>

    </div>
    <style>

        .messenger-button {
            display: flex;
            justify-content: center;
            align-items: center;
            position: fixed;
            bottom: 25%; /* Adjust the distance from the bottom */
            left: 13px; /* Adjust the distance from the right */
            width: 60px;
            height: 60px;
            background-color: #007bff;
            border-radius: 50%;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        .messenger-button img {
            width: 40px;
            height: 40px;
        }

        .floating-button {
            z-index: 1000;
            position: fixed;
            bottom: 10%; /* Adjust the distance from the bottom */
            left: 5px; /* Adjust the distance from the right */
        }


        .btn-circle.btn-xl {
            width: 70px;
            height: 70px;
            padding: 10px 16px;
            border-radius: 35px;
            font-size: 24px;
            line-height: 1.33;
        }

        .btn-circle {
            width: 30px;
            height: 30px;
            padding: 6px 0px;
            border-radius: 15px;
            text-align: center;
            font-size: 12px;
            line-height: 1.42857;
        }

        .bg-1{
            background-image: url('../images/index/banner/1.jpg');
            background-size: cover;
            background-repeat: no-repeat;
        }

        .bg-2{
            background-image: url('../images/index/banner/2.jpg');
            background-size: cover;
            background-repeat: no-repeat;
        }

        .bg-3{
            background-image: url('../images/index/banner/3.jpg');
            background-size: cover;
            background-repeat: no-repeat;
        }

    </style>
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

</body>

<!-- index-231:38-->

