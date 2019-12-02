import java.util.HashMap;

public class Padnos extends Location { //TODO: add Neurochem

    /** indicates if the player is in a fight */
    public boolean fighting;

    public Padnos(){
        setDescription();
        createCharList();
        createEventList();
        createMap();

        fighting = false; //player is not in a fight by default
    }

    @Override
    protected void setDescription() {
        name = "Padnos";
        flavorText = "You are in the hallway of the physical science" +
                " building.";
    }

    @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>(); //string is user command, event is the event to return
        mapOfEvents.put("LOOK", new Event("LOOK", "\nThere is a class" +
                " in session a few doors down, or a hallway leads " +
                "further on toward Henry Hall."));
        mapOfEvents.put("GO TO CLASS", new Event("GO TO CLASS", "\nYou" +
                " sit down in the classroom and start taking notes on the lecture."));
        //TODO: make "take notes" academic possible here
        mapOfEvents.put("GO TO HENRY HALL", new Event("GO TO HENRY HALL",
                "You walk toward Henry Hall."));
        mapOfEvents.get("GO TO HENRY HALL").setLocation("HENRY HALL");
        mapOfEvents.put("GO TO BRIDGE", new Event("GO TO BRIDGE",
                "You leave Padnos and walk back to the bridge."));

    }

    @Override
    protected void createMap() {
        listOfTraversable = new String[1];
        listOfTraversable[0] = "BRIDGE";
        //todo: add locations (henry hall, clocktower) if we ever create them
    }

    @Override
    protected void Fight(Character m, Character e, String str,int movepool){
    //todo: fill in
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

    protected Event getEvent(String userCommand) {
       // System.out.println(mapOfEvents.get(userCommand));
        return mapOfEvents.get(userCommand);
    }

    @Override
    protected void createCharList() {
        listOfCharacters = new HashMap<String, Character>();
        listOfCharacters.put("TALK NEUROCHEM", new Character());
        Character currChar = listOfCharacters.get("TALK NEUROCHEM");
        currChar.setCharName("Neurochem");
    } //todo: did I do this right?

}

//end Padnos
