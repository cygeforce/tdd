package bbc.gameoflife;

public class GameOfLifeImpl implements GameOfLife {
	private String state;

	@Override
	public void initialise(String initialState) {
		state = initialState;
	}

	@Override
	public String tick() {
		char[][] stateMatrix = getStateMatrix();
		char[][] newStateMatrix = getStateMatrix();

		for (int i = 0; i < stateMatrix.length; i++) {
			for (int j = 0; j < stateMatrix[i].length; j++) {
				boolean live = isLive(stateMatrix[i][j]);
				int liveNeighbours = countLiveNeighbours(stateMatrix, i, j);
				if (live && (liveNeighbours < 2)) {
					newStateMatrix[i][j] = '.';
				} else if (live &&
						((liveNeighbours == 2) || (liveNeighbours == 3))) {
					newStateMatrix[i][j] = '*';
				} else if (live && (liveNeighbours > 3)) {
					newStateMatrix[i][j] = '.';
				} else if (!live && (liveNeighbours == 3)) {
					newStateMatrix[i][j] = '*';
				}
			}
		}

		return matrixToString(newStateMatrix);
	}

	private boolean isLive(char state) {
		return (state == '*');
	}

	private String matrixToString(char[][] newStateMatrix) {

		int rows = newStateMatrix.length;
		StringBuilder state = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < newStateMatrix[i].length; j++) {
				state.append(newStateMatrix[i][j]);
			}

			if (i != rows - 1) {
				state.append("\n");
			}
		}
		return state.toString();
	}

	private int countLiveNeighbours(char[][] stateMatrix, int i, int j) {
		int previousRow = i - 1;
		int previousColumn = j - 1;

		int currentRow = i;
		int currentColumn = j;

		int nextRow = i + 1;
		int nextColumn = j + 1;

		int liveNeighboursCount = 0;
		liveNeighboursCount += (isInBoundaryAndLive(stateMatrix, previousRow, previousColumn)) ? 1 : 0;
		liveNeighboursCount += (isInBoundaryAndLive(stateMatrix, previousRow, currentColumn)) ? 1 : 0;
		liveNeighboursCount += (isInBoundaryAndLive(stateMatrix, previousRow, nextColumn)) ? 1 : 0;
		liveNeighboursCount += (isInBoundaryAndLive(stateMatrix, currentRow, previousColumn)) ? 1 : 0;
		liveNeighboursCount += (isInBoundaryAndLive(stateMatrix, currentRow, nextColumn)) ? 1 : 0;
		liveNeighboursCount += (isInBoundaryAndLive(stateMatrix, nextRow, previousColumn)) ? 1 : 0;
		liveNeighboursCount += (isInBoundaryAndLive(stateMatrix, nextRow, currentColumn)) ? 1 : 0;
		liveNeighboursCount += (isInBoundaryAndLive(stateMatrix, nextRow, nextColumn)) ? 1 : 0;

		return liveNeighboursCount;
	}

	private boolean isInBoundaryAndLive(char[][] stateMatrix, int previousRow, int previousColumn) {
		return isInBoundary(stateMatrix, previousRow, previousColumn) && stateMatrix[previousRow][previousColumn] == '*';
	}

	private boolean isInBoundary(char[][] stateMatrix, int row, int column) {
		return (row >= 0) && (column >= 0) && (row < stateMatrix.length) && (column < stateMatrix[row].length);
	}

	private char[][] getStateMatrix() {

		String[] stateArray = state.split("\n");
		int rows = stateArray.length;

		char[][] stateMatrix = new char[rows][];

		for (int i = 0; i < rows; i++) {
			int columns = stateArray[i].length();
			stateMatrix[i] = new char[columns];
			for (int j = 0; j < columns; j++) {
				stateMatrix[i][j] = stateArray[i].charAt(j);
			}
		}

		return stateMatrix;
	}


//	@Override
//	public void initialise(String initialState) {
//		state = initialState;
//
//	}
//
//	@Override
//	public String tick() {
//		char[][] matrix = getMatrix(state);
//		char[][] newMatrix = new char[matrix.length][];
//
//
//		for (int i = 0; i < matrix.length; i++) {
//			char[] row = matrix[i];
//			newMatrix[i] = new char[row.length];
//			for (int j = 0; j < row.length; j++) {
//				int liveNeighbours = countLiveNeighbours(matrix, i, j);
//				if (liveNeighbours < 2 && matrix[i][j] == '*') {
//					newMatrix[i][j] = '.';
//				} else if(liveNeighbours > 3 && matrix[i][j] == '*') {
//					newMatrix[i][j] = '.';
//				} else if(liveNeighbours == 3 && matrix[i][j] == '.') {
//					newMatrix[i][j] = '*';
//				} else if((liveNeighbours == 3 || liveNeighbours == 2) && matrix[i][j] == '*') {
//					newMatrix[i][j] = '*';
//				} else {
//					newMatrix[i][j] = '.';
//				}
//			}
//		}
//
//		return matrixToString(newMatrix);
//	}
//
//	public int countLiveNeighbours(char[][] matrix, int rowNum, int columnNum) {
//		int previousRow = rowNum - 1;
//		int prevousColumn = columnNum - 1;
//		int nextColumn = columnNum + 1;
//		int nextRow = rowNum + 1;
//
//		return ((isInBoundary(previousRow, prevousColumn, matrix) && matrix[previousRow][prevousColumn] == '*') ? 1:0)
//				+ ((isInBoundary(previousRow, columnNum, matrix) && matrix[previousRow][columnNum] == '*') ? 1:0)
//				+ ((isInBoundary(previousRow, nextColumn, matrix) && matrix[previousRow][nextColumn] == '*') ? 1:0)
//				+ ((isInBoundary(rowNum, prevousColumn, matrix) && matrix[rowNum][prevousColumn] == '*') ? 1:0)
//				+ ((isInBoundary(rowNum, nextColumn, matrix) && matrix[rowNum][nextColumn] == '*') ? 1:0)
//				+ ((isInBoundary(nextRow, prevousColumn, matrix) && matrix[nextRow][prevousColumn] == '*') ? 1:0)
//				+ ((isInBoundary(nextRow, columnNum, matrix) && matrix[nextRow][columnNum] == '*') ? 1:0)
//				+ ((isInBoundary(nextRow, nextColumn, matrix) && matrix[nextRow][nextColumn] == '*') ? 1:0);
//	}
//
//	private boolean isInBoundary(int row, int column, char[][] matrix) {
//		int totalRows = matrix.length;
//		int totalColumns = matrix[0].length;
//		if (row  >= 0 && row < totalRows && column >= 0 && column < totalColumns)
//			return true;
//		else
//			return false;
//	}
//
//
//	private String matrixToString(char[][] matrix) {
//		StringBuilder endState = new StringBuilder();
//		for (int i = 0; i < matrix.length; i++) {
//			char[] row = matrix[i];
//			for (int j = 0; j < row.length; j++) {
//				endState.append(matrix[i][j]);
//			}
//			if (i != matrix.length - 1) {
//				endState.append("\n");
//			}
//		}
//		return endState.toString();
//	}
//
//	private char[][] getMatrix(String initialState) {
//		String[] stateArray = initialState.split("\n");
//		int rows = stateArray.length;
//		char[][] stateMatrix = new char[rows][];
//		for (int i = 0; i < rows; i++) {
//			String row = stateArray[i];
//			int columnNumber = row.length();
//			char[] rowArray = new char[columnNumber];
//
//			for (int j = 0; j < rowArray.length; j++) {
//				rowArray[j] = row.charAt(j);
//			}
//
//			stateMatrix[i] = rowArray;
//		}
//		return stateMatrix;
//	}

}
