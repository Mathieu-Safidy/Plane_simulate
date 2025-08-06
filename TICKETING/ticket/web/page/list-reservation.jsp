<%@ page import="entity.event.Reservation,java.util.List" %>
<%@ page import="entity.event.ReservationMere" %>

<%
    List<Reservation> resa = (List<Reservation>) request.getAttribute("reservations");
    List<ReservationMere> resaMere = (List<ReservationMere>) request.getAttribute("reservationsMere");
    String message = "";
    if(request.getParameter("message") != null) {
        message = request.getParameter("message");
    }
 
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List de reservation</title>
</head>
<body>
    <h1>List de reservation</h1>
    <p><%=message %></p>
    <table>
        <% for(ReservationMere reservationMere : resaMere) { %>

        <tr>
            <td>
                <table>
                    <tr><td><%=reservationMere.getIdReservationMere()%></td></tr>
                    <tr><td><a href="<%=request.getContextPath() %>/Api/dupliquer?id=<%=reservationMere.getIdReservationMere()%>">Exporter</a></td></tr>
                </table>
            </td>
            <td>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Numero reservation</th>
                            <th>Date reservation</th>
                            <th>Classe</th>
                            <th>Option</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Reservation reservation : reservationMere.getReservations()) { %>
                            <tr>

                                <td><%=reservation.getIdReservation() %></td>
                                <td><%=reservation.getDateReservation() %></td>
                                <td><%=reservation.getType().getNom() %></td>
                                <td>
                                <form action="reservations/annulation" method="post">
                                    <input type="datetime-local" name="filtre_dateReservation">
                                    <input type="hidden" name="filtre_idReservation" value="<%=reservation.getIdReservation() %>" >
                                    <input type="submit" value="Annuler">
                                </form>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </td>
        </tr>
    <% } %>
    </table>
</body>
</html>