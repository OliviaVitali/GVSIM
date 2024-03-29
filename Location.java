import javax.swing.*;
import java.util.Map;


/***********************************************************************************************************************
 * generic Location class with required variables and helper methods
 * All instantiated locations must extend Location
 * @author Olivia Vitali
 **********************************************************************************************************************/
public abstract class Location {
    //contains all characters in location, relates user command
    protected Map<String, Character> listOfCharacters;

    /** Name of the location */
    protected String name;

    /** test that displays when area is loaded */
    protected String flavorText;

    /** image related to the location */
    protected ImageIcon image;

    /** list of places you can go from your location */
    protected String[] listOfTraversable;

    /** String refers to the user command.  Event is related event */
    protected Map<String, Event> mapOfEvents;

    /** helper method to create events in the area (excluding fights)*/
    protected abstract void createEventList();

    /**helper method that returns an event when passed a user command*/
    protected abstract Event getEvent(String userCommand);

    /** helper method to create list of traversable places */
    protected abstract void createMap();

    /** helper method to return list of traversable places */
    protected abstract String getMap();

    /** method to handle fighting */
    protected abstract int Fight(Character m,Character e,String str,int movepool);

    /** helper for constructor  to make characters (excluding player)*/
    protected abstract void createCharList();

    /** sets flavor text for area */
    protected abstract void setDescription();

    /** gets the string that allows fight */
    protected abstract String getFightableString();

    /** sets the string that allows fight */
    protected abstract void setFightableString(String str);

    /** gets the GameOver boolean from location to GamePanel */
    protected abstract boolean getGameOver();
}

//end location class