package sampleapp.numberquiz.model.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Representa a sequência de números contida em cada problema. É composta pela
 * sequência de números e uma solução (próximo termo da sequência).
 * 
 * 
 * Ex.: Sequência: [1, 1, 2, 3, 5]; Solução: 8. (Sequência de Fibonacci)
 * 
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "tb_problem_sequence")
@NamedQuery(name = "ProblemSequence.findByProblemSequence", query = "SELECT p FROM Problem p "
		+ "JOIN p.problemSequence pseq "
		+ "WHERE problemSequence = :problemSequence")
public class ProblemSequence implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = -652670746528979461L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Sequência de números
	 */
	@Column(nullable = false, unique = true)
	private Integer[] problemSequence;

	/**
	 * Representa a solução do problema, ou seja, o próximo termo da sequência.
	 */
	@Column(nullable = false)
	private int solution;

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

	public Integer[] getProblemSequence() {
		return problemSequence;
	}

	public void setProblemSequence(Integer[] problemSequence) {
		this.problemSequence = problemSequence;
	}

	public int getSolution() {
		return solution;
	}

	public void setSolution(int solution) {
		this.solution = solution;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ProblemSequence other = (ProblemSequence) obj;
		if (!Arrays.deepEquals(this.problemSequence, other.problemSequence)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 97 * hash + Arrays.deepHashCode(this.problemSequence);
		return hash;
	}

	/**
	 * Método para retornar uma representação dos dados da sequência em modo
	 * texto
	 * 
	 * @return String contendo os dados da sequência
	 */
	@Override
	public String toString() {
		return Arrays.toString(problemSequence);
	}
}
