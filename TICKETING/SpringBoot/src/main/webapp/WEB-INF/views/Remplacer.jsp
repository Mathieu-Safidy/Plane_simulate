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
  <%@ page import="mg.working.model.composant.*" %>
  <%@ page import="mg.working.model.service.*" %>
  <%@ page import="java.util.List" %>

 <!-- End Sidebar-->

  <main id="main" class="main">


   <main id="main" class="main">
        <section class="section">
            <div class="container">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h1 class="card-title text-primary">Liste des Composants en Stock</h1>
                        
                        <!-- Afficher les informations générales -->
                        <%
                            String libelleComposant = (String) request.getAttribute("libelleComposant");
                            ModelComposant modelComposant = (ModelComposant) request.getAttribute("modelComposant");
                            List<Composant> lsComposant = (List<Composant>) request.getAttribute("lsComposant");
                            List<ServiceClient> lsServiceClient = (List<ServiceClient>) request.getAttribute("lsServiceClient");
                        %>
                        <p><strong>Composant :</strong> <%= libelleComposant %></p>
                        <p><strong>Modele Composant :</strong> <%= modelComposant.getLibelle() %></p>
                        
                        <!-- Liste des composants -->
                        <h3 class="mt-4">Composants Disponibles</h3>
                        <table class="table table-bordered table-hover">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Nom</th>
                                    <th>Modele</th>
                                    <th>Type</th>
                                    <th>Stock Restant</th>
                                    <th>Prix Achat</th>
                                    <th>Prix Vente</th>
                                    <th>Type service</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if (lsComposant != null) {
                                        for (Composant composant : lsComposant) {
                                %>
                                <tr>
                                    <td><%= composant.getIdComposant() %></td>
                                    <td><%= composant.getNom() %></td>
                                    <td><%= composant.getModel() %></td>
                                    <td><%= composant.getTypeLibelle() %></td>
                                    <td><%= composant.getStockRestant() %></td>
                                    <td><%= composant.getPrixAchat() %></td>
                                    <td><%= composant.getPrixVente() %></td>
                                    <td> <select class="form-select" aria-label="Default select example" name="idServiceClient">
                                               <%
                                              
                                               for ( ServiceClient serviceClient : lsServiceClient )
                                                { %> 
                                                  <option value="<%=serviceClient.getIdService() %>"><%=serviceClient.getLibelle()%></option>
                                                <% } %>  
                                              </select>
                                              </td> 
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>

                   
                     
                    </div>
                </div>
            </div>
        </section>
    </main>

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