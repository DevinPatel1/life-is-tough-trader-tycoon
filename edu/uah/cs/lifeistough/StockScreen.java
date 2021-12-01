/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package stocksimdownloaded;

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
 * This JPanel is responsible for displaying to the user all
 *     of the stock information, including what they own and
 *     what they can buy/sell. 
 * 
 * @author Luke Farris
 * @author Corey Clayborn
 * @author Dominic Kenyon
 */
class StockScreen extends JPanel {
 
    private final GameManager manager;      // A pointer to this game's manager,
                                            // will be used to inform the gameManager
                                            // of StockScreen events.
    
    private Business currentBusinessCopy;   // A copy of one of the businesses
                                            // in the manager, is used to display
                                            // business information.
    
   
    private JLabel busListLabel;            // A label for the busList.
    private JList<BusinessSymbol> busList;  // A list of businessCodes that
                                            // lets the user select which business to view.
    private JButton busDetailButton;        // A button that lets the user see more
                                            // information about a selected business.
    
    private JButton exitButton;             // A button that lets the user exit the game.
    private JButton nextWeekButton;         // A button that lets the user go to
                                            // the next week.
    private JButton newsScreenButton;       // A button that shows the user the news screen.
    
    private JLabel bankLabel;               // A label for the bankAccountField.
    private JTextField bankAccountField;    // A field dispalying the user's bank account.
    private JLabel weekLabel;               // A label for the weekNumberField.
    private JTextField weekNumberField;     // A field displaying what the current week
                                            // is. Will be one greater than the week
                                            // number stored in the manager, as that
                                            // field tracks the number of survived weeks.
    
    private JLabel valueOfInvestmentsLabel; // A label for the valueOfInvestmentsField.
    private JTextField valueOfInvestmentsField; // The current value of all player-owned 
                                                //shares in the selected business.
    private JLabel currentSharePriceLabel;
    private JTextField currentSharePriceField;  // The current price of the selected
                                                // business' shares.
    private JLabel prevSharePriceLabel;
    private JTextField prevSharePriceField;     // Last week's price of the selected
                                                //business' shares.
    private JLabel sharePriceChangeLabel;
    private JTextField sharePriceChangeField;   // The numeric change in price of 
                                                // the selected business' shares.
    
    private JLabel percentSharePriceChangeLabel;
    private JTextField percentSharePriceChangeField; // The percent change in share price.
    private JLabel ownedSharesLabel;
    private JTextField ownedSharesField;             // The number of player owned shares in a business.
    private JLabel availSharesLabel;
    private JTextField availSharesField;             // The amount of shares available in a business.
    
    private JButton buySharesButton;            // A button that allows users to
                                                // buy shares in the selected business
    private JButton sellSharesButton;           // A button that allows users to
                                                // sell shares in the selected business
    private JSpinner shareQuantity;             // A spinner that allows the user
                                                // to select the number of shares
                                                // to buy or sell. 
    
    
    /**
     * Generates a StockScreen object with a reference to the GameManager.
     * @param aManager is a pointer to the game's controller class. 
     */
    public StockScreen(GameManager aManager){
        
        manager = aManager; // Assign the manager reference
        
        // Set the stock screen's initial business to be the first business
        // in the manager.
        currentBusinessCopy = manager.getBusinessCopy(BusinessSymbol.values()[0]);
        
        init();             // Initialize the stock screen's gui.
        
    }
    
    /**
     * Updates the game's business and player bank, as well as the stock screen's
     * corresponding fields by purchasing the number of shares defined by 
     * the shareQuantity spinner from the business defined by the busList's selected
     * business.
     */
    private void buyShare() {
        
        // Perform the buyShare method in the manager by passing in the BusinessSymbol 
        // of the business the share was purchased from and the number of shares bought.
        manager.buyShare(BusinessSymbol.values()[busList.getSelectedIndex()], (int) shareQuantity.getValue());
        
        // After the manager's business and the manager's player's bank
        // are updated following the buy share mehtod, update the Business Copy
        // and its fields along with the bankAccountField.
        updateBusiness();
        updateBank();
        
        // Following the change in the player's bank account or the business
        // bought from, the spinner and purchase options should be updated
        // in accordance to what the updated businesses can allow.
        updatePurchaseOptions();
        
    }
    
    /**
     * Updates the game's business and player bank, as well as the stock screen's
     * corresponding fields by selling the number of shares defined by 
     * the shareQuantity spinner from the business defined by the busList's selected
     * business.
     */
    private void sellShare() {
        
        // Perform the sellShare method in the manager by passing in the BusinessSymbol 
        // of the business the share was sold from and the number of shares sold.
        manager.sellShare(BusinessSymbol.values()[busList.getSelectedIndex()], (int) shareQuantity.getValue());
        
        // After the manager's business and the manager's player's bank
        // are updated following the buy share mehtod, update the Business Copy
        // and its fields along with the bankAccountField.
        updateBusiness();
        updateBank();
        
        // Following the change in the player's bank account or the business
        // sold from, the spinner and purchase options should be updated
        // in accordance to what the updated businesses can allow.
        updatePurchaseOptions();
        
    }
    
    /**
     * Presents the user with a dialog box asking if the user would like to 
     * end the game. If the user selects yes, the dialog closes and 
     * the game manager transitions the game back to the title screen. 
     * Otherwise, the dialog closes and nothing
     * happens.
     */
    private void returnToTitleScreen() {
        
        // Within the if statement, a dialog box is created. This dialog box is a
        // confirm dialog, meaning it will return an integer value depending
        // on the button the user chooses. The parameters for this dialog box
        // are as follows:
        //**********************************************************************
        //JOptionPane.showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType)
        //  parentComponent: the frame the dialog box is displayed in
        //      is set to 'this' so that the dialog box appears in and disables the stockScreen until resolved
        //  message: the string that prompts the user for input
        //      is set to the simple message "Are you sure you want to quit?"
        //  title: the title of the dialog box created, is set to "End Game"
        //  optionType: this parameter determines the options the dialog will
        //      present the user. Being set to YES_NO_OPTION means the dialog box
        //      will present the user a yes and no option. If the yes option
        //      is selected, a 0 is returned. If the no option is chosen, a 1
        //      is returned. If the dialog is closed, the CLOSED_OPTION constant
        //      is returned. 
        //  messageType: this parameter determines the default icon that is displayed
        //      by the dialog box. The QUESTION_MESSAGE constant means a green
        //      question mark is displayed.
        //**********************************************************************
        // The if statement checks if the dialog box's return value is 0,
        // meaning yes was chosen. If so, the game transitions to the title
        // screen using a call to the game manager. Otherwise, nothing happens. 
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", 
                "End Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            manager.returnToTitleScreen();
        
    }
    
    /**
     * Tells the game manager to generate the next week and updates the 
     * stock screen's fields to display the new values.
     */
    private void goToNextWeek() {

        manager.goToNextWeek();                                 // Tell manager to generate the next week.
        updateBank();                                           // Update the bankAccountField
        updateBusiness();                                       // Update the business fields
        updateWeek();  // Update the week counter.
        updatePurchaseOptions();                                // Update the purchase options. 
    }

    /**
     * Opens a dialog box detailing the name, description, and tags of the
     * currently selected business.
     */
    private void openBusinessDescription() {

        // Set var busName equal to the selected business' name (selected in the busList).
        String busName = manager.getBusinessName(BusinessSymbol.values()[busList.getSelectedIndex()]);

        // Create a dialog box with the following paramters:
        //**********************************************************************
        //showMessageDialog(Component parentComponent, Object message, String title, int messageType)
        //  parentComponent: the frame the dialog box is displayed in
        //      is set to 'this' so that the dialog box appears in and disables the stockScreen until resolved
        //  message: the string that is displayed by the dialog,
        //      is constructed as described below
        //  title: the title of the dialog box created, is set to "Business Details"
        //  messageType: this parameter determines the default icon that is displayed
        //      by the dialog box. The PLAIN_MESSAGE causes the dialog to have
        //      no symbol displayed with the message.
        
        JOptionPane.showMessageDialog(this,
            "" + busName + "\n" + currentBusinessCopy.getDescription()
                + "\n\nTags:\n           " + currentBusinessCopy.getTagsAsString(), "Business Details",
            JOptionPane.PLAIN_MESSAGE);

    }

    /**
     * Transitions the game to the currently existing (previous week's) newsScreen.
     */
    private void goToPreviousNewsScreen() {
        
        manager.goToPreviousNewsScreen();
        
    }
    

    /**
     * Checks whether the number of shares defined in the shareQuantity spinner
     * can be bought or sold and enables/disables the buy/sell purchase options
     * accordingly. I.e., if the shareQuantity displays more shares than can
     * be bought or sold, the buy and sell buttons are disabled respectively. 
     */
    private void updatePurchaseOptions() {
        
        // The selectedShares is the value held by the shareQuantity spinner.
        int selectedShares = (int) shareQuantity.getValue();
        
        // If the selectedShares is greater than the number
        // of shares the player owns in the selected business, disable the sell
        // button. Otherwise, enable the sell button. 
        if(selectedShares 
                > manager.getPlayerOwnedShares(BusinessSymbol.values()[busList.getSelectedIndex()])){
            
            sellSharesButton.setEnabled(false);
            
        }
        else {
        
            sellSharesButton.setEnabled(true);
        
        }
        
        // If the selectedShares is greater than the number
        // of shares available to purchase or if the total price of the
        // selected shares is greater than the value of the player's bank account
        // (total price determined by multiplying the price of shares by
        // the selectedShares) then disable the buy button. Otherwise
        // enable the buy button. 
        if( selectedShares > currentBusinessCopy.getSharesAvailable() 
                || (selectedShares * currentBusinessCopy.getPrice()) > manager.getPlayerBank()){
        
            buySharesButton.setEnabled(false);
            
        }
        else{
            
            buySharesButton.setEnabled(true);
            
        }

    }
 
    /**
     * Updates the business copy and the stock screen's business fields to 
     * accurately represent the latest information of the GameManager. 
     */
    private void updateBusiness() {
        
        // Update the business copy to be a copy of the latest version of the selected
        // business in the bus list. 
        currentBusinessCopy = manager.getBusinessCopy(BusinessSymbol.values()[busList.getSelectedIndex()]);
        
        // The price, previous price, owned shaers, and available shares are all
        // used for many calculations. They are accessed and stored in these local
        // variables for simplicity.
        int price = currentBusinessCopy.getPrice();
        int prevPrice = currentBusinessCopy.getPreviousPrice();
        int ownedShares = manager.getPlayerOwnedShares(BusinessSymbol.values()[busList.getSelectedIndex()]);
        int availShares = currentBusinessCopy.getSharesAvailable();
        
        // Set the value of all investments equal to the product of the number
        // of shares multiplied by the price of the shares.
        valueOfInvestmentsField.setText("$" + (price* ownedShares));
        
        // Set the price of the share field.
        currentSharePriceField.setText("$" + price);
        
        // Set the previous price of the share field.
        prevSharePriceField.setText("$" + prevPrice);
        
        // If the new share price is greater than the previous price, then the
        // previous price of a share, then set the change in price fields
        // with a positive sign +.
        if(price - prevPrice > 0){
            
            // Set the change in price by suptracting the lower, old price
            // from the new price to get a positive value equal to the
            // increase in price. 
            sharePriceChangeField.setText("+$" + (price - prevPrice));
            
            // Set the percent change by casting price to a float for percent
            //calculations. Then divide price by the previous price and multiply
            // by 100 to determine what percent (greater than 100) of the old
            // price the new price is. Then subtract 100 percent to determine
            // what percent increase the new price is over the old price.
            percentSharePriceChangeField.setText("+" + 
                ((int)(((float)price/prevPrice)*100) - 100) + "%");
            
        }
        
        // If the previous share price was less than or equal to the new share
        // price, display the change in price using a negative sign.
        else{
            
            // Set the change in price by suptracting the lower, new price
            // from the old price to get a positive value equal to the
            // decrease in price. 
            sharePriceChangeField.setText("-$" + (prevPrice - price));
            
            // Set the percent change by casting prevPrice to a float for percent
            //calculations. Then divide prevPrice by the price and multiply
            // by 100 to determine what percent (greater than 100) of the new
            // price the old price is. Then subtract this from 100 percent to determine
            // how much percentage was lost from the old price.
            percentSharePriceChangeField.setText("" + 
                (100-(int)(((float)prevPrice/price) * 100)) + "%");
        }
        
        // Set the number of owned shares.
        ownedSharesField.setText("" + ownedShares);
        
        // Set the number of available shares.
        availSharesField.setText("" + availShares);
     
        // Upon updating the business, make sure the purchase options are
        // valid for the newly updated prices.
        updatePurchaseOptions();
    }
 
    /**
     * Updates the user's bank account field to display the accurate value
     * in the game manager.
     */
    private void updateBank() {
        if(manager.getPlayerBank() < 0){
            
            bankAccountField.setText("-$" + (-1 * manager.getPlayerBank()));

        }
        else{
            bankAccountField.setText("$" + manager.getPlayerBank());
        }
    }
 
    /**
     * Update the week field to the accurate value stored in the game manager.
     */
    private void updateWeek(){
        weekNumberField.setText("" + (manager.getWeekNumber()+1));
    }
    
    /**
     * Initialize all Swing components on the JPanel to the correct position and
     * correct default values. The class' JPanel is set to a border layout,
     * so JPanels local to this method are used in each of the the border 
     * layout's sections to arrange each section's swing components. These
     * organizational JPanels are not referenced again, and so no class reference
     * to them are kept.
     */
    private void init(){
    
        setLayout(new BorderLayout());
        
        //*********************************************************
        // Set the Leftmost panel - the busListPanel
        //*********************************************************
        
        JPanel busListPanel = new JPanel(); // Create the organization panel.
        
        busListPanel.setLayout(new GridBagLayout()); // The organization panel
                                                     // is set to GridBagLayout
                                                     // so specific indexes can
                                                     // be defined.
                                                     
        // The GridBagLayout uses a GridBagConstraints object to set the
        // indexes. 
        GridBagConstraints constr = new GridBagConstraints();
        
        // Set the constrains to have insets (gaps) between components of 
        // size 4 on each side. 
        constr.insets = new Insets(4,4,4,4);
        
        //*********************************************************
        
        // Set the busList's label, align the text in the center of the label.
        busListLabel = new JLabel();
        busListLabel.setText("Businesses");
        busListLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        // Set the constraint's (busListLabel's) x and y positions, as well
        // as its height. Is the first element in the busListPanel.
        constr.gridx = 1;
        constr.gridy = 1;
        constr.gridheight = 1;
        
        // Add the busListLabel to the busListPaenl using above defined constriants. 
        busListPanel.add(busListLabel, constr);
        
        //*********************************************************
        
        // Create a new JList for busList using the array of strings
        // composed of the BusinessSymbol enum's string representation.
        busList = new JList<>(BusinessSymbol.values());
        
        // Set the busList to only allow one element to be selected at a time.
        busList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Assign the busList a black border.
        busList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // Make the default selected index the first index.
        busList.setSelectedIndex(0);
        
        // Assign the busList a ListSelectionListener that calls the updateBusiness()
        // method every time the selected index is changed.
        ListSelectionModel listSelectionModel = busList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
        
            @Override
            public void valueChanged(ListSelectionEvent e){ 
                updateBusiness();
            }
            
        });
        
        // Set the constraints for the busList to be aligned horizontally
        // with the label but below th label. The busList is thinner than
        // the label so it is centered below it.
        constr.gridx = 1;
        constr.gridy = 2;
        
        // Add the busList to the busListPanel
        busListPanel.add(busList, constr);
        
        //*********************************************************
        
        busDetailButton = new JButton("Details");
        busDetailButton.addActionListener((new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBusinessDescription();

            }
        }));
        
        constr.gridx = 1;
        constr.gridy=30;
        // Set the constraints' fill so that every component added from this
        // point will fill up remaining space in the panel if it is shorter
        // than the panel.
        constr.fill = GridBagConstraints.HORIZONTAL;
        
        busListPanel.add(busDetailButton, constr);
        
        //**********************************************************
        
        //Gives the busListPanel a beveled border, then adds it to the 
        //class JPanel in the LINE_START area, or the leftmost border.
        busListPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(busListPanel, BorderLayout.LINE_START);
        
        //**********************************************************
        // Set the Bottom panel - the transitionPanel
        //**********************************************************

        JPanel transitionPanel = new JPanel(new GridBagLayout());
        transitionPanel.setBackground(Color.WHITE);
      
        //**********************************************************
        
        //Create the exit button, set it to return to title screen when pressed
        //using an action listener.
        exitButton = new JButton("<html><center>Back to<br>Title</html>");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToTitleScreen();
            }
            
        });
        
        constr.gridx = 1;
        constr.gridy = 1;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        
        //These insets place space between the exit button and the other game transition buttons.
        constr.insets = new Insets(4, 4, 4, 310); 
        
        
        transitionPanel.add(exitButton, constr);
        
        //**********************************************************
        
        // Set the nextWeekButton to use an actionlistener to go to next week
        //when pressed.        
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
        
        // Return the constraints' insets to the previous 4 inset on each side
        constr.insets = new Insets(4, 4, 4, 4);
        
        transitionPanel.add(nextWeekButton, constr);
        
        //**********************************************************
        
        newsScreenButton = new JButton("<html><center>View<br>Previous Week</html>");
        newsScreenButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPreviousNewsScreen();
            }
            
        });

        constr.gridx = 20; // Here, the gridx is set smaller than the above next-week button's
                           // gridx, meaning the newsScreenButton apears before the
                           // above button. 
        constr.gridwidth = 20;
        
        transitionPanel.add(newsScreenButton, constr);

        //**********************************************************
        
        add(transitionPanel, BorderLayout.PAGE_END);
        
        //**********************************************************
        // Set the Top panel - the headerPanel
        //**********************************************************   
        
        
        JPanel headerPanel = new JPanel(new GridBagLayout());
        
        //**********************************************************
        
        // For spacing, the headerpanel contains two default JPanels, the
        // bankPanel and the weekPanel
        JPanel bankPanel = new JPanel();
        
        bankLabel = new JLabel();
        
        // In English, names ending with s typically don't show possesion with
        // an "'s", but rather an "'". This sets the bank label to show the
        // player's name using correct grammer. 
        if(manager.getPlayerName().endsWith("s")){
            bankLabel.setText("" + manager.getPlayerName() + "' Bank Account:");
        }
        else {
            bankLabel.setText("" + manager.getPlayerName() + "'s Bank Account:");
        }   

        bankLabel.setAlignmentX(RIGHT_ALIGNMENT);
        
        bankPanel.add(bankLabel);
        
        bankAccountField = new JTextField();
        bankAccountField.setBackground(Color.WHITE);
        bankAccountField.setEditable(false);    //All fields here are meant to
                                                //display information only, and
                                                //are not editable.
        bankAccountField.setColumns(10);        
        bankAccountField.setHorizontalAlignment(SwingConstants.RIGHT);
        
        bankPanel.add(bankAccountField);

        //**********************************************************
        
        JPanel weekPanel = new JPanel();
        
        weekLabel = new JLabel();
        weekLabel.setText("Current Week:");
        weekPanel.add(weekLabel);
        
        weekNumberField = new JTextField();
        weekNumberField.setBackground(Color.white);
        weekNumberField.setEditable(false);
        weekNumberField.setColumns(4);
        weekNumberField.setHorizontalAlignment(SwingConstants.RIGHT);
        
        weekPanel.add(weekNumberField);
        
        constr.gridx = 0;
        constr.gridy = 1;
        constr.gridwidth = 200;
        constr.insets = new Insets(4, 4, 4, 150); // Constraints are exagerated 
                                                  // to force space between the 
                                                  // bank panel and week panel
        
        headerPanel.add(bankPanel, constr);
        
        constr.gridx = 200;
        constr.gridy = 1;
        constr.gridwidth = 2;
        constr.gridheight = 1;
        constr.insets = new Insets(4, 4, 4, 4);   //Constrains are returned to normal.
        
        headerPanel.add(weekPanel, constr);
        
        headerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(headerPanel, BorderLayout.PAGE_START);
        
        //**********************************************************
        // Set the Center panel - the stockPanel
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
        
        constr.gridx = 5;
        
        stockPanel.add(valueOfInvestmentsField, constr);
                
        //**********************************************************  
        
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

        
        constr.gridx = 5;
        
        stockPanel.add(currentSharePriceField, constr);
        
        //**********************************************************  
        
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
        
        constr.gridx = 5;
        
        stockPanel.add(prevSharePriceField, constr);
        
        //**********************************************************  
        
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
        
        constr.gridx = 5;
        
        stockPanel.add(sharePriceChangeField, constr);
        
        //**********************************************************  

        
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

        constr.gridx = 5;
        
        stockPanel.add(percentSharePriceChangeField, constr);
        
        //**********************************************************  

        
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
                
        constr.gridx = 5;
        
        stockPanel.add(ownedSharesField, constr);
        
        //**********************************************************  
        
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
        
        constr.gridx = 5;
        
        stockPanel.add(availSharesField, constr);
        
        //**********************************************************  
        
        add(stockPanel, BorderLayout.CENTER);
        
        //**********************************************************
        // Set the Rightmost panel - the purchaseOptionsPanel
        //********************************************************** 
        
        JPanel purchaseOptionsPanel = new JPanel(new GridBagLayout());
        
        //Creates a sell button that calls the sellShare method when pressed.
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
        
        //********************************************************** 
        
        // The shareQuantity spinner uses a changeListener to call the 
        // updatePurchaseOptions method whenever the value is changed.
        // The spinner is bound by having a minimum value of 1 (default value)
        // and a maximum value of 100. The spinner buttons update the listed
        // value by 1, but a value can be typed. If an invalid value is typed,
        // the typed value is ignored.
        shareQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        shareQuantity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                
                updatePurchaseOptions();
                
            }
        
        });
        
        constr.gridx = 1;
        constr.gridy = 3;
        constr.gridwidth = 1;
        constr.gridheight = 1;
        
        purchaseOptionsPanel.add(shareQuantity, constr);
        
        //********************************************************** 
        
        //Creates a sell button that calls the sellShare method when pressed.
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
        
        //********************************************************** 

        purchaseOptionsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        add(purchaseOptionsPanel, BorderLayout.LINE_END);
        
        //********************************************************** 
        // Upon finishing all the labels and fields, make sure they
        // render properly and then update all of the fields. 
        //********************************************************** 
        
        revalidate();
        repaint();
        
        updateBusiness();
        updateWeek();
        updateBank();


    }
    
}
