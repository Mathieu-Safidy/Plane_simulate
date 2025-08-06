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

<%@ page import="mg.working.model.service.*" %>
<%@ page import="mg.working.model.user.*" %> 
<%@ page import="java.util.List" %>

  <main id="main" class="main">

   <section class="section mt-5">
        <form action="/filter" method="post">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-3">
                            <h1 class="card-title">Commissions des Techniciens</h1>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dateDebut">Date Debut</label>
                        <input type="date" class="form-control" id="dateDebut" name="dateDebut" required>
                    </div>

                    <div class="form-group">
                        <label for="dateFin">Date Fin</label>
                        <input type="date" class="form-control" id="dateFin" name="dateFin" required>
                    </div>

                <div class="form-group">
                    <label for="idSexe">Sexe</label>
                    <select class="form-control" id="idSexe" name="idSexe" required>
                        <% 
                            List<Sexe> lsSexe = (List<Sexe>) request.getAttribute("lsSexe");
                            for (Sexe sexe : lsSexe) { 
                        %>
                            <option value="<%= sexe.getIdSexe() %>">
                                <%= sexe.getNom() %> 
                            </option>
                        <% 
                            } 
                        %>
                    </select>
                </div>


                    <button type="submit" class="btn btn-primary">Filtrer</button>
                </div>
            </div>
        </form>

        <div class="card mt-4">
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID Technicien</th>
                            <th>Technicien</th>
                            <th>Total Commission</th>
                            <th>Date Commission</th>
                            <th>Sexe</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%-- Récupérer les commissions depuis le modèle --%>
                        <%
                            List<Commission> commissions = (List<Commission>) request.getAttribute("commissions");
                            if (commissions != null) {
                                for (Commission commission : commissions) {
                        %>
                        <tr>
                            <td><%= commission.getIdTech() %></td>
                            <td><%= commission.getTechnicien().getNom() %></td>
                            <td><%= commission.getTotalCommission() %></td>
                            <td><%= commission.getDateCommission() %></td>
                                 <td><%= commission.getSexe() %></td>
                        </tr>
                        <% 
                                }
                            }
                        %>
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