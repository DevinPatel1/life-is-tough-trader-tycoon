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
import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 *
 * @author Devin Patel and Ben Johnson
 */
public class NewsScreen extends JPanel
{
    private GameManager manager;
    
    private javax.swing.JPanel newsPanel;
    private javax.swing.JLabel newsTitle;
    private javax.swing.JTextArea newsDescription;
    
    private javax.swing.JPanel expensePanel;
    private javax.swing.JLabel expenseTitle;
    private javax.swing.JTextArea expenseReasonTextArea;
    private javax.swing.JTextArea expenseAmountTextArea;

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
        int bottomInset = 50;
        int rightInset = 10;
        constraints.insets = new Insets(topInset, leftInset, bottomInset, rightInset);
        
        //*******************************************************
        // Sets the news panel
        
        newsPanel = new JPanel();
        newsPanel.setLayout(new BorderLayout());

        // News Title
        newsTitle = new javax.swing.JLabel();
        
        newsTitle.setFont(new java.awt.Font("Tahoma", 0, 36));
        newsTitle.setText("Breaking News: " + event.getName());

        newsPanel.add(newsTitle, BorderLayout.NORTH);
        
        // Sets the news description
        newsDescription = new javax.swing.JTextArea();

        newsDescription.setFont(new java.awt.Font("Tahoma", 0, 18));
        newsDescription.setEditable(false);
        newsDescription.setText(event.getHeadline());

        newsPanel.add(newsDescription, BorderLayout.CENTER);

        constraints.gridx = 0;
        constraints.gridwidth = 4;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        
        constraints.fill = GridBagConstraints.BOTH;

        add(newsPanel, constraints);

        //********************************************************
        // Sets the week number
        weekNumber = new javax.swing.JLabel();
        
        weekNumber.setFont(new java.awt.Font("Tahoma", 0, 28));
        weekNumber.setText("Week " + Integer.toString(manager.getWeekNumber()));

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        
        add(weekNumber, constraints);

        //********************************************************
        // Sets the player's bank balance after expenses
        
        updatedPlayerBalance = new javax.swing.JTextArea();
        
        updatedPlayerBalance.setFont(new java.awt.Font("Tahoma", 0, 28));
        updatedPlayerBalance.setText("Bank Balance:\n" + Integer.toString(manager.getPlayerBank()));

        constraints.gridx = 4;
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.gridheight = 1;

        constraints.anchor = GridBagConstraints.PAGE_START;

        add(updatedPlayerBalance, constraints);

        //********************************************************
        // Sets Expense Panel

        expensePanel = new javax.swing.JPanel();
        expensePanel.setLayout(new GridBagLayout());
        
        GridBagConstraints expenseConstraints = new GridBagConstraints();
        expenseConstraints.anchor = GridBagConstraints.CENTER;

        // Expense Title
        expenseTitle = new javax.swing.JLabel();
        
        expenseTitle.setFont(new java.awt.Font("Tahoma", 0, 28));
        expenseTitle.setText("Bills:");

        expenseConstraints.gridx = 0;
        expenseConstraints.gridy = 0;

        expenseConstraints.insets = new Insets(0, 0, 10, 0);

        expensePanel.add(expenseTitle, expenseConstraints);


        // Displays the reason for the expense
        expenseReasonTextArea = new javax.swing.JTextArea();
        
        expenseReasonTextArea.setFont(new java.awt.Font("Tahoma", 0, 18));
        expenseReasonTextArea.setEditable(false);
        expenseReasonTextArea.setText(expenseReason);

        expenseConstraints.gridx = 0;
        expenseConstraints.gridy = 1;

        expensePanel.add(expenseReasonTextArea, expenseConstraints);

        // Displays the amount
        expenseAmountTextArea = new javax.swing.JTextArea();

        expenseAmountTextArea.setFont(new java.awt.Font("Tahoma", 0, 18));
        expenseAmountTextArea.setEditable(false);
        expenseAmountTextArea.setText("$"+Integer.toString(Math.abs(expenseAmount))+" will be deducted from your bank account.");

        expenseConstraints.gridx = 0;
        expenseConstraints.gridy = 2;

        expensePanel.add(expenseAmountTextArea, expenseConstraints);

        // Adds expense panel to main panel
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.gridheight = 1;


        add(expensePanel, constraints);

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
        
        constraints.gridx = 4;
        constraints.gridy = 4;

        expenseConstraints.insets = new Insets(0, 0, 0, 0);
        
        add(buttonPanel, constraints);
        
        revalidate();
        repaint();
        
    }
}
