package br.jus.trems.estagioaprendizado.numberquiz.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * 
 * @author heverson.vasconcelos
 */
public class EntityManagerUtil {

    private static EntityManager em = Persistence.createEntityManagerFactory("NumberQuizJSF2ModelPU").createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }

    public static void beginTransaction() {
        em.getTransaction().begin();
    }

    public static boolean isTransactionActive() {
        return em.getTransaction().isActive();
    }

    public static void commit() {
        em.getTransaction().commit();
    }

    public static void insert(Object obj) {
        em.persist(obj);
    }

    public static Query createQuery(String query) {
        return em.createQuery(query);
    }

    public static Object merge(Object obj) {
        return em.merge(obj);
    }

    public static Object update(Object obj) {
        return em.merge(obj);
    }

    public static void remove(Object obj) {
        em.remove(obj);
    }
}
