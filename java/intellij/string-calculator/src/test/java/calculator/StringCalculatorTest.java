package calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class StringCalculatorTest {

	private StringCalculator stringCalculator = new StringCalculator();

	@Test
	public void add_empty_input_returns_0() {
		assertEquals(0, stringCalculator.add(""));
	}

	@Test
	public void add_null_input_returns_0() {
		assertEquals(0, stringCalculator.add(null));
	}

	@Test
	public void add_single_number_input_returns_the_number() {
		assertEquals(1, stringCalculator.add("1"));
	}

	@Test
	public void add_single_number_input_returns_the_number2() {
		assertEquals(2, stringCalculator.add("2"));
	}

	@Test
	public void add_two_numbers_input_returns_the_sum() {
		assertEquals(3, stringCalculator.add("1,2"));
	}

	@Test
	public void add_three_numbers_input_returns_the_sum() {
		assertEquals(7, stringCalculator.add("1,2,4"));
	}

	@Test
	public void add_three_numbers_with_new_line_input_returns_the_sum() {
		assertEquals(7, stringCalculator.add("1,2\n4"));
	}

	@Test
	public void add_with_default_delimiter_returns_the_sum() {
		assertEquals(3, stringCalculator.add("//;\n1;2"));
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	@Test
	public void add_with_negative_numbers_throws_exception() {

		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("negatives not allowed");
		stringCalculator.add("-1,2");
	}

	@Test
	public void add_with_numbers_bigger_than_1000_is_ignored() {
		assertEquals(3, stringCalculator.add("1001,20002"));
	}

	@Test
	public void add_with_any_length_delimiter_returns_the_sum() {
		assertEquals(6, stringCalculator.add("//[***]\n1***2***3"));
	}

//	@Test
//	public void add_with_multiple_delimiters_returns_the_sum() {
//		assertEquals(9, stringCalculator.add("//[?][%]\n2?3%4"));
//	}
}