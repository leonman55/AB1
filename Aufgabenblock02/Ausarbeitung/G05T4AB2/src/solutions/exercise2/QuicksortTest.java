package solutions.exercise2;

<<<<<<< HEAD
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

=======
import static org.junit.Assert.*;

import org.junit.Test;
>>>>>>> leonman55AB03
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractQuicksortTest;

/**
 * @author Isabelle, Leon, Pascal, Stefan
 */
<<<<<<< HEAD
public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission {

	/*
	 * Test ob die Methode sut.partition ihre Funktion erfüllt
	 */
	@Test
	public void testPartition() {

		/*
		 * Hier wird getestet, ob der Index des Piveauelementes richtig bestimmt wird.
		 */
		Integer[] arr1 = { 1, 1, 1, 1, 1 };

		int treffpunkt12 = sut.partition(arr1, 0, 1);
		assertEquals(treffpunkt12, 1);

		int treffpunkt13 = sut.partition(arr1, 0, 2);
		assertEquals(treffpunkt13, 2);

		int treffpunkt14 = sut.partition(arr1, 0, 3);
		assertEquals(treffpunkt14, 2);

		int treffpunkt15 = sut.partition(arr1, 0, 4);
		assertEquals(treffpunkt15, 3);

		Integer[] arr2 = { 8, 3, 6, 4, 1 };

		int treffpunkt21 = sut.partition(arr2, 0, 4);
		assertEquals(treffpunkt21, 3);

		int treffpunkt22 = sut.partition(arr2, 0, 3);
		assertEquals(treffpunkt22, 2);

		Integer[] arr2l = { 1, 3, 4, 6, 8, }; // Das Lösungsarray nach dem Durchlaufen des Sortieralgorithmusses

		assertTrue(Arrays.equals(arr2, arr2l)); // Vergleich ob Lösungsarray und bestimmtes Array übereinstimmen

		/*
		 * Test ob bei komplexerem Array nach 1nem Sortierschritt die sortierung richtig
		 * verlaufen ist
		 */

		Integer[] arr3 = { 1, 12, 5, 26, 7, 14, 3, 7, 2, };
		sut.partition(arr3, 0, arr3.length - 1);

		Integer[] arr3l = { 1, 2, 5, 7, 3, 14, 7, 26, 12, };
		assertTrue(Arrays.equals(arr3, arr3l));

		Integer[] arr4 = { -500, 400, -30, 3, 5, 600, 20 };
		Integer[] arr4l = { -500, 3, -30, 400, 5, 600, 20 };

		sut.partition(arr4, 0, arr4.length - 1);
		assertTrue(Arrays.equals(arr4, arr4l));

		int treffpunkt46 = sut.partition(arr4, 0, arr4.length - 1);
		assertEquals(treffpunkt46, 5);

		int treffpunkt44 = sut.partition(arr4, 0, arr4.length - 3);
		assertEquals(treffpunkt44, 2);

	}

	/*
	 * Test ob die Methode sut.partition falsch übergebende bzw fehlerhafte
	 * Parameter richtig erkennt
	 */
	@Test
	public void testPartition_Parameters() {
		Integer[] arr = { 1, 2, 3 };
		try {
			sut.partition(null, 0, 2); // Test zum Erkennen für null-array
			fail();
		} catch (IllegalArgumentException e) {

		}

		try {
			sut.partition(arr, arr.length + 1, arr.length + 2); // Beide größer als Länge; rechts>links
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			sut.partition(arr, arr.length + 2, arr.length + 1); // beide größer als Länge; rechts<links
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			sut.partition(arr, 1, arr.length + 2); // links innerhalb, rechts größer als Länge; rechts>links
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.partition(arr, arr.length + 2, 1); // rechts innerhalb, links größer als Länge; rechts<links
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			sut.partition(arr, 2, 1); // beide innerhalb, rechts<links
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.partition(arr, -2, 2); // links <0, rechts innerhalb; links<rechts
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.partition(arr, 2, -2); // rechts<0, links innerhalb; rechts>links
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			sut.partition(arr, -3, -2); // beide <0; links<rechts
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			sut.partition(arr, -2, -3); // beide<0, rechts<links
			fail();
		} catch (IllegalArgumentException e) {

		}

	}

	/*
	 * Test ob die Methode sut.quicksort ihre Funktion erfüllt
	 */
	@Override
	@Test
	public void testQuicksort() {
		Integer[] arr1 = { 8, 3, 6, 4, 1 };
		Integer[] ergebnis1 = { 1, 3, 4, 6, 8 };
		sut.quicksort(arr1, 0, arr1.length - 1);
		assertArrayEquals(arr1, ergebnis1);

		Integer[] arr3 = { 1, 12, 5, 26, 7, 14, 3, 7, 2, };
		Integer[] arr3l = { 1, 2, 3, 5, 7, 7, 12, 14, 26, };
		sut.quicksort(arr3, 0, arr3.length - 1);
		assertTrue(Arrays.equals(arr3, arr3l));

		Integer[] arr4 = { -581, 12, -234, 26, 3257, 134, 0, -7, -2, };
		Integer[] arr4l = { -581, -234, -7, -2, 0, 12, 26, 134, 3257, };
		sut.quicksort(arr4, 0, arr4.length - 1);
		assertTrue(Arrays.equals(arr4, arr4l));
	}

	/*
	 * Test ob die Methode sut.quicksort falsch übergebende bzw fehlerhafte
	 * Parameter richtig erkennt
	 */

	@Override
	@Test
	public void testQuicksort_Parameters() {
		Integer[] arr = { 1, 2, 3 };
		try {
			sut.quicksort(null, 0, 2); // Test zum Erkennen für null-array
			fail();
		} catch (IllegalArgumentException e) {

		}

		try {
			sut.quicksort(arr, arr.length + 1, arr.length + 2); // Beide größer als Länge; rechts>links
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			sut.quicksort(arr, arr.length + 2, arr.length + 1); // beide größer als Länge; rechts<links
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			sut.quicksort(arr, 1, arr.length + 2); // links innerhalb, rechts größer als Länge; rechts>links
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.quicksort(arr, arr.length + 2, 1); // rechts innerhalb, links größer als Länge; rechts<links
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			sut.quicksort(arr, 2, 1); // beide innerhalb, rechts<links
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.quicksort(arr, -2, 2); // links <0, rechts innerhalb; links<rechts
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.quicksort(arr, 2, -2); // rechts<0, links innerhalb; rechts>links
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			sut.quicksort(arr, -3, -2); // beide <0; links<rechts
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			sut.quicksort(arr, -2, -3); // beide<0, rechts<links
			fail();
		} catch (IllegalArgumentException e) {

		}
	}

	@Override
	public String getTeamIdentifier() {
		// TODO Auto-generated method stub
=======
public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission
{
	Integer[] testint = {5, 8, 1, 5, 76, 3, 8, 9, 10};
	
	@Override
	@Test
	public void testPartition()
	{
		// TODO Auto-generated method stub
		int back = sut.partition(testint, 0, 8);
		System.out.println(back);
		assertEquals(back, 76);
	}

	@Override
	@Test
	public void testPartition_Parameters()
	{
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testQuicksort()
	{
		// TODO Auto-generated method stub

	}

	@Override
	@Test
	public void testQuicksort_Parameters()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getTeamIdentifier()
	{
>>>>>>> leonman55AB03
		return "G05T04";
	}

}
