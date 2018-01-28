package solutions.exercise6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sopra.api.CostCalculatorFactory;
import org.sopra.api.Game;
import org.sopra.api.Multiplicator;
import org.sopra.api.Scenario;
import org.sopra.api.command.CannotAssignCommandException;
import org.sopra.api.command.CannotExecuteCommandException;
import org.sopra.api.exercises.ExerciseSubmission;
import solutions.exercise1.ScenarioUtilImpl;
import solutions.exercise2.ConstructionCostCalculatorImpl;
import solutions.exercise2.PlayfieldElementComparator;

import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.PlantLocation;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.TransformerStation;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.Producer;
import org.sopra.api.model.producer.ProducerType;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class ScenarioImpl implements Game, ExerciseSubmission
{

	/**
	 * returns the specific team identification
	 * @return team identification string
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	/**
	 * Once executed before the scenario is started
	 * @param scenario the current scenario
	 * @throws CannotExecuteCommandException is thrown if a build command can not be executed
	 */
	@Override
	public void buildPhase(Scenario scenario)
	{
		ScenarioUtilImpl util = new ScenarioUtilImpl();
		List<PowerLine> lowLines = util.getPowerLinesByType(scenario.getGraph(), PowerLineType.LOW_VOLTAGE);
		List<PowerLine> mediumLines = util.getPowerLinesByType(scenario.getGraph(), PowerLineType.MEDIUM_VOLTAGE);
		List<PowerLine> highLines = util.getPowerLinesByType(scenario.getGraph(), PowerLineType.HIGH_VOLTAGE);
		List<Consumer> consumers = util.getConsumers(scenario.getGraph());
		List<ControllableConsumer> controllableConsumers = util.getControllableConsumers(scenario.getGraph());
		List<Producer> producers = util.getProducers(scenario.getGraph());
		List<ControllableProducer> controllableProducers = util.getControllableProducers(scenario.getGraph());
		HashMap<TransformerStation, PlayfieldElement> transformerStations = new HashMap<TransformerStation, PlayfieldElement>();
		for(EnergyNode node : scenario.getGraph().getNodes())
		{
			if(node instanceof TransformerStation)
			{
//				System.out.println("TransformerStation: " + node.getName());
				transformerStations.put((TransformerStation) node, scenario.getPlayfield().getPlayfieldElement(node.getXPos(), node.getYPos()));
			}
		}
		
		int maximumLoadConsumers = 0;
		int maximumLoadControllableConsumers = 0;
		int maximumLoadProducers = 0;
		int maximumLoadControllableProducers = 0;
		for(Consumer con : consumers)
		{
			System.out.println("Consumer: " + con.getName());
			maximumLoadConsumers += con.getMaximumEnergyLevel();
		}
		for(ControllableConsumer con : controllableConsumers)
		{
			System.out.println("ControllableConsumer: " + con.getName());
			maximumLoadControllableConsumers += con.getMaximumEnergyLevel();
		}
		for(Producer prod : producers)
		{
			System.out.println("Producer: " + prod.getName());
			maximumLoadProducers += prod.getMaximumEnergyLevel();
		}
		for(ControllableProducer prod : controllableProducers)
		{
			System.out.println("ControllableProducer: " + prod.getName());
			maximumLoadControllableProducers += prod.getMaximumEnergyLevel();
		}
		System.out.println(maximumLoadConsumers);
		System.out.println(maximumLoadControllableConsumers);
		System.out.println(maximumLoadProducers);
		System.out.println(maximumLoadControllableProducers);
		
		System.out.println("tranformerStations.size() = " + transformerStations.size());
		ConstructionCostCalculatorImpl calculateCost = new ConstructionCostCalculatorImpl(scenario);
		if(transformerStations.size() > 0)
		{
			System.out.println("Going through all TransformerStation objects in transformerStations HashMap");
			for(TransformerStation transformerStation : transformerStations.keySet())
			{
				double lowestCost = Double.MAX_VALUE;
				ProducerType lowestCostType = null;
				System.out.println("Initialisation of lowestCost and lowestCostType succesfull");
				for(ProducerType type : ProducerType.values())
				{
					System.out.println("Going through every ProducerType: " + type.name() + "     ElementType: " + transformerStations.get(transformerStation).getElementType());
					System.out.println(buildLocationDoesMatchPlantType(transformerStations.get(transformerStation).getElementType(), type));
					if(buildLocationDoesMatchPlantType(transformerStations.get(transformerStation).getElementType(), type))
					{
						System.out.println("ElementType matches ProducerType " + buildLocationDoesMatchPlantType(transformerStations.get(transformerStation).getElementType(), type));
						if(calculateCost.calculateCost(transformerStations.get(transformerStation), type) < lowestCost)
						{
							lowestCost = calculateCost.calculateCost(transformerStations.get(transformerStation), type);
							lowestCostType = type;
							System.out.println("Type for lowestCost was found for: " + lowestCostType.name());
						}
					}
				}
				if(scenario.getStatistics().getOverallScore() + lowestCost < 20000)
				{
					System.out.println("Maximum Cost was not exceeded" + scenario.getStatistics().getOverallScore() + " lowestCost: " + lowestCost);
					PlantLocation buildLocation = null;
					for(PlantLocation location : scenario.getPlantLocations())
					{
						if(transformerStations.get(transformerStation).equals(location.getPlayfieldElement()))
						{
							System.out.println("Found build location");
							buildLocation = location;
						}
					}
					if(buildLocation != null && lowestCostType != null)
					{
						try
						{
							scenario.getCommandFactory().createBuildPlantCommand(buildLocation, lowestCostType).execute();
							System.out.println("Build Command executed");
						}
						catch (CannotExecuteCommandException e)
						{
							
						}
					}
				}
			}
		}
		System.out.println(lowLines.size());
		if(lowLines.size() != 0)
		{
			for(PowerLine line : lowLines)
			{
				try
				{
					scenario.getCommandFactory().createUpgradeLineCommand(line, PowerLineType.HIGH_VOLTAGE).execute();
					System.out.println("lowLine upgraded");
				}
				catch(CannotExecuteCommandException e)
				{
					
				}
			}
		}
		System.out.println(mediumLines.size());
		if(mediumLines.size() != 0)
		{
			for(PowerLine line : mediumLines)
			{
				try
				{
					scenario.getCommandFactory().createUpgradeLineCommand(line, PowerLineType.HIGH_VOLTAGE).execute();
					System.out.println("meduimLine upgraded");
				}
				catch(CannotExecuteCommandException e)
				{
					
				}
			}
		}
	}

	/**
	 * Function, which calculates if the build location matches the type of producer
	 * @param element type of ground element
	 * @param type type of producer
	 * @return true if element and type do match, false otherwise
	 */
	private boolean buildLocationDoesMatchPlantType(ElementType element, ProducerType type)
	{
		if(element == null)
		{
			System.out.println("ElementType == null");
			return false;
		}
		else if(type == null)
		{
			System.out.println("ProducerType == null");
			return false;
		}
		switch (element)
		{
			case BEACH:
			{
				switch (type)
				{
					case GASFIRED_POWER_PLANT:
					{
						return true;
					}
					case HYDRO_POWER_PLANT:
					{
						return true;
					}
					case NUCLEAR_POWER_PLANT:
					{
						return true;
					}
					case SOLAR_POWER_PLANT:
					{
						return true;
					}
					case WIND_POWER_PLANT:
					{
						return true;
					}
					default:
					{
						return false;
					}
				}
			}
			case GRASSLAND:
			{
				switch (type)
				{
					case BIOGAS_FIRED_POWER_PLANT:
					{
						return true;
					}
					case COALFIRED_POWER_PLANT:
					{
						return true;
					}
					case GASFIRED_POWER_PLANT:
					{
						return true;
					}
					case NUCLEAR_POWER_PLANT:
					{
						return true;
					}
					case SOLAR_POWER_PLANT:
					{
						return true;
					}
					case WIND_POWER_PLANT:
					{
						return true;
					}
					default:
					{
						return false;
					}
				}
			}
			case MOUNTAIN:
			{
				switch (type)
				{
					case COALFIRED_POWER_PLANT:
					{
						return true;
					}
					case GASFIRED_POWER_PLANT:
					{
						return true;
					}
					case SOLAR_POWER_PLANT:
					{
						return true;
					}
					case WIND_POWER_PLANT:
					{
						return true;
					}
					default:
					{
						return false;
					}	
				}
			}
			case RIVER:
			{
				switch (type)
				{
					case BIOGAS_FIRED_POWER_PLANT:
					{
						return true;
					}
					case COALFIRED_POWER_PLANT:
					{
						return true;
					}
					case GASFIRED_POWER_PLANT:
					{
						return true;
					}
					case HYDRO_POWER_PLANT:
					{
						return true;
					}
					case NUCLEAR_POWER_PLANT:
					{
						return true;
					}
					case SOLAR_POWER_PLANT:
					{
						return true;
					}
					case WIND_POWER_PLANT:
					{
						return true;
					}
					default:
					{
						return false;
					}
				}
			}
			case SEA:
			{
				switch (type)
				{
					case HYDRO_POWER_PLANT:
					{
						return true;
					}
					case SOLAR_POWER_PLANT:
					{
						return true;
					}
					case WIND_POWER_PLANT:
					{
						return true;
					}
					default:
					{
						return false;
					}
				}
			}
			default:
			{
				return false;
			}
		}
	}
	
	/**
	 * called every round, should regulate the controllable producers and controllable consumers to match energy production and consumption
	 * @param scenario the current scenario
	 * @param round the current round
	 * @throws CannotAssignCommandException thrown if a assign command fails
	 */
	@Override
	public void executionPhase(Scenario scenario, int round)
	{
		ScenarioUtilImpl util = new ScenarioUtilImpl();
		List<PowerLine> lowLines = util.getPowerLinesByType(scenario.getGraph(), PowerLineType.LOW_VOLTAGE);
		List<PowerLine> mediumLines = util.getPowerLinesByType(scenario.getGraph(), PowerLineType.MEDIUM_VOLTAGE);
		List<PowerLine> highLines = util.getPowerLinesByType(scenario.getGraph(), PowerLineType.HIGH_VOLTAGE);
		List<Consumer> consumers = util.getConsumers(scenario.getGraph());
		List<ControllableConsumer> controllableConsumers = util.getControllableConsumers(scenario.getGraph());
		List<Producer> producers = util.getProducers(scenario.getGraph());
		List<ControllableProducer> controllableProducers = util.getControllableProducers(scenario.getGraph());
		HashMap<TransformerStation, PlayfieldElement> transformerStations = new HashMap<TransformerStation, PlayfieldElement>();
		for(EnergyNode node : scenario.getGraph().getNodes())
		{
			if(node instanceof TransformerStation)
			{
//				System.out.println("TransformerStation: " + node.getName());
				transformerStations.put((TransformerStation) node, scenario.getPlayfield().getPlayfieldElement(node.getXPos(), node.getYPos()));
			}
		}
		
		int maximumLoadConsumers = 0;
		int maximumLoadControllableConsumers = 0;
		int maximumLoadProducers = 0;
		int maximumLoadControllableProducers = 0;
		for(Consumer con : consumers)
		{
			System.out.println("Consumer: " + con.getName());
			maximumLoadConsumers += con.getMaximumEnergyLevel();
		}
		for(ControllableConsumer con : controllableConsumers)
		{
			System.out.println("ControllableConsumer: " + con.getName());
			maximumLoadControllableConsumers += con.getMaximumEnergyLevel();
		}
		for(Producer prod : producers)
		{
			System.out.println("Producer: " + prod.getName());
			maximumLoadProducers += prod.getMaximumEnergyLevel();
		}
		for(ControllableProducer prod : controllableProducers)
		{
			System.out.println("ControllableProducer: " + prod.getName());
			maximumLoadControllableProducers += prod.getMaximumEnergyLevel();
		}
		for(ControllableProducer prod : controllableProducers)
		{
			System.out.println("Current Energy:" + prod.getEnergyLevel() + "    Maximum Warp: " + prod.getMaximumEnergyLevel());
			try
			{
				scenario.getCommandFactory().createAdjustProducerCommand((ControllableProducer) prod, prod.getMaximumEnergyLevel() - prod.getEnergyLevel()).assign();
			}
			catch (CannotAssignCommandException e)
			{
				
			}
		}
		for(ControllableConsumer con : controllableConsumers)
		{
			try
			{
				scenario.getCommandFactory().createAdjustConsumerCommand((ControllableConsumer) con, con.getMaximumEnergyLevel() - con.getEnergyLevel()).assign(); 
			}
			catch(CannotAssignCommandException e)
			{
				
			}
		}
	}
}
