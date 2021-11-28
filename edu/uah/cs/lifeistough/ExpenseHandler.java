/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

import java.util.ArrayList;

/**
 * Stores all expenses and randomly chooses an expense when the next week operation is initiated.
 * 
 * @author Corey Clayborn
 */
public class ExpenseHandler {
    
    private ArrayList<Expense> expenses = new ArrayList<>();
    
    
    /**
     * Constructs each expense and their respective bounds for number generation.
     * @param aDifficulty Easy makes expenses smaller, Hard makes expenses larger.
     */
    public ExpenseHandler(Difficulties aDifficulty){
        
        float howdifficult;
        
        if (aDifficulty == Difficulties.EASY)
            howdifficult = (float) 0.5;
         else if(aDifficulty == Difficulties.HARD)
            howdifficult = (float) 1.5;
        else
            howdifficult = (float) 1.0;
        
        // add the expenses here what ever we decide 
        addTemplateExpense(howdifficult);
        
        
        
    }
    
    /**
     * Randomly selects an expense that will be displayed at the end of the week.
     * @return A randomly chosen expense
     */
    public Expense generateExpense(){
        int theExpense = GameManager.RNG.nextInt(expenses.size());
        
        return expenses.get(theExpense);
    }
    


    // Template Expense - The numbers -10 and -1 are base bounds for rng
    private void addTemplateExpense(float factor){
        String reasonForExpense = "Expense name";
        
        int lowerBound = Math.round(-50 * factor);
        int upperBound = Math.round(-25 * factor);
        
        Expense expense = new Expense(reasonForExpense,lowerBound,upperBound);
        expenses.add(expense);
    }
    
    
    
    
    
    
}