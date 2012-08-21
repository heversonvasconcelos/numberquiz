package sampleapp.numberquiz.model.entity;

import java.io.Serializable;

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
	private String problemSequence;

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

	public String getProblemSequence() {
		return problemSequence;
	}

	public void setProblemSequence(String problemSequence) {
		this.problemSequence = problemSequence;
	}

	public int getSolution() {
		return solution;
	}

	public void setSolution(int solution) {
		this.solution = solution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemSequence other = (ProblemSequence) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return problemSequence;
	}

}
