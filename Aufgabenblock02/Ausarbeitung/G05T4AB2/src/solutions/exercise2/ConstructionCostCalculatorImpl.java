package solutions.exercise2;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.producer.ProducerType;

public class ConstructionCostCalculatorImpl implements ConstructionCostCalculator, ExerciseSubmission
{
	private static double extremelyLow = 0.5;
	private static double veryLow = 0.8;
	private static double low = 0.9;
	private static double medium = 1;
	private static double high = 1.1;
	private static double veryHigh = 1.5;
	private static double extremelyHigh = 2;

	private Scenario scenario;

	public ConstructionCostCalculatorImpl(Scenario scenario) throws IllegalArgumentException
	{
		if(scenario == null)
		{
			throw new IllegalArgumentException("scenario must not be null!");
		}
		else
		{
			this.scenario = scenario;
		}
	}

	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	public double calculateCost(PlayfieldElement location, ProducerType type) throws IllegalArgumentException
	{
		// TODO
		if (location == null || type == null)
		{
			throw new IllegalArgumentException("location and type must not be null!");
		}
		else
		{
			double basicCost = scenario.getEnergyNodeConfig().getBasicConstructionCost(type);
			double costFactor = 1;
			ElementType locationType = location.getElementType();
			switch (type)
			{
				case BIOGAS_FIRED_POWER_PLANT:
				{
					switch (locationType)
					{
						case BEACH:
						{
							costFactor = high;
							break;
						}
						case GRASSLAND:
						{
							costFactor = medium;
							break;
						}
						case MOUNTAIN:
						{
							costFactor = extremelyHigh;
							break;
						}
						case RIVER:
						{
							costFactor = extremelyHigh;
							break;
						}
						case SEA:
						{
							costFactor = extremelyHigh;
							break;
						}
					}
					break;
				}
				case COALFIRED_POWER_PLANT:
				{
					switch (locationType)
					{
						case BEACH:
						{
							costFactor = high;
							break;
						}
						case GRASSLAND:
						{
							costFactor = medium;
							break;
						}
						case MOUNTAIN:
						{
							costFactor = extremelyHigh;
							break;
						}
						case RIVER:
						{
							costFactor = medium;
							break;
						}
						case SEA:
						{
							costFactor = extremelyHigh;
							break;
						}
					}
					break;
				}
				case GASFIRED_POWER_PLANT:
				{
					switch (locationType)
					{
						case BEACH:
						{
							costFactor = high;
							break;
						}
						case GRASSLAND:
						{
							costFactor = medium;
							break;
						}
						case MOUNTAIN:
						{
							costFactor = extremelyHigh;
							break;
						}
						case RIVER:
						{
							costFactor = extremelyHigh;
							break;
						}
						case SEA:
						{
							costFactor = extremelyHigh;
							break;
						}
					}
					break;
				}
				case HYDRO_POWER_PLANT:
				{
					switch (locationType)
					{
						case BEACH:
						{
							costFactor = low;
							break;
						}
						case GRASSLAND:
						{
							costFactor = extremelyHigh;
							break;
						}
						case MOUNTAIN:
						{
							costFactor = extremelyHigh;
							break;
						}
						case RIVER:
						{
							costFactor = veryLow;
							break;
						}
						case SEA:
						{
							costFactor = medium;
							break;
						}
					}
					break;
				}
				case NUCLEAR_POWER_PLANT:
				{
					switch (locationType)
					{
						case BEACH:
						{
							costFactor = medium;
							break;
						}
						case GRASSLAND:
						{
							costFactor = high;
							break;
						}
						case MOUNTAIN:
						{
							costFactor = extremelyHigh;
							break;
						}
						case RIVER:
						{
							costFactor = medium;
							break;
						}
						case SEA:
						{
							costFactor = extremelyHigh;
							break;
						}
					}
					break;
				}
				case SOLAR_POWER_PLANT:
				{
					switch (locationType)
					{
						case BEACH:
						{
							costFactor = medium;
							break;
						}
						case GRASSLAND:
						{
							costFactor = medium;
							break;
						}
						case MOUNTAIN:
						{
							costFactor = extremelyHigh;
							break;
						}
						case RIVER:
						{
							costFactor = extremelyHigh;
							break;
						}
						case SEA:
						{
							costFactor = extremelyHigh;
							break;
						}
					}
					break;
				}
				case WIND_POWER_PLANT:
				{
					switch (locationType)
					{
						case BEACH:
						{
							costFactor = low;
							break;
						}
						case GRASSLAND:
						{
							costFactor = medium;
							break;
						}
						case MOUNTAIN:
						{
							costFactor = extremelyHigh;
							break;
						}
						case RIVER:
						{
							costFactor = veryHigh;
							break;
						}
						case SEA:
						{
							costFactor = veryLow;
							break;
						}
					}
					break;
				}
			}
			return basicCost * costFactor;
		}
	}
}