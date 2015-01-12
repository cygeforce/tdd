package bbc.gameoflife;

interface GameOfLife {

	/**
	 * Specification of input/output:
	 *
	 * The initial state is defined as a String with the following character definitions
	 * 		.     dead cell
	 * 		*     live cell
	 *      newline  next line of grid
	 *
	 * e.g.
	 *
	 *   "..*.*..
	 *    .*....*
	 *    ...*..."
	 */
	public void initialise(String initialState);

	/**
	 * @see initialise(String)
	 */
	public String tick();

}