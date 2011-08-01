package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.dao.GenericDao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe abstrata parametrizada que implementa a interface Dao.
 * As opera��es implementadas aqui, ser�o utilizadas nas subclasses concretas:
 * ProblemDaoImpl, QuizDaoImpl e UserDaoImpl.
 *
 * @author heverson.vasconcelos
 */
public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * M�todo para retornar a classe do par�metro T
     *
     * @return Classe especificada por T
     */
    public abstract Class<T> getDomainClass();

    @Override
    @Transactional
    public void create(T obj) {
        getEntityManager().clear();
        getEntityManager().persist(obj);

    }

    @Override
    public T retrieve(ID id) {
        return (T) getEntityManager().find(getDomainClass(), id);

    }

    @Override
    public List<T> list() {
        String queryS = "SELECT obj FROM " + getDomainClass().getSimpleName() + " obj";
        Query query = getEntityManager().createQuery(queryS);

        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    @Transactional
    public T delete(T obj) {
        obj = getEntityManager().merge(obj);
        getEntityManager().remove(obj);

        return obj;

    }

    @Override
    @Transactional
    public T update(T obj) {
        T objReturn = null;

        if (obj != null) {
            objReturn = (T) getEntityManager().merge(obj);
        }

        return objReturn;
    }

    @PreDestroy
    @Override
    public void finalizeAccess() {
        getEntityManager().close();
    }
}
