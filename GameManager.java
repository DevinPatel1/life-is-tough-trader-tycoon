/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksimfinal;

import java.util.Random;

/**
 *
 * @author Luke Farris
 */
public class GameManager {
    
    private Player player;
    private Business[] businesses;
    private Difficulties difficulty;
    
    private ExpenseHandler expenses;
    private EventsHandler events;
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
        return businesses[index].clone();
    }
    
    public int getWeekNumber() {
        return weekNumber;
    }
    
    public void buyShare(BusinessSymbol index, int numberOfShares, int cost) {
        poop
    }
    
    public void sellShare(BusinessSymbol index, int numberOfShares, int cost) {
        dood
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
    
    public void endGame() {
        
        
        
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
    
    public Event generateEvent() {
        
        return EventHandler.generateEvent();
        
    }
    
    public Expense generateExpense() {
        
        return ExpenseHandler.generateExpense();
        
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
    
    private void initBusinesses() {
        
        
        //do business generation
        
        
    }

        
}
