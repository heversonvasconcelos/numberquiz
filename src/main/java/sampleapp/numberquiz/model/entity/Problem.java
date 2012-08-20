package sampleapp.numberquiz.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe entidade que representa cada problema a ser solucionado pelo usuário.
 * É composto por uma sequência de números (ProblemSequence). Este atributo
 * define a unicidade de um problema, ou seja, para cada problema existirá
 * somente uma sequência e vice-versa.
 * 
 * Ex.: Sequência: [1, 1, 2, 3, 5]; Solução: 8. (Sequência de Fibonacci)
 * 
 * Possui uma NamedQuery(Problem.findByProblemSequence) que será utilizada para
 * consultar um problema a partir de uma sequência (ProblemSequence).
 * 
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "tb_problem")
@NamedQuery(name = "Problem.findByProblemSequence", query = "SELECT p FROM Problem p "
		+ "JOIN p.problemSequence pseq "
		+ "WHERE problemSequence = :problemSequence")
public class Problem implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = -89930616621289568L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Representa a sequência de números que o usuário deverá adivinhar o
	 * próximo número desta mesma.
	 */
	@JoinColumn(name = "problem_sequence_id")
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	private ProblemSequence problemSequence;

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

	public ProblemSequence getProblemSequence() {
		return problemSequence;
	}

	public void setProblemSequence(ProblemSequence problemSequence) {
		this.problemSequence = problemSequence;
	}

	public Integer[] getIntegerArrayProblemSequence() {
		return problemSequence.getProblemSequence();
	}

	public void setProblemSequence(Integer[] problemSequence) {
		this.problemSequence.setProblemSequence(problemSequence);
	}

	public int getSolution() {
		return problemSequence.getSolution();
	}

	public void setSolution(int solution) {
		this.problemSequence.setSolution(solution);
	}

	public void setProblemSequenceAndSolution(Integer[] values, int solution) {
		this.problemSequence.setProblemSequence(values);
		this.problemSequence.setSolution(solution);
	}

	/*
	 * Hashcode e equals foram sobrescritos para que um problema possa ser
	 * comparado com outro a partir da sequência deste problema
	 * (ProblemSequence)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Problem other = (Problem) obj;
		if (this.problemSequence != other.problemSequence
				&& (this.problemSequence == null || !this.problemSequence
						.equals(other.problemSequence))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67
				* hash
				+ (this.problemSequence != null ? this.problemSequence
						.hashCode() : 0);
		return hash;
	}

	/**
	 * Método para retornar uma representação dos dados do problema em modo
	 * texto
	 * 
	 * @return String contendo os dados do problema
	 */
	@Override
	public String toString() {
		return (getId() + " - " + problemSequence + " - " + getSolution());
	}
}
