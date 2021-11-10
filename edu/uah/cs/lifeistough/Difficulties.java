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
 * When the player starts the game, they must choose a
 *     difficulty. This enum describes which difficulty
 *     was chosen so that each class that needs to know
 *     the difficulty can be constructed accordingly.
 * 
 * @author CS 321 - Team 4
 */
public enum Difficulties
{
    EASY,
    NORMAL,
    HARD;
}