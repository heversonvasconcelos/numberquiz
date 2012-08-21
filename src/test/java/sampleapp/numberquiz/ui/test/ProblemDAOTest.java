package sampleapp.numberquiz.ui.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import sampleapp.numberquiz.model.dao.ProblemDao;
import sampleapp.numberquiz.model.entity.Problem;

/**
 * 
 * @author heverson.vasconcelos
 */
@ContextConfiguration("classpath:application-test-context.xml")
public class ProblemDAOTest extends AbstractJUnit4SpringContextTests {

	@Inject
	private ProblemDao problemDao;

	@Test
	public void list() {
		List<Problem> problemList = problemDao.list();

		for (Problem p : problemList) {
			System.out.println(p.toString());
		}
	}
}
