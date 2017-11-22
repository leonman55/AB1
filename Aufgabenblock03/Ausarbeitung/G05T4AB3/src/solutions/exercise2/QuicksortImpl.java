package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Quicksort;

public class QuicksortImpl<T> implements Quicksort<T>, ExerciseSubmission {

	private final Comparator<T> comparator;

	/**
	 * Constructor with fields as arguments.
	 * 
	 * @param comparator {@link Comparator}
	 */
	public QuicksortImpl(Comparator<T> comparator) {
		if (comparator == null) {
			throw new IllegalArgumentException("Comparator can't be null.");
		}
		this.comparator = comparator;
	}

	@Override
	public int partition(T[] arr, int left, int right) {
		int i = left;
		int j = right;

		if (arr == null) {
			throw new IllegalArgumentException("Array is not allowed to be null.");
		}

		if (left < 0 || right > arr.length - 1 || right < left) {
			throw new IllegalArgumentException("Invalid interval.");
		}

		T pivot = arr[(right + left) / 2];
		T tmp;

		while (i <= j) {
			while (comparator.compare(arr[i], pivot) < 0) {
				i++;
			}
			while (comparator.compare(arr[j], pivot) > 0) {
				j--;
			}
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		return i;
	}

	@Override
	public void quicksort(T[] arr, int left, int right) {
		if (arr == null) {
			throw new IllegalArgumentException("Array is not allowed to be null.");
		}

		int index = partition(arr, left, right);

		if (left < index - 1) {
			quicksort(arr, left, index - 1);
		}
		if (index < right) {
			quicksort(arr, index, right);
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
