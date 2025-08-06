<%@ page import="param.Parametre,param.TypeParam" %>
<%
    Parametre param = (Parametre)request.getAttribute("parametre");
    TypeParam typeParam = (TypeParam)request.getAttribute("type");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update paramatre</title>
</head>                                                                                                                                                                                                                                                                                                                                 
<body>
    <form action="parametre/update" method="post">
        <input type="hidden" name="parametre_idParametre" value="<%=param.getIdParametre() %>">
        <input type="number" name="parametre_valeur" id="" value="<%=param.getValeur() %>">
        <input type="date" name="parametre_dateajout" id="" value="<%=param.getDateajout() %>">
        <select name="parametre_idType" id="" disabled="disabled">
            <option value="<%=typeParam.getIdType() %>"><%=typeParam.getLibele() %></option>
        </select>
        <input type="submit" value="valider">
    </form>
</body>
</html>