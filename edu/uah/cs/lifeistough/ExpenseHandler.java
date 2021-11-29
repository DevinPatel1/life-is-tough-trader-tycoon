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

        rent(howdifficult);
        subscriptions(howdifficult);
        subscriptions(howdifficult);
        groceries(howdifficult);
        SpeedingTicket(howdifficult);
         Doctorfee(howdifficult);
         hospitalfees(howdifficult);
          schoolfees(howdifficult);
    }
    
    /**
     * Randomly selects an expense that will be displayed at the end of the week.
     * @return A randomly chosen expense
     */
    public Expense generateExpense(){
        int theExpense = GameManager.RNG.nextInt(expenses.size());
        
        return expenses.get(theExpense);
    }
    

     //rent - The numbers -10 and -1 are base bounds for rng
    private void rent(float factor){
        String reasonForExpense = "it is time to pay rent!";
        
        int lowerBound = Math.round(-80 * factor);
        int upperBound = Math.round(-50 * factor);
        
        Expense expense = new Expense(reasonForExpense,lowerBound,upperBound);
        expenses.add(expense);
    }
    
     //subscriptions - The numbers -10 and -1 are base bounds for rng
    private void subscriptions(float factor){
        String reasonForExpense = "it's time to pay for those subscriptions you forgot about";
        
        int lowerBound = Math.round(-20 * factor);
        int upperBound = Math.round(-5 * factor);
        
        Expense expense = new Expense(reasonForExpense,lowerBound,upperBound);
        expenses.add(expense);
    }
    //groceries - The numbers -10 and -1 are base bounds for rng
    private void groceries(float factor){
        String reasonForExpense = "Your Fridge is empty looks like you need to go to the store!";
        
        int lowerBound = Math.round(-50 * factor);
        int upperBound = Math.round(-20 * factor);
        
        Expense expense = new Expense(reasonForExpense,lowerBound,upperBound);
        expenses.add(expense);
    }
    //SpeedingTicket - The numbers -10 and -1 are base bounds for rng
       private void SpeedingTicket(float factor){
        String reasonForExpense = "you recieved a speeding fine SLOW DOWN!";
        
        int lowerBound = Math.round(-40 * factor);
        int upperBound = Math.round(-20 * factor);
        
        Expense expense = new Expense(reasonForExpense,lowerBound,upperBound);
        expenses.add(expense);
    }
       //Doctorfee - The numbers -10 and -1 are base bounds for rng
    private void Doctorfee(float factor){
        String reasonForExpense = "its time for your yearly chekup!";
        
        int lowerBound = Math.round(-100 * factor);
        int upperBound = Math.round(-70 * factor);
        
        Expense expense = new Expense(reasonForExpense,lowerBound,upperBound);
        expenses.add(expense);
    }
    //hospitalfees - The numbers -10 and -1 are base bounds for rng
    private void hospitalfees(float factor){
        String reasonForExpense = "the hospital sent you a bill for that flu shot.";
        
        int lowerBound = Math.round(-30 * factor);
        int upperBound = Math.round(-20 * factor);
        
        Expense expense = new Expense(reasonForExpense,lowerBound,upperBound);
        expenses.add(expense);
    }
    //schoolfees - The numbers -10 and -1 are base bounds for rng
    private void schoolfees(float factor){
        String reasonForExpense = "its time to pay for those student loans again!";
        
        int lowerBound = Math.round(-80 * factor);
        int upperBound = Math.round(-50 * factor);
        
        Expense expense = new Expense(reasonForExpense,lowerBound,upperBound);
        expenses.add(expense);
    }
}