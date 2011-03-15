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

    private String userName = new String();
    private String userPassword;
    public UserDaoImpl userDaoImpl = new UserDaoImpl();

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }

    /**
     * Metodo para verificar o login do usuario
     * 
     * @return
     */
    public String verifyLogin() {
        User user = userDaoImpl.getUserByName(userName);

        if ((user != null) && (user.getPassword().compareTo(userPassword) == 0)) {
            return "numberquiz?faces-redirect=true";
        } else {
            FacesUtil.mensErro("Senha inválida.");
            return null;
        }
    }
}
