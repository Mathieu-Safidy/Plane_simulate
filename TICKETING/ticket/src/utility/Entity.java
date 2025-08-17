package utility;

// import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
// import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.PrimaryKey;
import utility.annotation.Table;

public abstract class Entity {

    private transient String tableName;
    private transient String[] columnList;
    private transient Class<?> cl;
    private transient Field[] fList;
    private transient String primaryKey;

    Properties properties = new Properties();
    public Connection connect() throws Exception {
        try {
            InputStream input = Entity.class.getClassLoader().getResourceAsStream("config/app.conf");
                properties.load(input);

            String user = properties.getProperty("username");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");

//            System.err.println("user "+user+" pass "+password +"url"+url);
            Class.forName("org.postgresql.Driver");

            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                return connection;
            } catch (SQLException e) {
                throw e;
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public static Connection staticConnection() throws Exception {
        Properties properties = new Properties();
        try {
            InputStream input = Entity.class.getClassLoader().getResourceAsStream("config/app.conf");
            properties.load(input);

            String user = properties.getProperty("username");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");

            Class.forName("org.postgresql.Driver");

            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                return connection;
            } catch (SQLException e) {
                throw e;
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public void initiate() throws Exception {
        this.cl = this.getClass();
        Table annotT = null;
        this.fList = cl.getDeclaredFields();
        List<String> list = new ArrayList<>();

        //initialisation du nom de la table
        if ((annotT = this.cl.getAnnotation(Table.class)) != null) {
            this.tableName = annotT.name();
        } else {
            this.tableName = cl.getSimpleName();
        }

        //initialisation des champs concerné
        for (Field f : fList) {
            Column annotC = f.getAnnotation(Column.class);
            if (this.isPrimaryColumn(f) != null) {
                System.out.println("colomn " + f.getName());
                this.primaryKey = this.isPrimaryColumn(f);
            }
            String tempName = "";
            if (annotC != null) {

                if (!annotC.name().equals("")) {
                    tempName = annotC.name();
                } else {
                    tempName = f.getName();
                }

            } else {
                tempName = f.getName();
            }
            //Verification 
            if (this.isValidColumn(f)) {
                list.add(tempName);
            }
        }
        this.columnList = list.toArray(new String[0]);
    }

    private static void setField(String fieldName, Object object, Object value) throws Exception {
        String fieldFormat = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        String methodName = String.format("set%s", fieldFormat);
        Method me = object.getClass().getMethod(methodName, value.getClass());
        me.invoke(object, value);
    }

    private static Object getField(String fieldName, Object object) throws Exception {
        String fieldFormat = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        String methodName = String.format("get%s", fieldFormat);
        Method me = object.getClass().getMethod(methodName);
        return me.invoke(object);
    }

    private static String findColumnName(Field f) throws Exception {
        Column annotC = f.getAnnotation(Column.class);
        if (annotC != null && !annotC.name().equals("")) {
            return annotC.name();
        } else {
            return f.getName();
        }
    }

    // private static Map<String,Object> findParameter(Object entity) throws Exception{
    //     Class<?> cla = entity.getClass();
    //     Field[] fl = cla.getDeclaredFields();
    //     Map<String,Object> res = new HashMap<>();
    //     for(Field f:fl){
    //         Object value = null;
    //         if((value = getField(f.getName(), entity)) != null){
    //             res.put(findColumnName(f), value);
    //         }
    //     }
    //     return res;
    // }
    private Map<String, Object> findParameter() throws Exception {
        Map<String, Object> res = new LinkedHashMap<>();
        for (Field f : fList) {
            Object value = null;
            if ((value = getField(f.getName(), this)) != null) {
                String colName = findColumnName(f);
                if (this.isValidColumn(f)) {
                    res.put(colName, value);
                }

            }
        }
        return res;
    }

    private boolean isValidColumn(Field f) throws Exception {
        if (f.getAnnotation(Ignore.class) != null) {
            return false;
        }
        return true;
    }

    private String isPrimaryColumn(Field f) throws Exception {
        PrimaryKey annotation = f.getAnnotation(PrimaryKey.class);
        if (annotation != null && annotation.name() != null) {
            return annotation.name();
        }
        return null;
    }

    private static String formatQuerySelect(String baseQuery, Map<String, Object> parameter) throws Exception {
        //on va prendre par exemple select * from identity where
        baseQuery += " where 1=1";
        for (String key : parameter.keySet()) {
            baseQuery += " and " + key + " = ?";
        }
        return baseQuery;

    }

    private static String formatInterval(ArrayList<String> column, ArrayList<Map<String, Object>> value) throws Exception {
        String res = "";
        int i = 0;
        for (String col : column) {
            if (!value.get(i).containsKey("max") && !value.get(i).containsKey("min")) {
                throw new Exception("Aucune valeur trouvée");
            }
            if (value.get(i).containsKey("max")) {
                System.out.println("max" + value.get(i).get("max")+" col "+col);
                res += " and " + col + " <= ?";
            }
            if (value.get(i).containsKey("min")) {
                System.out.println("min" + value.get(i).get("min")+ " col "+col);
                res += " and " + col + " >= ?";
            }
            i++;
        }
        return res;
    }

    private static String formatQueryInsert(String baseQuery, Map<String, Object> parameter) throws Exception {
        String columnList = "(";
        String parameterValues = "(";
        int i = 0;
        for (String key : parameter.keySet()) {
            columnList += key;
            parameterValues += "?";
            if (i < parameter.size() - 1) {
                columnList += ",";
                parameterValues += ",";
            }
            i++;
        }
        baseQuery += columnList + ") VALUES" + parameterValues + ")";
        return baseQuery;
    }

    private static String formatDeleteQuery(String baseQuery, Map<String, Object> parameter) throws Exception {
        String cond = " WHERE 1=1";
        for (String key : parameter.keySet()) {
            cond += " AND " + key + " = ?";
        }
        baseQuery += cond;
        return baseQuery;
    }

    private static String formatUpdateWhere(Map<String, Object> parameter) throws Exception {
        String cond = " WHERE 1=1";
        for (String key : parameter.keySet()) {
            cond += " AND " + key + " = ?";
        }
        return cond;
    }

//    private String formatUpdateValue() throws Exception{
//        String value = " SET ";
//        for(int i = 0; i<fList.length; i++){
//            String column = findColumnName(fList[i]);
//            value += column + " = ?";
//            if(i<fList.length - 1){
//                value += ",";
//            }
//        }
//        return value;
//    }
    private String formatUpdateValue(Map<String, Object> parameter) throws Exception {

        String value = " SET ";

        int i = 0;

        for (String key : parameter.keySet()) {

            value += key + " = ?";

            if (i < parameter.size() - 1) {

                value += ",";

            }

            i++;

        }

        return value;
    }

    public List<?> getAll() throws Exception {
        return getAll(this.getClass());
    }

    public <T extends Entity> List<T> getAll(Class<T> clazz) throws Exception {
        List<T> res = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        String query = String.format("select * from %s", this.tableName);

        try {
            con = this.connect();
            st = con.createStatement();
            rs = st.executeQuery(query);

//            Field[] dispo = getField(clazz.getDeclaredFields());
            while (rs.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                int i = 0;
                for (Field f : clazz.getDeclaredFields()) {
                    Object value = rs.getObject(columnList[i]);
                    if (value instanceof BigDecimal) {
                        value = ((BigDecimal) value).doubleValue();
                    }
                    f.setAccessible(true);
                    f.set(obj, value);
                    i++;
                    if (i >= columnList.length) {
                        break;
                    }
                }
                res.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return res;
    }

    private Field[] getField(Field[] all) {
        // Créer une liste temporaire pour stocker les champs correspondants
        List<Field> filteredFields = new ArrayList<>();

        // Parcourir chaque nom dans columnList
        for (String column : columnList) {
            // Rechercher un champ correspondant dans le tableau all
            for (Field field : all) {
                if (field.getName().equals(column)) {
                    filteredFields.add(field);
                    break; // Sortir de la boucle dès qu'une correspondance est trouvée
                }
            }
        }

        // Convertir la liste en tableau et la retourner
        return filteredFields.toArray(new Field[0]);
    }

    public Object[] getAllP() throws Exception {
        List<Object> res = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        String query = String.format("select * from %s", this.tableName);
        try {
            con = this.connect();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Object add = cl.getConstructor().newInstance();
                for (Field f : fList) {
                    Object value = null;
                    try {
                        value = rs.getObject(findColumnName(f));
                        if (value instanceof BigDecimal) {
                            double valeur = ((BigDecimal) value).doubleValue();
                            setField(f.getName(), add, valeur);
                        } else {
                            setField(f.getName(), add, value);
                        }
                    } catch (Exception e) {
                    }

                }
                res.add(add);
            }
            return res.toArray(new Object[0]);
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public Object[] getAllCustom(String sql) throws Exception {
        List<Object> res = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        String query = String.format(sql);
        try {
            con = this.connect();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Object add = cl.getConstructor().newInstance();
                for (Field f : fList) {
                    Object value = null;
                    try {
                        value = rs.getObject(findColumnName(f));
                        if (value instanceof BigDecimal) {
                            Object valeur = ((BigDecimal) value).doubleValue();
                            setField(f.getName(), add, valeur);
                        } else {
                            setField(f.getName(), add, value);
                        }
                    } catch (Exception e) {
                    }

                }
                res.add(add);
            }
            return res.toArray(new Object[0]);
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    List<Object> res = new ArrayList<>();

    public Object[] find(ArrayList<String> columnEcart, ArrayList<Map<String, Object>> compareValue) throws Exception {
        Map<String, Object> parameter = this.findParameter();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        String query = String.format("select * from %s", this.tableName);
        String interval = "";
        boolean hasInterval = false;
        if (columnEcart != null && compareValue != null) {
            hasInterval = true;
            interval = formatInterval(columnEcart, compareValue);
        }
        try {
            //formatage du query
            query = formatQuerySelect(query, parameter) + interval;
            System.out.println(query);
            con = this.connect();
            st = con.prepareStatement(query);
            int i = 1;
            for (String key : parameter.keySet()) {
                System.out.println(i+""+parameter.get(key));
                st.setObject(i, parameter.get(key));
                i++;
            }
            int j = 0;
            if (hasInterval) {
                    for(Map<String, Object> k : compareValue) {

                        if (compareValue.get(j).containsKey("max")) {
                            st.setObject(i, compareValue.get(j).get("max"));
                        i++;
                    }
                    if (compareValue.get(j).containsKey("min")) {
                        st.setObject(i, compareValue.get(j).get("min"));
                        i++;
                    }
                    j++;
                }
            }

            rs = st.executeQuery();
            while (rs.next()) {
                Object add = cl.getConstructor().newInstance();
                i = 0;
                for (Field f : fList) {
                    Object value = null;
                    try {
                        value = rs.getObject(findColumnName(f));
                        if (value instanceof BigDecimal) {
                            double valeur = ((BigDecimal) value).doubleValue();
                            setField(f.getName(), add, valeur);
                        } else {
                            System.out.println(f.getName()+" valeur : "+value);
                            setField(f.getName(), add, value);
                        }
                    } catch (Exception e) {
                    }

                    i++;
                }
                res.add(add);
            }
            return res.toArray(new Object[0]);
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean insert() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        Map<String, Object> parameter = this.findParameter();
        String query = String.format("INSERT INTO %s", this.tableName);
        try {
            query = formatQueryInsert(query, parameter);
            con = this.connect();
            ps = con.prepareStatement(query);
            int i = 1;
            for (String key : parameter.keySet()) {
                ps.setObject(i, parameter.get(key));
                i++;
            }
            ps.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return true;
    }

    public void insertWithConnection(Connection connect) throws Exception {
        Connection con = connect;
        PreparedStatement ps = null;
        Map<String, Object> parameter = this.findParameter();
        String query = String.format("INSERT INTO %s", this.tableName);
        try {
            query = formatQueryInsert(query, parameter);
            System.out.println("query comm "+query);
            if (con == null) {
                con = this.connect();
            }
            ps = con.prepareStatement(query);
            int i = 1;
            for (String key : parameter.keySet()) {
                ps.setObject(i, parameter.get(key));
                i++;
            }
            ps.executeUpdate();
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            throw new Exception("L'insertion dans la table " + this.tableName + " a echouer");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return;
    }
    public Object insertWithConnectionBack(Connection connect) throws Exception {
        Connection con = connect;
        PreparedStatement ps = null;
        Map<String, Object> parameter = this.findParameter();
        String query = String.format("INSERT INTO %s", this.tableName);
        Object object = null;
        try {
            query = formatQueryInsert(query, parameter);
            query = String.format(query+" RETURNING %s", this.primaryKey);
            System.out.println("query comm "+query);
            if (con == null) {
                con = this.connect();
            }
            ps = con.prepareStatement(query);
            int i = 1;
            for (String key : parameter.keySet()) {
                ps.setObject(i, parameter.get(key));
                i++;
            }
            // ps.executeUpdate();
            try (ResultSet rs = ps.executeQuery()) { // executeQuery pour RETURNING
                if (rs.next()) {
                    object = rs.getString(1); // Récupération de la clé générée
                } else {
                    throw new Exception("Aucune clé générée pour l'insertion dans la table " + this.tableName);
                }
            }
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            throw new Exception("L'insertion dans la table " + this.tableName + " a echouer");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return object;
    }

    public Object[] insertTrans() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        Map<String, Object> parameter = this.findParameter();

        String query = String.format("INSERT INTO %s ", this.tableName);
        
        Object[] data = new Object[2];
        try {
            query = formatQueryInsert(query, parameter);
            query = String.format(query+" RETURNING %s", this.primaryKey);
            System.out.println(query);
            con = this.connect();
            ps = con.prepareStatement(query);
            int i = 1;
            for (String key : parameter.keySet()) {
                ps.setObject(i, parameter.get(key));
                i++;
            }
            try (ResultSet rs = ps.executeQuery()) { // executeQuery pour RETURNING
                if (rs.next()) {
                    data[1] = rs.getString(1); // Récupération de la clé générée
                } else {
                    throw new Exception("Aucune clé générée pour l'insertion dans la table " + this.tableName);
                }
            }

            data[0] = con;
        } catch (Exception e) {
            throw e;
//            throw new Exception("L'insertion dans la table " + this.tableName + " a echouer");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return data;
    }

    public static String formatFunctionQuery(String baseQuery, ArrayList<Object> arguments) throws Exception {
        baseQuery += "(";
        for (int i = 0; i < arguments.size(); i++) {
            baseQuery += "?";
            if (i < arguments.size() - 1) {
                baseQuery += ",";
            }
        }
        baseQuery += ")";
        return baseQuery;
    }

    public static ArrayList<Map<String, Object>> executeFunction(String functionName, ArrayList<Object> arguments, String valueName) throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Map<String, Object>> res = new ArrayList<>();
        String query = "SELECT * FROM " + functionName;
        try {
            query = formatFunctionQuery(query, arguments);
            con = staticConnection();
            stm = con.prepareStatement(query);
            for (int i = 1; i <= arguments.size(); i++) {
                stm.setObject(i, arguments.get(i - 1));
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> add = new HashMap<>();
                add.put(valueName, rs.getObject(valueName));
                res.add(add);
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return res;
    }

    public void delete() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        Map<String, Object> parameter = this.findParameter();
        String query = "DELETE FROM " + tableName;
        try {
            query = formatDeleteQuery(query, parameter);
            con = this.connect();
            stm = con.prepareStatement(query);
            int i = 1;
            for (String key : parameter.keySet()) {
                stm.setObject(i, parameter.get(key));
                i++;
            }
            stm.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public void delete(Connection connection) throws Exception {
        Connection con = connection;
        PreparedStatement stm = null;
        Map<String, Object> parameter = this.findParameter();
        String query = "DELETE FROM " + tableName;
        try {
            query = formatDeleteQuery(query, parameter);
            con = this.connect();
            stm = con.prepareStatement(query);
            int i = 1;
            for (String key : parameter.keySet()) {
                stm.setObject(i, parameter.get(key));
                i++;
            }
            stm.executeUpdate();
            con.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
    }
    public Object[] deleteTrans() throws Exception {
        Connection con = null;
        PreparedStatement stm = null;
        Map<String, Object> parameter = this.findParameter();
        String query = "DELETE FROM " + tableName;
        
        Object[] data = new Object[2];
        try {
            query = formatDeleteQuery(query, parameter);
            con = this.connect();
            stm = con.prepareStatement(query);
            int i = 1;
            for (String key : parameter.keySet()) {
                stm.setObject(i, parameter.get(key));
                i++;
            }
            stm.executeUpdate();
            data[0] = con;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return data;
    }

//    public void update(Map<String, Object> parameter) throws Exception {
//        Connection con = null;
//        PreparedStatement stm = null;
//        String query = "UPDATE " + tableName;
//        try {
//            query += " " + this.formatUpdateValue() + " " + formatUpdateWhere(parameter);
//            con = this.connect();
//            stm = con.prepareStatement(query);
//            int i = 1;
//            for (Field f : fList) {
//                stm.setObject(i, getField(f.getName(), this));
//                i++;
//            }
//            for (String key : parameter.keySet()) {
//                stm.setObject(i, parameter.get(key));
//            }
//            stm.executeUpdate();
//            con.commit();
//        } catch (Exception e) {
//            con.rollback();
//            throw e;
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//    }
    public void update(Map<String, Object> parameter) throws Exception {

        Connection con = null;

        PreparedStatement stm = null;

        Map<String, Object> param = this.findParameter();

        String query = "UPDATE " + tableName;

        try {

            query += this.formatUpdateValue(param) + formatUpdateWhere(parameter);

            System.out.println(query);

            con = this.connect();

            stm = con.prepareStatement(query);

            int i = 1;

            for (String key : param.keySet()) {

                stm.setObject(i, param.get(key));

                i++;

            }

            for (String key : parameter.keySet()) {

                stm.setObject(i, parameter.get(key));

                i++;

            }

            stm.executeUpdate();

            con.commit();

        } catch (Exception e) {

            if (con != null) {
                con.rollback();
            }

            throw e;

        } finally {

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
}
