package solutions.exercise3;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.AbstractFlowGraphTest;
import org.sopra.api.exercises.exercise3.FlowEdge;

/**
 * Die Klasse testet, ob die Methoden der Klasse FlowGraphImpl ihre Funktionen
 * richtig ausführen.
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class FlowGraphTest extends AbstractFlowGraphTest implements ExerciseSubmission
{

	/**
	 * Testet die Methode addEdge auf richtige Funktionsweise
	 */
	@Override
	@Test
	public void test_addEdge()
	{
		sut.addNode("s");
		sut.addNode("u");
		FlowEdge<String> e1 = sut.addEdge("s", "u", 5);
		assertEquals(e1.getStart(), "s");
		assertEquals(e1.getEnd(), "u");
		assertEquals(e1.getCapacity(), 5);
		assertEquals(e1.getFlow(), 0);
		assertEquals(sut.addEdge("s", "u", 5), e1);
		assertEquals(sut.getEdge("s", "u"), e1);

		try
		{
			sut.addEdge("s", "a", 5);
			fail();
		} catch (NoSuchElementException e)
		{

		}
		try
		{
			sut.addEdge("b", "s", 5);
			fail();
		} catch (NoSuchElementException e)
		{

		}
	}

	/**
	 * Testet die Methode addNode auf richtige Funktionsweise
	 */
	@Override
	@Test
	public void test_addNode()
	{
		assertTrue(sut.addNode("u"));
		assertFalse(sut.addNode("u"));
		assertFalse(sut.addNode(null));
	}

	/**
	 * Testet die Methode containsNode auf richtige Funktionsweise
	 */
	@Override
	@Test
	public void test_containsNode()
	{
		sut.addNode("s");
		sut.addNode("u");

		assertTrue(sut.containsNode("u"));
		assertTrue(sut.containsNode("s"));
		assertFalse(sut.containsNode("h"));
		assertFalse(sut.containsNode("g"));
	}

	/**
	 * Testet die Methode edgesFrom auf richtige Funktionsweise
	 */
	@Override
	@Test
	public void test_edgesFrom()
	{
		sut.addNode("s");
		sut.addNode("u");
		sut.addNode("v");

		String nodes[] = { "s", "u", "v" };

		FlowEdge<String> e1 = sut.addEdge("s", "u", 5);
		FlowEdge<String> e2 = sut.addEdge("s", "v", 7);

		assertEquals(sut.edgesFrom("s").size(), 2);

		FlowEdge<String> e3 = sut.addEdge("u", "s", 10);
		FlowEdge<String> e4 = sut.addEdge("u", "v", 14);

		assertEquals(sut.edgesFrom("u").size(), 2);

		FlowEdge<String> e5 = sut.addEdge("v", "s", 2);
		FlowEdge<String> e6 = sut.addEdge("v", "u", 9);

		assertEquals(sut.edgesFrom("v").size(), 2);

		sut.addEdge("s", "u", 5);
		sut.addEdge("s", "v", 7);
		sut.addEdge("u", "s", 10);
		sut.addEdge("u", "v", 14);
		sut.addEdge("v", "s", 2);
		sut.addEdge("v", "u", 9);

		for (String node : nodes)
		{
			for (FlowEdge<String> flowEdge : sut.edgesFrom(node))
			{
				switch (node)
				{
				case "s":
				{
					assertTrue((flowEdge.getStart().equals(e1.getStart()) && flowEdge.getEnd().equals(e1.getEnd())
							&& flowEdge.getCapacity() == e1.getCapacity())
							|| (flowEdge.getStart().equals(e2.getStart()) && flowEdge.getEnd().equals(e2.getEnd())
									&& flowEdge.getCapacity() == e2.getCapacity()));
					break;
				}
				case "u":
				{
					assertTrue((flowEdge.getStart().equals(e3.getStart()) && flowEdge.getEnd().equals(e3.getEnd())
							&& flowEdge.getCapacity() == e3.getCapacity())
							|| (flowEdge.getStart().equals(e4.getStart()) && flowEdge.getEnd().equals(e4.getEnd())
									&& flowEdge.getCapacity() == e4.getCapacity()));
					break;
				}
				case "v":
				{
					assertTrue((flowEdge.getStart().equals(e5.getStart()) && flowEdge.getEnd().equals(e5.getEnd())
							&& flowEdge.getCapacity() == e5.getCapacity())
							|| (flowEdge.getStart().equals(e6.getStart()) && flowEdge.getEnd().equals(e6.getEnd())
									&& flowEdge.getCapacity() == e6.getCapacity()));
					break;
				}
				}
			}
		}

		try
		{
			sut.edgesFrom("a");
			fail();
		} catch (NoSuchElementException e)
		{

		}
	}

	/**
	 * Testet die Methode getEdge auf richtige Funktionsweise
	 */
	@Override
	@Test
	public void test_getEdge()
	{
		sut.addNode("s");
		sut.addNode("u");
		sut.addNode("v");
		FlowEdge<String> e1 = sut.addEdge("s", "u", 5);
		FlowEdge<String> e2 = sut.addEdge("s", "v", 7);

		assertEquals(sut.getEdge("s", "u"), e1);
		assertEquals(sut.getEdge("s", "v"), e2);
		assertNull(sut.getEdge("t", null));
		assertNull(sut.getEdge(null, "u"));
		assertNull(sut.getEdge("s", "a"));
		assertNull(sut.getEdge("u", "v"));
	}

	/**
	 * Testet die Methode getEdges auf richtige Funktionsweise
	 */
	@Override
	@Test
	public void test_getEdges()
	{
		assertFalse(sut.getEdges().size() != 0);

		sut.addNode("s");
		sut.addNode("u");
		sut.addNode("v");

		FlowEdge<String> e1 = sut.addEdge("s", "u", 5);
		FlowEdge<String> e2 = sut.addEdge("s", "v", 7);

		assertEquals(sut.getEdges().size(), 2);

		for (FlowEdge<String> flowEdge : sut.getEdges())
		{
			assertTrue((flowEdge.getStart().equals(e1.getStart()) && flowEdge.getEnd().equals(e1.getEnd())
					&& flowEdge.getCapacity() == e1.getCapacity())
					|| (flowEdge.getStart().equals(e2.getStart()) && flowEdge.getEnd().equals(e2.getEnd())
							&& flowEdge.getCapacity() == e2.getCapacity()));
		}
	}

	/**
	 * Testet die Methode getNodes auf richtige Funktionsweise
	 */
	@Override
	@Test
	public void test_getNodes()
	{
		assertFalse(sut.getNodes().size() != 0);

		sut.addNode("s");
		sut.addNode("u");
		sut.addNode("v");

		assertEquals(sut.getNodes().size(), 3);

		ArrayList<String> list = new ArrayList<String>();
		list.add("u");
		list.add("s");
		list.add("v");

		for (String node : sut.getNodes())
		{
			assertTrue((node.equals("u") || node.equals("s") || node.equals("v")));
		}
	}

	/**
	 * Gibt den Teamidentifier zurück
	 * 
	 * @return Teamidentifier
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}
}
