package solutions.exercise5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Game;
import org.sopra.api.Scenario;
import org.sopra.api.UpgradePowerLineCostCalculator;
import org.sopra.api.command.CannotExecuteCommandException;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlantLocation;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.producer.ProducerType;

import solutions.exercise2.ConstructionCostCalculatorImpl;
import solutions.exercise2.PlayfieldElementComparator;
import solutions.exercise2.QuicksortImpl;

public class FirstGame implements Game, ExerciseSubmission {

	@Override
	public void buildPhase(Scenario scenario) {
		// Build wind energy power plants
		buildWindEnergyPlants(scenario);

		// Upgrade power lines
		buildPowerLines(scenario);
	}

	private void buildWindEnergyPlants(Scenario scenario) {
		// Get all PlayfieldElements from Scenario for Sorting
		PlayfieldElement[] elements = getPlayfiedlElementArray(scenario);

		// Sort on basis of construction cost decreasingly. Expensive element
		// first.
		QuicksortImpl<PlayfieldElement> sorter = new QuicksortImpl<PlayfieldElement>(
				new PlayfieldElementComparator(ProducerType.WIND_POWER_PLANT, scenario));
		sorter.quicksort(elements, 0, elements.length - 1);

		Deque<PlayfieldElement> sortedStack = new ArrayDeque<>();
		sortedStack.addAll(Arrays.asList(elements));

		ConstructionCostCalculator calc = new ConstructionCostCalculatorImpl(scenario);

		// Build WindPowerPlants in decreasing order
		boolean overBudget = false;

		// only build two plants
		int countBuilt = 2;

		while (!sortedStack.isEmpty() && !overBudget && countBuilt > 0) {

			PlayfieldElement e = sortedStack.pollLast();
			PlantLocation target = getPlantLocation(scenario.getPlantLocations(), e);

			// Skip if no transformer station is found on the playfield
			// element.
			if (target != null) {

				// Calculate cost for building new plant.
				double estimatedCost = calc.calculateCost(e, ProducerType.WIND_POWER_PLANT);

				if (Math.abs(scenario.getStatistics().getOverallScore()) + estimatedCost <= 20000) {
					try {

						scenario.getCommandFactory().createBuildPlantCommand(target, ProducerType.WIND_POWER_PLANT)
								.execute();
						countBuilt--;
					} catch (CannotExecuteCommandException e1) {
						// try next
					}
				} else {
					overBudget = true;
				}
			}
		}
	}

	private void buildPowerLines(Scenario scenario) {
		final UpgradePowerLineCostCalculator calc = scenario.getCostCalculatorFactory()
				.createUpgradePowerLineCostCalculator();

		// Create sorted set with comparator to sort power lines according to
		// their costs for upgrading to HIGH_VOLTAGE

		List<PowerLine> lines = new ArrayList<>();

		// Only upgrade lines which are not high voltage
		for (PowerLine line : scenario.getGraph().getEdges()) {
			if (line.getType() != PowerLineType.HIGH_VOLTAGE) {
				lines.add(line);
			}
		}

		Collections.sort(lines, new Comparator<PowerLine>() {

			@Override
			public int compare(PowerLine o1, PowerLine o2) {
				double cost1 = calc.calculateCost(o1, scenario.getPlayfield(), PowerLineType.HIGH_VOLTAGE);
				double cost2 = calc.calculateCost(o2, scenario.getPlayfield(), PowerLineType.HIGH_VOLTAGE);

				return Double.compare(cost1, cost2);
			}
		});

		for (PowerLine l : lines) {
			// Calculate cost for upgrading power line.
			double estimatedCost = calc.calculateCost(l, scenario.getPlayfield(), PowerLineType.HIGH_VOLTAGE);

			// Costs are negative
			if (Math.abs(scenario.getStatistics().getOverallScore()) + estimatedCost <= 20000) {
				try {
					scenario.getCommandFactory().createUpgradeLineCommand(l, PowerLineType.HIGH_VOLTAGE).execute();
				} catch (CannotExecuteCommandException e) {
					e.printStackTrace();
				}
			} else {
				// stop upgrading lines if budget limit is exceeded
				break;
			}
		}
	}

	/**
	 * Get all buildable PlayfieldElements from given scenario and store them in
	 * an array.
	 * 
	 * @param scenario
	 * @return
	 */
	private PlayfieldElement[] getPlayfiedlElementArray(Scenario scenario) {
		List<PlayfieldElement> ret = new ArrayList<PlayfieldElement>();

		for (PlantLocation loc : scenario.getPlantLocations()) {
			if (loc.getTransformerStation().isPresent() && !loc.isBuilt()) {
				ret.add(loc.getPlayfieldElement());
			}
		}

		if (ret.size() < 2) {
			throw new RuntimeException("Not enough plant locations found in scenario.");
		}

		return ret.toArray(new PlayfieldElement[ret.size()]);
	}

	/**
	 * Get the plant location with the given playfield element.
	 * 
	 * @param locs
	 *            the PlantLocations where pair of playfield element and
	 *            transformer station are stored in
	 * @param element
	 *            the playfield element on which transformer station is located.
	 * @return the playfield location or <code>null</code> if no plant location
	 *         was found or a plant was already built..
	 */
	private PlantLocation getPlantLocation(List<PlantLocation> locs, PlayfieldElement element) {
		for (PlantLocation loc : locs) {
			if (loc.getPlayfieldElement().equals(element) && loc.getTransformerStation().isPresent()) {
				if (!loc.isBuilt()) {
					return loc;
				}

			}
		}
		return null;
	}

	@Override
	public void executionPhase(Scenario scenario, int round) {
		// Not to be implemented
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
