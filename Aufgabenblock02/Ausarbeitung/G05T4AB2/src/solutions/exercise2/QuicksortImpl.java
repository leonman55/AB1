package solutions.exercise2;

//import java.lang.reflect.Array;
import java.util.Comparator;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Quicksort;

/**
 * Implementation of the Quicksort algorithm
 * 
 * @author Isabelle, Leon, Pascal, Stefan
 */
public class QuicksortImpl<T> implements Quicksort<T>, ExerciseSubmission
{

	private final Comparator<T> comparator;

	/**
	 * Constructor for a new QuicksortImpl object
	 * @param comparator Comparator which is used to sort the elements
	 * @throws IllegalArgumentException if comparator is null
	 */
	public QuicksortImpl(Comparator<T> comparator) throws IllegalArgumentException
	{
		if (comparator == null)
		{
			throw new IllegalArgumentException("Parameter is not allowed to be null");
		}
		this.comparator = comparator;
	}

	/**
	 * Aufteilung des Arguments in zwei Gruppen. Die erste beinhaltet Werte die
	 * kleiner(/gleich) als das mittlere Element und die zweite Werte
	 * groesser(/gleich) dem mittleren Element.
	 * 
	 * @param arr eindimensionales Array der zu sortierenden Objekte
	 * @param left linker index
	 * @param right rechter index
	 * @return Index, bei dem sich die Zeiger links und rechts getroffen haben
	 */
	@Override
	public int partition(T[] arr, int left, int right)
	{
		if (arr == null || left > right || left < 0 || right < 0 || right > arr.length - 1 || left > arr.length - 1)
		{
			throw new IllegalArgumentException("Wrong Parameter(s)");
		}
		// Pivo Element in die Mitte setzen
		T pivo = arr[(left + right) / 2];
		// Index der von links hoch laeuft
		int i = left;
		// Index der von recht runter laeuft
		int j = right;
		// Ein Platzhalter fuer den tausch
		T platzhalter;

		do
		{
			// Solange von Links und Rechts zum Pivo Element laufen bis Werte
			// gefunden
			// wurden, die getauscht werden
			while (comparator.compare(arr[i], pivo) < 0)
			{
				i++;
			}
			while (comparator.compare(arr[j], pivo) > 0)
			{
				j--;
			}

			// Elemente tauschen, falls sich der linke und rechte Zeiger noch
			// nicht
			// getroffen haben
			if (i <= j)
			{
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
	 * Sortieren der Argumente
	 * @param arr eindimensionales Array der zu sortierenden Objekte
	 * @param left linker index
	 * @param right rechter index
	 */
	@Override
	public void quicksort(T[] arr, int left, int right)
	{
		if (arr == null)
		{
			throw new IllegalArgumentException("Parameter is not allowed to be null");
		}

		if (left < right)
		{
			int treffpunkt = partition(arr, left, right);
			if (left < treffpunkt)
			{
				quicksort(arr, left, treffpunkt - 1);
			}
			if (right > treffpunkt)
			{
				quicksort(arr, treffpunkt, right);
			}
		}

	}

	/**
	 * Method which returns the team number of the programming team
	 * @return specific team number
	 */
	@Override
	public String getTeamIdentifier()
	{
		return "G05T04";
	}

}
