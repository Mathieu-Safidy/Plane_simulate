package org.springcopy.exception;

public class ExpressException extends Exception {
     
    String cle;

    public ExpressException(String message) {
        super(message);
    }

    public ExpressException(String cle, String message) {
        super(message);
        setCle(cle);
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

}
