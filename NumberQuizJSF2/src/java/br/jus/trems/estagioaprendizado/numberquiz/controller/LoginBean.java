/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.controller;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.UserDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import br.jus.trems.estagioaprendizado.numberquiz.utils.FacesUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author heverson.vasconcelos
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    private User userToauthenticate;
    private User authenticatedUser;
    /* Utils */
    private UserDaoImpl userDaoImpl;
    private HttpSession session;

    @PostConstruct
    public void init() {
        userToauthenticate = new User();
    }

    public User getUserToauthenticate() {
        return userToauthenticate;
    }

    public void setUserToauthenticate(User userToauthenticate) {
        this.userToauthenticate = userToauthenticate;
    }

    public User getAuthenticatedUser() {
        getRequestSession();
        authenticatedUser = (User) session.getAttribute("authenticatedUser");

        return (authenticatedUser != null) ? authenticatedUser : null;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    /**
     * Metodo para verificar o login do usuario
     *
     * @return
     */
    public User verifyLogin() {
        userDaoImpl = new UserDaoImpl();
        User userFromDataBase = userDaoImpl.getUserByName(userToauthenticate.getName());

        if ((userFromDataBase != null)
                && (userFromDataBase.getPassword().compareTo(userToauthenticate.getPassword()) == 0)) {
            return userFromDataBase;
        }
        return null;
    }

    public String login() {
        authenticatedUser = verifyLogin();

        if (authenticatedUser != null) {
            getSession();
            session.setAttribute("authenticatedUser", authenticatedUser);
            return "numberquiz";
        } else {
            FacesUtil.mensErro("Senha inválida.");
            return null;
        }
    }

    public String logout() {
        userToauthenticate = new User();
        authenticatedUser = null;
        destroySession();

        return "index";
    }

    private void getSession() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
    }

    private void getRequestSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        session = request.getSession();
    }

    private void destroySession() {
        getSession();
        session.setAttribute("authenticatedUser", null);
        session.invalidate();

    }
}
