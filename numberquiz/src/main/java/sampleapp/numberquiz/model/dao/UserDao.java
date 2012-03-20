package sampleapp.numberquiz.model.dao;

import sampleapp.numberquiz.model.entity.User;

/**
 *
 * @author heverson.vasconcelos
 */
public interface UserDao extends GenericDao<User, Integer> {

    public User getUserByName(String userName);

    public boolean verifyIfUserNameExists(String userName);
}
