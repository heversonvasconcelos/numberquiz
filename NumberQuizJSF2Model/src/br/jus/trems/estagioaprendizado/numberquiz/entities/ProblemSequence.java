/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.entities;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "TB_PROBLEMSEQUENCE")
public class ProblemSequence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private Integer[] problemSequence;
    /**
     * Representa a solução do problema, ou seja, o próximo número da sequência.
     */
    @Column(nullable = false)
    private int solution;

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
    public String toString() {
        return Arrays.toString(problemSequence);
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
