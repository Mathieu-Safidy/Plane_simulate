<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Charts / ApexCharts - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Aug 30 2023 with Bootstrap v5.3.1
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
<%@ include file="header.jsp" %>
  <!-- End Header -->

  <!-- ======= Sidebar ======= -->
  <%@ include file="sidebar.jsp" %>
 <!-- End Sidebar-->

  <main id="main" class="main">


    <section class="section">
    <form action="/ValiderService" method="post" > 
        <div class="card">
            <div class="card-body">
              <div class="row"> 
                <div class="col-md-3">
                <h1 class="card-title">Liste des services a valider</h1>
                </div> 
                <div class="col-md-6" style="margin-top:10px; ">
                  <button type="submit" id="reparerButton" class="btn btn-primary">valider</button>
                </div>
              </div> 
                                                      <h5>descrition</h5>
                                                      <input class="form-control" type="text" id="prix" name="descReparationMere" step="0.01" required></td> 
                                                      <h5>date debut service</h5> 
                                                      <input class="form-control" type="date" id="prix" name="dateDebutServiceMere" step="0.01" ></td> 
                                                     
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>idPanier</th>
                            <th>composant</th>
                            <th>client</th>
                            <th>technicien</th>
                            <th>type composant</th>
                            <%-- <th>type service</th> --%>
                            <th>date fin</th>
                            <th>prix de reparation</th>
                        </tr>
                    </thead>
                    <tbody>

                    <%@ page import="mg.working.model.composant.*" %>
                    <%@ page import="mg.working.model.service.*" %>
                    <%@ page import="java.util.List" %>

                        <% 
                            List<Panier> lsPanier = (List<Panier>) request.getAttribute("panier");
                            if ( lsPanier != null) {
                                for (Panier panier : lsPanier) { 
                                    %>
                                    <tr>
                                        <td><%= panier.getIdPanier() %></td>
                                        <td><%= panier.getLibelle() %></td>
                                        <td><%= panier.getClient() %></td>
                                        <td><%= panier.getTechnicien() %></td>
                                        <td><%= panier.getTypeComposant() %></td>

                                        <%-- <td>
                                            <select class="form-select" aria-label="Default select example" name="idServiceClient">
                                               <%
                                               List<ServiceClient> lsServiceClient = (List<ServiceClient>) request.getAttribute("lsServiceClient") ;    
                                               for ( ServiceClient serviceClient : lsServiceClient )
                                                { %> 
                                                  <option value="<%=serviceClient.getIdService() %>"><%=serviceClient.getLibelle()%></option>
                                                <% } %>  
                                              </select>
                                        </td> --%>

                                        <td><input class="form-control" type="date" name="dateDebutServiceFille"> </td>   
                                        
                                   
                                        <td>
                                          <input type="number" name="prix_reparation[]" required>
                                        </td>
                                        <td> 
                                        <input type="hidden" name="idPaniers[]" value="<%= panier.getIdPanier() %>">
                                        <input type="hidden" name="idClient[]" value="<%= panier.getIdClient() %>">
                                        <input type="hidden" name="idTechnicien[]" value="<%= panier.getIdTechnicien() %>">
                                        <input type="hidden" name="idComposant[]" value="<%= panier.getIdComposant() %>">
                                        <input type="hidden" name="idTypeComposant[]" value="<%= panier.getIdTypeComposant() %>">
                                        <input type="hidden" name="idOrdinateur[]" value="<%= panier.getIdOrdianteur() %>">

                                        </td> 
                                   

        </form> 
                                        <td> 
                                        <form action="/deletePanier" method="post" > 
                                          <input type="hidden" name="idPanier" value="<%= panier.getIdPanier() %>">
                                           <button type="submit" id="reparerButton" class="btn btn-danger">suprimer reparation</button>
                                        </form> 
                                        </td> 
   
                                    </tr>
                                    <% 
                                  } } %>
                    </tbody>
                </table>
            
            </div>

        </div>

    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <%@ include file="footer.jsp" %>
<!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.umd.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

</body>

</html>