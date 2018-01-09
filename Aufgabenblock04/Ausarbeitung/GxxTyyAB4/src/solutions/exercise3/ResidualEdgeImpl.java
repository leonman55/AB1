package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualEdge;

public final class ResidualEdgeImpl<V> implements ResidualEdge<V>, ExerciseSubmission {

	private int capacity;
	private final V endNode;
	private final V startNode;
	private ResidualEdge<V> reverse;

	/**
	 * Constructor with arguments to set the ResidualEdgeImpl.
	 *
	 * @param startNode The start node.
	 * @param endNode The end node.
	 * @param capacity The value of the capacity.
	 */
	public ResidualEdgeImpl(V startNode, V endNode, int capacity) {
		this.endNode = endNode;
		this.startNode = startNode;
		this.capacity = capacity;
	}

	/**
	 * Set a non negative capacity.
	 * 
	 * @param capacity The new value of capacity.
	 * @throws IllegalArgumentException if capacity is negative
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity not allowed to be negative.");
		}
		this.capacity = capacity;
	}

	/**
	 * Returns the capacity of this edge.
	 *
	 * @return The value of the capacity of this edge.
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
	 * Adds or subtracts the indicated number of flow units across this edge. If the amount of flow added or removed from this edge exceeds the capacity of the edge, throws an IllegalArgumentException.
	 *
	 * @param amount The value of the flow to add or subtract.
	 */
	@Override
	public void addFlow(int amount) {
		if (amount < 0) {
			reverse.addFlow(-amount);
			return;
		}

		if (amount > capacity) {
			throw new IllegalArgumentException("Cannot push " + amount + " units of flow across edge of capacity " + capacity);
		}

		capacity -= amount;
		reverse.setCapacity(reverse.getCapacity() + amount);
	}

	/**
	 * Returns the reverse edge.
	 *
	 * @return The reverse edge({@link ResidualEdge}).
	 */
	@Override
	public ResidualEdge<V> getReverse() {
		return reverse;
	}

	/**
	 * Sets the reverse edge associated with this edge.
	 * 
	 * @param reverse The reverse edge({@link ResidualEdge})
	 */
	@Override
	public void setReverse(ResidualEdge<V> reverse) {
		this.reverse = reverse;
	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
