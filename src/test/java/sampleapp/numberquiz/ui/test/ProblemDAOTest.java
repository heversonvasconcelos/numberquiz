package sampleapp.numberquiz.ui.test;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

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

	private final Random rd = new Random(Calendar.getInstance()
			.getTimeInMillis());

	private Problem getAProblem() {
		List<Problem> problemList = problemDao.list();

		if (problemList.size() > 0) {
			int problemListIndex = Math.abs(rd.nextInt() % problemList.size());
			return problemList.get(problemListIndex);

		}
		return null;
	}

	// @Test
	public void create() {
		Problem problem00 = new Problem();
		problem00.setProblemSequenceAndSolution(
				new Integer[] { 3, 1, 4, 1, 5 }, 9); // pi

		if (!problemDao.verifyIfProblemSequenceExists(problem00
				.getIntegerArrayProblemSequence())) {
			problemDao.create(problem00);
		}

		Problem problem01 = new Problem();
		problem01.setProblemSequenceAndSolution(
				new Integer[] { 1, 1, 2, 3, 5 }, 8); // fibonacci

		if (!problemDao.verifyIfProblemSequenceExists(problem01
				.getIntegerArrayProblemSequence())) {
			problemDao.create(problem01);
		}

		Problem problem02 = new Problem();
		problem02.setProblemSequenceAndSolution(
				new Integer[] { 1, 4, 9, 16, 25 }, 36); // quadrados

		if (!problemDao.verifyIfProblemSequenceExists(problem02
				.getIntegerArrayProblemSequence())) {
			problemDao.create(problem02);
		}

		Problem problem03 = new Problem();
		problem03.setProblemSequenceAndSolution(
				new Integer[] { 2, 3, 5, 7, 11 }, 13); // primos

		if (!problemDao.verifyIfProblemSequenceExists(problem03
				.getIntegerArrayProblemSequence())) {
			problemDao.create(problem03);
		}

		Problem problem04 = new Problem();
		problem04.setProblemSequenceAndSolution(
				new Integer[] { 1, 2, 4, 8, 16 }, 32); // potências de 2

		if (!problemDao.verifyIfProblemSequenceExists(problem04
				.getIntegerArrayProblemSequence())) {
			problemDao.create(problem04);
		}
	}

	@Test
	public void list() {
		List<Problem> problemList = problemDao.list();

		for (Problem p : problemList) {
			System.out.println(p.toString());
		}
	}

	@Test
	public void listAProblem() {
		Problem problemToFind = getAProblem();
		Problem problemFound = (problemToFind != null) ? problemDao
				.getProblemBySequence(problemToFind
						.getIntegerArrayProblemSequence()) : null;

		if (problemFound != null) {
			System.out.println("Problem to find: " + problemToFind.toString());
			System.out.println("Problem found: " + problemFound.toString());
		} else {
			System.out.println("Problem not found!");
		}

	}
}
