public class Business {

    public Business(String aName, String desc, int firstPrice, int totalShares, 
            BusinessSymbol aSymbol, Tags[] aTags){
        name = aName;
        description = desc;
        currentPrice = firstPrice;
        previousPrice = firstPrice;
        sharesAvailable = totalShares;
        symbol = aSymbol;
        tags = aTags;
    }
    
    public int getPrice(){
        return currentPrice;
    }
    
    public int getPreviousPrice(){
        return previousPrice;
    }
    
    public int getSharesAvailable(){
        return sharesAvailable;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Tags[] getTags(){
        return tags;
    }
    
    public BusinessSymbol getSymbol(){
        return symbol;
    }
    
    public void generateNewPrice(){
        previousPrice = currentPrice;
        currentPrice = lowerBound + GameMaganger.RNG.nextInt(upperBound - lowerBound);
    }
    
    public void updateFortune(float fortuneModifier){
        fortune += fortuneModifier;
        
        upperBound += fortune;
        lowerBound += fortune;
    }
    
    public void changeSharesAvailable(int changeOfShares){
        sharesAvailable += changeOfShares;
    }

    private int currentPrice;
    
    private int previousPrice;
    
    private int sharesAvailable;
    
    private int upperBound;
    
    private int lowerBound;
    
    private int fortune;
    
    private final String name;
    
    private final String description;
    
    private final BusinessSymbol symbol;
    
    private final Tags[] tags;
}
