package src;

import java.util.Arrays;

/**
 * Board that represents a Sudoku puzzle. Puzzle may be fully or partially solved.
 * Unsolved squares have the value 0.
 */
public class SudokuBoard {
	private int[] board;
	private static final int BOARD_WIDTH = 9;
	private static final int BOX_WIDTH = 3;
	private int val, row, col, box, anchorRow, anchorCol;
	private boolean[] seenNumbers = new boolean[BOARD_WIDTH];

	public SudokuBoard(int[] board) {
		if (board == null || board.length != 81) {
			throw new IllegalArgumentException("Board must be of length 81");
		}
		for (int i : board) {
			if (i < 0 || i > 9) {
				throw new IllegalArgumentException(String.format("Invalid value in board %d", board[i]));
			}
		}
		this.board = board;
	}

	public int[] getBoard() {
		return board;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			if (i != 0 && i % BOARD_WIDTH == 0) {
				sb.append("\n");
			} else if (i != 0) {
				sb.append(" ");
			}
			sb.append(board[i]);
		}
		return sb.toString();
	}

	/**
	 * Validate rows, columns, and boxes using only primitive types. Returns true if a
	 * solution was found and false if a solution was not found.
	 * <p>
	 * Reuses the val, row, col, box, anchorRow, and anchorCol variables to avoid
	 * creating new variables.
	 * <p>
	 * The seenNumbers array acts like a set that checks to see if the value is already a
	 * member.
	 * <p>
	 * The box variable runs from (0,8) and represents different boxes in the puzzle
	 * starting at the top left box and then running reading direction (right and then
	 * down) through all of the boxes. The "anchors" represent the rop left square in
	 * each box.
	 */
	public boolean isValid() {
		// Row validation
		for (row = 0; row < BOARD_WIDTH; row++) {
			Arrays.fill(seenNumbers, false);
			for (col = 0; col < BOARD_WIDTH; col++) {
				val = get(row, col);
				if (val != 0) {
					if (seenNumbers[val - 1]) return false;
					else seenNumbers[val - 1] = true;
				}
			}
		}

		// Column validation
		for (col = 0; col < BOARD_WIDTH; col++) {
			Arrays.fill(seenNumbers, false);
			for (row = 0; row < BOARD_WIDTH; row++) {
				val = get(row, col);
				if (val != 0) {
					if (seenNumbers[val - 1]) return false;
					else seenNumbers[val - 1] = true;
				}
			}
		}

		// Box validation
		for (box = 0; box < BOARD_WIDTH; box++) {
			Arrays.fill(seenNumbers, false);
			anchorCol = (box % BOX_WIDTH) * BOX_WIDTH;
			anchorRow = (box / BOX_WIDTH) * BOX_WIDTH;
			for (row = 0; row < BOX_WIDTH; row++) {
				for (col = 0; col < BOX_WIDTH; col++) {
					val = this.get(anchorRow + row, anchorCol + col);
					if (val != 0) {
						if (seenNumbers[val - 1]) return false;
						else seenNumbers[val - 1] = true;
					}
				}
			}
		}

		// All validations passed
		return true;
	}

	/* Utility function to get the value at a certain (row, column) equivalent */
	private int get(int row, int col) {
		return board[col + row * BOARD_WIDTH];
	}

	/* Utility function to get the value at a certain (row, column) equivalent */
	private void set(int row, int col, int val) {
		board[col + row * BOARD_WIDTH] = val;
	}

	/**
	 * Recursive DFS algorithm that stops when any solution is found.
	 * After solving, use getBoard() to see the solution.
	 *
	 * @return true if a solution was found, else false
	 * <p>
	 * Solves Arto Inkala's "world's hardest Sudoku" in about 200 ms, finds a solution
	 * for a blank board in about 10 ms, and solves random boards in <10 ms.
	 */
	public boolean solve() {
		if (!isValid()) return false;
		for (int row = 0; row < BOARD_WIDTH; row++) {
			for (int col = 0; col < BOARD_WIDTH; col++) {
				// Search for an empty cell
				if (get(row, col) == 0) {
					// Try all values
					for (int val = 1; val <= BOARD_WIDTH; val++) {
						// Update the current blank for testing
						set(row, col, val);
						if (solve()) return true;
						// Undo the updated val
						set(row, col, 0);
					}
					// None of the values worked
					return false;
				}
			}
		}
		return true;
	}
}
