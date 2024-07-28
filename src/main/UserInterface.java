package main;

import object.Obj_Key;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.FileWriter;

public class UserInterface {
    GamePanel gp;
    Font impact_40, impact_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCount = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    // Text field and button
    private JTextField nameField;
    private JButton submitButton;
    private JPanel uiPanel; // Assuming you have a JPanel for the UI

    public UserInterface(GamePanel gp) {
        this.gp = gp;

        impact_40 = new Font("Impact", Font.ITALIC, 40);
        impact_80B = new Font("Impact_B", Font.BOLD, 80);
        Obj_Key key = new Obj_Key();
        keyImage = key.image;

        // Initialize text field and button
        nameField = new JTextField(15);
        nameField.setBounds(gp.tileSize * 5, gp.tileSize * 3, gp.tileSize * 8, gp.tileSize);
        submitButton = new JButton("Save");
        submitButton.setBounds(gp.tileSize * 14, gp.tileSize * 3, gp.tileSize * 5, gp.tileSize);

        // Add components to UI panel
        uiPanel = new JPanel();
        uiPanel.add(nameField);
        uiPanel.add(submitButton);
        uiPanel.setVisible(true);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {

            g2.setFont(impact_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found your lost daughter!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "Total time spent:" + decimalFormat.format(playTime) + " seconds!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(impact_80B);
            g2.setColor(Color.green);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;


        } else {
            g2.setFont(impact_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("Keys = " + gp.player.hasKey, 100, 80);

            playTime += (double) 1 / 60;
            g2.drawString("Time spent:" + decimalFormat.format(playTime) + " seconds", gp.tileSize * 9, 80);

            //Mesaj

            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCount++;

                if (messageCount > 300) {
                    messageCount = 0;
                    messageOn = false;

                    // Add UI panel to drawing
                    g2.translate(0, gp.screenHeight - uiPanel.getHeight());
                    uiPanel.paint(g2);
                    g2.translate(0, -uiPanel.getHeight());
                }
            }
        }
    }

}
