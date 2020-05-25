package src;

/**
 * Board that represents a Sudoku puzzle. Puzzle may be fully or partially solved.
 * Unsolved squares have the value 0.
 */
public class SudokuBoard {
	public int[] board;
	int[] res;

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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			if (i != 0 && i % 9 == 0) {
				sb.append("\n");
			} else if (i != 0) {
				sb.append(" ");
			}
			sb.append(board[i]);
		}
		return sb.toString();
	}

	/**
	 * Check for errors in rows, columns, and boxes.
	 * Loops from (0,8) on inner and outer for loops.
	 * <p>
	 * "seenIn" arrays act like sets that checks to see if the value
	 * is already a member. They are reset on each outer loop.
	 * <p>
	 * The box variable runs from (0,8) and represents different boxes
	 * in the puzzle starting at the top right box and then running
	 * reading direction (right and then down) through all of the boxes.
	 */
	public boolean isValid() {
		// Row and column validation
		boolean[] seenInRows, seenInCols;
		int rowVal, colVal;

		for (int i = 0; i < 9; i++) {
			seenInRows = new boolean[]{false, false, false, false, false, false, false, false, false};
			seenInCols = new boolean[]{false, false, false, false, false, false, false, false, false};
			for (int j = 0; j < 9; j++) {
				// Columns and row validation
				rowVal = this.get(i, j);
				colVal = this.get(j, i);
				if (rowVal != 0) {
					if (seenInRows[rowVal - 1]) return false;
					else seenInRows[rowVal - 1] = true;
				}
				if (colVal != 0) {
					if (seenInCols[colVal - 1]) return false;
					else seenInCols[colVal - 1] = true;
				}
			}
		}

		// Box validation
		boolean[] seenInBox;
		int anchorRow, anchorCol, boxVal;
		for (int box = 0; box < 9; box++) {
			seenInBox = new boolean[]{false, false, false, false, false, false, false, false, false};
			anchorCol = (box % 3) * 3;
			anchorRow = (box / 3) * 3;
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					boxVal = this.get(anchorRow + row, anchorCol + col);
					if (boxVal != 0) {
						if (seenInBox[boxVal - 1]) return false;
						else seenInBox[boxVal - 1] = true;
					}
				}
			}
		}

		// The position is valid
		return true;
	}

	/* Utility function to get the value at a certain (row, column) equivalent */
	private int get(int row, int col) {
		return this.board[col + row * 9];
	}

	/* Utility function to get the value at a certain (row, column) equivalent */
	private void set(int row, int col, int val) {
		this.board[col + row * 9] = val;
	}

	public boolean solve() {
		if (!isValid()) return false;
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				// Search for an empty cell
				if (get(row, col) == 0) {
					// Try all values
					for (int val = 1; val <= 9; val++) {
						set(row, col, val);
						if (solve()) return true;
						set(row, col, 0);
					}
					return false; // we return false
				}
			}
		}
		return true;
	}
}
