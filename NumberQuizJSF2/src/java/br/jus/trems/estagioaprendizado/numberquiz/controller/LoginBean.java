/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.controller;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.UserDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import br.jus.trems.estagioaprendizado.numberquiz.utils.FacesUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author heverson.vasconcelos
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private User user;
    private User loggedUser;
    public UserDaoImpl userDaoImpl;

    public LoginBean() {
        user = new User();
        userDaoImpl = new UserDaoImpl();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return user.getName();
    }

    public void setUserName(String name) {
        this.user.setName(name);
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(String password) {
        this.user.setPassword(password);
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    /**
     * Metodo para verificar o login do usuario
     * 
     * @return
     */
    public String verifyLogin() {
        User userFromDataBase = userDaoImpl.getUserByName(getUserName());

        if ((userFromDataBase != null)
                && (userFromDataBase.getPassword().compareTo(getUserPassword()) == 0)) {
            loggedUser = user;
            return "numberquiz?faces-redirect=true";
        } else {
            FacesUtil.mensErro("Senha inválida.");
            return null;
        }
    }

    public String logout() {
        user = new User();
        loggedUser = null;

        return "index";
    }
}
