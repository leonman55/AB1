package solutions.exercise4;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

import solutions.exercise3.ResidualGraphImpl;

public class FordFulkersonTest extends AbstractFordFulkersonTest implements ExerciseSubmission
{

	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	@Test
	public void test_augmentPath1()
	{
		ResidualGraphImpl<String> graph = new ResidualGraphImpl<String>(flowGraph1);
		Deque<ResidualEdge<String>> path = new ArrayDeque<ResidualEdge<String>>();

		path.add(graph.getEdge("s", "b"));
		path.add(graph.getEdge("b", "a"));
		path.add(graph.getEdge("a", "d"));
		path.add(graph.getEdge("d", "t"));

		int flowSB = graph.getEdge("s", "b").getCapacity();
		int flowBA = graph.getEdge("b", "a").getCapacity();
		int flowAD = graph.getEdge("a", "d").getCapacity();
		int flowDT = graph.getEdge("d", "t").getCapacity();

		sut.augmentPath(path);

		flowSB -= graph.getEdge("s", "b").getCapacity();
		flowBA -= graph.getEdge("b", "a").getCapacity();
		flowAD -= graph.getEdge("a", "d").getCapacity();
		flowDT -= graph.getEdge("d", "t").getCapacity();

		assertEquals(flowSB, 3);
		assertEquals(flowBA, 3);
		assertEquals(flowAD, 3);
		assertEquals(flowDT, 3);
	}

	@Test
	public void test_augmentPath2()
	{
		ResidualGraphImpl<String> graph = new ResidualGraphImpl<String>(flowGraph2);
		Deque<ResidualEdge<String>> path = new ArrayDeque<ResidualEdge<String>>();

		path.add(graph.getEdge("s", "a"));
		path.add(graph.getEdge("a", "c"));
		path.add(graph.getEdge("c", "b"));
		path.add(graph.getEdge("b", "e"));
		path.add(graph.getEdge("e", "t"));

		int flowSA = graph.getEdge("s", "a").getCapacity();
		int flowAC = graph.getEdge("a", "c").getCapacity();
		int flowCB = graph.getEdge("c", "b").getCapacity();
		int flowBE = graph.getEdge("b", "e").getCapacity();
		int flowET = graph.getEdge("e", "t").getCapacity();

		sut.augmentPath(path);

		flowSA -= graph.getEdge("s", "a").getCapacity();
		flowAC -= graph.getEdge("a", "c").getCapacity();
		flowCB -= graph.getEdge("c", "b").getCapacity();
		flowBE -= graph.getEdge("b", "e").getCapacity();
		flowET -= graph.getEdge("e", "t").getCapacity();

		assertEquals(flowSA, 5);
		assertEquals(flowAC, 5);
		assertEquals(flowCB, 5);
		assertEquals(flowBE, 5);
		assertEquals(flowET, 5);
	}

	@Test
	public void test_augmentPath_ParameterNull()
	{
		try
		{
			sut.augmentPath(null);
			fail();
		}
		catch (Exception e)
		{

		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterGraphIsNull()
	{
		sut.findMaxFlow(null, "s", "t");
	}

	@Test
	public void test_findMaxFlow_ParameterStartIsNull()
	{
		try
		{
			sut.findMaxFlow(flowGraphA, null, "t");
			fail();
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_findMaxFlow_ParameterTargetIsNull()
	{
		try
		{
			sut.findMaxFlow(flowGraphA, "s", null);
			fail();
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_findMaxFlow_ParameterTargetNotInGraph()
	{
		try
		{
			sut.findMaxFlow(flowGraphA, "s", "h");
			fail();
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_findMaxFlow_flowGraphA()
	{
		sut.findMaxFlow(flowGraphA, "s", "t");
		int flowS = 0;
		int flowT = 0;
		List<FlowEdge<String>> edges = flowGraphA.getEdges();

		for (FlowEdge<String> flowEdge : edges)
		{
			if (flowEdge.getStart().equals("s"))
			{
				flowS += flowEdge.getFlow();
			}
			if (flowEdge.getEnd().equals("t"))
			{
				flowT += flowEdge.getFlow();
			}
		}
		assertEquals(flowS, 17);
		assertEquals(flowT, 17);
	}

	@Test
	public void test_findMaxFlow_flowGraphB()
	{
		sut.findMaxFlow(flowGraphB, "s", "t");
		int flowS = 0;
		int flowT = 0;
		List<FlowEdge<String>> edges = flowGraphB.getEdges();

		for (FlowEdge<String> flowEdge : edges)
		{
			if (flowEdge.getStart().equals("s"))
			{
				flowS += flowEdge.getFlow();
			}
			if (flowEdge.getEnd().equals("t"))
			{
				flowT += flowEdge.getFlow();
			}
		}
		assertEquals(flowS, 24);
		assertEquals(flowT, 24);
	}

	@Test
	public void test_findMaxFlow_flowGraphC()
	{
		sut.findMaxFlow(flowGraphC, "s", "t");
		int flowS = 0;
		int flowT = 0;
		List<FlowEdge<String>> edges = flowGraphC.getEdges();

		for (FlowEdge<String> flowEdge : edges)
		{
			if (flowEdge.getStart().equals("s"))
			{
				flowS += flowEdge.getFlow();
			}
			if (flowEdge.getEnd().equals("t"))
			{
				flowT += flowEdge.getFlow();
			}
		}
		assertEquals(flowS, 10);
		assertEquals(flowT, 10);
	}

	@Test
	public void test_findPath1()
	{
		ResidualGraphImpl<String> graph = new ResidualGraphImpl<String>(flowGraph1);
		Deque<ResidualEdge<String>> ist = new ArrayDeque<ResidualEdge<String>>();

		ist = sut.findPath("s", "t", graph);

		for (ResidualEdge<String> residualEdge : ist)
		{
			assertTrue((residualEdge.getStart() == "s" && residualEdge.getEnd() == "a")
					|| (residualEdge.getStart() == "a" && residualEdge.getEnd() == "d")
					|| (residualEdge.getStart() == "d" && residualEdge.getEnd() == "t"));
		}
	}

	@Test
	public void test_findPath2()
	{
		ResidualGraphImpl<String> graph = new ResidualGraphImpl<String>(flowGraph2);
		Deque<ResidualEdge<String>> ist = new ArrayDeque<ResidualEdge<String>>();

		ist = sut.findPath("s", "t", graph);

		for (ResidualEdge<String> residualEdge : ist)
		{
			assertTrue(residualEdge.getStart() == "s" || residualEdge.getStart() == "b"
					|| residualEdge.getStart() == "e" || residualEdge.getEnd() == "b" || residualEdge.getEnd() == "e"
					|| residualEdge.getEnd() == "t");
		}
	}

	@Test
	public void test_findPath_IsNull()
	{
		ResidualGraphImpl<String> graph = new ResidualGraphImpl<String>(flowGraph3);
		assertNull(sut.findPath("s", "t", graph));
	}

	@Test
	public void test_findPath_ParameterGraphIsNull()
	{
		try
		{
			sut.findPath("s", "t", null);
			fail();
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_findPath_ParameterStartIsNull()
	{
		ResidualGraphImpl<String> graph = new ResidualGraphImpl<String>(flowGraph3);
		try
		{
			sut.findPath(null, "t", graph);
			fail();
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_findPath_ParameterTargetIsNull()
	{
		ResidualGraphImpl<String> graph = new ResidualGraphImpl<String>(flowGraph3);

		try
		{
			sut.findPath("s", null, graph);
			fail();
		}
		catch (Exception e)
		{

		}
	}
}
