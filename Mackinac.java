import java.util.HashMap;

public class Mackinac extends Location {
    public Mackinac(){
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
    protected void Fight(Character m, Character e, String str,int movepool){

    }
    
    @Override
    protected void createCharList() {
        listOfCharacters = new HashMap<String, Character>();
    }

    @Override
    protected void setDescription() {

    }
}
