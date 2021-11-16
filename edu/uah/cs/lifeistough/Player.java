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
     * @param aName
     * @param aDifficulty
     * @param numberOfBusinesses
     */
    public Player(String aName, Difficulties aDifficulty, int numberOfBusinesses) {
        
        aName = playerName;
        ownedShares = new int[numberOfBusinesses];

        int startingBalance = 1000;
        
        if(aDifficulty == Difficulties.EASY) {
            bank = (int) (startingBalance * 1.25);
        }
        else if(aDifficulty == Difficulties.HARD) {
            bank = (int) (startingBalance * 0.75);
        }
        else {
            bank = startingBalance;
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
    private int bank;
    private String playerName;
    private int[] ownedShares;
    //--------------------------------------------------------------------------
}
