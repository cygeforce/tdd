package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
	public int add(String numbers) {
		if ((numbers == null) || (numbers == "")) {
			return 0;
		}

		String delimiterPart = numbers.split("\n")[0];
		String delimiter = getDelimiter(delimiterPart);
		if(delimiterPart.startsWith("//")) {
			numbers = numbers.replace(delimiterPart + "\n", "");
		}

		String splitRegex = "(" + delimiter + "|\\n" + ")";
		String[] numberToAdd = numbers.split(splitRegex);
		int total = calculateAddResult(numberToAdd);

		return total;
	}

	private int calculateAddResult(String[] numberToAdd) {
		int total = 0;
		for (int i = 0; i < numberToAdd.length; i++) {
			Integer value = Integer.valueOf(numberToAdd[i]);
			if (value >= 0) {
				total += value % 1000;
			} else {
				throw new RuntimeException("negatives not allowed " + value);
			}
		}
		return total;
	}

	private String getDelimiter(String delimiterPart) {
		String delimiter = ",";
		if(delimiterPart.startsWith("//")) {
			int delimiterStartIndex = delimiterPart.indexOf("[");
			if (delimiterStartIndex != -1) {
				delimiter = delimiterPart.substring(delimiterStartIndex + 1,  delimiterPart.indexOf("]"));
			} else {
				delimiter = delimiterPart.substring(2);
			}
		}

		StringBuilder escapedDelimiter = new StringBuilder();
		for (int i = 0; i < delimiter.length(); i++) {
			char delimiterChar = delimiter.charAt(i);
			if (".\\+*?[^]$(){}=!<>|:-".indexOf(delimiterChar) != -1) {
				escapedDelimiter.append("\\" + delimiterChar);
			} else {
				escapedDelimiter.append(delimiterChar);
			}
		}
		return escapedDelimiter.toString();
	}
}
