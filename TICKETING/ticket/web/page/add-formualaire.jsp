<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.event.Vols, entity.lieu.Ville, entity.avion.Avion, entity.place.DetailSiege" %>
<%@ page import="java.util.List" %>
<%@ page import = "entity.place.TypeSiege" %>

<%
    Vols vols = (Vols) request.getAttribute("vols");
    List<DetailSiege> detailSiege = (List<DetailSiege>) request.getAttribute("detailSiege");
    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    List<Avion> avions = (List<Avion>) request.getAttribute("avions");
    List<TypeSiege> typeSiegeList = (List<TypeSiege>) request.getAttribute("typeSiege");
    boolean isUpdate = vols != null && vols.getIdVols() != null;
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><%= isUpdate ? "Mise à jour" : "Ajout" %> d’un vol</title>
</head>
<body>

<h1><%= isUpdate ? "Modifier un vol" : "Ajouter un vol" %></h1>

<form method="get" action="<%= isUpdate ? "vols/update" : "vols/add" %>">

    <!-- Champ caché pour l'ID (en mise à jour) -->
    <%
        if (isUpdate) {
    %>
    <input type="hidden" name="vols_idVols" value="<%= vols.getIdVols() %>">
    <%
        }
    %>

    <!-- Date du vol -->
    <label for="dateVol">Date du vol :</label>
    <input type="datetime-local" name="vols_dateVol" id="dateVol"
           value="<%= isUpdate && vols.getDateVol() != null ? vols.getDateVol().toLocalDateTime().toString().replace('T', 'T') : "" %>" required><br>

    <!-- Ville de départ -->
    <label for="villeDepart">Ville de départ :</label>
    <select name="vols_idVilleDepart" id="villeDepart" required>
        <option value="">-- Choisir --</option>
        <% for (Ville ville : villes) { %>
        <option value="<%= ville.getIdVille() %>" <%= (isUpdate && ville.getIdVille().equals(vols.getIdVilleDepart())) ? "selected" : "" %>>
            <%= ville.getNom() %>
        </option>
        <% } %>
    </select><br>

    <!-- Ville d'arrivée -->
    <label for="villeArrive">Ville d’arrivée :</label>
    <select name="vols_idVilleArrive" id="villeArrive" required>
        <option value="">-- Choisir --</option>
        <% for (Ville ville : villes) { %>
        <option value="<%= ville.getIdVille() %>" <%= (isUpdate && ville.getIdVille().equals(vols.getIdVilleArrive())) ? "selected" : "" %>>
            <%= ville.getNom() %>
        </option>
        <% } %>
    </select><br>

    <!-- Avion -->
    <label for="avion">Avion :</label>
    <select name="vols_idAvion" id="avion" required>
        <option value="">-- Choisir --</option>
        <% for (Avion a : avions) { %>
        <option value="<%= a.getIdAvion() %>" <%= (isUpdate && a.getIdAvion().equals(vols.getIdAvion())) ? "selected" : "" %>>
            <%= a.getIdAvion() %>
        </option>
        <% } %>
    </select><br>

    <!-- Temps d’annulation -->
    <label for="cancel">Temps d’annulation (en minute) :</label>
    <input type="number" step="0.1" name="vols_timeCancel" id="cancel"
           value="<%= isUpdate ? vols.getTimeCancel() : "" %>" required><br>

    <h2>Capacité par type de siège</h2>

    <table border="1">
        <thead>
        <tr>
            <th>Type de siège</th>
            <th>Nombre de places</th>
            <th>Prix</th>
        </tr>
        </thead>
        <tbody>
        <% 
            int taille = 0;
            if(isUpdate) {
                taille = detailSiege.size();
            }else{
                taille = typeSiegeList.size();
            }
        for (int i = 0 ; i < taille;i++) {
           TypeSiege t = null;
           DetailSiege detail = null;
           if(isUpdate) {
                detail = detailSiege.get(i);
            }else{
                t = typeSiegeList.get(i);
            }
           String prefix = "typeSiege"+i;
        %>
        <tr>
            <td>
                <%= (isUpdate) ? detail.getIdType() : t.getIdType() %>
                <%
                    String name = prefix+"_idType";
                %>
                <input type="hidden" name="<%=name%>" value="<%= (isUpdate) ? detail.getIdType() : t.getIdType() %>">
            </td>
            <td>
                <%
                    String name1 = prefix+"_nombre";
                %>
                <input type="number" name="<%=name1%>" min="0" placeholder="Ex: 50" value="<%= (isUpdate) ? detail.getNombre() : "" %>">
            </td>
            <td>
                <%
                    String name2 = prefix+"_prix";
                %>
                <input type="number" name="<%=name2%>" step="0.01" min="0" placeholder="Ex: 250000.00" value="<%= (isUpdate) ? detail.getPrix() : "" %>">
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>


    <button type="submit"><%= isUpdate ? "Mettre à jour" : "Ajouter" %></button>
</form>

</body>
</html>
