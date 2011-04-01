package br.jus.trems.estagioaprendizado.numberquiz.test;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.UserDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author heverson.vasconcelos
 */
public class UserDAOTest {

    private Random rd = new Random(Calendar.getInstance().getTimeInMillis());
    private UserDaoImpl userDaoImpl = new UserDaoImpl();

    public User createAUser() {
        User user = new User();
        Long userIdentification = Math.abs(rd.nextLong());

        user.setName("User_" + userIdentification.toString().substring(0, 15));
        user.setPassword(userIdentification.toString().substring(0, 10));

        return user;
    }

    public User getAUser() {
        List<User> userList = userDaoImpl.list();

        if (userList.size() > 0) {
            int userListIndex = Math.abs(rd.nextInt() % userList.size());
            return userList.get(userListIndex);
        }

        return null;
    }

    @Test
    public void create() {
        User user = new User();
        User admin = new User();

        user.setName("user");
        user.setPassword("user");
        admin.setName("admin");
        admin.setPassword("admin");

        if (!userDaoImpl.verifyIfUserNameExists(user.getName())) {
            userDaoImpl.create(user);
        }
        if (!userDaoImpl.verifyIfUserNameExists(admin.getName())) {
            userDaoImpl.create(admin);
        }
    }

    //@Test
    public void createALotOfUsers() {
        int numberOfUsers = Math.abs(rd.nextInt() % 30);
        User user;

        for (int i = 0; i < numberOfUsers; i++) {
            user = createAUser();

            if (!userDaoImpl.verifyIfUserNameExists(user.getName())) {
                userDaoImpl.create(user);
            } else {
                System.out.println("Elemento existente: "
                        + user.getId() + " - " + user.getName() + " - " + user.getPassword());
            }
        }
    }

    /*
    @Test
    public void remove() {
    List<User> userList = userDaoImpl.list();

    if (userList.size() > 0) {
    int userListIndex = Math.abs(rd.nextInt() % userList.size());

    userDaoImpl.delete(userList.get(userListIndex));
    }
    }
    
    //@Test
    public void removeAll() {
    List<User> userList = userDaoImpl.list();

    for (User u : userList) {
    userDaoImpl.delete(u);
    }
    }
     *
     */
    @Test
    public void list() {
        List<User> userList = userDaoImpl.list();

        for (User u : userList) {
            System.out.println(u.toString());
        }
    }

    @Test
    public void listAUser() {
        User userToFind = getAUser();
        User userFound = userToFind != null ? userDaoImpl.getUserByName(userToFind.getName()) : null;

        if (userFound != null) {
            System.out.println("User to find: " + userToFind.toString());
            System.out.println("User found: " + userFound.toString());
        } else {
            System.out.println("User not found!");
        }

    }
}
