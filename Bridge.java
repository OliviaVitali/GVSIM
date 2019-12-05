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
    //ImageIcon icon = new ImageIcon(getClass().getResource("LittleMak15(2).jpg"));

    /** indicates if the player is in a fight */
    public boolean fighting;

    /** Public so it can be used in multiple methods */
    private boolean gameOver;

    public String fightableString;
    /** restrictions so troll cant use special move twice */
    int restrictions;

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
        fightableString = "TROLL";
    }

    /*******************************************************************************************************************
     * Getter for gameOver variable
     * @return gameOver value (true/false)
     ******************************************************************************************************************/
    public boolean getGameOver() {
        return gameOver;
    }

    /*******************************************************************************************************************
     * Setter for gameOver boolean
     * @param gameOver set it to true or false
     ******************************************************************************************************************/
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


    /*******************************************************************************************************************
     * Helper method to create list of events.  An event is triggered by the string (user input) and will return a
     * string intended to be displayed on the GUI along with other information in the event like the NPC or the
     * secondary location the user is traveling to
     ******************************************************************************************************************/
 @Override
    protected void createEventList() {
        mapOfEvents = new HashMap<String, Event>();
        //gate travel
        mapOfEvents.put("GO TO GATE", new Event("GO TO GATE",
                "\nYou walk towards the Transformation Link"));
        mapOfEvents.get("GO TO GATE").setLocation("GATE");

        if(!gameOver) { //for when method is called while troll is alive
            mapOfEvents.put("LOOK", new Event("LOOK", this.flavorText));
            mapOfEvents.put("CHECK UNDER BRIDGE", new Event(
                    "CHECK UNDER BRIDGE", "\n There is " +
                    "something in the shadows; it is looking for a fight. A troll!"));
            //fighting events
            mapOfEvents.put("FIGHT TROLL", new Event("FIGHT TROLL",
                    "From under the bridge crawls a troll. He looks like" +
                            " he spends a lot of time online."));
            mapOfEvents.get("FIGHT TROLL").setNpc(listOfCharacters.get("FIGHT TROLL"));
            mapOfEvents.put("ATTACK", new Event("ATTACK", ""));
            mapOfEvents.get("ATTACK").setNpc(listOfCharacters.get("FIGHT TROLL"));
            mapOfEvents.put("DEFEND", new Event("DEFEND", ""));
            //blocks you from advancing to padnos
            mapOfEvents.put("GO TO PADNOS", new Event("GO TO PADNOS", "You try to cross the bridge, but " +
                    "the troll blocks your path!"));
        }else{ //for when method is called again after troll dies
            mapOfEvents.put("LOOK", new Event("LOOK", this.flavorText));
            //allows travel to padnos
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
     * Used to create a new characterList where player is only character.
     ******************************************************************************************************************/
    private void editCharList(){
        listOfCharacters = null;
        createCharList();
    }

    /*******************************************************************************************************************
     * getter method to return event based on user input
     * @param userCommand the string input by the user to trigger an event
     * @return the event associated with the given user command
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
        listOfTraversable = new String[2];
        listOfTraversable[0] = "GATE";
        listOfTraversable[1] = "PADNOS";
    }

    /*******************************************************************************************************************
     * Uses a loop to compile all locations user can access from this area into a string.
     * @return list of traversable locations
     ******************************************************************************************************************/
    @Override
    protected String getMap() {
        String temp = "";
        for (int i = 0; i < listOfTraversable.length; i++){
            if (listOfTraversable[i] != null)
                temp += listOfTraversable[i];
        }
        return temp;
        //return listOfTraversable.toString();
    }

    /*******************************************************************************************************************
     * Creates the list of characters for this area. Changes when troll is dead.
     ******************************************************************************************************************/
    @Override
    protected void createCharList() {
    listOfCharacters = new HashMap<String, Character>();
    Character currChar;
        if(!gameOver) {
            //troll
            listOfCharacters.put("TROLL", new Character());
            currChar = listOfCharacters.get("TROLL");
            currChar.setCharName("Gargafart the troll");
            currChar.setSpeechOptions("FIGHT", "I'm a troll and I want to fight you!");
            currChar.getCharStats().setAllStats(5, 7, 1, 2);

            //player
            listOfCharacters.put("PLAYER", new Character());
            currChar = listOfCharacters.get("PLAYER");
            currChar.getCharStats().setAllStats(20,5,3,2);
        }else{
            //player after level up
            listOfCharacters.put("PLAYER", new Character());
            currChar = listOfCharacters.get("PLAYER");
            currChar.getCharStats().setAllStats(21,8,4,3);
        }
    }

    /*******************************************************************************************************************
     * This method handles the fighting in this location.
     * @param m Player
     * @param e Troll
     * @param str Start, attack, or defend
     * @param movepool the number of different actions allowed during the fight
     * @return A number, for testing purposes
     ******************************************************************************************************************/
    @Override
    protected int Fight(Character m, Character e, String str, int movepool) {
        boolean roundOver = false;
        boolean gameOver = false;
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
                gameOver =true;
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
                            e.getCharStats().changeStr(3);
                            System.out.println("The Troll Went Berserk Plus 3 attack minus 1 defense");
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
                            e.getCharStats().changeStr(3);
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

        if(gameOver == true) {
            System.out.println("The Fight is Over");
            //after troll is defeated, area changes
           // setDescription(); //changes area description
            //editCharList(); //makes player only character present
            //editEventList(); //makes it so that player can go to padnos
        }
        return 1; //for testing
    }

    /*******************************************************************************************************************
     * Sets the flavortext description for this area when user LOOKs. Changes it depending on whether troll is dead.
     ******************************************************************************************************************/
    @Override
    protected void setDescription() {
        name = " bridge";
        if(!gameOver) { //troll alive
            flavorText = "\n You find yourself standing on a bridge.  you "
                    + "see a beautiful autumn scene with orange trees\nand"
                    + " a 50 foot drop to the ravine below.\n" +
                    "You are not alone. Try checking underneath the bridge...";
        } else { //troll dead
            flavorText = "\nYou breathe a sigh of relief and relax from the fight. What next?";
        }
    }

    /*******************************************************************************************************************
     * Sets the picture displayed for this area.
     ******************************************************************************************************************/
    protected void setImage(){
        //image = icon;
    }
    @Override
    protected void setFightableString(String str) {
        fightableString = str;
    }

    @Override
    protected String getFightableString() {
        return fightableString;
    }
}

//end Bridge class