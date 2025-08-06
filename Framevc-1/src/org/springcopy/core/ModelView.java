package org.springcopy.core;

import java.util.HashMap;

public class ModelView {
    String url;
    HashMap<String,Object> data = new HashMap<String,Object>();


    public ModelView(String url) {
        this.url = "/page/"+url;
    }

    public void addObject(String nomvariable, Object data){
        this.data.put(nomvariable, data);
    }
    public HashMap<String,Object> getData (){
        return data;
    }
    public String getUrl(){
        return url;
    }

}