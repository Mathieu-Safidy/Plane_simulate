package org.springcopy.core;

import java.util.Objects;

public class VerbAction {
    String method;
    String verb;
    String link;

    
    public VerbAction(String method, String verb) {
        this.method = method;
        this.verb = verb;
    }
    public VerbAction() {}

    public String getMethod() {
        return this.method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getVerb() {
        return verb;
    }
    public void setVerb(String verb) {
        this.verb = verb;
    }

    @Override
    public boolean equals(Object obj) {
        VerbAction vrb = (VerbAction)obj;
        if (this.getVerb() == vrb.getVerb()) {
            throw new IllegalArgumentException("Le meme verb avec une meme lien nest pas austoriser");
        }   
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(method,verb);
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    
    
}
