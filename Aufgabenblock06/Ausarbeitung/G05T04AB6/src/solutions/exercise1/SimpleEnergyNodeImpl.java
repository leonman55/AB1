package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleEnergyNode;

abstract class SimpleEnergyNodeImpl implements ExerciseSubmission, SimpleEnergyNode {

	private final int x;
	private final int y;
	protected int energyLevel = 0;

	/**
	 * Protected constructor of an EnergyNode instance.
	 * 
	 * @param x Integer
	 * @param y Integer
	 */
	protected SimpleEnergyNodeImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x-coordinate (represented by an Integer) of this EnergyNode
	 */
	@Override
	public int getXPos() {
		return x;
	}

	/**
	 * @return the y-coordinate (represented by an Integer) of this EnergyNode
	 */
	@Override
	public int getYPos() {
		return y;
	}

	/**
	 * @return the energyLevel (represented by an Integer) of this EnergyNode
	 */
	@Override
	public int getEnergyLevel() {
		return energyLevel;
	}

	/**
	 * @return EnergyNode ({@link String})
	 */
	@Override
	public String getName() {
		return "EnergyNode";
	}

	/**
	 * @param energyLevel The integer value energyLevel will be set to
	 */
	@Override
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}
}
