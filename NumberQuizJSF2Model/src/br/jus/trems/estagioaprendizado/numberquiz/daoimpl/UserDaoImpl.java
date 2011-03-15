package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import br.jus.trems.estagioaprendizado.numberquiz.utils.EntityManagerUtil;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author heverson.vasconcelos
 */
public class UserDaoImpl extends DaoImpl<User> {

    @Override
    public Class<User> getDomainClass() {
        return User.class;
    }

    public User getUserByName(String userName) {
        Query query = EntityManagerUtil.getEntityManager().createNamedQuery("User.findByUserName");
        query.setParameter("userName", userName);

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    public boolean verifyIfUserNameExists(String userName) {
        return (getUserByName(userName) != null) ? true : false;
    }
}
