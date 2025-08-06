package org.springcopy.exception;

import java.util.ArrayList;
import java.util.List;

public class FormException extends Exception {
   
    List<ExpressException> exceptions;

    public FormException(List<ExpressException> exceptions) {
        super();
        this.exceptions = exceptions;
    }

    public List<ExpressException> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<ExpressException> exceptions) {
        this.exceptions = exceptions;
    }
    
    public void addException(ExpressException exception) {
        if (this.exceptions != null) {
            this.exceptions.add(exception);
        }else {
            this.exceptions = new ArrayList<>();
            this.exceptions.add(exception);
        }
    } 
}
