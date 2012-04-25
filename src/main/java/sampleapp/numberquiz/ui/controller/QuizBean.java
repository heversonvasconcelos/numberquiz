package sampleapp.numberquiz.ui.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import sampleapp.numberquiz.model.dao.ProblemDao;
import sampleapp.numberquiz.model.dao.QuizDao;
import sampleapp.numberquiz.model.entity.Problem;
import sampleapp.numberquiz.model.entity.Quiz;
import sampleapp.numberquiz.model.entity.User;
import sampleapp.numberquiz.ui.util.Constants;
import sampleapp.numberquiz.ui.util.SessionUtil;

/**
 * Bean gerenciável utilizado no controle dos quizzes (jogos realizados). <br>
 * Este controle envolve principalmente: verificar se a resposta do usuário está
 * correta; salvar o jogo atual com a sua devida pontuação; iniciar um novo
 * jogo; apresentar as melhores pontuações;
 * 
 * 
 * 
 * @author heverson.vasconcelos
 */
@Named
@Scope("session")
public class QuizBean implements Serializable {

	private static final long serialVersionUID = 1L;
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
	@Inject
	private ProblemDao problemDao;
	/**
	 * Variável utilizada nos métodos que irão inserir ou consultar alguma
	 * informação relativa aos quizzes (jogos realizados).
	 */
	@Inject
	private QuizDao quizDao;

	/**
	 * Método que inicializa todas as variáveis necessárias para um novo jogo.
	 */
	@PostConstruct
	public void init() {
		quiz = new Quiz();

		problems = problemDao.list();
		score = 0;
		currentIndex = 0;
		Collections.shuffle(problems);
		quiz.setProblems((ArrayList<Problem>) problems);
		quiz.setScore(score);
		quiz.setUser((User) SessionUtil.getAttribute(Constants.LOGGED_USER));

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
	 * No caso desta aplicação, a expression language #{quizBean.answer}
	 * definida na página numberquiz.xhtml, acessa primeiramente o método
	 * getAnswer antes de setar a resposta do usuário no método setAnswer.
	 * 
	 * @return String vazia ("").
	 */
	public String getAnswer() {
		return "";
	}

	/**
	 * Método que verifica se a resposta do usuário está correta. Incrementa o
	 * score caso a resposta esteja correta.
	 * 
	 * @param answeredByUser
	 *            Resposta do usuário.
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
			quizDao.create(quiz);
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
	 * 
	 * @return String contendo o endereço de redirecionamento para início do
	 *         jogo (numberquiz.xhtml).
	 */
	public String newGame() {
		init();

		return Constants.PAGE_NUMBERQUIZ;
	}

	/**
	 * Método para apresentar ao usuário a sua pontuação final.
	 * 
	 * @return String contendo o endereço de redirecionamento para a página de
	 *         apresentação das pontuações (stats.xhtml).
	 */
	public String showScore() {
		saveScore();

		return Constants.PAGE_STATS;
	}

	/**
	 * Método para listar as melhores pontuações já realizadas.
	 * 
	 * @return Lista contendo os quizzes(jogos realizados) com as melhores
	 *         pontuações.
	 */
	public List<Quiz> getTopScores() {
		return quizDao.getTopScores(Constants.CONFIG_NUMBER_OF_SCORES);
	}
}
