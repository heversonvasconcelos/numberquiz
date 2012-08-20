package sampleapp.numberquiz.model.daoimpl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import sampleapp.numberquiz.model.dao.GenericDao;

/**
 * Classe abstrata parametrizada que implementa a interface Dao.
 * 
 * As operações implementadas aqui, serão utilizadas nas subclasses concretas:
 * ProblemDaoImpl, QuizDaoImpl e UserDaoImpl.
 * 
 * @author heverson.vasconcelos
 */
@SuppressWarnings("unchecked")
public abstract class GenericDaoImpl<T, ID extends Serializable> implements
		GenericDao<T, ID>, Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 6911972148588745364L;

	@PersistenceContext
	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Método para retornar a classe do parâmetro T
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
		return getEntityManager().find(getDomainClass(), id);

	}

	@Override
	public List<T> list() {
		String queryS = "SELECT obj FROM " + getDomainClass().getSimpleName()
				+ " obj";
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
			objReturn = getEntityManager().merge(obj);
		}

		return objReturn;
	}

	@PreDestroy
	@Override
	public void finalizeAccess() {
		getEntityManager().close();
	}
}
