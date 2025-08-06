package org.springcopy.core;

public class PathProcess {
    
    
    public static String getTarget(String path){
        if (path.split("/").length > 2) {
            return "/"+path.split("/")[path.split("/").length-1];
        }
        return "";
    }

    public static String getDomain(String path){
        if (path.split("/").length > 3) {
            return "/"+path.split("/")[path.split("/").length-2];  
        }  
        return "";
    }
}
