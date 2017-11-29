/**
 * 
 */
package solutions.exercise3;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;

/**
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 * @param <V>
 *
 */
public class FlowEdgeImpl<V> implements FlowEdge<V>, ExerciseSubmission {

	private V begin;
	private V end; 
	private int capacity;
	private int flow;
	
	/**
	 * initialisiert die start-, Endknoten einer Leitung, seine Kapazität
	 * und den Fluss einer Leitung(Kante) mit 0
	 * @param end Ende der Leitung
	 * @param begin Beginn der Leitung
	 * @param capacity Kapazität der Leitung
	 * @param flow Fluss in der Leitung
	 */
	public FlowEdgeImpl(V begin, V end, int capacity) {
		// TODO Auto-generated constructor stub
		this.begin = begin; 
		this.end = end; 
		this.capacity = capacity;
		this.flow = 0;
	}

	/**
	 * gibt die Gruppen- und Teamnummer zurück
	 * @param "G05T04" String der Gruppen- und Teamnummer enthält
	 */
	@Override
	public String getTeamIdentifier() {
		// TODO Auto-generated method stub
		return "G05T04";
	}

	/**
	 * gibt die Kapazität einer Leitung zurück
	 * @param capacity die Kapazität einer Leitung 
	 */
	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return this.capacity;
	}

	/**
	 * gibt den Endknoten einer Leitung zurück
	 * @param end der Endknoten einer Leitung 
	 */
	@Override
	public V getEnd() {
		// TODO Auto-generated method stub
		return this.end;
	}

	/**
	 * gibt den Fluss einer Leitung zurück
	 * @param flow der Fluss einer Leitung 
	 */
	@Override
	public int getFlow() {
		// TODO Auto-generated method stub
		return this.flow;
	}

	/**
	 * gibt den Anfangsknoten einer Leitung zurück
	 * @param begin der Anfangsknoten einer Leitung 
	 */
	@Override
	public V getStart() {
		// TODO Auto-generated method stub
		return this.begin;
	}

	/**
	 * setzt die Kapazität einer Leitung 
	 * @param capacity die Kapazität einer Leitung 
	 */
	@Override
	public void setCapacity(int capacity) {
		// TODO Auto-generated method stub
		// Abfrage, dass der übergebene Wert nicht null ist?
		this.capacity = capacity;
	}

	/**
	 * setzt den Fluss einer Leitung 
	 * @param flow der Fluss einer Leitung 
	 */
	@Override
	public void setFlow(int flow) {
		// TODO Auto-generated method stub
		// Abfrage, dass der übergebene Wert nicht null ist?
		this.flow = flow;
	}

}
