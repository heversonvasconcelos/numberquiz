package br.jus.trems.estagioaprendizado.numberquiz.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe entidade (POJO - Plain Old Java Object) que representa cada problema a
 * ser solucionado pelo usuário.
 * É composto por uma sequência (semelhante a um enunciado de uma questão) e
 * a sua solução.
 * 
 * Ex.: Sequência: [1, 1, 2, 3, 5]; Solução: 8.
 *      (Sequência de Fibonacci)
 *
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "TB_PROBLEM")
@NamedQuery(name = "Problem.findByProblemSequence",
query = "SELECT p FROM Problem p "
+ "JOIN p.problemSequence pseq "
+ "WHERE problemSequence = :problemSequence")
public class Problem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Representa a sequência de números que o usuário deverá adivinhar
     * o próximo número desta mesma.
     */
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    private ProblemSequence problemSequence = new ProblemSequence();

    /*
     *
     * GETTERS e SETTERS
     * 
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Problem other = (Problem) obj;
        if (this.problemSequence != other.problemSequence && (this.problemSequence == null || !this.problemSequence.equals(other.problemSequence))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.problemSequence != null ? this.problemSequence.hashCode() : 0);
        return hash;
    }

    /*
    @Override
    public String toString() {
    String problemSequenceString = new String();

    problemSequenceString += "[ ";
    for (int i = 0; i < getProblemSequence().length; i++) {
    if (i < (getProblemSequence().length - 1)) {
    problemSequenceString += getProblemSequence()[i] + ", ";
    } else {
    problemSequenceString += getProblemSequence()[i] + " ]";
    }
    }

    return (getId() + " - " + problemSequenceString + " - " + getSolution());
    }
     *
     */
    @Override
    public String toString() {
        return (getId() + " - " + problemSequence + " - " + getSolution());
    }
}
