package solutions.exercise3;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.AbstractFlowGraphTest;
import org.sopra.api.exercises.exercise3.FlowEdge;

public class FlowGraphTest extends AbstractFlowGraphTest implements ExerciseSubmission {

	@Test
	public void test_addNode() {
		// node does not exist
		assertTrue(sut.addNode("X"));

		// node should now exist
		assertFalse(sut.addNode("X"));

		// invalid parameter should yield false
		assertFalse(sut.addNode(null));
	}

	@Test
	public void test_addEdge() {
		// Add flow edge with both nodes existing in graph
		sut.addNode("u");
		sut.addNode("v");
		FlowEdge<String> edge = sut.addEdge("u", "v", 10);

		assertEquals(edge.getStart(), "u");
		assertEquals(edge.getEnd(), "v");
		assertEquals(edge.getCapacity(), 10);
		assertTrue(sut.getEdges().contains(edge));

		// Check if flow is 0 of newly created edge
		assertEquals(edge.getFlow(), 0);

		// Add flow edge a second time
		FlowEdge<String> edge2 = sut.addEdge("u", "v", 10);
		assertEquals(edge, edge2);

		// Add flow edge with start node not existing in graph
		try {
			sut.addEdge("X", "v", 10);
			fail("Expected an exception of type NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// expected an exception
		}

		// Add flow edge with destination node not existing in graph
		try {
			sut.addEdge("u", "X", 10);
			fail("Expected an exception of type NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// expected an exception
		}

	}

	@Test
	public void test_edgesFrom() {
		// initialize SUT with test data
		initSutWithTestData();

		// Check structure
		assertEquals(sut.edgesFrom("s").size(), 2);
		assertEquals(sut.edgesFrom("u").size(), 2);
		assertEquals(sut.edgesFrom("v").size(), 2);
		assertEquals(sut.edgesFrom("t").size(), 0);

		// Check identity of edges
		for (String start : nodes) {
			for (FlowEdge<String> edge : sut.edgesFrom(start)) {
				if (start.equals("s")) {
					assertTrue(edge.getEnd().equals("u") || edge.getEnd().equals("v"));
				}
				if (start.equals("u")) {
					assertTrue(edge.getEnd().equals("v") || edge.getEnd().equals("t"));
				}
				if (start.equals("v")) {
					assertTrue(edge.getEnd().equals("u") || edge.getEnd().equals("t"));
				}
			}
		}

		// check if node is not in graph
		try {
			sut.edgesFrom("X");
			fail("Expected an exception of type NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// expected an exception
		}
	}

	@Test
	public void test_getEdges() {
		// check for empty
		assertTrue(sut.getEdges().isEmpty());

		// initialize SUT with test data
		initSutWithTestData();

		// check structure of graph
		assertEquals(sut.getEdges().size(), 6);

		// check if edges in graph exist
		for (FlowEdge<String> edge : sut.getEdges()) {
			assertTrue(isEqual(edge, edges[0]) || isEqual(edge, edges[1]) || isEqual(edge, edges[2]) || isEqual(edge, edges[3])
					|| isEqual(edge, edges[4]) || isEqual(edge, edges[5]));
		}

	}

	@Test
	public void test_getEdge() {
		// initialize SUT with test data
		initSutWithTestData();
		
		// check behavior for start equals null
		assertNull(sut.getEdge(null, "t"));

		// check behavior for end equals null
		assertNull(sut.getEdge("s", null));
		
		// check behavior for edge whose direction doesn't exist in graph
		assertNull(sut.getEdge("t", "u"));
		
		// check behavior for edge with not existing node not in graph
		assertNull(sut.getEdge("s", "X"));
		
		// check behavior with test data
		assertTrue(isEqual(sut.getEdge("s", "u"), edges[0]));
		assertTrue(isEqual(sut.getEdge("s", "v"), edges[1]));
		assertTrue(isEqual(sut.getEdge("u", "v"), edges[2]));
		assertTrue(isEqual(sut.getEdge("v", "u"), edges[3]));
		assertTrue(isEqual(sut.getEdge("u", "t"), edges[4]));
		assertTrue(isEqual(sut.getEdge("v", "t"), edges[5]));
	}

	@Test
	public void test_getNodes() {
		// check for empty graph
		assertTrue(sut.getNodes().isEmpty());

		// initialize SUT with test data
		initSutWithTestData();
		
		// check if size corresponds
		assertEquals(sut.getNodes().size(), 4);
		
		// check if nodes in graph exist
		assertTrue(sut.getNodes().contains("s"));
		assertTrue(sut.getNodes().contains("u"));
		assertTrue(sut.getNodes().contains("v"));
		assertTrue(sut.getNodes().contains("t"));
	}

	@Test
	public void test_containsNode() {
		// initialize SUT with test data
		initSutWithTestData();

		// check for node which exists
		assertTrue(sut.containsNode("s"));
		assertTrue(sut.containsNode("u"));
		assertTrue(sut.containsNode("v"));
		assertTrue(sut.containsNode("t"));

		// check for node which does not exist
		assertFalse(sut.containsNode("X"));
	}

	private void initSutWithTestData() {
		// initialize graph
		for (String n : nodes) {
			sut.addNode(n);
		}
		for (FlowEdge<String> e : edges) {
			sut.addEdge(e.getStart(), e.getEnd(), e.getCapacity());
		}
	}

	/**
	 * Tests if two edges are equal by comparing the parameters of the two edges.
	 * 
	 * @param edge1 first edge
	 * @param edge2 second edge
	 * @param return true if parameters of edge are equal false otherwise
	 */
	private boolean isEqual(FlowEdge<String> edge1, FlowEdge<String> edge2) {
		return edge1.getStart().equals(edge2.getStart()) && edge1.getEnd().equals(edge2.getEnd())
				&& edge1.getCapacity() == edge2.getCapacity();
	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
