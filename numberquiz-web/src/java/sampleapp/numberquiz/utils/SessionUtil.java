package sampleapp.numberquiz.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Singleton utilizada na manipulação de dados na sessão.
 *
 * @author heverson.vasconcelos
 */
public class SessionUtil {

    /**
     * Armazena a sessão corrente.
     */
    private static HttpSession session;

    /**
     * Método para recuperar um objeto (atributo) previamente salvo na sessão.
     *
     * @param attributeName Nome do objeto (atributo) a ser recuperado.
     * @return Objeto recuperado.
     */
    public static Object getAttribute(String attributeName) {
        getRequestSession();
        return session.getAttribute(attributeName);
    }

    /**
     * Método para salvar um objeto (atributo) na sessão.
     *
     * @param attributeName Nome do objeto (atributo) a ser salvo.
     * @param obj Objeto a ser salvo.
     */
    public static void setAttribute(String attributeName, Object obj) {
        getSession();
        session.setAttribute(attributeName, obj);
    }

    /**
     * Método para destruir a sessão corrente.
     */
    public static void destroySession() {
        getSession();
        session.invalidate();
    }

    /**
     * Método para retornar a sessão corrente.
     */
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
