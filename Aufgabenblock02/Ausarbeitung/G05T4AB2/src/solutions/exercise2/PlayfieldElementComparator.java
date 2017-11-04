package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

public class PlayfieldElementComparator implements Comparator<PlayfieldElement>, ExerciseSubmission
{
	private ProducerType type;
	private Scenario scenario;
	private ConstructionCostCalculatorImpl calculator;
	
	public PlayfieldElementComparator(ProducerType type, Scenario scenario) throws IllegalArgumentException
	{
		if(type == null || scenario == null)
		{
			throw new IllegalArgumentException("type and scenario must not be null!");
		}
		else
		{
			this.type = type;
			this.scenario = scenario;
			this.calculator = new ConstructionCostCalculatorImpl(this.scenario);
		}
	}
	
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	public int compare(PlayfieldElement e1, PlayfieldElement e2) throws NullPointerException
	{
		// TODO Auto-generated method stub
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
