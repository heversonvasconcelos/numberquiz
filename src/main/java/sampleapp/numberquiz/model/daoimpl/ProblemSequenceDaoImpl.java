package sampleapp.numberquiz.model.daoimpl;

import javax.inject.Named;

import sampleapp.numberquiz.model.dao.ProblemSequenceDao;
import sampleapp.numberquiz.model.entity.ProblemSequence;

@Named
public class ProblemSequenceDaoImpl extends
		GenericDaoImpl<ProblemSequence, Integer> implements ProblemSequenceDao {

	/**
     *
     */
	private static final long serialVersionUID = -4202938052403950016L;

	@Override
	public Class<ProblemSequence> getDomainClass() {
		return ProblemSequence.class;
	}

}
