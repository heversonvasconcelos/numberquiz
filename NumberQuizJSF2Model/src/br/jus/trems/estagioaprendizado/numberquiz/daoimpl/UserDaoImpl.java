package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.dao.UserDao;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Classe concreta que será utilizada na execução de todas as operações de
 * persistência relativas a entidade User.
 * 
 * @author heverson.vasconcelos
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

    @Override
    public Class<User> getDomainClass() {
        return User.class;
    }

    /**
     * Método para consultar um usuário a partir de um nome de login.
     * Obs.: Utiliza a NamedQuery User.findByUserName descrita na classe User.
     *
     * @param userName Nome de login a ser buscado.
     * @return Usuário contendo o nome userName. <br>
     *         Null caso não exista um usuário contendo o nome userName.
     */
    public User getUserByName(String userName) {
        Query query = getEntityManager().createNamedQuery("User.findByUserName");
        query.setParameter("userName", userName);

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    /**
     * Método para verificar se existe um usuário com um determinado nome de login.
     *
     * @param userName Nome de login a ser buscado.
     * @return True caso exista um usuário contendo o nome de login.
     */
    public boolean verifyIfUserNameExists(String userName) {
        return (getUserByName(userName) != null) ? true : false;
    }
}
