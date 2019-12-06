import java.util.HashMap;
import java.util.Map;

/***********************************************************************************************************************
 * A class that acts as a template for characters, either NPC or the Player.
 * @author Olivia Vitali
 **********************************************************************************************************************/
public class Character {
    /** name of character*/
    private String charName;
    /** User command then speech options for character */
    private Map<String, String> speechOptions;
    /** list of inventory items the character has */
    private InventoryItem[] inventoryItems;
    /** stats used for fighting*/
    private Stats charStats;
    /**determines if this is an NPC or the player*/
    private boolean isPlayer;
    /** flavor text to describe the character */
    private String description;
    private String list;


    /*******************************************************************************************************************
    *Getter for Character name
    * @return charName which is the character's name
    *******************************************************************************************************************/
    public String getCharName() {
        return charName;
    }

    /*******************************************************************************************************************
    *setter for character name
    *******************************************************************************************************************/
    public void setCharName(String charName) {
        this.charName = charName;
    }

    /*******************************************************************************************************************
    * gets all conversation options for the character
    * @return list which is a list of all speech options
    *******************************************************************************************************************/
    public String getSpeechOptions() {
        return list;
    }

    /*******************************************************************************************************************
    * setter to add speech options
    * @param call is the user command
    * @param response is the NPC's response
    *******************************************************************************************************************/
    public void setSpeechOptions(String call, String response) {
        this.speechOptions.put(call, response);
        list += "\n " + call.toLowerCase();
    }

    /*******************************************************************************************************************
    * return intentory item in slot i
    * i is the index of the inventory item
    * @return the inventory item being requested at index i
    *******************************************************************************************************************/
    public InventoryItem getInventoryItems(int i) {
        return inventoryItems[i];
    }

    /*******************************************************************************************************************
    * setter for inventory item
    * @param inventoryItems is the new inventory item
    * @param i is the index intended in the list
    *******************************************************************************************************************/
    public void setInventoryItem(InventoryItem inventoryItems, int i) {
        this.inventoryItems[i] = inventoryItems;
    }

    /*******************************************************************************************************************
    * getter used in fighting
    * @return character's stats
    *******************************************************************************************************************/
    public Stats getCharStats() {
        return charStats;
    }

    /*******************************************************************************************************************
    * Sets Character's stats to the input
    * @param charStats is the new character status numbers
    *******************************************************************************************************************/
    public void setCharStats(Stats charStats) {
        this.charStats = charStats;
    }

    /*******************************************************************************************************************
    * checks if this is the player
    * @return isPlayer is true when the object is the player
    *******************************************************************************************************************/
    public boolean isPlayer() {
        return isPlayer;
    }

    /*******************************************************************************************************************
    * setter to change if object is the player or not
    * @param player is the true false value for if they are the player
    *******************************************************************************************************************/
    public void setIsPlayer(boolean player) {
        isPlayer = player;
    }


    /*******************************************************************************************************************
    * Default constructor for a character
    * if not changed, the character is Jenny.  Note: Jenny should not be
    * in the game so if you find her, something has gone wrong or is incomplete
    *******************************************************************************************************************/
    public Character() {
        charName = "Jenny";
        description = "\nA girl with a cell phone in her hand."; //her phone number is 867-5309, like the song :)
        speechOptions = new HashMap<String, String>();
        speechOptions.put("HELLO", "\n...world?");
        speechOptions.put("HI", "\nIt's nice to meet you.");
        inventoryItems = new InventoryItem[1];
        inventoryItems[0] = new InventoryItem("Car Keys", "\nThese keys belong to " + charName);
        charStats = new Stats(8, 6, 7, 5);
        isPlayer = false;
    }

    /*******************************************************************************************************************
    * gets the response associated with the user's input
    * @return Character's response to the user input
    *******************************************************************************************************************/
    public String getSpeech(String userCommand) {
        return speechOptions.get(userCommand);
    }

    /*******************************************************************************************************************
    * checks if the player can fight the character
    *******************************************************************************************************************/
    public boolean isFightable() {
        //you cannot fight yourself
        if (isPlayer) return false;
        //can't fight a player who has no Stats
        if (charStats == null)
            return false;
        //you cannot fight dead characters
        return charStats.getHP() >= 0;
    }
}

//end Character class