/**********************************************************************
 * lets you change the location, interact with npc's, interact with
 * inventory items, see the flavor text for events like jumping on the
 * bridge
 */
public class Event {

    private String name;
    private String flavorText;
    private InventoryItem inventoryItem;
    private Character npc;
    private String location;

    public Event(){
        name = "Event";
        flavorText = "flavorText ";
        inventoryItem = null;
        npc = null;
        location = null;
    }

    public Event(String name, String flavorText){
        this.name = name;
        this.flavorText = flavorText;
        inventoryItem = null;
        npc = null;
        location = null;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        if (name != null) return name;
        return null;
    }

    public void setName(String name) {
        if (name != null) this.name = name;
    }

    public String getFlavorText() {
        if (flavorText != null)return flavorText;
        return null;
    }

    public void setFlavorText(String flavorText) {
        if (flavorText != null)this.flavorText = flavorText;
    }

    public InventoryItem getInventoryItem() {
        if (inventoryItem != null) return inventoryItem;
        return null;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem != null) this.inventoryItem = inventoryItem;
    }

    public Character getNpc() {
        if (npc != null)return npc;
        return null;
    }

    public void setNpc(Character npc) {
        if (npc != null) this.npc = npc;
    }
}
