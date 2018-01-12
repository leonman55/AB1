package solutions.exercise5;

import java.util.ArrayList;

import org.sopra.api.Scenario;
import org.sopra.api.command.CannotAssignCommandException;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise5.AbstractSunEnergyBroker;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.consumer.IndustrialPark;
import org.sopra.api.model.producer.SolarPowerPlant;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */

public class SunEnergyBroker extends AbstractSunEnergyBroker implements ExerciseSubmission
{
	@Override
	public void executionPhase(Scenario scenario, int round)
	{
		
		// Alle Parks und Plants finden
		ArrayList<SolarPowerPlant> plants = new ArrayList<>();
		ArrayList<IndustrialPark> parks = new ArrayList<>();

		for (EnergyNode node : scenario.getGraph().getNodes())
		{
			if (node instanceof SolarPowerPlant)
			{
				plants.add((SolarPowerPlant) node);
			}
			else if (node instanceof IndustrialPark)
			{
				parks.add((IndustrialPark) node);
			}
		}

		// Verfuegbare und benoetigte Energie feststellen
		double[] intensitaet = scenario.getStatistics().getSunIntensityPerDay();
		double[] energieVerfuegbar = new double[24];

		// Energiegewinn ueber einen Tag simulieren
		for (int i = 0; i < 24; i++)
		{
			for (SolarPowerPlant plant : plants)
			{
				energieVerfuegbar[i] = energieVerfuegbar[i] + plant.getMaximumEnergyLevel() * intensitaet[i];
			}
		}
		
		// Verbrauch fuer verschiedene Prozentsaetze simulieren
		double[] energieVerbrauch = new double[101];
		
		for (int i = 0; i <= 100; i++)
		{
			for (IndustrialPark park : parks)
			{
				energieVerbrauch[i] = energieVerbrauch[i] + park.getMaximumEnergyLevel()*((double)i/100.00);
			}
		}

		// Industrieanlagen entsprechend regeln
		if(round == 0)
		{
			for (IndustrialPark park : parks)
			{
				try
				{
					scenario.getCommandFactory().createAdjustConsumerCommand(park, -park.getMaximumEnergyLevel()).assign();
				} catch (CannotAssignCommandException e)
				{
					
				}
			}
		}
		
		int zeit = round%24;
		if (energieVerfuegbar[zeit] != energieVerfuegbar[(zeit+2)%24])
		{
			int prozent = 0;
			for(prozent=0; prozent <= 100; prozent++)
			{
				if(energieVerbrauch[prozent] > energieVerfuegbar[(zeit+2)%24])
				{
					prozent--;
					break;
				}
			}
			int aenderung = 0;
			for (IndustrialPark park : parks)
			{
				if (!park.isAdjusting())
				{
					aenderung = (int) ((energieVerbrauch[prozent]-(park.getEnergyLevel()*parks.size()))/parks.size());
					try
					{
						scenario.getCommandFactory().createAdjustConsumerCommand(park, aenderung).assign();
					} catch (Exception e)
					{
						
					}
				}
			}
		}
	}

	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

}
