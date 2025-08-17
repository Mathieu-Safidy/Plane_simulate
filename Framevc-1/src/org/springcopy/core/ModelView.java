package org.springcopy.core;

import java.util.HashMap;

public class ModelView {
    String url;
    HashMap<String,Object> data = new HashMap<String,Object>();
    byte[] body;
    String filename;
    
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    boolean download;
    
    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

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