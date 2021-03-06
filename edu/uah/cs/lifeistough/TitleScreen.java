/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * This JPanel is the first window that the player sees.
 * It allows the player to select a difficulty, enter their name,
 *     view instructions, start the game, or close the game.
 * 
 * @author Luke Farris
 */
public class TitleScreen extends JPanel{
    
    private final GameManager manager;
    
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel titleLabel;
    
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JComboBox<String> difficultyBox;
    
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameField;
    
    private javax.swing.JButton quitButton;
    private javax.swing.JButton instructionsButton;
    private javax.swing.JButton startButton;
    
    /**
     * Constructs the window. Adds a reference to the game manager
     * in order to transfer information to it.
     * @param aManager Reference to the backend class GameManager
     */
    public TitleScreen(GameManager aManager){
        
        manager = aManager;
        init(); // Builds the screen
    }
    
    // Upon selection, this creates a dialog box that displays instructions
    private void openInstructions() {

        String message = "Keep buying and selling stocks until your finger drops! Press on the business \n" +
        "you want to buy or sell and change the number ticker that is under the buy button. \n" +
        "To get details on a business, press on the business you want to learn about and press details. \n" +
        "When you are done buying and selling, press next week for the stocks to fluctuate. \n" +
        "The losing condition is when the bank balance is negative for three weeks in \n" +
        "a row, reguardless of how much money is in tied up in shares. Have Fun and Enjoy!!";
        
        JOptionPane.showMessageDialog(this, message, "Instructions", JOptionPane.PLAIN_MESSAGE);
        
    }
    
    // Upon selection, this starts the game based on the chosen difficulty.
    private void startGame() {
        
        nameField.setText(nameField.getText().trim());
        manager.startGame();
        
    }
    
    // Upon selection, this closes the game window.
    private void closeGame() {
        
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", 
                "End Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {   
            manager.closeGame();
        }
        else return;
        
    }
    
    /**
     * Accesses the player's name specified in the name field.
     * @return The name specified by the Player. If no name is specified, 'Player' is used.
     */
    public String getNameField() {
        
        return nameField.getText();
        
    }
    
    /**
     * Accesses the difficulty chosen by the player.
     * @return The difficulty chosen
     */
    public Difficulties getDifficulty() {
        
        return switch (difficultyBox.getSelectedIndex()) {
            case 0 -> Difficulties.EASY;
            case 1 -> Difficulties.NORMAL;
            default -> Difficulties.HARD;
        };

    }
    
    
    // Builds the title screen
    private void init(){
        
        setLayout(new BorderLayout());
        
        //*******************************************************
        // Sets the title of the game and the image

        imageLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        
        
        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); 
        titleLabel.setText("Life is Tough: Trader Tycoon");
        add(titleLabel, BorderLayout.PAGE_START);
        
        // Inputs logo.png
        try {
            BufferedImage img = ImageIO.read(new File("edu\\uah\\cs\\lifeistough\\logo.png"));
            imageLabel.setIcon(new javax.swing.ImageIcon(img));
            add(imageLabel, BorderLayout.LINE_START);
        } catch (IOException e) {
            
        }       
        
        
        //********************************************************
        // Sets the difficulty drop-down select field

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints constr = new GridBagConstraints();
        
        JPanel difficultyPanel = new JPanel();
        
        difficultyLabel = new javax.swing.JLabel();
                
        difficultyLabel.setText("Select your difficulty:");
        difficultyPanel.add(difficultyLabel);
        
        
        String difficulties[] = {"Easy", "Normal", "Hard"};
        difficultyBox = new javax.swing.JComboBox<String>(difficulties);
                
        difficultyPanel.add(difficultyBox);
                
        constr.gridx = 0;
        constr.gridy = 1; 
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridheight = 1;
        
        fieldPanel.add(difficultyPanel, constr);
        
        //********************************************************
        // Sets the player name text field

        JPanel namePanel = new JPanel();
        
        nameLabel = new javax.swing.JLabel();
                
        nameLabel.setText("Select your name:");
        namePanel.add(nameLabel);
        
        nameField = new javax.swing.JTextField();
        nameField.setColumns(9);
        nameField.setTransferHandler(null);
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (nameField.getText().length() >= 15) // limit to between 1 and 15 characters
                e.consume();
            }
        });
        nameField.setText("Player");
        
        namePanel.add(nameField);
        
        constr.gridx = 0;
        constr.gridy = 2;
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridheight = 1;
        
        fieldPanel.add(namePanel, constr);
        
        fieldPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        add(fieldPanel, BorderLayout.CENTER);
        
        //********************************************************
        // Sets up the buttons


        // Quit Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        quitButton = new javax.swing.JButton("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeGame();
            }
        });
        
        buttonPanel.add(quitButton);
        

        // Instructions dialog box button
        instructionsButton = new javax.swing.JButton("Instructions");
        instructionsButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              openInstructions();
            }
        });
        
        buttonPanel.add(instructionsButton);
        

        // Start game button
        startButton = new javax.swing.JButton("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGame();
            }
        });
        
        buttonPanel.add(startButton);
        
        add(buttonPanel, BorderLayout.PAGE_END);
        
        //********************************************************

        revalidate();
        repaint();
        
    }
}
