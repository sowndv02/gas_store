<%-- 
    Document   : formaddproduct
    Created on : Feb 8, 2023, 8:03:44 PM
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
        <title>#${requestScope.product.productId}~${requestScope.product.name}</title>
        <!-- Favicon icon -->
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
        <!-- Custom CSS -->
        <link
            href="assets/libs/jquery-steps/jquery.steps.css"
            rel="stylesheet"
            />
        <link href="assets/libs/jquery-steps/steps.css" rel="stylesheet" />
        <link href="dist/css/style.min.css" rel="stylesheet" />
        <link href="dist/css/confirmproduct.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <style>
            .image-area {
                position: relative;
            }

            .remove-image {
                position: absolute;
                top: 0;
                right: 0;
                display: inline;
                color: white;
                background-color: rgba(0, 0, 0, 0.5);
                padding: 5px;
                text-decoration: none;
                border-radius: 50%;
                font-size: 12px;
            }

            .ck-editor__editable[role="textbox"] {
                /* editing area */
                min-height: 200px;
            }
            .ck-content .image {
                /* block images */
                max-width: 80%;
                margin: 20px auto;
            }

        </style>
        <fmt:setLocale value = "vi_VN"/>
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
                                <form class="app-search position-absolute">
                                    <input
                                        type="text"
                                        class="form-control"
                                        placeholder="Search &amp; enter"
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
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/changepass.jsp"
                                       ><i class="mdi mdi-settings me-1 ms-1"></i> Đổi mật khẩu</a
                                    >

                                    <div class="dropdown-divider"></div>
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
                            <h4 class="page-title">Xác nhận</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Xác nhận
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
                            <h4 class="card-title">Sản phẩm</h4>
                            <h6 class="card-subtitle">${requestScope.msg}</h6>
                            <form id="example-form" action="updateproduct" method="post" class="mt-5" enctype="multipart/form-data">
                                <input type="hidden" value="${requestScope.product.productId}" name="pid" >
                                <div>
                                    <h3>Sản phẩm</h3>
                                    <label for="productName">Tên sản phẩm *</label>
                                    <input
                                        id="productName"
                                        name="productName"
                                        type="text"
                                        value="${requestScope.product.name}"
                                        class="required form-control"
                                        />
                                    <label class="col-md-3 mt-3">Danh mục</label>
                                    <div class="col-md-12">
                                        <select
                                            name="categoryID"
                                            class="select2 form-select shadow-none"
                                            style="width: 100%; height: 36px"
                                            >
                                            <option>Select</option>
                                            <optgroup label="Danh mục">
                                                <c:forEach items="${requestScope.listAllCate}" var="c">
                                                    <option value="${c.categoryId}" <c:if test="${requestScope.product.category.categoryId == c.categoryId}">selected</c:if>>${c.name}</option>
                                                </c:forEach>
                                            </optgroup>
                                        </select>
                                    </div>

                                    <label class="col-md-3 mt-3">Thương hiệu</label>
                                    <div class="col-md-12">
                                        <select
                                            name="supplierID"
                                            class="select2 form-select shadow-none"
                                            style="width: 100%; height: 36px"
                                            >
                                            <option>Select</option>
                                            <optgroup label="Thương hiệu">
                                                <c:forEach items="${requestScope.listAllSup}" var="s">
                                                    <option value="${s.supplierId}" 
                                                            <c:if test="${s.supplierId eq requestScope.product.supplier.supplierId}">selected</c:if>
                                                            >${s.companyName}</option>
                                                </c:forEach>
                                            </optgroup>
                                        </select>
                                    </div>

                                    <label for="price" class="col-md-3 mt-3">Giá tiền</label>
                                    <div class="col-sm-12 row">
                                        <div class="col-md-11">
                                            <input
                                                value="<fmt:formatNumber value = "${product.unitPrice}"/>"
                                                name="price"
                                                type="text"
                                                class="form-control"
                                                />
                                        </div>
                                        <div class="col-md-1">
                                            <span class="input-group-text" id="basic-addon2">đ</span>
                                        </div>
                                    </div>

                                    <label for="stock" class="col-md-3 mt-3"
                                           >Số lượng trong kho *</label
                                    >
                                    <input
                                        value="${requestScope.product.stockQuantity}"
                                        id="stock"
                                        name="stock"
                                        type="text"
                                        class="required form-control"
                                        />

                                    <label for="discount" class="col-md-3 mt-3"
                                           >Discount *</label
                                    >
                                    <div class="col-sm-12 row">
                                        <div class="col-md-11">
                                            <select
                                                name="discount"
                                                class="select2 form-select shadow-none"
                                                >
                                                <option>Select</option>
                                                <optgroup label="Discount">
                                                    <c:forEach items="${requestScope.discounts}" var="d">
                                                        <option value="${d.discountId}" 
                                                                <c:if test="${d.discountId eq requestScope.product.discount.discountId}">selected</c:if>
                                                                ><fmt:formatNumber type = "percent" value = "${d.discount}" /></option>
                                                    </c:forEach>
                                                </optgroup>
                                            </select>
                                        </div>
                                        <div class="col-md-1">
                                            <span class="input-group-text" id="basic-addon2"
                                                  >%</span
                                            >
                                        </div>
                                    </div>

                                    <label class="col-md-3 mt-3">Ngừng sản xuất</label>
                                    <div class="col-md-9" style="display: flex">
                                        <div class="form-check col-md-6">
                                            <input
                                                type="radio"
                                                class="form-check-input"
                                                id="customControlValidation1"
                                                name="discontinued"
                                                <c:if test="${requestScope.product.isActive}">checked</c:if>

                                                    value="ON"
                                                    required
                                                    />
                                                <label
                                                    class="form-check-label mb-0"
                                                    for="customControlValidation1"
                                                    >Chưa</label
                                                >
                                            </div>
                                            <div class="form-check col-md-6">
                                                <input
                                                    type="radio"
                                                    class="form-check-input"
                                                    id="customControlValidation2"
                                                    name="discontinued"
                                                    required
                                                <c:if test="${!requestScope.product.isActive}">checked</c:if>
                                                    value="OFF"
                                                    />
                                                <label
                                                    class="form-check-label mb-0"
                                                    for="customControlValidation2"
                                                    >Đã ngưng</label
                                                >
                                            </div>
                                        </div>

                                        <label class="col-md-3 mt-3">Hình ảnh đại diện</label>
                                        <div class="col-md-12">
                                            <div class="image-area" style="margin-left: 400px; border: none">
                                                <img style="width: 300px; height: 298px" src="${pageContext.request.contextPath}/${requestScope.product.base64Image}" alt="Preview" />
                                        </div>
                                        <div class="custom-file" style="margin-left: 400px">
                                            <input
                                                type="file"
                                                name="add"
                                                />
                                            <label class="custom-file-label" for="validatedCustomFile"
                                                   >Choose file...</label
                                            >
                                        </div>
                                    </div>
                                    <label for="size">Warranty *</label>
                                    <input
                                        id="warranty"
                                        name="warranty"
                                        type="text"
                                        class="required form-control"
                                        value="${requestScope.product.warranty}"
                                        />
                                    <label for="keyword">Keyword *</label>
                                    <input
                                        id="keyword"
                                        name="keyword"
                                        type="text"
                                        class="required form-control"
                                        value="${requestScope.product.keyword}"
                                        />
                                    <label for="shortDescription">shortDescription *</label>
                                    <input
                                        id="shortDescription"
                                        name="shortDescription"
                                        type="text"
                                        value="${requestScope.product.shortDescription}"
                                        class="required form-control"
                                        />
                                    <label for="description">description</label>
                                    <textarea style="height: 300px" id="editor" name="description">${requestScope.product.description}
                                    </textarea><br/>
                                    <label for="DateCreated">DateCreated</label>
                                    <input
                                        id="DateCreated"
                                        name="DateCreated"
                                        type="text"
                                        readonly
                                        class="form-control"
                                        value="${requestScope.product.createdDate}"
                                        />
                                    <h3>Hình ảnh sản phẩm</h3>
                                    <p>Thêm các hình ảnh của sản phẩm</p>

                                    <div class="row">
                                        <c:forEach items="${requestScope.listAllImage}" var="img">
                                            <div class="image-area col-md-6" style="border-right: 1px solid black;width: 300px; height: 298px">
                                                <img style="width: 300px; height: 298px" src="${pageContext.request.contextPath}/${img.base64Image}" alt="Preview" />
                                                <a class="remove-image" href="updateproduct?pid=${requestScope.product.productId}&imgid=${img.productImgId}" onclick="return confirm('Are you sure you want delete image?')" style="display: inline"
                                                   >&#215;</a
                                                >
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <input style="margin-bottom: 20px" type="file" name="file" multiple /> <br />
                                    <input
                                        id="acceptTerms"
                                        name="acceptTerms"
                                        type="checkbox"
                                        class="required"
                                        required
                                        style="margin: 20px 0"
                                        />
                                    <label for="acceptTerms"
                                           >Tôi đồng ý với các Điều khoản và Điều kiện.</label
                                    >
                                </div>
                                <button
                                    onclick="confirmAndSubmit()"
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
        <script src="assets/libs/jquery-steps/build/jquery.steps.min.js"></script>
        <script src="assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/super-build/ckeditor.js"></script>
        <script>
                                        function confirmAndSubmit() {
                                            alert('Submit the form successfully!');
                                            document.getElementById('example-form').action = '${pageContext.request.contextPath}/admin/updateproduct'; // change the form action to the new URL
                                            document.getElementById('example-form').submit(); // submit the form
                                        }


                                        CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {
                                            toolbar: {
                                                items: [
                                                    'exportPDF', 'exportWord', '|',
                                                    'findAndReplace', 'selectAll', '|',
                                                    'heading', '|',
                                                    'bold', 'italic', 'strikethrough', 'underline', 'code', 'subscript', 'superscript', 'removeFormat', '|',
                                                    'bulletedList', 'numberedList', 'todoList', '|',
                                                    'outdent', 'indent', '|',
                                                    'undo', 'redo',
                                                    '-',
                                                    'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor', 'highlight', '|',
                                                    'alignment', '|',
                                                    'link', 'insertImage', 'blockQuote', 'insertTable', 'mediaEmbed', 'codeBlock', 'htmlEmbed', '|',
                                                    'specialCharacters', 'horizontalLine', 'pageBreak', '|',
                                                    'textPartLanguage', '|',
                                                    'sourceEditing'
                                                ],
                                                shouldNotGroupWhenFull: true
                                            },
// Changing the language of the interface requires loading the language file using the <script> tag.
// language: 'es',
                                            list: {
                                                properties: {
                                                    styles: true,
                                                    startIndex: true,
                                                    reversed: true
                                                }
                                            },
// https://ckeditor.com/docs/ckeditor5/latest/features/headings.html#configuration
                                            heading: {
                                                options: [
                                                    {model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph'},
                                                    {model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1'},
                                                    {model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2'},
                                                    {model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3'},
                                                    {model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4'},
                                                    {model: 'heading5', view: 'h5', title: 'Heading 5', class: 'ck-heading_heading5'},
                                                    {model: 'heading6', view: 'h6', title: 'Heading 6', class: 'ck-heading_heading6'}
                                                ]
                                            },
// https://ckeditor.com/docs/ckeditor5/latest/features/editor-placeholder.html#using-the-editor-configuration
                                            placeholder: 'Welcome to CKEditor 5!',
// https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-family-feature
                                            fontFamily: {
                                                options: [
                                                    'default',
                                                    'Arial, Helvetica, sans-serif',
                                                    'Courier New, Courier, monospace',
                                                    'Georgia, serif',
                                                    'Lucida Sans Unicode, Lucida Grande, sans-serif',
                                                    'Tahoma, Geneva, sans-serif',
                                                    'Times New Roman, Times, serif',
                                                    'Trebuchet MS, Helvetica, sans-serif',
                                                    'Verdana, Geneva, sans-serif'
                                                ],
                                                supportAllValues: true
                                            },
// https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-size-feature
                                            fontSize: {
                                                options: [10, 12, 14, 'default', 18, 20, 22],
                                                supportAllValues: true
                                            },
// Be careful with the setting below. It instructs CKEditor to accept ALL HTML markup.
// https://ckeditor.com/docs/ckeditor5/latest/features/general-html-support.html#enabling-all-html-features
                                            htmlSupport: {
                                                allow: [
                                                    {
                                                        name: /.*/,
                                                        attributes: true,
                                                        classes: true,
                                                        styles: true
                                                    }
                                                ]
                                            },
// Be careful with enabling previews
// https://ckeditor.com/docs/ckeditor5/latest/features/html-embed.html#content-previews
                                            htmlEmbed: {
                                                showPreviews: true
                                            },
// https://ckeditor.com/docs/ckeditor5/latest/features/link.html#custom-link-attributes-decorators
                                            link: {
                                                decorators: {
                                                    addTargetToExternalLinks: true,
                                                    defaultProtocol: 'https://',
                                                    toggleDownloadable: {
                                                        mode: 'manual',
                                                        label: 'Downloadable',
                                                        attributes: {
                                                            download: 'file'
                                                        }
                                                    }
                                                }
                                            },
// https://ckeditor.com/docs/ckeditor5/latest/features/mentions.html#configuration
                                            mention: {
                                                feeds: [
                                                    {
                                                        marker: '@',
                                                        feed: [
                                                            '@apple', '@bears', '@brownie', '@cake', '@cake', '@candy', '@canes', '@chocolate', '@cookie', '@cotton', '@cream',
                                                            '@cupcake', '@danish', '@donut', '@dragée', '@fruitcake', '@gingerbread', '@gummi', '@ice', '@jelly-o',
                                                            '@liquorice', '@macaroon', '@marzipan', '@oat', '@pie', '@plum', '@pudding', '@sesame', '@snaps', '@soufflé',
                                                            '@sugar', '@sweet', '@topping', '@wafer'
                                                        ],
                                                        minimumCharacters: 1
                                                    }
                                                ]
                                            },
// The "super-build" contains more premium features that require additional configuration, disable them below.
// Do not turn them on unless you read the documentation and know how to configure them and setup the editor.
                                            removePlugins: [
                                                // These two are commercial, but you can try them out without registering to a trial.
                                                // 'ExportPdf',
                                                // 'ExportWord',
                                                'CKBox',
                                                'CKFinder',
                                                'EasyImage',
                                                // This sample uses the Base64UploadAdapter to handle image uploads as it requires no configuration.
                                                // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/base64-upload-adapter.html
                                                // Storing images as Base64 is usually a very bad idea.
                                                // Replace it on production website with other solutions:
                                                // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/image-upload.html
                                                // 'Base64UploadAdapter',
                                                'RealTimeCollaborativeComments',
                                                'RealTimeCollaborativeTrackChanges',
                                                'RealTimeCollaborativeRevisionHistory',
                                                'PresenceList',
                                                'Comments',
                                                'TrackChanges',
                                                'TrackChangesData',
                                                'RevisionHistory',
                                                'Pagination',
                                                'WProofreader',
                                                // Careful, with the Mathtype plugin CKEditor will not load when loading this sample
                                                // from a local file system (file://) - load this site via HTTP server if you enable MathType.
                                                'MathType',
                                                // The following features are part of the Productivity Pack and require additional license.
                                                'SlashCommand',
                                                'Template',
                                                'DocumentOutline',
                                                'FormatPainter',
                                                'TableOfContents'
                                            ]
                                        });
        </script>
    </body>
</html>