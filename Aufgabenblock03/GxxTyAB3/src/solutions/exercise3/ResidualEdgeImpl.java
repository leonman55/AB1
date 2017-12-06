package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualEdge;

/**
 * Diese generische Klasse stellt die Kanten eines Residual Graphen da.
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class ResidualEdgeImpl<V> implements ResidualEdge<V>, ExerciseSubmission
{
	private V startNode;
	private V endNode;
	private int capacity;
	private ResidualEdge<V> reverse;

	public ResidualEdgeImpl(V startNode, V endNode, int capacity)
	{
		this.startNode = startNode;
		this.endNode = endNode;
		this.capacity = capacity;
	}

	@Override
	/**
	 * Gibt den Teamidentifier zurück
	 * 
	 * @return Teamidentifier
	 */
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	/**
	 * Fügt einer Kante einen Fluss hinzu oder zieht ihn ab
	 * 
	 * @param amount
	 *            Der Fluss der hinzugefügt oder abgezogen werden soll
	 * @exception IllegalArgumentexception
	 *                Wenn der Fluss größer als die Kapazität ist
	 */
	public void addFlow(int amount)
	{
		if (amount > this.capacity)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if (-amount > this.reverse.getCapacity())
			{
				throw new IllegalArgumentException();
			}
			else
			{
				this.capacity -= amount;
				this.reverse.setCapacity(this.reverse.getCapacity() + amount);
			}
		}
	}

	@Override
	/**
	 * Gibt die Kapazität einer Kante
	 * 
	 * @return Kapazität einer kante
	 */
	public int getCapacity()
	{
		return this.capacity;
	}

	@Override
	/**
	 * Gibt den Endknoten einer Kante
	 * 
	 * @return Endknoten einer Kante
	 */
	public V getEnd()
	{
		return this.endNode;
	}

	@Override
	/**
	 * Gibt Startknoten einer Kante
	 * 
	 * @return Startknoten
	 */
	public V getStart()
	{
		return this.startNode;
	}

	@Override
	/**
	 * Setzt die Kapazität einer Kante auf angegeebenen Wert
	 * 
	 * @param capacity
	 *            Neue Sollkapazität
	 * @exception IllegalArgumentException
	 *                Wenn die Kapazität negativ ist
	 */
	public void setCapacity(int capacity)
	{
		if (capacity < 0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.capacity = capacity;
		}
	}

	@Override
	/**
	 * Gibt die zusammengehörige Gegenkante zurück
	 * 
	 * @return Gegenkante
	 */
	public ResidualEdge<V> getReverse()
	{
		return this.reverse;
	}

	@Override
	/**
	 * Nimmt die angegebene Kante als neue Gegenkante
	 * 
	 * @param reverse
	 *            Neue Gegenkante
	 */
	public void setReverse(ResidualEdge<V> reverse)
	{
		this.reverse = reverse;
	}
}