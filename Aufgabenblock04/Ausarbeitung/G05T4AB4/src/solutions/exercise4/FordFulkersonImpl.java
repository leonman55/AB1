package solutions.exercise4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

import solutions.exercise3.ResidualGraphImpl;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 * @param <V> type of nodes contained in Graph
 */
public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission
{

	/**
	 * returns the specific team identification String
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	/**
	 * sets the maximum flow in a given path of the residual graph
	 * @param path the path on which the flow should be set to maximum flow
	 * @throws IllegalArgumentException when the given path is null
	 */
	@Override
	public void augmentPath(Deque<ResidualEdge<V>> path)
	{
		if(path == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			int i = 2147483647;
			//Computes the maximum possible flow along the path
			for(ResidualEdge<V> edge : path)
			{
				if(edge.getCapacity() < i)
				{
					i = edge.getCapacity();
				}
			}
			//sets the already computed maximum possible flow on each edge of the path
			for(ResidualEdge<V> edge : path)
			{
				edge.addFlow(i);
			}
		}
	}

	/**
	 * changes the flow along every possible path from start to target in graph to the maximum possible flow of each path
	 * @param graph the FlowGraph in which the flow should be set
	 * @param start the start node from where the route starts
	 * @param target the end node where the route ends
	 * @throws IllegalArgumentException when graph, start or target equals null
	 * @throws NoSuchElementException when graph does not contain start or target node
	 */
	@Override
	public void findMaxFlow(FlowGraph<V> graph, V start, V target)
	{
		if(graph == null || start == null || target == null)
		{
			throw new IllegalArgumentException();
		}
		else if(graph.containsNode(start) == false || graph.containsNode(target) == false)
		{
			throw new NoSuchElementException();
		}
		else if(start.equals(target))
		{
			return;
		}
		else
		{
			//convert FlowGraph into ResidualGraph with implementation of exercise 3
			ResidualGraph<V> rGraph = new ResidualGraphImpl<V>(graph);
			//computes the maximum flow in the residual graph for the given route from start to target
			while(this.findPath(start, target, rGraph) != null)
			{
				this.augmentPath(this.findPath(start, target, rGraph));
			}
			//reconverts and integrates the ResidualGraph into the given FlowGraph 
			for(V node : rGraph.getNodes())
			{
				for(ResidualEdge<V> edge : rGraph.edgesFrom(node))
				{
					//search the corresponding FlowEdge
					FlowEdge<V> flowEdge = graph.getEdge(edge.getStart(), edge.getEnd());
					if(flowEdge != null)
					{
						//if there is flow on the ResidualEdge compute new flow of FlowEdge
						if(edge.getCapacity() < edge.getReverse().getCapacity())
						{
							flowEdge.setFlow(flowEdge.getCapacity() - edge.getCapacity());
						}
						//If there is no flow on the ResidualEdge set flow of FlowEdge to zero
						else
						{
							flowEdge.setFlow(0);
						}
					}
				}
			}
		}
	}

	/**
	 * Searches a path from start to end on which the flow can still be increased
	 * @param start start the start node from where the route starts
	 * @param end the end node where the route ends
	 * @param graph the ResidualGraph in which a path from start to end should be searched
	 * @throws IllegalArgumentException when start, end or graph equals null
	 * @return returns a path from start to end if there is one, else null
	 */
	@Override
	public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph)
	{
		if(start == null || end == null || graph == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			ArrayDeque<V> nextNode = new ArrayDeque<V>();
			ArrayList<V> visitedNodes = new ArrayList<V>();
			HashMap<V, V> connectivityTree = new HashMap<V, V>();
			boolean foundPath = false;
			nextNode.addLast(start);
			visitedNodes.add(start);
			//if there are nodes which have not been visited yet search for further nodes
			while(nextNode.isEmpty() == false)
			{
				//search for non discovered edges and nodes going of from current node in ArrayDeque nextNode
				for(ResidualEdge<V> edge : graph.edgesFrom(nextNode.getFirst()))
				{
					//only if the node was not discovered before and the capacity on the edge is greater zero this way to the node will be dealt
					if(visitedNodes.contains(edge.getEnd()) == false && edge.getCapacity() > 0)
					{
						//Add the new discovered node to the ArrayDeque nextNode so it will be searched for further nodes connected to this node
						nextNode.addLast(edge.getEnd());
						//Add the new discovered node to the ArrayList visitedNodes of already discovered nodes
						visitedNodes.add(edge.getEnd());
						//Add the node to the HashMap connectivityTree which saves all discovered edges which still have capacity left.
						//The connections are saved with the start and end node switched, because in the second part of the program after the while loop
						//the eventually found path is read from this HashMap beginning with end node and HashMap only offers a method to search the corresponding values from a given key.
						connectivityTree.put(edge.getEnd(), edge.getStart());
						//if the new found node is the given end node stop searching for new nodes
						if(edge.getEnd().equals(end))
						{
							foundPath = true;
							break;
						}
					}
				}
				//needed to terminate the while loop
				if(foundPath == true)
				{
					break;
				}
				nextNode.pollFirst();
			}
			//if there is no path left to end node return null
			if(foundPath == false)
			{
				return null;
			}
			else
			{
				ArrayDeque<ResidualEdge<V>> back = new ArrayDeque<ResidualEdge<V>>();
				V node = end;
				//create a Deque which holds the path from start to end
				while(node.equals(start) == false)
				{
					back.add(graph.getEdge(connectivityTree.get(node), node));
					node = connectivityTree.get(node);
				}
				return back;
			}
		}
	}
}
