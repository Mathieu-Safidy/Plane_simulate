package org.springcopy.exception;

public class FieldException extends Exception{
    int code;
    String consigne;
    public FieldException (String message, int code){
        super(message);
        setCode(code);
    }
    public FieldException (String message, int code, String consigne){
        super(message);
        setCode(code);
        setConsigne(consigne);
    }
    public int getCode(){
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }
    public void setConsigne(String consigne){
        this.consigne = consigne;
    }
    public String getConsigne(){
        return this.consigne;
    }
    public String GETFULLMESSAGE(){
        return this.getMessage()+" ; code : "+this.getCode()+"\n"+this.getConsigne();
    }
}
