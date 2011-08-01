package br.jus.trems.estagioaprendizado.numberquiz.controller;

import br.jus.trems.estagioaprendizado.numberquiz.dao.UserDao;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import br.jus.trems.estagioaprendizado.numberquiz.utils.Constants;
import br.jus.trems.estagioaprendizado.numberquiz.utils.FacesUtil;
import br.jus.trems.estagioaprendizado.numberquiz.utils.SessionUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean gerenciável utilizado no controle do login de usuários na aplicação.
 * Este controle envolve principalmente:
 *                          verificação do login e senha;
 *                          login e logout;
 *                          cadastro de um novo usuário;
 *                          verificar se o usuário está autenticado e logado;
 *
 * @author heverson.vasconcelos
 */
@Controller("loginBean")
@Scope("session")
public class LoginBean implements Serializable {

    /**
     * Armazena o usuário corrente
     */
    private User user;
    /**
     * Armazena o usuário corrente após a autenticação, ou seja, o usuário
     * logado na sessão.
     */
    private User authenticatedUser;
    private final String sessionAttributeName = "authenticatedUser";
    /**
     * Variável utilizada nos métodos que irão inserir ou consultar alguma
     * informação relativa aos usuários.
     */
    @Resource
    private UserDao userDao;

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

    /**
     * Método que retorna o usuário que foi previamente autenticado e
     * está logado na sessão.
     *
     * @return Usuário autenticado.
     */
    public User getAuthenticatedUser() {
        authenticatedUser = (User) SessionUtil.getAttribute(sessionAttributeName);

        return (authenticatedUser != null) ? authenticatedUser : null;
    }

    /**
     * Método para verificar o login do usuário.
     * Consulta um determinado a partir do nome de login.
     *
     * @return Usuário encontrado. Null caso o usuário não seja encontrado.
     */
    private User verifyLogin() {

        return (userDao.getUserByName(user.getName()));
    }

    /**
     * Método para verificar a senha do usuário.
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
     * Método para autenticar o usuário. Verifica o login e a senha deste.
     *
     * @return String contendo o endereço de redirecionamento caso o usuário foi
     *          corretamente autenticado. Neste caso será redirecionado para o
     *          início do jogo (numberquiz.xhtml) <br>
     *          Null caso houver um erro na autenticação.
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
     * Método para executar o logout da sessão.
     *
     * @return String contendo o endereço de redirecionamento para a página
     *          inicial (index.xhtml).
     */
    public String logout() {
        user = new User();
        authenticatedUser = null;
        SessionUtil.destroySession();

        return Constants.PAGE_INDEX;
    }

    /**
     * Método para cadastrar um novo usuário.
     *
     * @return String contendo o endereço de redirecionamento para início do
     *          jogo (numberquiz.xhtml). <br>
     *          Null caso exista um usuário previamente cadastrado com o mesmo
     *          nome de login que o usuário corrente.
     */
    public String newUser() {

        if (userDao.verifyIfUserNameExists(user.getName())) {
            FacesUtil.mensErro(Constants.MSG_USER_ALREADY_EXISTS);
            return null;
        }

        userDao.create(user);

        authenticatedUser = user;
        SessionUtil.setAttribute(sessionAttributeName, authenticatedUser);


        return Constants.PAGE_NUMBERQUIZ;
    }

    /**
     * Método para verificar se o usuário foi autenticado e está logado.
     * 
     * @return String contendo o endereço de redirecionamento para a página
     *          informando ao (userisnotlogged.xhtml). <br>
     *          Null caso exista um usuário previamente cadastrado com o mesmo
     *          nome de login que o usuário corrente.
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
