package src;

/**
 * Board that represents a Sudoku puzzle. Puzzle may be fully or partially solved.
 * Unsolved squares will have the value 0.
 */
public class SudokuBoard {
    private int[] board;

    public SudokuBoard(int[] board) {
        if (board == null || board.length != 81) {
            throw new IllegalArgumentException("Board must be of length 9");
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

    private boolean isValid() {
        return true;
    }

    /* Utility function to get the value at a certain (row, column) equivalent */
    private int get(int row, int col){
        return this.board[col + row * 9];
    }

    private boolean rowsAreValid() {
        boolean[] seen;
        for (int row = 0; row<9; row++) {
            seen = new boolean[]{false, false, false, false, false, false, false, false, false};
            for (int col = 0; col < 9; col++) {
                if (this.get(row, col) == 0) {

                }
            }
        }
        //fixme
        return true;
    }

    public int[] solve() {
        if (!this.isValid()) {
            throw new IllegalStateException("Initial board is invalid");
        }
        SudokuBoard holder = new SudokuBoard(this.board);
        return holder.board;
    }
}
