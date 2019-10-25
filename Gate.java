import java.util.HashMap;

public class Gate extends Location{
    public Gate() {
        setDescription();
        createEventList();
        createMap();
    }

    @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>(); //string is user command, event is the event to return
        mapOfEvents.put("LOOK", new Event("LOOK", "\nYou look at the big blue structure. It is big and very blue. To the south, you can see the BRIDGE."));
        mapOfEvents.put("GO TO BRIDGE", new Event("GO TO BRIDGE", "\nYou walk towards the bridge"));
        mapOfEvents.get("GO TO BRIDGE").setLocation("BRIDGE");
    }


    protected Event getEvent(String userCommand) {
        System.out.println(mapOfEvents.get(userCommand));
        return mapOfEvents.get(userCommand);
    }

    @Override
    protected void createMap() {
        listOfTraversable = new String[1];
        listOfTraversable[0] = "BRIDGE";

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
        listOfCharacters.put("TALK T HAAS", new Character());
        Character currChar = listOfCharacters.get("TALK T HAAS");
        currChar.setCharName("T Haas");
    }

    @Override
    protected void setDescription() {
        name = "GATE";
        flavorText = "You look around and find yourself at the beginning again.";
    }
    public String getName() {return name;}
}
