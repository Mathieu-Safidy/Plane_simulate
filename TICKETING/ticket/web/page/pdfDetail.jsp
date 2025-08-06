<%
    String name = (String) request.getAttribute("name");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Aperçu PDF</title>
    <style>
        iframe {
            width: 100%;
            height: 600px;
            border: 1px solid #ccc;
        }
        .download-btn {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<h2>Aperçu du document PDF</h2>

<!-- Aperçu PDF -->
<embed  src="<%=request.getContextPath() %><%=name%>" type="application/pdf"/>

<!-- Bouton de téléchargement -->
<a href="<%=request.getContextPath() %><%=name%>" class="download-btn" download>Télécharger le PDF</a>

</body>
</html>
