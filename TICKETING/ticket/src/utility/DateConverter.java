package utility;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public static Timestamp stringToTimestamp(String date) throws Exception{
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        java.util.Date convertValue = dateTimeFormat.parse(date);
        Timestamp timestampValue = new Timestamp(convertValue.getTime());

        return timestampValue;
    }
    public static String timestampToString(Timestamp date) throws Exception{
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String stringValue = dateTimeFormat.format(date);

        return stringValue;
    }

    public static String dateToString(Date date) throws Exception{
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String stringValue = dateTimeFormat.format(date);

        return stringValue;
    }
    public static Date stringToDate(String date) throws Exception{
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        java.util.Date convertValue = dateTimeFormat.parse(date);
        Date dateValue = new Date(convertValue.getTime());

        return dateValue;
    }
    public static String getYear(String date) throws Exception{
        String[] dateTimePart = date.split(" ");
        String[] datePart = dateTimePart[0].split("-");
        return datePart[0];
    }
    public static Date convertDate(String date) throws Exception{
        Date res = null;
        // Supprimer le 'Z' à la fin et parser la date avec DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime localDateTime = LocalDateTime.parse(date.replace("Z", ""), formatter);
        
        // Convertir LocalDateTime en LocalDate
        LocalDate localDate = localDateTime.toLocalDate();
        
        // Convertir LocalDate en java.sql.Date
        res = Date.valueOf(localDate);
        
       return res;
    }

}
