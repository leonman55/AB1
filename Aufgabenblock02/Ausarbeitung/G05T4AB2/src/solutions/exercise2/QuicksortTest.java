package solutions.exercise2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractQuicksortTest;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission
{
	Integer[] testint = {5, 8, 1, 5, 76, 3, 8, 9, 10};
	
	@Override
	@Test
	public void testPartition()
	{
		// TODO Auto-generated method stub
		int back = sut.partition(testint, 0, 8);
		System.out.println(back);
		assertEquals(back, 76);
	}

	@Override
	@Test
	public void testPartition_Parameters()
	{
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testQuicksort()
	{
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testQuicksort_Parameters()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

}
