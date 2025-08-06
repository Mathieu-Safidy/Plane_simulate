package org.springcopy.core;

import org.springcopy.exception.ClientException;

import jakarta.servlet.http.HttpSession;

public class Session {
    HttpSession session;
    

    public Session(HttpSession session) {
        this.session = session;
    }

    public Object getSession(String key) throws ClientException {
        try {
            if (session.getAttribute(key) == null) {
                throw new ClientException("La session avec la cle '"+key+"' n'existe pas", 404, "veuillez votre administrateur");
            }
            return session.getAttribute(key);
        } catch (ClientException e) {
            throw e;
        }
    }

    public void setSession(String key, Object value) {
        session.setAttribute(key, value);
    }

    public void deleteSessionAttribute(String key){
        session.removeAttribute(key);
    }

    public void deleteSession() {
        session.invalidate();
    }    

   

}
