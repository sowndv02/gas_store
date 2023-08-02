<%-- 
    Document   : shopping
    Created on : May 17, 2023, 12:00:49 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html class="no-js" lang="zxx">

    <!-- shop-left-sidebar31:47-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Sản phẩm</title>
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

    <style>

        .price-content {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        label.min lable.max {
            font-size: 13px;
            font-weight: 500;
        }

        p.min, p.max {
            font-size: 18px;
            font-weight: 600;
        }

        .range-slider {
            width: 100%;
            position: relative;
            margin: 15px 0 30px 0;
        }

        input[type=range] {
            -webkit-appearance: none;
            width: 100%;
            background: transparent;
            position: absolute;
            left: 0;
        }

        input[type=range]::-webkit-slider-thumb {
            -webkit-appearance: none;
            height: 15px;
            width: 15px;
            border-radius: 50%;
            background: #36b37e;
            cursor: pointer;
            margin-top: -5px;
            position: relative;
            z-index: 1;
        }

        input[type=range]::-webkit-slider-runnable-track {
            width: 100%;
            height: 5px;
            background: #e8e8e8;
            border-radius: 3px;
            border: none;
        }
    </style>

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
                                                                    <a href="${pageContext.request.contextPath}/client/item/${i.product.code}" class="minicart-product-image"><img src="${pageContext.request.contextPath}/${i.product.base64Image}" alt="Preview" />
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
                            <li class="active">Sản phẩm</li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- Li's Breadcrumb Area End Here -->
            <!-- Begin Li's Content Wraper Area -->
            <div class="content-wraper pt-60 pb-60 pt-sm-30">
                <div class="container">
                    <form action="${pageContext.request.contextPath}/client/category/${requestScope.type}"  id="form1">
                        <div class="row">
                            <div class="col-lg-9 order-1 order-lg-2">
                                <!-- Begin Li's Banner Area -->
                                <div class="single-banner shop-page-banner">

                                </div>
                                <!-- Li's Banner Area End Here -->
                                <!-- shop-top-bar start -->
                                <div class="shop-top-bar mt-30">
                                    <div class="shop-bar-inner">
                                        <div class="product-view-mode">
                                            <!-- shop-item-filter-list start -->
                                            <ul class="nav shop-item-filter-list" role="tablist">
                                                <li class="active" role="presentation"><a aria-selected="true" class="active show" data-toggle="tab" role="tab" aria-controls="grid-view" href="#grid-view"><i class="fa fa-th"></i></a></li>
                                            </ul>
                                            <!-- shop-item-filter-list end -->
                                        </div>
                                        <div class="toolbar-amount">
                                        </div>
                                    </div>
                                    <!-- product-select-box start -->
                                    <div class="product-select-box">

                                        <div class="product-short">
                                            <!-- <p>Sort By:</p> -->
                                            <p>Sắp xếp theo:</p>
                                            <select class="nice-select" onchange="this.form.submit()" name="orderby">
                                                <option <c:if test="${requestScope.orderby == 1}">selected</c:if> value="1">Tên (A - Z)</option>
                                                <option <c:if test="${requestScope.orderby == 2}">selected</c:if> value="2">Tên (Z - A)</option>
                                                <option <c:if test="${requestScope.orderby == 3}">selected</c:if> value="3">Giá (Thấp &gt; Cao)</option>
                                                <option <c:if test="${requestScope.orderby == 4}">selected</c:if> value="4">Giá (Cao &gt; Thấp)</option>
                                                <c:if test="${requestScope.type eq 'giam-gia-0'}">
                                                    <option <c:if test="${requestScope.orderby == 5}">selected</c:if> value="5">Giảm giá (Cao &gt; Thấp)</option>
                                                    <option <c:if test="${requestScope.orderby == 6}">selected</c:if> value="5">Giảm giá (Thấp &gt; Cao)</option>
                                                </c:if>
                                                </select>
                                            </div>

                                        </div>
                                        <!-- product-select-box end -->
                                    </div>
                                    <!-- shop-top-bar end -->
                                    <!-- shop-products-wrapper start -->
                                    <div class="shop-products-wrapper">
                                        <div class="tab-content">
                                            <div id="grid-view" class="tab-pane fade active show" role="tabpanel">
                                                <div class="product-area shop-product-area">
                                                    <div class="row">

                                                    <c:forEach items="${requestScope.listAllType}" var="p">

                                                        <div class="col-lg-4 col-md-4 col-sm-6 mt-40">
                                                            <!-- single-product-wrap start -->
                                                            <div class="single-product-wrap">
                                                                <div class="product-image">
                                                                    <a href="${pageContext.request.contextPath}/client/item/${p.code}">
                                                                        <img style="width: 250px; height: 350px"  src="${pageContext.request.contextPath}/${p.base64Image}" alt="Preview" />
                                                                    </a>
                                                                </div>
                                                                <div class="product_desc">
                                                                    <div class="product_desc_info">
                                                                        <div class="product-review">
                                                                            <h5 class="manufacturer">
                                                                                <a href="${pageContext.request.contextPath}/client/category/${p.category.code}">${p.category.name}</a>
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
                                                                        <h4><a class="product_name" href="${pageContext.request.contextPath}/client/item/${p.code}">${p.name}</a></h4>
                                                                        <div class="price-box">
                                                                            <span class="new-price new-price-2"><fmt:formatNumber value = "${Math.round((p.unitPrice - p.unitPrice*p.discount.discount)/10000)*10000}" type = "currency"/></span>
                                                                            <span class="old-price"><fmt:formatNumber value = "${p.unitPrice}" type = "currency"/></span>
                                                                            <span class="discount-percentage"><fmt:formatNumber value = "${p.discount.discount}" type = "percent"/></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="add-actions">
                                                                        <ul class="add-actions-link">
                                                                            <li onclick="d(${p.productId})" class="add-cart active">Thêm vào giỏ hàng</li>
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

                                        <div class="paginatoin-area">
                                            <div class="row">

                                                <div class="col-lg-6 col-md-6">
                                                    <ul class="pagination-box pt-xs-20 pb-xs-15">
                                                        <li><a href="${pageContext.request.contextPath}/client/category/${requestScope.type}?${requestScope.link}orderby=${requestScope.orderby}&page=${page-1}&from=${requestScope.from}&to=${requestScope.to}" class="Previous <c:if test="${requestScope.page == 1}">disabled</c:if>"><i class="fa fa-chevron-left"></i> Previous</a>
                                                            </li>
                                                        <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                            <li  class="${i==page?"active":""}"><a href="${pageContext.request.contextPath}/client/category/${requestScope.type}?${requestScope.link}orderby=${requestScope.orderby}&page=${i}&from=${requestScope.from}&to=${requestScope.to}">${i}</a> </li>
                                                            </c:forEach>
                                                        <li>
                                                            <a href="${pageContext.request.contextPath}/client/category/${requestScope.type}?${requestScope.link}orderby=${requestScope.orderby}&page=${page+1}&from=${requestScope.from}&to=${requestScope.to}" class="Next <c:if test="${requestScope.page == num}">disabled</c:if>"> Next <i class="fa fa-chevron-right"></i></a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- shop-products-wrapper end -->
                                </div>
                                <div class="col-lg-3 order-2 order-lg-1">
                                    <!--sidebar-categores-box start  -->

                                    <!--sidebar-categores-box end  -->
                                    <!--sidebar-categores-box start  -->
                                    <div class="sidebar-categores-box">
                                        <div class="sidebar-title">
                                            <!-- <h2>Filter By</h2> -->
                                            <h2>Lọc bởi</h2>
                                        </div>
                                        <!-- btn-clear-all start -->
                                        <!-- <button class="btn-clear-all mb-sm-30 mb-xs-30">Clear all</button> -->
                                        <!-- btn-clear-all end -->
                                        <!-- filter-sub-area start -->
                                        <div class="filter-sub-area">
                                            <h5 class="filter-sub-titel">Thương hiệu</h5>
                                        <c:set var="ci" value="${requestScope.cid}"></c:set>
                                        <c:set var="sup" value="${requestScope.listAllSuppliersType}"></c:set>
                                            <div class="categori-checkbox">
                                            <c:forEach begin="0" end="${sup.size()-1}" var="i">
                                                <div class="col-sm-12"><label><input name="sid" value="${sup.get(i).getSupplierId()}" ${ci[i]?"checked":""} type="checkbox" onclick="this.form.submit()">&nbsp; ${sup.get(i).getCompanyName()}</label></div>
                                            </c:forEach>
                                        </div>

                                    </div>


                                    <div class="filter-sub-area"    >
                                        <div >
                                            <div class="price-content">
                                                <div>
                                                    <label class="min">Min</label>
                                                    <p id="min-value" class="min">${requestScope.minPrice}</p>
                                                </div>

                                                <div>
                                                    <label class="max">Max</label>
                                                    <p id="max-value" class="max"><fmt:formatNumber value = "${Math.round((requestScope.maxPrice)/1000)*1000}" type = "currency"/></p>
                                                </div>
                                            </div>

                                            <div class="range-slider">
                                                <input type="range" id="from" name="from" class="price_range" value="${requestScope.from}" min="${requestScope.minPrice}" max="${requestScope.maxPrice}" step="10000">
                                                <input type="range" id="to" name="to" class="price_range" value="${requestScope.to}" min="${requestScope.minPrice}" max="${requestScope.maxPrice}" step="10000">

                                            </div>
                                        </div>
                                        <button class="btn btn-dark mt-50" onchange="this.form.submit()">Submit</button>

                                    </div>
                                </div>
                                </form>
                            </div>
                        </div>
                        <div class="footer mt-50">
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
                                                    <!-- <img src="images/logo/logo.png" alt="Footer Logo"> -->
                                                    <p class="info">
                                                        <!-- We are a team of designers and developers that create high quality HTML Template & Woocommerce, Shopify Theme. -->

                                                    </p>
                                                </div>
                                                <ul class="des">
                                                    <li>
                                                        <span>Address: </span>
                                                        6688Princess Road, London, Greater London BAS 23JK, UK
                                                    </li>
                                                    <li>
                                                        <span>Phone: </span>
                                                        <a href="tel+123123321345">(+123) 123 321 345</a>
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
                                                    <img style="width: 20%" src="images/payment/1.png" alt="">
                                                </a>
                                            </div>
                                            <!-- Footer Payment Area End Here -->
                                            <!-- Begin Copyright Area -->
                                            <div class="copyright text-center pt-25">
                                                <!-- <span><a target="_blank" href="https://www.templateshub.net">Templates Hub</a></span> -->
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
                                            let minValue = document.getElementById("min-value");
                                            let maxValue = document.getElementById("max-value");

                                            function validateRange(minPrice, maxPrice) {
                                                minValue.innerHTML = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(minPrice);
                                                maxValue.innerHTML = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(maxPrice);
                                            }

                                            const inputElements = document.querySelectorAll(".price_range");

                                            inputElements.forEach((element) => {
                                                element.addEventListener("change", (e) => {
                                                    let minPrice = parseInt(inputElements[0].value);
                                                    let maxPrice = parseInt(inputElements[1].value);

                                                    if (minPrice > maxPrice) {
                                                        // Swap the values
                                                        [minPrice, maxPrice] = [maxPrice, minPrice];
                                                        // Set the values of the range inputs
                                                        inputElements[0].value = minPrice;
                                                        inputElements[1].value = maxPrice;
                                                    }
                                                    validateRange(minPrice, maxPrice);
                                                });
                                            });

                                            validateRange(inputElements[0].value, inputElements[1].value);
                </script>
                </body>
                <!-- shop-left-sidebar31:48-->
                </html>
