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
	/**
	 * Gibt den Teamidentifier zurück
	 * 
	 * @return TeamIdentifier als String
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

	/**
	 * Tested ob die Implementierung der Methode augmentPast richtig ist. Genutzt
	 * wird dazu flowGraph 1 mit dem Pfad von s nach t über b, a und d.
	 */
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

	/**
	 * Tested ob die Implementierung der Methode augmentPath richtig ist. Genutzt
	 * wird dazu flowGraph 2 mit dem Pfad von s nach t über a, c, b und e.
	 */
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

	/**
	 * Es wird getested ob die Methode augmentPath richtig mit dem fehlerhaften
	 * Parameter null umgeht.
	 */
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

	/**
	 * Es wird getested ob die Methode findMaxFlow richtig damit umgeht null
	 * anstelle des Graphen erhält.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterGraphIsNull()
	{
		sut.findMaxFlow(null, "s", "t");
	}

	/**
	 * Es wird getested ob die Methode findeMaxFlow richtig mit null anstelle einem
	 * ordnungsgemäßem Parameter Start umgeht.
	 */
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

	/**
	 * Es wird getested ob die Methode findeMaxFlow richtig mit null anstelle einem
	 * ordnungsgemäßem Parameter Target umgeht.
	 */

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

	/**
	 * Es wird getested ob die Methode findMaxFlow richtig mit einem Endknoten
	 * umgeht, der außerhalb des Graphen liegt.
	 */
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

	/**
	 * Es wird getested ob im Testgraph flowGraphA durch die Methode findMaxFlow der
	 * richtige maximale Fluss von s nach t bestimmt wird.
	 */
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

	/**
	 * Es wird getested ob im Testgraph flowGraphB durch die Methode findMaxFlow der
	 * richtige maximale Fluss von s nach t bestimmt wird.
	 */
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

	/**
	 * Es wird getested ob im Testgraph flowGraphC durch die Methode findMaxFlow der
	 * richtige maximale Fluss von s nach t bestimmt wird.
	 */
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

	/**
	 * Es wird getested ob im flowGraph1 die Methode flowPath den richtigen
	 * kürzesten Pfad findet.
	 */
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

	/**
	 * Es wird getested ob im flowGraph2 die Methode flowPath den richtigen
	 * kürzesten Pfad findet.
	 */

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

	/**
	 * Es wird getested ob die Methode findPath im Testgraph flowGraph3 richtig
	 * damit umgeht, dass es keinen Pfad mit einer Kapazität >0 von s nach t gibt.
	 */

	@Test
	public void test_findPath_IsNull()
	{
		ResidualGraphImpl<String> graph = new ResidualGraphImpl<String>(flowGraph3);
		assertNull(sut.findPath("s", "t", graph));
	}

	/**
	 * Es wird getested ob die Methode findPath richtig mit dem Parameter null
	 * anstelle eines Graphen umgeht.
	 */
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

	/**
	 * Es wird getested ob die Methode findPath richtig mit dem Parameter null
	 * anstelle eines ordnungsgemäßen Starts umgeht.
	 */
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

	/**
	 * Es wird getested ob die Methode findPath richtig mit dem Parameter null
	 * anstelle eines ordnungsgemäßen Targets umgeht.
	 */
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
