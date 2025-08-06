package org.springcopy.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

import org.springcopy.annote.AnnotParam;
import org.springcopy.annote.ControllerSet;
import org.springcopy.annote.ErrorRedirect;
import org.springcopy.annote.Format;
import org.springcopy.annote.GET;
import org.springcopy.annote.Length;
import org.springcopy.annote.MiddleWare;
import org.springcopy.annote.Numeric;
import org.springcopy.annote.POST;
import org.springcopy.annote.PathLink;
import org.springcopy.annote.RestApi;
import org.springcopy.core.FileMap;
import org.springcopy.exception.ClientException;
import org.springcopy.exception.ExpressException;
import org.springcopy.exception.FieldException;
import org.springcopy.exception.FormException;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.servlet.RequestDispatcher;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.Normalizer.Form;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;

public class Scanner {

    Map instance = new HashMap();
    static String referer = null;
    private String profilName;
    String methodPath ;

    // HashMap<String, FieldException> error = new HashMap<>();

    public static String getverb(Method met) {
        GET get = met.getAnnotation(GET.class);
        POST post = met.getAnnotation(POST.class);
        String verb = "GET";
        if (post != null) {
            verb = "POST";
        }
        if (get != null) {
            verb = "GET";
        }
        return verb;
    }

    public static Map<String, Mapping> scanCurrentProjet(String packageName) throws Exception {
        Map<String, Mapping> maper = new HashMap<>();
        try {
            if (packageName == null) {
                throw new Exception("Package inexistant veuillez verifier vos packages");
            }
            Thread currentThread = Thread.currentThread();
            ClassLoader classLoader = currentThread.getContextClassLoader();
            String path = packageName.replace(".", "/");
            java.net.URL ressource = classLoader.getResource(path);
            ressource = new java.net.URL(ressource.toString().replace("%20", " "));
            // System.out.println(ressource);
            // System.out.println(path);
            java.io.File directory = new java.io.File(ressource.getFile());
            // System.out.println(directory);

            List<String> listClass = new ArrayList<>();
            for (java.io.File file : directory.listFiles()) {
                if (file.getName().endsWith(".class")) {
                    String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                    Class<?> cl = Class.forName(className);
                    // prendre tout les class avec des annotation de controller
                    ControllerSet annot = cl.getAnnotation(ControllerSet.class);
                    if (annot != null) {
                        Method[] methode = cl.getMethods();
                        List<String> listLien = new ArrayList<>();
                        for (Method met : methode) {
                            PathLink link = met.getAnnotation(PathLink.class);
                            String verb = Scanner.getverb(met);
                            if (annot.url() != null 
                            // && !annot.url().equals("") 
                            && !listClass.contains(annot.url())) {
                                if (link != null && link.path() != null
                                //  && !link.path().equals("")
                                 ) {
                                    if (!in(listLien, annot.url() + link.path())) {
                                        listLien.add(annot.url() + link.path());
                                        
                                        Mapping map = new Mapping(cl.getName());
                                        // System.out.println("mapiditra : " + cl.getName());
                                        // System.out.println("mapiditra "+annot.url() + link.path()+" raha tsisy verb :
                                        // " + verb);
                                        VerbAction actverb = new VerbAction(met.getName(), verb);
                                        actverb.setLink(link.path());
                                        map.addVerbAction(actverb);
                                        maper.put(annot.url() + link.path(), map);
                                    } else {
                                        // System.out.println("mapiditra "+annot.url() + link.path()+" verb : " + verb);
                                        Mapping mp = maper.get(annot.url() + link.path());
                                        VerbAction actverb = new VerbAction(met.getName(), verb);
                                        mp.addVerbAction(actverb);
                                    }
                                    // System.out.println((maper.get( annot.url() + link.path()).getVerbActions())
                                    // == null ? 0 :maper.get( annot.url() + link.path()).getVerbActions().size());
                                }
                            }else if (annot.url() == null && !listClass.contains(annot.url())) {
                                if (link != null && link.path() != null) {
                                    if (!in(listLien, link.path())) {
                                        listLien.add(link.path());
                                        
                                        Mapping map = new Mapping(cl.getName());
                                        VerbAction actverb = new VerbAction(met.getName(), verb);
                                        actverb.setLink(link.path());
                                        map.addVerbAction(actverb);
                                        maper.put(link.path(), map);
                                    } else {
                                        Mapping mp = maper.get(link.path());
                                        VerbAction actverb = new VerbAction(met.getName(), verb);
                                        mp.addVerbAction(actverb);
                                    }
                                }
                            }
                             else if (listClass.contains(annot.url())) {
                                throw new Exception(
                                        "\n Il existe deja un super lien identique a ce dernier " + annot.url() + "\n");
                            }
                        }
                        listClass.add(annot.url());
                        //
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return maper;
    }

    public void initSession(Map<String, Mapping> mapp, HttpSession sessstock) {

        try {
            String deja = "";
            for (String key : mapp.keySet()) {
                Mapping map = mapp.get(key);
                String className = map.getClassName();
                if (className.compareTo(deja) != 0) {
                    deja = className;
                    System.out.println("class name : " + className + " deja : " + deja);
                    Class<?> cl = Class.forName(className);
                    Object classInstance = cl.getDeclaredConstructor().newInstance(); // Create an instance of the class
                    Field[] fields = cl.getDeclaredFields();
                    for (Field field : fields) {
                        if (field.getType().equals(Session.class)) {
                            field.setAccessible(true); // Make private fields accessible
                            sessstock.setAttribute("test", "ao tsara");
                            Session sess = (Session) field.getType().getDeclaredConstructor(HttpSession.class)
                                    .newInstance(sessstock);
                            System.out.println("class instance " + classInstance + " ; sess" + sess);
                            field.set(classInstance, sess); // Set the field on the class instance
                            // String fiel = field.getName();
                            // String change =
                            // fiel.substring(0,1).toUpperCase()+fiel.substring(1,fiel.length());
                            // String method = "set"+change;
                            // System.out.println(method);
                            // Method[] methods = cl.getDeclaredMethods();
                            // for (Method met : methods) {
                            // if (met.getName().equals(method)) {
                            // met.invoke(classInstance, sess);
                            // break;
                            // }
                            // }
                        }
                    }
                    // System.out.println("maka : " + cl.getName());
                    this.instance.put(cl.getName(), classInstance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isIN(Map<String, Mapping> list, String lien) {
        for (String element : list.keySet()) {
            if (element.equals(lien)) {
                return true;
            }
        }
        return false;
    }

    public static Class<?> takeTypeField(Class<?> model, String fiel) {
        Class<?> res = null;
        try {
            Field[] fields = model.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(fiel)) {
                    res = field.getType();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static HashMap<String, FieldException> checkFieldConstraint(Class<?> model, String fiel, Object value)
            throws Exception {
        Field jerena = null;
        Field[] fields = model.getDeclaredFields();
        HashMap<String, FieldException> fielException = null;
        for (Field field : fields) {
            if (field.getName().equals(fiel)) {
                jerena = field;
                break;
            }
        }

        Annotation[] fieldanot = jerena.getAnnotations();
        int nombre = 0;
        for (Annotation annotation : fieldanot) {
            if (annotation instanceof Format) {
                Format formation = (Format) annotation;
                String valueFormatDefault = formation.format();
                SimpleDateFormat formater = new SimpleDateFormat(valueFormatDefault);
                String formattedDateStr = formater.format((Date) value);
                java.util.Date parsedUtilDate = formater.parse(formattedDateStr);
                value = new Date(parsedUtilDate.getTime());
                System.out.println("format " + valueFormatDefault + " value " + value);
            } else if (annotation instanceof Length) {
                Length formation = (Length) annotation;
                int valueLenDefault = formation.len();
                if (value.toString().length() > valueLenDefault) {
                    if (fielException == null) {
                        fielException = new HashMap<>();
                    }
                    fielException.put("error_" + fiel + "_" + nombre, new FieldException(formation.message(), 500));
                    nombre += 1;
                }
            } else if (annotation instanceof Numeric) {
                if (!isNumeric(value)) {
                    if (fielException == null) {
                        fielException = new HashMap<>();
                    }
                    Numeric num = (Numeric) annotation;
                    fielException.put("error_" + fiel + "_" + nombre, new FieldException(num.message(), 500));
                    nombre += 1;
                }
            }
        }

        return fielException;
    }

    public static boolean isNumeric(Object input) {
        String donne = input.toString();
        if (input == null || (donne).isEmpty()) {
            return false; // Une chaîne null ou vide n'est pas un nombre
        }

        // Expression régulière pour identifier les nombres (entiers ou décimaux)
        String regex = "^-?\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(donne).matches();
    }

    private static Object convertParameterValue(Class<?> targetType, String parameterValue)
            throws IllegalArgumentException, Exception {
        try {
            if (targetType == String.class) {
                return parameterValue;
            } else if (targetType == int.class || targetType == Integer.class) {
                try {
                    return Integer.parseInt(parameterValue);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid integer: " + parameterValue, e);
                }
            } else if (targetType == Date.class) {
                String[] possibleFormats = { "yyyy-MM-dd", "MM/dd/yyyy", "dd-MM-yyyy" };
                for (String format : possibleFormats) {
                    System.out.println("format : "+format+ " value "+parameterValue);
                    try {

                        SimpleDateFormat sdf = new SimpleDateFormat(format);
                        java.util.Date parsedDate = sdf.parse(parameterValue);
                        // return new Date(parsedDate.getTime());
                        System.out.println("date : "+parsedDate.toString());
                        return parsedDate;
                    } catch (ParseException e) {
                        // e.printStackTrace();
                    }
                }
                throw new IllegalArgumentException("Invalid Date: " + parameterValue);
            }else if (targetType == java.sql.Date.class) {
                String[] possibleFormats = {"yyyy-MM-dd" , "dd-MM-yyyy" , "MM-dd-yyyy" ,  "MM/dd/yyyy"};
                for (String format : possibleFormats) {
                    System.out.println("format : "+format+ " value "+parameterValue);
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat(format);
                        java.util.Date parsedDate = sdf.parse(parameterValue);
                        java.sql.Date dateSql = new java.sql.Date(parsedDate.getTime());
                        // return new Date(parsedDate.getTime());
                        System.out.println("date : "+dateSql.toString());
                        return dateSql;
                    } catch (Exception e) {
                        // e.printStackTrace();
                    }
                }
                throw new IllegalArgumentException("Invalid Date: " + parameterValue);
            } else if (targetType == long.class || targetType == Long.class) {
                try {
                    return Long.parseLong(parameterValue);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid long: " + parameterValue, e);
                }
            } else if (targetType == float.class || targetType == Float.class) {
                try {
                    return Float.parseFloat(parameterValue);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid float: " + parameterValue, e);
                }
            } else if (targetType == double.class || targetType == Double.class) {
                try {
                    return Double.parseDouble(parameterValue);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid double: " + parameterValue, e);
                }
            } else if (targetType == boolean.class || targetType == Boolean.class) {
                try {
                    return Boolean.parseBoolean(parameterValue);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid boolean: " + parameterValue, e);
                }
            } else if (targetType == Timestamp.class) {
                System.out.println("type timestamp ...");
                try {
                    LocalDateTime localDateTime = null;
                    try {
                        localDateTime = LocalDateTime.parse(parameterValue,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                    } catch (DateTimeException e) {
                        LocalDate localDate = LocalDate.parse(parameterValue);
                        localDateTime = localDate.atTime(LocalTime.MAX);
                    }
                    return java.sql.Timestamp.valueOf(localDateTime);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid boolean: " + parameterValue, e);
                }
            }
            // Add more type conversions as needed
        } catch (Exception e) {
            // Log the exception or handle it according to the application's requirements
            throw new IllegalArgumentException(
                    "Error converting parameter value: " + parameterValue + " target type " + targetType, e);
        }
        return null;
    }

    public static boolean in(ArrayList<Object> list, String recherche) {
        for (Object object : list) {
            if (object.equals(recherche)) {
                return true;
            }
        }
        return false;
    }

    public static boolean in(List<String> liste, String recherche) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).equals(recherche)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRestApi(Method fonction) {
        if (fonction.getAnnotation(RestApi.class) != null) {
            return true;
        }
        return false;
    }

    public static boolean checkErrorLink(Method fonction) {
        if (fonction.getAnnotation(ErrorRedirect.class) != null) {
            return true;
        }
        return false;
    }

    public String checkView(Object resultat) {
        if (resultat instanceof ModelView) {
            HashMap<String, Object> mappe = ((ModelView) resultat).getData();
            Gson gson = new Gson();
            String json = gson.toJson(mappe);
            return json;
        }
        return (String) resultat;
    }

    public String getMethode(Mapping map, String verb) {
        for (VerbAction action : map.getVerbActions()) {
            if (verb.equals(action.getVerb())) {
                return action.getMethod();
            }
        }
        return null;
    }

    public boolean checkExistVerb(Mapping map, String verb) {
        for (VerbAction action : map.getVerbActions()) {
            if (verb.equals(action.getVerb())) {
                return true;
            }
        }
        return false;
    }

    public String getLinkMethod(Mapping map,String verb) {
        String methodlink = null;
        try {
            Object incancier = this.instance.get(map.getClassName());
            Method method = getMethod(incancier.getClass(), this.getMethode(map, verb));
            PathLink path = method.getAnnotation(PathLink.class);
            if (path != null) {
                methodlink = path.path();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return methodlink;
    }

    // ================================================================
    public Object[] execute(Mapping map, String lien, HttpServletRequest request, HttpServletResponse response,
            String typeAffichage, String verb, String linkerror)
            throws Exception {
        try {
            Object incancier = this.instance.get(map.getClassName());
            System.out.println("incancier = " + incancier + " meth = " + this.getMethode(map, verb) + " map "
                    + map.getClassName());
            Method method = getMethod(incancier.getClass(), this.getMethode(map, verb));
            if (method != null && this.checkExistVerb(map, verb) && request.getAttribute("error") == null) {
                MiddleWare middleWare = null;
                Class<?> classMere = (Class<?>)incancier;
                if(classMere.getAnnotation(MiddleWare.class) != null) {
                    middleWare = method.getAnnotation(MiddleWare.class);
                }
                if (method.getAnnotation(MiddleWare.class) != null) {
                    middleWare = method.getAnnotation(MiddleWare.class);
                }
                
                String userProfil = null;
                if (middleWare != null) {
                    userProfil = middleWare.acces();
                }
                HttpSession session = request.getSession();
                String profil = (String) session.getAttribute(profilName);
                if (profil == null && userProfil == null && middleWare !=null) {
                throw new Exception("User profil not found");
                } else if(userProfil!=null && !userProfil.equals(profil)) {
                    if(middleWare.linkLogin() != null) {
                        response.sendRedirect(request.getContextPath()+"/page/"+middleWare.linkLogin());
                    }
                throw new ClientException("Vous n'avez pas acces a ce lien ,veuillez informer votre administrateur", 505);
                }
                Parameter[] parameters = getParameters(method);
                System.out.println("lien error 3 " + linkerror + " identity" + System.identityHashCode(linkerror));
                Object[] resultat = processParameters(request, method, parameters, linkerror);
                Object[] processedParameters = (Object[]) resultat[0];
                linkerror = (String) resultat[1];
                System.out.println("processedParameters : " + processedParameters.length);
                Object result = invokeMethod(incancier, method, processedParameters);

                checkReturnType(result);
                if (checkRestApi(method)) {
                    result = checkView(result);
                    typeAffichage = "text/json";
                } else {
                    typeAffichage = "text/html";
                }
                return new Object[] { result, linkerror };

            } else if (method == null && request.getAttribute("error") != null) {
                method = getMethod(incancier.getClass(), this.getMethode(map, "GET"));
                Parameter[] parameters = getParameters(method);
                Object[] resultat = processParameters(request, method, parameters, linkerror);
                Object[] processedParameters = (Object[]) resultat[0];
                linkerror = (String) resultat[1];
                System.out.println("processedParameters : " + processedParameters.length);
                Object result = invokeMethod(incancier, method, processedParameters);

                checkReturnType(result);
                if (checkRestApi(method)) {
                    result = checkView(result);
                    typeAffichage = "text/json";
                } else {
                    typeAffichage = "text/html";
                }
                return new Object[] { result, linkerror };
            } else {
                throw new Exception("Method not found");
            }
        } catch (FormException e) {
            List<ExpressException> exceptions = e.getExceptions();
            if (exceptions != null) {
                HashMap<String, HashMap<String, FieldException>> except = (HashMap<String, HashMap<String, FieldException>>)request.getAttribute("error");
                for (ExpressException exception : exceptions) { 
                    String cle = exception.getCle();
                    String message = exception.getMessage();
                    if (except.keySet().contains(cle)) {
                        HashMap<String, FieldException> excpt = except.get(cle);
                        int last = getLastErrorNum(excpt, cle);
                        excpt.put(cle + "_" + (last + 1), new FieldException(message,500));
                    }
                }
            }
        } 
        catch (InvocationTargetException e) {
            // Handle the InvocationTargetException
            Throwable targetException = e.getTargetException();
            if (targetException instanceof Exception) {
                throw (Exception) targetException;
            } else {
                throw new Exception("Error during method invocation", e);
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public int getLastErrorNum(HashMap<String, FieldException> exp,String clebase) {
        int num = 0;
        for (String cle : exp.keySet()) {
            String[] values = cle.split("_");
            String clebasic = values[0]+"_"+values[1];
            if (clebasic.equals(clebase)) {
                int last = Integer.parseInt(values[2]);
                num = last;
            }
        }
        return num;
    }

    private static Object createInstance(String className) throws Exception {
        Class<?> cls = Class.forName(className);
        return cls.getDeclaredConstructor().newInstance();
    }

    private static Method getMethod(Class<?> cls, String methodName) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    private static Method getMethodVerbnt(Class<?> cls, String methodName) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    private static Parameter[] getParameters(Method method) {
        return method.getParameters();
    }

    private Object[] processParameters(HttpServletRequest request, Method fonct, Parameter[] parameters,
            String linkerror) throws Exception {
        List<Object> processedParameters = new ArrayList<>();
        Object link = "";
        for (Parameter parameter : parameters) {
            if (parameter.getType().equals(Session.class)) {
                HttpSession session = request.getSession();
                Session sess = new Session(session);
                processedParameters.add(sess);
            } else if (parameter.getType().equals(FileMap.class) || parameter.getType().equals(FileMap[].class)) {
                Object[] files = chooseAmount(request, parameter);
                if (files.length > 1) {
                    processedParameters.add((FileMap[]) files);
                } else if (files.length == 1) {
                    processedParameters.add((FileMap) files[0]);
                }
            } else {
                AnnotParam annotation = parameter.getAnnotation(AnnotParam.class);
                if (annotation != null) {
                    // String value = request.getParameter(annotation.name());
                    Object processedValue = new Object();
                    // Class<?> type = parameter.getType();
                    Object[] result = checkCompatibilty(request, fonct, parameter, linkerror);

                    processedValue = result[0];
                    linkerror = (String) result[1];
                    link = linkerror;
                    System.out.println("lien error 2 " + linkerror + " identity" + System.identityHashCode(linkerror));
                    // System.out.println(processedValue.getClass());

                    processedParameters.add(processedValue);
                } else {
                    throw new Exception("Parameter without annotation found");
                }
            }
        }

        return new Object[] { processedParameters.toArray(), link };
    }

    public static boolean isPrimitiveOrWrapperOrString(Class<?> clazz) {
        return clazz == String.class ||
                clazz == Double.class ||
                clazz == Integer.class ||
                clazz == Boolean.class ||
                clazz == Long.class ||
                clazz == Float.class ||
                clazz == Short.class ||
                clazz == Byte.class ||
                clazz == Character.class ||
                clazz.isPrimitive();
    }

    public Object[] checkCompatibilty(HttpServletRequest request, Method fonct, Parameter param, String linkerror)
            throws Exception {
        Enumeration<String> urlParame = request.getParameterNames();
        ArrayList<String> urlParameters = Collections.list(urlParame);
        AnnotParam annotation = param.getAnnotation(AnnotParam.class);
        Class<?> claz = param.getType();
        String annotationValue = annotation.name();
        Object miverina = convertParameterValue(claz, request.getParameter(annotationValue));
        Object link = linkerror;
        Object obj = claz.getDeclaredConstructor().newInstance();
        HashMap<String, HashMap<String, FieldException>> except = null;
        for (String string : urlParameters) {
            String[] objet = string.split("_");
            System.out.println(string+" > "+objet[0]+" > "+annotation.name());

            if (objet.length > 1 && !isPrimitiveOrWrapperOrString(claz)) {
                String cible = objet[0];
                String attribut = objet[1];
                if ((annotationValue.contains(cible) && string.contains(cible))) {
                    String metteName = "set" + attribut.substring(0, 1).toUpperCase() + attribut.substring(1);
                    System.out.println("methode = " + metteName);
                    Class<?> fildtype = takeTypeField(claz, attribut);
                    Method met = claz.getMethod(metteName, fildtype);
                    Object valiny = request.getParameter(string);
                    System.out.println("valeur recue "+valiny+" parametre "+string);
                    try {
                        valiny = convertParameterValue(fildtype, (String) valiny);
                    } catch (Exception e) {
                        // throw new ClientException(e.getMessage(), 500);
                        e.printStackTrace();
                    }

                    try {
                        System.out.println("atoooooooooooooooooooooooooooooooooooooo");
                        // if (except != null) {
                        // for (String key : except.keySet()) {
                        // System.out.println("Exception : "+key+" value " +except.get(key));
                        // }
                        // } else
                        if (checkFieldConstraint(claz, attribut, valiny) != null) {
                            System.out.println(checkErrorLink(fonct) + " methode : " + fonct.getName());
                            if (checkErrorLink(fonct)) {
                                ErrorRedirect errorlink = fonct.getAnnotation(ErrorRedirect.class);
                                if (errorlink != null && errorlink.link() != null
                                        || errorlink.link() != "" && linkerror != "" || linkerror != null) {
                                    System.out.println("lien error 1 " + linkerror + " identity"
                                            + System.identityHashCode(linkerror));
                                    StringBuilder str = new StringBuilder("/");
                                    str.setLength(0);
                                    linkerror = str.append(errorlink.link()).toString();
                                    link = errorlink.link();
                                    System.out.println("lien error 1 " + linkerror + " identity"
                                            + System.identityHashCode(linkerror));
                                }
                            } else {
                                throw new ClientException("Lien de redirection d'erreur introuvable", 500);
                            }
                        }

                        if (except == null && checkFieldConstraint(claz, attribut, valiny) != null) {
                            except = new HashMap<>();
                            except.put("error_" + attribut, checkFieldConstraint(claz, attribut, valiny));
                        } else if (except != null && checkFieldConstraint(claz, attribut, valiny) != null) {
                            except.put("error_" + attribut, checkFieldConstraint(claz, attribut, valiny));
                        }
                        System.out.println("error_" + attribut + " ; " + checkFieldConstraint(claz, attribut, valiny));

                    } catch (ClientException ex) {
                        throw ex;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // if (except == null) {
                    System.out.println(
                            "value " + valiny + " class " + fildtype + " valiny" + valiny + " parametre " + string);
                    met.invoke(obj, valiny);
                    Method mette = claz
                            .getMethod("get" + attribut.substring(0, 1).toUpperCase() + attribut.substring(1));
                    System.out.println("value dans lobjet " + mette.invoke(obj));
                    miverina = obj;
                    // }
                }
            }
        }
        if (except != null) {
            request.setAttribute("error", except);
            request.setAttribute("has_error", true);
        }
        return new Object[] { miverina, linkerror };
    }

    private static Object[] chooseAmount(HttpServletRequest request, Parameter parametre) throws Exception {
        Object[] files = null;
        try {
            FileMap map = getFileMapinPart(request, parametre);
            List<FileMap> maps = getFileMapinParts(request, parametre);
            if (map == null && maps != null) {
                FileMap[] mapfile = maps.toArray(new FileMap[0]);
                files = new Object[mapfile.length];
                files = mapfile;
            } else if (map != null && maps == null) {
                files = new Object[1];
                files[0] = map;
            } else if (map == null && maps == null) {
                throw new ClientException("Aucun fichier uploader ou verifier la source", 404);
            }
        } catch (Exception e) {
            throw e;
        }
        return files;
    }

    private static FileMap getFileMapinPart(HttpServletRequest request, Parameter parametre) throws Exception {
        if (parametre.getType().equals(FileMap.class)) {
            AnnotParam annotation = parametre.getAnnotation(AnnotParam.class);
            Part partie = request.getPart(annotation.name());
            String nameFile = getFileName(partie);
            byte[] body = getByteFile(partie);
            return new FileMap(nameFile, body);
        }
        return null;
    }

    private static List<FileMap> getFileMapinParts(HttpServletRequest request, Parameter parametre) throws Exception {
        List<FileMap> mapfile = new ArrayList<>();
        if (parametre.getType().equals(FileMap[].class)) {
            for (Part partie : request.getParts()) {
                if (partie != null) {
                    String nameFile = getFileName(partie);
                    byte[] body = getByteFile(partie);
                    FileMap file = new FileMap(nameFile, body);
                    mapfile.add(file);
                }
            }
            return mapfile;
        }
        return null;
    }

    private static byte[] getByteFile(Part part) throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (InputStream fileContent = part.getInputStream()) {
            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            return buffer.toByteArray();
        }
    }

    private static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String content : contentDisposition.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private static Object invokeMethod(Object instance, Method method, Object[] parameters) throws Exception {
        System.out.println("parametre :" + parameters.length);
        for (Object object : parameters) {
            System.out.println("type " + object);
            // System.out.println("type "+object.getClass());
        }
        Parameter[] parameterse = method.getParameters();
        for (Parameter object : parameterse) {
            System.out.println("param " + object.getType());
        }
        return method.invoke(instance, parameters);
    }

    private static void checkReturnType(Object result) throws Exception {
        if (!(result instanceof String) && !(result instanceof ModelView)) {
            throw new Exception("Invalid return type");
        }
    }

    public static boolean isIN(String url, String key) {
        if (url.endsWith(key)) {
            return true;
        }
        return false;
    }

    public static boolean isIN(String url, Map<String, Mapping> key) throws Exception {
        String[] urlGroup = url.split("/");
        System.out.println("lien de redirection debug : "+url);
        for (String cle : key.keySet()) {
            if (cle.length() < url.length()) {
                if (url.endsWith(cle)) {
                    return true;
                }
            }
        }
        
        throw new ClientException("Page not found", 404, "Veuillez a informer votre administrateur");
    }

    public static boolean isIN(String url, String key, Mapping map) {
        try {
            String cls_name = map.getClassName();
            Class<?> cls = Class.forName(cls_name);
            ControllerSet annot = cls.getAnnotation(ControllerSet.class);
            int inddice = url.indexOf(annot.url());
            if (inddice != -1) {
                String url_suit = url.substring(inddice + annot.url().length());
                if (url_suit.equals(key)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<String> getErrorsByKey(
            HashMap<String, HashMap<String, FieldException>> errorMap,
            String key) {
        ArrayList<String> errorMessages = new ArrayList<>();

        if (errorMap != null && errorMap.containsKey(key)) {
            HashMap<String, FieldException> subMap = errorMap.get(key);

            if (subMap != null) {
                // Parcourir les sous-clés correspondantes ("key_0", "key_1", ...)
                for (int i = 0;; i++) {
                    String subKey = key + "_" + i;

                    // Si la sous-clé n'existe pas, arrêter la recherche
                    if (!subMap.containsKey(subKey)) {
                        break;
                    }

                    // Récupérer la liste des FieldException pour cette sous-clé
                    FieldException exceptions = subMap.get(subKey);
                    if (exceptions != null) {
                        errorMessages.add(exceptions.GETFULLMESSAGE());
                    }
                }
            }
        }

        return errorMessages;
    }

    public Map getInstance() {
        return instance;
    }

    public void setInstance(Map instance) {
        this.instance = instance;
    }

    public static String getReferer() {
        return referer;
    }

    public static void setReferer(String referer) {
        Scanner.referer = referer;
    }

    public String getProfilName() {
        return profilName;
    }

    public void setProfilName(String profilName) {
        this.profilName = profilName;
    }
}
