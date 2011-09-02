package sampleapp.numberquiz.daoimpl;

import sampleapp.numberquiz.dao.ProblemDao;
import sampleapp.numberquiz.entities.Problem;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Classe concreta que será utilizada na execução de todas as operações de
 * persistência relativas a entidade Problem.
 *
 * @author heverson.vasconcelos
 */
@Repository
public class ProblemDaoImpl extends GenericDaoImpl<Problem, Integer> implements ProblemDao {

    @Override
    public Class<Problem> getDomainClass() {
        return Problem.class;
    }

    /**
     * Método para consultar um problema a partir de uma determinada sequência.
     * Obs.: Utiliza a NamedQuery Problem.findByProblemSequence descrita na classe
     * Problem.
     *
     * @param problemSequence Sequência a ser buscada.
     * @return Problema contendo a sequência problemSequence. <br>
     *         Null caso não exista um problema contendo a sequência problemSequence.
     */
    @Override
    public Problem getProblemBySequence(Integer[] problemSequence) {
        Query query = getEntityManager().createNamedQuery("Problem.findByProblemSequence");
        query.setParameter("problemSequence", problemSequence);

        try {
            return (Problem) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    /**
     * Método para verificar se existe um problema com uma determinada sequência.
     *
     * @param problemSequence Sequência a ser buscada.
     * @return True caso exista um problema contendo a determinada sequência.
     */
    @Override
    public boolean verifyIfProblemSequenceExists(Integer[] problemSequence) {
        return (getProblemBySequence(problemSequence) != null) ? true : false;
    }
}
