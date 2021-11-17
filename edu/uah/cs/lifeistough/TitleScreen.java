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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author luked
 */
public class TitleScreen extends JPanel{
    
    private final GameManager manager;
    
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
                
        JOptionPane.showMessageDialog(this,
            "To play the game, guess and press buttons \nrandomly until something"
                    + " good happens. Thanks!", "Instructions",
            JOptionPane.PLAIN_MESSAGE);
        
    }
    
    private void startGame() {
        
        nameField.setText(nameField.getText().trim());
        
        if(nameField.getText().length() < 1){
            nameField.setText("         Player");
        }
        else if(nameField.getText().length() < 15){
            String buffer = "";
            for (int i = 0; i < 15 - nameField.getText().length(); i++){
                if(i%2 == 0){
                    buffer += "  ";
                }
                else{
                    buffer +=" ";
                }
            }
            nameField.setText(buffer + nameField.getText());
        }
        manager.startGame();
        
    }
    
    private void closeGame() {
        
        manager.endGame();
        
    }
    
    public String getNameField() {
        
        return nameField.getText();
        
    }
    
    public Difficulties getDifficulty() {
        
        return switch (difficultyBox.getSelectedIndex()) {
            case 0 -> Difficulties.EASY;
            case 1 -> Difficulties.MEDIUM;
            default -> Difficulties.HARD;
        };
        
    }
    
    public void restartTitle(){
     
        while(nameField.getText().charAt(0) == ' ')
            nameField.setText(nameField.getText().substring(1));
        
    }
    
    private void init(){
        
        setLayout(new BorderLayout());
        
        //*******************************************************
        
        imageLabel = new javax.swing.JLabel();
        TitleLabel = new javax.swing.JLabel();
        
        
        TitleLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); 
        TitleLabel.setText("StockSimGame");
        add(TitleLabel, BorderLayout.PAGE_START);
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Picture for picture.png"));
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
        difficultyBox = new javax.swing.JComboBox(difficulties);
                
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
       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        quitButton = new javax.swing.JButton("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeGame();            }
        });
        
        buttonPanel.add(quitButton);
        
        instructionsButton = new javax.swing.JButton("Instructions");
        instructionsButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openInstructions();
            }
                      
        });
        
        buttonPanel.add(instructionsButton);
        
        startButton = new javax.swing.JButton("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGame();
            }
        });
        
        buttonPanel.add(startButton);
        
        add(buttonPanel, BorderLayout.PAGE_END);
        
        revalidate();
        repaint();
        
    }
}
