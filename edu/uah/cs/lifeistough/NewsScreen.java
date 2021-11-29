/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

import javax.swing.JPanel;
import javax.swing.border.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.BorderLayout;


/**
 * This JPanel is responsible for showing the player a summary of what news event affected the businesses
 *     and any expenses charged to their bank account. Their current bank balance is also displayed, and
 *     the player may return to the stock screen by choosing that option on the interface.
 * 
 * @author Devin Patel
 * @author Ben Johnson
 */
public class NewsScreen extends JPanel
{
    private GameManager manager;
    
    private javax.swing.JPanel newsPanel;
    private javax.swing.JLabel breakingNews;
    private javax.swing.JTextArea newsTitle;
    private javax.swing.JTextArea newsDescription;
    
    private javax.swing.JPanel expensePanel;
    private javax.swing.JLabel expenseTitle;
    private javax.swing.JTextArea expenseReasonTextArea;
    private javax.swing.JTextArea expenseAmountTextArea;

    private javax.swing.JPanel weekNumberPanel;
    private javax.swing.JLabel weekNumber;

    private javax.swing.JPanel updatedPlayerBalancePanel;
    private javax.swing.JTextArea updatedPlayerBalance;
    
    private javax.swing.JButton continueButton;

    private NewsEvent event;
    private String expenseReason;
    private int expenseAmount;
    
    
    /**
     * Constructs the window. Adds a reference to the game manager as well as the
     *     news event and expense information.
     * @param aManager Reference to the backend class GameManager
     * @param aEvent Event chosen in GameManager
     * @param aExpenseReason Expense reason chosen in GameManager
     * @param aExpenseAmount Expense amount generated in GameManager
     */
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
        manager.goToStockScreen();
    }

    
    private void buildScreen()
    {
        Color reddishBrown = new Color(80,0,0);
        Color darkRed = new Color(150,0,0);

        setBackground(darkRed);

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        int topInset = 10;
        int leftInset = 10;
        int bottomInset = 50;
        int rightInset = 10;
        constraints.insets = new Insets(topInset, leftInset, bottomInset, rightInset);
        
        //Border lineBorder = new LineBorder(Color.BLACK, 5);
        Border emptyBorder = new EmptyBorder(8,8,8,8);
        Border raisedEtched = new EtchedBorder(EtchedBorder.RAISED, reddishBrown, Color.BLACK);

        //*******************************************************
        // Sets the news panel
        
        newsPanel = new JPanel();
        newsPanel.setLayout(new BorderLayout(0,10));
        newsPanel.setBackground(reddishBrown);

        //newsPanel.setBorder(lineBorder);
        newsPanel.setBorder(raisedEtched);

        // Breaking News
        breakingNews = new javax.swing.JLabel();

        breakingNews.setFont(new java.awt.Font("Tahoma", 0, 36));
        breakingNews.setText("Breaking News: ");

        breakingNews.setBackground(reddishBrown);
        breakingNews.setForeground(Color.WHITE);

        breakingNews.setBorder(new EmptyBorder(5,5,0,5));

        newsPanel.add(breakingNews, BorderLayout.NORTH);

        // News Title
        newsTitle = new javax.swing.JTextArea();
        
        newsTitle.setFont(new java.awt.Font("Tahoma", 0, 28));
        newsTitle.setText(event.getName());
        newsTitle.setEditable(false);

        newsTitle.setBackground(reddishBrown);
        newsTitle.setForeground(Color.WHITE);

        newsTitle.setBorder(new EmptyBorder(0,5,0,5));

        newsPanel.add(newsTitle, BorderLayout.CENTER);
        
        // Sets the news description
        newsDescription = new javax.swing.JTextArea(5, 20);

        newsDescription.setFont(new java.awt.Font("Tahoma", 0, 18));
        newsDescription.setText(event.getHeadline());
        newsDescription.setEditable(false);
        newsDescription.setLineWrap(true);
        newsDescription.setWrapStyleWord(true);

        newsDescription.setBackground(reddishBrown);
        newsDescription.setForeground(Color.WHITE);
        newsDescription.setBorder(new EmptyBorder(0,5,5,5));

        newsPanel.add(newsDescription, BorderLayout.SOUTH);

        constraints.gridx = 0;
        constraints.gridwidth = 4;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        
        constraints.fill = GridBagConstraints.BOTH;

        add(newsPanel, constraints);

        //********************************************************
        // Sets the week number
        weekNumberPanel = new javax.swing.JPanel();
        weekNumberPanel.setLayout(new BorderLayout());

        weekNumberPanel.setBackground(reddishBrown);
        weekNumberPanel.setBorder(raisedEtched);

        weekNumber = new javax.swing.JLabel();
        
        weekNumber.setFont(new java.awt.Font("Tahoma", 0, 28));
        weekNumber.setText("Week " + Integer.toString(manager.getWeekNumber()));
        weekNumber.setBackground(reddishBrown);
        weekNumber.setForeground(Color.WHITE);
        weekNumber.setBorder(emptyBorder);

        weekNumberPanel.add(weekNumber, BorderLayout.CENTER);

        constraints.gridx = 5;
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        
        add(weekNumberPanel, constraints);

        //********************************************************
        // Sets the player's bank balance after expenses
        
        updatedPlayerBalancePanel = new javax.swing.JPanel();
        updatedPlayerBalancePanel.setLayout(new BorderLayout());

        updatedPlayerBalancePanel.setBackground(reddishBrown);
        updatedPlayerBalancePanel.setBorder(raisedEtched);


        updatedPlayerBalance = new javax.swing.JTextArea();
        
        updatedPlayerBalance.setFont(new java.awt.Font("Tahoma", 0, 28));
        updatedPlayerBalance.setText("Bank Balance:\n" + Integer.toString(manager.getPlayerBank()));

        updatedPlayerBalance.setBackground(reddishBrown);
        updatedPlayerBalance.setForeground(Color.WHITE);
        updatedPlayerBalance.setBorder(emptyBorder);

        updatedPlayerBalancePanel.add(updatedPlayerBalance, BorderLayout.CENTER);

        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.gridheight = 1;

        constraints.anchor = GridBagConstraints.FIRST_LINE_END;

        add(updatedPlayerBalancePanel, constraints);

        //********************************************************
        // Sets Expense Panel

        expensePanel = new javax.swing.JPanel();
        expensePanel.setLayout(new GridBagLayout());

        expensePanel.setBackground(reddishBrown);
        expensePanel.setBorder(raisedEtched);
        
        GridBagConstraints expenseConstraints = new GridBagConstraints();
        expenseConstraints.anchor = GridBagConstraints.CENTER;

        // Expense Title
        expenseTitle = new javax.swing.JLabel();
        
        expenseTitle.setFont(new java.awt.Font("Tahoma", 0, 28));
        expenseTitle.setText("Bills:");

        expenseTitle.setBackground(reddishBrown);
        expenseTitle.setForeground(Color.WHITE);
        expenseTitle.setBorder(new EmptyBorder(5,5,0,5));

        expenseConstraints.gridx = 0;
        expenseConstraints.gridy = 0;

        expenseConstraints.insets = new Insets(0, 0, 10, 0);

        expensePanel.add(expenseTitle, expenseConstraints);


        // Displays the reason for the expense
        expenseReasonTextArea = new javax.swing.JTextArea();
        
        expenseReasonTextArea.setFont(new java.awt.Font("Tahoma", 0, 18));
        expenseReasonTextArea.setEditable(false);
        expenseReasonTextArea.setText(expenseReason);

        expenseReasonTextArea.setBackground(reddishBrown);
        expenseReasonTextArea.setForeground(Color.WHITE);
        expenseReasonTextArea.setBorder(new EmptyBorder(0,5,0,5));

        expenseConstraints.gridx = 0;
        expenseConstraints.gridy = 1;

        expenseConstraints.anchor = GridBagConstraints.LINE_START;

        expensePanel.add(expenseReasonTextArea, expenseConstraints);

        // Displays the amount
        expenseAmountTextArea = new javax.swing.JTextArea();

        expenseAmountTextArea.setFont(new java.awt.Font("Tahoma", 0, 18));
        expenseAmountTextArea.setEditable(false);
        expenseAmountTextArea.setText("$"+Integer.toString(Math.abs(expenseAmount))+" will be deducted from your bank account.");

        expenseAmountTextArea.setBackground(reddishBrown);
        expenseAmountTextArea.setForeground(Color.WHITE);
        expenseAmountTextArea.setBorder(new EmptyBorder(0,5,5,5));

        expenseConstraints.gridx = 0;
        expenseConstraints.gridy = 2;

        expensePanel.add(expenseAmountTextArea, expenseConstraints);

        // Adds expense panel to main panel
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 2;
        constraints.gridheight = 1;

        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        add(expensePanel, constraints);

        //********************************************************
        // Sets the continue button
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        continueButton = new javax.swing.JButton(" Go to Stock Screen ");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonPressed();
            }
        });
        
        continueButton.setBorder(raisedEtched);
        
        buttonPanel.setBackground(darkRed);
        
        buttonPanel.add(continueButton);


        // Adds button panel to News Screen
        constraints.gridx = 5;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.gridheight = 1;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        
        add(buttonPanel, constraints);
        

        
        // Refreshes the screen
        revalidate();
        repaint();
    }
}
