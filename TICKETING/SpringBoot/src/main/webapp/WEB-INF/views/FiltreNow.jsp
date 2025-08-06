


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

  <div class="container">
        <!-- Formulaire de filtrage -->

      <div class="card" style="margin-top: 30px; padding: 20px;">
    <h1 class="my-4 text-center">Filtre</h1>
    <form id="filterForm" class="mb-4">
        <div class="row g-3">
            <!-- Type de Composant -->
            <div class="col-md-4">
                <label for="typeComposant" class="form-label fw-bold">Type de Composant</label>
                <select id="idTypeComposant" class="form-select">
                    <option value="">-- Choisir --</option>
                </select>
            </div>

            <!-- Type d'Ordinateur -->
            <div class="col-md-4">
                <label for="typeOrdinateur" class="form-label fw-bold">Type d'Ordinateur</label>
                <select id="idTypeOrdinateur" class="form-select">
                    <option value="">-- Choisir --</option>
                </select>
            </div>

            <!-- Service -->
            <div class="col-md-4">
                <label for="service" class="form-label fw-bold">Service</label>
                <select id="idService" class="form-select">
                    <option value="">-- Choisir --</option>
                </select>
            </div>
        </div>
    </form>
</div>


        <!-- Tableau des composants -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Ordinateur</th>
                    <th>Type Ordinateur</th>
                    <th>ID Client</th>
                    <th>Nom Client</th>
                    <th>Nom Composant</th>
                    <th>idModel</th>
                    <th>Modele Composant</th>
                    <th>Prix Vente</th>
                    <th>Prix Achat</th>
                    <th>Etat Composant</th>
                    <th>Type Composant</th>
                    <th>Service </th>
                </tr>
            </thead>
            <tbody id="tableBody">
                <!-- Les lignes du tableau seront générées dynamiquement via JavaScript -->
            </tbody>
        </table>
    </div>
      <script src="/assets/js/FiltreNow.js"></script>


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



