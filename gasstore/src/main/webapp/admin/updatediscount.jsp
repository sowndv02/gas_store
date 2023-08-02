<%-- 
    Document   : Products
    Created on : Feb 10, 2023, 1:06:00 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="robots" content="noindex,nofollow" />
        <title>Cập nhật giảm giá </title>
        <!-- Favicon icon -->
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
        <!-- Custom CSS -->
        <link
            href="assets/libs/magnific-popup/dist/magnific-popup.css"
            rel="stylesheet"
            />

        <link
            rel="stylesheet"
            type="text/css"
            href="assets/libs/select2/dist/css/select2.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="assets/libs/jquery-minicolors/jquery.minicolors.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="assets/libs/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="assets/libs/quill/dist/quill.snow.css"
            />

        <link href="dist/css/style.min.css" rel="stylesheet" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <button onclick="topFunction()" id="myBtn" title="Go to top"><i style="text-align: center" class="ti-arrow-up"></i></button>
            <fmt:setLocale value = "vi_VN"/>
            <%
                response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
                response.setHeader("Pragma", "no-cache"); //HTTP 1.0
                response.setDateHeader("Expires", 0);
            %>
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Main wrapper - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <div
            id="main-wrapper"
            data-layout="vertical"
            data-navbarbg="skin5"
            data-sidebartype="full"
            data-sidebar-position="absolute"
            data-header-position="absolute"
            data-boxed-layout="full"
            >
            <!-- ============================================================== -->
            <!-- Topbar header - style you can find in pages.scss -->
            <!-- ============================================================== -->
            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin5">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/index">
                            <b class="logo-icon"  style="margin: 0px;">
                                <img src="assets/images/logo.png" alt="homepage" class="light-logo" width="50" />
                            </b>
                            <span class="logo-text " style="margin-right: 15px">
                                <img src="${pageContext.request.contextPath}/images/logo_text.png" alt="homepage" class="light-logo" width="140" height="50" />
                            </span>
                        </a>
                        <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i
                                class="ti-menu ti-close"></i></a>
                    </div>
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <div
                        class="navbar-collapse collapse"
                        id="navbarSupportedContent"
                        data-navbarbg="skin5"
                        >
                        <!-- ============================================================== -->
                        <!-- toggle and nav items -->
                        <!-- ============================================================== -->
                        <ul class="navbar-nav float-start me-auto">
                            <li class="nav-item d-none d-lg-block">
                                <a
                                    class="nav-link sidebartoggler waves-effect waves-light"
                                    href="javascript:void(0)"
                                    data-sidebartype="mini-sidebar"
                                    ><i class="mdi mdi-menu font-24"></i
                                    ></a>
                            </li>
                            <!-- ============================================================== -->
                            <!-- create new -->
                            <!-- ============================================================== -->
                            <li class="nav-item dropdown">
                                <a
                                    class="nav-link dropdown-toggle"
                                    href="#"
                                    id="navbarDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                                    >
                                    <span class="d-none d-md-block"
                                          >Thêm <i class="fa fa-angle-down"></i
                                        ></span>
                                    <span class="d-block d-md-none"
                                          ><i class="fa fa-plus"></i
                                        ></span>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addsupplier">Thương hiệu</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addcategory">Danh mục</a></li>
                                    <li><hr class="dropdown-divider" /></li>
                                    <li>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addproduct">Sản phẩm</a>
                                    </li>
                                </ul>
                            </li>
                            <!-- ============================================================== -->
                            <!-- Search -->
                            <!-- ============================================================== -->
                            <li class="nav-item search-box">
                                <a
                                    class="nav-link waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    ><i class="mdi mdi-magnify fs-4"></i
                                    ></a>
                                <form class="app-search position-absolute" action="${pageContext.request.contextPath}/admin/products" method="post">
                                    <input
                                        name="pid"
                                        type="text"
                                        class="form-control"
                                        placeholder="Search by ID &amp; enter"
                                        />
                                    <a class="srh-btn"><i class="mdi mdi-window-close"></i></a>
                                </form>
                            </li>
                        </ul>
                        <!-- ============================================================== -->
                        <!-- Right side toggle and nav items -->
                        <!-- ============================================================== -->
                        <ul class="navbar-nav float-end">
                            <!-- ============================================================== -->
                            <!-- Comment -->
                            <!-- ============================================================== -->

                            <!-- ============================================================== -->
                            <!-- End Comment -->
                            <!-- ============================================================== -->
                            <!-- ============================================================== -->
                            <!-- Messages -->
                            <!-- ============================================================== -->

                            <!-- ============================================================== -->
                            <!-- End Messages -->
                            <!-- ============================================================== -->

                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                            <li class="nav-item dropdown">
                                <a
                                    class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic"
                                    href="#"
                                    id="navbarDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                                    >
                                    <img
                                        src="data:image/jpg;base64,${sessionScope.admin.base64Image}"
                                        alt="user"
                                        class="rounded-circle"
                                        width="31"
                                        />
                                </a>
                                <ul
                                    class="dropdown-menu dropdown-menu-end user-dd animated"
                                    aria-labelledby="navbarDropdown"
                                    >
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/profileadmin"
                                       ><i class="mdi mdi-account me-1 ms-1"></i> Thông tin của
                                        tôi</a
                                    >

                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/changepass.jsp"
                                       ><i class="mdi mdi-settings me-1 ms-1"></i> Đổi mật khẩu</a
                                    >

                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/logoutadmin"
                                       ><i class="fa fa-power-off me-1 ms-1"></i> Đăng xuất</a
                                    >
                                    <div class="dropdown-divider"></div>
                                    <div class="ps-4 p-10">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/profileadmin"
                                            class="btn btn-sm btn-success btn-rounded text-white"
                                            >Xem hồ sơ</a
                                        >
                                    </div>
                                </ul>
                            </li>
                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- ============================================================== -->
            <!-- End Topbar header -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
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
                            <li class="sidebar-item selected">
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
            <!-- ============================================================== -->
            <!-- End Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Page wrapper  -->
            <!-- ============================================================== -->
            <div class="page-wrapper">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <div class="page-breadcrumb">
                    <div class="row">
                        <div class="col-12 d-flex no-block align-items-center">
                            <h4 class="page-title">Sản phẩm</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Sản phẩm
                                        </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Container fluid  -->
                <!-- ============================================================== -->


                <div class="container-fluid">
                    <!-- ============================================================== -->
                    <!-- Start Page Content -->
                    <!-- ============================================================== -->

                    <div class="card">
                        <div class="card-body wizard-content">
                            <h4 class="card-title">Update Discount</h4>
                            <h6 class="card-subtitle">${requestScope.msg}</h6>
                            <form id="example-form" action="updatediscount" method="post" class="mt-5" >
                                <div>
                                    <section>
                                        <input type="hidden" name="discountid" value="${requestScope.discount.discountId}" class="required form-control"/>
                                        <label>Name *</label>
                                        <input name="name" type="text" value="${requestScope.discount.name}" class="required form-control" />
                                        <label>Description *</label>
                                        <textarea name="description" rows="5" value="${requestScope.discount.description}" class="form-control"></textarea>
                                        <label>isActive </label>
                                        <div class="col-md-9" style="display: flex">
                                            <div class="form-check col-md-6">
                                                <input type="radio" class="form-check-input" id="customControlValidation1" name="isActive"
                                                       value="ON" checked />
                                                <label class="form-check-label mb-0" for="customControlValidation1">Chưa</label>
                                            </div>
                                            <div class="form-check col-md-6">
                                                <input type="radio" class="form-check-input" name="isActive" value="OFF" />
                                                <label class="form-check-label mb-0" for="customControlValidation2">Đã ngưng</label>
                                            </div>
                                        </div>

                                        <label>Discount *</label>
                                        <div class="col-sm-12 row">
                                            <div class="col-md-11">
                                                <input value="${Math.round(requestScope.discount.discount*100)/100}" type="number" class="form-control required" aria-describedby="basic-addon2"
                                                       name="discount" min="0" required max="1" step="0.01"/>
                                            </div>
                                            <div class="col-md-1">
                                                <span class="input-group-text" id="basic-addon2">%</span>
                                            </div>
                                        </div>
                                        <label>Coupon Code*</label>
                                        <input name="couponCode" type="text" value="${requestScope.discount.couponCode}" class="required form-control" />
                                        <label>Start Date *</label>
                                        <input name="startDate" type="date" value="${requestScope.discount.startDate}" class="required form-control" />
                                        <label>Expiration Date *</label>
                                        <input name="expirationDate" type="date" value="${requestScope.discount.expirationDate}" class="required form-control" />
                                    </section>
                                </div>
                                <button
                                    style="
                                    background-color: orange;
                                    border: none;
                                    color: white;
                                    height: 50px;
                                    width: 200px;
                                    margin-top: 20px;
                                    "
                                    >
                                    Submit
                                </button>
                            </form>
                        </div>
                    </div>

                    <!-- ============================================================== -->
                    <!-- End PAge Content -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- Right sidebar -->
                    <!-- ============================================================== -->
                    <!-- .right-sidebar -->
                    <!-- ============================================================== -->
                    <!-- End Right sidebar -->
                    <!-- ============================================================== -->
                </div>
                <!-- ============================================================== -->
                <!-- End Container fluid  -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- footer -->
                <!-- ============================================================== -->
                <footer class="footer text-center">

                </footer>
                <!-- ============================================================== -->
                <!-- End footer -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Page wrapper  -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Wrapper -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- All Jquery -->
        <!-- ============================================================== -->
        <script src="assets/libs/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap tether Core JavaScript -->
        <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <!-- slimscrollbar scrollbar JavaScript -->
        <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
        <script src="assets/extra-libs/sparkline/sparkline.js"></script>
        <!--Wave Effects -->
        <script src="dist/js/waves.js"></script>
        <!--Menu sidebar -->
        <script src="dist/js/sidebarmenu.js"></script>
        <!--Custom JavaScript -->
        <script src="dist/js/custom.min.js"></script>
        <!-- this page js -->
        <script src="assets/libs/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
        <script src="assets/libs/magnific-popup/meg.init.js"></script>
        <script src="assets/libs/inputmask/dist/min/jquery.inputmask.bundle.min.js"></script>
        <script src="dist/js/pages/mask/mask.init.js"></script>
        <script src="assets/libs/select2/dist/js/select2.full.min.js"></script>
        <script src="assets/libs/select2/dist/js/select2.min.js"></script>
        <script src="assets/libs/jquery-asColor/dist/jquery-asColor.min.js"></script>
        <script src="assets/libs/jquery-asGradient/dist/jquery-asGradient.js"></script>
        <script src="assets/libs/jquery-asColorPicker/dist/jquery-asColorPicker.min.js"></script>
        <script src="assets/libs/jquery-minicolors/jquery.minicolors.min.js"></script>
        <script src="assets/libs/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
        <script src="assets/libs/quill/dist/quill.min.js"></script>

        <script>

                                    $(".select2").select2();

                                    $(".demo").each(function () {
                                        $(this).minicolors({
                                            control: $(this).attr("data-control") || "hue",
                                            position: $(this).attr("data-position") || "bottom left",

                                            change: function (value, opacity) {
                                                if (!value)
                                                    return;
                                                if (opacity)
                                                    value += ", " + opacity;
                                                if (typeof console === "object") {
                                                    console.log(value);
                                                }
                                            },
                                            theme: "bootstrap",
                                        });
                                    });
                                    /*datwpicker*/
                                    jQuery(".mydatepicker").datepicker();
                                    jQuery("#datepicker-autoclose").datepicker({
                                        autoclose: true,
                                        todayHighlight: true,
                                    });
                                    var quill = new Quill("#editor", {
                                        theme: "snow",
                                    });

                                    function selectAllCheckbox() {
                                        var checkboxes = document.querySelectorAll('input[type="checkbox"]');
                                        checkboxes.forEach(function (checkbox) {
                                            checkbox.checked = true;
                                        });
                                        var checkedValues = [];
                                        checkboxes.forEach(function (checkbox) {
                                            if (checkbox.checked) {
                                                checkedValues.push(checkbox.value);
                                            }
                                        }
                                        );
                                    }
                                    function uncheckAllCheckbox() {
                                        var checkboxes = document.querySelectorAll('input[type="checkbox"]');
                                        checkboxes.forEach(function (checkbox) {
                                            checkbox.checked = false;
                                        });
                                    }

                                    function isAnyCheckboxChecked() {
                                        var checkboxes = document.querySelectorAll('input[type="checkbox"]');
                                        for (var i = 0; i < checkboxes.length; i++) {
                                            if (checkboxes[i].checked) {
                                                return true;
                                            }
                                        }

                                        return false;
                                    }

        </script>
    </body>
</html>