package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleEnergyNode;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */
public abstract class SimpleEnergyNodeImpl implements SimpleEnergyNode, ExerciseSubmission
{
	private final int x, y;
	protected int energyLevel;
	
	/**
	 * constructor for a new SimpmleEnergyNode which takes the x and y position of the node and refers it to the corresponding class variables
	 * @param x x coordinate of the node
	 * @param y y coordinate of the node
	 */
	protected SimpleEnergyNodeImpl(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.energyLevel = 0;
	}
	
	/**
	 * returns the team identifier of this team
	 * @return team identifier
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	/**
	 * getter method for energyLevel
	 * @return energyLevel
	 */
	@Override
	public int getEnergyLevel()
	{
		return this.energyLevel;
	}

	/**
	 * getter method for the name of the node
	 * @return name
	 */
	@Override
	public String getName()
	{
		return "EnergyNode";
	}

	/**
	 * getter method for x position of the node
	 */
	@Override
	public int getXPos()
	{
		return this.x;
	}

	/**
	 * getter method for y position of the node
	 */
	@Override
	public int getYPos()
	{
		return this.y;
	}

	/**
	 * setter method for the energyLevel of the node
	 * @param energyLevel the new energyLevel
	 */
	@Override
	public void setEnergyLevel(int energyLevel)
	{
		this.energyLevel = energyLevel;
	}

}
