<%-- 
    Document   : orders
    Created on : May 18, 2023, 12:47:13 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="robots" content="noindex,nofollow" />
        <title>Warranties</title>
        <link rel="icon" type="image/png" sizes="16x16" href="assets/images/logo.png" />
        <link href="assets/libs/flot/css/float-chart.css" rel="stylesheet" />
        <link href="dist/css/style.min.css" rel="stylesheet" />

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts@3.25.0/dist/apexcharts.min.js"></script>
        <link href="dist/css/orders.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>

        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
        %>
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>

        <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
             data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin5">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/index">
                            <b class="logo-icon"  style="margin: 0px;">
                                <img src="assets/images/logo.png" alt="homepage" class="light-logo" width="50" height ="50" />
                            </b>
                            <span class="logo-text " style="margin-right: 15px">
                                <img src="${pageContext.request.contextPath}/images/logo_text.png" alt="homepage" class="light-logo" width="140" height="50" />
                            </span>
                        </a>
                        <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i
                                class="ti-menu ti-close"></i></a>
                    </div>
                    <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                        <ul class="navbar-nav float-start me-auto">
                            <li class="nav-item d-none d-lg-block">
                                <a class="nav-link sidebartoggler waves-effect waves-light" href="javascript:void(0)"
                                   data-sidebartype="mini-sidebar"><i class="mdi mdi-menu font-24"></i></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    <span class="d-none d-md-block">Thêm mới  &nbsp;&nbsp;&nbsp;<i class="fa fa-angle-down"></i></span>
                                    <span class="d-block d-md-none"><i class="fa fa-plus"></i></span>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addsupplier">Thương hiệu</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addcategory">Danh mục</a></li>
                                    <li>
                                        <hr class="dropdown-divider" />
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addproduct">Sản phẩm</a>
                                    </li>
                                </ul>
                            </li>

                            <li class="nav-item search-box">
                                <a class="nav-link waves-effect waves-dark" href="javascript:void(0)"><i
                                        class="mdi mdi-magnify fs-4"></i></a>
                                <form class="app-search position-absolute" action="${pageContext.request.contextPath}/admin/warranty" method="post">
                                    <input type="text" name="orderID" class="form-control" placeholder="Search by OrderID &amp; enter" />
                                    <a class="srh-btn"><i class="mdi mdi-window-close"></i></a>
                                </form>
                            </li>
                        </ul>
                        <ul class="navbar-nav float-end">
                            <li class="nav-item dropdown">
                                <a class="
                                   nav-link
                                   dropdown-toggle
                                   text-muted
                                   waves-effect waves-dark
                                   pro-pic
                                   " href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <img src="data:image/jpg;base64,${sessionScope.admin.base64Image}" alt="user" class="rounded-circle" width="31" />
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end user-dd animated" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/profileadmin"><i class="mdi mdi-account me-1 ms-1"></i> Hồ sơ của
                                        tôi</a>

                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/changepassword"><i class="mdi mdi-settings me-1 ms-1"></i> Đổi mật
                                        khẩu</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/logout"><i class="fa fa-power-off me-1 ms-1"></i> Đăng
                                        xuất</a>
                                    <div class="dropdown-divider"></div>
                                    <div class="ps-4 p-10">
                                        <a href="${pageContext.request.contextPath}/admin/profileadmin" class="btn btn-sm btn-success btn-rounded text-white">View Profile</a>
                                    </div>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <aside class="left-sidebar" data-sidebarbg="skin5">
                <!-- Sidebar scroll-->
                <div class="scroll-sidebar">
                    <!-- Sidebar navigation-->
                    <nav class="sidebar-nav">
                        <ul id="sidebarnav" class="pt-4">
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/index"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-view-dashboard"></i
                                    ><span class="hide-menu">Trang chủ</span></a
                                >
                            </li>
                            <li class="sidebar-item ">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/charts"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-chart-bar"></i
                                    ><span class="hide-menu">Thống kê</span></a
                                >
                            </li>


                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/feedbacks"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-help-circle"></i
                                    ><span class="hide-menu">Feedbacks</span></a
                                >
                            </li>


                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/orders"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-tag"></i
                                    ><span class="hide-menu">Orders</span></a
                                >
                            </li>

                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/reviews"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-comment-processing"></i
                                    ><span class="hide-menu">Reviews</span></a
                                >
                            </li>
                            
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/manageblog"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-newspaper"></i
                                    ><span class="hide-menu">Blogs</span></a
                                >
                            </li>
                            
                            <li class="sidebar-item selected">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/warranty"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-security"></i
                                    ><span class="hide-menu">Warranty</span></a
                                >
                            </li>
                            
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/discounts"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-sale"></i
                                    ><span class="hide-menu">Discounts</span></a
                                >
                            </li>
                            
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/faqs"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-calendar-question"></i
                                    ><span class="hide-menu">FAQ</span></a
                                >
                            </li>

                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-format-list-bulleted"></i
                                    ><span class="hide-menu">Danh sách </span></a
                                >
                                <ul aria-expanded="false" class="collapse first-level">
                                    <li class="sidebar-item ">
                                        <a href="${pageContext.request.contextPath}/admin/warrantypolicy" class="sidebar-link"
                                           ><i class="mdi mdi-file-document"></i
                                            ><span class="hide-menu"> Warranty Policy </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/managetypeblog" class="sidebar-link"
                                           ><i class="mdi mdi-format-list-bulleted-type"></i
                                            ><span class="hide-menu"> Type Blog </span></a
                                        >
                                    </li>

                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/accounts" class="sidebar-link"
                                           ><i class="mdi mdi-account"></i
                                            ><span class="hide-menu"> Người dùng </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/products" class="sidebar-link"
                                           ><i class="fab fa-product-hunt"></i
                                            ><span class="hide-menu"> Sản phẩm </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/categories" class="sidebar-link"
                                           ><i class="mdi mdi-group"></i
                                            ><span class="hide-menu"> Danh mục </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/shippers" class="sidebar-link"
                                           ><i class="mdi mdi-truck"></i
                                            ><span class="hide-menu"> Đơn vị vận chuyển </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/suppliers" class="sidebar-link"
                                           ><i class="mdi mdi-human-greeting"></i
                                            ><span class="hide-menu"> Thương hiệu </span></a
                                        >
                                    </li>
                                </ul>
                            </li>

                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-playlist-plus"></i
                                    ><span class="hide-menu">Thêm mới </span></a
                                >
                                <ul aria-expanded="false" class="collapse first-level">
                                    
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/newdiscount" class="sidebar-link"
                                           ><i class="mdi mdi-sale"></i
                                            ><span class="hide-menu"> Discount </span></a
                                        >
                                    </li>
                                    
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addfaq" class="sidebar-link"
                                           ><i class="mdi mdi-calendar-question"></i
                                            ><span class="hide-menu"> FAQ </span></a
                                        >
                                    </li>
                                    
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/newblog" class="sidebar-link"
                                           ><i class="mdi mdi-newspaper"></i
                                            ><span class="hide-menu"> Blogs </span></a
                                        >
                                    </li>
                                    
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addproduct" class="sidebar-link"
                                           ><i class="fab fa-product-hunt"></i
                                            ><span class="hide-menu"> Sản phẩm </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addcategory" class="sidebar-link"
                                           ><i class="mdi mdi-group"></i
                                            ><span class="hide-menu"> Danh mục </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addshipper" class="sidebar-link"
                                           ><i class="mdi mdi-truck"></i
                                            ><span class="hide-menu"> Đơn vị vận chuyển </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addsupplier" class="sidebar-link"
                                           ><i class="mdi mdi-human-greeting"></i
                                            ><span class="hide-menu"> Thương hiệu </span></a
                                        >
                                    </li>
                                </ul>
                            </li>


                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-account-key"></i
                                    ><span class="hide-menu">Xác thực</span></a
                                >
                                <ul aria-expanded="false" class="collapse first-level">


                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/changepass.jsp" class="sidebar-link"
                                           ><i class="mdi mdi-key-change"></i
                                            ><span class="hide-menu"> Đổi mật khẩu </span></a
                                        >
                                    </li>

                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/profileadmin" class="sidebar-link"
                                           ><i class="mdi mdi-account-card-details"></i
                                            ><span class="hide-menu"> Hồ sơ </span></a
                                        >
                                    </li>


                                </ul>
                            </li>
                        </ul>
                    </nav>
                    <!-- End Sidebar navigation -->
                </div>
                <!-- End Sidebar scroll-->
            </aside>
            <div class="page-wrapper">
                <div class="page-breadcrumb">
                    <div class="row">
                        <div class="col-12 d-flex no-block align-items-center">
                            <h4 class="page-title">Warranty</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Warranty
                                        </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="container-fluid">
                    <div class="row">
                        <!-- column -->

                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="${pageContext.request.contextPath}/admin/warranty" method="post" id="form0">
                                        <input type="hidden" name="status" value="process">
                                        <div class="row">
                                            <div class="col-md-7">
                                                <h4 class="card-title mb-0"><a href="${pageContext.request.contextPath}/admin/warranty">Bảo hành đang xử lý(${requestScope.sizeProcess})</a></h4>
                                            </div>
                                            <div class="col-md-2"><label for="from0">From&nbsp;&nbsp;&nbsp;</label><input id="from0" name="from0" type="date"></div>
                                            <div class="col-md-2"><label for="to0">To&nbsp;&nbsp;&nbsp;</label><input id="to0" type="date" name="to0"></div>
                                            <div class="col-md-1"><button class="btn-orange border-0 text-white" type="button" onclick="checkForm0()" >Search</button></div>
                                        </div>
                                    </form>
                                </div>
                                <div class="comment-widgets scrollable">
                                    <!-- Comment Row -->
                                    <div id="contentShip">
                                        <c:forEach items="${requestScope.getProcessWarranty}" var="wp">
                                            <div class="d-flex flex-row comment-row mt-0 ordersShipping">
                                                <div class="p-2">
                                                    <img
                                                        src="data:image/jpg;base64,${wp.customer.base64Image}"
                                                        alt="user"
                                                        width="50"
                                                        height="50"
                                                        class="rounded-circle"
                                                        />
                                                </div>
                                                <div class="comment-text w-100">
                                                    <a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${wp.customer.customerID}">${wp.customer.firstName} ${wp.customer.lastName}</a>
                                                    <span class="mb-3 d-block">
                                                        <a href="${pageContext.request.contextPath}/admin/warrantydetail?id=${wp.warrantyId}">WarrantyId: ${wp.warrantyId}</a><br>
                                                        CreatedDate: ${wp.createdDate}<br>
                                                        RequiredDate: ${wp.appointmentDate}<br>
                                                        Product: <a href="${pageContext.request.contextPath}/admin/updateproduct?pid=${wp.product.productId}">${wp.product.name}</a><br>
                                                        Notes: ${wp.note}<br>
                                                        Description: ${wp.description}<br>
                                                    </span>
                                                    <div class="comment-footer">
                                                        <span class="text-muted float-end">${wp.createdDate}</span>
                                                        <button
                                                            type="button"
                                                            class="btn btn-cyan btn-sm text-white"
                                                            >
                                                            <a class="text-white" href="${pageContext.request.contextPath}/admin/warrantydetail?id=${wp.warrantyId}">
                                                                Information 
                                                            </a>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div> 
                                        </c:forEach>
                                    </div>
                                    <div class="btn_loadmore" style="display: grid; place-items: center">
                                        <button class="btn btn-warning btn-lg" onclick="loadMoreOrdersShipping()">
                                            More
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="${pageContext.request.contextPath}/admin/warranty" method="post" id="form1">
                                        <input type="hidden" name="status" value="new">
                                        <div class="row">
                                            <div class="col-md-7">
                                                <h4 class="card-title mb-0"><a href="${pageContext.request.contextPath}/admin/warranty">Đơn bảo hành mới(${requestScope.sizeNew})</a></h4>
                                            </div>
                                            <div class="col-md-2"><label for="from1">From&nbsp;&nbsp;&nbsp;</label><input id="from1" name="from1" type="date"></div>
                                            <div class="col-md-2"><label for="to1">To&nbsp;&nbsp;&nbsp;</label><input id="to1" type="date" name="to1"></div>
                                            <div class="col-md-1"><button class="btn-orange border-0 text-white" type="button" onclick="checkForm1()" >Search</button></div>
                                        </div>
                                    </form>
                                </div>
                                <div class="comment-widgets scrollable">
                                    <!-- Comment Row -->
                                    <div id="content">
                                        <c:forEach items="${requestScope.getNewWarranty}" var="nw">
                                            <div class="d-flex flex-row comment-row mt-0 orders">
                                                <div class="p-2">
                                                    <img
                                                        src="data:image/jpg;base64,${nw.customer.base64Image}"
                                                        alt="user"
                                                        width="50"
                                                        height="50"
                                                        class="rounded-circle"
                                                        />
                                                </div>
                                                <div class="comment-text w-100">
                                                    <a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${nw.customer.customerID}">${nw.customer.firstName} ${nw.customer.lastName}</a>
                                                    <span class="mb-3 d-block">
                                                        <a href="${pageContext.request.contextPath}/admin/warrantydetail?id=${nw.warrantyId}">WarrantyId: ${nw.warrantyId}</a><br>
                                                        CreatedDate: ${nw.createdDate}<br>
                                                        RequiredDate: ${nw.appointmentDate}<br>
                                                        Product: <a href="${pageContext.request.contextPath}/admin/updateproduct?pid=${nw.product.productId}">${nw.product.name}</a><br>
                                                        Notes: ${nw.note}<br>
                                                        Description: ${nw.description}<br>
                                                    </span>
                                                    <div class="comment-footer">
                                                        <span class="text-muted float-end">${nw.createdDate}</span>
                                                        <button
                                                            type="button"
                                                            class="btn btn-cyan btn-sm text-white"
                                                            >
                                                            <a class="text-white" href="${pageContext.request.contextPath}/admin/warrantydetail?id=${nw.warrantyId}">
                                                                Information 
                                                            </a>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div> 
                                        </c:forEach>
                                    </div>
                                    <div class="btn_loadmore" style="display: grid; place-items: center">
                                        <button class="btn btn-warning btn-lg" onclick="loadMoreNewOrders()">
                                            More
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="card-body">
                                        <form action="${pageContext.request.contextPath}/admin/warranty" method="post" id="form2">
                                            <input type="hidden" name="status" value="done">
                                            <div class="row">
                                                <div class="col-md-7">
                                                    <h4 class="card-title mb-0"><a href="${pageContext.request.contextPath}/admin/warranty">Đơn hàng đã xử lý(${requestScope.sizeDone})</a></h4>                                            </div>
                                                <div class="col-md-2"><label for="from2">From&nbsp;&nbsp;&nbsp;</label><input name="from2" id="from2" type="date"></div>
                                                <div class="col-md-2"><label for="to2">To&nbsp;&nbsp;&nbsp;</label><input id="to2" name="to2" type="date"></div>
                                                <div class="col-md-1"><button class="btn-orange border-0 text-white" type="button" onclick="checkForm2()" >Search</button></div>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                                <div class="comment-widgets scrollable">
                                    <!-- Comment Row -->
                                    <div id="contentOrders">
                                        <c:forEach items="${requestScope.getDoneWarranty}" var="nd">
                                            <div class="d-flex flex-row comment-row mt-0 ordersProcess">
                                                <div class="p-2">
                                                    <img
                                                        src="data:image/jpg;base64,${nd.customer.base64Image}"
                                                        alt="user"
                                                        width="50"
                                                        height="50"
                                                        class="rounded-circle"
                                                        />
                                                </div>
                                                <div class="comment-text w-100">
                                                    <a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${nd.customer.customerID}">${nd.customer.firstName} ${nd.customer.lastName}</a>
                                                    <span class="mb-3 d-block">
                                                        <a href="${pageContext.request.contextPath}/admin/warrantydetail?id=${nd.warrantyId}">WarrantyID: ${nd.warrantyId}</a><br>
                                                        CreatedDate: ${nd.createdDate}<br>
                                                        RequiredDate: ${nd.appointmentDate}<br>
                                                        Product: <a href="${pageContext.request.contextPath}/admin/updateproduct?pid=${nd.product.productId}">${nd.product.name}</a><br>
                                                        Notes: ${nd.note}<br>
                                                        Description: ${nd.description}<br>
                                                    </span>
                                                    <div class="comment-footer">
                                                        <span class="text-muted float-end">${nd.createdDate}</span>
                                                        <button
                                                            type="button"
                                                            class="btn btn-cyan btn-sm text-white"
                                                            >
                                                            <a class="text-white" href="${pageContext.request.contextPath}/admin/warrantydetail?id=${nd.warrantyId}">
                                                                Information 
                                                            </a>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div> 
                                        </c:forEach>
                                    </div>
                                    <div class="btn_loadmore " style="display: grid; place-items: center">
                                        <button  class="btn btn-warning btn-lg" onclick="loadMoreOrders()">
                                            More
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                    <footer class="footer text-center">

                    </footer>
                </div>
            </div>
            <script src="assets/libs/jquery/dist/jquery.min.js"></script>
            <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
            <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
            <script src="assets/extra-libs/sparkline/sparkline.js"></script>
            <script src="dist/js/waves.js"></script>
            <script src="dist/js/sidebarmenu.js"></script>
            <!--Custom JavaScript -->
            <script src="dist/js/custom.min.js"></script>
            <!--This page JavaScript -->
            <!-- <script src="dist/js/pages/dashboards/dashboard1.js"></script> -->
            <!-- Charts js Files -->
            <script src="assets/libs/flot/excanvas.js"></script>
            <script src="assets/libs/flot/jquery.flot.js"></script>
            <script src="assets/libs/flot/jquery.flot.pie.js"></script>
            <script src="assets/libs/flot/jquery.flot.time.js"></script>
            <script src="assets/libs/flot/jquery.flot.stack.js"></script>
            <script src="assets/libs/flot/jquery.flot.crosshair.js"></script>
            <script src="assets/libs/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
            <script src="dist/js/pages/chart/chart-page-init.js"></script>
            <script>
                                            function updateOrder(id, type) {
                                                if (confirm("Are you sure you want " + type + " warranty " + "have warrantyId = " + id + "?")) {
                                                    window.location.href = "${pageContext.request.contextPath}/admin/updatewarranty?id=" + id + "&type=" + type;
                                                }
                                            }

                                            function loadMoreOrdersShipping() {
                                                var amount = document.getElementsByClassName('ordersShipping').length;

                                                $.ajax({
                                                    url: "${pageContext.request.contextPath}/admin/loadorder",
                                                    type: "get",
                                                    data: {
                                                        total: amount,
                                                        type: "ship"
                                                    },

                                                    success: function (data) {
                                                        var row = document.getElementById('contentShip');
                                                        row.innerHTML += data;
                                                    },
                                                    error: function (xhr) {
                                                        alert("Bug cmnr");
                                                    }
                                                });
                                            }

                                            function loadMoreNewOrders() {
                                                var amount = document.getElementsByClassName('orders').length;

                                                $.ajax({
                                                    url: "${pageContext.request.contextPath}/admin/loadorder",
                                                    type: "get",
                                                    data: {
                                                        total: amount,
                                                        type: "process"
                                                    },

                                                    success: function (data) {
                                                        var row = document.getElementById('content');
                                                        row.innerHTML += data;
                                                    },
                                                    error: function (xhr) {
                                                        //Do Something to handle error
                                                    }
                                                });
                                            }

                                            function loadMoreOrders() {
                                                var amount = document.getElementsByClassName('ordersProcess').length;

                                                $.ajax({
                                                    url: "${pageContext.request.contextPath}/admin/loadorder",
                                                    type: "get",
                                                    data: {
                                                        total: amount,
                                                        type: "done"
                                                    },

                                                    success: function (data) {
                                                        var row = document.getElementById('contentOrders');
                                                        row.innerHTML += data;
                                                    },
                                                    error: function (xhr) {
                                                        //Do Something to handle error
                                                    }
                                                });
                                            }

                                            const fromDate0 = document.getElementById("from0");
                                            const toDate0 = document.getElementById("to0");

                                            fromDate0.addEventListener("change", () => {
                                                if (fromDate.value > toDate.value) {
                                                    const temp = fromDate0.value;
                                                    fromDate0.value = toDate0.value;
                                                    toDate0.value = temp;
                                                }
                                            });

                                            toDate0.addEventListener("change", () => {
                                                if (toDate0.value < fromDate0.value) {
                                                    const temp = toDate0.value;
                                                    toDate0.value = fromDate0.value;
                                                    fromDate0.value = temp;
                                                }
                                            });
                                            
                                            function checkFormr() {
                                                const fromInput = document.getElementById("fromr");
                                                const toInput = document.getElementById("tor");

                                                if (fromInput.value === "" || toInput.value === "") {
                                                    alert("Please select both a 'From' and a 'To' date.");
                                                } else {
                                                    document.getElementById('formr').submit();
                                                }
                                            }

                                            function checkForm0() {
                                                const fromInput = document.getElementById("from0");
                                                const toInput = document.getElementById("to0");

                                                if (fromInput.value === "" || toInput.value === "") {
                                                    alert("Please select both a 'From' and a 'To' date.");
                                                } else {
                                                    document.getElementById('form0').submit();
                                                }
                                            }

                                            const fromDate1 = document.getElementById("from1");
                                            const toDate1 = document.getElementById("to1");

                                            fromDate1.addEventListener("change", () => {
                                                if (fromDate.value > toDate.value) {
                                                    const temp = fromDate1.value;
                                                    fromDate1.value = toDate1.value;
                                                    toDate1.value = temp;
                                                }
                                            });

                                            toDate1.addEventListener("change", () => {
                                                if (toDate1.value < fromDate1.value) {
                                                    const temp = toDate1.value;
                                                    toDate1.value = fromDate1.value;
                                                    fromDate1.value = temp;
                                                }
                                            });

                                            function checkForm1() {
                                                const fromInput = document.getElementById("from1");
                                                const toInput = document.getElementById("to1");

                                                if (fromInput.value === "" || toInput.value === "") {
                                                    alert("Please select both a 'From' and a 'To' date.");
                                                } else {
                                                    document.getElementById('form1').submit();
                                                }
                                            }

                                            const fromDate2 = document.getElementById("from2");
                                            const toDate2 = document.getElementById("to2");

                                            fromDate2.addEventListener("change", () => {
                                                if (fromDate2.value > toDate2.value) {
                                                    const temp = fromDate2.value;
                                                    fromDate2.value = toDate2.value;
                                                    toDate2.value = temp;
                                                }
                                            });

                                            toDate2.addEventListener("change", () => {
                                                if (toDate1.value < fromDate1.value) {
                                                    const temp = toDate2.value;
                                                    toDate2.value = fromDate2.value;
                                                    fromDate2.value = temp;
                                                }
                                            });

                                            function checkForm2() {
                                                const fromInput = document.getElementById("from2");
                                                const toInput = document.getElementById("to2");

                                                if (fromInput.value === "" || toInput.value === "") {
                                                    alert("Please select both a 'From' and a 'To' date.");
                                                } else {
                                                    document.getElementById('form2').submit();
                                                }
                                            }
            </script>
    </body>
</html>
