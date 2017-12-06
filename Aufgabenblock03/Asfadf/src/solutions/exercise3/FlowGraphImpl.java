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
	 * Gibt den Teamidentifier zur�ck
	 * 
	 * @return Teamidentifier
	 */
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	/**
	 * F�gt eine Kante mit Anfang, Ende und Kapazit�t hinzu
	 * 
	 * @param start
	 *            Startknoten
	 * @param end
	 *            Endknoten
	 * @param capacity
	 *            Kapazit�t der Kante
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
	 * F�gt einen Knoten hinzu
	 * 
	 * @param node
	 *            Knoten der hinzugef�gt werden soll
	 * @return Boolean ob Knoten hinzugef�gt wurde
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
	 * �berpr�ft ob eine Map einen gewissen Knoten beinhaltet
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
	 * Gibt alle Kanten zur�ck, die von einem bestimmten Knoten ausgehen
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
	 * Gibt eine Kante die vom Startknoten zum Endknoten l�uft zur�ck
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
	 * Gibt alle Kanten zur�ck
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
