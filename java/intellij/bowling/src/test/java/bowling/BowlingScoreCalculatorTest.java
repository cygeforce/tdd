package bowling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BowlingScoreCalculatorTest {

	private BowlingScoreCalculator bowlingScoreCalculator;

	@Before
	public void setUp(){
		bowlingScoreCalculator = new BowlingScoreCalculator();
	}
	@Test
	public void all_miss_returns_0_score() {
		List<int[]> frameResults = getAllMissResults();

		assertEquals(0, bowlingScoreCalculator.run(frameResults));
	}

	@Test
	public void knock_down_one_pin_each_ball_returns_20_score() {
		List<int[]> frameResults = getAllOneResults();

		assertEquals(20, bowlingScoreCalculator.run(frameResults));
	}

	@Test
	public void spare_in_first_frame_then_three_pins_then_all_miss_returns_correct_score() {
		List<int[]> frameResults = getSpareThenThreeThenAllMissResults();

		assertEquals(16, bowlingScoreCalculator.run(frameResults));
	}

	@Test
	public void strike_in_first_frame_then_three_pins_and_four_pins_then_all_miss_returns_correct_score() {
		List<int[]> frameResults = getStrikeThenThreeAndFourThenAllMissResults();

		assertEquals(24, bowlingScoreCalculator.run(frameResults));
	}

	@Test
	public void all_strikes_returns_correct_score() {
		List<int[]> frameResults = getPerfectGameResults();

		assertEquals(300, bowlingScoreCalculator.run(frameResults));
	}

	private List<int[]> getPerfectGameResults() {
		List<int[]> frameResults = new ArrayList<int[]>();
		for (int i = 0; i < 10; i ++) {
			int[] frameResult = new int[3];
			if (i != 9) {
				frameResult[0] = 10;
			} else {
				frameResult[0] = 10;
				frameResult[1] = 10;
				frameResult[2] = 10;
			}
			frameResults.add(frameResult);
		}
		return frameResults;
	}

	private List<int[]> getStrikeThenThreeAndFourThenAllMissResults() {
		List<int[]> frameResults = new ArrayList<int[]>();
		for (int i = 0; i < 10; i ++) {

			int[] frameResult = new int[2];
			if (i == 0) {
				frameResult[0] = 10;
			} else if (i == 1) {
				frameResult[0] = 3;
				frameResult[1] = 4;
			} else {
				frameResult[0] = 0;
				frameResult[1] = 0;
			}
			frameResults.add(frameResult);
		}
		return frameResults;
	}

	private List<int[]> getAllOneResults() {
		return getConsistantPerformanceResult(1);
	}

	private List<int[]> getAllMissResults() {
		return getConsistantPerformanceResult(0);
	}

	private List<int[]> getSpareThenThreeThenAllMissResults() {
		List<int[]> frameResults = new ArrayList<int[]>();
		for (int i = 0; i < 10; i ++) {

			int[] frameResult = new int[2];
			if (i == 0) {
				frameResult[0] = 6;
				frameResult[1] = 4;
			} else if (i == 1) {
				frameResult[0] = 3;
				frameResult[1] = 0;
			} else {
				frameResult[0] = 0;
				frameResult[1] = 0;
			}
			frameResults.add(frameResult);
		}
		return frameResults;
	}

	private List<int[]> getConsistantPerformanceResult(int performance) {
		List<int[]> frameResults = new ArrayList<int[]>();
		for (int i = 0; i < 10; i ++) {
			int[] frameResult = new int[2];
			frameResult[0] = performance;
			frameResult[1] = performance;
			frameResults.add(frameResult);
		}
		return frameResults;
	}
}
