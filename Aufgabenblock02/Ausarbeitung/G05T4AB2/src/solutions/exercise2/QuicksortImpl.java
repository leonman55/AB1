package solutions.exercise2;

//import java.lang.reflect.Array;
import java.util.Comparator;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Quicksort;

/**
 * Implementation des Quicksort Algorithmus
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class QuicksortImpl<T> implements Quicksort<T>, ExerciseSubmission {

	private Comparator<T> comparator;

	public QuicksortImpl(Comparator<T> comparator) throws IllegalArgumentException {
		if (comparator == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null");
		}
		this.comparator = comparator;
	}

	/**
	 * Aufteilung des Arrays in zwei Gruppen. Die erste beinhaltet Werte die
	 * kleiner(/gleich) als das Element mit Index i sind und die zweite Werte
	 * groesser(/gleich) dem Element mit Index i.
	 * 
	 * @param arr zu partitionierendes Array
	 * @param left linke Grenze des zu partitionierendes Intervalls
	 * @param right rechte Grenze des zu partitionierendes Intervalls
	 * @return i Index an dem sich die beiden Gruppen treffen
	 */
	@Override
	public int partition(T[] arr, int left, int right) {
		// TODO Noch mehr zu prüfen?
		if (arr == null || left > right || left < 0 || right < 0 || right > arr.length - 1 || left > arr.length - 1) {
			throw new IllegalArgumentException("Wrong Parameter(s)");
		}
		// Pivo Element in die Mitte setzen
		T pivo = arr[(left + right) / 2];
		// Index der von links hoch läuft
		int i = left;
		// Index der von recht runter läuft
		int j = right;
		// Ein Platzhalter für den tausch
		T platzhalter;

		do {
			// Solange von Links und Rechts zum Pivo Element laufen bis Werte gefunden
			// wurden, die getauscht werden
			while (comparator.compare(arr[i], pivo) < 0) {
				i++;
			}
			while (comparator.compare(arr[j], pivo) > 0) {
				j--;
			}

			// Elemente tauschen, falls sich der linke und rechte Zeiger noch nicht
			// getroffen haben
			if (i <= j) {
				platzhalter = arr[i];
				arr[i] = arr[j];
				arr[j] = platzhalter;
				i++;
				j--;
			}

		} while (i <= j); // Solange tauschen bis sich die Zeiger treffen

		// Der Index des Elements bei dem sich die Zeiger getroffen haben
		return i;
	}

	/**
	 * Sortieren eines Array innerhalb des vorgegebenen Intervalls.
	 * 
	 * @param arr zu sortierendes Array
	 * @param left linke Grenze des zu sortierenden Intervalls
	 * @param right rechte Grenze des zu sortierenden Intervalls
	 * 
	 */
	@Override
	public void quicksort(T[] arr, int left, int right) {
		if (arr == null) {
			throw new IllegalArgumentException("Parameter is not allowed to be null");
		}

		if (left < right) {
			int treffpunkt = partition(arr, left, right);
			if (left < treffpunkt) {
				quicksort(arr, left, treffpunkt - 1);
			}
			if (right > treffpunkt) {
				quicksort(arr, treffpunkt, right);
			}
		}

	}

	@Override
	public String getTeamIdentifier() {
		// TODO Auto-generated method stub
		return "G05T04";
	}

}
