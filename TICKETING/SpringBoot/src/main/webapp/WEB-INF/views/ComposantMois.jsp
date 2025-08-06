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
    <section class="section">
        <div class="container">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h1 class="card-title text-primary">Liste des Composants Recommandés</h1>

                    <!-- Formulaire de filtre -->
                     <form action="/CompRecommander" method="get" class="row g-3">
                        <div class="col-md-6">
                            <label for="idMois" class="form-label">Mois</label>
                            <select id="idMois" name="idMois" class="form-select">
                                <option value="" selected>Choisir un mois</option>
                                <% 
                                    List<Mois> lsMois = (List<Mois>) request.getAttribute("lsMois");
                                    if (lsMois != null) {
                                        for (Mois mois : lsMois) { 
                                %>
                                    <option value="<%= mois.getIdMois() %>"><%= mois.getLibelle() %></option>
                                <% 
                                        } 
                                    } 
                                %>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="annee" class="form-label">Année</label>
                            <input type="number" id="annee" name="annee" class="form-control" placeholder="Entrez l'année">
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary">Filtrer</button>
                        </div>
                    </form> 

                    <!-- Liste des composants recommandés -->
                    <h3 class="mt-4">Historique 2024</h3>
                    <table class="table table-bordered table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Composant</th>
                                <th>Modèle</th>
                                <th>Type</th>
                                <th>Prix Achat</th>
                                <th>Prix Vente</th>
                                <th>Année</th>
                                <th>Mois</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                List<ComposantRecommender> composants = (List<ComposantRecommender>) request.getAttribute("lsComposant2024");
                                if (composants != null) {
                                    for (ComposantRecommender composant : composants) { 
                            %>
                                <tr>
                                    <td><%= composant.getIdLiaison() %></td>
                                    <td><%= composant.getComposant() %></td>
                                    <td><%= composant.getIdModel() %></td>
                                    <td><%= composant.getIdType() %></td>
                                    <td><%= composant.getPrixAchat() %></td>
                                    <td><%= composant.getPrixVente() %></td>
                                    <td><%= composant.getAnnee() %></td>
                                    <td><%= composant.getMois() %></td>
                                </tr>
                            <% 
                                    } 
                                } 
                            %>
                        </tbody>
                    </table>

                     <h3 class="mt-4">Filtre composant recommandes</h3>
                       <table class="table table-bordered table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Composant</th>
                                <th>Modèle</th>
                                <th>Type</th>
                                <th>Prix Achat</th>
                                <th>Prix Vente</th>
                                <th>Année</th>
                                <th>Mois</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                List<ComposantRecommender> composantsFiltre = (List<ComposantRecommender>) request.getAttribute("lsComposant");
                                if (composants != null) {
                                    for (ComposantRecommender composantFiltre : composantsFiltre) { 
                            %>
                                <tr>
                                    <td><%= composantFiltre.getIdLiaison() %></td>
                                    <td><%= composantFiltre.getComposant() %></td>
                                    <td><%= composantFiltre.getIdModel() %></td>
                                    <td><%= composantFiltre.getIdType() %></td>
                                    <td><%= composantFiltre.getPrixAchat() %></td>
                                    <td><%= composantFiltre.getPrixVente() %></td>
                                    <td><%= composantFiltre.getAnnee() %></td>
                                    <td><%= composantFiltre.getMois() %></td>
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