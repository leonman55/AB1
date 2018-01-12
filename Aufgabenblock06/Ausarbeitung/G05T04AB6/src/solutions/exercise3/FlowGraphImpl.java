package solutions.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;

public final class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission {

	private Map<V, Map<V, FlowEdge<V>>> graph;

	/**
	 * Constructor with no arguments.
	 */
	public FlowGraphImpl() {
		graph = new HashMap<V, Map<V, FlowEdge<V>>>();
	}

	/**
	 * Adds a new node. If the node is added returns <code>true<\code>, else returns <code>false</code>.
	 * 
	 * @param node The node to add.
	 * @return If the node was added or not.
	 */
	@Override
	public boolean addNode(V node) {
		if (node == null || graph.containsKey(node)) { 
			return false;
		}

		graph.put(node, new HashMap<V, FlowEdge<V>>());
		return true;
	}

	/**
	 * Adds a new FlowEdge to a start and a destination node with initial flow of 0. If the start or destination node does not exist, throws an <code>NoSuchElementException</code>. If the edge already exist, returns the existing edge. 
	 * 
	 * @param start The start node.
	 * @param dest The destination node.
	 * @param capacity The value of the capacity of the edge.
	 */
	@Override
	public FlowEdge<V> addEdge(V start, V dest, int capacity) {
		if (!graph.containsKey(start) || !graph.containsKey(dest))
			throw new NoSuchElementException("Both nodes must exist in the graph."); 

		if (!graph.get(start).containsKey(dest))
			graph.get(start).put(dest, new FlowEdgeImpl<V>(start, dest, capacity));

		return graph.get(start).get(dest);
	}

	/**
	 * Gets all edges existing in the graph.
	 * 
	 * @return Returns a {@link List} of all {@link FlowEdge}s.
	 */
	@Override
	public List<FlowEdge<V>> getEdges() {
		Iterator<Map<V, FlowEdge<V>>> itMaps = graph.values().iterator();
		List<FlowEdge<V>> edges = new ArrayList<FlowEdge<V>>();

		while (itMaps.hasNext())
			edges.addAll(itMaps.next().values());

		return edges;
	}

	/**
	 * Gets all nodes existing in the graph.
	 * 
	 * @return Returns an {@link Set} of all nodes.
	 */
	@Override
	public Set<V> getNodes() {
		return graph.keySet();
	}

	/**
	 * Returns <code>true</code> if the given node is contained in the graph. If not, returns <code>false</code>
	 *
	 * @param node The node to test for inclusion.
	 * @return Whether that node is contained in the graph.
	 */
	@Override
	public boolean containsNode(V node) {
		return graph.containsKey(node);
	}

	/**
	 * Returns all edges from the leaving node. If the node is not given in the graph, throws a <code>NoSuchElementException</code>
	 * 
	 * @param node The node whose edges should be queried.
	 * @return A {@link Collection} of all {@link FlowEdge}s leaving the node.
	 */
	@Override
	public Collection<FlowEdge<V>> edgesFrom(V node) { 
		Map<V, FlowEdge<V>> arcs = graph.get(node);
		if (arcs == null) {
			throw new NoSuchElementException("Source node does not exist");
		}
		return arcs.values();
	}

	/**
	 * Returns a FlowEdge going from <code>start</code> to <code>end</code>. Returns <code>null</code> if FlowEdge is not present.
	 * 
	 * @param end end node.
	 * @param start start node.
	 * @return A {@link FlowEdge} which connects start & end node.
	 */
	@Override
	public FlowEdge<V> getEdge(V start, V end) {
		if (start == null || end == null) {
			return null;
		}
		return (graph.get(start)!=null) ? graph.get(start).get(end) : null;
	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
