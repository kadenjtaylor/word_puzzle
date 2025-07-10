package com.puzzles.word;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Utils class
 */
@DisplayName("Utils Tests")
public class UtilsTest {

    @Test
    @DisplayName("Should correctly mark all cells LOCKED_LETTER")
    public void testAllGreen() {
        String target = "FORKS";
        String guess = target;

        var cells = Utils.computeCells(guess, target);
        var isWin = Utils.allCorrect(cells);

        assertEquals(cells[0].score(), CellScore.LOCKED_LETTER);
        assertEquals(cells[1].score(), CellScore.LOCKED_LETTER);
        assertEquals(cells[2].score(), CellScore.LOCKED_LETTER);
        assertEquals(cells[3].score(), CellScore.LOCKED_LETTER);
        assertEquals(cells[4].score(), CellScore.LOCKED_LETTER);
        assertTrue(isWin);
    }

    @Test
    @DisplayName("Should correctly mark all cells MISS")
    public void testAllGray() {
        String target = "FORKS";
        String guess = "AAAAA";

        var cells = Utils.computeCells(guess, target);
        var isWin = Utils.allCorrect(cells);

        System.out.println(Utils.render(cells));

        assertEquals(cells[0].score(), CellScore.MISS);
        assertEquals(cells[1].score(), CellScore.MISS);
        assertEquals(cells[2].score(), CellScore.MISS);
        assertEquals(cells[3].score(), CellScore.MISS);
        assertEquals(cells[4].score(), CellScore.MISS);
        assertFalse(isWin);
    }

    @Test
    @DisplayName("Should correctly mark all cells MISPLACED_LETTER")
    public void testAllYellow() {
        String target = "FORKS";
        String guess = "SFORK";

        var cells = Utils.computeCells(guess, target);
        var isWin = Utils.allCorrect(cells);

        System.out.println(Utils.render(cells));

        assertEquals(cells[0].score(), CellScore.MISPLACED_LETTER);
        assertEquals(cells[1].score(), CellScore.MISPLACED_LETTER);
        assertEquals(cells[2].score(), CellScore.MISPLACED_LETTER);
        assertEquals(cells[3].score(), CellScore.MISPLACED_LETTER);
        assertEquals(cells[4].score(), CellScore.MISPLACED_LETTER);
        assertFalse(isWin);
    }

}