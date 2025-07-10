package com.puzzles.word;

enum CellScore {
    MISS,
    MISPLACED_LETTER,
    LOCKED_LETTER
}

enum GameState {
    ONGOING,
    LOSS,
    WIN
}

public record Turn(String guess, Cell[] cells, GameState result) {
    public record Cell(char letter, CellScore score) {
    }

    @Override
    public String toString() {
        return Utils.render(cells);
    }

}