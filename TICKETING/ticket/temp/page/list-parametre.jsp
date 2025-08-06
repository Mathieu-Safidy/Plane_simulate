<%@page import="java.util.List,param.Parametre" %>
<%
    List<Parametre> list = (List<Parametre>)request.getAttribute("parametre");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List parametre</title>
</head>
<body>
    <h1>List parametre</h1>
    <table>
        <thead>
            <tr>
                <th>Id parametre</th>
                <th>Nom</th>
                <th>Valeur</th>
            </tr>
        </thead>
        <tbody>
            <% for (Parametre parametre : list) { %>
                <tr>
                    <td><a href="parametre/update/form?id=<%= parametre.getIdParametre() %>"><%= parametre.getIdParametre() %></a></td>
                    <td><%= parametre.getType().getLibele() %></td>
                    <td><%= parametre.getValeur() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    </table>
</body>
</html>