package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleProducer;

public class SimpleSolarPowerPlantImpl extends SimpleEnergyNodeImpl implements ExerciseSubmission, SimpleProducer {

	/**
	 * Constructor of a SolarPowerPlantImpl instance.
	 * 
	 * @param x Integer
	 * @param y Integer
	 */
	public SimpleSolarPowerPlantImpl(int x, int y) {
		super(x, y);
	}

	/**
	 * @return the return value of the super type method + a {@link String} of the form ": (Type of this PowerPlant)"
	 */
	@Override
	public String getProducerName() {
		return super.getName() + ": SolarPowerPlant";
	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
