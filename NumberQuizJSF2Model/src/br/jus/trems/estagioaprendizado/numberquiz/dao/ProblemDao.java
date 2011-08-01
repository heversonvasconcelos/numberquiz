/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.dao;

import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;

/**
 *
 * @author heverson.vasconcelos
 */
public interface ProblemDao extends GenericDao<Problem, Integer> {

    public Problem getProblemBySequence(Integer[] problemSequence);

    public boolean verifyIfProblemSequenceExists(Integer[] problemSequence);
}
