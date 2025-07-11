package com.puzzles.word;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puzzles.word.Turn.Cell;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Round class
 */
@DisplayName("Round Tests")
public class RoundTest {

    private Round round;
    private final String target = "BIOME";
    private final int turnMax = 2;

    @BeforeEach
    public void setUp() {
        round = new Round(target, turnMax);
    }

    @Test
    @DisplayName("Should initialize correctly")
    public void testInitialState() {
        assertEquals(1, round.getTurnNumber());
        assertEquals("BIOME", round.getTargetWord());
        assertEquals(GameState.ONGOING, round.getGameState());
        assertTrue(round.getAllTurns().isEmpty());
        assertEquals("You have %s guesses to find the %s-letter target word.".formatted(turnMax, target.length()),
                round.getRules());
    }

    @Test
    @DisplayName("Should correctly record a guess")
    public void testFirstTurnRecordedCorrectly() {

        // Send in a guess which does not win
        var maybeTurn = round.makeGuess("FORKS");

        // Round Info
        assertEquals(2, round.getTurnNumber());
        assertEquals(GameState.ONGOING, round.getGameState());
        assertEquals(1, round.getAllTurns().size());

        // Turn info
        assertTrue(maybeTurn.isRight());
        var turn = maybeTurn.getRight().get();
        assertEquals(GameState.ONGOING, turn.result());
        Cell[] expected = {
                new Cell('F', CellScore.MISS),
                new Cell('O', CellScore.MISPLACED_LETTER),
                new Cell('R', CellScore.MISS),
                new Cell('K', CellScore.MISS),
                new Cell('S', CellScore.MISS),
        };
        assertArrayEquals(expected, turn.cells());
    }

    // TODO: Error cases

    // TODO: Win Condition

    // TODO: Loss Condition

}