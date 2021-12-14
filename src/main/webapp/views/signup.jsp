<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link  rel="stylesheet" href="/assets/css/templatemo-hexashop.css">
<link  rel="stylesheet" href="/assets/css/font-awesome.css">
<script src="https://use.fontawesome.com/518bcdd6c6.js"></script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .error {
        color: #FF0000;
    }
    .form-border{
        border-top: none;
        border-left: none;
        border-right: none;
        width: 300px;
    }
    .form-border:focus{
        border-top: none;
        border-left: none;
        border-right: none;
    }
    .form-submit{
        padding-top: 15px;
        padding-left: 40px;
        padding-bottom: 15px;
        padding-right: 40px;
        border-radius: 5px;
        font-style: italic;
    }
    .box input[type="text"],.box input[type="password"],.box input[type="email"]{

        border:0;
        background:none;
        display:block;
        margin:30px auto;
        text-align:center;
        border:5px solid #3498db;
        padding:14px 10px;
        width:350px;
        outline:none;
        color:gray;
        border-radius:24px;
        transition:0.25s;

        }

        .box input[type="text"]:focus,.box input[type="password"]:focus,.box input[type="email"]:focus{
        width:500px;
        border-color:#2ecc71;

        }
        .container {
            padding: 10px;
            box-shadow: 5px 10px 8px #888888;
        }
</style>
	<section class="signup" style="margin-top: 50; text-align: center;">
            <div class="container" style="background-color:#e4ebf7; padding: 90px; border-radius: 20px; margin-top: 20px;">
                <div class="signup-content row">
                    <div class="signup-form col-lg-8" style="text-align: center;">
                        <h2 class="form-title">Sign Up<i class="fa fa-sign-out" aria-hidden="true" style="padding-left: 5px;"></i></h2>
                        <form:form method="POST" class="register-form box" id="register-form" action="adduser" modelAttribute="userdetails">
                             <div class="form-group" style="margin-top: 30px;">
                                <form:input type="text" class="form-border" path="username" id="name" placeholder="Your Name"/>
                                <form:errors path="username" cssClass="error"/>
                            </div>
                            <div class="form-group" style="margin-top: 30px;">
                                <form:input type="email" class="form-border" path="email" id="email" placeholder="Your Email"/>
                                <form:errors path="email" cssClass="error"/>
                            </div>
                            <div class="form-group" style="margin-top: 30px;">
                                <form:input type="password" class="form-border" path="password" id="pass" placeholder="Password"/>
                                <form:errors path="password" cssClass="error"/>
                            </div>
                            <div class="form-group" style="margin-top: 30px;">
                                <form:input type="password" class="form-border" path="confirmpassword" id="pass" placeholder="Confirm Password"/>
                                <form:errors path="confirmpassword" cssClass="error"/>
                            </div>
                            <div class="form-group form-button" style="margin-top: 30px;">
                                <input type="submit" id="signup" class="form-submit btn-success" />
                            </div>
                        </form:form>
                    </div>
                    <div class="signup-image col-lg-4" style="margin-top: 60px;">
                        <figure><img src="/assets/images/signup.jpg" alt="sign up image"></figure>
                    </div>
                </div>
            </div>
        </section>
	
