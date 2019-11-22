import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
* this is the game.  It holds all the objects, players, locations and navigation through the game
* @author Olivia Vitali
* @version 4
*/
public class GamePanel extends JPanel {
    /**
     * Unused yet.  Has image loaded into GUI
     */
    ImageIcon icon = new ImageIcon("THaas.jpg");
    /**
     * menu for GUI
     */
    public JMenuBar mb;
    JMenu m1;
    JButton helpButton, mapButton, convoChoices, fightChoices;
    JMenuItem m11, m22;
    public JPanel panel;
    JLabel label, lbl;

    /**
     * user puts commands in this line
     */
    JTextField tf;
    /**
     * sends text in user command line to panel
     */
    JButton send;
    /**
     * button to reset text in input line in GUI
     */
    JButton reset;
    /**
     * text displayed on GUI
     */
    JTextArea ta;
    /**
     * makes the text area scrollable
     */
    JScrollPane scroll;
    /**
     * listens to mouse clicks for GUI and relays them to panel
     */
    ActionListener listener;
    /**
     * pointer to player's current location
     */
    Location currLocation;
    /**
     * All locations are stored in a map
     */
    Map<String, Location> listAllLocations;
    /**
     * Boolean that make sure user initiated a fight
     **/
    boolean theGoodFight;
    /**
     * Connects text box title to text box for more readable coding
     */
    Map<String, String> dicFlavorText;
    /**
     * determines if we're in a conversation loop
     */
    private boolean isConvo;
    /**
     * player character
     */
    Character player;
    /**
     * the npc currently interacted with
     */
    Character currChar;
    /**
     * current event being referenced
     */
    Event currEvent;

    /**
    * Constructor for GamePanel
    * Instantiates most objects referenced in game
    */
    public GamePanel() {
        /** connect GUI to panel */
        listener = new listener();
        /** Text Area at the Center */
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
        //by default, current character is set to Jenny.
        //if you reach Jenny, you've messed up
        currChar = new Character();
    }

    /**
    * helper method to create flavortext for game not used elsewhere
    */
    private void createdicFlavorText() {
      /** map of abbreviation and text for GUI*/
        dicFlavorText = new HashMap<>();
        dicFlavorText.put("intro",
                "Welcome to GVSimulator! " +
                        "Type LOOK to take a peek at the world around you!");
        dicFlavorText.put("credits", "This game was made by ORCS.");
        dicFlavorText.put("", "\n-------\n");

        dicFlavorText.put("GO", "\nYou are at " + currLocation);
        dicFlavorText.put("TALK", "\n You are talking to ");
    }

    /**
    * helper method for constructory
    * creates all locations for the game
    */
    private void createLocations() {
        listAllLocations = new HashMap<String, Location>();
        listAllLocations.put("GATE", new Gate());
        listAllLocations.put("BRIDGE", new Bridge());
        listAllLocations.put("MACKINAC", new Mackinac());
        //sets default location to GATE
        currLocation = listAllLocations.get("GATE");
        //added for flavor text helper in GUI
        dicFlavorText.put("map", "\nBridge--------Gate\n|\n|\n|\nMackinac");
    }

    /**
    * helper method for constructor
    * adds panel information for GUI
    */
    private void setPanel() {
        panel = new JPanel();
        label = new JLabel("Enter Text");
        //lbl.setIcon(icon); // Sets image
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

    /**
    * helper method for constructor
    * sets menu to items for panel
    */
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

    /**
    * when in a conversation, user is re-routed here to directly translate
    * user commands and returns character's responses
    */
    private void conversation(String cmd, Character c) {
        //handles conversation with npc

        //ta.append(dicFlavorText.get("TALK") + c.getCharName());

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

    /******************************************************************
     * Basically updates everything.  Gets related event from user cmd
     * If the player starts a conversation, they must either type "bye"
     * or another valid event name to leave the conversation
     * If the player starts a fight, they must either finish the fight
     * or enter another valid event to leave the fight
     * @param userCommand is the user's intention
     */
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
                theGoodFight = false;
            //favortext update for game
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
            //this is the character we're interacting with
            currChar = currEvent.getNpc();
            //triggers conversation with NPC
            if (cmd.contains("TALK")) {
                isConvo = true;
                conversation(cmd, currChar);
            } else if (cmd.contains("FIGHT")) {
                theGoodFight = true;
                CameronsFightShenanigans(currEvent, cmd, currChar);
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
            }
        } catch (NullPointerException e) {
            tf.setText(null);
        }

    }

    /******************************************************************
     * Updates game's fighting state
     * @param currEvent is the event related to the fight
     */
    private void CameronsFightShenanigans(Event currEvent, String userCommand, Character opp) {
        //Start of Cameron's Fight Shenanigans
        if (currEvent.getName().equals("FIGHT TROLL")) {
            if (userCommand.contains("FIGHT"))
                ta.append(opp.getSpeech(userCommand));

            if (currLocation.listOfCharacters.get("TALK TO TROLL").getCharStats().getHP() <= 0) {
                ta.append("\n You can't fight a dead body -_-");

            } else if (theGoodFight == true) {
                ta.append("\n You are already in a fight. LOOK ALIVE.");

            } else {
                ta.append("\n LET THE FIGHT BEGIN");
                ta.append("\n Player Stats: \n Hp = " + currLocation.listOfCharacters.get("PLAYER").getCharStats().getHP());
                ta.append("\n Str = " + currLocation.listOfCharacters.get("PLAYER").getCharStats().getStr());
                ta.append("\n Def = " + currLocation.listOfCharacters.get("PLAYER").getCharStats().getDef());
                ta.append("\n Spd = " + currLocation.listOfCharacters.get("PLAYER").getCharStats().getSpd());
                ta.append("\n" + currLocation.listOfCharacters.get("FIGHT TROLL").getCharName() + " Stats: \n Hp = " +
                        currLocation.listOfCharacters.get("FIGHT TROLL").getCharStats().getHP());
                ta.append("\n Str = " + opp.getCharStats().getStr());
                ta.append("\n Def = " + currLocation.listOfCharacters.get("FIGHT TROLL").getCharStats().getDef());
                ta.append("\n Spd = " + currLocation.listOfCharacters.get("FIGHT TROLL").getCharStats().getSpd());
                currLocation.Fight(currLocation.listOfCharacters.get("FIGHT TROLL"), currLocation.listOfCharacters.get("TALK TO TROLL"), "START");
                theGoodFight = true;
            }
        }
        if (currEvent.getName().equals("ATTACK")) {
            if (theGoodFight == false) {
                ta.append("\n Maaaayyybbbbeeee you should try and initiate a fight before you go attacking someone");

            } else if (currLocation.listOfCharacters.get("TALK TO TROLL").getCharStats().getHP() <= 0) {
                ta.append("\n They are already dead R E L A X");
            } else {
                currLocation.Fight(currLocation.listOfCharacters.get("PLAYER"), currLocation.listOfCharacters.get("TALK TO TROLL"), "ATTACK");
                ta.append("\n You Attack the Troll and he Attacks back \n Player hp:" + currLocation.listOfCharacters.get("PLAYER").getCharStats().getHP()
                        + "\n Enemy hp:" + currLocation.listOfCharacters.get("TALK TO TROLL").getCharStats().getHP());
            }
        }
        if (currEvent.getName().equals("DEFEND")) {
            if (theGoodFight == false) {
                ta.append("\n What are you even trying to block");
            } else if (currLocation.listOfCharacters.get("TALK TO TROLL").getCharStats().getHP() <= 0) {
                ta.append("\n There is no t-bagging in this game you look like a fool");
            } else if (currLocation.listOfCharacters.get("PLAYER").getCharStats().getDef() >= 30) {
                ta.append("\n Olivia Said your defense cant go that high :(");
            } else {
                currLocation.Fight(currLocation.listOfCharacters.get("PLAYER"), currLocation.listOfCharacters.get("TALK TO TROLL"), "DEFEND");
                ta.append("\n You raise your shield to the sun, imbued with sunlight your shield receives + 2 Defense ");
                ta.append("\n Your Defense is now:" + currLocation.listOfCharacters.get("PLAYER").getCharStats().getDef());
                ta.append("\n The Troll mocks you calling you a coward for trying to block his attack but ultimately do nothing");
            }
        }
        //End of Cameron's Fight Shenanigans

    }

    /******************************************************************
     * listener class relates user input to game play
     * This includes buttons and user entered commands (strings)
     */
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //updates text area with flavortext
            if (send == event.getSource())
                updateEvent(tf.getText());
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
            if (fightChoices == event.getSource()) {
                ta.append(dicFlavorText.get(""));
                if (theGoodFight)
                    ta.append("You're in a fight with " + currChar.getCharName());
                else ta.append("You're not in a fight.");
                ta.append(dicFlavorText.get(""));
            }
        }
    }
}

//end GamePanel
