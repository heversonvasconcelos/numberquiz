package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.dao.Dao;
import br.jus.trems.estagioaprendizado.numberquiz.utils.EntityManagerUtil;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author heverson.vasconcelos
 */
public abstract class DaoImpl<T> implements Dao<T> {

    public abstract Class<T> getDomainClass();

    @Override
    public void create(T obj) {
        if (!EntityManagerUtil.isTransactionActive()) {
            EntityManagerUtil.beginTransaction();
        }

        EntityManagerUtil.insert(obj);
        EntityManagerUtil.commit();

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
        if (!EntityManagerUtil.isTransactionActive()) {
            EntityManagerUtil.beginTransaction();
        }

        EntityManagerUtil.merge(obj);
        EntityManagerUtil.commit();

        return obj;
    }
}
