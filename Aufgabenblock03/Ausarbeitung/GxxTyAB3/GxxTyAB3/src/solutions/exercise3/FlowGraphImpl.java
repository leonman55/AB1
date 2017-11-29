/**
 * 
 */
package solutions.exercise3;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;

/**
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 *
 */
public class FlowGraphImpl<V> implements ExerciseSubmission, FlowGraph<V> {

//	private Map<V, FlowEdge<V>> ends; //Gewicht einem Zielknoten zuordnen
	private Map<V, Map<V, FlowEdge<V>>> edges; //Kante - Zielknoten einem Startknoten zuordnen
	//Map<V, Map<V, FlowEdge<V>>>;
	
	/**
	 * 
	 */
	public FlowGraphImpl() {
		// TODO Auto-generated constructor stub
		//ends = new HashMap<V, FlowEdge<V>>();
		edges = new HashMap<V, Map<V, FlowEdge<V>>>();
	}

	@Override
	public FlowEdge<V> addEdge(V start, V dest, int capacity) {
		// TODO Auto-generated method stub
		//eingehende Werte auf erlaubtheit prüfen 
		//FlowEdge<V> newEdge = FlowEdge(start, dest, capacity);
		edges.get(start).put(dest, );
		return ;
	}

	@Override
	public boolean addNode(V node) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean containsNode(V node) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public Collection<FlowEdge<V>> edgesFrom(V node) {
		// TODO Auto-generated method stub
//		Collection<FlowEdge<V>> edgesFromV = new Collection<FlowEdges<V>>();
		List<FlowEdge<V>> edgesFromV = new List<FlowEdges<V>>();
		Map<V, FlowEdge<V>> destinations = new HashSet<V, FlowEdge<V>>();
		
		for(V edge : edges.keySet())
		{
			destinations = edges.get(edge);
//			destinations.put(aDestination); //welche methode ergänzt?
		}
		for(V weight : destinations.keySet())
		{
			edgesFromV = destinations.get(weight);
		}
		return edgesFromV;
		//muss ich casten?
	}

	
	//Was beinhaltet FlowEdge<V>? 
	//nur die Kapazität oder (scheinbar) auch den Zielknoten der Leitung
	
	
	@Override
	public FlowEdge<V> getEdge(V start, V end) {
		// TODO Auto-generated method stub
		
		//return edge(start, end);
		return null;
	}

	@Override
	public List<FlowEdge<V>> getEdges() {
		// TODO Auto-generated method stub
		List<FlowEdge<V>> edges = ;
		edgesList.put();
		for()
		{
			edgesList.
		}
		return ;
	}

	@Override
	public Set<V> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTeamIdentifier() {
		// TODO Auto-generated method stub
		return "G05T04";
	}

}
