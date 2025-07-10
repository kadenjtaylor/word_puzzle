package com.puzzles.word;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
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
    @DisplayName("Should start on turn #1")
    public void testInitialState() {
        assertEquals(1, round.getTurnNumber());
        assertEquals("BIOME", round.getTargetWord());
        assertEquals(GameState.ONGOING, round.getGameState());
        assertThat(round.getAllTurns().isEmpty());
        assertEquals("You have %s guesses to find the %s-letter target word.".formatted(turnMax, target.length()),
                round.getRules());
    }

    // TODO: Error cases

    // TODO: Win Condition

    // TODO: Loss Condition

}