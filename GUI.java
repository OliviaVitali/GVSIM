import javax.swing.*;
import java.awt.*;

public class GUI {

    public static void main(String[] args){

        JFrame frame = new JFrame("GV Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setBackground(Color.PINK);

        GamePanel panel = new GamePanel();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, panel.mb);
        frame.getContentPane().add(BorderLayout.SOUTH, panel.panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel.scroll);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(panel.send);
    }
}
