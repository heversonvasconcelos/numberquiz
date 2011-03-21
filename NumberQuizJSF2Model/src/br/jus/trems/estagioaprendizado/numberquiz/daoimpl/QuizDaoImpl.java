package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.entities.Quiz;
import br.jus.trems.estagioaprendizado.numberquiz.utils.EntityManagerUtil;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author heverson.vasconcelos
 */
public class QuizDaoImpl extends DaoImpl<Quiz> {

    @Override
    public Class<Quiz> getDomainClass() {
        return Quiz.class;
    }

    public List<Quiz> getTopScores() {
        Query query = EntityManagerUtil.getEntityManager().createNamedQuery("Quiz.getTopScores");

        try {
            query.setMaxResults(5);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }

    }
}
