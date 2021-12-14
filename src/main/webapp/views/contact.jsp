<%@include file="header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
  .error {
        color: #FF0000;
    }
</style>
<div class="page-heading about-page-heading" id="top" style="margin-top: 80px;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="inner-content">
                        <h2 style="color: white;">Contact Us</h2>
                   
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <div class="contact-us">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div id="map">
                      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d90186.37207676383!2d-80.13495239500924!3d25.9317678710111!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x88d9ad1877e4a82d%3A0xa891714787d1fb5e!2sPier%20Park!5e1!3m2!1sen!2sth!4v1637512439384!5m2!1sen!2sth" width="100%" height="400px" frameborder="0" style="border:0" allowfullscreen></iframe> 
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="section-heading">
                        <h2>Say Hello. Don't Be Shy!</h2>
                        <span>Details to details is what makes our Shop different from the others.</span>
                    </div>
                    <form:form method="POST" id = "contact" action="addmessage" modelAttribute="messagemodel">
                        <div class="row">
                          <div class="col-lg-6">
                              <form:input type="text" path="name" id="name" placeholder="Your name"/>
                                <form:errors path="name" cssClass="error"/>
                          </div>
                          <div class="col-lg-6">
                              <form:input type="text" path="email" id="email" placeholder="Your email"/>
                                <form:errors path="email" cssClass="error"/>
                          </div>
                          <div class="col-lg-12">
                              <form:textarea path="message" rows="6" id="msg" placeholder="Your message"></form:textarea>
                              <form:errors path="message" cssClass="error"/>
                          </div>
                          <div class="col-lg-12">
                              <button type="submit" id="form-submit" class="main-dark-button" ><i class="fa fa-paper-plane"></i></button>
                          </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <div class="subscribe">
        <div class="container">
                <div class="col-lg-12" style="text-align:center">
                    <div class="row">
                        <div class="col-6">
                            <ul>
                                <li style="font-size: 20px;">Store Location:<br><i class="fa fa-shopping-basket fa-2x" aria-hidden="true" style="padding-right: 5px;"></i><span style="padding-left: 5px; font-size: 20px;">2 , Adhi Selva Ganapathy Street, T-Nagar, Chennai</span></li>
                                <li style="font-size: 20px;">Phone:<br><i class="fa fa-mobile fa-2x" aria-hidden="true" style="padding-right: 5px;"></i><span style="padding-left: 5px; font-size: 20px;">0427-2240987</span></li>
                                <li style="font-size: 20px;">Office Location:<br><i class="fa fa-map-marker fa-2x" aria-hidden="true" style="padding-right: 5px;"></i><span style="padding-left: 5px; font-size: 20px;">Chennai</span></li>
                            </ul>
                        </div>
                        <div class="col-6">
                            <ul>
                                <li style="font-size: 20px;">Work Hours:<br><i class="fa fa-hourglass fa-2x" aria-hidden="true" style="padding-right: 5px;"></i><span style="padding-left: 5px; font-size: 20px;">Mon - Sat : 9am - 7pm<br>Sun : Closed</span></li>
                                <li style="font-size: 20px;">Email:<br><i class="fa fa-envelope fa-2x" aria-hidden="true" style="padding-right: 5px;"></i><span style="padding-left: 5px; font-size: 20px;">shop@yahoo.in</span></li>
                                <li style="font-size: 20px; padding-bottom: 10px;">Social Media:<br>
                                  <div style="padding-left: 5px; padding-top: 5px;">
                                    <a href="#"><i class="fa fa-facebook fa-2x" aria-hidden="true" style="color: black;"></i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#"><i class="fa fa-instagram fa-2x" aria-hidden="true" style="color: black;"></i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#"><i class="fa fa-linkedin fa-2x" aria-hidden="true" style="color: black;"></i></a>&nbsp;&nbsp;&nbsp;
                                    <a href="#"><i class="fa fa-telegram fa-2x" aria-hidden="true" style="color: black;"></i></a>
                                  </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@include file="footer.jsp" %>