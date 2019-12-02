/**********************************************************************
 * This is the Stats page.  It holds all information on the stats of
 * characters
 * @author Cameron Shearer
 *********************************************************************/
public class Stats {

    //Instance variables for all the stats
    private int hp, strength, speed, defense,level;
    /******************************************************************
     * Sets The Default Stats for a Character
     *****************************************************************/
    public Stats(){
        level = 5;
        hp=1;
        strength=1;
        speed=1;
        defense =1;
    }

    /******************************************************************
     * Sets The Default Stats for a Character based on given parameters
     * @param hp How many hit points a character has
     * @param strength How much damage the character will do
     * @param def How much damage a character can blcok
     * @param speed How fast the character is determines turn order
     *****************************************************************/
    public Stats(int hp, int strength, int speed, int def){
        this.hp = hp;
        this.strength = strength;
        this.speed = speed;
        this.defense = def;
    }

    /******************************************************************
     * Sets The Default Stats for a Character based on given parameters
     * used to update unlike the initialization version
     * @param health How many hit points a character has
     * @param strength How much damage the character will do
     * @param defense How much damage a character can blcok
     * @param speed How fast the character is determines turn order
     *****************************************************************/
    public void setAllStats(int health, int strength, int speed, int defense){
        this.hp = health;
        this.strength = strength;
        this.speed = speed;
        this.defense = defense;
    }
    /******************************************************************
     * Calculates the damage step of an attack
     * @param hit how much damage is being Dealt
     *****************************************************************/
    public void damageCalculations(int hit){
        hp -= hit - defense;
    }

    /******************************************************************
     * Updates only Strength
     * @param i number to add to strength
     *****************************************************************/
    public void changeStr(int i){
        strength += i;
    }

    /******************************************************************
     * This method is to reset hp to original after a fight
     * @param i original hp
     *****************************************************************/
    public void resetHp(int i){
        hp = i;
    }

    /******************************************************************
     * Level up the character
     * @param i level of character
     *****************************************************************/
    public void levelUp(int i){
        level = i;
    }
    /******************************************************************
     * Updates only Defense
     * @param i number to add to defense
     *****************************************************************/
    public void changeDef(int i){
        defense += i;
    }

    /******************************************************************
     * Updates only Speed
     * @param i number to add to speed
     *****************************************************************/
    public void changeSpd(int i){
        speed+= i;
    }

    /******************************************************************
     * Returns HP
     * @return hp characters hit points
     *****************************************************************/
    public int getHP(){
        return hp;
    }

    /******************************************************************
     * Returns Strength
     * @return strength characters attack power
     *****************************************************************/
    public int getStr(){
        return strength;
    }

    /******************************************************************
     * Returns Defense
     * @return defense characters Defenses
     *****************************************************************/
    public int getDef(){
        return defense;
    }

    /******************************************************************
     * Returns Speed
     * @return speed characters agility
     *****************************************************************/
    public int getSpd(){
        return speed;
    }

    /******************************************************************
     * Returns Level
     * @return the level of the character
     *****************************************************************/
    public int getLevel(){
        return level;
    }
}
