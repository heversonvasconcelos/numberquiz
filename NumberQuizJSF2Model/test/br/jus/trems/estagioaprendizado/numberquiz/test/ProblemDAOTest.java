package br.jus.trems.estagioaprendizado.numberquiz.test;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.ProblemDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author heverson.vasconcelos
 */
public class ProblemDAOTest {

    private Random rd = new Random(Calendar.getInstance().getTimeInMillis());
    private ProblemDaoImpl problemDaoImpl = new ProblemDaoImpl();

    public Problem getAProblem() {
        List<Problem> problemList = problemDaoImpl.list();

        if (problemList.size() > 0) {
            int problemListIndex = Math.abs(rd.nextInt() % problemList.size());
            return problemList.get(problemListIndex);

        }
        return null;
    }

    @Test
    public void create() {
        Problem problem00 = new Problem();
        problem00.setProblemSequenceAndSolution(new Integer[]{3, 1, 4, 1, 5}, 9); //pi

        if (!problemDaoImpl.verifyIfProblemSequenceExists(problem00.getIntegerArrayProblemSequence())) {
            problemDaoImpl.create(problem00);
        }

        Problem problem01 = new Problem();
        problem01.setProblemSequenceAndSolution(new Integer[]{1, 1, 2, 3, 5}, 8); // fibonacci

        if (!problemDaoImpl.verifyIfProblemSequenceExists(problem01.getIntegerArrayProblemSequence())) {
            problemDaoImpl.create(problem01);
        }

        Problem problem02 = new Problem();
        problem02.setProblemSequenceAndSolution(new Integer[]{1, 4, 9, 16, 25}, 36); // quadrados

        if (!problemDaoImpl.verifyIfProblemSequenceExists(problem02.getIntegerArrayProblemSequence())) {
            problemDaoImpl.create(problem02);
        }

        Problem problem03 = new Problem();
        problem03.setProblemSequenceAndSolution(new Integer[]{2, 3, 5, 7, 11}, 13); // primos

        if (!problemDaoImpl.verifyIfProblemSequenceExists(problem03.getIntegerArrayProblemSequence())) {
            problemDaoImpl.create(problem03);
        }

        Problem problem04 = new Problem();
        problem04.setProblemSequenceAndSolution(new Integer[]{1, 2, 4, 8, 16}, 32); // potências de 2

        if (!problemDaoImpl.verifyIfProblemSequenceExists(problem04.getIntegerArrayProblemSequence())) {
            problemDaoImpl.create(problem04);
        }
    }

    @Test
    public void list() {
        List<Problem> problemList = problemDaoImpl.list();

        for (Problem p : problemList) {
            System.out.println(p.toString());
        }
    }

    @Test
    public void listAProblem() {
        Problem problemToFind = getAProblem();
        Problem problemFound = (problemToFind != null)
                ? problemDaoImpl.getProblemBySequence(problemToFind.getIntegerArrayProblemSequence()) : null;

        if (problemFound != null) {
            System.out.println("Problem to find: " + problemToFind.toString());
            System.out.println("Problem found: " + problemFound.toString());
        }

    }
}
