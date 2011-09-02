package sampleapp.numberquiz.utils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @PostConstruct
    public static void init() {
        if (!isOpen()) {
            emf = Persistence.createEntityManagerFactory("NumberQuizJSF2ModelPU");
            em = emf.createEntityManager();
        }
    }

    /**
     * Método para retornar a EntityManager criada
     *
     * @return EntityManager criada a partir da unidade de persistência NumberQuizJSF2ModelPU
     */
    public static EntityManager getEntityManager() {
        init();
        return em;
    }

    /**
     * Método para iniciar uma transação no banco. Necessária para operações
     * transacionais.
     */
    public static void beginTransaction() {
        init();
        if (!isTransactionActive()) {
            em.getTransaction().begin();
        }
    }

    /**
     * Método para verificar se a transação está ativa.
     * @return True caso esteja ativa a transação. False caso contrário.
     */
    public static boolean isTransactionActive() {
        init();
        return em.getTransaction().isActive();
    }

    /**
     * Método para dar commit na transação.
     */
    public static void commit() {
        init();
        em.getTransaction().commit();
    }

    /**
     * Método para inserir um novo objeto no banco.
     * 
     * @param obj Objeto a ser inserido
     */
    public static void insert(Object obj) {
        init();
        em.persist(obj);
    }

    /**
     * Método para criar uma query a ser executada no banco.
     * 
     * @param query String que representa a query
     * @return Query criada
     */
    public static Query createQuery(String query) {
        init();
        return em.createQuery(query);
    }

    /**
     * Método para atualizar o estado de um determinado objeto no banco.
     * 
     * @param obj Objeto a ser atualizado
     * @return Objeto atualizado
     */
    public static Object update(Object obj) {
        init();
        return em.merge(obj);
    }

    /**
     * Método para remover um determinado objeto.
     * 
     * @param obj Objeto a ser removido.
     */
    public static void remove(Object obj) {
        init();
        em.remove(obj);
    }

    @PreDestroy
    public static void close() {
        if (em.isOpen()) {
            em.close();
        }

        if (emf.isOpen()) {
            emf.close();
        }
    }

    public static boolean isOpen() {
        if (emf != null) {
            return emf.isOpen();
        }

        return false;
    }
}
