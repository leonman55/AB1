package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualEdge;

public class ResidualEdgeImpl<V> implements ResidualEdge<V>, ExerciseSubmission
{
	private V startNode;
	private V endNode;
	private int capacity;
	private ResidualEdge<V> reverse;
	
	public ResidualEdgeImpl(V startNode, V endNode, int capacity)
	{
		this.startNode = startNode;
		this.endNode = endNode;
		this.capacity = capacity;
	}
	
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	public void addFlow(int amount)
	{
		if(amount > this.capacity)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if(- amount > this.reverse.getCapacity())
			{
				throw new IllegalArgumentException();
			}
			else
			{
				this.capacity -= amount;
				this.reverse.setCapacity(this.reverse.getCapacity() + amount);
			}
		}
	}

	@Override
	public int getCapacity()
	{
		return this.capacity;
	}

	@Override
	public V getEnd()
	{
		return this.endNode;
	}

	@Override
	public V getStart()
	{
		return this.startNode;
	}

	@Override
	public void setCapacity(int capacity)
	{
		if(capacity < 0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.capacity = capacity;
		}
	}
	
	@Override
	public ResidualEdge<V> getReverse()
	{
		return this.reverse;
	}
	
	@Override
	public void setReverse(ResidualEdge<V> reverse)
	{
		this.reverse = reverse;
	}
}