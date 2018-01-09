package solutions.exercise5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//import java.util.Arrays;
//import java.util.Collections;
import java.util.List;

//import java.util.HashSet;
//import java.util.Set;

import org.sopra.api.Game;
import org.sopra.api.Scenario;
import org.sopra.api.UpgradePowerLineCostCalculator;
import org.sopra.api.exercises.ExerciseSubmission;
//import org.sopra.api.command.BuildPowerPlantCommand;
import org.sopra.api.command.CannotExecuteCommandException;
// import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.PlantLocation;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
//import org.sopra.api.model.TransformerStation;
import org.sopra.api.model.producer.ProducerType;

import solutions.exercise2.ConstructionCostCalculatorImpl;
import solutions.exercise2.PlayfieldElementComparator;
import solutions.exercise2.QuicksortImpl;

public class FirstGame implements Game, ExerciseSubmission
{

	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	public void buildPhase(Scenario scenario) throws RuntimeException
	{
		double cost = 0;

		/************************/
		/* Windkraftwerke bauen */
		/************************/

		// Alle Trafos finden
		List<PlantLocation> locations = scenario.getPlantLocations();
		List<PlayfieldElement> transformer = new ArrayList<PlayfieldElement>();
		for (PlantLocation plantLocation : locations)
		{
			if (!plantLocation.isBuilt())
			{
				transformer.add(plantLocation.getPlayfieldElement());
			}
		}

		// Weniger als 2 Baumöglichkeiten
		if (transformer.size() < 2)
		{
			throw new RuntimeException();
		}

		// günstigsten Preis für Windkraftanlagen finden
		PlayfieldElement[] transformerarr = transformer.toArray(new PlayfieldElement[transformer.size()]);

		PlayfieldElementComparator comptransformer = new PlayfieldElementComparator(ProducerType.WIND_POWER_PLANT,
				scenario);
		QuicksortImpl<PlayfieldElement> sorttransformer = new QuicksortImpl<PlayfieldElement>(comptransformer);
		sorttransformer.quicksort(transformerarr, 0, transformerarr.length - 1);

		// bauen
		ConstructionCostCalculatorImpl costcalc = new ConstructionCostCalculatorImpl(scenario);
		PlantLocation plantlocation = null;

		for (int i = transformerarr.length - 1; i > transformerarr.length - 3; i--)
		{
			// prüfen ob noch genug budget vorhanden ist
			if (cost + costcalc.calculateCost(transformerarr[i], ProducerType.WIND_POWER_PLANT) <= 20000)
			{
				// plantLocation suchen

				for (PlantLocation plantloc : scenario.getPlantLocations())
				{
					if (plantloc.getPlayfieldElement().equals(transformerarr[i]))
					{
						plantlocation = plantloc;
					}
				}

				if (plantlocation != null)
				{
					try
					{
						// bau ausfuehren
						scenario.getCommandFactory()
								.createBuildPlantCommand(plantlocation, ProducerType.WIND_POWER_PLANT).execute();
						cost = cost + costcalc.calculateCost(transformerarr[i], ProducerType.WIND_POWER_PLANT);

					}
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

		// Alle Powerlines finden, die nicht ausgebaut sind
		for (PowerLine powerline : scenario.getGraph().getEdges())
		{
			if (powerline.getType() != PowerLineType.HIGH_VOLTAGE)
			{
				powerlines.add(powerline);
			}
		}

		UpgradePowerLineCostCalculator costcalcpowerline = scenario.getCostCalculatorFactory()
				.createUpgradePowerLineCostCalculator();

		for (PowerLine powerLine : powerlines)
		{
			if (cost + costcalcpowerline.calculateCost(powerLine, scenario.getPlayfield(),
					PowerLineType.HIGH_VOLTAGE) <= 20000)
			{
				try
				{
					scenario.getCommandFactory().createUpgradeLineCommand(powerLine, PowerLineType.HIGH_VOLTAGE)
							.execute();
					;
					cost = cost + costcalcpowerline.calculateCost(powerLine, scenario.getPlayfield(),
							PowerLineType.HIGH_VOLTAGE);
				}
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
