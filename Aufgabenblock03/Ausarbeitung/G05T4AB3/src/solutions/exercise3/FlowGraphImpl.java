package solutions.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;

public class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission
{
	private Map<V, Map<V, FlowEdge<V>>> outerMap;
	
	public FlowGraphImpl()
	{
		this.outerMap = new HashMap<V, Map<V, FlowEdge<V>>>();
	}
	
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	public FlowEdge<V> addEdge(V start, V dest, int capacity)
	{
		if(start == null || dest == null || capacity < 0)
		{
			throw new NullPointerException("Arguments must not be null and capacity not lower 0!");
		}
		else
		{
			if(this.containsNode(start) == false || this.containsNode(dest) == false)
			{
				throw new NoSuchElementException("Element start or end not found!");
			}
			else
			{
				if(this.outerMap.get(start).containsKey(dest) == false)
				{
					return this.outerMap.get(start).put(dest, new FlowEdgeImpl<V>(start, dest, capacity));
				}
				else
				{
					return this.outerMap.get(start).get(dest);
				}
			}
		}
	}

	@Override
	public boolean addNode(V node)
	{
		if(node == null)
		{
			return false;
		}
		else
		{
			if(this.containsNode(node) == false)
			{
				this.outerMap.put(node, new HashMap<V, FlowEdge<V>>());
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	public boolean containsNode(V node)
	{
		if(this.outerMap.containsKey(node) == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Collection<FlowEdge<V>> edgesFrom(V node)
	{
		if(this.outerMap.containsKey(node) == false)
		{
			throw new NoSuchElementException("element not found!");
		}
		else
		{
			return this.outerMap.get(node).values();
		}
	}

	@Override
	public FlowEdge<V> getEdge(V start, V end)
	{
		if(start == null || end == null)
		{
			return null;
		}
		else
		{
			if(this.containsNode(start) == false)
			{
				return null;
			}
			else
			{
				if(this.outerMap.get(start).containsKey(end) == false)
				{
					return null;
				}
				else
				{
					return this.outerMap.get(start).get(end);
				}
			}
		}
	}

	@Override
	public List<FlowEdge<V>> getEdges()
	{
		ArrayList<FlowEdge<V>> back = new ArrayList<FlowEdge<V>>();
		for(Map<V, FlowEdge<V>> map: this.outerMap.values())
		{
			for(FlowEdge<V> edge : map.values())
			{
				back.add(edge);
			}
		}
		return back;
	}

	@Override
	public Set<V> getNodes()
	{
		return this.outerMap.keySet();
	}
}
