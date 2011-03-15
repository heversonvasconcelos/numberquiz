/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.dao;

import java.util.List;

/**
 *
 * @author heverson.vasconcelos
 */
public interface Dao<T> {

    public void create(T obj);

    public T retrieve(Integer id);

    public List<T> list();

    //public T delete(T obj);

    public T update(T obj);
}
