package solutions.exercise1;

import java.util.ArrayList;
import java.util.List;

import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.ScenarioUtil;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.Producer;

public class ScenarioUtilImpl implements ExerciseSubmission, ScenarioUtil {

	/**
	 * Returns all PlayfieldElements of the given scenario and type in a List.
	 * 
	 * @param scenario {@link Scenario}
	 * @param type {@link ElementType}
	 * @return {@link List} of {@link PlayfieldElement} of given type
	 */
	@Override
	public List<PlayfieldElement> getPlayfieldElementsByType(Scenario scenario, ElementType type) {
		if (scenario == null || type == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<PlayfieldElement> ret = new ArrayList<>();
		for (int x = 0; x < scenario.getPlayfield().getHorizontalSize(); x++) {
			for (int y = 0; y < scenario.getPlayfield().getVerticalSize(); y++) {
				PlayfieldElement element = scenario.getPlayfield().getPlayfieldElement(x, y);
				if (element.getElementType() == type) {
					ret.add(element);
				}
			}
		}
		return ret;
	}

	/**
	 * Returns all PowerLines of the given graph and type in a List.
	 * 
	 * @param graph {@link Graph}
	 * @param type {@link PowerLineType} of interest
	 * @return {@link List} of {@link PowerLine} of given type
	 */
	@Override
	public List<PowerLine> getPowerLinesByType(Graph<EnergyNode, PowerLine> graph, PowerLineType type) {
		if (graph == null || type == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<PowerLine> ret = new ArrayList<>();
		for (PowerLine line : graph.getEdges()) {
			if (line.getType() == type)
				ret.add(line);
		}
		return ret;
	}

	/**
	 * Returns all ControllableProducers of the given graph in a List.
	 * 
	 * @param graph {@link Graph}
	 * @return {@link List} of {@link ControllableProducer}
	 */
	@Override
	public List<ControllableProducer> getControllableProducers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<ControllableProducer> ret = new ArrayList<ControllableProducer>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof ControllableProducer) {
				ret.add((ControllableProducer) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all ControllableConsumers of the given graph in a List.
	 * 
	 * @param graph {@link Graph}
	 * @return {@link List} of {@link ControllableConsumer}
	 */
	@Override
	public List<ControllableConsumer> getControllableConsumers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<ControllableConsumer> ret = new ArrayList<ControllableConsumer>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof ControllableConsumer) {
				ret.add((ControllableConsumer) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all Producers of the given graph in a List.
	 * 
	 * @param graph {@link Graph}
	 * @return {@link List} of {@link Producer}
	 */
	@Override
	public List<Producer> getProducers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<Producer> ret = new ArrayList<Producer>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof Producer) {
				ret.add((Producer) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all Consumers of the given graph in a List.
	 * 
	 * @param graph {@link Graph}
	 * @return {@link List} of {@link Consumer}
	 */
	@Override
	public List<Consumer> getConsumers(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<Consumer> ret = new ArrayList<Consumer>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof Consumer) {
				ret.add((Consumer) node);
			}
		}
		return ret;
	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
