import java.util.Arrays;
import java.util.HashMap;

/***********************************************************************************************************************
 * Padnos is a traversable location in the game.  The player's string command is passed in and is the most often
 * referenced when deciding output.
 *
 * @author Sarah Arnott
 * @version 2
 **********************************************************************************************************************/
public class Padnos extends Location {

    /**
     * indicates if the player is in a fight
     */
    public boolean fighting;
    public String fightableString;
    /*******************************************************************************************************************
     * Constructor for class.  Creates characters, description for bridge, map of possible places to go to, and
     * events related to the flavor text.
     ******************************************************************************************************************/
    public Padnos() {
        setDescription();
        createCharList();
        createEventList();
        createMap();
        fightableString = "";
        fighting = false; //player is not in a fight by default
    }

    /*******************************************************************************************************************
     * Sets the flavortext description for this area when user LOOKs.
     ******************************************************************************************************************/
    @Override
    protected void setDescription() {
        name = "Padnos";
        flavorText = "\nYou are in the hallway of the physical science" +
                " building.";
    }

    /*******************************************************************************************************************
     * Helper method to create list of events.  An event is triggered by the string (user input) and will return a
     * string intended to be displayed on the GUI along with other information in the event like the NPC or the
     * secondary location the user is traveling to
     ******************************************************************************************************************/
    @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>(); //string is user command, event is the event to return
        mapOfEvents.put("LOOK", new Event("LOOK", "\nThere is a class" +
                " in session a few doors down, or a hallway leads " +
                "further on toward Henry Hall. There is a man to your left."));
        //talking to Neurochem
        mapOfEvents.put("TALK TO NEUROCHEM", new Event("TALK TO NEUROCHEM", "You approach the man and " +
                "start a conversation."));
        mapOfEvents.get("TALK TO NEUROCHEM").setNpc(listOfCharacters.get("TALK TO NEUROCHEM"));
        mapOfEvents.get("TALK TO NEUROCHEM").setName("TALK TO NEUROCHEM");

        mapOfEvents.put("GO TO CLASS", new Event("GO TO CLASS", "\nYou" +
                " sit down in the classroom and start taking notes on the lecture."));
        mapOfEvents.put("LEAVE CLASS", new Event("LEAVE CLASS", "You excuse yourself from the classroom." +
                " You've learned enough for now."));
        //TODO: make "take notes" academic possible here
        mapOfEvents.put("GO TO HENRY HALL", new Event("GO TO HENRY HALL",
                "You walk toward Henry Hall."));
        mapOfEvents.get("GO TO HENRY HALL").setLocation("HENRY HALL");
        mapOfEvents.put("GO TO BRIDGE", new Event("GO TO BRIDGE",
                "You leave Padnos and walk back to the bridge."));
        mapOfEvents.get("GO TO BRIDGE").setLocation("BRIDGE");
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
     * @return 2 for testing purposes.
     ******************************************************************************************************************/
    @Override
    protected int Fight(Character m, Character e, String str, int movepool) {
        return 2;
    }

    /*******************************************************************************************************************
     * Uses a loop to compile all locations user can access from this area into a string.
     * @return list of traversable locations
     ******************************************************************************************************************/
    @Override
    protected String getMap() {
        String temp = "";
        for (int i = 0; i < listOfTraversable.length; i++) {
            if (listOfTraversable[i] != null)
                temp += listOfTraversable[i];
        }
        return temp;
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
     * Creates the list of characters for this area.
     ******************************************************************************************************************/
    @Override
    protected void createCharList() {
        listOfCharacters = new HashMap<String, Character>();
        listOfCharacters.put("TALK TO NEUROCHEM", new Character());
        //Neurochem
        listOfCharacters.get("TALK TO NEUROCHEM").setCharName("Neurochem");
        listOfCharacters.get("TALK TO NEUROCHEM").setSpeechOptions("HELLO", "Hello, I teach brain chemistry" +
                " and do cancer research. You can sit in on one of my lectures if you like.");
        listOfCharacters.get("TALK TO NEUROCHEM").setSpeechOptions("GOODBYE", "See you around.");
        listOfCharacters.get("TALK TO NEUROCHEM").setCharStats(new Stats(10, 10, 10, 10));
        //player
        listOfCharacters.put("PLAYER", new Character());
        Character currChar = listOfCharacters.get("PLAYER");
        currChar.getCharStats().setAllStats(20, 5, 3, 2);
    }

    /*******************************************************************************************************************
     * Getter for name of this area
     ******************************************************************************************************************/
    public String getName() {
        return name;
    }

    @Override
    protected void setFightableString(String str) {
        fightableString = str;
    }

    @Override
    protected String getFightableString() {
        return fightableString;
    }
}
//end Padnos