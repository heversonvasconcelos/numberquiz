/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.controller;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.UserDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import br.jus.trems.estagioaprendizado.numberquiz.utils.Constants;
import br.jus.trems.estagioaprendizado.numberquiz.utils.FacesUtil;
import br.jus.trems.estagioaprendizado.numberquiz.utils.SessionUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author heverson.vasconcelos
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    private User user;
    private User authenticatedUser;
    /* Utils */
    private UserDaoImpl userDaoImpl;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userToauthenticate) {
        this.user = userToauthenticate;
    }

    public User getAuthenticatedUser() {
        authenticatedUser = (User) SessionUtil.getAttribute("authenticatedUser");

        return (authenticatedUser != null) ? authenticatedUser : null;
    }

    /**
     * Metodo para verificar o login do usuario
     *
     * @return
     */
    private User verifyLogin() {
        userDaoImpl = new UserDaoImpl();

        return (userDaoImpl.getUserByName(user.getName()));
    }

    /**
     * Metodo para verificar a senha do usuario
     *
     * @return
     */
    private boolean verifyPassword() {
        if ((authenticatedUser.getPassword().compareTo(user.getPassword()) == 0)) {
            return true;
        }

        return false;
    }

    public String login() {
        authenticatedUser = verifyLogin();

        if (authenticatedUser == null) {
            FacesUtil.mensErro(Constants.MSG_INVALID_USER);
            return null;
        } else if (!verifyPassword()) {
            authenticatedUser = null;
            FacesUtil.mensErro(Constants.MSG_INVALID_PASSWORD);
            return null;
        }

        SessionUtil.setAttribute("authenticatedUser", authenticatedUser);
        return Constants.PAGE_NUMBERQUIZ;
    }

    public String logout() {
        user = new User();
        authenticatedUser = null;
        SessionUtil.destroySession();

        return Constants.PAGE_INDEX;
    }

    public String newUser() {
        userDaoImpl = new UserDaoImpl();

        if (userDaoImpl.verifyIfUserNameExists(user.getName())) {
            FacesUtil.mensErro(Constants.MSG_USER_ALREADY_EXISTS);
            return null;
        }

        userDaoImpl.create(user);
        authenticatedUser = user;
        SessionUtil.setAttribute("authenticatedUser", authenticatedUser);

        return Constants.PAGE_NUMBERQUIZ;
    }

    public String verifyAuthenticatedUser() {
        authenticatedUser = (User) SessionUtil.getAttribute("authenticatedUser");

        if (authenticatedUser == null) {
            FacesUtil.mensErro(Constants.MSG_INVALID_USER);
            return Constants.PAGE_USER_ISNOT_LOGGED;
        }

        return null;
    }
}
