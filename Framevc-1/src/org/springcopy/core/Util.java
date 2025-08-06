package org.springcopy.core;

import java.util.ArrayList;

public class Util {
    public static String genererList(ArrayList<String> list) {
        String script = "";
        script+="<ul>";
        for (String string : list) {
            script+="<li>";
            script+=string;
            script+="</li>";
        }
        script+="</ul>";
        return script;
    }
}
