package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;

public class FlowEdgeImpl<V> implements FlowEdge<V>, ExerciseSubmission
{
	private V start;
	private V end;
	private int capacity;
	private int flow;
	
	public FlowEdgeImpl(V start, V end, int capacity)
	{
		if(start == null || end == null || capacity < 1)
		{
			throw new IllegalArgumentException("Arguments must not be null");
		}
		else
		{
			this.start = start;
			this.end = end;
			this.capacity = capacity;
			this.flow = 0;
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
		return this.end;
	}

	@Override
	public int getFlow()
	{
		return this.flow;
	}

	@Override
	public V getStart()
	{
		return this.start;
	}

	@Override
	public void setCapacity(int i)
	{
		if(i < 0)
		{
			throw new IllegalArgumentException("no negative capacity please!");
		}
		else
		{

			this.capacity = i;
		}
	}

	@Override
	public void setFlow(int i)
	{
		if(i < 0)
		{
			throw new IllegalArgumentException("No negative flow please!");
		}
		else
		{
			this.flow = i;
		}
	}

	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}
}