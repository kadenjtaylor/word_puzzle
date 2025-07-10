package com.puzzles.word;

import java.util.ArrayList;
import java.util.List;

import io.jbock.util.Either;

/**
 * PuzzleService - Service class for managing a round of the game
 */
public class Round {

    private final String targetWord;
    private final int turnMax;
    private List<Turn> turns;
    // TODO: hard mode

    public Round(String targetWord, int turnMax) {
        this.targetWord = targetWord.toUpperCase();
        this.turnMax = turnMax;
        this.turns = new ArrayList<Turn>();
    }

    /**
     * Make new guess
     * 
     * @param guess The next guessed word
     * @return The created turn, or an error message if the guess is invalid
     */
    public Either<String, Turn> makeGuess(String guess) {
        var guessedWord = guess.toUpperCase();

        // Check to make sure they only guessed letters
        for (char c : guessedWord.toCharArray()) {
            if (!Character.isLetter(c)) {
                return Either.left("Guess can only contain letters");
            }
        }

        // Only words of the correct length are acceptable
        if (guessedWord.length() != targetWord.length()) {
            return Either.left("Guess can only be %s letters long".formatted(targetWord.length()));
        }

        var cells = Utils.computeCells(guessedWord, this.targetWord);

        // Assume game is ongoing unless...
        var result = GameState.ONGOING;

        if (Utils.allCorrect(cells)) {
            // ...we got every letter right
            result = GameState.WIN;
        } else if (this.turns.size() + 1 >= this.turnMax) {
            // ... or we are out of turns
            result = GameState.LOSS;
        }

        // Finally, save the turn to our memory and return it
        var turn = new Turn(guessedWord, cells, result);
        this.turns.add(turn);
        return Either.right(turn);
    }

    /**
     * Get the current turn counter
     * 
     * @return Turn number of the current round
     */
    public int getTurnNumber() {
        return this.turns.size() + 1;
    }

    /**
     * Show the rules of the game given the starting parameters
     * 
     * @return A string representation of the rules of this round.
     */
    public String getRules() {
        return "You have %s guesses to find the %s-letter target word.".formatted(this.turnMax,
                this.targetWord.length());
    }

    /**
     * Get all turns
     * 
     * @return List of all turns in chronological order
     */
    public List<Turn> getAllTurns() {
        return new ArrayList<>(this.turns);
    }

    /**
     * Get the current state of the active round
     * 
     * @return GameState which describes whether the round is ongoing or not, and if
     *         not, how the round ended
     */
    public GameState getGameState() {
        if (this.turns.size() == 0) {
            return GameState.ONGOING;
        } else {
            return this.turns.getLast().result();
        }
    }

    /**
     * Get the target word the user must try to guess
     * 
     * @return String - the target word for this round
     */
    public String getTargetWord() {
        return this.targetWord;
    }
}