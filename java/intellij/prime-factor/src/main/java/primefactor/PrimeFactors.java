package primefactor;


import java.util.ArrayList;

public class PrimeFactors {
//	public static ArrayList<Integer> generate(int number) {
//
//		ArrayList<Integer> list = new ArrayList<Integer>();
//
//		int newNumber = number;
//		for (int i = 2; i <= number; i++) {
//			if (newNumber % i == 0) {
//				newNumber = newNumber/i;
//				list.add(i);
//				if (newNumber == 1) break;
//				i--;
//			}
//		}
//
//		return list;
//	}

	public static ArrayList<Integer> generate(int originalNumber) {
		ArrayList<Integer> primeList = new ArrayList<Integer>();

		int primeNumber = 2;
		while (originalNumber != 1) {
			if (originalNumber % primeNumber == 0) {
				originalNumber = originalNumber/primeNumber;
				primeList.add(primeNumber);
			} else {
				primeNumber++;
			}
		}

		return primeList;
	}
}
