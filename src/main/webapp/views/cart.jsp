<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="header.jsp" %>
<style>
body {
    font-family: 'Rubik', sans-serif;
    font-size: 14px;
    font-weight: 400;
    color: #000000
}
ul {
    list-style: none;
    margin-bottom: 0px
}
.button {
    display: inline-block;
    background: red;
    border-radius: 5px;
    height: 48px;
    -webkit-transition: all 200ms ease;
    -moz-transition: all 200ms ease;
    -ms-transition: all 200ms ease;
    -o-transition: all 200ms ease;
    transition: all 200ms ease
}
.button a {
    display: block;
    font-size: 18px;
    font-weight: 400;
    line-height: 48px;
    color: #FFFFFF;
    padding-left: 35px;
    padding-right: 35px
}
.button:hover {
    opacity: 0.8
}
.cart_section {
    width: 100%;
    padding-top: 30px;
    padding-bottom: 111px
}
.cart_title {
    font-size: 30px;
    font-weight: 500
}
.cart_items {
    margin-top: 8px
}
.cart_list {
    border: solid 1px #e8e8e8;
    box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);
    background-color: #fff
}
.cart_item {
    width: 100%;
    padding: 15px;
    padding-right: 46px
}
.cart_item_image {
    width: 133px;
    height: 133px;
    float: left
}
.cart_item_image img {
    max-width: 100%
}
.cart_item_info {
    width: calc(100% - 133px);
    float: left;
    padding-top: 18px
}
.cart_item_name {
    margin-left: 7.53%
}
.cart_item_title {
    font-size: 14px;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.5)
}
.cart_item_text {
    font-size: 18px;
    margin-top: 35px
}
.cart_item_text span {
    display: inline-block;
    width: 20px;
    height: 20px;
    border-radius: 50%;
    margin-right: 11px;
    -webkit-transform: translateY(4px);
    -moz-transform: translateY(4px);
    -ms-transform: translateY(4px);
    -o-transform: translateY(4px);
    transform: translateY(4px)
}
.cart_item_price {
    text-align: right
}
.cart_item_total {
    text-align: right
}
.order_total {
    width: 100%;
    height: 60px;
    margin-top: 30px;
    border: solid 1px #e8e8e8;
    box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);
    padding-right: 46px;
    padding-left: 15px;
    background-color: #fff
}
.order_total_title {
    display: inline-block;
    font-size: 14px;
    color: rgba(0, 0, 0, 0.5);
    line-height: 60px
}
.order_total_amount {
    display: inline-block;
    font-size: 18px;
    font-weight: 500;
    margin-left: 26px;
    line-height: 60px
}
.cart_buttons {
    margin-top: 60px;
    text-align: right
}
.cart_button_clear {
    display: inline-block;
    border: none;
    font-size: 18px;
    font-weight: 400;
    line-height: 48px;
    color: rgba(0, 0, 0, 0.5);
    background: #FFFFFF;
    border: solid 1px #b2b2b2;
    padding-left: 35px;
    padding-right: 35px;
    outline: none;
    cursor: pointer;
    margin-right: 26px
}
.cart_button_clear:hover {
    border-color: #0e8ce4;
    color: #0e8ce4
}
.cart_button_checkout {
    display: inline-block;
    border: none;
    font-size: 18px;
    font-weight: 400;
    line-height: 48px;
    color: #FFFFFF;
    padding-left: 35px;
    padding-right: 35px;
    outline: none;
    cursor: pointer;
    vertical-align: top
}
.shopping {
    display: inline-block;
    border: none;
    font-size: 18px;
    font-weight: 400;
    line-height: 48px;
    color: #FFFFFF;
    padding-left: 35px;
    padding-right: 35px;
    outline: none;
    cursor: pointer;
    background: red;
    
}
.empty-cart h3{
    font-weight:bolder;
    
}
.empty-cart p{
    text-decoration: gray;
}
.icon-input-btn {
    position: relative;
}
.empty-cart{
    margin: auto;
    width: 30%;
    padding: 10px;
}

.fontuser i{
            position: absolute;
            left: 15px;
            color: gray;
        }
        .icon-input-btn input[type="submit"]{
            padding-left: 20px;
        }
        .icon-input-btn{
            position: relative;
        }
        .icon-input-btn i{
            position: absolute;
            left:0;
            padding: 9px 8px;
            color: white;
            transition: .3s;
        }
</style>
<div class="cart_section">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-10 offset-lg-1">
                <div class="cart_container">
                    <div class="cart_title">Your Shopping Cart <i class="fa fa-cart-arrow-down fa-2x" style="margin-left: 5px;" aria-hidden="true"></i></div>
                    <c:if test="${empty cart}">
                        <div class="empty-cart">
                            <img src="/assets/images/cart.jpg"/>
                        <h3>Hey, it feels so light!</h3>
                        <p>There is nothing in your bag.Let's add some clothing</p>
                            <a href="/products"><button type="button" class="shopping">Continue Shopping</button></a>
                        </div>
                    </c:if>
                    <c:if test="${not empty cart}">
                        <c:forEach items="${cart}" var="cart">
                        <div class="cart_items">
                            <ul class="cart_list">
                                <li class="cart_item clearfix">
                                    <div class="cart_item_image"><img src="data:image/jpg;base64,${cart.image}" style="height: 130px;" alt=""></div>
                                    <div class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
                                        <div class="cart_item_name cart_info_col">
                                            <div class="cart_item_title">Name</div>
                                            <div class="cart_item_text"><c:out value="${cart.productname}"/></div>
                                        </div>
                                        <div class="cart_item_quantity cart_info_col">
                                            <div class="cart_item_title">Quantity</div>
                                            <div class="cart_item_text"><c:out value="${cart.quantity}"/></div>
                                        </div>
                                        <div class="cart_item_price cart_info_col">
                                            <div class="cart_item_title">Price</div>
                                            <div class="cart_item_text">Rs.<c:out value="${cart.price}"/></div>
                                        </div>
                                        <form action="/deletecart/${cart.cartid}" method="GET">
                                            <div class="icon-input-btn">
                                                <input type="submit" value="Remove" class="btn btn-danger btn-sm"/>
                                                <i class="fa fa-trash" aria-hidden="true" style="padding-right: 10px;"></i>
                                            </div>
                                        </form>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="exampleModalLabel">Proceed to Payment</h5>
                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <form action="/addaddress" method="POST">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-user-circle-o" style="padding-right: 5px;" aria-hidden="true"></i>Name</label>
                                        <input type="text" name="name" class="form-control" id="name">
                                    </div>
                                    <div class="mb-3">
                                        <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-phone-square" style="padding-right: 5px;" aria-hidden="true"></i>Phone Number</label>
                                        <input type="text" name="phone" class="form-control" id="phone">
                                    </div>
                                    <div class="mb-3">
                                        <label for="exampleFormControlTextarea1" class="form-label"><i class="fa fa-address-book-o" style="padding-right: 5px;" aria-hidden="true"></i>Address</label>
                                        <textarea class="form-control" id="address" name="address" rows="3"></textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-map-marker" style="padding-right: 5px;" aria-hidden="true"></i>District</label>
                                        <input type="text" name="district" class="form-control" id="district">
                                    </div>
                                    <div class="mb-3">
                                        <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-map-marker" style="padding-right: 5px;" aria-hidden="true"></i>State</label>
                                        <input type="text" name="state" class="form-control" id="state">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="submit" value="Proceed to Payment" class="btn btn-primary"/>
                                    <!--<button type="button" class="btn btn-danger">Proceed to Payment</button>-->
                                </div>
                            </form>
                          </div>
                        </div>
                      </div>
                        <div class="modal fade" id="addressModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <h5 class="modal-title" id="exampleModalLabel">Your Order Will be Delivered To</h5>
                                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <c:forEach items="${address}" var="address">
                                    <form action="/addaddress" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-user-circle-o" style="padding-right: 5px;" aria-hidden="true"></i>Name of the Customer</label>
                                                <input type="text" class="form-control" name="name" value="${address.name}"/><br>
                                                <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-phone-square" style="padding-right: 5px;" aria-hidden="true"></i>Phone Number</label>
                                                <input type="text" class="form-control" name="phone" value="${address.phonenumber}"/><br>
                                                <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-address-book-o" style="padding-right: 5px;" aria-hidden="true"></i>Address of the Customer</label>
                                                <textarea type="text" class="form-control" name="address" rows="3">${address.address}</textarea><br>
                                                <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-map-marker" style="padding-right: 5px;" aria-hidden="true"></i>District</label>
                                                <input type="text" class="form-control" name="district" value="${address.district}"/><br>
                                                <label for="exampleFormControlInput1" class="form-label"><i class="fa fa-map-marker" style="padding-right: 5px;" aria-hidden="true"></i>State</label>
                                                <input type="text" class="form-control" name="state" value="${address.state}"/><br>
                                                <!-- <a href="#exampleModal" data-toggle="modal" data-dismiss="modal"> Edit</a> -->
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <input type="submit" value="Proceed to Payment" class="btn btn-primary"/>
                                        <!--<button type="button" class="btn btn-danger">Proceed to Payment</button>-->
                                        </div>
                                    </form>
                                </c:forEach>
                              </div>
                            </div>
                          </div>
                    <div class="order_total">
                        <div class="order_total_content" style="text-align: right;">
                            <div class="order_total_title">Order Total:</div>
                            <div class="order_total_amount">Rs. <c:out value="${total}"/></div>
                        </div>
                    </div>
                    <div class="cart_buttons"> 
                            <!--<i class="fa fa-cart-plus" aria-hidden="true"></i>-->
                            <a href="/products"><button type="button" class="button cart_button_clear">Continue Shopping</button></a> 
                            <c:if test="${empty address}">
                                <button type="button" class="button cart_button_checkout" data-bs-toggle="modal" data-bs-target="#exampleModal">Place Order</button> 
                            </c:if>
                            <c:if test="${not empty address}">
                                <button type="button" class="button cart_button_checkout" data-bs-toggle="modal" data-bs-target="#addressModal">Place Order</button> 
                            </c:if>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>