package solutions.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;

/**
 * Die Klasse implementiert einen Graphen mit FlowEdges
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class FlowGraphImpl<V> implements FlowGraph<V>, ExerciseSubmission
{
	private Map<V, Map<V, FlowEdge<V>>> outerMap;

	public FlowGraphImpl()
	{
		this.outerMap = new HashMap<V, Map<V, FlowEdge<V>>>();
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
	 * Fügt eine Kante mit Anfang, Ende und Kapazität hinzu
	 * 
	 * @param start
	 *            Startknoten
	 * @param end
	 *            Endknoten
	 * @param capacity
	 *            Kapazität der Kante
	 * @return Die Kante zwischen den Knoten
	 */
	public FlowEdge<V> addEdge(V start, V dest, int capacity)
	{
		if (this.containsNode(start) == false || this.containsNode(dest) == false)
		{
			throw new NoSuchElementException("Element start or end not found!");
		}
		else
		{
			if (this.outerMap.get(start).containsKey(dest) == false)
			{
				this.outerMap.get(start).put(dest, new FlowEdgeImpl<V>(start, dest, capacity));
				return this.outerMap.get(start).get(dest);
			}
			else
			{
				return this.outerMap.get(start).get(dest);
			}
		}
	}

	@Override
	/**
	 * Fügt einen Knoten hinzu
	 * 
	 * @param node
	 *            Knoten der hinzugefügt werden soll
	 * @return Boolean ob Knoten hinzugefügt wurde
	 */
	public boolean addNode(V node)
	{
		if (node == null)
		{
			return false;
		}
		else
		{
			if (this.containsNode(node) == false)
			{
				this.outerMap.put(node, new HashMap<V, FlowEdge<V>>());
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	@Override
	/**
	 * Überprüft ob eine Map einen gewissen Knoten beinhaltet
	 * 
	 * @param node
	 *            Knoten nach dem gesucht wird
	 * @return Boolean ob Knoten beinhaltet ist
	 */
	public boolean containsNode(V node)
	{
		if (this.outerMap.containsKey(node) == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	/**
	 * Gibt alle Kanten zurück, die von einem bestimmten Knoten ausgehen
	 * 
	 * @return Kanten die den Knoten verlassen
	 */
	public Collection<FlowEdge<V>> edgesFrom(V node)
	{
		if (this.outerMap.containsKey(node) == false)
		{
			throw new NoSuchElementException("element not found!");
		}
		else
		{
			return this.outerMap.get(node).values();
		}
	}

	@Override
	/**
	 * Gibt eine Kante die vom Startknoten zum Endknoten läuft zurück
	 * 
	 * @param start
	 *            Startknoten
	 * @param end
	 *            Endknoten
	 * @return Kante
	 */
	public FlowEdge<V> getEdge(V start, V end)
	{
		if (start == null || end == null)
		{
			return null;
		}
		else
		{
			if (this.containsNode(start) == false)
			{
				return null;
			}
			else
			{
				if (this.outerMap.get(start).containsKey(end) == false)
				{
					return null;
				}
				else
				{
					return this.outerMap.get(start).get(end);
				}
			}
		}
	}

	@Override
	/**
	 * Gibt alle Kanten zurück
	 * 
	 * @return Alle Kanten des Graphen
	 */
	public List<FlowEdge<V>> getEdges()
	{
		ArrayList<FlowEdge<V>> back = new ArrayList<FlowEdge<V>>();
		for (Map<V, FlowEdge<V>> map : this.outerMap.values())
		{
			for (FlowEdge<V> edge : map.values())
			{
				back.add(edge);
			}
		}
		return back;
	}

	@Override
	/**
	 * Gibt alle Knoten aus
	 * 
	 * @return Alle Knoten des Graphen
	 */

	public Set<V> getNodes()
	{
		return this.outerMap.keySet();
	}
}
