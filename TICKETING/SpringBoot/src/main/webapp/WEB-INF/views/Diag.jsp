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
            <h2 class="mb-4">Diagnostique du produit</h2>
            <form id="formDiagnostique" method="POST">
                <input type="hidden" id="hiddenOrdiId" name="idOrdinateur" />
                <div class="mb-3">
                    <label for="client" class="form-label">Liste Client :</label>
                    <%@ page import="java.util.List" %>
                    <%@ page import="mg.working.model.user.*" %>
                    <%@ page import="mg.working.model.composant.*" %>
                       <%@ page import="mg.working.model.composant.ModelComposant" %>
               
                      <%
                 
                    List<Client> clients = (List<Client>) request.getAttribute("clients");
                %>

                 <div class="mb-3">
                <select class="form-select" id="client" name="idClient" aria-label="Sélectionner un client">
                    <option value="" disabled selected>Choisir un client</option>
                    <%    
                    for (Client client : clients) {
                           
                            String ordinateursJson = new com.google.gson.Gson().toJson(client.getLsOrdi());
                    %>
                        <option value="<%=client.getIdClient()%>" data-ordinateurs='<%=ordinateursJson%>'>
                            <%=client.getNom()%> - Ordinateur(s)
                        </option>
                    <% } %>
                </select>
                </div> 


                <div class="mb-3">
                <select class="form-select" id="ordinateur" name="idOrdi" aria-label="Sélectionner un ordinateur" disabled>
                    <option value="" disabled selected>Choisir un ordinateur</option>
                </select>
                </div>

         
                </div>

                <div class="mb-3">
                    <label for="composant" class="form-label">Composant :</label>
                    <input type="text" id="composant" name="libelle" class="form-control" required />
                </div>
                <div class="mb-3">
                  
                      <label for="technicien" class="form-label">Liste des Models :</label>
                    <% 
                        List<ModelComposant> modelComposants = (List<ModelComposant>) request.getAttribute("lsModel");
                        if (modelComposants != null) { 
                    %>
                        <select class="form-select" id="TypeComposant" name="idModel" aria-label="Default select example">
                            <% for (ModelComposant md : modelComposants ) { %>
                                <option value="<%=md.getIdModel() %>"><%=md.getLibelle()%>  </option>
                            <% } %>
                        </select>
                    <% } %>
                </div>

                 <div class="mb-3">
                    <label for="technicien" class="form-label">Liste des TypeComposant :</label>
                    <% 
                        List<TypeComposant> typeComposants = (List<TypeComposant>) request.getAttribute("typeComposants");
                        if (typeComposants != null) { 
                    %>
                        <select class="form-select" id="TypeComposant" name="idTypeComposant" aria-label="Default select example">
                            <% for (TypeComposant typeComposant : typeComposants) { %>
                                <option value="<%=typeComposant.getIdType()%>"><%=typeComposant.getLibelle()%>  </option>
                            <% } %>
                        </select>
                    <% } %>
                </div>

                <div class="mb-3">
                    <label for="technicien" class="form-label">Liste des Techniciens :</label>
                    <% 
                        List<Technicien> techniciens = (List<Technicien>) request.getAttribute("techniciens");
                        if (techniciens != null) { 
                    %>
                        <select class="form-select" id="technicien" name="idTechnicien" aria-label="Default select example">
                            <% for (Technicien technicien : techniciens) { %>
                                <option value="<%=technicien.getIdTechnicien()%>"><%=technicien.getNom()%></option>
                            <% } %>
                        </select>
                    <% } %>
                </div>
                <button type="button" id="serviceButton" class="btn btn-primary">Service</button>
                <button type="button" id="remplacerButton" class="btn btn-primary">Remplacer</button>
            </form>

        
        
                <form action="/VoirPanier" method="GET">
                    <button type="submit" class="btn btn-primary" style="margin-top :10px;">Voir Panier</button>
                </formdtirtieroh>
    
            
        </div>
    </div>
    </section>

    <script src="/assets/js/choixUnique.js"></script>
    <script src="/assets/js/clientPc.js"></script>

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