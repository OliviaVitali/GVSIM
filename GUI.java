import javax.swing.*;
import java.awt.*;

/**
 * Displays game to user.  Contains no game logic
 * @version 1
 */
public class GUI {

    public static void main(String[] args){

        /** frame for display */
        JFrame frame = new JFrame("GV Simulator");
        //sets commands for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        /** only instance of the game */
        GamePanel panel = new GamePanel();

        //Adding Components to the frame.
        panel.ta.setBackground(Color.PINK);
        frame.getContentPane().add(BorderLayout.NORTH, panel.mb);
        frame.getContentPane().add(BorderLayout.SOUTH, panel.panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel.scroll);
        frame.setVisible(true);
    }
}
