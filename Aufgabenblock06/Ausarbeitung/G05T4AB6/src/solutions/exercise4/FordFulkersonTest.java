package solutions.exercise4;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;

import solutions.exercise3.ResidualGraphImpl;

public class FordFulkersonTest extends AbstractFordFulkersonTest implements ExerciseSubmission {
    
    	@Override
    	@Test
	public void test_findPath1() {
		// Expected to find one unique shortest path
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph1);

		Deque<ResidualEdge<String>> path = sut.findPath("s", "t", resGraph);

		String strPath[] = { "s", "a", "d", "t" };
		int count = strPath.length - 1;

		for (ResidualEdge<String> edge : path) {
			assertTrue(edge.getEnd() == strPath[count--] && edge.getStart() == strPath[count]);
		}
	}

	@Override
	@Test
	public void test_findPath2() {
		// Two paths with equal lengths, but one has no remaining capacity. Expected to find one unique shortest path
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph2);

		Deque<ResidualEdge<String>> path = sut.findPath("s", "t", resGraph);

		String strPath[] = { "s", "b", "e", "t" };
		int count = strPath.length - 1;

		for (ResidualEdge<String> edge : path) {
			assertTrue(edge.getEnd() == strPath[count--] && edge.getStart() == strPath[count]);
		}
	}

	@Override
	@Test
	public void test_findPath_IsNull() {
		// No path with remaining capacity exists Expected to return null
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph3);

		Deque<ResidualEdge<String>> path = sut.findPath("s", "t", resGraph);

		assertNull(path);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findPath_ParameterStartIsNull() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph2);
		sut.findPath(null, "t", resGraph);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findPath_ParameterTargetIsNull() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph2);
		sut.findPath("s", null, resGraph);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findPath_ParameterGraphIsNull() {
		sut.findPath("s", "t", null);
	}

	@Override
	@Test
	public void test_augmentPath1() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph1);

		Deque<ResidualEdge<String>> path = new ArrayDeque<>();
		path.add(resGraph.getEdge("s", "b"));
		path.add(resGraph.getEdge("b", "a"));
		path.add(resGraph.getEdge("a", "d"));
		path.add(resGraph.getEdge("d", "t"));

		sut.augmentPath(path);

		// Not all following tests are necessary. It depends which path you augment.
		
		// Edge a -> s
		assertTrue(resGraph.getEdge("s", "a").getCapacity() == 10);
		assertTrue(resGraph.getEdge("a", "s").getCapacity() == 10);
		// Edge s -> b
		assertTrue(resGraph.getEdge("s", "b").getCapacity() == 4);
		assertTrue(resGraph.getEdge("b", "s").getCapacity() == 10);
		// Edge a -> b
		assertTrue(resGraph.getEdge("a", "b").getCapacity() == 6);
		assertTrue(resGraph.getEdge("b", "a").getCapacity() == 0);
		// Edge a -> d
		assertTrue(resGraph.getEdge("a", "d").getCapacity() == 5);
		assertTrue(resGraph.getEdge("d", "a").getCapacity() == 11);
		// Edge c -> d
		assertTrue(resGraph.getEdge("c", "d").getCapacity() == 11);
		assertTrue(resGraph.getEdge("d", "c").getCapacity() == 11);
		//Edge c -> t
		assertTrue(resGraph.getEdge("c", "t").getCapacity() == 13);
		assertTrue(resGraph.getEdge("t", "c").getCapacity() == 13);
		//Edge d -> t
		assertTrue(resGraph.getEdge("d", "t").getCapacity() == 3);
		assertTrue(resGraph.getEdge("t", "d").getCapacity() == 9);
	}

	@Override
	@Test
	public void test_augmentPath2() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph2);

		Deque<ResidualEdge<String>> path = new ArrayDeque<>();
		path.add(resGraph.getEdge("s", "a"));
		path.add(resGraph.getEdge("a", "c"));
		path.add(resGraph.getEdge("c", "b"));
		path.add(resGraph.getEdge("b", "e"));
		path.add(resGraph.getEdge("e", "t"));

		sut.augmentPath(path);

		// Not all following tests are necessary. It depends which path you augment.
		
		// Edge s -> a
		assertTrue(resGraph.getEdge("s", "a").getCapacity() == 7);
		assertTrue(resGraph.getEdge("a", "s").getCapacity() == 17);
		// Edge s -> b
		assertTrue(resGraph.getEdge("s", "b").getCapacity() == 11);
		assertTrue(resGraph.getEdge("b", "s").getCapacity() == 11);
		// Edge a -> c
		assertTrue(resGraph.getEdge("a", "c").getCapacity() == 2);
		assertTrue(resGraph.getEdge("c", "a").getCapacity() == 12);
		// Edge a -> d
		assertTrue(resGraph.getEdge("a", "d").getCapacity() == 0);
		assertTrue(resGraph.getEdge("d", "a").getCapacity() == 0);
		// Edge b -> c
		assertTrue(resGraph.getEdge("b", "c").getCapacity() == 10);
		assertTrue(resGraph.getEdge("c", "b").getCapacity() == 0);
		// Edge b -> e
		assertTrue(resGraph.getEdge("b", "e").getCapacity() == 4);
		assertTrue(resGraph.getEdge("e", "b").getCapacity() == 14);
		// Edge c -> d
		assertTrue(resGraph.getEdge("c", "d").getCapacity() == 4);
		assertTrue(resGraph.getEdge("d", "c").getCapacity() == 4);
		// Edge d -> e
		assertTrue(resGraph.getEdge("d", "e").getCapacity() == 3);
		assertTrue(resGraph.getEdge("e", "d").getCapacity() == 3);
		// Edge d -> t
		assertTrue(resGraph.getEdge("d", "t").getCapacity() == 13);
		assertTrue(resGraph.getEdge("t", "d").getCapacity() == 13);
		// Edge e -> t
		assertTrue(resGraph.getEdge("e", "t").getCapacity() == 2);
		assertTrue(resGraph.getEdge("t", "e").getCapacity() == 12);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_augmentPath_ParameterNull() {
		sut.augmentPath(null);
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphA() {

		sut.findMaxFlow(flowGraphA, "s", "t");

		int dispatched = 0, arrived = 0;

		FlowEdge<String> e = flowGraphA.getEdge("s", "a");
		dispatched += e.getFlow();

		e = flowGraphA.getEdge("s", "b");
		dispatched += e.getFlow();

		e = flowGraphA.getEdge("d", "t");
		arrived += e.getFlow();

		e = flowGraphA.getEdge("e", "t");
		arrived += e.getFlow();
		
		assertEquals("Maxflow wrong", dispatched, 17);
		assertTrue("Input flow equals not output flow", dispatched == arrived);
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphB() {

		sut.findMaxFlow(flowGraphB, "s", "t");

		int dispatched = 0, arrived = 0;

		FlowEdge<String> e = flowGraphB.getEdge("s", "a");
		dispatched += e.getFlow();
		e = flowGraphB.getEdge("s", "b");
		dispatched += e.getFlow();
		e = flowGraphB.getEdge("d", "t");
		arrived += e.getFlow();
		e = flowGraphB.getEdge("e", "t");
		arrived += e.getFlow();

		assertEquals("Maxflow wrong", dispatched, 24);
		assertTrue(arrived == dispatched);
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphC() {

		sut.findMaxFlow(flowGraphC, "s", "t");

		int dispatched = 0, arrived = 0;

		FlowEdge<String> e = flowGraphC.getEdge("s", "a");
		dispatched += e.getFlow();
		e = flowGraphC.getEdge("s", "b");
		dispatched += e.getFlow();
		e = flowGraphC.getEdge("d", "t");
		arrived += e.getFlow();
		e = flowGraphC.getEdge("e", "t");
		arrived += e.getFlow();

		assertEquals("Maxflow wrong", dispatched, 10);
		assertTrue(arrived == dispatched);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterGraphIsNull() {
		sut.findMaxFlow(null, "s", "t");
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterStartIsNull() {
		sut.findMaxFlow(flowGraphB, null, "t");
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterTargetIsNull() {
		sut.findMaxFlow(flowGraphB, "s", null);
	}

	@Override
	@Test(expected = NoSuchElementException.class)
	public void test_findMaxFlow_ParameterTargetNotInGraph() {
		sut.findMaxFlow(flowGraphB, "s", "X");
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
