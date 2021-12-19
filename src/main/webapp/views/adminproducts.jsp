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
 <html>
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
           .error {
        color: #FF0000;
           }
   </style>
     <h3 style="text-align: center; padding-top: 20px;">Add A Products To Your Store</h3>
     <div class="container">
         <div class="row" style="margin-left: 100px;">
             <div class="col-lg-3" style="margin-top: 130px;">
                <i class="fa fa-database" style="font-size: 250px; padding-left: 20px;" aria-hidden="true"></i>
             </div>
             <div class="col-lg-9">
                <form:form action="successinsertion" method="get" enctype="multipart/form-data" modelAttribute = "productsmodel" style="width: 600px;">
                    <label for="exampleInputName" class="form-label">Product ID</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa fa-product-hunt" aria-hidden="true"></i></span>
                        <form:input type="text" class="form-control" path="productid"/>
                    </div>
                    <form:errors path="productid" cssClass="error"/>
                    <label for="exampleInputName" class="form-label">Product Name</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa fa-product-hunt" aria-hidden="true"></i></span>
                        <form:input type="text" class="form-control" path="productname"/>
                    </div>
                    <form:errors path="productname" cssClass="error"/>
                    <label for="exampleInputName" class="form-label">Product Price</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa fa-money" aria-hidden="true"></i></span>
                        <form:input type="text" class="form-control" path="price"/>
                    </div>
                    <form:errors path="price" cssClass="error"/>
                    <label for="exampleInputName" class="form-label">Quantity Available</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa fa-list-ol" aria-hidden="true"></i></span>
                        <form:input type="text" class="form-control" path="quantity_aval"/>
                    </div>
                    <form:errors path="quantity_aval" cssClass="error"/>
                    <label for="exampleInputName" class="form-label">Product Image</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text"><i class="fa fa-upload" aria-hidden="true"></i></span>
                        <form:input type="file" class="form-control" id="myFile" path="image" accept = "image/jpg"/>
                    </div>
                    <form:errors path="image" cssClass="error"/>
                    <div class="icon-input-update">
                        <i class="fa fa-database" aria-hidden="true"></i>
                        <input type="submit" value="Insert Product" class="btn btn-success"/>
                    </div>
                </form:form>
             </div>
         </div>
     </div>
</html>