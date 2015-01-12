package bbc.gameoflife;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameOfLifeImplTest {
	private String allDeadState =
			"...\n" +
			"...\n" +
			"...";

	private String underPopulatedState =
			".*.\n" +
			"...\n" +
			"...";

	private String survivalState =
			".*.\n" +
			".**\n" +
			"...";

	@Test
	public void all_dead_cells_result_in_all_dead_cells(){
		GameOfLife gameOfLife = getGameOfLife(allDeadState);

		assertEquals(allDeadState, gameOfLife.tick());
	}

	@Test
	public void under_populated_cells_die(){
		GameOfLife gameOfLife = getGameOfLife(underPopulatedState);

		assertEquals(allDeadState, gameOfLife.tick());
	}

	@Test
	public void live_cells_with_two_neighbours_survive(){

		String endState =
				".**\n" +
				".**\n" +
				"...";

		GameOfLife gameOfLife = getGameOfLife(survivalState);

		assertEquals(endState, gameOfLife.tick());
	}

	@Test
	public void live_cells_with_too_many_neighbours_die(){
		String overcrowdingState =
				".*.\n" +
				"***\n" +
				".*.";
		String endState =
				"***\n" +
				"*.*\n" +
				"***";

		GameOfLife gameOfLife = getGameOfLife(overcrowdingState);

		assertEquals(endState, gameOfLife.tick());
	}

	@Test
	public void deal_cells_with_three_live_neighbours_alive(){
		String creationState =
				"...\n" +
				"***\n" +
				"...";
		String endState =
				".*.\n" +
				".*.\n" +
				".*.";

		GameOfLife gameOfLife = getGameOfLife(creationState);

		assertEquals(endState, gameOfLife.tick());
	}

	@Test
	public void test_4_by_8_grid(){
		String creationState =
				"........\n" +
				"....*...\n" +
				"...**...\n" +
				"........";
		String endState =
				"........\n" +
				"...**...\n" +
				"...**...\n" +
				"........";

		GameOfLife gameOfLife = getGameOfLife(creationState);

		assertEquals(endState, gameOfLife.tick());
	}

	private GameOfLife getGameOfLife(String state) {
		GameOfLife gameOfLife = new GameOfLifeImpl();
		gameOfLife.initialise(state);
		return gameOfLife;
	}
}
