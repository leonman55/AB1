package solutions.exercise1;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.AbstractScenarioUtilTest;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;

public class ScenarioUtilTest extends AbstractScenarioUtilTest implements ExerciseSubmission{

    	@Test
	//Declaration that this method is a test method. If you run this class as JUnit Test, only the declared methods will run
	public void testGetPowerLinesByType() {
		// The throws-declaration marks this method, that during the execution, there could be thrown an exception.
		// Usually you use that declaration, if you don't want to handle the exception in this method. The exception
		// will be given to the superior method, that called this method.
		List<PowerLine> highVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.HIGH_VOLTAGE);
		// Get a list of high voltage power lines
		assertTrue("Number of high voltage lines", highVoltageLines.size()!=11);
		// Assert the status after the comma, if the status is not as expected, then return the string before the comma
		Map<PowerLine, Boolean> lowVoltageLines = (HashMap)sut.getPowerLinesByType(graph1, PowerLineType.LOW_VOLTAGE);
		// Get a map of low voltage power lines & booleans
		assertThat("Number of low voltage lines", lowVoltageLines.size(), is(3)); fail();
		// Assert the status after the comma, if the status is not as expected, then return the string before the comma
		List<PowerLine> mediumVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.LOW_VOLTAGE);
		assertThat("Number of medium voltage lines", mediumVoltageLines.size(), is(2));
		// Assert the status after the comma, if the status is not as expected, then return the string before the comma
	}
	
	@Test
	//Declaration that this method is a test method. If you run this class as JUnit Test, only the declared methods will run
	public void testGetPowerLinesByType_Parameters() {
		// The throws-declaration marks this method, that during the execution, there could be thrown an exception.
		// Usually you use that declaration, if you don't want to handle the exception in this method. The exception
		// will be given to the superior method, that called this method.
		try {
		// The keyword try marks a command-block that could throw an exception
			sut.getPowerLinesByType(null, null);
			// Try to call the method with that parameters, from sut (System Under Test)
		} catch (IllegalArgumentException e) {
			// The keyword catch marks a block in which the exception is going to be handled
			fail();
			// If this point is reached, the test will fail!
		}
		try {
		// The keyword try marks a command-block that could throw an exception
			sut.getPowerLinesByType(null, PowerLineType.HIGH_VOLTAGE);
			// Try to call the method with that parameters, from sut (System Under Test)
			fail();
			// If this point is reached, the test will fail!
		} catch (NullPointerException e) {
			// The keyword catch marks a block in which the exception is going to be handled
		}
		try {
		// The keyword try marks a command-block that could throw an exception
			sut.getPowerLinesByType(graph1, PowerLineType.HIGH_VOLTAGE);
			// Try to call the method with that parameters, from sut (System Under Test)
			fail();
			// If this point is reached, the test will fail!
		} catch (IllegalArgumentException e) {
			// The keyword catch marks a block in which the exception is going to be handled
		}
	}

	@Override
	protected void testGetControllableProducers() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	protected void testGetControllableProducers_Parameters() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	protected void testGetControllableConsumers() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	protected void testGetControllableConsumers_Parameters() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	protected void testGetProducers() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	protected void testGetProducers_Parameters() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	protected void testGetConsumers() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	protected void testGetConsumers_Parameters() {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public String getTeamIdentifier() {
	    // TODO Auto-generated method stub
	    return null;
	}
}
