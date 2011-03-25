package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.entities.Quiz;
import br.jus.trems.estagioaprendizado.numberquiz.utils.EntityManagerUtil;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Classe concreta que será utilizada na execução de todas as operações de
 * persistência relativas a entidade Quiz.
 * 
 * @author heverson.vasconcelos
 */
public class QuizDaoImpl extends DaoImpl<Quiz> {

    @Override
    public Class<Quiz> getDomainClass() {
        return Quiz.class;
    }

    /**
     * Método para listar os quizzes com as melhores pontuações.
     * Serão organizados a partir da pontuação de forma decrescrente.
     * Obs.: Utiliza a NamedQuery Quiz.getTopScores descrita na classe
     * Quiz.
     *
     * @param Número máximo de quizzes que deverão ser consultados.
     * @return Lista contendo os quizzes resultado da consulta.
     */
    public List<Quiz> getTopScores(int numberOfScores) {
        Query query = EntityManagerUtil.getEntityManager().createNamedQuery("Quiz.getTopScores");

        try {
            query.setMaxResults(numberOfScores);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }

    }
}
