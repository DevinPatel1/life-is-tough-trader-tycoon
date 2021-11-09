/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uah.expense;

/**
 *
 * @author Corey Clayborn
 */
public class Expense {
    
       private String reasonForExpense;
    private int upperBound;
    private int lowerBound;
    
    
    public void Expense(String aReason, int aUpper, int aLower){
        
        this.reasonForExpense = aReason;
        this.upperBound = aUpper;
        this.lowerBound = aLower;
    }
    
    public int generateExpense(){
        // i dont realy know what we want to return so this is just a placeholder 
        int generate = GameManager.RNG.nextInt(upperBound-lowerBound) + lowerBound;;
        
        return generate;
    }
    
    public String getReason(){
    return reasonForExpense;
}
}

