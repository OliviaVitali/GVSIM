import java.util.Map;

public abstract class Location {
    //contains all characters in location, relates user command
    protected Map<String, Character> listOfCharacters;
    protected String name;
    protected String flavorText;
    protected String[] listOfTraversable;
    protected Map<String, Event> mapOfEvents;

    //public Location(){}

    protected abstract void createEventList();
    protected abstract Event getEvent(String userCommand);

    protected abstract void createMap();
    protected abstract String getMap();

    protected abstract void createCharList();
    protected abstract void setDescription();


}
