/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.Insets;

import javax.swing.JPanel;

/**
 *
 * @author Devin Patel and Ben Johnson
 */
public class NewsScreen extends JPanel
{
    private GameManager manager;
    
    private javax.swing.JLabel newsTitle;
    private javax.swing.JTextArea newsDescription;
    
    private javax.swing.JTextArea expenseInformation; // Includes reason and amount

    private javax.swing.JLabel weekNumber;
    private javax.swing.JTextArea updatedPlayerBalance;
    
    private javax.swing.JButton continueButton;

    private NewsEvent event;
    private String expenseReason;
    private int expenseAmount;
    
    
    public NewsScreen(GameManager aManager, NewsEvent aEvent, String aExpenseReason, int aExpenseAmount)
    {
        manager = aManager;
        event = aEvent;
        expenseReason = aExpenseReason;
        expenseAmount = aExpenseAmount;

        buildScreen();
    }

    private void continueButtonPressed()
    {
        manager.returnToTitleScreen();
        //manager.goToStockScreen();
    }

    
    private void buildScreen()
    {
        //setBackground(new Color(150,0,0)); // Dark red

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        int topInset = 10;
        int leftInset = 10;
        int bottomInset = 10;
        int rightInset = 10;
        constraints.insets = new Insets(topInset, leftInset, bottomInset, rightInset);
        
        //*******************************************************
        // Sets the news title
        newsTitle = new javax.swing.JLabel();
        
        newsTitle.setFont(new java.awt.Font("Tahoma", 0, 36));
        newsTitle.setText("Breaking News: " + event.getName());

        constraints.gridx = 0;
        constraints.gridwidth = 4;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        constraints.fill = GridBagConstraints.BOTH;


        add(newsTitle, constraints);
        

        //********************************************************
        // Sets the news description
        newsDescription = new javax.swing.JTextArea();

        newsDescription.setFont(new java.awt.Font("Tahoma", 0, 18));
        newsDescription.setEditable(false);
        newsDescription.setText(event.getHeadline());

        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridheight = 2;


        add(newsDescription, constraints);

        //********************************************************
        // Sets Expense information

        String expenseMessage = "You have a new bill to pay." + "\n"
                                + expenseReason + "\n"
                                + "$" + Math.abs(expenseAmount) + " will be deducted from your bank account.";

        expenseInformation = new javax.swing.JTextArea();
        
        expenseInformation.setFont(new java.awt.Font("Tahoma", 0, 18));
        expenseInformation.setEditable(false);
        expenseInformation.setText(expenseMessage);

        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.gridheight = 2;

        constraints.insets = new Insets(100, leftInset, bottomInset, rightInset);


        add(expenseInformation, constraints);

        //********************************************************
        // Sets the week number
        
        weekNumber = new javax.swing.JLabel();
        
        weekNumber.setFont(new java.awt.Font("Tahoma", 0, 28));
        weekNumber.setText("Week " + Integer.toString(manager.getWeekNumber()));

        constraints.gridx = 5;
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        constraints.insets = new Insets(topInset, 150, bottomInset, rightInset);

        add(weekNumber, constraints);

        //********************************************************
        // Sets the player's bank balance after expenses
        
        updatedPlayerBalance = new javax.swing.JTextArea();
        
        updatedPlayerBalance.setFont(new java.awt.Font("Tahoma", 0, 22));
        updatedPlayerBalance.setText("Bank Balance:\n" + Integer.toString(manager.getPlayerBank()));

        constraints.gridx = 5;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.gridheight = 1;

        constraints.insets = new Insets(topInset, 100, bottomInset, rightInset);

        add(updatedPlayerBalance, constraints);

        //********************************************************
        // Sets the continue button
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        continueButton = new javax.swing.JButton("Continue to Stock Screen");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }

            private void continueButtonActionPerformed(ActionEvent evt) {
                continueButtonPressed(); // User-defined Method above
            }
        });
        
        buttonPanel.add(continueButton);
        
        constraints.gridx = 5;
        constraints.gridy = 10;

        constraints.insets = new Insets(topInset, leftInset, bottomInset, rightInset);
        
        add(buttonPanel, constraints);
        
        revalidate();
        repaint();
        
    }
}
