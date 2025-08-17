<%@page import="entity.event.Vols" %>
  <%@page import="entity.event.VDetailVolsDispo" %>
    <%@page import="entity.event.VDetailReservation" %>
      <%@page import="entity.lieu.Ville" %>
        <%@page import="java.util.List,java.util.ArrayList" %>
          <% List<Ville> villes = (List<Ville>) request.getAttribute("villes");
              List<Vols> vols = (List<Vols>)request.getAttribute("vols");
                  List<VDetailVolsDispo> volsdispo = (List<VDetailVolsDispo>)request.getAttribute("volsdispo");
                      %>
                      <!DOCTYPE html>
                      <html lang="en">

                      <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Document</title>
                        <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.css">
                        <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/style.css">
                        <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/fonts.css">
                        <link rel="stylesheet" href="../assets/css/bootstrap.css">
                        <link rel="stylesheet" href="../assets/css/style.css">
                        <link rel="stylesheet" href="../assets/css/fonts.css">
                      </head>

                      <body>
                        <form class="rd-mailform form-fix" action="list" method="get">
                          <div class="row row-20 row-fix">
                            <div class="col-sm-12">
                              <label class="form-label-outside">From</label>
                              <div class="form-wrap form-wrap-inline">
                                <select class="form-input select-filter" data-placeholder="All"
                                  data-minimum-results-for-search="Infinity" name="filtre_villeDepart">
                                  <% for(Ville ville : villes) { %>
                                    <%  if (volsdispo.get(0).getVilleDepart().getIdVille().equals(ville.getIdVille())) { %>
                                      <option value="<%=ville.getIdVille() %>" selected>
                                        <%=ville.getNom() %>
                                      </option>
                                    <% } else { %>
                                      <option value="<%=ville.getIdVille() %>">
                                        <%=ville.getNom() %>
                                      </option>
                                    <% } %>
                                  <% } %>
                                </select>
                              </div>
                            </div>
                            <div class="col-sm-12">
                              <label class="form-label-outside">To</label>
                              <div class="form-wrap form-wrap-inline">
                                <select class="form-input select-filter" data-placeholder="All"
                                  data-minimum-results-for-search="Infinity" name="filtre_villeArrive">
                                  <% for(Ville ville : villes) { %>
                                    <%  if (volsdispo.get(0).getVilleArrive().getIdVille().equals(ville.getIdVille())) { %>
                                      <option value="<%=ville.getIdVille() %>" selected>
                                        <%=ville.getNom() %>
                                      </option>
                                      <% } else { %>
                                      <option value="<%=ville.getIdVille() %>">
                                        <%=ville.getNom() %>
                                      </option>
                                      <% } %>
                                    <% } %>
                                </select>
                              </div>
                            </div>
                            <div class="col-sm-12 col-lg-6">
                              <label class="form-label-outside">Depart
                                Date</label>
                              <div class="form-wrap form-wrap-validation">
                                <!-- Select -->
                                <input class="form-input" id="dateForm" type="date" name="filtre_dateDepart">
                              </div>
                            </div>

                            <div class="col-lg-6">
                              <label class="form-label-outside">Place</label>
                              <div class="form-wrap form-wrap-modern">
                                <input class="form-input input-append" id="form-element-stepper" type="number" min="0"
                                  value="2" name="filtre_place">
                              </div>
                            </div>
                          </div>
                          <div class="form-wrap form-button">
                            <button class="button button-block button-secondary" type="submit">search flight</button>
                          </div>
                        </form>
                        <br>
                        <p>Vols dispo</p>
                        <table>
                          <tr>
                            <th>Id vol</th>
                            <th>Date du vol</th>
                            <th>Ville depart</th>
                            <th>Ville d'arrive</th>
                            <th>Id avion</th>
                            <th>Model Avion</th>
                            <th>Modifier</th>
                            <th>Suprimer</th>
                          </tr>
                          <% for(VDetailVolsDispo vol : volsdispo) { %>
                            <tr>
                              <td>
                                <%=vol.getIdVols() %>
                              </td>
                              <td>
                                <%=vol.getDateVol() %>
                              </td>
                              <td>
                                <%=vol.getVilleDepart().getNom() %>
                              </td>
                              <td>
                                <%=vol.getVilleArrive().getNom() %>
                              </td>
                              <td>
                                <%=vol.getIdAvion() %>
                              </td>
                              <td>
                                <%=vol.getAvion().getModel().getNom() %>
                              </td>
                              <td>
                                <%=vol.getSiegeDetail().getNom() %>
                              </td>
                              <td><a href="update/form?id_vols=<%=vol.getIdVols() %>"
                                  class="button button-sm button-secondary button-nina">Update</a></td>
                              <td><a href="delete?id_vols=<%=vol.getIdVols() %>"
                                  class="button button-sm button-secondary button-nina">Delete</a></td>
                            </tr>
                            <% } %>
                        </table>
                        <p>Tout les vols</p>
                        <table>
                          <tr>
                            <th>Id vol</th>
                            <th>Date du vol</th>
                            <th>Ville depart</th>
                            <th>Ville d'arrive</th>
                            <th>Id avion</th>
                            <th>Model Avion</th>
                            <th>Modifier</th>
                            <th>Suprimer</th>
                          </tr>
                          <% for(Vols vol : vols) { %>
                            <tr>
                              <td>
                                <%=vol.getIdVols() %>
                              </td>
                              <td>
                                <%=vol.getDateVol() %>
                              </td>
                              <td>
                                <%=vol.getVilleDepart().getNom() %>
                              </td>
                              <td>
                                <%=vol.getVilleArrive().getNom() %>
                              </td>
                              <td>
                                <%=vol.getIdAvion() %>
                              </td>
                              <td>
                                <%=vol.getAvion().getModel().getNom() %>
                              </td>
                              <td><a href="update/form?id_vols=<%=vol.getIdVols() %>"
                                  class="button button-sm button-secondary button-nina">Update</a></td>
                              <td><a href="delete?id_vols=<%=vol.getIdVols() %>"
                                  class="button button-sm button-secondary button-nina">Delete</a></td>
                            </tr>
                            <% } %>
                        </table>

                      </body>

                      </html>