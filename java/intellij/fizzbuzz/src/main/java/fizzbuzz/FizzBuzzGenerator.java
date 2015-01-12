package fizzbuzz;

public class FizzBuzzGenerator {

	private final String input;
	private static final int FIZZ_NUM = 3;
	private static final int BUZZ_NUM = 5;
	private static final int FIZZBUZZ_NUM = 15;

	public FizzBuzzGenerator(String input) {
		this.input = input;
	}

	public String generate() {
		String[] inputArray = parseInput();

		StringBuilder outputBuilder = buildFizzBuzz(inputArray);

		return outputBuilder.toString();
	}

	private StringBuilder buildFizzBuzz(String[] inputArray) {

		int inputLength = inputArray.length;

		StringBuilder outputBuilder = new StringBuilder();

		for (int i = 0; i < inputLength; i++) {
			int number = Integer.parseInt(inputArray[i]);

			if(number % FIZZBUZZ_NUM == 0) {
				outputBuilder.append("FizzBuzz");
			} else if (number % FIZZ_NUM == 0) {
				outputBuilder.append("Fizz");
			} else if (number % BUZZ_NUM == 0) {
				outputBuilder.append("Buzz");
			} else {
				outputBuilder.append(inputArray[i]);
			}

			if(i != inputLength - 1) {
				outputBuilder.append(",");
			}
		}
		return outputBuilder;
	}

	private String[] parseInput() {
		return input.split(",");
	}
}
