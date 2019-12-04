import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.util.Random;

/***********************************************************************************************************************
* this is the game.  It holds all the objects, players, locations and navigation through the game
* @author Olivia Vitali, Fight by Cameron Shearer
* @version 4
***********************************************************************************************************************/
public class GamePanel extends JPanel {
    /*******************************************************************************************************************
     * Default image for the GUI
     *******************************************************************************************************************/
    ImageIcon icon = new ImageIcon(getClass().getResource("TransformationLink15.png"));
    /**
     * menu for GUI
     */
    public JMenuBar mb;
    JMenu m1;
    JButton helpButton, mapButton, convoChoices, fightChoices;
    JMenuItem m11, m22;
    public JPanel panel;
    JLabel label, lbl;

    /** user puts commands in this line */
    JTextField tf;

    /** sends text in user command line to panel */
    JButton send;

    /** button to reset text in input line in GUI */
    JButton reset;

    /** text displayed on GUI */
    JTextArea ta;

    /** makes the text area scrollable */
    JScrollPane scroll;

    /** listens to mouse clicks for GUI and relays them to panel */
    ActionListener listener;

    /** pointer to player's current location */
    static Location currLocation;

    /** All locations are stored in a map */
    Map<String, Location> listAllLocations;
    /** Boolean that make sure user initiated a fight */
    boolean theGoodFight;

    /** Boolean to make sure level up works */
    boolean alreadyLvl;

    /** Connects text box title to text box for more readable coding */
    Map<String, String> dicFlavorText;

    /** determines if in a conversation loop */
    private boolean isConvo;

    /** player character */
    Character player;
    /** the npc currently interacted with */
    Character currChar;
    /** current event being referenced */
    Event currEvent;

    /** The current default hp which is needed to reset after a fight */
    int defaultHp = 20;

    /** Random number to restrict move pool and related ints*/
    Random res = new Random();
    int bound = 2;
    int restrictions = res.nextInt(2);

    /*******************************************************************************************************************
    * Constructor for GamePanel
    * Instantiates most objects referenced in game
    *******************************************************************************************************************/
    public GamePanel() {
        //connect GUI to panel
        listener = new listener();

        //Text Area at the Center
        ta = new JTextArea(5, 5);

        //helper to create some flavor text
        createdicFlavorText();

        //sets current text to Intro on GUI
        ta.setText(dicFlavorText.get("intro"));
        scroll = new JScrollPane(ta);
        ta.setEditable(false);
        theGoodFight = false;
        isConvo = false;
        setMenu();
        //Creating the panel at bottom and adding components
        setPanel();
        createLocations();

        //initializes player character
        player = new Character();
        player.setIsPlayer(true);
        player.setCharName("ORCS");
        player.getCharStats().setAllStats(20,5,3,2);
        //by default, current character is set to Jenny. If you reach Jenny, you've messed up.
        currChar = new Character();
    }

    /*******************************************************************************************************************
     *  Getter for currLocation
     ******************************************************************************************************************/
    public Location getCurrLocation(){
        return currLocation;
    }

    /*******************************************************************************************************************
     * Setter for currLocation, used in bridge class
     ******************************************************************************************************************/
    public static void setCurrLocation(Location x){
        currLocation = x;
    }

    /*******************************************************************************************************************
    * helper method to create flavortext for game not used elsewhere
    ******************************************************************************************************************/
    private void createdicFlavorText() {
      /** map of abbreviation and text for GUI*/
        dicFlavorText = new HashMap<>();
        dicFlavorText.put("intro",
                "Welcome to GVSimulator! " +
                        "Type LOOK to take a peek at the world around you!");
        dicFlavorText.put("credits", "This game was made by ORCS.");
        dicFlavorText.put("", "\n-------");

        dicFlavorText.put("GO", "\nYou are at " + currLocation);
        dicFlavorText.put("TALK", "\n You are talking to ");
    }

    /*******************************************************************************************************************
    * helper method for constructor
    * creates all locations for the game
    *******************************************************************************************************************/
    private void createLocations() {
        listAllLocations = new HashMap<String, Location>();
        listAllLocations.put("GATE", new Gate());
        listAllLocations.put("BRIDGE", new Bridge());
        listAllLocations.put("PADNOS", new Padnos());
        //sets default location to GATE
        currLocation = listAllLocations.get("GATE");
        //added for flavor text helper in GUI
        dicFlavorText.put("map", "\nBridge--------Gate\n|\n|\n|\nPadnos");
    }

    /*******************************************************************************************************************
    * helper method for constructor
    * adds panel information for GUI
    *******************************************************************************************************************/
    private void setPanel() {
        panel = new JPanel();
        label = new JLabel("Enter Text");
        lbl = new JLabel(icon);
        tf = new JTextField(20); // accepts up to 20 characters
        send = new JButton("Send");
        send.addActionListener(listener);
        reset = new JButton("Clear");
        reset.addActionListener(listener);
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);
    }

    /*******************************************************************************************************************
    * helper method for constructor
    * sets menu to items for panel
    *******************************************************************************************************************/
    private void setMenu() {
        //Creating the MenuBar and adding components
        mb = new JMenuBar();
        //m1 = new JMenu("File");
        helpButton = new JButton("Help");
        mapButton = new JButton("Map");
        convoChoices = new JButton("Conversation Options");
        fightChoices = new JButton("Fight Options");
        //mb.add(m1);
        mb.add(helpButton);
        mb.add(mapButton);
        mb.add(convoChoices);
        mb.add(fightChoices);
        helpButton.addActionListener(listener);
        mapButton.addActionListener(listener);
        convoChoices.addActionListener(listener);
        fightChoices.addActionListener(listener);
        //m11 = new JMenuItem("Open");
        //m22 = new JMenuItem("Save as");
        //m1.add(m11);
        //m1.add(m22);
    }

    /*******************************************************************************************************************
    * when in a conversation, user is re-routed here to directly translate
    * user commands and returns character's responses
    *******************************************************************************************************************/
    private void conversation(String cmd, Character c) {
        //handles conversation with npc

        //ta.append(dicFlavorText.get("TALK") + c.getCharName()); todo: remove this(?)

        //try catch handles invalid user commands
        try {
            //while in conversation, trigger talking options
            if (isConvo) {
                ta.append("\nYou say " + cmd.toLowerCase());
                ta.append("\n" + c.getCharName() + " says \"");
            }
            ta.append(c.getSpeech(cmd));
            ta.append("\"");
            //user can exit a conversation
            if (cmd.equals("BYE") || cmd.equals("GOODBYE") || cmd.equals("QUIT")) {
                isConvo = false;
                ta.append(c.getSpeech("BYE"));
            }
        } catch (NullPointerException e) {
            tf.setText(null);
        }
    }

    /*******************************************************************************************************************
     * Basically updates everything.  Gets related event from user cmd
     * If the player starts a conversation, they must either type "bye"
     * or another valid event name to leave the conversation
     * If the player starts a fight, they must either finish the fight
     * or enter another valid event to leave the fight
     * @param userCommand is the user's intention
     ******************************************************************************************************************/
    private void updateEvent(String userCommand) {
        String cmd = userCommand.toUpperCase();

        if (isConvo)
            conversation(cmd, currChar);

        try {
            currEvent = currLocation.getEvent(cmd);

            //if you just walk away during a conversation
            //this sets the conversation flag to false
            if (!currEvent.getName().contains("TALK"))
                isConvo = false;
            //if a player triggers a non-fighting event, navigate away
            //from fight
            if (!currEvent.getName().contains("FIGHT"))
                //theGoodFight = false;
            //flavortext update for game
            ta.append(currEvent.getFlavorText());
            ta.setCaretPosition(ta.getDocument().getLength());
            tf.setText(null);
            ta.append(dicFlavorText.get(""));

            //sets NPC if able
            currChar = currEvent.getNpc();
        } catch (NullPointerException e) {
            tf.setText(null);
        }
        try {
            System.out.println("is this working");
            //this is the character we're interacting with
            currChar = currEvent.getNpc();
            //triggers conversation with NPC
            if (cmd.contains("TALK")) {
                isConvo = true;
                conversation(cmd, currChar);
                //This Triggers the Fight Mechanic if it is available
            } else if (cmd.contains("FIGHT")) {
                System.out.println("it is");
                CameronsFightShenanigans(currEvent, cmd, currChar);
                theGoodFight = true;
                //Needed for if someone chooses attack
            }else if (cmd.contains("ATTACK")){
                System.out.println("Does this one worko");
                CameronsFightShenanigans(currEvent, cmd, currChar);
            }else if(cmd.contains("DEFEND")){
                System.out.println("DEFEEENNNDDDDD");
                CameronsFightShenanigans(currEvent,cmd,currChar);
            }
        } catch (NullPointerException e) {
            tf.setText(null);
        }

        //TODO update so that only conversation, location change, or fight occur
        //updates location if event has a location and prints new
        //location's flavortext
        try {
            if (currEvent.getLocation() != null) {
                currLocation = listAllLocations.get(currEvent.getLocation());
                ta.append(currLocation.flavorText);
                lbl.setIcon(currLocation.image);
            }
        } catch (NullPointerException e) {
            tf.setText(null);
        }
    }

    /*******************************************************************************************************************
     * Updates game's fighting state
     * @param currEvent is the event related to the fight
     ******************************************************************************************************************/
    private void CameronsFightShenanigans(Event currEvent, String userCommand, Character opp) {
        //Start of Cameron's Fight Shenanigans
        //If the string contains fight it runs and uses print statements to test if working
        //Also makes sure to initialize a fight unless one is already initialized
        restrictions = res.nextInt(bound);
        if(restrictions == 3){
            bound = 2;
        }
        if (currEvent.getName().contains("FIGHT")) {
            System.out.println(userCommand);
           if (userCommand.contains("FIGHT")) {
               System.out.println(currLocation.listOfCharacters.get("TROLL").getCharStats().getHP());
               System.out.println("2");
               ta.append(currLocation.listOfCharacters.get("TROLL").getSpeech(userCommand));
               System.out.println("9");
            }
            //If the character is already dead why fight it
            if(currLocation.listOfCharacters.get("TROLL").getCharStats().getHP() <= 0) {
                System.out.println("14");
                alreadyLvl = true;
                ta.append("\n You can't fight a dead body -_-");

                //If your already in a fight why are you trying to start another
            } else if (theGoodFight) {
                System.out.println("6");
                ta.append("\n You are already in a fight. LOOK ALIVE.");

            } else {
                //Starting up the fight
                alreadyLvl = false;
                theGoodFight = true;
                currLocation.Fight(player, currLocation.listOfCharacters.get("TROLL"), "START", restrictions);
                System.out.println("3");
                ta.append("\n LET THE FIGHT BEGIN");
                ta.append("\n Player Stats: \n Hp = " + player.getCharStats().getHP());
                ta.append("\n Str = " + player.getCharStats().getStr());
                ta.append("\n Def = " + player.getCharStats().getDef());
                ta.append("\n Spd = " + player.getCharStats().getSpd());
                ta.append("\n" + currLocation.listOfCharacters.get("TROLL").getCharName() + " Stats: \n Hp = " +
                        currLocation.listOfCharacters.get("TROLL").getCharStats().getHP());
                ta.append("\n Str = " + currLocation.listOfCharacters.get("TROLL").getCharStats().getStr());
                ta.append("\n Def = " + currLocation.listOfCharacters.get("TROLL").getCharStats().getDef());
                ta.append("\n Spd = " + currLocation.listOfCharacters.get("TROLL").getCharStats().getSpd());
            }
        }
        //Attack logic
        if (currEvent.getName().equals("ATTACK")) {
            if (!theGoodFight) {
                ta.append("\n Maaaayyybbbbeeee you should try and initiate a fight before you go attacking someone");

            }
            //no overkill
            else if (currLocation.listOfCharacters.get("TROLL").getCharStats().getHP() <= 0) {
                alreadyLvl = true;
                ta.append("\n They are already dead R E L A X");
            }
            //Allow the Fight
            else {
                currLocation.Fight(player, currLocation.listOfCharacters.get("TROLL"), "ATTACK",restrictions);
                if(restrictions == 0) {
                    ta.append("\n You Attack the Troll and he Attacks back \n Player hp:" + player.getCharStats().getHP()
                            + "\n Enemy hp:" + currLocation.listOfCharacters.get("TROLL").getCharStats().getHP());
                }
                if(restrictions == 1){
                    ta.append("\n The Enemy went Berserk the Troll Raises his attack by one but lowers his defenses" +
                            "\n Player hp:" + player.getCharStats().getHP()
                            + "\n Enemy hp:" + currLocation.listOfCharacters.get("TROLL").getCharStats().getHP());
                }
                if(restrictions == 2){
                    ta.append("\n The Troll unleashes a devastating special attack SPIKED SHIELD" +
                            "\n Player hp:" + player.getCharStats().getHP() +
                             "\n Enemy hp:" + currLocation.listOfCharacters.get("TROLL").getCharStats().getHP());
                }
            }
        }
        if (currEvent.getName().equals("DEFEND")) {
            if (!theGoodFight) {
                ta.append("\n What are you even trying to block");
            } else if (currLocation.listOfCharacters.get("TROLL").getCharStats().getHP() <= 0) {
                alreadyLvl = true;
                ta.append("\n There is no t-bagging in this game you look like a fool");
            } else if (player.getCharStats().getDef() >= 30) {
                ta.append("\n Olivia Said your defense cant go that high");
            } else {
                currLocation.Fight(player, currLocation.listOfCharacters.get("TROLL"), "DEFEND",restrictions);
                ta.append("\n You raise your shield to the sun, imbued with sunlight your shield receives + 2 Defense");
                ta.append("\n Your Defense is now:" + player.getCharStats().getDef());
                if(restrictions == 0) {
                    ta.append("\n The Troll mocks you calling you a coward for trying to block his attack but " +
                            "ultimately did nothing");
                }
                if(restrictions == 1){
                    ta.append("\n The Enemy went Berserk the Troll Raises his attack by one but lowers his defenses" +
                            "\n Player hp:" + player.getCharStats().getHP()
                            + "\n Enemy hp:" + currLocation.listOfCharacters.get("TROLL").getCharStats().getHP());
                }
                if(restrictions == 2){
                    ta.append("\n The Troll unleashes a devastating special attack SPIKED SHIELD" +
                            "\n Player hp:" + player.getCharStats().getHP() +
                            "\n Enemy hp:" + currLocation.listOfCharacters.get("TROLL").getCharStats().getHP());
                }
            }
        }
        // This is the Level up section if the troll is killed the player will level up
        if(currLocation.listOfCharacters.get("TROLL").getCharStats().getHP()<=0 && !alreadyLvl){
            //Player level up
            player.getCharStats().resetHp(defaultHp);
            Random levelup = new Random();
            int hplvl = levelup.nextInt(4);
            int strlvl = levelup.nextInt(4);
            int deflvl = levelup.nextInt(4);
            int spdlvl = levelup.nextInt(4);
            defaultHp = player.getCharStats().getHP()+hplvl;
            player.getCharStats().setAllStats(player.getCharStats().getHP()+hplvl,
                    player.getCharStats().getStr() + strlvl,player.getCharStats().getSpd() +spdlvl,
                    player.getCharStats().getDef() +deflvl);
            ta.append("\n You Leveled up!!!!!! \n Level" + player.getCharStats().getLevel() + "->" +
                    (player.getCharStats().getLevel() + 1));
            player.getCharStats().levelUp(player.getCharStats().getLevel()+1);
            ta.append("\n Increased Stats to: \n Hp = " + player.getCharStats().getHP());
            ta.append("\n Str = " + player.getCharStats().getStr());
            ta.append("\n Def = " + player.getCharStats().getDef());
            ta.append("\n Spd = " + player.getCharStats().getSpd());
        }
        //End of Cameron's Fight Shenanigans
    }

    /*******************************************************************************************************************
     * listener class relates user input to game play
     * This includes buttons and user entered commands (strings)
     ******************************************************************************************************************/
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //updates text area with flavortext
            if (send == event.getSource()) {
                updateEvent(tf.getText());}
            //resets text box for user commands
            if (reset == event.getSource())
                tf.setText(null);
            //help button prints out list of possible commands
            if (helpButton == event.getSource()) {
                ta.append("\nYou can do the following commands.");
                for (String command : currLocation.mapOfEvents.keySet()) {
                    ta.append("\n" + command);
                }
                ta.append(dicFlavorText.get(""));
            }
            //prints map and current location for debugging/easy  mode
            if (mapButton == event.getSource()) {
                ta.append(dicFlavorText.get("map"));
                ta.append("\n\nYou are at the " +
                        currLocation.name.toLowerCase());
                ta.append(dicFlavorText.get(""));
            }
            //prints conversation options for debugging/easy game mode
            if (convoChoices == event.getSource()) {
                if (isConvo) {
                    ta.append("\nYou're talking to " +
                            currChar.getCharName() +
                            "\nYou can say the following\n");
                    ta.append(currChar.getSpeechOptions());
                } else ta.append("\nYou're not talking to anyone.");
                ta.append(dicFlavorText.get(""));
            }
            if (fightChoices == event.getSource() && currLocation.name.equals("BRIDGE")) {
                ta.append(dicFlavorText.get(""));
                ta.append("You can fight" + "Gargafart the Troll");
                ta.append("You Have access to the following commands while in a fight" +
                        "\n Attack: You will hit the Troll for your attack minus his defense (Must Fight Troll First)" +
                        "\n Defend: This will Raise your Defense by 2(Must Fight Troll First)" +
                        "\n Fight Troll: This will antagonize the Troll into a fight(Can only Start once)");
                }
            }
        }
    }

//end GamePanel class