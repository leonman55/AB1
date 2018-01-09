/**
 * 
 */
package solutions.exercise4;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;

/**
 * @author Isabelle
 * @param <V>
 *
 */
public class FordFulkersonImpl<V> implements FordFulkerson<V>, ExerciseSubmission {

	/**
	 * 
	 */
	public FordFulkersonImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void augmentPath(Deque<ResidualEdge<V>> path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findMaxFlow(FlowGraph<V> graph, V start, V target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Deque<ResidualEdge<V>> findPath(V start, V end, ResidualGraph<V> graph) {
		// TODO Auto-generated method stub
		ArrayList<Map<V, FlowEdge<V>>> pfade = new ArrayList<Map<V, FlowEdge<V>>>();
		graph.edgesFrom(start);
		
		Deque knoten;
		
		
		
		//breitensuche erstellt liste von Pfaden 
		int n=30; //irgendein Wert, der nie erreicht werden sollte
		for(int j = 0 ; j < pfade.size();j++)
		{
			for(int i = 0 ; i < pfade.get(j).size() ; i++)
			{
				ArrayList fromK = pfade.get(j).get(i).getEnd();
			
			}
				if( <= n)
				{
					n=pfade.get(j).size();
				}
		}
		//oder 
		
		
		return null;
	}

	@Override
	public String getTeamIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
