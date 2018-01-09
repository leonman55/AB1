package solutions.exercise5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sopra.api.Scenario;
import org.sopra.api.command.CannotAssignCommandException;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise5.AbstractEnergyNetworkAnalyzer;
import org.sopra.api.exercises.exercise5.AbstractSunEnergyBroker;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.IndustrialPark;
import org.sopra.api.model.producer.Producer;
import org.sopra.api.model.producer.SolarPowerPlant;

public class SunEnergyBroker extends AbstractSunEnergyBroker implements ExerciseSubmission {

	private double[] sunIntensity;

	private Map<Integer, Map<Consumer, Integer>> cached = new HashMap<>();

	private Logger logger = LogManager.getLogger();

	@Override
	public void executionPhase(Scenario scenario, int round) {
		sunIntensity = scenario.getStatistics().getSunIntensityPerDay();

		List<IndustrialPark> parks = scenario.getGraph().getNodes().stream().filter(n -> n instanceof IndustrialPark).map(n -> (IndustrialPark) n).collect(Collectors.toList());

		if (round == 0) {
			preProcess(scenario);

			for (IndustrialPark p : parks) {
				int amount = p.getRequiredEnergyLevel();
				try {
					scenario.getCommandFactory().createAdjustConsumerCommand(p, -amount).assign();
				} catch (CannotAssignCommandException e) {
					e.printStackTrace();
					throw new RuntimeException("Error in round " + round + " not expected to happen.");
				}
			}
		}

		for (IndustrialPark park : parks) {
			int hour = round % 24;

			int forcastIndex;
			if (hour + park.getAdjustmentTime() - 1 < sunIntensity.length) {
				forcastIndex = hour + park.getAdjustmentTime() - 1;
			} else {
				forcastIndex = hour + park.getAdjustmentTime() - 1 - sunIntensity.length;
			}
			logger.debug("Sun intensity at " + hour + ": " + sunIntensity[hour] + " --- forcasted sun intensity at "
					+ forcastIndex + ": " + sunIntensity[forcastIndex]);

			if (sunIntensity[hour] != sunIntensity[forcastIndex] && !park.isAdjusting()) {
				int amount = ((park.getRequiredEnergyLevel() - cached.get(forcastIndex).get(park)));
				try {
					logger.debug("Issue command in round " + round + " with amount of " + -amount + " for round "
							+ forcastIndex);
					scenario.getCommandFactory().createAdjustConsumerCommand(park, -amount).assign();
				} catch (CannotAssignCommandException e) {
					e.printStackTrace();
					throw new RuntimeException("Error in round " + round + " not expected to happen.");
				}
			}
		}
	}

	/**
	 * Preprocess maximum provided values for industrial parks for a whole day.
	 * 
	 * @param scenario
	 */
	private void preProcess(Scenario scenario) {
		List<SolarPowerPlant> plants = scenario.getGraph().getNodes().stream().filter(n -> n instanceof SolarPowerPlant).map(n -> (SolarPowerPlant) n).collect(Collectors.toList());

		for (int hour = 0; hour < 24; hour++) {
			Map<Producer, Integer> producerCapacities = new HashMap<>();
			for (SolarPowerPlant plant : plants) {
				logger.debug(
						"Prod capacity in round  " + hour + " " + plant.getMaximumEnergyLevel() * sunIntensity[hour]);
				producerCapacities.put(plant, (int) (plant.getMaximumEnergyLevel() * sunIntensity[hour]));
			}

			Map<Consumer, Integer> requiredCap = simulateWith(scenario.getGraph(), producerCapacities);

			StringBuffer logMsg = new StringBuffer();
			logMsg.append("in hour " + hour + " ");
			requiredCap.keySet().forEach(c -> {
				logMsg.append("Consumer " + c.getName() + " requires " + requiredCap.get(c));
			});
			logger.debug(logMsg);

			cached.put(hour, requiredCap);
		}
	}

	private Map<Consumer, Integer> simulateWith(Graph<EnergyNode, PowerLine> graph,Map<Producer, Integer> producerCapacities) {
		AbstractEnergyNetworkAnalyzer analyzer = new EnergyNetworkAnalyzerImpl(graph, Optional.of(producerCapacities),Optional.empty());
		analyzer.calculateMaxFlow();
		return analyzer.getConsumerLevels();
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
