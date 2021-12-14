<link  rel="stylesheet" href="/assets/css/templatemo-hexashop.css">
<script src="https://use.fontawesome.com/518bcdd6c6.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
    p{
        font-size: 20px;
        padding: 10px;
    }
</style>
<div class="container">
    <div class="mt-5 text-center payment-failed">
        <div class="payment-failed-header" style="padding-top: 50px; padding-bottom: 30px;">
            <h1><i class="far text-warning fa-frown-open"></i></h1>
            <h1 class="text-info">Payment Failed</h1>
        </div>
        <hr>
        <div class="payment-failed-body" style="padding-top: 30px; padding-bottom: 30px;">
            <p>Your payment was not successful. Please try again.</p>
            <p>If you continue to have problems, please <a href="/contact">contact us</a>.</p>
        </div>
        <hr>
        <div class=" payment-failed-footer" style="padding-top: 30px;">
            <a class="btn-info btn" href="/cart">Back to Cart</a>
        </div>
    </div>
</div>