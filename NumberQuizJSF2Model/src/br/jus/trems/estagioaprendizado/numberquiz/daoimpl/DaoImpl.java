package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.dao.Dao;
import br.jus.trems.estagioaprendizado.numberquiz.utils.EntityManagerUtil;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Classe abstrata parametrizada que implementa a interface Dao.
 * As operações implementadas aqui, serão utilizadas nas subclasses concretas:
 * ProblemDaoImpl, QuizDaoImpl e UserDaoImpl.
 *
 * @author heverson.vasconcelos
 */
public abstract class DaoImpl<T> implements Dao<T> {

    public DaoImpl() {
        EntityManagerUtil.init();
    }

    /**
     * Método para retornar a classe do parâmetro T
     *
     * @return Classe especificada por T
     */
    public abstract Class<T> getDomainClass();

    @Override
    public void create(T obj) {
        if (!EntityManagerUtil.isTransactionActive()) {
            EntityManagerUtil.beginTransaction();
        }
        if (obj != null) {
            EntityManagerUtil.insert(obj);
            EntityManagerUtil.commit();
        }
    }

    @Override
    public T retrieve(Integer id) {
        return (T) EntityManagerUtil.getEntityManager().find(getDomainClass(), id);

    }

    @Override
    public List<T> list() {
        String queryS = "SELECT obj FROM " + getDomainClass().getSimpleName() + " obj";
        Query query = EntityManagerUtil.createQuery(queryS);

        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /*
    @Override
    public T delete(T obj) {
    if (!EntityManagerUtil.isTransactionActive()) {
    EntityManagerUtil.beginTransaction();
    }

    obj = (T) EntityManagerUtil.merge(obj);
    EntityManagerUtil.remove(obj);

    EntityManagerUtil.commit();

    return obj;
    }
     *
     */
    @Override
    public T update(T obj) {
        T objReturn = null;

        if (!EntityManagerUtil.isTransactionActive()) {
            EntityManagerUtil.beginTransaction();
        }

        if (obj != null) {
            objReturn = (T) EntityManagerUtil.update(obj);
            EntityManagerUtil.commit();
        }

        return objReturn;
    }

    @PreDestroy
    public void finalizeAccess() {
        EntityManagerUtil.close();
    }
}
