/*
* Devin Patel, Luke Farris, Dominic Kenyon, Corey Clayborn, Ben Johnson
* Team 4
* CS 321 - 02
* Intro to OOP - Java
* December 1, 2021
* Description: See Javadoc comment below
*/

package edu.uah.cs.lifeistough;

import java.util.Random;

/**
 * Game Manager is the interface between the GUI system and the game logic system.
 * 
 * @author Luke Farris
 */
public class GameManager {
    
    private Player player;
    private Business[] businesses;
    private Difficulties difficulty;
    
    private ExpenseHandler expenses;
    private NewsEventHandler events;
    private GameWindow window;
    
    private StockScreen stockScreen;
    private NewsScreen newsScreen;
    private TitleScreen titleScreen;
    
    private GameState currentState;
    
    private int weekNumber;
    private int weeksBelowZero;
    
    public static final Random RNG = new Random();
    
    GameManager(GameWindow aWindow) {      
    }
    
    public String getPlayerName() {
        return player.getPlayerName();
    }
    
    public int getPlayerBank() {
        return player.getBank();
    }
    
    public int getPlayerOwnedShares(BusinessSymbol index){
        return player.getBusinessShares(index);
    }
    
    public int setDifficulty(Difficulties aDifficulty){
        difficulty = aDifficulty;
    }
    
    public Business getBusinessCopy(BusinessSymbol index) {
        return businesses[index.index].clone();
    }
    
    public int getWeekNumber() {
        return weekNumber;
    }
    
    public void buyShare(BusinessSymbol index, int numberOfShares, int cost) {
        
    }
    
    public void sellShare(BusinessSymbol index, int numberOfShares, int cost) {
        
    }
    
    public int checkWeeksBelowZero() {
        return weeksBelowZero;
    }
    
    public String[] getListOfBusinessCodes() {
        
        String[] list = new String[businesses.length];
        
        for(int i = 0; i<businesses.length; i++){
            
            list[i] = businesses[i].getSymbol();
            
        }
        
        return list;
        
    }
    
    public void startGame() {
        
        //Create Player Object, do initial generations
        
        currentState = GameState.STOCK_SCREEN;
        updateScreen();
        
        
    }
    
    public void goToNewsScreen() {
        
        //No logic, 
        
        currentState = GameState.NEWS_SCREEN;
        updateScreen();
    }
    
    public void goToStockScreen() {
        
        //No logic
        
        currentState = GameState.STOCK_SCREEN;
        updateScreen();
    }
    
    public void goToNextWeek() {
        
        if(getPlayerBank() < 0){
            weeksBelowZero++;
        }
        
        if(weeksBelowZero >= 3) {
            endGame();
        }
        
        generateBusinessWeeklyUpdate();
                        
        currentState = GameState.NEWS_SCREEN;
        updateScreen();
    }
    
    public void returnToTitleScreen() {
        
        //delete player?
        
        currentState = GameState.TITLE_SCREEN;
        updateScreen();
    }
    
    public void endGame() {
        
        //display end game
    }


    private void generateBusinessWeeklyUpdate() {
        
        //Expense expenseOne, expense two = eventHandler.generateEvent
        //Set Week Screen parameters using expense
        //Set week screen playerbank parameter 
        //Modify player bank
        //
        //Event generation
        //Set titleScreen parameters using event
        //for all three events
        //  for loop of all business
        //      if business.getTag = currentevent tag
        //
        
        currentState = GameState.NEWS_SCREEN;
        updateScreen();
    }
    
    public NewsEvent generateEvent() {
        
        return NewsEventHandler.generateEvent();
        
    }
    
    public Expense generateExpense() {
        
        return expenses.generateExpense();
        
    }
    
    private void updateScreen() {
        
        switch(currentState){
            case TITLE_SCREEN:
                window.setContentPane(titleScreen);
                window.pack();
                break;
            case STOCK_SCREEN:
                window.setContentPane(stockScreen);
                window.pack();
                break;
            case NEWS_SCREEN:
                window.setContentPane(weekScreen);
                break;
            default:
        }
                
    }
    
    private void initBusinesses()
    {
        // Business 1 - Farmers Mart (FRM)
        String name = "Farmers Mart";
        String description = "A farmer's market where people congregate and buy groceries and home goods.";
        int initialPrice = 100;
        int totalShares = 1000;
        Tags[] tags1 = {Tags.FOOD, Tags.SOCIAL, Tags.DOMESTIC_GOODS};
        businesses[BusinessSymbol.FRM.index] = new Business(name, description, initialPrice, totalShares, tags1);

        // Business 2 - Fuber (FUB)
        name = "Fuber";
        description = "An online food delivery service that relies on crowd-sourced drivers.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags2 = {Tags.FOOD, Tags.AUTOMOTIVE, Tags.INTERNET};
        businesses[BusinessSymbol.FUB.index] = new Business(name, description, initialPrice, totalShares, tags2);

        // Business 3 - Blockbuster (BUS)
        name = "Blockbuster";
        description = "A multinational online media streaming service.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags3 = {Tags.INTERNATIONAL, Tags.INTERNET};
        businesses[BusinessSymbol.BUS.index] = new Business(name, description, initialPrice, totalShares, tags3);

        // Business 4 - Winghut (WGH)
        name = "Winghut";
        description = "A sports bar chain that hosts large gatherings and serves food.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags4 = {Tags.FOOD, Tags.SOCIAL};
        businesses[BusinessSymbol.WGH.index] = new Business(name, description, initialPrice, totalShares, tags4);

        // Business 5 - United Postal Dudes (UPD)
        name = "United Postal Dudes";
        description = "A shipping company that relies on trucks and international travel.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags5 = {Tags.INTERNATIONAL, Tags.AUTOMOTIVE};
        businesses[BusinessSymbol.UPD.index] = new Business(name, description, initialPrice, totalShares, tags5);

        // Business 6 - iBay (BAY)
        name = "iBay";
        description = "An online multinational eCommerce platform that allows consumers to buy goods.";
        initialPrice = 100;
        totalShares = 1000;
        Tags[] tags6 = {Tags.INTERNATIONAL, Tags.INTERNET, Tags.DOMESTIC_GOODS};
        businesses[BusinessSymbol.BAY.index] = new Business(name, description, initialPrice, totalShares, tags6);
    }
        
}
