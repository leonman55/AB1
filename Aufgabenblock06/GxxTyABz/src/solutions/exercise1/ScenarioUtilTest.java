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
	public void testGetPowerLinesByType() throws Exception {
		List<PowerLine> highVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.HIGH_VOLTAGE);
		assertThat("Number of high voltage lines", highVoltageLines.size(), is(11));

		List<PowerLine> lowVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.LOW_VOLTAGE);
		assertThat("Number of low voltage lines", lowVoltageLines.size(), is(3));

		List<PowerLine> mediumVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.MEDIUM_VOLTAGE);
		assertThat("Number of medium voltage lines", mediumVoltageLines.size(), is(4));
	}

	@Override
	@Test
	public void testGetPowerLinesByType_Parameters() throws Exception {
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
	public void testGetCoalFiredPowerPlants() throws Exception {
		assertThat(sut.getCoalFiredPowerPlants(graph1).size(), is(1));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetCoalFiredPowerPlants_Parameters() throws Exception {
		sut.getCoalFiredPowerPlants(null);
	}

	@Override
	@Test
	public void testGetGasFiredPowerPlants() throws Exception {
		assertThat(sut.getGasFiredPowerPlants(graph1).size(), is(2));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetGasFiredPowerPlants_Parameters() throws Exception {
		sut.getGasFiredPowerPlants(null);
	}

	@Override
	@Test
	public void testGetBioGasFiredPowerPlants() throws Exception {
		assertThat(sut.getBioGasFiredPowerPlants(graph1).size(), is(0));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetBioGasFiredPowerPlants_Parameters() {
		sut.getBioGasFiredPowerPlants(null);
	}

	@Override
	@Test
	public void testGetHydroPowerPlants() throws Exception {
		assertThat(sut.getHydroPowerPlants(graph1).size(), is(1));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetHydroPowerPlants_Parameters() throws Exception {
		sut.getHydroPowerPlants(null);
	}

	@Override
	@Test
	public void testGetNuclearPowerPlants() throws Exception {
		assertThat(sut.getNuclearPowerPlants(graph1).size(), is(1));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetNuclearPowerPlants_Parameters() throws Exception {
		sut.getNuclearPowerPlants(null);
	}

	@Override
	@Test
	public void testGetSolarPowerPlants() throws Exception {
		assertThat(sut.getSolarPowerPlants(graph1).size(), is(2));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetSolarPowerPlants_Parameters() throws Exception {
		sut.getSolarPowerPlants(null);
	}

	@Override
	@Test
	public void testGetWindPowerPlants() throws Exception {
		assertThat(sut.getWindPowerPlants(graph1).size(), is(3));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetWindPowerPlants_Parameters() throws Exception {
		sut.getWindPowerPlants(null);
	}

	@Override
	@Test
	public void testGetControllableProducers() throws Exception {
		assertThat(sut.getControllableProducers(graph1).size(), is(4));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetControllableProducers_Parameters() throws Exception {
		sut.getControllableProducers(null);
	}

	@Override
	@Test
	public void testGetCities() throws Exception {
		assertThat(sut.getCities(graph1).size(), is(4));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetCities_Parameters() throws Exception {
		sut.getCities(null);
	}

	@Override
	@Test
	public void testGetCommercialParks() throws Exception {
		assertThat(sut.getCommercialParks(graph1).size(), is(1));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetCommercialParks_Parameters() throws Exception {
		sut.getCommercialParks(null);
	}

	@Override
	@Test
	public void testGetIndustrialParks() throws Exception {
		assertThat(sut.getIndustrialParks(graph1).size(), is(3));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetIndustrialParks_Parameters() throws Exception {
		sut.getIndustrialParks(null);
	}

	@Override
	@Test
	public void testGetControllableConsumers() throws Exception {
		assertThat(sut.getControllableConsumers(graph1).size(), is(3));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetControllableConsumers_Parameters() throws Exception {
		sut.getConsumers(null);
	}

	@Override
	@Test
	public void testGetProducers() throws Exception {
		assertThat(sut.getProducers(graph1).size(), is(10));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetProducers_Parameters() throws Exception {
		sut.getProducers(null);
	}

	@Override
	@Test
	public void testGetConsumers() throws Exception {
		assertThat(sut.getConsumers(graph1).size(), is(8));
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void testGetConsumers_Parameters() throws Exception {
		sut.getConsumers(null);
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
