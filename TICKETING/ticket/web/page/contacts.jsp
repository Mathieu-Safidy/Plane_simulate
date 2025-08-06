<%@page import="entity.event.VDetailVolsDispo" %>
<%@page import="java.util.List,java.util.ArrayList" %>
<%
    List<VDetailVolsDispo> vols = (List<VDetailVolsDispo>)request.getAttribute("vols");
    String idvol = (String)request.getAttribute("idvols");
    String message = "";
    if(request.getParameter("message") != null) {
        message = request.getParameter("message");
    }
%>
<!DOCTYPE html>
<html class="wide wow-animation" lang="en">
  <head>
    <!-- Site Title-->
    <title>Contacts</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport"
      content="width=device-width, height=device-height, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <link rel="icon"
      href="<%=request.getContextPath() %>/assets/images/favicon.ico"
      type="image/x-icon">
    <!-- Stylesheets -->
    <link rel="stylesheet" type="text/css"
      href="//fonts.googleapis.com/css?family=Oswald:200,400%7CLato:300,400,300italic,700%7CMontserrat:900">
    <link rel="stylesheet"
      href="<%=request.getContextPath() %>/assets/css/bootstrap.css">
    <link rel="stylesheet"
      href="<%=request.getContextPath() %>/assets/css/style.css">
    <link rel="stylesheet"
      href="<%=request.getContextPath() %>/assets/css/fonts.css">
    <!--[if lt IE 10]>
<div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;"><a href="http://windows.microsoft.com/en-US/internet-explorer/"><img src="<%=request.getContextPath() %>/assets/images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a></div>
<script src="<%=request.getContextPath() %>/assets/js/html5shiv.min.js"> </script>
<![endif]-->
  </head>
  <body>
    <!-- Page preloader-->
    <div class="page-loader">
      <div class="page-loader-body">
        <div class="preloader-wrapper big active">
          <div class="spinner-layer spinner-blue">
            <div class="circle-clipper left">
              <div class="circle"> </div>
            </div>
            <div class="gap-patch">
              <div class="circle"> </div>
            </div>
            <div class="circle-clipper right">
              <div class="circle"></div>
            </div>
          </div>
          <div class="spinner-layer spinner-red">
            <div class="circle-clipper left">
              <div class="circle"></div>
            </div>
            <div class="gap-patch">
              <div class="circle"> </div>
            </div>
            <div class="circle-clipper right">
              <div class="circle"></div>
            </div>
          </div>
          <div class="spinner-layer spinner-yellow">
            <div class="circle-clipper left">
              <div class="circle"></div>
            </div>
            <div class="gap-patch">
              <div class="circle"></div>
            </div>
            <div class="circle-clipper right">
              <div class="circle"> </div>
            </div>
          </div>
          <div class="spinner-layer spinner-green">
            <div class="circle-clipper left">
              <div class="circle"></div>
            </div>
            <div class="gap-patch">
              <div class="circle"></div>
            </div>
            <div class="circle-clipper right">
              <div class="circle"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Page-->
    <div class="page"><a
        class="section section-banner text-center d-none d-xl-block"
        href="https://www.templatemonster.com/intense-multipurpose-html-template.html"
        style="background-image: url(<%=request.getContextPath() %>/assets/images/banner/background-04-1920x60.jpg); background-image: -webkit-image-set( url(<%=request.getContextPath() %>/assets/images/banner/background-04-1920x60.jpg) 1x, url(<%=request.getContextPath() %>/assets/images/banner/background-04-3840x120.jpg) 2x )"><img
          src="<%=request.getContextPath() %>/assets/images/banner/foreground-04-1600x60.png"
          srcset="<%=request.getContextPath() %>/assets/images/banner/foreground-04-1600x60.png 1x, <%=request.getContextPath() %>/assets/images/banner/foreground-04-3200x120.png 2x"
          alt width="1600" height="310"></a>
      <!-- Page Header-->
      <header
        class="section page-header breadcrumbs-custom-wrap bg-gradient bg-secondary-2 novi-background bg-cover">
        <!-- RD Navbar-->
        <div class="rd-navbar-wrap rd-navbar-default">
          <nav class="rd-navbar" data-layout="rd-navbar-fixed"
            data-sm-layout="rd-navbar-fixed"
            data-md-layout="rd-navbar-fixed"
            data-md-device-layout="rd-navbar-fixed"
            data-lg-layout="rd-navbar-fullwidth"
            data-xl-layout="rd-navbar-static"
            data-lg-device-layout="rd-navbar-fixed"
            data-xl-device-layout="rd-navbar-static"
            data-md-stick-up-offset="2px" data-lg-stick-up-offset="2px"
            data-stick-up="true" data-sm-stick-up="true"
            data-md-stick-up="true" data-lg-stick-up="true"
            data-xl-stick-up="true">
            <div class="rd-navbar-inner">
              <!-- RD Navbar Panel-->
              <div class="rd-navbar-panel">
                <!-- RD Navbar Toggle-->
                <button class="rd-navbar-toggle"
                  data-rd-navbar-toggle=".rd-navbar-nav-wrap"><span></span></button>
                <!-- RD Navbar Brand-->
                <div class="rd-navbar-brand"><a class="brand-name"
                    href="index.html"><img class="logo-default"
                      src="<%=request.getContextPath() %>/assets/images/logo-default-208x46.png"
                      alt width="208" height="46" /><img
                      class="logo-inverse"
                      src="<%=request.getContextPath() %>/assets/images/logo-inverse-208x46.png"
                      alt width="208" height="46" /></a></div>
              </div>
              <div class="rd-navbar-aside-right">
                <div class="rd-navbar-nav-wrap">
                  <!-- RD Navbar Nav-->
                  <ul class="rd-navbar-nav">
                    <li><a href="index.html">Home</a>
                    </li>
                    <li><a href="about-us.html">About Us</a>
                    </li>
                    <li class="active"><a href="contacts.html">Contacts</a>
                    </li>
                    <li><a href="typography.html">Typography</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
        </div>
        <!-- Breadcrumbs-->
        <section class="breadcrumbs-custom"
          style="background: url(&quot;<%=request.getContextPath() %>/assets/images/breadcrumbs-bg.jpg&quot;); background-size: cover;">
          <div class="container">
            <p class="breadcrumbs-custom-subtitle">Get in Touch with Us</p>
            <p class="heading-1 breadcrumbs-custom-title">Contacts</p>
            <ul class="breadcrumbs-custom-path">
              <li><a href="index">Home</a></li>
              <li class="active">Contacts</li>
            </ul>
          </div>
        </section>

      </header>

      <!-- Contact us-->
      <section
        class="section section-wrap bg-gray-lighter novi-background bg-cover">
        <div class="section-wrap-inner">
          <div class="container container-bigger">
            <div class="row row-fix row-50">
              <div class="col-lg-8 offset-lg-2 col-xl-7 offset-xl-2">
                <div class="section-wrap-content section-lg">
                  <h3>Contact us</h3>
                  <hr class="divider divider-left divider-secondary">
                  <p class="big">You can contact us any way that is
                    convenient for you. We are available 24/7 via fax or
                    email. You can also use a quick contact form below or
                    visit our office personally.
                    
                  </p>
                  <% if (!message.equals(" ")) { %>
                  <p class="big alert alert-danger">
                    <%=message %>
                  </p>
                  <% } %>
                  <!-- RD Mailform-->
                  <form class="rd-mailform"
                    data-form-output="form-output-global"
                    data-form-type="contact" method="post"
                    action="reserver">
                    <div class="row row-fix row-20">

                      <div class="col-sm-12">
                        <label class="form-label-outside">To</label>
                        <div class="form-wrap form-wrap-inline">
                          <select class="form-input select-filter"
                            data-placeholder="All"
                            data-minimum-results-for-search="Infinity"
                            name="filtre_idType">
                            <% for(VDetailVolsDispo vol : vols) { %>
                            <option
                              value="<%=vol.getSiegeDetail().getIdType() %>"><%=vol.getSiegeDetail().getNom()
                              %></option>
                            <% } %>
                          </select>
                        </div>
                      </div>

                      <div class="col-lg-6">
                        <label class="form-label-outside">Nombre de
                          billet</label>
                        <div class="form-wrap form-wrap-modern">
                          <input class="form-input input-append"
                            id="form-element-stepper" type="number"
                            min="0" value="1" name="filtre_place">
                        </div>
                      </div>

                      <input type="hidden" name="filtre_idVols" value="<%=idvol %>" >
                      <div class="col-lg-6">
                        <div class="form-wrap form-wrap-modern"></div>
                        <input class="form-input" id="dateForm"
                           type="datetime-local"
                            name="filtre_dateReservation">
                          
                      </div>

                      <div class="col-sm-12 offset-custom-1">
                        <div class="form-button">
                          <button
                            class="button button-secondary button-nina"
                            type="submit">send message</button>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- Contact info-->
      <section class="section section-lg bg-default text-center">
        <div class="container container-wide">
          <div class="row row-fix row-50 row-custom-bordered">
            <div class="col-sm-6 col-lg-3">
              <!-- Box minimal-->
              <article class="box-simple">
                <div
                  class="box-simple-icon novi-icon mdi mdi-map-marker"></div>
                <h6>Address</h6>
                <div class="box-simple-text"><a href="#">2130 Fulton Street,
                    Chicago, IL <br> 94117-1080 USA</a></div>
              </article>
            </div>
            <div class="col-sm-6 col-lg-3">
              <!-- Box simple-->
              <article class="box-simple">
                <div class="box-simple-icon novi-icon mdi mdi-phone"></div>
                <h6>phones</h6>
                <div class="box-simple-text">
                  <ul class="list-comma list-0">
                    <li><a href="tel:#">1-800-6543-765</a></li>
                    <li><a href="tel:#">1-800-3434-876</a></li>
                  </ul>
                </div>
              </article>
            </div>
            <div class="col-sm-6 col-lg-3">
              <!-- Box simple-->
              <article class="box-simple">
                <div
                  class="box-simple-icon novi-icon mdi mdi-email-open"></div>
                <h6>e-mail</h6>
                <div class="box-simple-text">
                  <ul class="list-comma list-0">
                    <li><a href="mailto:#">mail@demolink.org</a></li>
                  </ul>
                </div>
              </article>
            </div>
            <div class="col-sm-6 col-lg-3">
              <!-- Box simple-->
              <article class="box-simple">
                <div
                  class="box-simple-icon novi-icon mdi mdi-calendar-clock"></div>
                <h6>opening hours</h6>
                <div class="box-simple-text">
                  <ul class="list-0">
                    <li>Mon–Fri: 9:00 am–6:00 pm</li>
                    <li>Sat–Sun: 11:00 am–4:00 pm</li>
                  </ul>
                </div>
              </article>
            </div>
          </div>
        </div>
      </section>
      <a class="section section-banner"
        href="https://www.templatemonster.com/intense-multipurpose-html-template.html"
        style="background-image: url(<%=request.getContextPath() %>/assets/images/banner/background-03-1920x310.jpg); background-image: -webkit-image-set( url(<%=request.getContextPath() %>/assets/images/banner/background-03-1920x310.jpg) 1x, url(<%=request.getContextPath() %>/assets/images/banner/background-03-3840x620.jpg) 2x )"><img
          src="<%=request.getContextPath() %>/assets/images/banner/foreground-03-1600x310.png"
          srcset="<%=request.getContextPath() %>/assets/images/banner/foreground-03-1600x310.png 1x, <%=request.getContextPath() %>/assets/images/banner/foreground-03-3200x620.png 2x"
          alt width="1600" height="310"></a>
      <!-- Page Footer-->
      <!-- Footer Minimal-->
      <footer
        class="section page-footer page-footer-minimal novi-background bg-cover text-center bg-gray-darker">
        <div class="container container-wide">
          <div
            class="row row-fix justify-content-sm-center align-items-md-center row-30">
            <div class="col-md-10 col-lg-7 col-xl-4 text-xl-left"><a
                href="index.html"><img class="inverse-logo"
                  src="<%=request.getContextPath() %>/assets/images/logo-inverse-208x46.png"
                  alt width="208" height="46" /></a></div>
            <div class="col-md-10 col-lg-7 col-xl-4">
              <p class="right">&#169;&nbsp;<span
                  class="copyright-year"></span> All Rights Reserved.
                Design&nbsp;by&nbsp;<a
                  href="https://www.templatemonster.com">TemplateMonster</a></p>
            </div>
            <div class="col-md-10 col-lg-7 col-xl-4 text-xl-right">
              <ul class="group-xs group-middle">
                <li><a
                    class="icon novi-icon icon-md-middle icon-circle icon-secondary-5-filled mdi mdi-facebook"
                    href="#"></a></li>
                <li><a
                    class="icon novi-icon icon-md-middle icon-circle icon-secondary-5-filled mdi mdi-twitter"
                    href="#"></a></li>
                <li><a
                    class="icon novi-icon icon-md-middle icon-circle icon-secondary-5-filled mdi mdi-instagram"
                    href="#"></a></li>
                <li><a
                    class="icon novi-icon icon-md-middle icon-circle icon-secondary-5-filled mdi mdi-google"
                    href="#"></a></li>
                <li><a
                    class="icon novi-icon icon-md-middle icon-circle icon-secondary-5-filled mdi mdi-linkedin"
                    href="#"></a></li>
              </ul>
            </div>
          </div>
        </div>
      </footer>
    </div>
    <!-- Global Mailform Output-->
    <div class="snackbars" id="form-output-global"> </div>
    <!-- Javascript-->
    <script
      src="<%=request.getContextPath() %>/assets/js/core.min.js"></script>
    <script
      src="<%=request.getContextPath() %>/assets/js/script.js"></script>

      <script>
        $(document).ready(function() {
          $('form').off('submit reset');  
        });
      </script>
  </body>
</html>