/**
 * Event represents the random event that will affect businesses at the end of each week.<p>
 * Each event has a headline that describes each event and clues the player on which businesses will be affected.<p>
 * Each event will have a set of tags that correlate to what businesses will be affected.<p>
 * Each event will generate a random value that will alter each businesses' fortune value.<p>
 * 
 * @author Devin Patel
 */
public class NewsEvent
{
    // Name of event; Headline to be shown to player
    private String name;
    private String headline;

    // Upper and lower bounds for RNG
    private int upperBound;
    private int lowerBound;

    // Tags corresponding to this Event
    private Tags[] tags;


    /**
     * Constructs each event.<p>
     * Lower bound is inclusive, upper bound is exclusive.<p>
     * Please note that the lower bound must be numerically less than the upper bound.<p>
     *      i.e. lowerBound = 2, upperBound = 6 <p>
     *      i.e. lowerBound = -6, upperBound = -2
     * 
     * @param aName The name of the random event
     * @param aHeadline The description of the event
     * @param aLower The lower bound for number generation (inclusive)
     * @param aUpper The upper bound for number generation (exclusive)
     * @param aTags An array of tags that the event will affect.
    */
    public NewsEvent( String aName, String aHeadline,
                  int aLower, int aUpper,
                  Tags[] aTags
                )
    {
        this.name = aName;
        this.headline = aHeadline;
        this.lowerBound = aLower;
        this.upperBound = aUpper;
        this.tags = aTags;
    }


    /**
     * Generates the fortune modifier value between the bounds specified.
     * @return The modifier value
     */
    public int generateModifier()
    {
        int modifier = GameManager.RNG.nextInt(upperBound-lowerBound) + lowerBound;
        return modifier;
    }


    /**
     * Accesses the name of the event.
     * @return Name of the event
     */
    public String getName() { return name; }

    /**
     * Accesses the description of the event.
     * @return Headline of the event
     */
    public String getHeadline() { return headline; }

    /**
     * Accesses the affected tags of the event.
     * @return Tags of the event
     */
    public Tags[] getTags() { return tags; }
}