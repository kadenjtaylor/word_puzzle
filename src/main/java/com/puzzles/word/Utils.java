package com.puzzles.word;

import java.util.TreeSet;

import com.puzzles.word.Turn.Cell;

class Utils {
    static Cell[] computeCells(String guess, String targetWord) {
        // Create cell array to score word
        Cell[] yesNo = new Cell[guess.length()];

        // Some easy access arrays for iterating through the words
        var g = guess.toCharArray();
        var t = targetWord.toCharArray();

        // Used to store letters that aren't green, but may be yellow
        var possibles = new TreeSet<Character>();

        // Green Pass
        for (int i = 0; i < yesNo.length; i++) {
            if (g[i] == t[i]) {
                yesNo[i] = new Cell(g[i], CellScore.LOCKED_LETTER);
            } else {
                yesNo[i] = new Cell(g[i], CellScore.MISS);
                possibles.add(t[i]);
            }
        }

        // Yellow pass
        Cell[] cells = yesNo.clone();
        for (int i = 0; i < cells.length; i++) {
            if (yesNo[i].score() == CellScore.MISS && possibles.contains(yesNo[i].letter())) {
                cells[i] = new Cell(g[i], CellScore.MISPLACED_LETTER);
                possibles.remove(yesNo[i].letter());
            }
        }

        return cells;
    }

    static boolean allCorrect(Cell[] cells) {
        for (Cell c : cells) {
            if (c.score() != CellScore.LOCKED_LETTER) {
                return false;
            }
        }
        return true;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    static String render(Cell[] cells) {
        var ret = new StringBuilder();
        for (Cell c : cells) {
            String color = switch (c.score()) {
                case MISPLACED_LETTER -> ANSI_YELLOW_BACKGROUND;
                case LOCKED_LETTER -> ANSI_GREEN_BACKGROUND;
                case MISS -> ANSI_BLACK_BACKGROUND;
                default -> throw new RuntimeException("Invalid score");
            };
            ret.append("%s%s%s".formatted(color, c.letter(), ANSI_RESET));
        }
        return ret.toString();
    }
}
