<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link  rel="stylesheet" href="/assets/css/templatemo-hexashop.css">
<script src="https://use.fontawesome.com/518bcdd6c6.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <div class="container">
            <div class="row">
                <div class="col-lg-12" style="padding-top: 20px; padding-bottom: 20px;">
                    <h2 style="text-align: center;">Products Order by the User</h2>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <c:forEach items="${indorderdisplay}" var="indorderdisplay">
                    <div class="col-lg-4">
                        <div class="card" style="width: 18rem;">
                            <div style="text-align: center;">
                                <img src="data:image/jpg;base64,${indorderdisplay.image}" style="width: 150px; height: 150px;" class="card-img-top" alt="...">
                            </div>
                            <div class="card-body">
                                <h3 class="card-title"><c:out value="${indorderdisplay.productname}"/></h3>
                                <div style="padding-top: 10px; padding-bottom: 10px;">
                                    <h6 class="card-text"><i class="fa fa-product-hunt" aria-hidden="true" style="padding-right: 5px;"></i>Product ID : <c:out value="${indorderdisplay.productid}"/></h6>
                                    <h6 class="card-text"><i class="fa fa-money" aria-hidden="true" style="padding-right: 5px;"></i>Price : <c:out value="${indorderdisplay.price}"/></h6>
                                    <h6 class="card-text"><i class="fa fa-list-ol" aria-hidden="true" style="padding-right: 5px;"></i>Quantity : <c:out value="${indorderdisplay.quantity}"/></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>