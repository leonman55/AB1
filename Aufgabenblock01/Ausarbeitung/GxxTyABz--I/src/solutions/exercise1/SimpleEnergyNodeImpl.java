package solutions.exercise1;

public abstract class SimpleEnergyNodeImpl implements SimpleEnergyNode, ExerciseSubmission {
	
	private final int x; 
	private final int y;
	protected int energyLevel;
	
	protected SimpleEnergyNodeImpl(int x, int y)
	{
		this.x = x;
		this.y = y;
		energyLevel = 0;
	}

}
