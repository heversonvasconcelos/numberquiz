/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author heverson.vasconcelos
 */
public class SessionUtil {

    private static HttpSession session;

    public static Object getAttribute(String attributeName) {
        getRequestSession();
        return session.getAttribute(attributeName);
    }

    public static void setAttribute(String attributeName, Object obj) {
        getSession();
        session.setAttribute(attributeName, obj);
    }

    public static void destroySession() {
        getSession();
        session.invalidate();
    }

    private static void getSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
    }

    private static void getRequestSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        session = request.getSession();
    }
}
