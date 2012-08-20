package sampleapp.numberquiz.model.dao;

import java.util.List;

import sampleapp.numberquiz.model.entity.Quiz;

/**
 * 
 * @author heverson.vasconcelos
 */
public interface QuizDao extends GenericDao<Quiz, Integer> {

	public List<Quiz> getTopScores(int numberOfScores);
}
