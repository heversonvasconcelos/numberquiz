package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.entities.Quiz;

/**
 *
 * @author heverson.vasconcelos
 */
public class QuizDaoImpl extends DaoImpl<Quiz> {

    @Override
    public Class<Quiz> getDomainClass() {
        return Quiz.class;
    }
}
