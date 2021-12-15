<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link  rel="stylesheet" href="/assets/css/templatemo-hexashop.css">
<script src="https://use.fontawesome.com/518bcdd6c6.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<%@include file="adminheader.jsp" %>
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
        font-size: 15px;
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
    .icon-input-btn {
        position: relative;
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
<html>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-lg-12" style="padding-top: 20px; padding-bottom: 20px;">
                    <h2 style="text-align: center;">Products Ordered Details</h2>
                </div>
            </div>
        </div>
        <div class="container">
        <c:forEach items="${orderdisplaylist}" var="orderdisplaylist">
                        <div class="cart_items">
                            <ul class="cart_list">
                                <li class="cart_item clearfix">
                                    <div class="cart_item_info d-flex flex-md-row flex-column justify-content-between">
                                        <div class="cart_item_name cart_info_col">
                                            <div class="cart_item_title"><h5>Customer Name</h5></div>
                                            <div class="cart_item_text"><c:out value="${orderdisplaylist.name}"/></div>
                                        </div>
                                        <div class="cart_item_quantity cart_info_col">
                                            <div class="cart_item_title"><h5>Ordered Date</h5></div>
                                            <div class="cart_item_text"><c:out value="${orderdisplaylist.date}"/></div>
                                        </div>
                                        <div class="cart_item_price cart_info_col">
                                            <div class="cart_item_title"><h5>Price</h5></div>
                                            <div class="cart_item_text">Rs.<c:out value="${orderdisplaylist.amount}"/></div>
                                        </div>
                                        <div class="cart_item_total cart_info_col">
                                            <div class="cart_item_title"><h5>Phone Number</h5></div>
                                            <div class="cart_item_text" ><c:out value="${orderdisplaylist.phoneno}"/></div>
                                        </div>
                                        <div class="cart_item_total cart_info_col">
                                            <div class="cart_item_title"><h5>View Products Ordered</h5></div>
                                            <div class="cart_item_text"><a href="/indorder/${orderdisplaylist.orderid}" style="text-decoration: none;"><i class="fa fa-product-hunt fa-3x" style="color: black;" aria-hidden="true"></i></a></div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                </div>
        <!--<table width: 100%; text-align:center>
            <tr>
                <th>OrderId</th>
                <th>Name</th>
                <th>Date</th>
                <th>Amount</th>
                <th>District</th>
                <th>Phone number</th>
            </tr>
            <tbody>
                <c:forEach items="${orderdisplaylist}" var="orderdisplaylist">
                <tr>
                        <td><a href="/indorder/${orderdisplaylist.orderid}">${orderdisplaylist.orderid}</a></td>
                        <td><c:out value="${orderdisplaylist.name}"/></td>
                        <td><c:out value="${orderdisplaylist.date}"/></td>
                        <td><c:out value="${orderdisplaylist.amount}"/></td>
                        <td><c:out value="${orderdisplaylist.district}"/></td>
                        <td><c:out value="${orderdisplaylist.phoneno}"/></td>
                </tr>  
                </c:forEach>
                </tbody>
        </table>-->
    </body>
</html>