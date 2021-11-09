/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uah.expense;
import java.util.ArrayList;
/**
 *
 * @author Corey Clayborn
 */
public class ExpenseHandler {
    
    private ArrayList<Expense> expenses = new ArrayList<>();
    
    
    
    public void ExpenseHandler(Difficulties aDifficulty){
        
        float howdifficult;
        
        if (aDifficulty == Difficulties.EASY)
            howdifficult = (float)0.5;
         else if(aDifficulty == Difficulties.HARD)
            howdifficult = (float) 1.5;
        else
            howdifficult = (float) 1.0;
        
        // add the expenses here what ever we decide 
        addTemplateExpense(howdifficult);
        
        
        
    }
    
    public Expense generateExpense(){
        int theExpense = GameManager.RNG.nextInt(expenses.size());
        
        return expenses.get(theExpense);
    }
    
    private void addTemplateExpense(float factor){
        String reasonForExpense = "temp name";
        
         int lowerBound = Math.round(-10 * factor);
        int upperBound = Math.round(-1 * factor);
        
        Expense expense = new Expense(reasonForExpense,upperBound,lowerBound);
        expenses.add(expense);
    }
    
    
    
    
    
    
}