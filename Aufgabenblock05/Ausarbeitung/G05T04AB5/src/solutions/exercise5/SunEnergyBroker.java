/**
 * 
 */
package solutions.exercise5;

import java.util.ArrayList;
import java.util.List;

import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise5.AbstractSunEnergyBroker;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.consumer.IndustrialPark;
import org.sopra.api.model.producer.SolarPowerPlant;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 *
 */
public class SunEnergyBroker extends AbstractSunEnergyBroker implements ExerciseSubmission {

	/**
	 * 
	 */
	public SunEnergyBroker() {
		// TODO Auto-generated constructor stub
		super();
	}

	/* (non-Javadoc)
	 * @see org.sopra.api.Game#executionPhase(org.sopra.api.Scenario, int)
	 */
	@Override
	public void executionPhase(Scenario scenario, int round) {
		System.out.println(round);
		// TODO Auto-generated method stub
		
		List<SolarPowerPlant> plant = new ArrayList<>();
		List<SolarPowerPlant> plants = new ArrayList<>();
		List<IndustrialPark> consumer = new ArrayList<>();
		List<IndustrialPark> consumers = new ArrayList<>();
		List<EnergyNode> nodes = new ArrayList<>(scenario.getGraph().getNodes());
		//double sunIntensity = scenario.getStatistics().getSunIntensity():
		
		for (EnergyNode energyNode : nodes)
		{
			if(energyNode instanceof SolarPowerPlant)
			{
				plant.add((SolarPowerPlant)energyNode);
			}
			if(energyNode instanceof SolarPowerPlant)
			{
				plants.add((SolarPowerPlant)energyNode);
			}
			if(energyNode instanceof IndustrialPark)
			{
				consumer.add((IndustrialPark)energyNode);
			}
			if(energyNode instanceof IndustrialPark)
			{
				consumers.add((IndustrialPark)energyNode);
			}
		}
		
		if(round == 0)
		{
			for(IndustrialPark consumer_nr : consumer)
			{
				scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, 30);
			}
		}
		
		int hour = round % 24;
		if(consumers.size()+plants.size()==consumer.size()+plant.size())
		{
			for(SolarPowerPlant plant_nr : plant)
			{
				if((consumers.size()==consumer.size()) && (plants.size()==plant.size()))
				{
					
					for(IndustrialPark consumer_nr : consumer)
					{
						int sum = plant_nr.getProvidedPower()-consumer_nr.getRequiredPower();
						
						if(plant_nr.getProvidedPower() > consumer_nr.getRequiredPower() && hour < 12)
						{
								switch (hour)
								{
									case 1: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, +-sum);
									break;
								}
								
						}
						if(plant_nr.getProvidedPower() < consumer_nr.getRequiredPower() && hour < 12)
						{
								switch (hour)
								{
									case 1: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, +-sum);
									break;
								}
								
						}
						if(plant_nr.getProvidedPower() == consumer_nr.getRequiredPower() && hour < 12)
						{
								switch (hour)
								{
									case 1: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, +-sum);
									break;
								}
								
						}
						
						
					}
				}
			}
		}
			
			
			
			
			
			
//			if((consumers.size()==consumer.size())==(plants.size()==plant.size()))
//			{
//				for(IndustrialPark consumer_nr : consumer)
//				{
//					switch (hour)
//					{
//						case 1: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, 30);
//						break;
//						case 4: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, 30);
//						break;
//						case 7: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, 30);
//						break;
//						case 12: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, -30);
//						break;
//						case 15: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, -30);
//						break;
//						case 19: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, -30);
//						break;
//						
//					}
//				}
//			}
//			
//			if((consumers.size()==consumer.size()) && (plants.size()==plant.size()) && (consumer.size() > plant.size()))
//			{
//				for(IndustrialPark consumer_nr : consumer)
//				{
//					switch (hour)
//					{
//						case 1: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, 30);
//						break;
//						case 4: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, 30);
//						break;
//						case 7: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, 30);
//						break;
//						case 12: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, -30);
//						break;
//						case 15: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, -30);
//						break;
//						case 19: scenario.getCommandFactory().createAdjustConsumerCommand(consumer_nr, -30);
//						break;
//						
//					}
//				}
//			}
//			
			

		//TransformerStation
		//System.out.println(scenario.getStatistics().getConsumerLoad()); 
		
		//superquelle und senke erstellen.... ->superSink, superSource
		
		//verschiedene Fälle (switch case..) für verschiedene Tageszeiten/Runden 
		//-> Konsumenten auf Prozentualen Anteil regeln sodass der angeforderte Anteil lieferbar ist 
		
		//Runde 0: Bauphase Sprung zu Runde 1 werden alle Regelungen übernommen. anforderungen auf maximalem Bedarf
		//wenn das Solarkraftwerk gerade nichts liefert muss der Industrie Park runtergeregelt werden
	}

	/* (non-Javadoc)
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		// TODO Auto-generated method stub
		return "G05T04";
	}

}