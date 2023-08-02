
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Begin Footer Area -->
<div class="footer">
    <!-- Begin Footer Static Top Area -->
    <div class="footer-static-top">
        <div class="container">
            <!-- Begin Footer Shipping Area -->
            <div class="footer-shipping pt-60 pb-55 pb-xs-25">
                <div class="row">
                    <!-- Begin Li's Shipping Inner Box Area -->
                    <c:forEach items="${footers}" var="footer">
                        <div class="col-lg-3 col-md-6 col-sm-6 pb-sm-55 pb-xs-55">
                            <div class="li-shipping-inner-box">
                                <div class="shipping-icon">
                                    <img src="${footer.img}" alt="Shipping Icon">
                                </div>
                                <div class="shipping-text">
                                    <h2>${footer.content}</h2>
                                    <p>${footer.description}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!-- Li's Shipping Inner Box Area End Here -->

                </div>
            </div>
            <!-- Footer Shipping Area End Here -->
        </div>
    </div>
</div>
<!-- Footer Area End Here -->