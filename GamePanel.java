import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class GamePanel extends JPanel {
    /** Unused yet.  Has image loaded into GUI */
    private ImageIcon testImage;
    /** menu for GUI */
    public JMenuBar mb;
    JMenu m1, m2;
    JMenuItem m11, m22;
    public JPanel panel;
    JLabel label;
    /** user puts commands in this line */
    JTextField tf;
    /** sends text in user command line to panel */
    JButton send;
    /** button to reset text in input line in GUI */
    JButton reset;
    /** text displayed on GUI */
    JTextArea ta;
    /** listens to mouse clicks for GUI and relays them to panel*/
    ActionListener listener;
    /** pointer to player's current location*/
    Location currLocation;
    /** might delete*/
    Location prevLocation;
    /** All locations are stored in a map*/
    Map<String, Location> listAllLocations;

    public GamePanel() {
        listener = new listener();
        setMenu();
        //Creating the panel at bottom and adding components
        setPanel();
        createLocations();
        // Text Area at the Center
        ta = new JTextArea("Welcome!  I worked so hard on this only to delete everything!" +
                "  It's ok.  I rebuilt it and did it clean this time! You should type LOOK");

    }
    private void createLocations(){
        listAllLocations = new HashMap<String, Location>();
        listAllLocations.put("GATE", new Gate());
        listAllLocations.put("BRIDGE", new Bridge());
        //listAllLocations.put("FIGHT", new Fight());
        currLocation = listAllLocations.get("GATE");
        
    }

    private void setPanel() {
        panel = new JPanel();
        label = new JLabel("Enter Text");
        tf = new JTextField(20); // accepts up to 20 characters
        send = new JButton("Send");
        send.addActionListener(listener);
        reset = new JButton("Reset");
        reset.addActionListener(listener);
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);
    }

    /**
     * helper method to set up Menu for GUI
     */
    private void setMenu() {
        //Creating the MenuBar and adding components
        mb = new JMenuBar();
        m1 = new JMenu("FILE");
        m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        m11 = new JMenuItem("Open");
        m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
    }

    /**
     * helper method that sets background image
     * TODO not working right now
     */
    private void createBackgroundImages() {
        testImage = new ImageIcon("./src/CIS350");
    }

    /**
     * Passes user command to location and gets Event.  This is where
     * the actual updating of the game state occurs.
     * If we do a save game option, it will probably reference this
     * method
     * @param userCommand
     */
    private void updateEvent(String userCommand){
        //converts user input to Upper case for simplicity
        String cmd = userCommand.toUpperCase();

        //only accepts valid commands
        try{
            Event currEvent = currLocation.getEvent(cmd);
            if (currEvent != null)
                ta.setText(currEvent.getFlavorText());

            //updates location if event has a location
//            if (currEvent.getLocation() != null)
//                currLocation = listAllLocations.get(currEvent.getLocation());
//            if (!currLocation.getName().equals("FIGHT"))
//                prevLocation = currLocation;
        } catch(NullPointerException e){
            //resets user text area if invalid command
            tf.setText(null);
        }

    }

    /**
     * Private class that contains listener.  Listener is used to
     * connect buttons in GUI to panel
     */
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //sends user Command to panel
            if (send == event.getSource())
                updateEvent(tf.getText());
            //resets text area for user
            if (reset == event.getSource())
                tf.setText(null);
        }
    }
}
