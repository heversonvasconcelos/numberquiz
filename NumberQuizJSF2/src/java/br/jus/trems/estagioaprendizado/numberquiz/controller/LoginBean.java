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
 * Bean gerenci�vel utilizado no controle do login de usu�rios na aplica��o.
 * Este controle envolve principalmente:
 *                          verifica��o do login e senha;
 *                          login e logout;
 *                          cadastro de um novo usu�rio;
 *                          verificar se o usu�rio est� autenticado e logado;
 *
 * @author heverson.vasconcelos
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    /**
     * Armazena o usu�rio corrente
     */
    private User user;
    /**
     * Armazena o usu�rio corrente ap�s a autentica��o, ou seja, o usu�rio
     * logado na sess�o.
     */
    private User authenticatedUser;
    private final String sessionAttributeName = "authenticatedUser";
    /**
     * Vari�vel utilizada nos m�todos que ir�o inserir ou consultar alguma
     * informa��o relativa aos usu�rios.
     */
    private UserDaoImpl userDaoImpl;

    @PostConstruct
    public void init() {
        user = new User();
        userDaoImpl = new UserDaoImpl();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userToauthenticate) {
        this.user = userToauthenticate;
    }

    /**
     * M�todo que retorna o usu�rio que foi previamente autenticado e
     * est� logado na sess�o.
     *
     * @return Usu�rio autenticado.
     */
    public User getAuthenticatedUser() {
        authenticatedUser = (User) SessionUtil.getAttribute(sessionAttributeName);

        return (authenticatedUser != null) ? authenticatedUser : null;
    }

    /**
     * M�todo para verificar o login do usu�rio.
     * Consulta um determinado a partir do nome de login.
     *
     * @return Usu�rio encontrado. Null caso o usu�rio n�o seja encontrado.
     */
    private User verifyLogin() {

        return (userDaoImpl.getUserByName(user.getName()));
    }

    /**
     * M�todo para verificar a senha do usu�rio.
     *
     * @return True caso a senha esteja correta.
     */
    private boolean verifyPassword() {
        if ((authenticatedUser.getPassword().compareTo(user.getPassword()) == 0)) {
            return true;
        }

        return false;
    }

    /**
     * M�todo para autenticar o usu�rio. Verifica o login e a senha deste.
     *
     * @return String contendo o endere�o de redirecionamento caso o usu�rio foi
     *          corretamente autenticado. Neste caso ser� redirecionado para o
     *          in�cio do jogo (numberquiz.xhtml) <br>
     *          Null caso houver um erro na autentica��o.
     */
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

        SessionUtil.setAttribute(sessionAttributeName, authenticatedUser);

        return Constants.PAGE_NUMBERQUIZ;
    }

    /**
     * M�todo para executar o logout da sess�o.
     *
     * @return String contendo o endere�o de redirecionamento para a p�gina
     *          inicial (index.xhtml).
     */
    public String logout() {
        user = new User();
        authenticatedUser = null;
        SessionUtil.destroySession();

        userDaoImpl.finalizeAccess();

        return Constants.PAGE_INDEX;
    }

    /**
     * M�todo para cadastrar um novo usu�rio.
     *
     * @return String contendo o endere�o de redirecionamento para in�cio do
     *          jogo (numberquiz.xhtml). <br>
     *          Null caso exista um usu�rio previamente cadastrado com o mesmo
     *          nome de login que o usu�rio corrente.
     */
    public String newUser() {

        if (userDaoImpl.verifyIfUserNameExists(user.getName())) {
            FacesUtil.mensErro(Constants.MSG_USER_ALREADY_EXISTS);
            return null;
        }

        userDaoImpl.create(user);
        authenticatedUser = user;
        SessionUtil.setAttribute(sessionAttributeName, authenticatedUser);

        return Constants.PAGE_NUMBERQUIZ;
    }

    /**
     * M�todo para verificar se o usu�rio foi autenticado e est� logado.
     * 
     * @return String contendo o endere�o de redirecionamento para a p�gina
     *          informando ao (userisnotlogged.xhtml). <br>
     *          Null caso exista um usu�rio previamente cadastrado com o mesmo
     *          nome de login que o usu�rio corrente.
     */
    public String verifyAuthenticatedUser() {
        authenticatedUser = (User) SessionUtil.getAttribute(sessionAttributeName);

        if (authenticatedUser == null) {
            FacesUtil.mensErro(Constants.MSG_INVALID_USER);
            return Constants.PAGE_USER_ISNOT_LOGGED;
        }

        return null;
    }
}
