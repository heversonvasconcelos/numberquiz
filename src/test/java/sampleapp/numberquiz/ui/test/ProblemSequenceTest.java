package sampleapp.numberquiz.ui.test;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import sampleapp.numberquiz.model.dao.ProblemSequenceDao;
import sampleapp.numberquiz.model.entity.ProblemSequence;

import com.google.gson.Gson;

@ContextConfiguration("classpath:application-test-context.xml")
public class ProblemSequenceTest extends AbstractJUnit4SpringContextTests {

	@Inject
	private ProblemSequenceDao problemSequenceDao;

	private final Gson gson = new Gson();

	@Test
	public void testConvertToGson() {
		ProblemSequence problemSequence = problemSequenceDao.retrieve(-1);
		String json = gson.toJson(problemSequence);
		System.out.println(json);

		ProblemSequence problemSequenceGson = gson.fromJson(json,
				ProblemSequence.class);
		Assert.assertEquals(problemSequenceGson, problemSequence);
	}
}
