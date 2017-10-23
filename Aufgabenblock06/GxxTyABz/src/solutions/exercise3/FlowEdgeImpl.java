package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;

/**
 * This class implements a solution of AB 3.1.
 */
public class FlowEdgeImpl<V> implements FlowEdge<V>, ExerciseSubmission {

	/**
	 * The capacity of the edge.
	 */
	private int capacity;

	/**
	 * The end node of the edge.
	 */
	private V endNode;

	/**
	 * The start node of the edge.
	 */
	private V startNode;

	/**
	 * The flow of the edge.
	 */
	private int flow;

	/**
	 * Constructor with fields as arguments.
	 * 
	 * @param startNode
	 *            The start node.
	 * @param endNode
	 *            The end Node.
	 * @param capacity
	 *            The capacity.
	 * @param flow
	 *            The flow.
	 */
	public FlowEdgeImpl(V startNode, V endNode, int capacity) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.capacity = capacity;
		this.flow = 0;
	}

	/**
	 * Returns the capacity of the edge.
	 * 
	 * @return Integer of Capacity.
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Returns the end node of the edge.
	 * 
	 * @return The end node.
	 */
	@Override
	public V getEnd() {
		return endNode;
	}

	/**
	 * Return the start node of the edge.
	 * 
	 * @return The start node.
	 */
	@Override
	public V getStart() {
		return startNode;
	}

	/**
	 * Returns the flow across this edge.
	 * 
	 * @return The flow.
	 */
	@Override
	public int getFlow() {
		return flow;
	}

	/**
	 * Sets the flow across this edge. Throws an IllegalArgumentException if the
	 * value is negative .
	 * 
	 * @param flow
	 *            The new flow along this edge.
	 */
	@Override
	public void addFlow(int flow) {
		if (flow < 0)
			throw new IllegalArgumentException("Flow must be positive!");

		this.flow = flow;
	}

	/**
	 * Sets the capacity of the edge. Throws an IllegalArgumentException if the
	 * value is negative .
	 * 
	 * @param capacity
	 *            The new capacity of this edge.
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity must be positive");
		}
		this.capacity = capacity;
	}

	/**
	 * Returns the informations of the team.
	 * 
	 * @returns	text with team team number.
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
