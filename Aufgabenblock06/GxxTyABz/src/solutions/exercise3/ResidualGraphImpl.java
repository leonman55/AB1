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

/**
 * This class implements a solution of AB 3.5.
 */
public final class ResidualGraphImpl<V> implements ExerciseSubmission, ResidualGraph<V> {

	private Map<V, List<ResidualEdge<V>>> graph = new HashMap<V, List<ResidualEdge<V>>>();

	/**
	 * Constructs a new residual graph from a given flow network.
	 *
	 * @param flowGraph
	 *            The flow network from which the graph should be constructed.
	 */
	public ResidualGraphImpl(FlowGraph<V> flowGraph) {

		// Contains edges already represented in residual graph
		List<FlowEdge<V>> visited = new ArrayList<FlowEdge<V>>();

		for (V node : flowGraph.getNodes()) {
			graph.put(node, new ArrayList<ResidualEdge<V>>());
		}

		for (V start : flowGraph.getNodes()) {
			for (FlowEdge<V> edge : flowGraph.edgesFrom(start)) {
				if (!visited.contains(edge)) {
					ResidualEdge<V> forward;
					ResidualEdge<V> reverse;
					int c_1 = edge.getCapacity();
					int f_1 = edge.getFlow();

					// Search for reverse edge in flow graph
					FlowEdge<V> rev_edge = flowGraph.getEdge(edge.getEnd(), start);

					// Case 1: Edge in both directions in flow graph
					if (rev_edge != null) {
						// Add edge to visited list, so residual edges are not
						// created twice
						visited.add(rev_edge);

						// Case 2: Undirected edge in flow graph
						int c_2 = rev_edge.getCapacity();
						int f_2 = rev_edge.getFlow();

						forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), c_1 - f_1, true);
						reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), c_2 - f_2, true);
					} else {
						forward = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(), c_1 - f_1, true);
						reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), f_1, false);
					}

					// assign reverse attributes for both edges
					forward.setReverse(reverse);
					reverse.setReverse(forward);

					// add edges to residual graph
					graph.get(edge.getStart()).add(forward);
					graph.get(edge.getEnd()).add(reverse);
				}
			}

		}

	}

	/**
	 * Given all nodes existing in the graph.
	 * 
	 * @return Returns an Set off all nodes.
	 */
	@Override
	public Set<V> getNodes() {
		return graph.keySet();
	}

	/**
	 * Given all edges existing in the graph.
	 * 
	 * @return Returns a List off all edges.
	 */
	@Override
	public List<ResidualEdge<V>> getEdges() {

		Iterator<List<ResidualEdge<V>>> itEdges = graph.values().iterator();
		List<ResidualEdge<V>> edges = new ArrayList<ResidualEdge<V>>();

		while (itEdges.hasNext())
			edges.addAll(itEdges.next());

		return edges;
	}

	/**
	 * Returns the edges leaving a particular node.
	 *
	 * @param node
	 *            The node to look up.
	 * @return The set of edges leaving that node.
	 * @throws NoSuchElementException
	 *             If the node does not exist.
	 */
	@Override
	public List<ResidualEdge<V>> edgesFrom(V node) {
		List<ResidualEdge<V>> edges = graph.get(node);
		if (edges == null)
			throw new NoSuchElementException("Node does not exist.");

		return edges;
	}

	/**
	 * Returns the edge between node <code>start</code> and node
	 * <code>end</code>. Return <code>null</code> if no edge exists.
	 * 
	 * @param start
	 *            The start node
	 * @param end
	 *            The end node
	 * @return the edge between start and end, null if no edge exists or either
	 *         start or end are not contained in the graph.
	 * @throws IllegalArgumentException
	 *             if either parameter start or end are null.
	 */
	@Override
	public ResidualEdge<V> getEdge(V start, V end) {
		if (start == null || end == null || !graph.containsKey(start) || !graph.containsKey(end)) {
			throw new IllegalArgumentException("Invalid parameters.");
		}
		for (ResidualEdge<V> edge : graph.get(start)) {
			if (edge.getEnd().equals(end)) {
				return edge;
			}
		}
		return null;
	}

	/**
	 * Returns the informations of the team.
	 * 
	 * @returns text with team team number.
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
