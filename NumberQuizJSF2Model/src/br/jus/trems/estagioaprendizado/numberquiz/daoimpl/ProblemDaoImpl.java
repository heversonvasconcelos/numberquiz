package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;
import br.jus.trems.estagioaprendizado.numberquiz.utils.EntityManagerUtil;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author heverson.vasconcelos
 */
public class ProblemDaoImpl extends DaoImpl<Problem> {

    @Override
    public Class<Problem> getDomainClass() {
        return Problem.class;
    }

    public Problem getProblemBySequence(Integer[] problemSequence) {
        Query query = EntityManagerUtil.getEntityManager().createNamedQuery("Problem.findByProblemSequence");
        query.setParameter("problemSequence", problemSequence);

        try {
            return (Problem) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    public boolean verifyIfProblemSequenceExists(Integer[] problemSequence) {
        return (getProblemBySequence(problemSequence) != null) ? true : false;
    }
}
