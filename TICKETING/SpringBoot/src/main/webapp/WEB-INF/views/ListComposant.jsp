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
        <div class="card">
            <div class="card-body">

              <div class="card">
                      <div class="card-body">
                          <h2 class="mb-4">Rechercher un composant</h2>
                          <form id="searchForm">
                              <!-- Champ Nom -->
                              <div class="mb-3">
                                  <label for="nom" class="form-label">Nom :</label>
                                  <input type="text" id="nom" name="name" class="form-control" />
                              </div>
                              <!-- Champ Model -->
                              <div class="mb-3">
                                  <label for="model" class="form-label">Model :</label>
                                  <input type="text" id="prix" name="model" class="form-control" />
                              </div>
                              <!-- Champ Type -->
                              <div class="mb-3">
                                  <label for="type" class="form-label">Type :</label>
                                  <input type="text" id="type" name="type" class="form-control" />
                              </div>
                          </form>
                      </div>
                  </div>
            </div> 
        

           <center> <h2 class="card-title">Liste des composants</h2> </center>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Model</th>
                        <th>Type</th>
                        <th>Stock Restant</th>
                        <th>PrixAchat</th> 
                        <th>Prix Vente</th> 
                    </tr>
                </thead>
                <tbody id="results">
                    <!-- résultats dynamiquement -->
                </tbody>
            </table>


    <script src="/assets/js/rechercheComposant.js"></script>

            

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