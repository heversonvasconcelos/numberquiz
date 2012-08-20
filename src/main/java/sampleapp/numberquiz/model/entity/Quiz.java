package sampleapp.numberquiz.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe entidade que representa um jogo realizado. É composto por um conjunto
 * de problemas (Problem) e por um usuário (User).
 * 
 * Possui uma NamedQuery (Quiz.getTopScores) que será utilizada para consultar
 * todas as pontuações dos jogos ordenadas de forma decrescente. O resultado
 * desta query será utilizada para mostar as melhores pontuações.
 * 
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "tb_quiz")
@NamedQuery(name = "Quiz.getTopScores", query = "SELECT q FROM Quiz AS q ORDER BY SCORE desc")
public class Quiz implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = -714232475989204830L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "problems_id")
	@ManyToMany
	private List<Problem> problems = new ArrayList<Problem>();

	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;

	@Column(nullable = false)
	private int score;

	/*
	 * 
	 * GETTERS e SETTERS
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Problem> getProblems() {
		return problems;
	}

	public void setProblems(ArrayList<Problem> problems) {
		this.problems = problems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Método para retornar uma representação dos dados do Quiz em modo texto
	 * 
	 * @return String contendo os dados do quiz
	 */
	@Override
	public String toString() {
		String string = "";

		string += ("QuizID: " + getId() + "\n");
		string += ("User: " + getUser().getName() + "\n");
		string += ("Problems: " + "\n");
		for (Problem p : getProblems()) {
			string += ("\t" + p.toString() + "\n");
		}
		string += ("Score: " + getScore());

		return string;
	}
}
