import java.util.HashMap;
import javax.swing.*;

/***********************************************************************************************************************
 * Gate is a traversable location in the game.  The player's string command is passed in and is the most often
 *  referenced when deciding output. This structure is technically known as Transformational Link.
 *  @author Olivia Vitali
 *  @author Cameron Shearer
 *  @author Riley Hernandez
 *  @version 1
 **********************************************************************************************************************/
public class Gate extends Location {
    /** Character you can fight in this area */
    public String fightableString;

    /** Image for the GUI in this area */
    ImageIcon icon = new ImageIcon(getClass().getResource("TransformationLink15.png"));

    /*******************************************************************************************************************
     * Constructor for class.  Creates characters, sets area picture, description for gate, map of possible places to go
     * to, and events related to the flavor text.
     ******************************************************************************************************************/
    public Gate() {
        setDescription();
        setImage();
        createMap();
        createCharList();
        createEventList();
        fightableString = "TALK TO T HAAS"; //fight t haas
    }

    /*******************************************************************************************************************
     * Helper method to create list of events.  An event is triggered by the string (user input) and will return a
     * string intended to be displayed on the GUI along with other information in the event like the NPC or the
     * secondary location the user is traveling to
     ******************************************************************************************************************/
    @Override
    protected void createEventList() {
        //string is user command, event is the event to return
        mapOfEvents = new HashMap<String, Event>();
        mapOfEvents.put("LOOK", new Event("LOOK",
                "\nIt's Saturday and you've been wandering " +
                        "around campus because...let's face it, you " +
                        "didn't really have anything else to do.\n" +
                        "By mistake, you've wandered under a large " +
                        "blue sculpture only to remember that you" +
                        " weren't supposed to walk under that.\nThe" +
                        " world shimmers and you see T Haas ahead, " +
                        "handing out ice cream.  Behind you is a bridge."));
        //event that changes location to bridge from gate
        mapOfEvents.put("GO TO BRIDGE", new Event("GO TO BRIDGE", "\nYou walk towards the bridge"));
        mapOfEvents.get("GO TO BRIDGE").setLocation("BRIDGE");

        //talking event for t haas
        mapOfEvents.put("TALK TO T HAAS", new Event("TALK TO T HAAS", "\n You walk up to a man at an ice cream cart." +
                "\n He smiles at you."));
        mapOfEvents.get("TALK TO T HAAS").setNpc(listOfCharacters.get("TALK TO T HAAS"));
        mapOfEvents.get("TALK TO T HAAS").setName("TALK TO T HAAS");
        //mapOfEvents.get("TALK TO T HAAS").setFlavorText(listOfCharacters.get("TALK TO T HAAS").);
    }

    /*******************************************************************************************************************
     * getter method to return event based on user input
     * @param userCommand the string input by the user to trigger an event
     * @return the event associated with the given user command
     ******************************************************************************************************************/
    protected Event getEvent(String userCommand) {
        return mapOfEvents.get(userCommand);
    }

    /*******************************************************************************************************************
     * helper method for debugging.  Lists possible locations the user can travel to.  Note: this doesn't correspond to
     * an event creation.  Actual travel events are created in createEvents
     ******************************************************************************************************************/
    @Override
    protected void createMap() {
        listOfTraversable = new String[1];
        listOfTraversable[0] = "BRIDGE";

    }

    /*******************************************************************************************************************
     * Has to be present due to inheritance. Not used in this context.
     * @param m initiating character
     * @param e accepting character
     * @param str action being performed by character
     * @param movepool possible moves in this fight
     * @return 0 for testing purposes.
     ******************************************************************************************************************/
    @Override
    protected int Fight(Character m, Character e, String str,int movepool){
        return 0;
    }

    /*******************************************************************************************************************
     * Uses a loop to compile all locations user can access from this area into a string.
     * @return list of traversable locations
     ******************************************************************************************************************/
    @Override
    protected String getMap() {
        String temp = "";
        for (int i = 0; i < listOfTraversable.length; i++){
            if (listOfTraversable[i] != null)
                temp += listOfTraversable[i];
        }
        return temp;
    }

    /*******************************************************************************************************************
     * Creates the list of characters for this area.
     ******************************************************************************************************************/
    @Override
    protected void createCharList() {
        listOfCharacters = new HashMap<String, Character>();
        listOfCharacters.put("TALK TO T HAAS", new Character());
        listOfCharacters.get("TALK TO T HAAS").setCharName("T Haas");
        listOfCharacters.get("TALK TO T HAAS").setSpeechOptions(
                "HELLO", "world");
        listOfCharacters.get("TALK TO T HAAS").setSpeechOptions(
                "YES", "Looks like I'm out. Sorry.");
        listOfCharacters.get("TALK TO T HAAS").setSpeechOptions(
                "NO", "That's ok.");
        listOfCharacters.get("TALK TO T HAAS").setSpeechOptions("TALK TO T HAAS",
                "Hello stranger.  Would you like to some ice cream?\n" +
                        "You look lost in this world.  This is USVG.  " +
                        "The University State of Valley Grand.");
        listOfCharacters.get("TALK TO T HAAS").setSpeechOptions("BYE", "See you later!");
        listOfCharacters.get("TALK TO T HAAS").setCharStats(new Stats(10, 10, 10, 40));
    }

    /*******************************************************************************************************************
     * Sets the flavortext description for this area when user LOOKs.
     ******************************************************************************************************************/
    @Override
    protected void setDescription() {
        name = "GATE";
        flavorText = "You look around and find yourself at the beginning again.";
    }

    /*******************************************************************************************************************
     * Sets the image on the panel to the one for this area.
     ******************************************************************************************************************/
    protected void setImage(){
        image = icon;
    }

    /*******************************************************************************************************************
     * Getter for name of this area
     ******************************************************************************************************************/
    public String getName() {return name;}

    /*******************************************************************************************************************
     * A setter for the variable FightableString
     ******************************************************************************************************************/
    @Override
    protected void setFightableString(String str) { fightableString = str; }

    /*******************************************************************************************************************
     * A getter for the variable FightableString
     * @return fightableString
     ******************************************************************************************************************/
    @Override
    protected String getFightableString() { return fightableString; }
}
