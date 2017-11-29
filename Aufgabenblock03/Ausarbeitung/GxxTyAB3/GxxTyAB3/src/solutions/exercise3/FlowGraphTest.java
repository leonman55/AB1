package solutions.exercise3;


import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.AbstractFlowGraphTest;
import org.sopra.api.exercises.exercise3.FlowEdge;

/**
 * Die Klasse tested, ob die Methoden der Klasse FlowGraphImpl<V> ihre Funktionen richtig ausführt. 
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 *
 * @param <V>
 */
public class FlowGraphTest<V> extends AbstractFlowGraphTest implements ExerciseSubmission {

	private FlowEdge<String> e1;
	private FlowEdge<String> e2;
	private FlowEdge<String> e3;
	
	@Before
	public void setUp()
	{
		sut.addNode("u");
		sut.addNode("s");
		sut.addNode("v");
		sut.addNode("t");
		
		e1 = sut.addEdge("s", "u", 5);
		e2 = sut.addEdge("v", "t", 6);
		e3 = sut.addEdge("v", "u", 9);
		
	}
	
	

	/**
	 * tested die Methode addEdge indem sie tested, ob die Kanten richtig ergänzt wurden. 
	 */
	@Override
	@Test
	public void test_addEdge() {
		// TODO Auto-generated method stub

		assertEquals(sut.addEdge("s", "u", 5), e1);
		assertEquals(sut.addEdge("v", "t", 6), e2);
		assertEquals(sut.addEdge("v", "u", 9), e3);
		
//		try
//		{
//			fail();
//		}
//		catch(Exception e)
//		{
//			
//		}
	}

	/**
	 * tested, ob die Methode addNode neue Knoten richtig ergänzt
	 * dazu gibt es Positivbeispiele und Widersprüche. 
	 */
	@Override
	@Test
	public void test_addNode() {
		// TODO Auto-generated method stub
		
		assertTrue(sut.addNode("u"));
		assertFalse(sut.addNode("u"));
		assertFalse(sut.addNode(null));
	}

	/**
	 * tested die Methode containsNode mit Positivbeispielen und Widersprüchen. 
	 */
	@Override
	@Test
	public void test_containsNode() {
		// TODO Auto-generated method stub

		assertTrue(sut.containsNode("u"));
		assertTrue(sut.containsNode("s"));
		assertFalse(sut.containsNode("h"));
		assertFalse(sut.containsNode("g"));
	}

	/**
	 * tested die Methode edgesFrom indem mehrere Kanten erstellt werden 
	 * und dann abgefragt wird, ob alle richtig erfasst werden. 
	 */
	@Override
	@Test
	public void test_edgesFrom() {
		// TODO Auto-generated method stub
		
		ArrayList<FlowEdge<String>> list = new ArrayList<FlowEdge<String>>();
		list.add(e2); 
		list.add(e3);
		
		assertEquals(sut.edgesFrom("v"), list);
		
	}

	/**
	 * tested die Methode getEdge indem Kanten erstellt werden und dann getested wird, 
	 * ob sie einzeln richtig widergegeben werden. 
	 */
	@Override
	@Test
	public void test_getEdge() {
		// TODO Auto-generated method stub
		
		assertEquals(sut.getEdge("s", "u"), e1);
		assertEquals(sut.getEdge("v", "t"), e2);
		assertEquals(sut.getEdge("v", "u"), e3);
//		assertNull(sut.getEdge("s", "b"));
//		assertNull(sut.getEdge("b", "t"));
		assertNull(sut.getEdge("t", null));
		assertNull(sut.getEdge(null, "u"));
		
	}

	/**
	 * tested die Methode getEdge indem Kanten erstellt werden und dann getested wird, 
	 * ob sie in einer Liste gesammelt richtig widergegeben werden. 
	 */
	@Override
	@Test
	public void test_getEdges() {
		// TODO Auto-generated method stub
		
		ArrayList<FlowEdge<String>> list = new ArrayList<FlowEdge<String>>();
		list.add(e1); 
		list.add(e2); 
		list.add(e3);
		
		assertEquals(sut.getEdges(), list);
		
	}

	/**
	 * tested die Methode getNodes indem mehrere Knoten erstellt werden 
	 * und dann abgefragt wird, ob sie in einer Liste gesammelt richtig widergegeben werden. 
	 */
	@Override
	@Test
	public void test_getNodes() {
		// TODO Auto-generated method stub
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("u"); 
		list.add("s"); 
		list.add("t");
		list.add("v");
		
		assertEquals(sut.getNodes(), list);
	}

	/**
	 * gibt die Gruppen- und Teamnummer als String zurück
	 */
	@Override
	public String getTeamIdentifier() {
		// TODO Auto-generated method stub
		return "G05T04";
	}
}
