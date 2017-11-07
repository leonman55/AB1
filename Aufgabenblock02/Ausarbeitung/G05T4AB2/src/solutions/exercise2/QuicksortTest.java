package solutions.exercise2;

import org.junit.Test;
import static org.junit.Assert.*;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractQuicksortTest;

public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission {

	@Test
	public void testPartition() {
		Integer[] arr1 = { 1, 1, 1, 1, 1 };

		int treffpunkt = sut.partition(arr1, 0, 1);
		assertEquals(treffpunkt, 1);

		treffpunkt = sut.partition(arr1, 0, 2);
		assertEquals(treffpunkt, 2);

		treffpunkt = sut.partition(arr1, 0, 3);
		assertEquals(treffpunkt, 2);

		treffpunkt = sut.partition(arr1, 0, 4);
		assertEquals(treffpunkt, 3);

		Integer[] arr2 = { 8, 3, 6, 4, 1 };
		treffpunkt = sut.partition(arr2, 0, 4);

		for (int i = 0; i <= treffpunkt; i++) {
			assertTrue(arr2[treffpunkt] >= arr2[i]);
		}
		for (int j = 4; j >= treffpunkt; j--) {
			assertTrue(arr2[treffpunkt] <= arr2[j]);
		}
	}

	@Test
	public void testPartition_Parameters() {
		Integer[] arr = { 1, 2, 3 };
		try {
			sut.partition(null, 0, 1);
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.partition(arr, -1, 2);
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.partition(arr, 0, -1);
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.partition(arr, -3, -1);
			fail();
		} catch (IllegalArgumentException e) {

		}

	}

	@Override
	@Test
	public void testQuicksort() {
		Integer[] arr1 = { 8, 3, 6, 4, 1 };
		Integer[] ergebnis = { 1, 3, 4, 6, 8 };
		sut.quicksort(arr1, 0, 4);
		
		assertArrayEquals(arr1, ergebnis);

	}

	@Override
	public void testQuicksort_Parameters() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTeamIdentifier() {
		// TODO Auto-generated method stub
		return "G05T04";
	}

}
