<%@ page import="org.springcopy.exception.FieldException" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.springcopy.core.Scanner" %>
<%@ page import="org.springcopy.core.Util" %>
<%
    HashMap<String,HashMap<String,FieldException>> errorType = null;
    if (request.getAttribute("error") != null) {
        errorType = ((HashMap<String,HashMap<String,FieldException>>)request.getAttribute("error"));
    }

    ArrayList<String> errorIdentifiant =  Scanner.getErrorsByKey(errorType,"error_email");
    ArrayList<String> errorPass =  Scanner.getErrorsByKey(errorType,"error_password");
    String userEmail = request.getParameter("user_email") != null ? request.getParameter("user_email") : "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div>
        <form action="security/login" method="post">
            <div><%= Util.genererList(errorIdentifiant) %></div>
            <input type="text" name="user_email" id="" placeholder="Identifiant" value="<%=userEmail %>">
            <div><%= Util.genererList(errorPass) %></div>
            <input type="password" name="user_password" id="" placeholder="Password"> 
            <input type="submit" value="Log in">
        </form>
    </div>
</body>
</html>