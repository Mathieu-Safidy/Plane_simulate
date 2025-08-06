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

<main>
    <h1>Ajouter une Liaison Mois-Composant</h1>

    <%-- <!-- Formulaire d'insertion -->
    <form action="/ajouterMoisComposant" method="post">
        <div>
            <label for="idMois">Mois :</label>
            <select id="idMois" name="idMois" required>
                <!-- Options des mois -->
                <% 
                    List<Mois> moisList = (List<Mois>) request.getAttribute("moisList");
                    if (moisList != null) {
                        for (Mois mois : moisList) {
                %>
                    <option value="<%= mois.getIdMois() %>"><%= mois.getLibelle() %></option>
                <% 
                        }
                    }
                %>
            </select>
        </div>

        <div>
            <label for="idComposant">Composant :</label>
            <select id="idComposant" name="idComposant" required>
                <!-- Options des composants -->
                <% 
                    List<Composant> composantList = (List<Composant>) request.getAttribute("composantList");
                    if (composantList != null) {
                        for (Composant composant : composantList) {
                %>
                    <option value="<%= composant.getIdComposant() %>"><%= composant.getNom() %></option>
                <% 
                        }
                    }
                %>
            </select>
        </div>

        <div>
            <label for="annee">Année :</label>
            <input type="number" id="annee" name="annee" placeholder="Entrez l'année" required />
        </div>

        <div>
            <button type="submit">Ajouter</button>
        </div>
    </form> --%>

    <!-- Formulaire d'insertion -->
<form action="/ajouterMoisComposant" method="post">
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-body">
                <h3 class="card-title text-center mb-4">Ajouter Mois-Composant</h3>

                <!-- Mois Select -->
                <div class="mb-3">
                    <label for="idMois" class="form-label">Mois :</label>
                    <select id="idMois" name="idMois" class="form-select" required>
                        <option value="" disabled selected>Choisir un mois</option>
                        <% 
                            List<Mois> moisList = (List<Mois>) request.getAttribute("moisList");
                            if (moisList != null) {
                                for (Mois mois : moisList) {
                        %>
                            <option value="<%= mois.getIdMois() %>"><%= mois.getLibelle() %></option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Composant Select -->
                <div class="mb-3">
                    <label for="idComposant" class="form-label">Composant :</label>
                    <select id="idComposant" name="idComposant" class="form-select" required>
                        <option value="" disabled selected>Choisir un composant</option>
                        <% 
                            List<Composant> composantList = (List<Composant>) request.getAttribute("composantList");
                            if (composantList != null) {
                                for (Composant composant : composantList) {
                        %>
                            <option value="<%= composant.getIdComposant() %>"><%= composant.getNom() %></option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Année Input -->
                <div class="mb-3">
                    <label for="annee" class="form-label">Année :</label>
                    <input type="number" id="annee" name="annee" class="form-control" placeholder="Entrez l'année" required />
                </div>

                <!-- Submit Button -->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </div>
        </div>
    </div>
</form>

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