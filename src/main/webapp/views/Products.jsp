
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp" %>

 <!--<div class="page-heading" id="top" style="margin-top: 80px;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="inner-content">
                        <h2 style="color: white;">Check Our Products</h2>-->
                        <!--<span>&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&</span>-->
                 <!--   </div>
                </div>
            </div>
        </div>
    </div>-->
    
    <section class="section" id="products">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-heading">
                        <h2>Our Latest Products</h2>
                        <span>Check out all of our products.</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <c:forEach items="${products}" var="product">
                    <div class="col-lg-4">
                        <form action="${pageContext.request.contextPath }/addcart/${product.productid}" method="get">
                            <div class="item">
                            <div class="thumb" style="text-align: center;">
                                <div class="hover-content" style="margin-left: -10px;">
                                    <ul>
                                        <li><button onclick="alertfunction()" style=" border-radius: 7px; padding: 15px;"><i class="fa fa-shopping-cart"></i></button></li>
                                    </ul>
                                </div>
                                <img src="data:image/jpg;base64,${product.image}" style="height:230px; width: 200px;" alt="">
                            </div>
                            <div class="down-content">
                                    <h4><c:out value="${product.productname}"/></h4>
                                    <span>Rs :<c:out value="${product.price}"/></span>
                                    <!--<span>Availability of Product in Store :<c:out value="${product.quantity_aval}"/></span>-->
                                    <div class="quantity-content">
                                        <div class="left-content">
                                            <h6>No. of Orders</h6>
                                        </div>
                                        <div class="right-content">
                                            <div class="quantity buttons_added">
                                                <button type="button" class="minus" onclick="dec('qty${product.productid}')">-</button>
                                                <input type="number" id="qty${product.productid}" min="1" max="${product.quantity_aval}" name="quantity" value="1" title="Qty" class="input-text qty text">
                                                <button type="button" onclick="inc('qty${product.productid}')" class="plus">+</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <script>
        
         function alertfunction(){
            alert("Product Added to Your Cart");
        }
        function dec(id)
                {
                        document.getElementById(id).value=parseInt(document.getElementById(id).value)-1;      
                }
                function inc(id)
                {
                    document.getElementById(id).value=parseInt(document.getElementById(id).value)+1;            
                }
    </script>

<%@include file="footer.jsp" %>