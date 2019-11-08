import java.util.HashMap;
import java.util.Map;

public class Character {

    private String charName;
    private Map<String, String> speechOptions;
    private InventoryItem[] inventoryItems;
    private Stats charStats;
    private boolean isPlayer;
    private String description;
    private String list;



    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getSpeechOptions() {
        return list;
    }

    public void setSpeechOptions(String call, String response) {
        this.speechOptions.put(call, response);
        list += "\n " + call.toLowerCase();
    }

    public InventoryItem getInventoryItems(int i) {
        return inventoryItems[i];
    }

    public void setInventoryItem(InventoryItem inventoryItems, int i) {
        this.inventoryItems[i] = inventoryItems;
    }

    public Stats getCharStats() {
        return charStats;
    }

    public void setCharStats(Stats charStats) {
        this.charStats = charStats;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(boolean player) {
        isPlayer = player;
    }

    public Character() {
        charName = "Jenny";
        description = "\nA girl with a cell phone in her hand.";
        speechOptions = new HashMap<String, String>();
        speechOptions.put("HELLO", "\n...world?");
        speechOptions.put("HI", "\nIt's nice to meet you.");
        inventoryItems = new InventoryItem[1];
        inventoryItems[0] = new InventoryItem("Car Keys", "\nThese keys belong to " + charName);
        charStats = new Stats(8, 6, 7, 5);
        isPlayer = false;
    }

    public String getSpeech(String userCommand) {
        return speechOptions.get(userCommand);
    }

    public boolean isFightable() {
        if (charStats == null)
            return false;
        return true;
    }
}
