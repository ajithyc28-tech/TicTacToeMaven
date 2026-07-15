package com.ajith;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {


    JButton[] buttons = new JButton[9];

    JLabel statusLabel;
    JLabel scoreLabel;

    JButton restartButton;

    int xScore = 0;
    int oScore = 0;

    char currentPlayer = 'X';
    String playerX;
    String playerO;

    public TicTacToeGUI() {
        playerX = JOptionPane.showInputDialog("Enter Player X Name:");
        playerO = JOptionPane.showInputDialog("Enter Player O Name:");
        setTitle("Tic Tac Toe");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));

        scoreLabel = new JLabel("X Score: 0     O Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));

        statusLabel = new JLabel("Player X Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(scoreLabel);
        topPanel.add(statusLabel);

        add(topPanel, BorderLayout.NORTH);

        // Board Panel
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].addActionListener(this);
            boardPanel.add(buttons[i]);
        }

        add(boardPanel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();

        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 18));

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        bottomPanel.add(restartButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals(""))
            return;

        clicked.setText(String.valueOf(currentPlayer));

        if (checkWinner()) {

            if (currentPlayer == 'X')
                xScore++;
            else
                oScore++;

            scoreLabel.setText(
                    "X Score: " + xScore +
                    "     O Score: " + oScore);

            JOptionPane.showMessageDialog(
                    this,
                    "Player " + currentPlayer + " Wins!"
            );

            resetBoard();
            return;
        }

        if (isDraw()) {

           String winnerName;

if(currentPlayer == 'X')
    winnerName = playerX;
else
    winnerName = playerO;

JOptionPane.showMessageDialog(
        this,
        winnerName + " Wins!"
);

            resetBoard();
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

        statusLabel = new JLabel(playerX + "'s Turn", SwingConstants.CENTER);
    }
    boolean checkWinner() {

    int[][] winPatterns = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    for(int[] p : winPatterns){

        String a = buttons[p[0]].getText();
        String b = buttons[p[1]].getText();
        String c = buttons[p[2]].getText();

        if(!a.equals("") && a.equals(b) && b.equals(c)){
            highlightWinner(p[0], p[1], p[2]);
            return true;
        }
    }

    return false;
}

    boolean isDraw() {

        for (JButton b : buttons) {
            if (b.getText().equals(""))
                return false;
        }

        return true;
    }

    void resetBoard() {

        for (JButton b : buttons) {
        b.setText("");
        b.setBackground(null);
}

        currentPlayer = 'X';
        if(currentPlayer == 'X')
             statusLabel.setText(playerX + "'s Turn");
        else
            statusLabel.setText(playerO + "'s Turn");
    }
    void highlightWinner(int a, int b, int c) {
    buttons[a].setBackground(Color.GREEN);
    buttons[b].setBackground(Color.GREEN);
    buttons[c].setBackground(Color.GREEN);
}

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
