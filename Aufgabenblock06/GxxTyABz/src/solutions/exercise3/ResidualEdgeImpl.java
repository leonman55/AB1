package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualEdge;

/**
 * This class implements a solution of AB 3.4.
 */
public final class ResidualEdgeImpl<V> implements ResidualEdge<V>, ExerciseSubmission {

	/**
	 * The capacity.
	 */
	private int capacity;
	/**
	 * The end node.
	 */
	private final V endNode;
	/**
	 * The start node.
	 */
	private final V startNode;

	/**
	 * The reverse edge.
	 */
	private ResidualEdge<V> reverse;
	/**
	 * A real or residual edge.
	 */
	private final boolean isOriginal;

	/**
	 * Constructor with arguments to set the ResidualEdgeImpl.
	 *
	 * @param start
	 *            The start node.
	 * @param end
	 *            The end node.
	 * @param capacity
	 *            The capacity.
	 * @param isOriginal
	 *            Flag to indicate if edge is either reverse or original.
	 */
	public ResidualEdgeImpl(V startNode, V endNode, int capacity, boolean isOriginal) {
		this.endNode = endNode;
		this.startNode = startNode;
		this.capacity = capacity;
		this.isOriginal = isOriginal;
	}

	/**
	 * Set the capacity.
	 * 
	 * @param capacity
	 *            The new capacity.
	 */
	@Override
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Returns the capacity of this edge.
	 *
	 * @return The capacity of this edge.
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Returns the end node of this edge.
	 *
	 * @return The end node of this edge.
	 */
	@Override
	public V getEnd() {
		return endNode;
	}

	/**
	 * Returns the start node of this edge.
	 *
	 * @return The start node of this edge.
	 */
	@Override
	public V getStart() {
		return startNode;
	}

	/**
	 * Adds or subtracts the indicated number of flow units across this edge. If
	 * the amount of flow added or removed from this edge exceeds the capacity
	 * of the edge, throws an IllegalArgumentException.
	 *
	 * @param amount
	 *            The flow to add or subtract.
	 */
	@Override
	public void addFlow(int amount) {
		if (amount < 0) {
			reverse.addFlow(-amount);
			return;
		}

		if (amount > capacity)
			throw new IllegalArgumentException(
					"Cannot push " + amount + " units of flow across edge of capacity " + capacity);

		capacity -= amount;
		reverse.setCapacity(reverse.getCapacity() + amount);
	}

	/**
	 * Returns the reverse edge.
	 *
	 * @return The reverse edge.
	 */
	@Override
	public ResidualEdge<V> getReverse() {
		return reverse;
	}

	/**
	 * Returns <code>true<\code> if the edge is an original edge in the graph
	 * else it returns <code>false<\code>
	 * 
	 * @return Wether it is a original edge.
	 */
	@Override
	public boolean isOriginal() {
		return isOriginal;
	}

	/**
	 * Sets the reverse edge associated with this edge.
	 */
	@Override
	public void setReverse(ResidualEdge<V> reverse) {
		this.reverse = reverse;
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
