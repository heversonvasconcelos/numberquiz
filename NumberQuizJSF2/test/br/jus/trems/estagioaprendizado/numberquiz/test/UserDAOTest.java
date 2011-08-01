package br.jus.trems.estagioaprendizado.numberquiz.test;

import br.jus.trems.estagioaprendizado.numberquiz.dao.UserDao;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author heverson.vasconcelos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public final class UserDAOTest {

    @Resource
    private UserDao userDao;

    @Test
    public void testLoadService() {
        Assert.assertNotNull(userDao);

    }

    @Test
    public void testListAllUsers() {
        List<User> userList = userDao.list();

        printListManifestacao(userList);
    }

    @Test
    public void testCreatANewUser() {
        User user = new User();
        user.setName("mario");
        user.setPassword("mario");

        userDao.create(user);
    }

    public void printListManifestacao(List<User> userList) {
        System.out.println("----------------------------------------------");
        for (Iterator<User> it = userList.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
    }
}
