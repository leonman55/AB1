package solutions.exercise1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.ScenarioUtil;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.Producer;

public class ScenarioUtilImpl implements ExerciseSubmission, ScenarioUtil {

	public List<PlayfieldElement> getPlayfieldElementsByType(ElementType type, Scenario scenario) throws NullPointerException {
		if (scenario == null || type == null) {
		//check for null parameters
			throw new NullPointerException("Parameter is not allowed to be null.");
			//if at least 1 parameter is null, then throw the exception
		}
		Deque<PlayfieldElement> ret = new ArrayDeque<>();
		// A Deque is a Double-Ended-Queue. A Queue is a special List, where you just can add something new at the beginning
		for (int x = 0; x <= scenario.getPlayfield().getHorizontalSize()-1; x++) {
		//Iterate over the whole play field in horizontal direction (x-axes)
			for (int y = scenario.getPlayfield().getVerticalSize()-1; y >= 0; y--) {
				//Iterate over the whole play field in vertical direction (y-axes)
				PlayfieldElement element = scenario.getPlayfield().getPlayfieldElement(x, y);
				//Take the play field element by the iterated position (x,y)
				if (element.getElementType() == type) {
				//check if the element type is the same as the searched one
					ret.add(element);
					//if it is the same, then add the current play field element to the result list
				}
			}
		}
		return new HashSet<>(ret);
		//return (List<PlayFieldElement> ret;
		// give the result back
	}


	@Override
	public List<ControllableProducer> getControllableProducers(Graph<EnergyNode, PowerLine> graph) throws IllegalArgumentException {
	    // TODO Auto-generated method stub
		if(graph == null)
		{
			throw new IllegalArgumentException("graph should not be null.");
		}
		
		return null;
		
	}

	@Override
	public List<ControllableConsumer> getControllableConsumers(Graph<EnergyNode, PowerLine> graph) throws IllegalArgumentException {
	    // TODO Auto-generated method stub
		if(graph == null)
		{
			throw new IllegalArgumentException("graph should not be null.");
		}
		
	    return null;
	}

	@Override
	public List<Producer> getProducers(Graph<EnergyNode, PowerLine> graph) throws IllegalArgumentException {
	    // TODO Auto-generated method stub
		if(graph == null)
		{
			throw new IllegalArgumentException("graph should not be null.");
		}
		
	    return null;
	}

	@Override
	public List<Consumer> getConsumers(Graph<EnergyNode, PowerLine> graph) throws IllegalArgumentException {
	    // TODO Auto-generated method stub
		if(graph == null)
		{
			throw new IllegalArgumentException("graph should not be null.");
		}
		
		//String types = 
		//if(graph.getNodes().equals(type))
		if(graph.getNodes().contains(consumer.ConsumerType.city) || graph.getNodes().contains(consumer.industrialPark) || graph.getNodes().contains(consumer.commercialPark))
		{
			
		}
		return null;
	}

	@Override
	public String getTeamIdentifier() {
	    // TODO Auto-generated method stub
	    return null;
	}


	@Override
	public List<PlayfieldElement> getPlayfieldElementsByType(Scenario scenario, ElementType type) throws IllegalArgumentException {
	    // TODO Auto-generated method stub
		if(scenario == null || type == null)
		{
			throw new IllegalArgumentException("scenario or type should not be null.");
		}
		
		return null;
	}


	@Override
	public List<PowerLine> getPowerLinesByType(Graph<EnergyNode, PowerLine> graph, PowerLineType type) throws IllegalArgumentException 
	{
		// TODO Auto-generated method stub
		if(graph == null || type == null)
		{
			throw new IllegalArgumentException("graph or type should not be null.");
		}
		if(graph.getEdges().equals(type)){
			//Iterator graf = graph.iterator();
			((List) graph).subgraph(type);
		}
		
		return new List<>();
	}


}
