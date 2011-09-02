/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapp.numberquiz.dao;

import sampleapp.numberquiz.entities.Quiz;
import java.util.List;

/**
 *
 * @author heverson.vasconcelos
 */
public interface QuizDao extends GenericDao<Quiz, Integer> {

    public List<Quiz> getTopScores(int numberOfScores);
}
