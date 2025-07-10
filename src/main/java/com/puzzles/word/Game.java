package com.puzzles.word;

import java.util.Scanner;

import io.jbock.util.Either;

public class Game {
    private final Round round;
    private final Scanner scanner;

    public Game(String target, int maxGuesses) {
        this.round = new Round(target, maxGuesses);
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        // TODO: Make this come from some config instead
        String target = "BIOME";
        int maxGuesses = 6;
        Game app = new Game(target, maxGuesses);
        app.run();
    }

    public void run() {
        clearScreen();
        System.out.println("Welcome to the Word Puzzle Game!");
        System.out.println(this.round.getRules());
        System.out.println("Type a word to make a guess, or '/q' to quit.");

        while (true) {
            System.out.print("\n %s > ".formatted(this.round.getTurnNumber()));
            String input = scanner.nextLine().trim();

            if (input.equals("/q")) {
                System.out.println("Goodbye!");
                break;
            }

            processCommand(input);

            GameState gameState = this.round.getGameState();

            if (gameState == GameState.WIN) {
                System.out.println("\nGREAT JOB!");
                break;
            }

            if (gameState == GameState.LOSS) {
                System.out.println(
                        "\nSORRY, THE WORD WAS '%s'\nBETTER LUCK NEXT TIME!".formatted(this.round.getTargetWord()));
                break;
            }

        }

        scanner.close();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void processCommand(String input) {
        Either<String, Turn> result = this.round.makeGuess(input);
        if (result.isLeft()) {
            System.out.println(result.getLeft().get());
        } else {
            System.out.println(result.getRight().get());
        }
    }

}