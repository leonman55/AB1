package solutions.exercise1;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.AbstractScenarioUtilTest;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.Producer;

public class ScenarioUtilTest extends AbstractScenarioUtilTest implements ExerciseSubmission{

    	@Test
	//Declaration that this method is a test method. If you run this class as JUnit Test, only the declared methods will run
	public void testGetPowerLinesByType() {
    		// The throws-declaration marks this method, that during the execution, there could be thrown an exception.
    		// Usually you use that declaration, if you don't want to handle the exception in this method. The exception
    		// will be given to the superior method, that called this method.
		List<PowerLine> highVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.HIGH_VOLTAGE);
			// Get a list of high voltage power lines
		assertThat("Number of high voltage lines", highVoltageLines.size(), is(11));
			// Assert the status after the comma, if the status is not as expected, then return the string before the comma
		List<PowerLine> lowVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.LOW_VOLTAGE);
			// Get a map of low voltage power lines & booleans
		assertThat("Number of low voltage lines", lowVoltageLines.size(), is(3));
			// Assert the status after the comma, if the status is not as expected, then return the string before the comma
		List<PowerLine> mediumVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.MEDIUM_VOLTAGE);
		assertThat("Number of medium voltage lines", mediumVoltageLines.size(), is(4));
			// Assert the status after the comma, if the status is not as expected, then return the string before the comma
	}
	
	@Test
	public void testGetPowerLinesByType_Parameters()
	{
		try
		{
			sut.getPowerLinesByType(null, null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
				
		}
		try
		{
			sut.getPowerLinesByType(null, PowerLineType.HIGH_VOLTAGE);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			
		}
		try
		{
			sut.getPowerLinesByType(graph1, null);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			
		}
	}

	@Override
	@Test
	public void testGetControllableProducers()
	{
	    // TODO Auto-generated method stub
		List<ControllableProducer> prod = sut.getControllableProducers(graph1);
		assertEquals(prod.size(), 4);
	}

	@Override
	@Test
	public void testGetControllableProducers_Parameters()
	{
	    // TODO Auto-generated method stub
	    try
	    {
	    	sut.getControllableProducers(null);
	    	//List<ControllableProducer> prod = sut.getControllableProducers(null);
	    	fail();
	    }
	    catch(IllegalArgumentException e)
	    {
	    	
	    }
	}

	@Override
	@Test
	public void testGetControllableConsumers()
	{
	    // TODO Auto-generated method stub
		List<ControllableConsumer> con = sut.getControllableConsumers(graph1);
		assertEquals(con.size(), 3);
	}

	@Override
	@Test
	public void testGetControllableConsumers_Parameters()
	{
	    // TODO Auto-generated method stub
		try
	    {
			sut.getControllableConsumers(null);
	    	//List<ControllableConsumer> con = sut.getControllableConsumers(null);
	    	fail();
	    }
	    catch(IllegalArgumentException e)
	    {
	    	
	    }
	}

	@Override
	@Test
	public void testGetProducers()
	{
	    // TODO Auto-generated method stub
		List<Producer> prod = sut.getProducers(graph1);
		System.out.println(prod.size());
		assertEquals(prod.size(), 10);
	}

	@Override
	@Test
	public void testGetProducers_Parameters()
	{
	    // TODO Auto-generated method stub
		try
	    {
			sut.getProducers(null);
	    	//List<Producer> prod = sut.getProducers(null);
	    	fail();
	    }
	    catch(IllegalArgumentException e)
	    {
	    	
	    }
	}

	@Override
	@Test
	public void testGetConsumers()
	{
	    // TODO Auto-generated method stub
		List<Consumer> con = sut.getConsumers(graph1);
		System.out.println(con.size());
		assertEquals(con.size(), 8);
	}

	@Override
	@Test
	public void testGetConsumers_Parameters()
	{
	    // TODO Auto-generated method stub
		try
	    {
			sut.getConsumers(null);
	    	//List<Consumer> con = sut.getConsumers(null);
	    	fail();
	    }
	    catch(IllegalArgumentException e)
	    {
	    	
	    }
	}

	@Override
	public String getTeamIdentifier() {
	    // TODO Auto-generated method stub
	    return "G05T04";
	}
}
