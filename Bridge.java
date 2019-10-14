import java.util.HashMap;

public class Bridge extends Location {
    public Bridge(){
        createEventList();
        createMap();
        createCharList();
    }
    @Override
    protected void createEventList() {

    }

    @Override
    protected Event getEvent(String userCommand) {
        return null;
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
}
