/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp.numberquiz.dao;

import sampleapp.numberquiz.entities.User;

/**
 *
 * @author heverson.vasconcelos
 */
public interface UserDao extends GenericDao<User, Integer> {

    public User getUserByName(String userName);

    public boolean verifyIfUserNameExists(String userName);
}
