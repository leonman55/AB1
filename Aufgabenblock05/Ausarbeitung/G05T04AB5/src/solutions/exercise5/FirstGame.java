package solutions.exercise5;

import java.util.ArrayList;
import java.util.List;

import org.sopra.api.Game;
import org.sopra.api.Scenario;
import org.sopra.api.UpgradePowerLineCostCalculator;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.command.CannotExecuteCommandException;
import org.sopra.api.model.PlantLocation;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.producer.ProducerType;

import solutions.exercise2.ConstructionCostCalculatorImpl;
import solutions.exercise2.PlayfieldElementComparator;
import solutions.exercise2.QuicksortImpl;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class FirstGame implements Game, ExerciseSubmission
{

	/**
	 * returns the specific identification phrase for the team
	 * @return team identification String
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	/**
	 * Trys to build WIND_POWER_PLANT PlayfieldElement objects onto transformer stations and upgrade PowerLine objects to type HIGH_VOLTAGE bevore scenario starts
	 * @param scenario the scenario corresponding to the current playfield
	 * @throws CannotExecuteCommandException if something went wrong during upgrade or build method
	*/
	@Override
	public void buildPhase(Scenario scenario) throws RuntimeException
	{
		double cost = 0;

		/************************/
		/* Windkraftwerke bauen */
		/************************/

		//find all energy node locations
		List<PlantLocation> locations = scenario.getPlantLocations();
		List<PlayfieldElement> transformer = new ArrayList<PlayfieldElement>();
		//Goes through every plant location found
		for (PlantLocation plantLocation : locations)
		{
			//Searches for transformer station PlayfieldElement objects
			if (!plantLocation.isBuilt())
			{
				transformer.add(plantLocation.getPlayfieldElement());
			}
		}

		//if there are less then two transformer locations left throw an RuntimeException
		if (transformer.size() < 2)
		{
			throw new RuntimeException();
		}

		// guenstigsten Preis fuer Windkraftanlagen finden
		//Convert ArrayList of transformer stations to Array
		PlayfieldElement[] transformerarr = transformer.toArray(new PlayfieldElement[transformer.size()]);

		//New Comparator for WIND_POWER_PLANT PlayfieldElement objects
		PlayfieldElementComparator comptransformer = new PlayfieldElementComparator(ProducerType.WIND_POWER_PLANT,
				scenario);
		//Quicksort for WIND_POWER_PLANT Comparator
		QuicksortImpl<PlayfieldElement> sorttransformer = new QuicksortImpl<PlayfieldElement>(comptransformer);
		//Execution for Quicksort algorithm
		sorttransformer.quicksort(transformerarr, 0, transformerarr.length - 1);

		// bauen
		ConstructionCostCalculatorImpl costcalc = new ConstructionCostCalculatorImpl(scenario);
		PlantLocation plantlocation = null;

		for (int i = transformerarr.length - 1; i > transformerarr.length - 3; i--)
		{
			//calculate, if budget is sufficient
			if (cost + costcalc.calculateCost(transformerarr[i], ProducerType.WIND_POWER_PLANT) <= 20000)
			{

				//if budget fits search for location
				for (PlantLocation plantloc : scenario.getPlantLocations())
				{
					if (plantloc.getPlayfieldElement().equals(transformerarr[i]))
					{
						plantlocation = plantloc;
					}
				}

				//if a location was found try to build 
				if (plantlocation != null)
				{
					try
					{
						//build plant
						scenario.getCommandFactory()
								.createBuildPlantCommand(plantlocation, ProducerType.WIND_POWER_PLANT).execute();
						cost = cost + costcalc.calculateCost(transformerarr[i], ProducerType.WIND_POWER_PLANT);

					}
					//if an error occurs throw ConnotEcecuteCommandException
					catch (CannotExecuteCommandException e)
					{

					}

				}
				else
				{
					// System.out.println("Plantlocation ist null");
				}
			}
			else
			{
				// System.out.println("Baukosten ueberschritten");
			}
		}

		// for (PlayfieldElement playfieldElement : transformerarr)
		// {
		// System.out.println(playfieldElement.getElementType());
		// }
		// System.out.println("-------------");
		// System.out.println("_______________________________");

		/************************/
		/* Ausbau der Leitungen */
		/************************/

		List<PowerLine> powerlines = new ArrayList<>();

		//search for all PowerLine object, which are not of type HIGH_Voltage
		for (PowerLine powerline : scenario.getGraph().getEdges())
		{
			if (powerline.getType() != PowerLineType.HIGH_VOLTAGE)
			{
				powerlines.add(powerline);
			}
		}

		//new CostCalculator for PowerLine objects
		UpgradePowerLineCostCalculator costcalcpowerline = scenario.getCostCalculatorFactory()
				.createUpgradePowerLineCostCalculator();

		//go through all found non HIGH_VOLTAGE PowerLine objects
		for (PowerLine powerLine : powerlines)
		{
			//If cost does not exceed 20000 try to upgrade PowerLine object to type HIGH_VOLTAGE
			if (cost + costcalcpowerline.calculateCost(powerLine, scenario.getPlayfield(),
					PowerLineType.HIGH_VOLTAGE) <= 20000)
			{
				try
				{
					//upgrade PowerLine to type HIGH_VOLTAGE
					scenario.getCommandFactory().createUpgradeLineCommand(powerLine, PowerLineType.HIGH_VOLTAGE)
							.execute();
					
					//update cost variable
					cost = cost + costcalcpowerline.calculateCost(powerLine, scenario.getPlayfield(),
							PowerLineType.HIGH_VOLTAGE);
				}
				
				//if an error occurs throw ConnotEcecuteCommandException
				catch (CannotExecuteCommandException e)
				{

				}
			}
		}
	}

	@Override
	public void executionPhase(Scenario scenario, int round)
	{
		// TODO Auto-generated method stub

	}

}
