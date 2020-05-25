package src;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SudokuBoardTest {

    private int[] fullValid = {
            1, 8, 4, 9, 6, 3, 7, 2, 5,
            5, 6, 2, 7, 4, 8, 3, 1, 9,
            3, 9, 7, 5, 1, 2, 8, 6, 4,
            2, 3, 9, 6, 5, 7, 1, 4, 8,
            7, 5, 6, 1, 8, 4, 2, 9, 3,
            4, 1, 8, 2, 3, 9, 6, 5, 7,
            9, 4, 1, 3, 7, 6, 5, 8, 2,
            6, 2, 3, 8, 9, 5, 4, 7, 1,
            8, 7, 5, 4, 2, 1, 9, 3, 6
    };

    private int[] partialValid = {
            0, 0, 4, 9, 0, 3, 7, 2, 5,
            5, 6, 2, 7, 0, 8, 3, 1, 9,
            3, 9, 7, 5, 0, 2, 8, 6, 4,
            2, 3, 9, 6, 0, 7, 1, 4, 8,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            4, 1, 8, 2, 0, 9, 6, 5, 7,
            9, 4, 1, 3, 0, 6, 5, 8, 2,
            6, 2, 3, 8, 0, 5, 4, 7, 1,
            8, 7, 5, 4, 0, 1, 9, 3, 0
    };

    @Test
    void validCreation() {
        new SudokuBoard(fullValid);
        new SudokuBoard(partialValid);
    }

    @Test
    void tooManyCreation() {
        int[] arr = {
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 1
        };
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SudokuBoard(arr));
    }

    @Test
    void outOfRangeCreation() {
        int[] arr = {
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 10, 8, 9
        };
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SudokuBoard(arr));
    }

    @Test
    void show() {
        String s = new SudokuBoard(fullValid).toString();
        Assertions.assertEquals(
                "1 8 4 9 6 3 7 2 5\n" +
                        "5 6 2 7 4 8 3 1 9\n" +
                        "3 9 7 5 1 2 8 6 4\n" +
                        "2 3 9 6 5 7 1 4 8\n" +
                        "7 5 6 1 8 4 2 9 3\n" +
                        "4 1 8 2 3 9 6 5 7\n" +
                        "9 4 1 3 7 6 5 8 2\n" +
                        "6 2 3 8 9 5 4 7 1\n" +
                        "8 7 5 4 2 1 9 3 6",
                s
        );
    }

    @Test
    void simpleValidation() {
        SudokuBoard s;

        int[] fullInvalid = {
                1, 8, 4, 9, 6, 3, 7, 2, 5,
                5, 6, 2, 7, 4, 8, 3, 1, 9,
                3, 9, 7, 5, 1, 2, 8, 6, 4,
                2, 3, 9, 6, 5, 7, 1, 4, 8,
                7, 5, 6, 1, 8, 4, 2, 9, 3,
                4, 1, 8, 2, 3, 9, 6, 5, 7,
                9, 4, 1, 3, 7, 6, 5, 8, 2,
                6, 2, 3, 8, 9, 5, 4, 7, 7, // note the duplicate 7s
                8, 7, 5, 4, 2, 1, 9, 3, 6
        };
        Assertions.assertFalse(new SudokuBoard(fullInvalid).isValid());

        int[] rowInvalid = {
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 7, 7, // note the duplicate 7s
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        Assertions.assertFalse(new SudokuBoard(rowInvalid).isValid());

        int[] colInvalid = {
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 3, 0, 0, 0, 0, 0, 0, 0, // note the duplicate 3s
                0, 3, 0, 0, 0, 0, 0, 0, 0, // note the duplicate 3s
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        Assertions.assertFalse(new SudokuBoard(colInvalid).isValid());

        int[] boxInvalid = {
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 1, 0, 0, // note the duplicate 1s
                0, 0, 0, 0, 0, 0, 0, 0, 1, // note the duplicate 1s
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        Assertions.assertFalse(new SudokuBoard(boxInvalid).isValid());

        Assertions.assertTrue(new SudokuBoard(fullValid).isValid());
    }

    @Test
    void trivialSolve() {
        SudokuBoard s = new SudokuBoard(fullValid);
        Assertions.assertEquals(true, s.solve());
        Assertions.assertArrayEquals(s.board, fullValid);
    }

    @Test
    void easySolve() {
        int[] easyValid = {
                1, 8, 4, 9, 6, 3, 7, 2, 5,
                5, 6, 2, 7, 4, 8, 3, 1, 9,
                3, 9, 7, 5, 1, 2, 8, 6, 4,
                2, 3, 9, 6, 5, 7, 1, 4, 8,
                7, 5, 6, 1, 0, 4, 2, 9, 3, // note the blank
                4, 1, 8, 2, 3, 9, 6, 5, 7,
                9, 4, 1, 3, 7, 6, 5, 8, 2,
                6, 2, 3, 8, 9, 5, 4, 7, 1,
                8, 7, 5, 4, 2, 1, 9, 3, 6
        };
        SudokuBoard s = new SudokuBoard(easyValid);
        Assertions.assertEquals(true, s.solve());
        Assertions.assertArrayEquals(s.board, fullValid);
    }

    @Test
    void mediumSolve() {
        int[] mediumValid = {
                1, 8, 4, 9, 0, 3, 7, 2, 5,
                5, 6, 2, 7, 0, 8, 3, 1, 9,
                3, 9, 7, 5, 0, 2, 8, 6, 4,
                0, 3, 9, 6, 0, 7, 1, 4, 8,
                0, 0, 6, 1, 0, 4, 2, 9, 3,
                4, 1, 8, 2, 0, 9, 6, 5, 7,
                9, 4, 1, 3, 0, 6, 5, 8, 2,
                6, 2, 3, 8, 0, 5, 4, 7, 1,
                8, 7, 5, 4, 0, 1, 9, 3, 6
        };
        SudokuBoard s = new SudokuBoard(mediumValid);
        Assertions.assertEquals(true, s.solve());
        Assertions.assertArrayEquals(s.board, fullValid);
    }

    @Test
    void emptySolve() {
        int[] empty = {
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        SudokuBoard s = new SudokuBoard(empty);
        Assertions.assertEquals(true, s.solve());
    }

    @Test
    void hardestSolve() {
        int[] empty = {
                8, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 3, 6, 0, 0, 0, 0, 0,
                0, 7, 0, 0, 9, 0, 2, 0, 0,
                0, 5, 0, 0, 0, 7, 0, 0, 0,
                0, 0, 0, 0, 4, 5, 7, 0, 0,
                0, 0, 0, 1, 0, 0, 0, 3, 0,
                0, 0, 1, 0, 0, 0, 0, 6, 8,
                0, 0, 8, 5, 0, 0, 0, 1, 0,
                0, 9, 0, 0, 0, 0, 4, 0, 0
        };
        SudokuBoard s = new SudokuBoard(empty);
        Assertions.assertEquals(true, s.solve());
        System.out.println(s);
    }










}