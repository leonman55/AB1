package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleProducer;

public class SimpleSolarPowerPlantImpl extends SimpleEnergyNodeImpl implements SimpleProducer, ExerciseSubmission
{
	public SimpleSolarPowerPlantImpl(int x, int y)
	{
		super(x, y);
	}

	@Override
	public String getProducerName()
	{
		// TODO Auto-generated method stub
		return null;
	}
}