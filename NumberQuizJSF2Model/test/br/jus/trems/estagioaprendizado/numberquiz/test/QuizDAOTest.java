/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.test;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.ProblemDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.QuizDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.UserDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Quiz;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author heverson.vasconcelos
 */
public class QuizDAOTest {

    private Random rd = new Random(Calendar.getInstance().getTimeInMillis());
    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private ProblemDaoImpl problemDaoImpl = new ProblemDaoImpl();
    private QuizDaoImpl quizDaoImpl = new QuizDaoImpl();

    public User getUser() {
        List<User> userList = userDaoImpl.list();

        if (userList.size() > 0) {
            int userListIndex = Math.abs(rd.nextInt() % userList.size());
            return userList.get(userListIndex);
        }

        return null;
    }

    public Problem getProblem() {

        List<Problem> problemList = problemDaoImpl.list();

        if (problemList.size() > 0) {
            int problemListIndex = Math.abs(rd.nextInt() % problemList.size());
            return problemList.get(problemListIndex);
        }

        return null;
    }

    public List<Problem> getProblems() {
        List<Problem> problemList = problemDaoImpl.list();

        if (problemList.size() > 0) {
            int numberOfProblems = Math.abs(rd.nextInt() % problemList.size());

            List<Problem> quizProblemList = new ArrayList<Problem>();
            Collections.shuffle(problemList, rd);

            for (int i = 0; i < numberOfProblems; i++) {
                quizProblemList.add(problemList.get(i));
            }

            return quizProblemList;
        }

        return null;
    }

    //@Test
    public void create() {
        Quiz quiz = new Quiz();
        User user = getUser();
        List<Problem> quizProblemList = getProblems();

        if (user != null && quizProblemList != null) {
            quiz.setUser(getUser());
            quiz.setProblems((ArrayList<Problem>) getProblems());
            quiz.setScore(0);
            quizDaoImpl.create(quiz);
        }
    }

    //@Test
    public void createALotOfQuizzes() {
        int numberOfQuizzes = Math.abs(rd.nextInt() % 30);
        Quiz quiz;
        User user;
        List<Problem> quizProblemList;

        for (int i = 0; i < numberOfQuizzes; i++) {
            user = getUser();
            quizProblemList = getProblems();

            if (user != null && quizProblemList != null) {
                quiz = new Quiz();
                quiz.setUser(getUser());
                quiz.setProblems((ArrayList<Problem>) getProblems());
                quiz.setScore(0);
                quizDaoImpl.create(quiz);
            }

        }
    }

    /*
    @Test
    public void remove() {
    List<Quiz> quizList = quizDaoImpl.list();

    if (quizList.size() > 0) {
    int quizListIndex = Math.abs(rd.nextInt() % quizList.size());

    quizDaoImpl.delete(quizList.get(quizListIndex));
    }
    }
     */
    //@Test
    public void list() {
        List<Quiz> quizList = quizDaoImpl.list();

        for (Quiz q : quizList) {
            /*System.out.println("##Quiz: " + q.getId());
            System.out.println("User: " + q.getUser().getName());
            System.out.println("Problems: ");
            for (Problem p : q.getProblems()) {
                System.out.println(p.getId() + " - " + p.getProblemSequence());
            }
            System.out.println("Score: " + q.getScore());*/
            System.out.println(q.toString());
            System.out.println("----------------------------------------------");
        }
    }

    @Test
    public void getTopScores() {
        List<Quiz> quizList = quizDaoImpl.getTopScores(5);

        for (Quiz q : quizList) {
            /*System.out.println("##Quiz: " + q.getId());
            System.out.println("User: " + q.getUser().getName());
            System.out.println("Problems: ");
            for (Problem p : q.getProblems()) {
                System.out.println(p.getId() + " - " + p.getProblemSequence());
            }
            System.out.println("Score: " + q.getScore());*/
            System.out.println(q.toString());
            System.out.println("----------------------------------------------");
        }
    }

}
