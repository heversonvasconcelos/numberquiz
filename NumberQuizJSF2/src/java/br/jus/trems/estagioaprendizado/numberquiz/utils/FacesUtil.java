package br.jus.trems.estagioaprendizado.numberquiz.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

    public static void mensInfo(String message) {
        mensagem(message, FacesMessage.SEVERITY_INFO);
    }

    public static void mensErro(String message) {
        mensagem(message, FacesMessage.SEVERITY_ERROR);
    }

    public static void mensagem(String message,
            FacesMessage.Severity severity) {

        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, message, null));
    }
}