package primefactor;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PrimeFactorsTest {

	@Test
	public void generate_empty_list_for_one() {
		assertEquals(getPrimeList(), PrimeFactors.generate(1));
	}

	@Test
	public void generate_two_for_two() {
		ArrayList<Integer> list = getPrimeList(2);

		assertEquals(list, PrimeFactors.generate(2));
	}

	@Test
	public void generate_two_prime_numbers_for_six(){
		ArrayList<Integer> list = getPrimeList(2, 3);

		assertEquals(list, PrimeFactors.generate(6));
	}

	@Test
	 public void generate_three_prime_numbers_for_eight(){
		ArrayList<Integer> list = getPrimeList(2, 2, 2);

		assertEquals(list, PrimeFactors.generate(8));
	}

	@Test
	public void generate_three_prime_numbers_for_larger_number(){
		ArrayList<Integer> list = getPrimeList(2, 3, 5, 7, 11);

		assertEquals(list, PrimeFactors.generate(2310));
	}

	private ArrayList<Integer> getPrimeList(int... primes) {
		ArrayList<Integer> primeList = new ArrayList<Integer>();

		for (int i = 0; i < primes.length; i ++ ) {
			primeList.add(primes[i]);
		}

		return primeList;

	}
}
