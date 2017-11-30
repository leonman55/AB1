package solutions.exercise3;

import java.util.ArrayList;
import java.util.HashMap;
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
public final class ResidualGraphImpl<V> implements ExerciseSubmission, ResidualGraph<V>
{

	private Map<V, List<ResidualEdge<V>>> outerMap;

	public ResidualGraphImpl(FlowGraph<V> flowGraph)
	{
		this.outerMap = new HashMap<V, List<ResidualEdge<V>>>();
		List<FlowEdge<V>> ignore = new ArrayList<FlowEdge<V>>();
		List<V> startNodes = new ArrayList<V>(flowGraph.getNodes());

		// Alle Knoten m�ssen im Residualgraphen vorhanden sein
		for (V nodes : startNodes)
		{
			outerMap.put(nodes, new ArrayList<ResidualEdge<V>>());
		}

		for (V nodes : startNodes)
		{
			for (FlowEdge<V> edge : flowGraph.edgesFrom(nodes))
			{
				// Nur neue Kanten in den Graphen aufnehmen
				if (ignore.contains(edge) == false)
				{
					// Neue Kante
					ResidualEdge<V> newEdge;
					// Neue Gegenkante
					ResidualEdge<V> newEdge_Reverse;

					// Neue Kante zum Graphen hinzuf�gen
					newEdge = new ResidualEdgeImpl<V>(edge.getStart(), edge.getEnd(),
							edge.getCapacity() - edge.getFlow());
					FlowEdge<V> tempEdge = flowGraph.getEdge(edge.getEnd(), edge.getStart());
					FlowEdge<V> tempReverseEdge = flowGraph.getEdge(edge.getEnd(), edge.getStart());
					
					// Neue Kante zur ignore Liste hinzuf�gen
					ignore.add(edge);

					// Wenn es im FlowGraphen keine Gegenkante gibt: Dann eine residuale Gegenkante aus
					// den Daten der aktuellen Kante erstellen.
					//
					// Ansonsten mit den Daten der Gegenkante aus dem FlowGraphen eine Gegenkante
					// erstellen.
					if (tempReverseEdge == null)
					{
						newEdge_Reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(), edge.getFlow());
					}
					else
					{
						newEdge_Reverse = new ResidualEdgeImpl<V>(edge.getEnd(), edge.getStart(),
								tempEdge.getCapacity() - tempEdge.getFlow());
						ignore.add(tempEdge);
					}

					// Den neuen residualen Kanten ihre Gegenkanten zuweisen
					newEdge.setReverse(newEdge_Reverse);
					newEdge_Reverse.setReverse(newEdge);

					// Die Kanten zur Map hinzuf�gen
					outerMap.get(edge.getStart()).add(newEdge);
					outerMap.get(edge.getEnd()).add(newEdge_Reverse);
				}

			}
		}
	}

	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Override
	public List<ResidualEdge<V>> edgesFrom(V node)
	{
		if (this.outerMap.get(node) == null)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return this.outerMap.get(node);
		}
	}

	@Override
	public ResidualEdge<V> getEdge(V start, V end)
	{
		if (start == null || end == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			if (this.outerMap.containsKey(start) == false || this.outerMap.containsKey(end) == false)
			{
				throw new NoSuchElementException();
			}
			else
			{
				for (ResidualEdge<V> edge : this.outerMap.get(start))
				{
					if (edge.getEnd() == end)
					{
						return edge;
					}
				}
				return null;
			}
		}
	}

	@Override
	public List<ResidualEdge<V>> getEdges()
	{
		ArrayList<ResidualEdge<V>> back = new ArrayList<ResidualEdge<V>>();
		for (List<ResidualEdge<V>> edges : this.outerMap.values())
		{
			for (ResidualEdge<V> edge : edges)
			{
				back.add(edge);
			}
		}
		if (back.size() == 0)
		{
			return null;
		}
		else
		{
			return back;
		}
	}

	@Override
	public Set<V> getNodes()
	{
		return this.outerMap.keySet();
	}

}
