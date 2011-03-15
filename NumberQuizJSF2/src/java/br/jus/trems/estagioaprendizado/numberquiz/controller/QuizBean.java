/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.controller;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.ProblemDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 *
 *
 * @author heverson.vasconcelos
 */
@ManagedBean(name = "quizBean")
@SessionScoped
public class QuizBean implements Serializable {

    private User user;
    private List<Problem> problems;
    private int currentIndex;
    private int score;
    private ProblemDaoImpl problemDaoImpl = new ProblemDaoImpl();

    public QuizBean() {
        problems = problemDaoImpl.list();
        Collections.shuffle(problems);
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
        currentIndex = 0;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public Problem getCurrent() {
        return problems.get(currentIndex);
    }

    public String getAnswer() {
        return "";
    }

    public void setAnswer(String newValue) {
        try {
            int answer = Integer.parseInt(newValue.trim());
            if (getCurrent().getSolution() == answer) {
                score++;
            }
            currentIndex = (currentIndex + 1) % problems.size();
        } catch (NumberFormatException ex) {
        }
    }

    /**
     * Método para limpar o score, a sequencia de questões e embaralhar as questões
     */
    public void resetQuiz() {
        score = 0;
        currentIndex = 0;
        Collections.shuffle(problems);
    }

    /**
     * Método para armazenar o score atual no banco. Este score será atrelado
     * a um jogo
     */
    public void saveScore() {
        /*
         * TODO: devera ser implementado o
         * método para salvar o score atual
         */
    }

    /**
     * Método para iniciar um novo jogo. Inicialmente será armazenado o score atual
     * e então serão 
     */
    public String newGame() {
        resetQuiz();

        return "numberquiz";
    }

    public String showScore() {
        saveScore();

        return "stats";
    }

    public String quit() {
        resetQuiz();
        /*
         * TODO: devera ser implementado a finalizacao da sessao
         */

        return "index";
    }
}
