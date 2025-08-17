package org.springcopy.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springcopy.annote.PathLink;
import org.springcopy.exception.ClientException;
import org.springcopy.exception.FieldException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@MultipartConfig
public class FrontController extends HttpServlet {
    Map<String, Mapping> urlMap;
    Scanner scan;
    String erreur = null;
    int passage = 0;
    boolean initialized = true;
    String profilName = "PROFIL";
    List<String> urlPassed = null;

    public void init() throws ServletException {
        String controllPackage = "";
        try {
            scan = new Scanner();
            controllPackage = this.getInitParameter("controllerPack");
            try {
                profilName = this.getInitParameter("PROFILNAME");
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.urlMap = Scanner.scanCurrentProjet(controllPackage);

            System.out.println("les mapp existante : ");
            for (String key : this.urlMap.keySet()) {
                System.out.println("map : " + key);
                if (this.urlMap.get(key).getVerbActions() != null &&
                        this.urlMap.get(key).getVerbActions().size() > 0) {
                    for (VerbAction verb : this.urlMap.get(key).getVerbActions()) {
                        System.out.println("method : " + verb.getMethod() + " verb existant: " +
                                verb.getVerb());
                    }
                }
            }

        } catch (Exception e) {
            initialized = false;
            erreur = e.getMessage();
            e.printStackTrace();
        }
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response, String verb)
            throws ServletException, IOException {
        // PrintWriter out = response.getWriter();
        String url = request.getRequestURL().toString();
        // System.out.println("session instancier..");
        scan.initSession(this.urlMap, request.getSession());
        // System.out.println("session instancier...");
        request.getSession().removeAttribute("error");
        String path = request.getRequestURI();

        // Si la requête concerne un fichier statique, gérer manuellement le Content-Type
        // if (url.contains("/assets/")) {
        //     String mimeType = getServletContext().getMimeType(path);
    
        //     if (mimeType == null) {
        //         // Si le type MIME n'est pas reconnu, utiliser un type par défaut
        //         mimeType = "application/octet-stream";
        //     }
    
        //     response.setContentType(mimeType);
        //     request.getRequestDispatcher(path).forward(request, response);
        //     return;
        // }
        // else
         if (initialized && !url.contains("assets")) {
            try {
                urlPassed = null;
                urlPassed = new ArrayList<>();
                if (Scanner.isIN(url, this.urlMap)) {
                    // request.getSession().removeAttribute("error");
                    for (String key : this.urlMap.keySet()) {
                        if (Scanner.isIN(url, key)) {
                            Object res = new Object();
                            String type = "";
                            System.out.println(
                                    "Cet url : " + key + " est associé à la class "
                                            + ((Mapping) (this.urlMap.get(key))));
                            System.out.println(url + " ; " + key);
                            String linkerror = "";
                            scan.setProfilName(profilName);
                            Object[] result = scan.execute(this.urlMap.get(key), key, request, response, type, verb,
                                    linkerror);
                            res = result[0];
                            linkerror = (String) result[1];
                            String link = null;
                            response.setContentType(type);
                            if (request.getAttribute("error") != null && request.getAttribute("has_error") != null) {

                                String urlPRev = "";
                                if (linkerror != "") {
                                    urlPRev = linkerror;
                                }
                                System.out.println("misy erreur ; MIVERINA = " + urlPRev);

                                String reponse = traverserMap(urlMap, urlPRev);

                                System.out.println("misy erreur ; travers = " + reponse);
                                link = reponse;
                            }

                            if (res instanceof String) {
                                if(((String)res).startsWith("redirect:")){
                                    // String linkmethod = scan.getLinkMethod(this.urlMap.get(key), verb);
                                    // String fullURL = request.getRequestURL().toString();
                                    String requestURI = request.getRequestURI();
                                    // String parentURL = fullURL.replace(requestURI, requestURI.replace(linkmethod, "/"));
                                    System.out.println("link method : "+res);
                                    String linkredirect = ((String)res).split(":")[1];
                                    String finalLink = request.getContextPath()+linkredirect;
                                    response.sendRedirect(finalLink);
                                }else {        
                                    PrintWriter out = response.getWriter();
                                    out.println(res);
                                }
                            } else if (res instanceof ModelView) {
                                ModelView view = (ModelView) res;
                                if (request.getAttribute("has_error") == null) {
                                    link = view.getUrl();
                                }
                                if (request.getAttribute("error") == null) {
                                    Scanner.referer = null;
                                }
                                // if (view.getBody() != null && view.isDownload() && view.getFilename() != null) {
                                //     try (OutputStream output = response.getOutputStream()){
                                //         byte[] buffer = view.getBody();
                                //         response.setContentType("application/octet-stream");
                                //         response.setHeader("Content-Disposition", "attachment;filename="+view.getFilename());
                                //         response.setContentLength(buffer.length);
                                //         output.write(buffer);
                                //     } catch (Exception e) {
                                //         e.printStackTrace();
                                //     }
                                // }
                                RequestDispatcher dispatch = request.getRequestDispatcher(link);
                                HashMap<String, Object> mape = view.getData();
                                for (Map.Entry<String, Object> entry : mape.entrySet()) {
                                    String dataname = (String) entry.getKey();
                                    Object data = entry.getValue();
                                    request.setAttribute(dataname, data);
                                }
                                if (request.getAttribute("has_error") != null) {
                                    request.removeAttribute("has_error");
                                }
                                dispatch.forward(request, response);
                                return;
                            }
                            System.out.println("Cet url : " + key + " est associé à la class " + this.urlMap.get(key));
                        }
                    }

                }
            } catch (ClientException cl) {
                // if (cl.getCode() == 505) {
                // request.setAttribute("error", cl);
                // System.out.println(cl.GETFULLMESSAGE());
                // RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");

                // dispatch.forward(request, response);
                // } else {
                // out.println(cl.GETFULLMESSAGE());
                // }
                response.setContentType("text/json");
                Gson json = new Gson();
                
                PrintWriter out = response.getWriter();
                out.println(json.toJson(cl));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // else if (url.contains("assets") && !urlPassed.contains(url)) {
        // String mimeType = getServletContext().getMimeType(url);
        // if (mimeType == null) {
        // mimeType = "application/octet-stream";
        // }
        // response.setContentType(mimeType);

        // System.out.println("mime Type : " + mimeType);
        // System.out.println("Lien de url : " + url);
        // urlPassed.add(url);
        // InputStream resourceStream = getServletContext().getResourceAsStream(url);
        // if (resourceStream == null) {
        // response.sendError(HttpServletResponse.SC_NOT_FOUND);
        // return;
        // }

        // OutputStream output = response.getOutputStream();
        // byte[] buffer = new byte[1024];
        // int bytesRead;
        // while ((bytesRead = resourceStream.read(buffer)) != -1) {
        // output.write(buffer, 0, bytesRead);
        // }
        // resourceStream.close();
        // return;
        // }
        else {
            response.setContentType("text/json");
            ClientException cl = new ClientException(erreur, 500);
            Gson json = new Gson();        
            PrintWriter out = response.getWriter();
            out.println(json.toJson(cl));
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "GET");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, "POST");
    }

    public String traverserMap(Map<String, Mapping> map, String lien) {
        String reponse = null;
        for (String key : this.urlMap.keySet()) {
            if (key.contains(lien)) {
                reponse = key;
            }
        }
        return reponse;
    }
}