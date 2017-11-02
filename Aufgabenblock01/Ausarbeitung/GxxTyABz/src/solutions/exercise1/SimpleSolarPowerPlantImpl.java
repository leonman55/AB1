package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleProducer;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class SimpleSolarPowerPlantImpl extends SimpleEnergyNodeImpl implements SimpleProducer, ExerciseSubmission
{
	/**
	 * constructor for a new SimpleSolarPowerPlant which takes the x and y position of the node and refers it to the corresponding class variables
	 * @param x x coordinate of the node
	 * @param y y coordinate of the node
	 */
	public SimpleSolarPowerPlantImpl(int x, int y)
	{
		super(x, y);
	}

	/**
	 * getter method for the name of the node
	 * @return name
	 */
	@Override
	public String getProducerName()
	{
		return super.getName() + ": SolarPowerPlant";
	}
}