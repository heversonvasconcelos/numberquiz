package br.jus.trems.estagioaprendizado.numberquiz.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Singleton que realiza as operações básicas para persistência
 * dos dados no banco. Inicializa uma EntityManager a partir da unidade de persistência
 * NumberQuizJSF2ModelPU.
 *
 * @author heverson.vasconcelos
 */
public class EntityManagerUtil {

    private static EntityManager em = Persistence.createEntityManagerFactory("NumberQuizJSF2ModelPU").createEntityManager();

    /**
     * Método para retornar a EntityManager criada
     *
     * @return EntityManager criada a partir da unidade de persistência NumberQuizJSF2ModelPU
     */
    public static EntityManager getEntityManager() {
        return em;
    }

    /**
     * Método para iniciar uma transação no banco. Necessária para operações
     * transacionais.
     */
    public static void beginTransaction() {
        em.getTransaction().begin();
    }

    /**
     * Método para verificar se a transação está ativa.
     * @return True caso esteja ativa a transação. False caso contrário.
     */
    public static boolean isTransactionActive() {
        return em.getTransaction().isActive();
    }

    /**
     * Método para dar commit na transação.
     */
    public static void commit() {
        em.getTransaction().commit();
    }

    /**
     * Método para inserir um novo objeto no banco.
     * 
     * @param obj Objeto a ser inserido
     */
    public static void insert(Object obj) {
        em.persist(obj);
    }

    /**
     * Método para criar uma query a ser executada no banco.
     * 
     * @param query String que representa a query
     * @return Query criada
     */
    public static Query createQuery(String query) {
        return em.createQuery(query);
    }

    /**
     * Método para atualizar o estado de um determinado objeto no banco.
     * 
     * @param obj Objeto a ser atualizado
     * @return Objeto atualizado
     */
    public static Object update(Object obj) {
        return em.merge(obj);
    }

    /**
     * Método para remover um determinado objeto.
     * 
     * @param obj Objeto a ser removido.
     */
    public static void remove(Object obj) {
        em.remove(obj);
    }
}
