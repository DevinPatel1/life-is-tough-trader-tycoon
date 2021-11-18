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
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Luke Farris
 * @author Corey Clayborn
 * @author Dominic Kenyon
 */
class StockScreen extends JPanel {
 
    private GameManager manager;
    private Business currentBusinessCopy;
    
    private JLabel busListLabel;
    private JList<BusinessSymbol> busList;
    private JButton busDetailButton;
    
    private JButton exitButton;
    private JButton nextWeekButton;
    private JButton newsScreenButton;
    
    private JLabel bankLabel;
    private JTextField bankAccountField;
    private JLabel weekLabel;
    private JTextField weekNumberField;
    
    private JLabel valueOfInvestmentsLabel;
    private JTextField valueOfInvestmentsField;
    private JLabel currentSharePriceLabel;
    private JTextField currentSharePriceField;
    private JLabel prevSharePriceLabel;
    private JTextField prevSharePriceField;
    private JLabel sharePriceChangeLabel;
    private JTextField sharePriceChangeField;
    private JLabel percentSharePriceChangeLabel;
    private JTextField percentSharePriceChangeField;
    private JLabel ownedSharesLabel;
    private JTextField ownedSharesField;
    private JLabel availSharesLabel;
    private JTextField availSharesField;
    
    JButton buySharesButton;
    JButton sellSharesButton;
    JSpinner shareQuantity;
    
    
    public StockScreen(GameManager aManager){
        
        manager = aManager;
        currentBusinessCopy = manager.getBusinessCopy(BusinessSymbol.values()[0]);
        init();
        
    }
    
    private void buyShare() {
        
        manager.buyShare(BusinessSymbol.values()[busList.getSelectedIndex()], (int) shareQuantity.getValue());
        
        updateBusiness();
        updateBank();
        changeSpinner();
        
    }
    
    private void sellShare() {
        
        manager.sellShare(BusinessSymbol.values()[busList.getSelectedIndex()], (int) shareQuantity.getValue());
         
        updateBusiness();
        updateBank();
        changeSpinner();
        
    }
    
    private void returnToTitleScreen() {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "End Game", 0, 1) == 0)
        manager.returnToTitleScreen();
        
    }
    
    private void goToNextWeek() {
        
        manager.goToNextWeek();
        updateBank();
        updateBusiness();
        updateWeek();
        
        
    }

    private void goToNewsScreen() {
        
    
        manager.goToPreviousNewsScreen();
        
    }
    

    /**
     * Updates button availability based on whether the transaction would be possible.
     */
    private void changeSpinner() {
        
        if( (int) shareQuantity.getValue() 
                > manager.getPlayerOwnedShares(BusinessSymbol.values()[busList.getSelectedIndex()])){
            
            sellSharesButton.setEnabled(false);
            
        }
        else {
        
            sellSharesButton.setEnabled(true);
        
        }
        
        if( (int) shareQuantity.getValue() > currentBusinessCopy.getSharesAvailable() 
                || (int) shareQuantity.getValue() * 
                manager.getPlayerOwnedShares(BusinessSymbol.values()[busList.getSelectedIndex()]) 
                > manager.getPlayerBank()){
        
            buySharesButton.setEnabled(false);
            
        }
        else{
            
            buySharesButton.setEnabled(true);
            
        }

    }
    
    /**
     * When a new game is started, updates some fields like player name
     * and (possibly added later) week number and even background color
     * based on difficulty.
     */
    public void startStockScreen() {
        
        if(manager.getPlayerName().endsWith("s")){
            bankLabel.setText("" + manager.getPlayerName() + "' Bank Account:");
        }
        else {
            bankLabel.setText("" + manager.getPlayerName() + "'s Bank Account:");
        }        
    }
 
 
    private void updateBusiness() {
        try {
            currentBusinessCopy = manager.getBusinessCopy(BusinessSymbol.values()[busList.getSelectedIndex()]);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(StockScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        valueOfInvestmentsField.setText("$" + 
                (currentBusinessCopy.getPrice() 
                * manager.getPlayerOwnedShares(busList.getSelectedIndex())));
        currentSharePriceField.setText("$" + 
                (currentBusinessCopy.getPrice()));
        prevSharePriceField.setText("$" + 
                (currentBusinessCopy.getPreviousPrice()));
        
        if(currentBusinessCopy.getPrice() - currentBusinessCopy.getPreviousPrice() > 0){
            sharePriceChangeField.setText("+$" + 
                (currentBusinessCopy.getPrice() - currentBusinessCopy.getPreviousPrice()));
        }
        else{
            sharePriceChangeField.setText("-$" + 
                Math.abs(currentBusinessCopy.getPrice() - currentBusinessCopy.getPreviousPrice()));
        }
        
        if(currentBusinessCopy.getPrice() - currentBusinessCopy.getPreviousPrice() > 0){
            percentSharePriceChangeField.setText("+" + 
                ((int)((float)currentBusinessCopy.getPrice()/(float)currentBusinessCopy.getPreviousPrice()*100) - 100) + "%");
        }
        else{
            percentSharePriceChangeField.setText("-" + 
                (100-(int)((float)currentBusinessCopy.getPreviousPrice()/(float)currentBusinessCopy.getPrice() * 100)) + "%");
        }
        
        ownedSharesField.setText("" + manager.getPlayerOwnedShares(busList.getSelectedIndex()));
        
        availSharesField.setText("" + 
                (currentBusinessCopy.getSharesAvailable()));
        
    }
 
 
    private void updateBank() {
        if(manager.getPlayerBank() < 0){
            
            bankAccountField.setText("-$" + Math.abs(manager.getPlayerBank()));

        }
        else{
            bankAccountField.setText("$" + manager.getPlayerBank());
        }
    }
    
    private void updateWeek(){
            weekNumberField.setText("" + manager.getWeekNumber());
    }
 
    
    private void init(){
    
        setLayout(new BorderLayout());
        
        //*********************************************************
        
        JPanel busListPanel = new JPanel();
        busListPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints constr = new GridBagConstraints();
        
        constr.insets = new Insets(4,4,4,4);
        
        busListLabel = new JLabel();
        busListLabel.setText("Businesses");
        busListLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        constr.gridx = 1;
        constr.gridy = 1;
        constr.fill = 4;
        constr.gridheight = 1;
        
        busListPanel.add(busListLabel, constr);
        
        
        
        busList = new JList<BusinessSymbol>(BusinessSymbol.values());
        busList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        busList.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 12)));
        busList.setSelectedIndex(0);
        
        ListSelectionModel listSelectionModel = busList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
        
            
            @Override
            public void valueChanged(ListSelectionEvent e){ 
                updateBusiness();
            }
            
        });
        
        constr.gridx = 1;
        constr.gridy = 50;
        constr.fill = 80;
        constr.gridheight = GridBagConstraints.VERTICAL;
        
        busListPanel.add(busList, constr);
        busListPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        
        busDetailButton = new JButton("Details");
        busDetailButton.addActionListener((new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBusinessDescription();

            }
        }));
        
        constr.gridx = 1;
        constr.gridy=80;
        constr.fill = 1;
        constr.gridheight = GridBagConstraints.VERTICAL;
        
        busListPanel.add(busDetailButton, constr);
               
        add(busListPanel, BorderLayout.LINE_START);
        
        //*********************************************************
        
        JPanel transitionPanel = new JPanel(new GridBagLayout());
        transitionPanel.setBackground(new Color(255, 255, 255));
        
        constr.gridx = 1;
        constr.gridy = 1;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        constr.insets = new Insets(4, 4, 4, 310);
        
        exitButton = new JButton("<html><center>Back to<br>Title</html>");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToTitleScreen();
            }
            
        });
        
        
        transitionPanel.add(exitButton, constr);
        
        
        nextWeekButton = new JButton("<html><center>Go to<br>Next Week</html>");
        nextWeekButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToNextWeek();
            }
            
        });
        
        constr.gridx = 40;
        constr.gridy = 1;
        constr.gridwidth = 20;
        constr.gridheight = 1;
        constr.insets = new Insets(4, 4, 4, 4);
        
        transitionPanel.add(nextWeekButton, constr);
        
        
        newsScreenButton = new JButton("<html><center>View<br>Previous Week</html>");
        newsScreenButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToNewsScreen();
            }
            
        });

        
        constr.gridx = 20;
        constr.gridy = 1;
        constr.gridwidth = 20;
        constr.gridheight = 1;
        
        transitionPanel.add(newsScreenButton, constr);

        
        add(transitionPanel, BorderLayout.PAGE_END);
        
        //****************************************************8
        
        JPanel headerPanel = new JPanel(new GridBagLayout());
        
        JPanel bankPanel = new JPanel();
        
        bankLabel = new JLabel();
        if(manager.getPlayerName().endsWith("s")){
            bankLabel.setText("" + manager.getPlayerName() + "' Bank Account:");
        }
        else {
            bankLabel.setText("" + manager.getPlayerName() + "' Bank Account:");
        }
        bankLabel.setAlignmentX(RIGHT_ALIGNMENT);
        
        bankPanel.add(bankLabel);
        
        bankAccountField = new JTextField();
        bankAccountField.setBackground(Color.white);
        bankAccountField.setEditable(false);
        bankAccountField.setColumns(10);
        bankAccountField.setHorizontalAlignment(SwingConstants.RIGHT);
        bankAccountField.setText("$" + manager.getPlayerBank());
        
        bankPanel.add(bankAccountField);

        JPanel weekPanel = new JPanel();
        
        weekLabel = new JLabel();
        weekLabel.setText("Current Week:");
        weekPanel.add(weekLabel);
        
        weekNumberField = new JTextField();
        weekNumberField.setBackground(Color.white);
        weekNumberField.setEditable(false);
        weekNumberField.setColumns(4);
        weekNumberField.setHorizontalAlignment(SwingConstants.RIGHT);
        weekNumberField.setText("" + manager.getWeekNumber());
        
        weekPanel.add(weekNumberField);
        
        constr.gridx = 0;
        constr.gridy = 1;
        constr.gridwidth = 200;
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.insets = new Insets(4, 4, 4, 150);
        
        headerPanel.add(bankPanel, constr);
        
        constr.gridx = 200;
        constr.gridy = 1;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        constr.insets = new Insets(4, 4, 4, 4);
        
        headerPanel.add(weekPanel, constr);
        
        headerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(headerPanel, BorderLayout.PAGE_START);
        
        //**********************************************************
        
        JPanel stockPanel = new JPanel(new GridBagLayout());
        stockPanel.setBackground(new Color(12, 200, 140));
        
        valueOfInvestmentsLabel = new JLabel();
        valueOfInvestmentsLabel.setText("Value of investments:");
        
        constr.gridx = 1;
        constr.gridy = 1;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        constr.insets = new Insets(4, 4, 4, 4);
        
        stockPanel.add(valueOfInvestmentsLabel, constr);
        
        
        valueOfInvestmentsField = new JTextField();
        valueOfInvestmentsField.setBackground(Color.white);
        valueOfInvestmentsField.setEditable(false);
        valueOfInvestmentsField.setColumns(10);
        valueOfInvestmentsField.setHorizontalAlignment(SwingConstants.RIGHT);
        valueOfInvestmentsField.setText("$" + 
                (currentBusinessCopy.getPrice() 
                * manager.getPlayerOwnedShares(BusinessSymbol.values()[busList.getSelectedIndex()])));
        
        constr.gridx = 5;
        
        stockPanel.add(valueOfInvestmentsField, constr);
                
        //***
        
        currentSharePriceLabel = new JLabel();
        currentSharePriceLabel.setText("Current share price:");
        
        constr.gridx = 1;
        constr.gridy = 3;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        
        stockPanel.add(currentSharePriceLabel, constr);
        
        
        currentSharePriceField = new JTextField();
        currentSharePriceField.setBackground(Color.white);
        currentSharePriceField.setEditable(false);
        currentSharePriceField.setColumns(10);
        currentSharePriceField.setHorizontalAlignment(SwingConstants.RIGHT);
        currentSharePriceField.setText("$" + 
                (currentBusinessCopy.getPrice()));
        
        constr.gridx = 5;
        
        stockPanel.add(currentSharePriceField, constr);
        
        //***
        
        prevSharePriceLabel = new JLabel();
        prevSharePriceLabel.setText("Previous share price:");
        
        constr.gridx = 1;
        constr.gridy = 5;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        
        stockPanel.add(prevSharePriceLabel, constr);
        
        
        prevSharePriceField = new JTextField();
        prevSharePriceField.setBackground(Color.white);
        prevSharePriceField.setEditable(false);
        prevSharePriceField.setColumns(10);
        prevSharePriceField.setHorizontalAlignment(SwingConstants.RIGHT);
        prevSharePriceField.setText("$" + 
                (currentBusinessCopy.getPreviousPrice()));
        
        constr.gridx = 5;
        
        stockPanel.add(prevSharePriceField, constr);
        
        //**
        
        sharePriceChangeLabel = new JLabel();
        sharePriceChangeLabel.setText("Change in share price:");
        
        constr.gridx = 1;
        constr.gridy = 7;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        
        stockPanel.add(sharePriceChangeLabel, constr);
        
        
        sharePriceChangeField = new JTextField();
        sharePriceChangeField.setBackground(Color.white);
        sharePriceChangeField.setEditable(false);
        sharePriceChangeField.setColumns(10);
        sharePriceChangeField.setHorizontalAlignment(SwingConstants.RIGHT);
        
        if(currentBusinessCopy.getPrice() - currentBusinessCopy.getPreviousPrice() > 0){
            sharePriceChangeField.setText("+$" + 
                (currentBusinessCopy.getPrice() - currentBusinessCopy.getPreviousPrice()));
        }
        else{
            sharePriceChangeField.setText("-$" + 
                Math.abs(currentBusinessCopy.getPrice() - currentBusinessCopy.getPreviousPrice()));
        }
        
        
        
        constr.gridx = 5;
        
        stockPanel.add(sharePriceChangeField, constr);
        
        //***
        
        percentSharePriceChangeLabel = new JLabel();
        percentSharePriceChangeLabel.setText("Percent share price change:");
        
        constr.gridx = 1;
        constr.gridy = 9;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        
        stockPanel.add(percentSharePriceChangeLabel, constr);
        
        
        percentSharePriceChangeField = new JTextField();
        percentSharePriceChangeField.setBackground(Color.white);
        percentSharePriceChangeField.setEditable(false);
        percentSharePriceChangeField.setColumns(10);
        percentSharePriceChangeField.setHorizontalAlignment(SwingConstants.RIGHT);
        
        if(currentBusinessCopy.getPrice() - currentBusinessCopy.getPreviousPrice() > 0){
            percentSharePriceChangeField.setText("+" + 
                ((int)((float)currentBusinessCopy.getPrice()/(float)currentBusinessCopy.getPreviousPrice()*100) - 100) + "%");
        }
        else{
            percentSharePriceChangeField.setText("-" + 
                (100-(int)((float)currentBusinessCopy.getPreviousPrice()/(float)currentBusinessCopy.getPrice() * 100)) + "%");
        }
        
        
        
        constr.gridx = 5;
        
        stockPanel.add(percentSharePriceChangeField, constr);
        
        //***
        
        ownedSharesLabel = new JLabel();
        ownedSharesLabel.setText("Owned Shares:");
        
        constr.gridx = 1;
        constr.gridy = 11;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        
        stockPanel.add(ownedSharesLabel, constr);
        
        
        ownedSharesField = new JTextField();
        ownedSharesField.setBackground(Color.white);
        ownedSharesField.setEditable(false);
        ownedSharesField.setColumns(5);
        ownedSharesField.setHorizontalAlignment(SwingConstants.RIGHT);
                
        ownedSharesField.setText("" + manager.getPlayerOwnedShares(BusinessSymbol.values()[busList.getSelectedIndex()]));
        
        constr.gridx = 5;
        
        stockPanel.add(ownedSharesField, constr);
        
        //***
        
        availSharesLabel = new JLabel();
        availSharesLabel.setText("Available Shares:");
        
        constr.gridx = 1;
        constr.gridy = 13;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        
        stockPanel.add(availSharesLabel, constr);
        
        
        availSharesField = new JTextField();
        availSharesField.setBackground(Color.white);
        availSharesField.setEditable(false);
        availSharesField.setColumns(5);
        availSharesField.setHorizontalAlignment(SwingConstants.RIGHT);
        availSharesField.setText("" + 
                (currentBusinessCopy.getSharesAvailable()));
        
        constr.gridx = 5;
        
        stockPanel.add(availSharesField, constr);
        
        //**
        add(stockPanel, BorderLayout.CENTER);
        
        //*******************************************************************
        
        JPanel purchaseOptionsPanel = new JPanel(new GridBagLayout());
        
        
        buySharesButton = new JButton("Buy");
        buySharesButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyShare();
            }
            
        });
                
        constr.gridx = 1;
        constr.gridy = 1; 
        constr.gridwidth = 1;
        constr.gridheight = 1;
        
        purchaseOptionsPanel.add(buySharesButton, constr);
        
        //***
        
        shareQuantity = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        shareQuantity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                
                changeSpinner();
                
            }
        
        });
        
        constr.gridx = 1;
        constr.gridy = 3;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        
        purchaseOptionsPanel.add(shareQuantity, constr);
        
        //**
        
        sellSharesButton = new JButton("Sell");
        sellSharesButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellShare();
            }
            
        });

        
        constr.gridx = 1;
        constr.gridy = 5;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        
        purchaseOptionsPanel.add(sellSharesButton, constr);
        
        purchaseOptionsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        
        add(purchaseOptionsPanel, BorderLayout.LINE_END);
        
        revalidate();
        repaint();
    }
    
}
