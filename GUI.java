import javax.swing.*;
import java.awt.*;

public class GUI {

    /**
    * main function for game
    * creates GUI for the game
    */
    public static void main(String[] args){
        /** frame that hold the game */
        JFrame frame = new JFrame("GV Simulator");
        //sets defaults for frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setBackground(Color.PINK);

        /** panel is the game itself */
        GamePanel panel = new GamePanel();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, panel.mb);
        frame.getContentPane().add(BorderLayout.SOUTH, panel.panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel.scroll);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(panel.send);
        //frame.add(panel.lbl);
        frame.setVisible(true);
    }
}
