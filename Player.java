
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Character 
{
    // Player Variables
    //protected int lv;      // Default = 1
    //protected int hp;      // Default = 20: (lv * 20)
    ///protected int exp;     // Default = 0
    //protected int[] atk;   // Default = 10: (1,2,5,10)
    protected int wins;    // Tallies Battles won
    protected int losses;  // Tallies Battles lost
    
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        this(0,10); // Default player data
    }

    public Player(int exp, int atk){
        this.lv = checkLv(exp);
        this.hp = lv * 20;
        this.exp = exp;
        this.atk = factoring(atk);
    }

    public void setAtk(int n){
        this.atk = factoring(n);
    }
    
    private static int checkLv(int xp){
        int[] expNeeded = {10, 40, 90, 160, 250, 360, 490, 640, 810, 1000};
        int level = 0;
        for (int i = 0; i < expNeeded.length; i++){
            if (xp > expNeeded[i]){
                level = i + 1;
            }
        }
        return level;
    }

    // Adds a random number from the atk array of the defeated enemy as a result of winning. 
    public void victory(int n){
        int[] newAtk = new int[atk.length + 1];
        for (int i = 0; i < atk.length; i++){
            newAtk[i] = atk[i];
        }
        newAtk[atk.length] = n;
        this.atk = newAtk;
        this.wins++;
    }

    // selects random index in player's atk array to delete, as a result of losing.
    public void loss(){
        int[] newAtk = new int[atk.length - 1];
        int counter = 0;
        int del = (int)(Math.random()*(atk.length - 1)); 
        for (int i = 0; i < atk.length; i++){
            if (i != del){
                newAtk[counter] = atk[i];
                counter++;
            }
        }
        this.atk = newAtk;
        this.losses++;
    }

    @Override
    public String toString(){
        String s = "Player:\nLevel: " + lv + "\nHit Points: " + hp + "\nExperience: " + exp + "\nAttack: [ ";
        for (int n : atk){
            s = s + n + " ";
        }
        s = s + "]\n";
        return s;
    }
}
