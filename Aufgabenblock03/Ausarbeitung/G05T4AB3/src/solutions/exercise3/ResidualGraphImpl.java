package solutions.exercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import solutions.exercise3.*;

public class ResidualGraphImpl<V> implements ResidualGraph<V>, ExerciseSubmission
{
	private Map<V, List<ResidualEdge<V>>> outerMap;
	
	public ResidualGraphImpl(FlowGraph<V> flowGraph)
	{
		this.outerMap = new HashMap<V, List<ResidualEdge<V>>>();
		List<ResidualEdge<V>> ignored = new ArrayList<ResidualEdge<V>>();
		List<FlowEdge<V>> visited = new ArrayList<FlowEdge<V>>();
		for(V node : flowGraph.getNodes())
		{
			this.outerMap.put(node, new ArrayList<ResidualEdge<V>>());
		}
		for(V node : flowGraph.getNodes())
		{
			for(FlowEdge<V> edge : flowGraph.edgesFrom(node))
			{
				ResidualEdge<V> forward;
				ResidualEdge<V> reverse;
				if(outerMap.get(edge.getStart()).contains((ResidualEdge<V>) new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), edge.getCapacity() - edge.getFlow())) == false)
				{
					// Search for reverse edge in flow graph
					FlowEdge<V> rev_edge = flowGraph.getEdge(edge.getEnd(), node);
					// Case 1: Edge in both directions in flow graph
					if (rev_edge != null)
					{
						visited.add(rev_edge);
						forward = (ResidualEdge<V>) new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), edge.getCapacity() - edge.getFlow());
						reverse = (ResidualEdge<V>) new ResidualEdgeImpl<V>(rev_edge.getStart(), rev_edge.getEnd(), rev_edge.getCapacity() - rev_edge.getFlow());
						ignored.add(reverse);
					}
					else
					{
					forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), edge.getCapacity() - edge.getFlow());
					reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), edge.getFlow());
					}
					ignored.add(forward);
					visited.add(edge);
					forward.setReverse(reverse);
					reverse.setReverse(forward);
					outerMap.get(edge.getStart()).add(forward);
					outerMap.get(edge.getEnd()).add(reverse);
				}
			}
		}
	}		
				
//				if(ignored.contains(edge) == false);
//				{
//					ResidualEdgeImpl<V> newEdge;
//					ResidualEdgeImpl<V> newEdgeReverse;
//					int capacityEdge = edge.getCapacity();
//					int flowCapacity = edge.getFlow();
//					newEdge = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), capacityEdge - flowCapacity);
//					if(flowGraph.getEdge(edge.getEnd(), edge.getStart()) == null)
//					{
//						newEdgeReverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), flowCapacity);
//					}
//					else
//					{
//						FlowEdge<V> counterEdge;
//						counterEdge = flowGraph.getEdge(edge.getEnd(), edge.getStart());
//						int capacityReverse = counterEdge.getCapacity();
//						int flowCapacityReverse = counterEdge.getFlow();
//						newEdgeReverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), capacityReverse - flowCapacityReverse);
//						ignored.add(counterEdge);
//					}
//					ignored.add(edge);
//					newEdge.setReverse(newEdgeReverse);
//					newEdgeReverse.setReverse(newEdge);
//					this.outerMap.get(edge.getStart()).add(newEdge);
//					this.outerMap.get(edge.getEnd()).add(newEdgeReverse);
//				}
//			}
//		}
	
	
//	public ResidualGraphImpl(FlowGraph<V> flowGraph) {
//
//		this.outerMap = new HashMap<V, List<ResidualEdge<V>>>();
//		
//		// Contains edges already represented in residual graph
//		List<FlowEdge<V>> visited = new ArrayList<FlowEdge<V>>();
//
//		for (V node : flowGraph.getNodes()) {
//			outerMap.put(node, new ArrayList<ResidualEdge<V>>());
//		}
//
//		for (V start : flowGraph.getNodes()) {
//			for (FlowEdge<V> edge : flowGraph.edgesFrom(start)) {
//				if (!visited.contains(edge)) {
//					ResidualEdge<V> forward;
//					ResidualEdge<V> reverse;
//					int c_1 = edge.getCapacity();
//					int f_1 = edge.getFlow();
//					// Search for reverse edge in flow graph
//					FlowEdge<V> rev_edge = flowGraph.getEdge(edge.getEnd(), start);
//					// Case 1: Edge in both directions in flow graph
//					if (rev_edge != null) {
//						// Add edge to visited list, so residual edges are not
//						// created twice
//						visited.add(rev_edge);
//
//						// Case 2: Undirected edge in flow graph
//						int c_2 = rev_edge.getCapacity();
//						int f_2 = rev_edge.getFlow();
//
//						forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), c_1 - f_1);
//						reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), c_2 - f_2);
//					} else {
//						forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), c_1 - f_1);
//						reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), f_1);
//					}
//					visited.add(edge);
//					// assign reverse attributes for both edges
//					forward.setReverse(reverse);
//					reverse.setReverse(forward);
//
//					// add edges to residual graph
//					outerMap.get(edge.getStart()).add(forward);
//					outerMap.get(edge.getEnd()).add(reverse);
//				}
//			}
//		}
//	}
	
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	public List<ResidualEdge<V>> edgesFrom(V node)
	{
		if(this.outerMap.get(node) == null)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return this.outerMap.get(node);
		}
	}

	@Override
	public ResidualEdge<V> getEdge(V start, V end)
	{
		if(start == null || end == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if(this.outerMap.containsKey(start) == false || this.outerMap.containsKey(end) == false)
			{
				throw new NoSuchElementException();
			}
			else
			{
				for(ResidualEdge<V> edge : this.outerMap.get(start))
				{
					if(edge.getEnd() == end)
					{
						return edge;
					}
				}
				return null;
			}
		}
	}

	@Override
	public List<ResidualEdge<V>> getEdges()
	{
		ArrayList<ResidualEdge<V>> back = new ArrayList<ResidualEdge<V>>();
		for(List<ResidualEdge<V>> edges : this.outerMap.values())
		{
			for(ResidualEdge<V> edge : edges)
			{
				back.add(edge);
			}
		}
		if(back.size() == 0)
		{
			return null;
		}
		else
		{
			return back;
		}
	}

	@Override
	public Set<V> getNodes()
	{
		return this.outerMap.keySet();
	}
}