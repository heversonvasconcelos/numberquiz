package sampleapp.numberquiz.model.daoimpl;

import java.util.List;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import sampleapp.numberquiz.model.dao.QuizDao;
import sampleapp.numberquiz.model.entity.Quiz;

/**
 * Classe concreta que será utilizada na execução de todas as operações de
 * persistência relativas a entidade Quiz.
 *
 * @author heverson.vasconcelos
 */
@Named
public class QuizDaoImpl extends GenericDaoImpl<Quiz, Integer> implements QuizDao {

    @Override
    public Class<Quiz> getDomainClass() {
        return Quiz.class;
    }

    /**
     * Método para listar os quizzes com as melhores pontuações. Serão
     * organizados a partir da pontuação de forma decrescrente. Obs.: Utiliza a
     * NamedQuery Quiz.getTopScores descrita na classe Quiz.
     *
     * @param numberOfScores Número máximo de quizzes que deverão ser
     * consultados.
     * @return Lista contendo os quizzes resultado da consulta.
     */
    public List<Quiz> getTopScores(int numberOfScores) {
        Query query = getEntityManager().createNamedQuery("Quiz.getTopScores");

        try {
            query.setMaxResults(numberOfScores);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }

    }
}
