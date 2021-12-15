<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link  rel="stylesheet" href="/assets/css/templatemo-hexashop.css">
<script src="https://use.fontawesome.com/518bcdd6c6.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<style>
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
        label{
            padding-top:15px;
        }
</style>
<body>
    <h3 style="text-align: center; padding-top: 30px;">Update Your Product Details</h3>
    <c:forEach items="${indlist}" var="indlist">
        <div style="margin-left: 500px; padding-top: 80px;">
            <form action="${pageContext.request.contextPath }/updateform/${indlist.productid}" method="get" style="width: 500px;">
                <input type="hidden" name="productid" value="${indlist.productid}"/>
                <label for="exampleInputName" class="form-label">Product Name</label>
                <div class="input-group mb-3">
                    <span class="input-group-text"><i class="fa fa-product-hunt" aria-hidden="true"></i></span>
                    <input type="text" name="productname" class="form-control" value = "${indlist.productname}"/>
                </div>
                <label for="exampleInputquantity" class="form-label">Quantity Available</label>
                <div class="input-group mb-3">
                    <span class="input-group-text"><i class="fa fa-list-ol" aria-hidden="true"></i></span>
                    <input type="text" class="form-control" name="quantityavail" value="${indlist.quantity_aval}"/>
                </div>
                <label for="exampleInputprice" class="form-label">Price</label>
                <div class="input-group mb-3">
                    <span class="input-group-text"><i class="fa fa-money" aria-hidden="true"></i></span>
                    <input type="text" name="price" class="form-control" value="${indlist.price}"/>
                </div>
                <label for="exampleInputEmail1" class="form-label"><i class="fa fa-picture-o" aria-hidden="true" style="padding-right: 10px;"></i>Image</label>
                <div class="input-group mb-3">
                    <span class="input-group-text"><i class="fa fa-upload" aria-hidden="true"></i></span>
                    <input type="file" class="form-control" name="image"/>
                </div>
                <div class="icon-input-update">
                    <input type="submit" name="Update" value="Update" class="btn btn-info">
                    <i class="fa fa-pencil" aria-hidden="true" style="padding-right: 15px;"></i>
                </div>
            </form>
        </div>
    </c:forEach>
</body>
