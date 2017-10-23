package solutions.exercise5;

import java.util.Map;
import java.util.Optional;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise5.AbstractEnergyNetworkAnalyzer;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.producer.Producer;

import solutions.exercise1.ScenarioUtilImpl;
import solutions.exercise3.FlowGraphImpl;
import solutions.exercise4.FordFulkersonImpl;

public class EnergyNetworkAnalyzerImpl extends AbstractEnergyNetworkAnalyzer implements ExerciseSubmission {

	public EnergyNetworkAnalyzerImpl(Graph<EnergyNode, PowerLine> graph,
			Optional<Map<Producer, Integer>> producerCapacities, Optional<Map<Consumer, Integer>> consumerCapacities) {
		super(graph, producerCapacities, consumerCapacities);
	}

	@Override
	public FlowGraph<EnergyNode> createFlowGraph(Graph<EnergyNode, PowerLine> graph,
			Optional<Map<Producer, Integer>> producerCapacities, Optional<Map<Consumer, Integer>> consumerCapacities) {

		// Create flow graph which is to be returned
		FlowGraph<EnergyNode> flowGraph = new FlowGraphImpl<EnergyNode>();

		// Add nodes of energy graph to flow graph
		for (EnergyNode node : graph.getNodes()) {
			flowGraph.addNode(node);
		}

		for (PowerLine line : graph.getEdges()) {
			// Add edge in each direction with equal capacity to represent
			// undirected edge of energy graph in flow graph
			flowGraph.addEdge(line.getStart(), line.getEnd(), line.getCapacity());
			flowGraph.addEdge(line.getEnd(), line.getStart(), line.getCapacity());
		}

		// Add super source and super sink to graph
		flowGraph.addNode(super_source);
		flowGraph.addNode(super_sink);

		// Connect the super source to each producer with new directed edge.
		// The capacity is equal to the currently provided energy level if no
		// artificial capacities are provided.
		for (Producer prod : new ScenarioUtilImpl().getProducers(graph)) {
			if (producerCapacities.isPresent()) {
				flowGraph.addEdge(super_source, prod, producerCapacities.get().get(prod));
			} else {
				flowGraph.addEdge(super_source, prod, prod.getProvidedEnergyLevel());
			}
		}

		// Connect each consumer to super sink with new directed edge to super
		// source. The capacity is equal to the currently required energy level
		// if no artificial capacities are provided.
		for (Consumer cons : new ScenarioUtilImpl().getConsumers(graph)) {
			if (consumerCapacities.isPresent()) {
				flowGraph.addEdge(cons, super_sink, consumerCapacities.get().get(cons));
			} else {
				flowGraph.addEdge(cons, super_sink, cons.getRequiredEnergyLevel());
			}
		}

		return flowGraph;
	}

	@Override
	public void calculateMaxFlow() {
		FordFulkersonImpl<EnergyNode> fordFulkerson = new FordFulkersonImpl<>();
		fordFulkerson.findMaxFlow(flowGraph, super_source, super_sink);

		// retrieve producer levels
		for (FlowEdge<EnergyNode> edge : flowGraph.edgesFrom(super_source)) {

			// Check if node is actually a producer
			if (!producerLevels.containsKey(edge.getEnd())) {
				throw new IllegalStateException("Producer levels were not initialized correctly.");
			}
			// Update level for producer, casting is safe here due to prior
			// check of existence of node in map
			producerLevels.put((Producer) edge.getEnd(), edge.getFlow());
		}

		// retrieve consumer levels
		for (FlowEdge<EnergyNode> edge : flowGraph.getEdges()) {

			// Select only those edges going to super sink.
			if (edge.getEnd().equals(super_sink)) {

				// Check if node is actually a consumer
				if (!consumerLevels.containsKey(edge.getStart())) {
					throw new IllegalStateException("Producer levels were not initialized correctly.");
				}

				// Update level for consumer, casting is safe here due to prior
				// check of existence of node in map
				consumerLevels.put((Consumer) edge.getStart(), edge.getFlow());
			}
		}

		// retrieve power line levels
		for (PowerLine tmpLine : powerlineLevels.keySet()) {

			int newLevel = Math.max(flowGraph.getEdge(tmpLine.getStart(), tmpLine.getEnd()).getFlow(),
					flowGraph.getEdge(tmpLine.getEnd(), tmpLine.getStart()).getFlow());

			// Update level for power line
			powerlineLevels.put(tmpLine, newLevel);

		}

	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
