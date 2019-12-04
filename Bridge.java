import javax.swing.*;
import java.util.HashMap;
import java.util.Random;

/***********************************************************************************************************************
 * Bridge is a traversable location in the game.  The player's string command is passed in and is the most often
 * referenced when deciding output. There are two versions: with and without troll, since locations are only created 1x.
 * @author Olivia Vitali
 * @author Cameron Shearer
 * @author Sarah Arnott
 * @version 3
 **********************************************************************************************************************/
public class Bridge extends Location {
    /** Icon for the bridge */
    ImageIcon icon = new ImageIcon(getClass().getResource("LittleMak15(2).jpg"));

    /** indicates if the player is in a fight */
    public boolean fighting;

    /** Public so it can be used in multiple methods */
    private boolean gameOver;

    /** restrictions so troll cant use special move twice */
    int restrictions;

    /**Whether troll is dead */

    /*******************************************************************************************************************
     * Constructor for class.  Creates characters, description for bridge, map of possible places to go to, and
     * events related to the flavor text.
     ******************************************************************************************************************/
    public Bridge(){
        //order listed below should not be changed
        setDescription();
        setImage();
        createCharList();
        createEventList();
        createMap();
        //by default, the player is not in a fight
        fighting = false;
        //by default, the troll is undefeated
        gameOver = false;
    }

    /** For after the troll is defeated, always call with gameOver = true */
    public Bridge(boolean gameOver){
        setDescription();
        createCharList();
        createEventList();
        createMap();
        //by default, the player is not in a fight
        fighting = false;

        //by default, the troll is undefeated
        gameOver = true;
    }


    /*******************************************************************************************************************
     * Helper method to create list of events.  An event is triggered by the string (user input) and will return a
     * string intended to be displayed on the GUI along with other information in the event like the NPC or the
     * secondary location the user is traveling to
     ********************************************************************************************************************/
 @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>();
        mapOfEvents.put("GO TO GATE", new Event("GO TO GATE",
                "\nYou walk towards the Transformation Link"));
        mapOfEvents.get("GO TO GATE").setLocation("GATE");
       // mapOfEvents.put("BRIDGE2 TRAVEL", );
        if(!gameOver) {
            mapOfEvents.put("LOOK", new Event("LOOK", this.flavorText));
            mapOfEvents.put("CHECK UNDER BRIDGE", new Event(
                    "CHECK UNDER BRIDGE", "\n There is " +
                    "something in the shadows; it is looking for a fight. A troll!"));
            mapOfEvents.put("FIGHT TROLL", new Event("FIGHT TROLL",
                    "From under the bridge crawls a troll. He looks like" +
                            " he spends a lot of time online."));
            mapOfEvents.get("FIGHT TROLL").setNpc(listOfCharacters.get("FIGHT TROLL"));
            mapOfEvents.put("ATTACK", new Event("ATTACK", ""));
            mapOfEvents.get("ATTACK").setNpc(listOfCharacters.get("FIGHT TROLL"));
            mapOfEvents.put("DEFEND", new Event("DEFEND", ""));
            mapOfEvents.put("GO TO PADNOS", new Event("GO TO PADNOS", "You try to cross the bridge, but " +
                    "the troll blocks your path!"));
        }else{
            mapOfEvents.put("LOOK", new Event("LOOK", this.flavorText));
            mapOfEvents.put("GO TO PADNOS", new Event("GO TO PADNOS", "You cross the bridge and enter " +
                    "Padnos")); //todo: fix look
            mapOfEvents.get("GO TO PADNOS").setLocation("PADNOS");
        }
    }

    /*******************************************************************************************************************
     * Used to create a new eventList where player can go to Padnos location.
     ******************************************************************************************************************/
    private void editEventList(){
        mapOfEvents = null;
        createEventList();
    }

    /*******************************************************************************************************************
     * getter method to return event based on user input
     ******************************************************************************************************************/
 @Override
    protected Event getEvent(String userCommand) {
        //System.out.println(mapOfEvents.get(userCommand).getName());
        return mapOfEvents.get(userCommand);
    }

    /*******************************************************************************************************************
     * helper method for debugging.  Lists possible locations the user can travel to.  Note: this doesn't correspond to
     * an event creation.  Actual travel events are created in createEvents
     ******************************************************************************************************************/
    @Override
    protected void createMap() {
        listOfTraversable = new String[1];
        listOfTraversable[0] = "GATE";
    }

    /*******************************************************************************************************************
     *
     * @return list of traversable locations
     ******************************************************************/
    @Override
    protected String getMap() {
        return listOfTraversable.toString();//should this be toString? I had a problem with return types. -Sarah
    }

    @Override
    protected void createCharList() {
    listOfCharacters = new HashMap<String, Character>();
    Character currChar;
    if(!gameOver) {
        listOfCharacters.put("TROLL", new Character());
        currChar = listOfCharacters.get("TROLL");
        currChar.setCharName("Gargafart the troll");
        currChar.setSpeechOptions("FIGHT", "I'm a troll and I want to fight you!");
        ;
        currChar.getCharStats().setAllStats(5, 7, 1, 2);
    }

        listOfCharacters.put("PLAYER", new Character());
        currChar = listOfCharacters.get("PLAYER");
        currChar.getCharStats().setAllStats(20,5,3,2);
    }
 @Override
    protected void Fight(Character m, Character e, String str, int movepool) {
        boolean roundOver = false;
        gameOver = false;
        //Move Choice 0 = troll attacks back
        //Move Choice 1 =
        // Move Choice 2 =
        int movechoice = movepool;
     if (str.equals("START")) {
            fighting = true;
        }
     while (fighting && !str.equals("START") && !roundOver) {
            if (e.getCharStats().getHP() <= 0) {
                fighting = false;
                System.out.println("You Win");
                gameOver = true;
            }
            if (m.getCharStats().getHP() <= 0) {
                fighting = false;
                System.out.println("You Lose");
                gameOver = true;
            }
            if (str == "ATTACK") {
                System.out.println("Is this working.jpg");
                if (e.getCharStats().getHP() > 0 && m.getCharStats().getHP() > 0) {
                    if (e.getCharStats().getDef() > m.getCharStats().getStr()) {
                        System.out.println("This mans too stronk");
                    } else {
                        e.getCharStats().damageCalculations(m.getCharStats().getStr() - e.getCharStats().getDef());
                        System.out.println("You Deal " + (m.getCharStats().getStr() - e.getCharStats().getDef()) + " Damage Enemy Hp:" + e.getCharStats().getHP());
                    }
                }
                if (e.getCharStats().getHP() > 0 && m.getCharStats().getHP() > 0) {
                    if(movechoice == 0) {
                        if (m.getCharStats().getDef() > e.getCharStats().getStr()) {
                            System.out.println("You Realize wait this troll is kinda weak, the trolls attack does nothing ");
                        } else {
                            m.getCharStats().damageCalculations(e.getCharStats().getStr() - m.getCharStats().getDef());
                            System.out.println("Enemy Deals " + (e.getCharStats().getStr() - m.getCharStats().getDef()) + " Damage \n Your Hp is:" + m.getCharStats().getHP());
                        }
                    }
                    if(movechoice == 1){
                        if(e.getCharStats().getHP() > 0 && m.getCharStats().getHP() > 0) {
                            e.getCharStats().changeDef(-1);
                            e.getCharStats().changeStr(1);
                            System.out.println("The Troll went Berserk: Plus 1 attack, minus 1 defense.");
                        }
                    }

                    if(movechoice == 2){
                        if (m.getCharStats().getDef() > e.getCharStats().getStr()) {
                            System.out.println("Even the Trolls Special move could not hurt you");
                        } else {
                            m.getCharStats().damageCalculations(e.getCharStats().getStr() + e.getCharStats().getDef() - m.getCharStats().getDef());
                            System.out.println("Enemy Unleashes Their Special Move, Spiked Shield Bash, and deals " + (e.getCharStats().getStr() + e.getCharStats().getDef() -
                                    m.getCharStats().getDef()) + " Damage. \nEnemy Hp:" + m.getCharStats().getHP());
                        }
                    }

                    movechoice = movepool;
                    roundOver = true;
                }
            }
                if(str == "DEFEND"){
                    if(e.getCharStats().getHP() > 0 && m.getCharStats().getHP() > 0) {
                       m.getCharStats().changeDef(2);
                    }
                    if (e.getCharStats().getHP() > 0 && m.getCharStats().getHP() >0) {
                        if(movechoice == 0) {
                            if (m.getCharStats().getDef() > e.getCharStats().getStr()) {
                                System.out.println("You Realize wait this troll is kinda weak, the trolls attack does nothing ");
                            } else {
                                m.getCharStats().damageCalculations(e.getCharStats().getStr() - m.getCharStats().getDef());
                                System.out.println("Enemy Deals " + (e.getCharStats().getStr() - m.getCharStats().getDef()) + " Damage \n Your Hp is:" + m.getCharStats().getHP());
                            }
                        }
                        if(movechoice == 1){
                            if(e.getCharStats().getHP() > 0 && m.getCharStats().getHP() > 0) {
                                e.getCharStats().changeDef(-1);
                                e.getCharStats().changeStr(1);
                                System.out.println("The Troll Went Berserk Plus 1 attack minus 1 defense");
                            }
                        }

                        if(movechoice == 2){
                            if (m.getCharStats().getDef() > e.getCharStats().getStr()) {
                                System.out.println("Even the Trolls Special move could not hurt you");
                            } else {
                                m.getCharStats().damageCalculations(e.getCharStats().getStr() + e.getCharStats().getDef() - m.getCharStats().getDef());
                                System.out.println("Enemy Unleashes Their Special Move Spiked Shield Bash and Deals " + (e.getCharStats().getStr() + e.getCharStats().getDef() -
                                        m.getCharStats().getDef()) + " Damage Enemy Hp:" + m.getCharStats().getHP());
                            }
                        }
                        roundOver = true;
                        movechoice = movepool;
                    }

                }
            }

        if(gameOver) {
            System.out.println("The Fight is over");
            editEventList(); //makes it so that player can go to padnos
            setDescription();
        }
    }
    @Override
    protected void setDescription() {
        name = " bridge";
        if(!gameOver) {
            flavorText = "\n You find yourself standing on a bridge.  you "
                    + "see a beautiful autumn scene with orange trees\nand"
                    + " a 50 foot drop to the ravine below.\n" +
                    "You are not alone. Try checking underneath the bridge...";
        } else {
            flavorText = "You breathe a sigh of relief and relax from the fight. What next?";
        }
    }

    protected void setImage(){
        image = icon;
    }

}
