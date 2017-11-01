package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleEnergyNode;

public abstract class SimpleEnergyNodeImpl implements SimpleEnergyNode, ExerciseSubmission
{
	private final int x, y;
	protected int energyLevel;
	
	protected SimpleEnergyNodeImpl(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.energyLevel = 0;
	}
	
	@Override
	public String getTeamIdentifier()
	{
		// TODO Auto-generated method stub
		return "G05T04";
	}

	@Override
	public int getEnergyLevel()
	{
		// TODO Auto-generated method stub
		return this.energyLevel;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getXPos()
	{
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getYPos()
	{
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public void setEnergyLevel(int energyLevel)
	{
		// TODO Auto-generated method stub
		this.energyLevel = energyLevel;
	}

}
