package solutions.exercise2;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractQuicksortTest;

public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission {
	
	@Override
	@Test
	public void testPartition() {
		testPartition(new Integer[] { 1, 2 }, 0, 1, 1);
		testPartition(new Integer[] { 1, 2, 2}, 0, 2, 2);
		testPartition(new Integer[] { 1, 2, 2, 2}, 0, 3, 3);
		testPartition(new Integer[] { 1, 1, 2, 2}, 0, 3, 1);
		testPartition(new Integer[] { 1, 1, 1, 2}, 0, 3, 2);
		testPartition(new Integer[] { 1, 2, 3, 4, 5 }, 0, 4, 3);
		testPartition(new Integer[] { 5, 4, 3, 2, 1 }, 2, 4, 4);
		testPartition(new Integer[] { 1, 1, 4, 4, 2, 2, 3, 3 }, 2, 5, 4);
	}

	private void testPartition(Integer[] input, int left, int right, int expectedIndex) {
		int pivot = input[(right+left)/2];

		int actualIndex = sut.partition(input, left, right);

		assertThat("Computed index is wrong.", actualIndex, is(expectedIndex));

		for (int i = left; i < expectedIndex; i++) {
			assertThat("Left partition contains values less than or equal to pivot.", input[i], lessThanOrEqualTo(pivot));
		}

		for (int j = right; j > expectedIndex; j--) {
			assertThat("Right partition contains values greater than pivot.", input[j], greaterThanOrEqualTo(pivot));
		}
	}

	@Override
	@Test
	public void testPartition_Parameters() {
		try {
			sut.partition(null, 1, 3);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			sut.partition(new Integer[] { 1, 2, 3 }, -1, 2);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			sut.partition(new Integer[] { 1, 2, 3 }, 2, -1);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			sut.partition(new Integer[] { 1, 2, 3 }, 3, 2);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			sut.partition(new Integer[] { 1, 2, 3 }, 4, 5);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Override
	@Test
	public void testQuicksort() {
		testQuicksort(new Integer[] { -1, -3 }, new Integer[] { -3, -1 });
		testQuicksort(new Integer[] { 3, 4, 2, 4, 0, 10, 1, 0 }, new Integer[] { 0, 0, 1, 2, 3, 4, 4, 10 });
		testQuicksort(new Integer[] { 1, 1, 1, 1, 0, 1, 1, -3 }, new Integer[] { -3, 0, 1, 1, 1, 1, 1, 1 });
		testQuicksort(new Integer[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
	}

	private void testQuicksort(Integer[] input, Integer[] expected) {
		sut.quicksort(input, 0, input.length - 1);
		
		assertThat("Array is not sorted.", input, equalTo(expected));
	}

	@Override
	@Test
	public void testQuicksort_Parameters() {
		try {
			sut.quicksort(null, 1, 3);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
		}

		try {
			sut.quicksort(new Integer[] { 1, 2, 3 }, -1, 2);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
		}

		try {
			sut.quicksort(new Integer[] { 1, 2, 3 }, 4, 5);
			fail("IllegalArgumentException was not detected.");
		} catch (IllegalArgumentException e) {
			assertThat("Expected IllegalArgumentException.", e, instanceOf(IllegalArgumentException.class));
		}

	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
