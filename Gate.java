import java.util.HashMap;

public class Gate extends Location {
    public Gate() {
        setDescription();
        createMap();
        createCharList();
        createEventList();
    }

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
                "\n He smiles at you"));
        mapOfEvents.get("TALK TO T HAAS").setNpc(listOfCharacters.get("TALK TO T HAAS"));
        mapOfEvents.get("TALK TO T HAAS").setName("TALK TO T HAAS");
        //mapOfEvents.get("TALK TO T HAAS").setFlavorText(listOfCharacters.get("TALK TO T HAAS").);



    }


    protected Event getEvent(String userCommand) {
        return mapOfEvents.get(userCommand);
    }

    @Override
    protected void createMap() {
        listOfTraversable = new String[1];
        listOfTraversable[0] = "BRIDGE";

    }
    @Override
    protected void Fight(Character m, Character e, String str){

    }
    
    @Override
    protected String getMap() {
        String temp = "";
        for (int i = 0; i < listOfTraversable.length; i++){
            if (listOfTraversable[i] != null)
                temp += listOfTraversable[i];
        }
        return temp;
    }

    @Override
    protected void createCharList() {
        listOfCharacters = new HashMap<String, Character>();
        listOfCharacters.put("TALK TO T HAAS", new Character());
        //Character currChar = listOfCharacters.get("TALK TO T HAAS");
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

    @Override
    protected void setDescription() {
        name = "GATE";
        flavorText = "You look around and find yourself at the beginning again.";
    }
    public String getName() {return name;}
}
