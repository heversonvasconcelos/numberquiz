package sampleapp.numberquiz.ui.test;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import sampleapp.numberquiz.model.dao.UserDao;
import sampleapp.numberquiz.model.entity.User;

@ContextConfiguration("classpath:application-test-context.xml")
public class UserDAOTest extends AbstractJUnit4SpringContextTests {

	@Inject
	private UserDao userDao;

	@Test
	public void testLoadService() {
		Assert.assertNotNull(userDao);
	}

	// @Test
	public void testListAllUsers() {
		List<User> userList = userDao.list();

		printUserList(userList);
	}

	// @Test
	public void testCreatANewUser() {
		User user = new User();
		user.setName("joao");
		user.setPassword("joao");

		userDao.create(user);

		Assert.assertNotNull(user.getId());
	}

	public void printUserList(List<User> userList) {
		System.out.println("----------------------------------------------");
		for (Iterator<User> it = userList.iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
	}
}
