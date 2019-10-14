import java.util.HashMap;

public class Gate extends Location{
    public Gate() {
        setDescription();
        createEventList();
        createMap();
    }

    @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>();
        mapOfEvents.put("LOOK", new Event("LOOK", "You look at the gate"));
        mapOfEvents.put("GO BRIDGE", new Event("GO BRIDGE", "You walk towards the bridge"));
        mapOfEvents.get("GO BRIDGE").setLocation("BRIDGE");
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
        for (int i =0; i < listOfTraversable.length; i++){
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
