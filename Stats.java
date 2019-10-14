public class Stats {

    private int hp, strength, speed, defense;
    //creates default stats
    public Stats(){
        hp=1;
        strength=1;
        speed=1;
        defense =1;
    }
    //creates states based on input
    public Stats(int hp, int strength, int speed, int def){
        this.hp = hp;
        this.strength = strength;
        this.speed = speed;
        this.defense = def;
    }
    
    public void damageCalculations(int hit){
    hp -= hit-defense;
    }
    
    public void changeStr(int i){
        strength += i;
    }
    public void changeDef(int i){
        defense += i;
    }
    public void changeSpd(int i){
        speed+= i;
    }
    public int getHP(){
        return hp;
    }
    public int getStr(){
        return strength;
    }
    public int getDef(){
        return defense;
    }
    public int getSpd(){
        return speed;
    }
}
