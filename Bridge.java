import java.util.HashMap;

public class Bridge extends Location {
    public boolean fighting;
    public Bridge(){
        setDescription();
        createCharList();
        createEventList();
        createMap();
        fighting = false;
    }
    @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>();
        mapOfEvents.put("LOOK", new Event("LOOK", this.flavorText));
        mapOfEvents.put("GO TO GATE", new Event("GO TO GATE", "\nYou walk towards the Transformation Link"));
        mapOfEvents.get("GO TO GATE").setLocation("GATE");
        mapOfEvents.put("CHECK UNDER BRIDGE", new Event(
                "CHECK UNDER BRIDGE", "\n There is " +
                "something in the shadows it is eyeing you looking" +
                " for a fight"));
        mapOfEvents.put("FIGHT TROLL", new Event("FIGHT TROLL",
                "From under the bridge crawls a troll.  He looks like he spends a lot of time online."));
        mapOfEvents.get("FIGHT TROLL").setNpc(listOfCharacters.get("FIGHT TROLL"));

        mapOfEvents.put("ATTACK", new Event("ATTACK",""));
        mapOfEvents.get("ATTACK").setNpc(listOfCharacters.get("FIGHT TROLL"));
        mapOfEvents.put("DEFEND",new Event("DEFEND", ""));
    }

    @Override
    protected Event getEvent(String userCommand) {
        //System.out.println(mapOfEvents.get(userCommand).getName());
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
        listOfCharacters.put("FIGHT TROLL", new Character());
        Character currChar = listOfCharacters.get("FIGHT TROLL");
        currChar.setCharName("Gargafart the troll");
        currChar.setSpeechOptions("FIGHT", "I'm a troll and I want to fight you!");
        ;
        currChar.getCharStats().setAllStats(5, 7, 1, 2);


        listOfCharacters.put("PLAYER", new Character());
        currChar = listOfCharacters.get("PLAYER");
        currChar.getCharStats().setAllStats(20,5,3,2);
    }
 @Override
    protected void Fight(Character m, Character e, String str) {
        boolean roundOver = false;
        boolean gameOver = false;
     if (str.equals("START")) {
            fighting = true;
        }
     while (fighting && !str.equals("START") && !roundOver) {
            if (e.getCharStats().getHP() <= 0) {
                fighting = false;
                System.out.println("You Win");
                gameOver =true;
            }
            if (m.getCharStats().getHP() <= 0) {
                fighting = false;
                System.out.println("You Lose");
                gameOver = true;
            }
            if (str == "ATTACK") {
                if (e.getCharStats().getHP() > 0 && m.getCharStats().getHP() > 0) {
                    if (e.getCharStats().getDef() > m.getCharStats().getStr()) {
                        System.out.println("This mans too stronk");
                    } else {
                        e.getCharStats().damageCalculations(m.getCharStats().getStr() - e.getCharStats().getDef());
                        System.out.println("You Deal " + (m.getCharStats().getStr() - e.getCharStats().getDef()) + " Damage Enemy Hp:" + e.getCharStats().getHP());
                    }
                }
                if (e.getCharStats().getHP() > 0 && m.getCharStats().getHP() > 0) {
                    if (m.getCharStats().getDef() > e.getCharStats().getStr()) {
                        System.out.println("You Realize wait this troll is kinda weak, the trolls attack does nothing ");
                    } else {
                        m.getCharStats().damageCalculations(e.getCharStats().getStr() - m.getCharStats().getDef());
                        System.out.println("Enemy Deals " + (e.getCharStats().getStr() - m.getCharStats().getDef()) + " Damage \n Your Hp is:" + m.getCharStats().getHP());
                    }
                    roundOver = true;
                }
            }
                if(str == "DEFEND"){
                    if(e.getCharStats().getHP() > 0 && m.getCharStats().getHP() > 0) {
                       m.getCharStats().changeDef(2);
                    }
                    if (e.getCharStats().getHP() > 0 && m.getCharStats().getHP() >0) {
                        System.out.println("The Troll mocks you calling you a coward for trying to block his attacks");
                        //Code if you want the Troll to attack while your defending
                        // m.getCharStats().damageCalculations(e.getCharStats().getStr() - m.getCharStats().getDef());
                        //System.out.println("Enemy Deals " + (e.getCharStats().getStr() - m.getCharStats().getDef()) + " Damage \n Your Hp is:" + m.getCharStats().getHP());
                        roundOver = true;
                    }
                }
            }
        
        if(gameOver == true) {
            System.out.println("The Fight is Over");
        }
    }
    @Override
    protected void setDescription() {
        name = " bridge";
        flavorText = "You find yourself standing on a bridge.  you "
                + "see a beautiful autumn scene with orange trees\nand"
                + " a 50 foot drop to the ravine below.\n" +
                "You are not alone.";
    }
}
