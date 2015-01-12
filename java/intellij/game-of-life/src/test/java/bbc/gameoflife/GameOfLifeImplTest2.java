package bbc.gameoflife;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeImplTest2 {

	String allDeadState =
			"...\n" +
			"...\n" +
			"...";
	GameOfLife game = new GameOfLifeImpl();;

	@Test
	public void initialStateOfZeroPopulationDoesNotChange() {
		String initialState = allDeadState;
		game.initialise(initialState);
		String newState = game.tick();

		assertEquals(initialState, newState);
	}

	@Test
	public void liveCellsWithLessThanTwoNeighboursDie() {
		String initialState =
				".*.\n" +
				"...\n" +
				"...";
		game.initialise(initialState);

		String newState = game.tick();

		assertEquals(allDeadState, newState);
	}

	@Test
	public void liveCellsWithMoreThanThreeNeighboursDie() {
		String initialState =
				".*.\n" +
				"***\n" +
				".*.";
		String endState =
				"***\n" +
				"*.*\n" +
				"***";
		game.initialise(initialState);

		String newState = game.tick();

		assertEquals(endState, newState);
	}

	@Test
	public void liveCellsWithTwoOrThreeNeighboursSurvive() {
		String initialState =
				".*.\n" +
				"***\n" +
				"...";
		String endState =
				"***\n" +
				"***\n" +
				".*.";
		game.initialise(initialState);

		String newState = game.tick();

		assertEquals(endState, newState);
	}

	@Test
	public void deadCellsWithThreeNeighboursAlive() {
		String initialState =
				"...\n" +
				"***\n" +
				"...";
		String endState =
				".*.\n" +
				".*.\n" +
				".*.";
		game.initialise(initialState);

		String newState = game.tick();

		assertEquals(endState, newState);
	}

	@Test
	public void moreComplexGOLExample() {
		String initialState =
				".....\n" +
				"*****\n" +
				".....";
		String endState =
				".***.\n" +
				".***.\n" +
				".***.";
		game.initialise(initialState);

		String newState = game.tick();

		assertEquals(endState, newState);
	}
//
//	@Test
//	public void countLiveNeighboursAllInBoundary() {
//		char[][] initialState = {
//				{'.','*','.'},
//				{'.','.','.'},
//				{'.','.','.'}
//		};
//		assertEquals(1, game.countLiveNeighbours(initialState, 1, 1));
//	}
//
//	@Test
//	public void countLiveNeighboursOutOfBoundary() {
//		char[][] initialState = {
//				{'.','*','.'},
//				{'.','.','.'},
//				{'.','.','.'}
//		};
//		assertEquals(1, game.countLiveNeighbours(initialState, 0, 0));
//	}
}