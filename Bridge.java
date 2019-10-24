import java.util.HashMap;

public class Bridge extends Location {
    public Bridge(){
        createEventList();
        createMap();
        createCharList();
    }
    @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>();
        mapOfEvents.put("LOOK", new Event("LOOK", "You find yourself at the BRIDGE over a steep ravine. You are not alone."));
    }

    @Override
    protected Event getEvent(String userCommand) {
        System.out.println(mapOfEvents.get(userCommand));
        return mapOfEvents.get(userCommand);
    }

    @Override
    protected void createMap() {

    }

    @Override
    protected String getMap() {
        return null;
    }

    @Override
    protected void createCharList() {
    listOfCharacters = new HashMap<String, Character>();
        listOfCharacters.put("TALK TO TROLL", new Character());
        Character currChar = listOfCharacters.get("TALK TO TROLL");
        currChar.setCharName("Gargafart");
        currChar.getCharStats().setAllStats(5, 1, 1, 2);
    }

    @Override
    protected void setDescription() {

    }
    public String getName() {return name;}
}
