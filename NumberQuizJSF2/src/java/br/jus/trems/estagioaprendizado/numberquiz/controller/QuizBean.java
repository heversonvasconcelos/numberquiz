package br.jus.trems.estagioaprendizado.numberquiz.controller;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.ProblemDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.QuizDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Quiz;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import br.jus.trems.estagioaprendizado.numberquiz.utils.Constants;
import br.jus.trems.estagioaprendizado.numberquiz.utils.SessionUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean gerenciável utilizado no controle dos quizzes (jogos realizados). <br>
 * Este controle envolve principalmente:
 *                          verificar se a resposta do usuário está correta;
 *                          salvar o jogo atual com a sua devida pontuação;
 *                          iniciar um novo jogo;
 *                          apresentar as melhores pontuações;
 *                          
 *
 *
 * @author heverson.vasconcelos
 */
@ManagedBean(name = "quizBean")
@SessionScoped
public class QuizBean implements Serializable {

    /**
     * Armazena a lista com os problemas que serão apresentados ao usuário.
     */
    private List<Problem> problems;
    /**
     * Utilizado como índice de navegação na lista de problemas.
     */
    private int currentIndex;
    private int answer;
    /**
     * Armazena o score(pontuação) atual.
     */
    private int score;
    /**
     * Armazena o quiz corrente, ou seja, o jogo propriamente dito.
     */
    private Quiz quiz;
    /**
     * Variável utilizada nos métodos que irão inserir ou consultar alguma
     * informação relativa aos problemas.
     */
    private ProblemDaoImpl problemDaoImpl;
    /**
     * Variável utilizada nos métodos que irão inserir ou consultar alguma
     * informação relativa aos quizzes (jogos realizados).
     */
    private QuizDaoImpl quizDaoImpl;

    /**
     * Construtor que inicializa as singletons que gerenciarão os problemas e
     * os quizzes requisitados.
     */
    public QuizBean() {
        problemDaoImpl = new ProblemDaoImpl();
        quizDaoImpl = new QuizDaoImpl();
    }

    /**
     * Método que inicializa todas as variáveis necessárias para um novo jogo.
     */
    @PostConstruct
    public void init() {
        quiz = new Quiz();

        problems = problemDaoImpl.list();
        score = 0;
        currentIndex = 0;
        Collections.shuffle(problems);
        quiz.setProblems((ArrayList<Problem>) problems);
        quiz.setScore(score);
        quiz.setUser((User) SessionUtil.getAttribute("authenticatedUser"));

    }

    @PreDestroy
    private void finalizeAccess() {
        problemDaoImpl.finalizeAccess();
        quizDaoImpl.finalizeAccess();
    }

    /**
     * Método que retorna o score atual.
     * 
     * @return O score atual.
     */
    public int getScore() {
        return score;
    }

    /**
     * Método que retorna o problema corrente.
     *
     * @return O problema corrente.
     */
    public Problem getCurrent() {
        return problems.get(currentIndex);
    }

    /**
     * Este método só foi implementado pela necessidade do JSF obter o valor
     * atual de uma propriedade acessada pela "expression language", antes do
     * novo valor ser submetido pelo formulário. <br>
     * No caso desta aplicação, a expression language #{quizBean.answer} definida
     * na página numberquiz.xhtml, acessa primeiramente o método getAnswer
     * antes de setar a resposta do usuário no método setAnswer.
     *
     * @return String vazia ("").
     */
    public String getAnswer() {
        return "";
    }

    /**
     * Método que verifica se a resposta do usuário está correta.
     * Incrementa o score caso a resposta esteja correta.
     *
     * @param answeredByUser Resposta do usuário.
     */
    public void setAnswer(String answeredByUser) {
        try {
            answer = Integer.parseInt(answeredByUser.trim());
        } catch (NumberFormatException ex) {
        }
    }

    /**
     * Método para armazenar no banco, o jogo que foi realizado com o seu score.
     */
    public void saveScore() {
        if (score > 0) {
            quiz.setScore(score);
            quizDaoImpl.create(quiz);
        }
    }

    public String checkSolution() {
        if (getCurrent().getSolution() == answer) {
            score++;
        }
        currentIndex = (currentIndex + 1) % problems.size();

        return null;
    }

    /**
     * Método para iniciar um novo jogo.
     * @return String contendo o endereço de redirecionamento para início do
     *          jogo (numberquiz.xhtml).
     */
    public String newGame() {
        init();

        return Constants.PAGE_NUMBERQUIZ;
    }

    /**
     * Método para apresentar ao usuário a sua pontuação final.
     * 
     * @return String contendo o endereço de redirecionamento para a página
     *          de apresentação das pontuações (stats.xhtml).
     */
    public String showScore() {
        saveScore();

        return Constants.PAGE_STATS;
    }

    /**
     * Método para listar as melhores pontuações já realizadas.
     * 
     * @return Lista contendo os quizzes(jogos realizados) com as melhores pontuações.
     */
    public List<Quiz> getTopScores() {
        return quizDaoImpl.getTopScores(Constants.CONFIG_NUMBER_OF_SCORES);
    }
}
