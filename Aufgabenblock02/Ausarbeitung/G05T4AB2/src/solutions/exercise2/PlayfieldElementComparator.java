package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */

public class PlayfieldElementComparator implements Comparator<PlayfieldElement>, ExerciseSubmission
{
	private ProducerType type;
	private Scenario scenario;
	private ConstructionCostCalculatorImpl calculator;
	
	/**
	 * Constructor for a new PlayfieldElementComparator object
	 * @param type the type of the producer
	 * @param scenario loaded Scenario from xml file
	 * @throws NullPointerException if type or scenario is null
	 */
	
	public PlayfieldElementComparator(ProducerType type, Scenario scenario) throws NullPointerException
	{
		if(type == null || scenario == null)
		{
			throw new NullPointerException("type and scenario must not be null!");
		}
		else
		{
			this.type = type;
			this.scenario = scenario;
			this.calculator = new ConstructionCostCalculatorImpl(this.scenario);
		}
	}
	
	/**
	 * Method which returns the team number of the programming team
	 * @return specific team number
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	/**
	 * Compares the production cost for a specific producer type on two different playfield locations
	 * @param e1 playfield location one
	 * @param e2 playfield location two
	 * @return {@inheritDoc}
	 */
	@Override
	public int compare(PlayfieldElement e1, PlayfieldElement e2) throws NullPointerException
	{
		if(e1 == null || e2 == null)
		{
			throw new NullPointerException("e1 and e2 must not be null!");
		}
		else
		{
			double cost1, cost2;
			int back;
			cost1 = this.calculator.calculateCost(e1, this.type);
			cost2 = this.calculator.calculateCost(e2, this.type);
			if(cost1 < cost2)
			{
				back = 1;
			}
			else if(cost1 > cost2)
			{
				back = -1;
			}
			else
			{
				back = 0;
			}
			return back;
		}
	}
}
