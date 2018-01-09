package solutions.exercise5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise4.FordFulkerson;
import org.sopra.api.exercises.exercise5.AbstractEnergyNetworkAnalyzer;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.producer.Producer;

import solutions.exercise3.FlowGraphImpl;
import solutions.exercise4.FordFulkersonImpl;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class EnergyNetworkAnalyzerImpl extends AbstractEnergyNetworkAnalyzer implements ExerciseSubmission
{
	/**
	 * Constructor for new EnergyNetworkAnalyzerImpl object
	 * @param graph the graph representing the playfield with EnergyNode and PowerLine objects
	 * @param producerCapacities may contain specific values for the capacities of the Producer objects
	 * @param consumerCapacities may contain specific values for the capacities of the Consumer objects
	 */
	public EnergyNetworkAnalyzerImpl(Graph<EnergyNode, PowerLine> graph, Optional<Map<Producer, Integer>> producerCapacities, Optional<Map<Consumer, Integer>> consumerCapacities)
	{
		super(graph, producerCapacities, consumerCapacities);
	}

	/**
	 * returns the specific identification phrase for the team
	 * @return team identification String
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	/**
	 * calculates, searches and sets the correct values for the producerLevels, consumerLevels and powerlineLevels objects
	 */
	@Override
	public void calculateMaxFlow()
	{
		//new FordFulkerson object is created and findMaxFlow method is executed to calculate the maximum possible usage of the network
		FordFulkerson<EnergyNode> ford = new FordFulkersonImpl<EnergyNode>();
		ford.findMaxFlow(flowGraph, super_source, super_sink);
		List<FlowEdge<EnergyNode>> edgesToSuperSink = new ArrayList<FlowEdge<EnergyNode>>();
		List<FlowEdge<EnergyNode>> edgesFromSuperSource = new ArrayList<FlowEdge<EnergyNode>>();
		//Get every edge from super_source
		for(FlowEdge<EnergyNode> edge : flowGraph.edgesFrom(super_source))
		{
			edgesFromSuperSource.add(edge);
			//Add data to producerLevels if the end of the edge is a Producer
			if(edge.getEnd() instanceof Producer)
			{
				//the producer level is the flow of the edge
				this.producerLevels.put((Producer) edge.getEnd(), edge.getFlow());
			}
		}
		//Go through every edge of the flowGraph to find edges to the super_sink
		for(FlowEdge<EnergyNode> edge : flowGraph.getEdges())
		{
			//if an edge to super_sink is found, it is added to a List
			if(edge.getEnd().equals(super_sink))
			{
				edgesToSuperSink.add(edge);
			}
		}
		//Go through every edge which leads to the super_sink and add the data to consumerLevels
		for(FlowEdge<EnergyNode> edge : edgesToSuperSink)
		{
			//the producer level is the flow of the edge
			this.consumerLevels.put((Consumer) edge.getStart(), edge.getFlow());
		}
		//Extract all PowerLine objects from powerlineLevels to calculate their value
		for(PowerLine line : this.powerlineLevels.keySet())
		{
			//Searches the flow of the corresponding edge and reverse edge from the flowGraph object
			int flow1 = flowGraph.getEdge(line.getStart(), line.getEnd()).getFlow();
			int flow2 = flowGraph.getEdge(line.getEnd(), line.getStart()).getFlow();
			//sets the value for the PowerLine to the higher available flow
			if(flow1 < flow2)
			{
				this.powerlineLevels.put(line, flow2);
			}
			else if(flow1 > flow2)
			{
				this.powerlineLevels.put(line, flow1);
			}
			else if(flow1 == flow2 && flow1 != 0 && flow2 != 0)
			{
				this.powerlineLevels.put(line, flow1);
			}
			else if(flow1 == 0 && flow2 == 0)
			{
				this.powerlineLevels.put(line, 0);
			}
		}
	}
	
	/**
	 * creates a new FlowGraph object from the given graph producerLevels and consumerLevels objects
	 * @param graph the graph representing the playfield
	 * @param producerLevels if available this object is used for the capacity of the edges from super_source to Producer objects
	 * @param consumerLevels if abailable this object is used for the capacity of the edges from Consumer objects to super_sink
	 * @return return the newly created FlowGraph object
	 */
	@Override
	public FlowGraph<EnergyNode> createFlowGraph(Graph<EnergyNode, PowerLine> graph, Optional<Map<Producer, Integer>> producerCapacities, Optional<Map<Consumer, Integer>> consumerCapacities)
	{
		//new FlowGraphImpl object, which will be returned at the end of this method, is created 
		FlowGraphImpl<EnergyNode> back = new FlowGraphImpl<EnergyNode>();
		List<Producer> prod = new ArrayList<Producer>();
		List<Consumer> con = new ArrayList<Consumer>();
		//super_source node is added
		back.addNode(super_source);
		//super_sink node is added
		back.addNode(super_sink);
		//All nodes from given graph object are put into FlowGraphImpl object back
		for(EnergyNode node : graph.getNodes())
		{
			back.addNode(node);
		}
		//all nodes, which are Producer or Consumer objects are searched and stored in separate List objects for further use
		for(EnergyNode node: back.getNodes())
		{
			if(node instanceof Producer)
			{
				prod.add((Producer) node);
			}
			else if(node instanceof Consumer)
			{
				con.add((Consumer) node);
			}
		}
		//if the producerCapacities object is available, the capacity values for the edges from super_source to Producer objects are taken out of producerCapacities object
		if(producerCapacities.isPresent())
		{
			//new edge from super_source to every Producer object is added
			for(Producer producer : prod)
			{
				back.addEdge(super_source, producer, producerCapacities.get().get(producer));
			}
		}
		//if the producerCapacities object is not available, the capacity values for the edges from super_source to Producer objects equal the return value of the getProvidedPower method
		else
		{
			//new edge from super_source to every Producer object is added
			for(Producer producer : prod)
			{
				back.addEdge(super_source, producer, producer.getProvidedPower());
			}
		}
		//if the ConsumerCapacities object is available, the capacity values for the edges from Consumer objects to the super_sink are taken out of consumerCapacities object
		if(consumerCapacities.isPresent())
		{
			//new edge from every Consumer object to super_sink is added
			for(Consumer consumer : con)
			{
				back.addEdge(consumer, super_sink, consumerCapacities.get().get(consumer));
			}
		}
		//if the consumerCapacities object is not available, the capacity values for the edges from Consumer objects to the super_sink equal the return value of the getRequiredPower method of the consumer
		else
		{
			//new edge from every Consumer object to super_sink is added
			for(Consumer consumer : con)//new edge from every Consumer object to super_sink is added
			{
				back.addEdge(consumer, super_sink, consumer.getRequiredPower());
			}
		}
		//Add edges to return object for every PowerLine stored in graph
		for(PowerLine line : graph.getEdges())
		{
			back.addEdge(line.getStart(), line.getEnd(), line.getCapacity());
			back.addEdge(line.getEnd(), line.getStart(), line.getCapacity());
		}
		return back;
	}
}