package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;

/**
 * Die Klasse implementiert eine FlowEdge
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class FlowEdgeImpl<V> implements FlowEdge<V>, ExerciseSubmission
{
	private V start;
	private V end;
	private int capacity;
	private int flow;

	public FlowEdgeImpl(V start, V end, int capacity)
	{
		if (start == null || end == null || capacity < 0)
		{
			throw new IllegalArgumentException("Arguments must not be null");
		}
		else
		{
			this.start = start;
			this.end = end;
			this.capacity = capacity;
			this.flow = 0;
		}
	}

	@Override
	/**
	 * Gitb die Kapazität einer Kante zurück
	 * 
	 * @return Kapazität
	 */
	public int getCapacity()
	{
		return this.capacity;
	}

	@Override
	/**
	 * Gibt das Ende einer Kante zurück
	 * 
	 * @return Ende
	 */
	public V getEnd()
	{
		return this.end;
	}

	@Override
	/**
	 * Gibt den Fluss an einer Kante zurück
	 * 
	 * @return Fluss
	 */
	public int getFlow()
	{
		return this.flow;
	}

	@Override
	/**
	 * Gibt den Start einer Kante zurück
	 * 
	 * @return Kantenstart
	 */
	public V getStart()
	{
		return this.start;
	}

	@Override
	/**
	 * Setzt die Kapazität einer Kante auf den gegebenen Wert
	 * 
	 * @param i
	 *            Neue Kapazität
	 */
	public void setCapacity(int i)
	{
		if (i < 0)
		{
			throw new IllegalArgumentException("Die Kapazitaet muss positiv sein!");
		}
		else
		{

			this.capacity = i;
		}
	}

	@Override
	/**
	 * Setzt den Flow in einer Kante auf den gegebenen Wert
	 * 
	 * @param i
	 *            Neuer Fluss
	 */
	public void setFlow(int i)
	{
		if (i < 0)
		{
			throw new IllegalArgumentException("Der Fluss muss positiv sein!");
		}
		else
		{
			this.flow = i;
		}
	}

	@Override
	/**
	 * Gibt den Teamidentifier zurück
	 * 
	 * @return Team identifier
	 * 
	 */
	public String getTeamIdentifier()
	{
		return "G05T04";
	}
}