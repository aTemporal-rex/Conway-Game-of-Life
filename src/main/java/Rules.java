import javax.swing.*;
import java.awt.*;

public class Rules extends JFrame {
    public Rules(int rulesX, int rulesY) {
        JButton okButton;
        JTextArea rulesText;

        // Instantiating the rules text area with the game rules
        rulesText = new JTextArea("Conway's Game of Life consists of a two-dimensional orthogonal grid of square " +
                "cells. Each cell is in one of two possible states, live or dead.\nCells interact with their " +
                "neighbors in the following ways:\n" +
                "1. Any live cell with fewer than two live neighbors dies.\n" +
                "2. Any live cell with two or three live neighbors lives.\n" +
                "3. Any live cell with more than three neighbors dies.\n" +
                "4. Any dead cell with exactly three live neighbors becomes a live cell.\n\n" +
                "The SETTINGS button can be used to change a number of different settings.\n" +
                "The RANDOMIZE button can be used to randomize the initial layout of the cells.\n" +
                "Press NEXT to advance the game.");

        // Formatting the font style, size, and color
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        rulesText.setFont(font);
        rulesText.setBackground(Color.DARK_GRAY);
        rulesText.setForeground(Color.LIGHT_GRAY);

        // Setting bounds of rules text area
        rulesText.setBounds(0, 0, rulesX, rulesY);

        // Instantiating OK button and setting it's size and position
        okButton = new JButton("OK");
        okButton.setBounds(1100, 400, 60, 40);

        this.add(okButton);
        this.add(rulesText);

        // Lambda function that makes the OK button close the window
        okButton.addActionListener(e -> dispose());
    }
}
