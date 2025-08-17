// This code should be in a dedicated servlet or controller
// and should not have any HTML tags.

<%
    byte[] data = (byte[])request.getAttribute("fichier");
    if(data != null && data.length > 0) {
        // Set headers for file download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + request.getAttribute("filename"));
        response.setContentLength(data.length);
        
        // Write the PDF data to the output stream
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(data);
            outputStream.flush();
        }
    } else {
        // If file is not found, send an error status
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Fichier non trouvé ou vide");
    }
%>