package solutions.exercise3;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.AbstractFlowGraphTest;
import org.sopra.api.exercises.exercise3.FlowEdge;

/**
 * This class implements a solution of AB 3.3.
 */
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
		sut.addNode("a");
		sut.addNode("b");
		FlowEdge<String> ret = sut.addEdge("a", "b", 10);

		assertEquals(ret.getStart(), "a");
		assertEquals(ret.getEnd(), "b");
		assertEquals(ret.getCapacity(), 10);
		assertTrue(sut.getEdges().contains(ret));

		// Check if flow is 0 of newly created edge
		assertEquals(ret.getFlow(), 0);

		// Add flow edge a second time
		FlowEdge<String> ret2 = sut.addEdge("a", "b", 10);
		assertEquals(ret, ret2);

		// Add flow edge with start node not existing in graph
		try {
			sut.addEdge("X", "b", 10);
			fail("Expected an exception of type NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// expected an exception
		}

		// Add flow edge with destination node not existing in graph
		try {
			sut.addEdge("a", "X", 10);
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
		assertEquals(sut.edgesFrom("a").size(), 2);
		assertEquals(sut.edgesFrom("b").size(), 2);
		assertEquals(sut.edgesFrom("t").size(), 0);

		// Check identity of edges
		for (String n : nodes) {
			for (FlowEdge<String> e : sut.edgesFrom(n)) {
				if (n.equals("s")) {
					String end = e.getEnd();
					assertTrue(end.equals("a") || end.equals("b"));
				}
				if (n.equals("a")) {
					String end = e.getEnd();
					assertTrue(end.equals("b") || end.equals("t"));
				}
				if (n.equals("b")) {
					String end = e.getEnd();
					assertTrue(end.equals("a") || end.equals("t"));
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

		// check for null parameter
		try {
			sut.edgesFrom(null);
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
		for (FlowEdge<String> e : sut.getEdges()) {
			assertTrue(isEqual(e, edges[0]) || isEqual(e, edges[1]) || isEqual(e, edges[2]) || isEqual(e, edges[3])
					|| isEqual(e, edges[4]) || isEqual(e, edges[5]));
		}

	}

	@Test
	public void test_getEdge() {
		// initialize SUT with test data
		initSutWithTestData();

		// check behavior with test data
		assertTrue(isEqual(sut.getEdge("s", "a"), edges[0]));
		assertTrue(isEqual(sut.getEdge("s", "b"), edges[1]));
		assertTrue(isEqual(sut.getEdge("a", "b"), edges[2]));
		assertTrue(isEqual(sut.getEdge("b", "a"), edges[3]));
		assertTrue(isEqual(sut.getEdge("a", "t"), edges[4]));
		assertTrue(isEqual(sut.getEdge("b", "t"), edges[5]));

		assertNull(sut.getEdge("t", "a"));

		// check behavior for edge not in graph
		assertNull(sut.getEdge("s", "X"));

		// check behavior for start equals null
		assertNull(sut.getEdge(null, "t"));

		// check behavior for end equals null
		assertNull(sut.getEdge("s", null));

	}

	@Test
	public void test_getNodes() {
		// check for empty graph
		assertTrue(sut.getNodes().isEmpty());

		// initialize SUT with test data
		initSutWithTestData();

		// check if nodes in graph exist
		assertEquals(sut.getNodes().size(), 4);
		assertTrue(sut.getNodes().contains("s"));
		assertTrue(sut.getNodes().contains("a"));
		assertTrue(sut.getNodes().contains("b"));
		assertTrue(sut.getNodes().contains("t"));
	}

	@Test
	public void test_containsNode() {
		// initialize SUT with test data
		initSutWithTestData();

		// check for node which exists
		assertTrue(sut.containsNode("s"));
		assertTrue(sut.containsNode("a"));
		assertTrue(sut.containsNode("b"));
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
	 * Tests if two edges are equal by comparing the parameters of the two
	 * edges.
	 * 
	 * @param edge1
	 *            first edge
	 * @param edge2
	 *            second edge
	 * @param return
	 *            true if parameters of edge are equal false otherwise
	 */
	private boolean isEqual(FlowEdge<String> edge1, FlowEdge<String> edge2) {
		return edge1.getStart().equals(edge2.getStart()) && edge1.getEnd().equals(edge2.getEnd())
				&& edge1.getCapacity() == edge2.getCapacity();
	}

	/**
	 * Returns the informations of the team.
	 * 
	 * @returns text with team team number.
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
