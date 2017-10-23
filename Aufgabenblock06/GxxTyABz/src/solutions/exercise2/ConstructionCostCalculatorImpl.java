package solutions.exercise2;

import java.util.HashMap;
import java.util.Map;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Multiplicator;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.producer.ProducerType;

public class ConstructionCostCalculatorImpl implements ConstructionCostCalculator, ExerciseSubmission {

	private final Map<ProducerType, Map<ElementType, Multiplicator>> data = new HashMap<>();
	private final Scenario scenario;

	public ConstructionCostCalculatorImpl(Scenario scenario) {
		this.scenario = scenario;

		// Add data for BioGasFiredPowerPlant
		Map<ElementType, Multiplicator> bioGasFiredPowerPlant = new HashMap<>(5);
		bioGasFiredPowerPlant.put(ElementType.BEACH, Multiplicator.HIGH);
		bioGasFiredPowerPlant.put(ElementType.GRASSLAND, Multiplicator.MEDIUM);
		bioGasFiredPowerPlant.put(ElementType.MOUNTAIN, Multiplicator.EXTREMELY_HIGH);
		bioGasFiredPowerPlant.put(ElementType.RIVER, Multiplicator.EXTREMELY_HIGH);
		bioGasFiredPowerPlant.put(ElementType.SEA, Multiplicator.EXTREMELY_HIGH);
		data.put(ProducerType.BIOGAS_FIRED_POWER_PLANT, bioGasFiredPowerPlant);

		// Add data for CoalFiredPowerPlant
		Map<ElementType, Multiplicator> coalFiredPowerPlant = new HashMap<>(5);
		coalFiredPowerPlant.put(ElementType.BEACH, Multiplicator.HIGH);
		coalFiredPowerPlant.put(ElementType.GRASSLAND, Multiplicator.MEDIUM);
		coalFiredPowerPlant.put(ElementType.MOUNTAIN, Multiplicator.EXTREMELY_HIGH);
		coalFiredPowerPlant.put(ElementType.RIVER, Multiplicator.MEDIUM);
		coalFiredPowerPlant.put(ElementType.SEA, Multiplicator.EXTREMELY_HIGH);
		data.put(ProducerType.COALFIRED_POWER_PLANT, coalFiredPowerPlant);

		// Add data for GasFiredPowerPlant
		Map<ElementType, Multiplicator> gasFiredPowerPlant = new HashMap<>(5);
		gasFiredPowerPlant.put(ElementType.BEACH, Multiplicator.HIGH);
		gasFiredPowerPlant.put(ElementType.GRASSLAND, Multiplicator.MEDIUM);
		gasFiredPowerPlant.put(ElementType.MOUNTAIN, Multiplicator.EXTREMELY_HIGH);
		gasFiredPowerPlant.put(ElementType.RIVER, Multiplicator.EXTREMELY_HIGH);
		gasFiredPowerPlant.put(ElementType.SEA, Multiplicator.EXTREMELY_HIGH);
		data.put(ProducerType.GASFIRED_POWER_PLANT, gasFiredPowerPlant);

		// Add data for HydroPowerPlant
		Map<ElementType, Multiplicator> hydroPowerPlant = new HashMap<>(5);
		hydroPowerPlant.put(ElementType.BEACH, Multiplicator.LOW);
		hydroPowerPlant.put(ElementType.GRASSLAND, Multiplicator.EXTREMELY_HIGH);
		hydroPowerPlant.put(ElementType.MOUNTAIN, Multiplicator.EXTREMELY_HIGH);
		hydroPowerPlant.put(ElementType.RIVER, Multiplicator.VERY_LOW);
		hydroPowerPlant.put(ElementType.SEA, Multiplicator.MEDIUM);
		data.put(ProducerType.HYDRO_POWER_PLANT, hydroPowerPlant);

		// Add data for NuclearPowerPlant
		Map<ElementType, Multiplicator> nuclearPowerPlant = new HashMap<>(5);
		nuclearPowerPlant.put(ElementType.BEACH, Multiplicator.MEDIUM);
		nuclearPowerPlant.put(ElementType.GRASSLAND, Multiplicator.HIGH);
		nuclearPowerPlant.put(ElementType.MOUNTAIN, Multiplicator.EXTREMELY_HIGH);
		nuclearPowerPlant.put(ElementType.RIVER, Multiplicator.MEDIUM);
		nuclearPowerPlant.put(ElementType.SEA, Multiplicator.EXTREMELY_HIGH);
		data.put(ProducerType.NUCLEAR_POWER_PLANT, nuclearPowerPlant);

		// Add data for SolarPowerPlant
		Map<ElementType, Multiplicator> solarPowerPlant = new HashMap<>(5);
		solarPowerPlant.put(ElementType.BEACH, Multiplicator.MEDIUM);
		solarPowerPlant.put(ElementType.GRASSLAND, Multiplicator.MEDIUM);
		solarPowerPlant.put(ElementType.MOUNTAIN, Multiplicator.EXTREMELY_HIGH);
		solarPowerPlant.put(ElementType.RIVER, Multiplicator.EXTREMELY_HIGH);
		solarPowerPlant.put(ElementType.SEA, Multiplicator.EXTREMELY_HIGH);
		data.put(ProducerType.SOLAR_POWER_PLANT, solarPowerPlant);

		// Add data for WindPowerPlant
		Map<ElementType, Multiplicator> windPowerPlant = new HashMap<>(5);
		windPowerPlant.put(ElementType.BEACH, Multiplicator.LOW);
		windPowerPlant.put(ElementType.GRASSLAND, Multiplicator.MEDIUM);
		windPowerPlant.put(ElementType.MOUNTAIN, Multiplicator.EXTREMELY_HIGH);
		windPowerPlant.put(ElementType.RIVER, Multiplicator.VERY_HIGH);
		windPowerPlant.put(ElementType.SEA, Multiplicator.VERY_LOW);
		data.put(ProducerType.WIND_POWER_PLANT, windPowerPlant);

	}

	@Override
	public double calculateCost(PlayfieldElement location, ProducerType type) {
		if (location == null || type == null) {
			throw new IllegalArgumentException("Input parameters are not allowed to be null.");
		}

		double constructionCostFactor = data.get(type).get(location.getElementType()).getFactor();
		double basicConstructionCost = scenario.getEnergyNodeConfig().getBasicConstructionCost(type);
		return constructionCostFactor * basicConstructionCost;
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
