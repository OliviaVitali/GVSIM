import java.util.HashMap;

public class Padnos extends Location {

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
                "further on toward Henry Hall. There is a man to your left."));
        //talking to Neurochem
        mapOfEvents.put("TALK TO NEUROCHEM", new Event("TALK TO NEUROCHEM", "You approach the man and " +
                "start a conversation."));
        mapOfEvents.get("TALK TO NEUROCHEM").setNpc(listOfCharacters.get("TALK TO NEUROCHEM"));
        mapOfEvents.get("TALK TO NEUROCHEM").setName("TALK TO NEUROCHEM");

        mapOfEvents.put("GO TO CLASS", new Event("GO TO CLASS", "\nYou" +
                " sit down in the classroom and start taking notes on the lecture."));
        mapOfEvents.put("LEAVE CLASS", new Event("LEAVE CLASS", "You excuse yourself from the classroom." +
                " You've learned enough for now."));
        //TODO: make "take notes" academic possible here
        mapOfEvents.put("GO TO HENRY HALL", new Event("GO TO HENRY HALL",
                "You walk toward Henry Hall."));
        mapOfEvents.get("GO TO HENRY HALL").setLocation("HENRY HALL");
        mapOfEvents.put("GO TO BRIDGE", new Event("GO TO BRIDGE",
                "You leave Padnos and walk back to the bridge."));
        mapOfEvents.get("GO TO BRIDGE").setLocation("BRIDGE");
    }

    @Override
    protected void createMap() {
        listOfTraversable = new String[2];
        listOfTraversable[0] = "BRIDGE";
        listOfTraversable[1] = "HENRY HALL";
        //todo: add locations (henry hall, clocktower) if we ever create them
    }

    @Override
    protected void Fight(Character m, Character e, String str,int movepool){
        System.out.print("There is nobody you can fight here.");
    }
    
    @Override
    protected String getMap() {
//        String temp = "";
//        for (int i = 0; i < listOfTraversable.length; i++){
//            if (listOfTraversable[i] != null)
//                temp += listOfTraversable[i];
//        }
//        return temp;

        return listOfTraversable.toString(); //todo: which way works? bridge above, or this?
    }

    protected Event getEvent(String userCommand) {
       // System.out.println(mapOfEvents.get(userCommand));
        return mapOfEvents.get(userCommand);
    }

    @Override
    protected void createCharList() {
        listOfCharacters = new HashMap<String, Character>();
        listOfCharacters.put("TALK TO NEUROCHEM", new Character());
        //Character currChar = listOfCharacters.get("NEUROCHEM");
        listOfCharacters.get("TALK TO NEUROCHEM").setCharName("Neurochem");
        listOfCharacters.get("TALK TO NEUROCHEM").setSpeechOptions("HELLO", "Hello, I teach brain chemistry and do cancer research." +
                " You can sit in on one of my lectures if you like.");
        listOfCharacters.get("TALK TO NEUROCHEM").setSpeechOptions("GOODBYE", "See you around.");
        listOfCharacters.get("TALK TO NEUROCHEM").setCharStats(new Stats(10, 10, 10, 10));
        listOfCharacters.put("PLAYER", new Character());
        Character currChar = listOfCharacters.get("PLAYER");
        currChar.getCharStats().setAllStats(20, 5, 3, 2);
    } //todo: did I do this right?

}

//end Padnos
