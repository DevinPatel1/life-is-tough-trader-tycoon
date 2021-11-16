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
 * Symbols will be used as an indexing method for each of the
 *     businesses in GameManager.
 * 
 * @author Devin Patel
 * @author Luke Farris
 * @author Dominic Kenyon
 * @author Corey Clayborn
 * @author Ben Johnson
 */
public enum BusinessSymbol
{
    FRM(0), // FarmersMart - Grocery
    FUB(1), // Fuber - Food Delivery
    BUS(2), // Blockbuster - Streaming Platform
    WGH(3), // Winghut - Sports Bar
    UPD(4), // United Postal Boys - Shipping
    BAY(5); // iBay - ECommerce

    public final int index;

    private BusinessSymbol(int value)
    {
        this.index = value;
    }
}
