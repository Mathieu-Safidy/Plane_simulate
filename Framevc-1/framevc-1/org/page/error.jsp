<%@ page import="org.springcopy.exception.ClientException" %>
<%
    ClientException error = (ClientException)request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error page</title>
</head>
<body>
    <%=error.GETFULLMESSAGE() %>
</body>
</html>