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
import org.sopra.api.model.consumer.City;
import org.sopra.api.model.consumer.CommercialPark;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.consumer.IndustrialPark;
import org.sopra.api.model.producer.BioGasFiredPowerPlant;
import org.sopra.api.model.producer.CoalFiredPowerPlant;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.GasFiredPowerPlant;
import org.sopra.api.model.producer.HydroPowerPlant;
import org.sopra.api.model.producer.NuclearPowerPlant;
import org.sopra.api.model.producer.Producer;
import org.sopra.api.model.producer.SolarPowerPlant;
import org.sopra.api.model.producer.WindPowerPlant;

/**
 * Utility class providing useful functionality to retrieve information from a
 * scenario.
 *
 */
public class ScenarioUtilImpl implements ExerciseSubmission, ScenarioUtil {

	/**
	 * Returns all PlayfieldElements of the given scenario and type in a List.
	 * 
	 * @param scenario
	 *            a scenario
	 * @param type
	 *            type of interest
	 * @return List of PlayfieldElements of given type
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
	 * @param graph
	 *            a graph
	 * @param type
	 *            type of interest
	 * @return List of PowerLines of given type
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
	 * Returns all CoalFiredPowerPlants of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of CoalFiredPowerPlants
	 */
	@Override
	public List<CoalFiredPowerPlant> getCoalFiredPowerPlants(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<CoalFiredPowerPlant> ret = new ArrayList<CoalFiredPowerPlant>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof CoalFiredPowerPlant) {
				ret.add((CoalFiredPowerPlant) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all GasFiredPowerPlants of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of GasFiredPowerPlants
	 */
	@Override
	public List<GasFiredPowerPlant> getGasFiredPowerPlants(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<GasFiredPowerPlant> ret = new ArrayList<GasFiredPowerPlant>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof GasFiredPowerPlant) {
				ret.add((GasFiredPowerPlant) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all BioGasFiredPowerPlants of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of BioGasFiredPowerPlants
	 */
	@Override
	public List<BioGasFiredPowerPlant> getBioGasFiredPowerPlants(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<BioGasFiredPowerPlant> ret = new ArrayList<BioGasFiredPowerPlant>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof BioGasFiredPowerPlant) {
				ret.add((BioGasFiredPowerPlant) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all HydroPowerPlants of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of HydroPowerPlants
	 */
	@Override
	public List<HydroPowerPlant> getHydroPowerPlants(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<HydroPowerPlant> ret = new ArrayList<HydroPowerPlant>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof HydroPowerPlant) {
				ret.add((HydroPowerPlant) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all NuclearPowerPlants of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of NuclearPowerPlants
	 */
	@Override
	public List<NuclearPowerPlant> getNuclearPowerPlants(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<NuclearPowerPlant> ret = new ArrayList<NuclearPowerPlant>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof NuclearPowerPlant) {
				ret.add((NuclearPowerPlant) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all SolarPowerPlants of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of SolarPowerPlants
	 */
	@Override
	public List<SolarPowerPlant> getSolarPowerPlants(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<SolarPowerPlant> ret = new ArrayList<SolarPowerPlant>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof SolarPowerPlant) {
				ret.add((SolarPowerPlant) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all WindPowerPlants of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of WindPowerPlants
	 */
	@Override
	public List<WindPowerPlant> getWindPowerPlants(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<WindPowerPlant> ret = new ArrayList<WindPowerPlant>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof WindPowerPlant) {
				ret.add((WindPowerPlant) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all ControllableProducers of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of ControllableProducers
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
	 * Returns all Cities of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of Cities
	 */
	@Override
	public List<City> getCities(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<City> ret = new ArrayList<City>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof City) {
				ret.add((City) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all CommercialParks of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of CommercialParks
	 */
	@Override
	public List<CommercialPark> getCommercialParks(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<CommercialPark> ret = new ArrayList<CommercialPark>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof CommercialPark) {
				ret.add((CommercialPark) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all IndustrialParks of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of IndustrialParks
	 */
	@Override
	public List<IndustrialPark> getIndustrialParks(Graph<EnergyNode, PowerLine> graph) {
		if (graph == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		}

		List<IndustrialPark> ret = new ArrayList<IndustrialPark>();
		for (EnergyNode node : graph.getNodes()) {
			if (node instanceof IndustrialPark) {
				ret.add((IndustrialPark) node);
			}
		}
		return ret;
	}

	/**
	 * Returns all ControllableConsumers of the given graph in a List.
	 * 
	 * @param graph
	 *            a graph
	 * @return List of ControllableConsumers
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
	 * @param graph
	 *            a graph
	 * @return List of Producers
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
	 * @param graph
	 *            a graph
	 * @return List of Consumers
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
