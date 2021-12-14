<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link  rel="stylesheet" href="/assets/css/templatemo-hexashop.css">
<link  rel="stylesheet" href="/assets/css/font-awesome.css">
<script src="https://use.fontawesome.com/518bcdd6c6.js"></script>

<style>
    .form-border{
        border-top: none;
        border-left: none;
        border-right: none;
        width: 300px;
    }
    .form-border:focus{
        border-color: white;
    }
    .form-submit{
        padding-top: 15px;
        padding-left: 40px;
        padding-bottom: 15px;
        padding-right: 40px;
        border-radius: 5px;
        font-style: italic;
    }
    .box input[type="email"],.box input[type="password"]{

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

        .box input[type="email"]:focus,.box input[type="password"]:focus{
        width:500px;
        border-color:#2ecc71;

        }
        .container {
            padding: 10px;
            box-shadow: 5px 10px 18px #888888;
        }
</style>
	<section class="sign-in" style="margin-top: 70; text-align: center;">
            <div class="container" style="background-color:#e4ebf7; padding: 90px; border-radius: 20px;">
                <div class="row signin-content">
                    <div class="signin-image col-lg-4">
                        <figure><img src="/assets/images/signin.jpg" alt="sign up image"></figure>
                    </div>

                    <div class="signin-form col-lg-8">
                        <h2 class="form-title">Login<i class="fa fa-sign-in" aria-hidden="true" style="padding-left: 5px;"></i></h2>
                        <form method="POST" class="register-form box" id="login-form">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <div class="form-group  ${error != null ? 'has-error' : ''}">
                            <div class="form-group" style="margin-top: 30px;">
                                <input type="email" class="form-border" name="email" id="your_name" placeholder="Email id"/>
                            </div>
                            <div class="form-group" style="margin-top: 30px;">
                                <input type="password" class="form-border" name="password" id="your_pass" placeholder="Password"/>
                            </div>
                            <div>
                                <p class="mb-3 text-danger">${error}</p>
                            </div>
                            <div class="form-button" style="margin-top: 30px;">
                                <input type="submit" id="signin" class="form-submit btn-success" value="Log me in"/>
                            </div>
                        </div>
                        </form>
                        <a href="/signup" class="signup-image-link" style="padding-top: 50px;">Create an account</a>
                    </div>
                </div>
            </div>
        </section>
	
