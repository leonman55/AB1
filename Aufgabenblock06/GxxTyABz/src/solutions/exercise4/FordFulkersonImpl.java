package solutions.exercise4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

import solutions.exercise3.ResidualGraphImpl;

/**
 * This class implements a solution of AB 4.1.
 */
public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {

	/**
	 * Computes the maximum flow in a flow network <code>graph</code> from
	 * source <code>s</code> to target <code>t</code>.
	 * 
	 * @param graph
	 *            The graph representing the flow network. Flows along edges in
	 *            this graph will be set to max flows.
	 * @param start
	 *            The source node.
	 * @param target
	 *            The target node.
	 */
	@Override
	public void findMaxFlow(FlowGraph<V> graph, V start, V target) {
		// Check for invalid parameters and corner cases
		if (graph == null || start == null || target == null) {
			throw new IllegalArgumentException("Parameters are not allowed to be null");
		}

		if (!graph.containsNode(start) || !graph.containsNode(target)) {
			throw new NoSuchElementException("Start and end nodes must be in the flow network!");
		}

		// Do nothing if start equals target
		if (start.equals(target)) {
			return;
		}

		// Create residual graph
		ResidualGraph<V> residualGraph = new ResidualGraphImpl<V>(graph);

		// Start iterating as long as there does a path exists with remaining
		// capacity
		while (true) {
			// Find path from start to target
			Deque<ResidualEdge<V>> path = findPath(start, target, residualGraph);

			// If no path exists stop iterating
			if (path == null) {
				break;
			}

			// Update capacities along the path
			augmentPath(path);
		}

		// Update edges in flow graph based on capacities in residual graph
		for (V node : residualGraph.getNodes()) {
			for (ResidualEdge<V> edge : residualGraph.edgesFrom(node)) {

				// Get edge running from end to start as capacity of residual
				// non-original edge indicates flow of edge
				FlowEdge<V> flowEdge = graph.getEdge(edge.getEnd(), edge.getStart());

				// For undirected edges get reverse edge in flow graph, null
				// otherwise
				FlowEdge<V> rev_flowEdge = graph.getEdge(edge.getStart(), edge.getEnd());

				// If edge is not original it is a residual edge without a
				// corresponding directed edge in the flow graph. In this case
				// residual edge indicates flow of flow edge.
				if (!edge.isOriginal()) {
					flowEdge.addFlow(edge.getCapacity());
				} else if (rev_flowEdge != null && flowEdge != null) {

					// If there is a reverse edge in the flow graph, flow can be
					// derived from corresponding edge in residual graph.
					if (edge.getCapacity() - flowEdge.getCapacity() >= 0) {
						flowEdge.addFlow(edge.getCapacity() - flowEdge.getCapacity());
					} else {
						flowEdge.addFlow(0);
					}
				}
			}
		}
	}

	/**
	 * Calculates the shortest path on the graph by using breadth-first search
	 * (BFS)
	 * 
	 * @param start
	 *            The start node of the path.
	 * @param end
	 *            The end node of the path.
	 * @param graph
	 *            The residual graph to search in.
	 * @return and ArrayList containing all the UnderlayEdges on the shortest
	 *         path
	 */
	@Override
	public Deque<ResidualEdge<V>> findPath(V s, V t, ResidualGraph<V> graph) {
		if (s == null || t == null || graph == null) {
			throw new IllegalArgumentException("Parameters are not allowed to be null.");
		}

		HashSet<V> visitedNodes = new HashSet<V>();
		LinkedList<V> nodeQueue = new LinkedList<V>();

		// map farther Node (closer to target) to closer Node (closer to source)
		HashMap<V, V> nodeMap = new HashMap<V, V>();
		boolean foundPathToTarget = false;

		visitedNodes.add(s);
		nodeQueue.add(s);

		while (!nodeQueue.isEmpty()) {
			V currentNode = nodeQueue.poll();

			for (ResidualEdge<V> edge : graph.edgesFrom(currentNode)) {
				V otherNode = edge.getEnd();

				if (!visitedNodes.contains(otherNode) && edge.getCapacity() > 0) {
					visitedNodes.add(otherNode);
					nodeMap.put(otherNode, currentNode);
					nodeQueue.add(otherNode);
					if (otherNode.equals(t)) {
						foundPathToTarget = true;
					}
				}
			}
		}

		// Check if path to target exists
		if (foundPathToTarget) {

			// find path to source backwards beginning at target
			Deque<ResidualEdge<V>> result = new ArrayDeque<ResidualEdge<V>>();

			// start at target
			V end = t;

			// Iterate as long as there is an edge directing at end node
			while (nodeMap.containsKey(end)) {

				// set start node of edge directing at end node
				V start = nodeMap.get(end);

				// find edge from start to end
				final V tmpEnd = end;
				Optional<ResidualEdge<V>> retEdge = graph.edgesFrom(start).stream()
						.filter(e -> e.getEnd().equals(tmpEnd)).findAny();

				if (!retEdge.isPresent()) {
					throw new IllegalStateException("There should be an edge. Graph corrupted?");
				}

				result.add(retEdge.get());

				// update pointer for next iteration
				end = start;
			}
			return result;
		} else {
			// no path to target exists
			return null;
		}

	}

	/**
	 * Finds the minimum capacity along the given path.
	 * 
	 * @param path
	 *            The augmented path.
	 */
	@Override
	public void augmentPath(Deque<ResidualEdge<V>> path) {
		// Check for invalid parameter
		if (path == null) {
			throw new IllegalArgumentException("Path is not allowed to be null.");
		}

		// Find minimal remaining capacity along the path
		int capacity = Integer.MAX_VALUE;
		for (ResidualEdge<V> edge : path) {
			capacity = Math.min(capacity, edge.getCapacity());
		}

		// Add minimal remaining to flow of edges along the path
		for (ResidualEdge<V> edge : path) {
			edge.addFlow(capacity);
		}
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
