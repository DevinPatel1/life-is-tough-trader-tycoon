/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

/**
 * Player keeps track of the player's bank account, name, and the amount
 *     of shares owned.
 * 
 * @author Ben Johnson
 */
public class Player {
    
    /**
     * Constructs the player's bank account. The initial amount is 
     * @param aName stores the name of the player
     * @param aDifficulty stores the difficulty the player choose
     * @param numberOfBusinesses stores an int of how many businesses we are using
     */
    public Player(String aName, Difficulties aDifficulty, int numberOfBusinesses) {
        
        playerName = aName; //store param aName into variable playerName
        ownedShares = new int[numberOfBusinesses]; //intialize ownedShares with an array of length of numberOfBusinesses

        // Initializes the player's bank account based on the difficulty
        int startingBalance = 1000; // will be increased on Easy, stay the same on Medium, and decrease on Hard
        
        if(aDifficulty == Difficulties.EASY) {
            bank = (int) (startingBalance * 1.25); //easy
        }
        else if(aDifficulty == Difficulties.HARD) {
            bank = (int) (startingBalance * 0.75); //hard
        }
        else {
            bank = startingBalance; //medium
        }
    }

    //--------------------------------------------------------------------------
    

    
    // Getter Methods
    //--------------------------------------------------------------------------

    /**
     * Accesses the name that the player enters on the title screen.
     * @return Name of the player
     */
    public String getPlayerName() {
        return playerName;
    }
    

    /**
     * Accesses the amount of money the player has in their bank account.
     * @return Player's balance
     */
    public int getBank() {
        return bank;
    }


    /**
     * Accesses the amount of shares the player owns of a particular business.
     * @param stockSymbol Stock symbol of the business in question
     * @return Amount of stocks owned by the player
     */
    public int getBusinessShares(BusinessSymbol stockSymbol) {
        return ownedShares[stockSymbol.index];
    }
    //--------------------------------------------------------------------------
    


    //Updating data methods
    //--------------------------------------------------------------------------

    /**
     * Adds or subtracts the balance of the player's bank account.
     * @param money The amount to be applied. Can be negative or positive.
     */
    public void updateBank(int money) {
        bank += money;
    }
    
    
    /**
     * Adds or subtracts the owned shares based on whether the player buys or sells.
     * @param stockSymbol Stock symbol of business in question
     * @param shares Positive if buying shares, Negative if selling shares
     */
    public void updateShares(BusinessSymbol stockSymbol, int shares) {
        ownedShares[stockSymbol.index] += shares;
    }
    //--------------------------------------------------------------------------
    

    
    //data
    //--------------------------------------------------------------------------
    private int bank; //balance of bank account
    private String playerName;  //string the player entered
    private int[] ownedShares; //how many shares of each business the player has
    //--------------------------------------------------------------------------
}
