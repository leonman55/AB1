package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Quicksort;

public class QuicksortImpl<T> implements Quicksort<T>, ExerciseSubmission {

	private final Comparator<T> comparator;

	public QuicksortImpl(Comparator<T> comparator) {
		if (comparator == null) {
			throw new IllegalArgumentException("Arguments can't be null.");
		}
		this.comparator = comparator;
	}

	@Override
	public int partition(T[] arr, int left, int right) {
		int i = left;
		int j = right - 1;

		if (arr == null) {
			throw new IllegalArgumentException("Arguments are not allowed to be null.");
		}

		if (left < 0 || right < 0 || left > arr.length - 1 || right > arr.length - 1 || right <= left) {
			throw new IllegalArgumentException("Invalid interval.");
		}

		T pivot = arr[right];
		T tmp;

		do {
			while (comparator.compare(arr[i], pivot) <= 0 && i < right) {
				i++;
			}
			while (comparator.compare(arr[j], pivot) >= 0 && j > left) {
				j--;
			}
			if (i < j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		} while (i < j);

		if (comparator.compare(arr[i], pivot) > 0) {
			tmp = arr[i];
			arr[i] = arr[right];
			arr[right] = tmp;
		}

		return i;
	}

	@Override
	public void quicksort(T[] arr, int left, int right) {
		if (arr == null) {
			throw new IllegalArgumentException("Argument is not allowed to be null.");
		}

		if (left < right) {
			int index = partition(arr, left, right);
			quicksort(arr, left, index - 1);
			quicksort(arr, index + 1, right);
		}
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}
}
