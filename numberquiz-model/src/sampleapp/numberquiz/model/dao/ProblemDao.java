/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp.numberquiz.model.dao;

import sampleapp.numberquiz.model.entity.Problem;

/**
 *
 * @author heverson.vasconcelos
 */
public interface ProblemDao extends GenericDao<Problem, Integer> {

    public Problem getProblemBySequence(Integer[] problemSequence);

    public boolean verifyIfProblemSequenceExists(Integer[] problemSequence);
}
