<link  rel="stylesheet" href="/assets/css/templatemo-hexashop.css">
<script src="https://use.fontawesome.com/518bcdd6c6.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="header-area sticky-top">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav class="main-nav">
                        <a href="/home">
                            <img src="/assets/images/MMM.png" style="width:80px; height: 80px;"/>
                        </a>
                        <ul class="nav">
                            <sec:authentication var="user" property="principal" />
                            <li class="scroll-to-section"><a href="/home" class="active"><i class="fa fa-home" aria-hidden="true" style="padding-right: 5px;"></i>Home</a></li>
                            <li><a href="/products"><i class="fa fa-product-hunt" aria-hidden="true" style="padding-right: 5px;"></i>Products</a></li>
                            <li><a href="/cart"><i class="fa fa-shopping-cart" aria-hidden="true" style="padding-right: 5px;"></i>Cart</a></li>
                            <li><a href="/about"><i class="fa fa-id-card-o" aria-hidden="true" style="padding-right: 5px;"></i>About Us</a></li>
                            <li><a href="/contact"><i class="fa fa-phone-square" aria-hidden="true" style="padding-right: 5px;"></i>Contact Us</a></li>
                            <sec:authorize access="isAuthenticated()">
                            <li><a href="/logout" 
                                style="
                                    padding-bottom: 15px;
                                    padding-left: 25px;
                                    padding-right: 25px;
                                    background-color: #7a6968;
                                    border-radius: 20px;">
                                    <i class="fa fa-sign-in" aria-hidden="true" style="padding-right: 5px;"></i>Logout!</a></li>
                            </sec:authorize>
                            <sec:authorize access="!isAuthenticated()">
                            <li><a href="/login" 
                                style="
                                    padding-bottom: 15px;
                                    padding-left: 25px;
                                    padding-right: 25px;
                                    background-color: #7a6968;
                                    border-radius: 20px;">
                                    <i class="fa fa-sign-out" aria-hidden="true" style="padding-right: 5px;"></i>Login!</a></li>
                            </sec:authorize>
                        </ul>        
                        <a class='menu-trigger'>
                            <span>Menu</span>
                        </a>
                    </nav>
                </div>
            </div>
        </div>
    </header>

