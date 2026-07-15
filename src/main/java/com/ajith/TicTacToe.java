package com.ajith;

import java.util.Scanner;

public class TicTacToe {

        public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter Player 1 Name (X): ");
    String player1 = sc.nextLine();

    System.out.print("Enter Player 2 Name (O): ");
    String player2 = sc.nextLine();

    int score1 = 0;
    int score2 = 0;

    char choice;

    do {

        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        char player = 'X';
        boolean winner = false;

        for (int turn = 0; turn < 9; turn++) {

            printBoard(board);

            if (player == 'X')
                System.out.println(player1 + "'s Turn (X)");
            else
                System.out.println(player2 + "'s Turn (O)");

            System.out.print("Enter row (0-2): ");
            int row = sc.nextInt();

            System.out.print("Enter column (0-2): ");
            int col = sc.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid Position!");
                turn--;
                continue;
            }

            if (board[row][col] != ' ') {
                System.out.println("Cell Already Occupied!");
                turn--;
                continue;
            }

            board[row][col] = player;

            if (checkWinner(board, player)) {

                printBoard(board);

                if (player == 'X') {
                    System.out.println(player1 + " Wins!");
                    score1++;
                } else {
                    System.out.println(player2 + " Wins!");
                    score2++;
                }

                winner = true;
                break;
            }

            player = (player == 'X') ? 'O' : 'X';
        }

        if (!winner) {
            printBoard(board);
            System.out.println("Game Draw!");
        }

        System.out.println("\n===== SCOREBOARD =====");
        System.out.println(player1 + " : " + score1);
        System.out.println(player2 + " : " + score2);

        System.out.print("\nPlay Again? (Y/N): ");
        choice = sc.next().charAt(0);

    } while (choice == 'Y' || choice == 'y');

    System.out.println("Thank You For Playing!");
    sc.close();
}

    static void printBoard(char[][] board) {

    System.out.println();
    System.out.println("    0   1   2");

    for (int i = 0; i < 3; i++) {

        System.out.print(i + " ");

        for (int j = 0; j < 3; j++) {

            System.out.print(" " + board[i][j] + " ");

            if (j < 2)
                System.out.print("|");
        }

        System.out.println();

        if (i < 2)
            System.out.println("   ---+---+---");
    }

    System.out.println();
}

    static boolean checkWinner(char[][] b, char p) {

        // Rows
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == p && b[i][1] == p && b[i][2] == p)
                return true;
        }

        // Columns
        for (int i = 0; i < 3; i++) {
            if (b[0][i] == p && b[1][i] == p && b[2][i] == p)
                return true;
        }

        // Main diagonal
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p)
            return true;

        // Other diagonal
        if (b[0][2] == p && b[1][1] == p && b[2][0] == p)
            return true;

        return false;
    }
}