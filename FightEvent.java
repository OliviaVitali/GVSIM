public class FightEvent extends Event {

    private Character player;

    public FightEvent(Character npc, Character player) {
        flavorText = "\nGeneric fight constructor triggered.";
        name = "FIGHT " + npc.getCharName();
        this.npc = npc;
        this.player = player;
        System.out.println(name);

    }

    /******************************************************************
     * Character a attacks Character b
     * @param a attacker character
     * @param b victim character
     */
    public void Attack(Character a, Character b) {
        b.getCharStats().damageCalculations(a.getCharStats().getStr());
    }
}
