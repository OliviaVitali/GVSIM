import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class GamePanel extends JPanel {
    /** Unused yet.  Has image loaded into GUI */
    ImageIcon icon = new ImageIcon("THaas.jpg");

    /** menu for GUI */
    public JMenuBar mb;
    JMenu m1;
    JButton m2;
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
    /** listens to mouse clicks for GUI and relays them to panel*/
    ActionListener listener;
    /** pointer to player's current location*/
    Location currLocation;
    /** might delete*/
    Location prevLocation;
    /** All locations are stored in a map*/
    Map<String, Location> listAllLocations;
    /**Boolean that make sure user initiated a fight**/
    boolean theGoodFight;

    public GamePanel() {
        listener = new listener();
        // Text Area at the Center
        ta = new JTextArea(5, 5);
        ta.setText("Welcome to GVSimulator! Type LOOK to take a peek at the world around you!");
        scroll = new JScrollPane(ta);
        ta.setEditable(false);
        theGoodFight = false;
        setMenu();
        //Creating the panel at bottom and adding components
        setPanel();
        createLocations();

    }
    private void createLocations(){
        listAllLocations = new HashMap<String, Location>();
        listAllLocations.put("GATE", new Gate());
        listAllLocations.put("BRIDGE", new Bridge());
        listAllLocations.put("MACKINAC", new Mackinac());
        //listAllLocations.put("FIGHT", new Fight());
        currLocation = listAllLocations.get("GATE");
        
    }

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

    private void setMenu() {
        //Creating the MenuBar and adding components
        mb = new JMenuBar();
        //m1 = new JMenu("File");
        m2 = new JButton("Help");
        //mb.add(m1);
        mb.add(m2);
        m2.addActionListener(listener);
        //m11 = new JMenuItem("Open");
        //m22 = new JMenuItem("Save as");
        //m1.add(m11);
        //m1.add(m22);
    }

    //private void createBackgroundImages() {
    //    testImage = new ImageIcon("./src/CIS350");
    // }
    private void updateEvent(String userCommand) {
        String cmd = userCommand.toUpperCase();

        try {
            Event currEvent = currLocation.getEvent(cmd);
            if (currEvent != null) {
                ta.append(currEvent.getFlavorText());
                ta.setCaretPosition(ta.getDocument().getLength());
                tf.setText(null);
            }

            //updates location if event has a location
            if (currEvent.getLocation() != null)
                currLocation = listAllLocations.get(currEvent.getLocation());

            //Start of Cameron's Fight Shenanigans
            if (currEvent.getName().equals("FIGHT")) {
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
                    ta.append("\n" + currLocation.listOfCharacters.get("TALK TO TROLL").getCharName() + " Stats: \n Hp = " +
                            currLocation.listOfCharacters.get("TALK TO TROLL").getCharStats().getHP());
                    ta.append("\n Str = " + currLocation.listOfCharacters.get("TALK TO TROLL").getCharStats().getStr());
                    ta.append("\n Def = " + currLocation.listOfCharacters.get("TALK TO TROLL").getCharStats().getDef());
                    ta.append("\n Spd = " + currLocation.listOfCharacters.get("TALK TO TROLL").getCharStats().getSpd());
                    currLocation.Fight(currLocation.listOfCharacters.get("TALK TO TROLL"), currLocation.listOfCharacters.get("TALK TO TROLL"), "START");
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

        } catch (NullPointerException e) {
            tf.setText(null);
        }
    }

        private class listener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (send == event.getSource())
                    updateEvent(tf.getText());
                if (reset == event.getSource())
                    tf.setText(null);
                if (m2 == event.getSource()) {
                    for (String command : currLocation.mapOfEvents.keySet()) {
                        String variableKey = command;
                        ta.append("\n" + variableKey);
                    }
                }
            }
        }
    }

//end GamePanel
