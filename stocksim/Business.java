/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksim;

import java.util.Random;

/**
 *
 * @author luked
 */
public class Business {
    
    private Tags[] marketTags;
    
    private String name;
    
    private String code;
    
    private String description;
    
    private int stockCost; //stored as 1000 of cents, gets rounded up to highest 
                           //10 when purchasing
    private int previousStockCost;
    
    private int numberOfStocks;
    
    private int numberOfOwnedStocks;
    
    private int numberOfAvailableStocks;
    
    private int fortune;
    
    private static Random RNG = new Random(System.nanoTime());
    
    private void setTags(Tags ... tags){
        marketTags = tags;
    }
    
    public Business(){
        char first = (char) ( RNG.nextInt(9) + 65);
        char fst = (char) (RNG.nextInt(25) + 65);
        char firt = (char) (RNG.nextInt(9) + 65);
        String name = ""+first+fst+firt;
        code = name;
        
        stockCost = RNG.nextInt(99999) + 50000;
        previousStockCost = stockCost - RNG.nextInt(99999);
        previousStockCost += RNG.nextInt(99999);
    }
    
    public Business(String name, String code, String description, 
            int numberOfStocks, int fortune, Tags... tags){
        this.name = name;
        this.code = code;
        this.description = description;
        this.numberOfStocks = numberOfStocks;
        this.fortune = fortune;
        marketTags = tags;
        
    }
    
    public Tags[] getMarketTags() {
        return marketTags.clone();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getStockCost() {
        return stockCost;
    }

    public int getPreviousStockCost() {
        return previousStockCost;
    }

    public int getNumberOfStocks() {
        return numberOfStocks;
    }

    public int getNumberOfOwnedStocks() {
        return numberOfOwnedStocks;
    }

    public int getNumberOfAvailableStocks() {
        return numberOfAvailableStocks;
    }

    public int getFortune() {
        return fortune;
    }
    
    public void addFortune(int luckIncrement){
        fortune += luckIncrement;
    }
    
    public void generateStockFluctuation() {
        
    }
    
    
}
