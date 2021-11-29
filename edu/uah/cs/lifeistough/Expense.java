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
 * Expenses occur when the player progresses to the next week.
 * They are a deduction on the player's bank account as a means
 *     to emulate the player's cost of living.<p>
 * Each expense will vary in its severity, as in each expense
 *     will have its own bounds for random number generation.<p>
 * Each expense will also provide its reason
 *     (e.g. utility bills, hospital fees, etc.).
 * 
 * @author Corey Clayborn
 */
public class Expense {
    
    private String reasonForExpense;
    private int upperBound;
    private int lowerBound;
    
    
    /**
     * Constructs the expense.
     * Lower bound is inclusive, upper bound is exclusive.<p>
     * Please note that the lower bound must be numerically less than the upper bound.<p>
     *      e.g. lowerBound = 2, upperBound = 6 <p>
     *      e.g. lowerBound = -6, upperBound = -2
     * 
     * @param aReason Reason for the expense
     * @param aLower Lower bound for number generation (inclusive)
     * @param aUpper Upper bound for number generation (exclusive)
     */
    public Expense(String aReason, int aLower, int aUpper){
        
        this.reasonForExpense = aReason;
        this.upperBound = aUpper;
        this.lowerBound = aLower;
    }
    

    /**
     * Generates a value that will be subtracted from the player's bank account.
     * @return The total amount in bills the player must pay.
     */
    public int generateExpense(){
        int generate = GameManager.RNG.nextInt(upperBound-lowerBound) + lowerBound;
        
        return generate;
    }
    

    /**
     * Accesses the reason for the expense.
     * @return Reason for the expense
     */
    public String getReason(){
    return reasonForExpense;
    }
}

