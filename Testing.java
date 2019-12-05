import org.junit.Test;
import static org.junit.Assert.*;

/***********************************************************************************************************************
 * A class used to test for code coverage on the project.
 * @author Sarah Arnott
 * @author Riley Hernandez
 **********************************************************************************************************************/
public class Testing {
    /** What we need to test (what the project does):
     * Talk to T Haas, Neurochem
     * Fight troll
     * Change locations
     * Create locations
     * Click buttons
     * COMMANDS
     */

    //Location testing: make location, check all events exist/have proper flavortext, npcs exist, map has proper item(s),
    //proper description. NOTE: DO NOT change line formatting of flavortext or assertEquals will fail!
    @Test
    public void LocationTest(){
        /** Gate location */
        Gate g = new Gate();
        //look event
        assertNotNull(g.getEvent("LOOK"));
        assertEquals(g.getEvent("LOOK").getFlavorText(), "\nIt's Saturday and you've been wandering " +
                "around campus because...let's face it, you " +
                "didn't really have anything else to do.\n" +
                "By mistake, you've wandered under a large " +
                "blue sculpture only to remember that you" +
                " weren't supposed to walk under that.\nThe" +
                " world shimmers and you see T Haas ahead, " +
                "handing out ice cream.  Behind you is a bridge.");
        //go to bridge event
        assertEquals("\nYou walk towards the bridge", g.getEvent("GO TO BRIDGE").getFlavorText());
        assertNotNull(g.getEvent("GO TO BRIDGE"));

        //talk to t haas event
        assertNotNull(g.getEvent("TALK TO T HAAS"));
        assertEquals(g.getEvent("TALK TO T HAAS").getFlavorText(), "\n You walk up to a man at an ice cream cart." +
                "\n He smiles at you.");
        assertNotEquals(g.getEvent("TALK TO T HAAS").getNpc(), new Character()); //not Jenny

        //map item
        assertEquals(g.getMap(), "BRIDGE");

        //test name of location
        assertEquals(g.getName(), "GATE");

        //test fight
       assertEquals(g.Fight(new Character(), new Character(), "!", 1), 0);


        /** Bridge location */ //todo: test troll death
        Bridge b = new Bridge();
        //PRE-TROLL DEATH
        //look event
        assertNotNull(b.getEvent("LOOK"));
        assertEquals(b.getEvent("LOOK").getFlavorText(), "\nIt's Saturday and you've been wandering " +
                "around campus because...let's face it, you " +
                "didn't really have anything else to do.\n" +
                "By mistake, you've wandered under a large " +
                "blue sculpture only to remember that you" +
                " weren't supposed to walk under that.\nThe" +
                " world shimmers and you see T Haas ahead, " +
                "handing out ice cream.  Behind you is a bridge.");
        //go to gate event
        assertEquals("\nYou walk towards the gate ", b.getEvent("GO TO GATE").getFlavorText());
        assertNotNull(b.getEvent("GO TO GATE"));

        //go to padnos event
        assertEquals("You try to cross the bridge, but " +
                "the troll blocks your path!", b.getEvent("GO TO PADNOS").getFlavorText());

        //map item
        assertEquals(g.getMap(), "GATE");

        //test name of location
        assertEquals(g.getName(), "GATE");

        //test fight
        assertEquals(g.Fight(new Character(), new Character(), "!", 1), 0);



    }

}
