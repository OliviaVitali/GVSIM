import javax.swing.*;

/**********************************************************************
 * lets you change the location, interact with npc's, interact with
 * inventory items, see the flavor text for events like jumping on the
 * bridge
 */
public class Event {
    /** name of the event*/
    String name;
    /** flavortext that should be printed to the GUI*/
    protected String flavorText;
    /** associated inventory item*/
    protected InventoryItem inventoryItem;
    /** related npc if needed for the event*/
    protected Character npc;
    /** if the event changes the location, the String is the new location */
    protected String location;
    /** image that corresponds with the location **/
    protected ImageIcon image;

    /**
    * Default constructor for events
    * generally should be avoided being used
    */
    public Event(){
        name = "Event";
        flavorText = "flavorText ";
        inventoryItem = null;
        npc = null;
        location = null;
        image = null;
    }

    /**
    * Constructor that initializes name and flavorText
    * @param name is the name of the event
    * @param flavorText is the text that should be printed on the GUI
    */
    public Event(String name, String flavorText){
        this.name = name;
        this.flavorText = flavorText;
        inventoryItem = null;
        npc = null;
        location = null;
    }

    /**
    * returns the new location name (NOT a location class object) if 
    * location will be changed
    * @return the new location name
    */
    public String getLocation() {
        return location;
    }

    /**
    * changes location in game (may not be used)
    */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
    * getter for the name of the book
    * @returns name of the event
    */
    public String getName() {
        if (name != null) return name;
        return null;
    }

    /**
    * setter for the name of the event
    */
    public void setName(String name) {
        if (name != null) this.name = name;
    }

    /**
    * getter for flavor text
    * @return string with flavorText to be print on GUI
    */
    public String getFlavorText() {
        if (flavorText != null)return flavorText;
        return null;
    }

    /**
    * Setter for flavor text.  Appends to current text (not replaces it)
    */
    public void setFlavorText(String flavorText) {
        if (flavorText != null) this.flavorText += flavorText;
    }

    /**
    * @returns inventory item if associated with the event
    */
    public InventoryItem getInventoryItem() {
        if (inventoryItem != null) return inventoryItem;
        return null;
    }

    /**
    * setter for inventory item
    */
    public void setInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem != null) this.inventoryItem = inventoryItem;
    }

    /**
    * getter for NPC
    * @returns character NPC associated with event
    */
    public Character getNpc() {
        if (npc != null)return npc;
        return null;
    }

    /**
    * setter for NPC associated with event
    * could be player but that goes against the game's design
    */
    public void setNpc(Character npc) {
        //cannot be null
        if (npc != null) {
            this.npc = npc;
        }
    }

    /**
    * gets the speech associated with the NPC
    * may not be used
    * @ returns string with npc's response
    */
    public String getSpeech() {
        if (npc != null) {
            return npc.getSpeech(name);
        }
        return "";
    }
}
