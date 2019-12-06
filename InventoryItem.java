/***********************************************************************************************************************
 * Inventory Item that contains name, description, and relevant stat
 * changes
 * @author Olivia Vitali
 * @author Riley Hernandez
 * @version 1
 **********************************************************************************************************************/
public class InventoryItem {

    /** name of the item */
    private String name;

    /** flavortext for the item */
    private String description;

    /*******************************************************************************************************************
     * Generic constructor.  All items in game are Scrunchies until reskinned
     ******************************************************************************************************************/
    public InventoryItem(){
        /** name of inventory item*/
        name = "Scrunchie";
        /** flavortext for inventory item */
        description = "A hair tie for your hair";
    }

    /*******************************************************************************************************************
     * Constructor that takes inputs
     * @param name object's name
     * @param description flavortext
     ******************************************************************************************************************/
    public InventoryItem(String name, String description){
        this.name = name;
        this.description = description;
    }
}

//end InventoryItem class