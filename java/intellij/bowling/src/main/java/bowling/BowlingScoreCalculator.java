package bowling;

import java.util.List;

public class BowlingScoreCalculator {
	public int run(List<int[]> frameResults) {

		int firstNineFrameResult = 0;

		firstNineFrameResult = getFirstNineFramesScore(frameResults);

		int totalResult = getLastFrameScore(frameResults, firstNineFrameResult);

		return totalResult;
	}

	private int getLastFrameScore(List<int[]> frameResults, int result) {
		int[] lastFrameResult = frameResults.get(9);
		for (int i = 0; i < lastFrameResult.length; i++) {
			if (i == 0 && isStrike(frameResults.get(8)) && isStrike(frameResults.get(7))) {
				result += lastFrameResult[i] * 3;
			} else if ((i == 1 && isStrike(frameResults.get(8)))) {
				result += lastFrameResult[i] * 2;
			} else
				result += lastFrameResult[i];
		}
		return result;
	}

	private int getFirstNineFramesScore(List<int[]> frameResults) {
		int result = 0;
		for (int i = 0; i < 9; i++) {
			int[] frameResult = frameResults.get(i);

			for (int j = 0; j < frameResult.length; j++) {
				if (i > 1 && isStrike(frameResults.get(i - 1)) && isStrike(frameResults.get(i - 2))) {
					result += frameResult[j] * 3;
				} else if (i > 0 && isStrike(frameResults.get(i - 1))) {
					result += frameResult[j] * 2;
				} else if (i > 0 && isSpare(frameResults.get(i-1)) && j == 0) {
					result += frameResult[j] * 2;
				} else {
					result += frameResult[j];
				}
			}
		}
		return result;
	}

	private boolean isStrike(int[] frameResult) {
		return (frameResult[0] == 10);
	}

	private boolean isSpare(int[] frameResult) {
		return (frameResult[0] + frameResult[1] == 10);
	}


}
