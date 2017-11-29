package solutions.exercise1;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.AbstractScenarioUtilTest;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;

public class ScenarioUtilTest extends AbstractScenarioUtilTest implements ExerciseSubmission {

	@Override
	@Test
	public void testGetPowerLinesByType(){
		List<PowerLine> highVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.HIGH_VOLTAGE);
		assertThat("Number of high voltage lines", highVoltageLines.size(), is(11));

		List<PowerLine> lowVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.LOW_VOLTAGE);
		assertThat("Number of low voltage lines", lowVoltageLines.size(), is(3));

		List<PowerLine> mediumVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.MEDIUM_VOLTAGE);
		assertThat("Number of medium voltage lines", mediumVoltageLines.size(), is(4));
	}

	@Override
	@Test
	public void testGetPowerLinesByType_Parameters() {
		try {
			sut.getPowerLinesByType(null, null);
			fail();
		} catch (IllegalArgumentException e) {

		}

		try {
			sut.getPowerLinesByType(null, PowerLineType.HIGH_VOLTAGE);
			fail();
		} catch (IllegalArgumentException e) {

		}

		try {
			sut.getPowerLinesByType(graph1, null);
			fail();
		} catch (IllegalArgumentException e) {

		}

	}

	@Override
	@Test
	public void testGetControllableProducers() {
		assertThat(sut.getControllableProducers(graph1).size(), is(4));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetControllableProducers_Parameters(){
		sut.getControllableProducers(null);
	}

	@Override
	@Test
	public void testGetControllableConsumers() {
		assertThat(sut.getControllableConsumers(graph1).size(), is(3));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetControllableConsumers_Parameters(){
		sut.getConsumers(null);
	}

	@Override
	@Test
	public void testGetProducers() {
		assertThat(sut.getProducers(graph1).size(), is(10));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetProducers_Parameters(){
		sut.getProducers(null);
	}

	@Override
	@Test
	public void testGetConsumers() {
		assertThat(sut.getConsumers(graph1).size(), is(8));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetConsumers_Parameters(){
		sut.getConsumers(null);
	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}
}
