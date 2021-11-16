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
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Luke Farris
 */
public class TitleScreen extends JPanel{
    
    private GameManager manager;
    
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel TitleLabel;
    
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JComboBox<String> difficultyBox;
    
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameField;
    
    private javax.swing.JButton quitButton;
    private javax.swing.JButton instructionsButton;
    private javax.swing.JButton startButton;
    
    
    public TitleScreen(GameManager aManager){
        
        manager = aManager;
        init();
        
    }
    
    private void openInstructions() {
        
        JDialog frame = new JDialog();
        
        JOptionPane.showMessageDialog(frame,
            "To play the game, guess and press buttons \nrandomly until something"
                    + " good happens. Thanks!", "Instructions",
            JOptionPane.PLAIN_MESSAGE);
        
    }
    
    private void startGame() {
        
        manager.startGame();
        
    }
    
    private void closeGame() {
        
        manager.endGame();
        
    }
    
    public String getNameField() {
        
        return nameField.getText();
        
    }
    
    public Difficulties getDifficulty() {
        
        switch (difficultyBox.getSelectedIndex()){
            
            case 0:
                return Difficulties.EASY;
            case 1:
                return Difficulties.NORMAL;
            default:
                return Difficulties.HARD;
            
        }
        
    }
    
    private void init(){
        
        setLayout(new BorderLayout());
        
        //*******************************************************
        
        imageLabel = new javax.swing.JLabel();
        TitleLabel = new javax.swing.JLabel();
        
        
        TitleLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); 
        TitleLabel.setText("Life is Tough: Trader Tycoon");
        add(TitleLabel, BorderLayout.PAGE_START);
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("edu\\uah\\cs\\lifeistough\\logo.png"));
        } catch (IOException e) {
            
        }       
        
        imageLabel.setIcon(new javax.swing.ImageIcon(img));
        add(imageLabel, BorderLayout.LINE_START);
        
        //********************************************************
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints constr = new GridBagConstraints();
        
        JPanel difficultyPanel = new JPanel();
        
        difficultyLabel = new javax.swing.JLabel();
                
        difficultyLabel.setText("Select your difficulty:");
        difficultyPanel.add(difficultyLabel);
        
        
        String difficulties[] = {"Easy", "Medium", "Hard"};
        difficultyBox = new javax.swing.JComboBox<String>(difficulties);
                
        difficultyPanel.add(difficultyBox);
                
        constr.gridx = 0;
        constr.gridy = 1; 
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.gridheight = 1;
        
        fieldPanel.add(difficultyPanel, constr);
        
        //********************************************************
        
        JPanel namePanel = new JPanel();
        
        nameLabel = new javax.swing.JLabel();
                
        nameLabel.setText("Select your name:");
        namePanel.add(nameLabel);
        
        nameField = new javax.swing.JTextField();
        nameField.setColumns(10);
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
       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        quitButton = new javax.swing.JButton("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitButtonActionPerformed(evt);
            }

            private void QuitButtonActionPerformed(ActionEvent evt) {
                closeGame();
            }
        });
        
        buttonPanel.add(quitButton);
        
        instructionsButton = new javax.swing.JButton("Instructions");
        instructionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InstructionsButtonActionPerformed(evt);
            }

            private void InstructionsButtonActionPerformed(ActionEvent evt) {
                openInstructions();
            }

            
        });
        
        buttonPanel.add(instructionsButton);
        
        startButton = new javax.swing.JButton("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }

            private void StartButtonActionPerformed(ActionEvent evt) {
                startGame();
            }
        });
        
        buttonPanel.add(startButton);
        
        add(buttonPanel, BorderLayout.PAGE_END);
        
        revalidate();
        repaint();
        
    }
}
