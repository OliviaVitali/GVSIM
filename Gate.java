import java.util.HashMap;

public class Gate extends Location{
    /**
     * Constructor for Gate location
     */
    public Gate() {
        //helper methods to instantiate the location
        setDescription();
        createEventList();
        createMap();
    }

    /**
     * Creates list of events
     */
    @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>();
        mapOfEvents.put("LOOK", new Event("LOOK", "You look at the gate"));
        mapOfEvents.put("GO BRIDGE", new Event("GO BRIDGE", "You walk towards the bridge"));
        mapOfEvents.get("GO BRIDGE").setLocation("BRIDGE");
    }


    /**
     * Returns event related to the user's command
     * @param userCommand
     * @return Event that
     */
    protected Event getEvent(String userCommand) {
        System.out.println(mapOfEvents.get(userCommand));
        return mapOfEvents.get(userCommand);
    }

    /**
     * creates String list traversable locations (NOT the location
     * objects themselves)
     */
    @Override
    protected void createMap() {
        listOfTraversable = new String[1];
        listOfTraversable[0] = "BRIDGE";

    }

    /**
     * helper method for debugging.  Returns list of traversable areas
     * @return String of traversable areas (Excluding scooter function)
     */
    @Override
    protected String getMap() {
        String temp = "";
        for (int i =0; i < listOfTraversable.length; i++){
            if (listOfTraversable[i] != null)
                temp += listOfTraversable[i] + "\n";
        }
        return temp;
    }

    /**
     * creates list of Characters
     */
    @Override
    protected void createCharList() {
        /**String refers to usercommand, Character refers to character*/
        listOfCharacters = new HashMap<String, Character>();
        /** Makes a character called T haas */
        listOfCharacters.put("TALK T HAAS", new Character());

        /** updates character information */
        Character currChar = listOfCharacters.get("TALK T HAAS");
        currChar.setCharName("T Haas");
    }

    /**
     * Sets information about the Gate
     */
    @Override
    protected void setDescription() {
        name = "GATE";
        flavorText = "You look around and find yourself at the beginning again.";
    }

    /**
     * getter for name of location
     * @return String with location's name
     */
    public String getName() {return name;}
}
