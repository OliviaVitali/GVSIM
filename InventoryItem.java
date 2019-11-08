public class InventoryItem {

    private String name;
    private String description;
    public InventoryItem(){
        name = "Scrunchie";
        description = "A hair tie for your hair";
    }

    public InventoryItem(String name, String description){
        this.name = name;
        this.description = description;
    }
}
