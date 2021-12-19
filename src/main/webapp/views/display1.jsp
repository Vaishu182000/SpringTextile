<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link  rel="stylesheet" href="/assets/css/templatemo-hexashop.css">
<script src="https://use.fontawesome.com/518bcdd6c6.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<%@include file="adminheader.jsp" %>
<style>
    .icon-input-btn {
    position: relative;
}
.fontuser i{
            position: absolute;
            left: 15px;
            color: gray;
        }
        .icon-input-btn input[type="submit"]{
            padding-left: 30px;
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
        .icon-input-update {
    position: relative;
}
.fontuser i{
            position: absolute;
            left: 15px;
            color: gray;
        }
        .icon-input-update input[type="submit"]{
            padding-left: 30px;
        }
        .icon-input-update{
            position: relative;
        }
        .icon-input-update i{
            position: absolute;
            left:0;
            padding: 9px 8px;
            color: black;
            transition: .3s;
        }
        .fontset{
            font-style: italic;
            font-weight: bold;
            font-size: 25px;
            text-align: center;
        }
        th{
            font-size: 30px;
            font-style: oblique;
            text-align: center;
        }
        table, th, td{
            border: 1px solid black;
        }
        .card{
            box-shadow: 8px 5px 5px #85edff;
        }
        .card:hover{
            box-shadow: 8px 5px 5px #fa683c;
        }
</style>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-12" style="padding-top: 20px; padding-bottom: 20px;">
                <h2 style="text-align: center;">Products Available in Your Store</h2>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <c:forEach items="${products}" var="product">
                <div class="col-lg-3">
                    <form action="${pageContext.request.contextPath }/updatedelete/${product.productid}" method="get">
                        <div class="card" style="width: 18rem;">
                            <div style="text-align: center;">
                                <img src="data:image/jpg;base64,${product.image}" style="width: 150px; height: 150px;" class="card-img-top" alt="...">
                            </div>
                            <div class="card-body">
                              <h3 class="card-title"><c:out value="${product.productname}"/></h3>
                              <div style="padding-top: 10px; padding-bottom: 10px;">
                                <h6 class="card-text"><i class="fa fa-product-hunt" aria-hidden="true" style="padding-right: 5px;"></i>Product ID : <c:out value="${product.productid}"/></h6>
                                <h6 class="card-text"><i class="fa fa-money" aria-hidden="true" style="padding-right: 5px;"></i>Price : <c:out value="${product.price}"/></h6>
                                <h6 class="card-text"><i class="fa fa-list-ol" aria-hidden="true" style="padding-right: 5px;"></i>Quantity : <c:out value="${product.quantity_aval}"/></h6>
                              </div>
                              <div style="display: inline-flex;">
                              <div class="icon-input-update">
                                <input type="submit" name="update" value="Update" class="btn btn-warning">
                                <i class="fa fa-pencil" aria-hidden="true" style="padding-right: 15px;"></i>
                              </div>
                              <div style="padding-left:20px">
                              <div class="icon-input-btn">
                                <input type="submit" name="delete" value="Delete" class="btn btn-danger"/>
                                <i class="fa fa-trash" aria-hidden="true" style="padding-right: 15px;"></i>
                              </div>
                            </div>
                            </div>
                            </div>
                          </div>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>