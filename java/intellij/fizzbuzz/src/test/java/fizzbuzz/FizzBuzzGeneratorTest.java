package fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzGeneratorTest {

	@Test
	public void one_generate_number_output(){
		assertEquals("1", generate("1"));
	}

	@Test
	public void two_numbers_generate_number_output(){
		assertEquals("1,2", generate("1,2"));
	}

	@Test
	public void three_numbers_generate_number_and_Fizz_output(){
		assertEquals("1,2,Fizz", generate("1,2,3"));
	}

	@Test
	public void five_numbers_generate_number_Fizz_and_Buzz_output(){
		assertEquals("1,2,Fizz,4,Buzz", generate("1,2,3,4,5"));
	}

	@Test
	public void six_numbers_generate_number_more_than_one_Fizz_output(){
		assertEquals("1,2,Fizz,4,Buzz,Fizz", generate("1,2,3,4,5,6"));
	}

	@Test
	public void ten_numbers_generate_number_more_than_one_Buzz_output(){
		assertEquals("1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz", generate("1,2,3,4,5,6,7,8,9,10"));
	}

	@Test
	public void fifteen_generate_FizzBuzz_output(){
		assertEquals("11,Fizz,13,14,FizzBuzz", generate("11,12,13,14,15"));
	}

	@Test
	public void fifteen_and_thirty_both_generate_FizzBuzz_output(){
		assertEquals("FizzBuzz,FizzBuzz", generate("15,30"));
	}

	private String generate(String input) {
		return new FizzBuzzGenerator(input).generate();
	}
}
