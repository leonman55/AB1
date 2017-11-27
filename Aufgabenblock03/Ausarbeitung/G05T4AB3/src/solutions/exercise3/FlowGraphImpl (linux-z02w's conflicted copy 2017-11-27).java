package solutions.exercise3;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;

public class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission
{
	private Map<V, Map<V, FlowEdge<V>>> outerMap = new HashMap();
	
	public FlowGraphImpl()
	{
		this.outerMap = new HashMap();
	}
	
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	public FlowEdge<V> addEdge(V arg0, V arg1, int arg2)
	{
		//TODO
		if(arg0 == null || arg1 == null || arg2 < 0)
		{
			throw new IllegalArgumentException("Arguments must not be null and capacity not lower 0!");
		}
		else
		{
			FlowEdgeImpl newEdge = new FlowEdgeImpl(arg0, arg1, arg2);
			return newEdge;
		}
	}

	@Override
	public boolean addNode(V arg0)
	{
		if(arg0 == null)
		{
			throw new IllegalArgumentException("Arguments must not be null!");
		}
		else
		{
			this.outerMap.put(arg0, new HashMap<V, FlowEdge<V>>());
			return true;
		}
	}

	@Override
	public boolean containsNode(V arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<FlowEdge<V>> edgesFrom(V arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlowEdge<V> getEdge(V arg0, V arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlowEdge<V>> getEdges()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<V> getNodes()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
