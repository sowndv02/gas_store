<%-- 
    Document   : index
    Created on : Feb 8, 2023, 4:37:29 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="robots" content="noindex,nofollow" />
        <title>Trang chủ</title>
        <link rel="icon" type="image/png" sizes="16x16" href="assets/images/logo.png" />
        <link href="assets/libs/flot/css/float-chart.css" rel="stylesheet" />
        <link href="dist/css/style.min.css" rel="stylesheet" />

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts@3.25.0/dist/apexcharts.min.js"></script>
    </head>
    <body onload="drawChart()">

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
                                <img src="assets/images/logo.png" alt="homepage" class="light-logo" width="50" />
                            </b>
                            <span class="logo-text " >
                                <img src="assets/images/logo_text.png" alt="homepage" class="light-logo" width="140" height="50" />
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
                                <form class="app-search position-absolute">
                                    <input type="text" class="form-control" placeholder="Search &amp; enter" />
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
                            
                            <li class="sidebar-item">
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
                            <h4 class="page-title">Dashboard</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-md-flex align-items-center">
                                        <div>
                                            <h4 class="card-title">Site Analysis</h4>
                                            <form action="${pageContext.request.contextPath}/admin/index" method="get">
                                                <select name="year" onchange="this.form.submit()">
                                                    <optgroup label="Year">
                                                        <option value="2023" <c:if test="${requestScope.year == 2023}">selected</c:if>>2023</option>
                                                        <option value="2022" <c:if test="${requestScope.year == 2022}">selected</c:if>>2022</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <!-- column -->
                                            <div class="col-lg-9">
                                                <div class="flot-chart">
                                                    <div id="chart"></div>
                                                </div>
                                            </div>
                                            <div class="col-lg-3">
                                                <div class="row">
                                                    <div class="col-6">
                                                        <div class="bg-dark p-10 text-white text-center">
                                                            <i class="mdi mdi-account fs-3 mb-1 font-16"></i>
                                                            <h5 class="mb-0 mt-1">${requestScope.totalCustomers}</h5>
                                                        <small class="font-light">Total Users</small>
                                                    </div>
                                                </div>
                                                <div class="col-6">
                                                    <div class="bg-dark p-10 text-white text-center">
                                                        <i class="mdi mdi-cash fs-3 font-16"></i>
                                                        <h5 class="mb-0 mt-1"><fmt:formatNumber value = "${Math.round((requestScope.totalMoney)/1000)*1000}" type = "currency"/></h5>
                                                        <small class="font-light">Total Money</small>
                                                    </div>
                                                </div>
                                                <div class="col-6 mt-3">
                                                    <div class="bg-dark p-10 text-white text-center">
                                                        <i class="mdi mdi-tag-multiple fs-3 mb-1 font-16"></i>
                                                        <h5 class="mb-0 mt-1">${requestScope.totalOrders}</h5>
                                                        <small class="font-light">Total Orders</small>
                                                    </div>
                                                </div>

                                                <div class="col-6 mt-3">
                                                    <div class="bg-dark p-10 text-white text-center">
                                                        <i class="mdi mdi-help-circle fs-3 mb-1 font-16"></i>
                                                        <h5 class="mb-0 mt-1">${requestScope.totalFeedback}</h5>
                                                        <small class="font-light">Total Feedback</small>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!-- column -->
                        <div class="col-lg-12">


                            <c:if test="${requestScope.listWarranty.size() != 0}">
                                <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog ">
                                        <div class="modal-content">
                                            <div class="modal-header" style="flex-direction: column">
                                                <h4 class="modal-title" id="myModalLabel">Sản phẩm có yêu cầu bảo hành nhiều trong tháng</h4>
                                                <c:if test="${requestScope.warning.size() > 0}"><h4 class="modal-title text-danger" id="myModalLabel">Có sản phẩm đến hạn bảo dưỡng</h4></c:if>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <table style="border-collapse: collapse; width: 100%;">
                                                    <thead>
                                                        <tr style="border: 1px solid black; padding: 8px;padding: 8px;">
                                                            <td style="border: 1px solid black; padding: 8px;padding: 8px;">Name</td>
                                                            <td style="border: 1px solid black; padding: 8px;padding: 8px;">Nhà cung cấp</td>
                                                            <td style="border: 1px solid black; padding: 8px;padding: 8px;">Số đơn</td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.listWarranty}" var="w">
                                                            <tr style="border: 1px solid black; padding: 8px;padding: 8px;">
                                                                <td  style="border: 1px solid black; padding: 8px;padding: 8px;"><a href="${pageContext.request.contextPath}/client/item/${w.product.code}">${w.product.name}</a></td>
                                                                <td style="border: 1px solid black; padding: 8px;padding: 8px;"><a href="${pageContext.request.contextPath}/admin/profile?type=sup&id=${w.product.supplier.supplierId}">${w.product.supplier.companyName}</a></td></td>
                                                                <td style="border: 1px solid black; padding: 8px;padding: 8px;">${w.number}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title mb-0"><a href="${pageContext.request.contextPath}/admin/orders">Sản phẩm đến hạn bảo trì(${requestScope.warningList})</a></h4>
                                </div>
                                <div class="comment-widgets scrollable">
                                    <!-- Comment Row -->
                                    <c:forEach items="${requestScope.warning}" var="ow" begin="0" end="4">
                                        <div class="d-flex flex-row comment-row mt-0">
                                            
                                            <div class="comment-text w-100">
                                                <span class="mb-3 d-block">
                                                    <a href="${pageContext.request.contextPath}/admin/orderdetail?id=${ow.order.customerId}&oid=${ow.order.orderId}">OrderID: ${ow.order.orderId}</a><br>
                                                    Order Date: ${ow.order.orderDate}<br>
                                                    Customer: ${ow.order.customer.firstName } ${ow.order.customer.lastName}<br>
                                                    Product: ${ow.product.name}<br>
                                                    Shipped Date ${ow.order.shippedDate}<br>
                                                </span>
                                                <div class="comment-footer">
                                                    <span class="text-muted float-end">${ow.order.orderDate}</span>
                                                    <button
                                                        type="button"
                                                        onclick="sendWarning(${ow.orderID},${ow.productID} )"
                                                        class="btn btn-success btn-sm text-white"
                                                        >
                                                        Send Mail
                                                    </button>
                                                </div>
                                            </div>
                                        </div> 
                                    </c:forEach>
                                </div>
                            </div>


                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title mb-0"><a href="${pageContext.request.contextPath}/admin/orders">Đơn hàng chưa xử lý(${requestScope.totalOrdersProcess})</a></h4>
                                </div>
                                <div class="comment-widgets scrollable">
                                    <!-- Comment Row -->
                                    <c:forEach items="${requestScope.getNewOrders}" var="orderNew" begin="0" end="4">
                                        <div class="d-flex flex-row comment-row mt-0">
                                            <div class="p-2">
                                                <img
                                                    src="data:image/jpg;base64,${orderNew.customer.base64Image}"
                                                    alt="user"
                                                    width="50"
                                                    height="50"
                                                    class="rounded-circle"
                                                    />
                                            </div>
                                            <div class="comment-text w-100">
                                                <a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${orderNew.customer.customerID}">${orderNew.customer.firstName} ${orderNew.customer.lastName}</a>
                                                <span class="mb-3 d-block">
                                                    <a href="${pageContext.request.contextPath}/admin/orderdetail?id=${orderNew.customer.customerID}&oid=${orderNew.orderId}">OrderID: ${orderNew.orderId}</a><br>
                                                    OrderDate: ${orderNew.orderDate}<br>
                                                    RequiredDate: ${orderNew.requiredDate}<br>
                                                    TotalMoney: <fmt:formatNumber value = "${Math.round((orderNew.totalMoney)/1000)*1000}" type = "currency"/><br>
                                                </span>
                                                <div class="comment-footer">
                                                    <span class="text-muted float-end">${orderNew.orderDate}</span>
                                                    <button
                                                        type="button"
                                                        class="btn btn-cyan btn-sm text-white"
                                                        >
                                                        <a class="text-white" href="${pageContext.request.contextPath}/admin/orderdetail?id=${orderNew.customer.customerID}&oid=${orderNew.orderId}">
                                                            Information 
                                                        </a>
                                                    </button>
                                                    <button
                                                        type="button"
                                                        onclick="updateOrder(${orderNew.orderId}, 'accept')"
                                                        class="btn btn-success btn-sm text-white"
                                                        >
                                                        Accept
                                                    </button>
                                                    <button
                                                        type="button"
                                                        onclick="updateOrder(${orderNew.orderId}, 'reject')"
                                                        class="btn btn-danger btn-sm text-white"
                                                        >
                                                        Reject
                                                    </button>
                                                </div>
                                            </div>
                                        </div> 
                                    </c:forEach>





                                </div>
                            </div>



                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title"><a href="${pageContext.request.contextPath}/admin/reviews">Bình luận mới</a> </h4>
                                </div>
                                <div class="comment-widgets scrollable">
                                    <!-- Comment Row -->
                                    <c:forEach items="${requestScope.top5Review}" var="r">
                                        <div class="d-flex flex-row comment-row mt-0">
                                            <div class="p-2">
                                                <img src="data:image/jpg;base64,${r.customer.base64Image}" alt="user" width="50" height="50" class="rounded-circle" />
                                            </div>
                                            <div class="comment-text w-100">
                                                <h6 class="font-medium"><a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${r.customer.customerID}">${r.customer.firstName} ${r.customer.lastName}</a></h6>
                                                <span class="mb-3 d-block">(${r.rate} <i class="fas fa-star" style="color: gold;"></i>)${r.content}
                                                    (<a href="${pageContext.request.contextPath}/client/item?pid=${r.product.productId}">&nbsp; ${r.product.name}&nbsp;</a>)
                                                    <c:if test="${r.status}"><i class="mdi mdi-check-circle"></i></c:if>
                                                    <c:if test="${!r.status}"><i class="mdi mdi-block-helper"></i></c:if>
                                                    </span>
                                                    <div class="comment-footer">
                                                        <span class="text-muted float-end">${r.dateRate}</span>
                                                    <form action="" method="POST" id="review" name="review">
                                                        <button type="button" onclick="changeReview('public', '${r.reviewId}')" class="  btn btn-success btn-sm text-white <c:if test="${r.status}">disabled</c:if>">
                                                                Publish
                                                            </button>
                                                            <button type="button" onclick="changeReview('hidden', '${r.reviewId}')" class="btn btn-danger btn-sm text-white <c:if test="${!r.status}">disabled</c:if>">
                                                                Hidden
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                    </c:forEach>

                                </div>
                            </div>

                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title mb-0">Tài khoản mới</h5>
                                </div>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class="thead-light">
                                            <tr>
                                                <th>#</th>
                                                <th scope="col">Họ và tên</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Số điện thoại</th>
                                                <th scope="col">Giới tính</th>
                                                <th scope="col">Địa chỉ</th>
                                            </tr>
                                        </thead>
                                        <tbody class="customtable">
                                            <c:forEach items="${requestScope.newCustomers}" var="c">
                                                <tr>
                                                    <td>${c.customerID}</td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${c.customerID}">${c.firstName} ${c.lastName}</a></td>
                                                    <td>${c.email}</td>
                                                    <td>${c.phone}</td>
                                                    <td><c:choose>
                                                            <c:when test="${!c.gender}">Nữ</c:when>
                                                            <c:otherwise>Nam</c:otherwise>
                                                        </c:choose></td>
                                                    <td>${c.address}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>


                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title mb-0">Khách hàng thân thiết</h5>
                                </div>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class="thead-light">
                                            <tr>
                                                <th>#</th>
                                                <th scope="col">Họ và tên</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Số điện thoại</th>
                                                <th scope="col">Giới tính</th>
                                                <th scope="col">Địa chỉ</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Chi tiêu</th>
                                            </tr>
                                        </thead>
                                        <tbody class="customtable">
                                            <c:forEach items="${requestScope.customerVjp}" var="vjp">
                                                <tr>
                                                    <td>${vjp.customerID}</td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${vjp.customerID}">${vjp.firstName} ${vjp.lastName}</a></td>
                                                    <td>${vjp.email}</td>
                                                    <td>${vjp.phone}</td>
                                                    <td><c:choose>
                                                            <c:when test="${!vjp.gender}">Nữ</c:when>
                                                            <c:otherwise>Nam</c:otherwise>
                                                        </c:choose></td>
                                                    <td>${vjp.address}</td>
                                                    <td><c:choose>
                                                            <c:when test="${vjp.status}">Hoạt động</c:when>
                                                            <c:otherwise>Khoá</c:otherwise>
                                                        </c:choose></td>
                                                    <td><fmt:formatNumber value = "${Math.round((vjp.totalMoney)/1000)*1000}" type = "currency"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
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
                                                                function changeReview(type, id) {
                                                                    let text;
                                                                    if (type === 'hidden')
                                                                        text = "ẩn";
                                                                    else
                                                                        text = "hiển thị";
                                                                    if (confirm("Bạn có chắc muốn " + text + " bình luận này?") === true) {
                                                                        document.getElementById("review").action = "${pageContext.request.contextPath}/admin/updatereview?rid=" + id + "&type=" + type;
                                                                        document.getElementById("review").submit();
                                                                    }
                                                                }















                                                                var data2 = [];
                                                                var months = [];
                <%
    Map<Integer, Integer> ordersByMonth = (Map<Integer, Integer>) request.getAttribute("ordersByMonth");
    if (ordersByMonth != null) {
        for (Map.Entry<Integer, Integer> entry : ordersByMonth.entrySet()) {
            Integer month = entry.getKey();
            Integer number = entry.getValue();
                %>
                                                                data2.push(<%= number %>);
                <%
        }
    }
                %>



                                                                const monthNames = months.map((month) => {
                                                                    const date = new Date(Date.UTC(2000, month - 1, 1)); // Create a Date object with the month and year
                                                                    return date.toLocaleString('default', {month: 'long'}); // Get the full month name
                                                                });
                                                                function drawChart() {
                                                                    var chartData = {
                                                                        series: [
                                                                            {
                                                                                name: "Number Orders",
                                                                                type: "column",
                                                                                data: data2,
                                                                            },
                                                                        ],
                                                                        xaxis: {
                                                                            categories: monthNames,
                                                                        },
                                                                    };

                                                                    // Define options for chart
                                                                    var chartOptions = {
                                                                        chart: {
                                                                            height: 350,
                                                                            type: "line",
                                                                            stacked: false,
                                                                        },
                                                                        dataLabels: {
                                                                            enabled: false,
                                                                        },
                                                                        colors: ["#008FFB", "#00E396"],
                                                                        series: chartData.series,
                                                                        xaxis: chartData.xaxis,
                                                                    };

                                                                    // Initialize ApexCharts object with chart data and options
                                                                    var chart = new ApexCharts(document.querySelector("#chart"), chartOptions);

                                                                    // Render the chart
                                                                    chart.render();
                                                                }





                                                                function updateOrder(id, type, cid) {
                                                                    if (confirm("Are you sure you want " + type + " order " + "have OrderID = " + id + "?")) {
                                                                        window.location.href = "${pageContext.request.contextPath}/admin/updateorder?oid=" + id + "&type=" + type;
                                                                    }
                                                                }


            </script>

            <script>
                $(document).ready(function () {
                    $('#myModal').modal('show');
                });

                function sendWarning(pid, oid) {
                    if (confirm("Are you sure you want send this message ?")) {
                        window.location.href = "${pageContext.request.contextPath}/admin/warrantywarning?oid=" + oid + "&pid=" + pid;
                    }
                    }

            </script>

    </body>
</html>