package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

public class PlayfieldElementComparator implements Comparator<PlayfieldElement>, ExerciseSubmission {

	private final ProducerType type;
	private final ConstructionCostCalculator calculator;

	/**
	 * Constructor with fields as arguments.
	 * 
	 * @param type {@link ProducerType}
	 * @param scenario {@link Scenario}
	 */
	public PlayfieldElementComparator(ProducerType type, Scenario scenario) {
		if (type == null){
			throw new NullPointerException("ProducerType is not allowed to be null");
		}
		else if(scenario == null){
			throw new NullPointerException("Scenario is not allowed to be null");
		}

		this.type = type;
		this.calculator = new ConstructionCostCalculatorImpl(scenario);
	}

	@Override
	public int compare(PlayfieldElement o1, PlayfieldElement o2) {
		if (o1 == null || o2 == null) {
			throw new NullPointerException("Arguments can't be null");
		}

		// Calculate cost for combination of elements and types
		double val1 = calculator.calculateCost(o1, type);
		double val2 = calculator.calculateCost(o2, type);

		// Reverse order
		return -Double.compare(val1, val2);
	}

	/**
	 * @see org.sopra.api.exercises.ExerciseSubmission#getTeamIdentifier()
	 */
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
