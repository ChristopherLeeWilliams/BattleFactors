
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Character 
{
    // Player Variables
    // protected int lv;     
    // protected int hp; 
    // protected int exp;    
    // protected int[] atk;  

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        this(0,10); // Default player data
    }

    public Player(int exp, int atk){
        this.lv = checkLV();
        this.hp = (this.lv * 20) + 10;
        this.exp = exp;
        this.atk = factoring(atk);
    }

    public int getHP(){
        return this.hp;
    }

    public void setHP(int n){
        this.hp = n;
    }
    
    public void fixHP(){
        this.hp = (this.lv * 20) + 10;
    }

    public int getLV(){
        return this.lv;
    }

    public void setLV(int n){
        this.lv = n; 
    } 

    public int getXP(){
        return this.exp;
    }

    public void setXP(int n){
        this.exp = n;
    }    

    public int checkLV(){
        int[] expNeeded = {10, 40, 90, 160, 250, 360, 490, 640, 810, 1000};
        int level = 0;
        for (int i = 0; i < expNeeded.length; i++){
            if (this.exp >= expNeeded[i]){
                level = i + 1;
            }
        }
        return level;
    }

    // Selects random index in player's atk array to delete, as a result of losing.
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
    }

    public void print(){
        String s = "Player:\nLevel: " + lv + "\nHit Points: " + hp + "\nExperience: " + exp + "\nAttack: [ ";
        for (int n : atk){
            s = s + n + " ";
        }
        s = s + "]\n";
        System.out.println(s);
    }

    //Adds a random number from the atk array of the defeated enemy as a result of winning. 
    public void victory(int n){
        int[] newAtk = new int[atk.length + 1];
        for (int i = 0; i < atk.length; i++){
            newAtk[i] = atk[i];
        }
        newAtk[atk.length] = n;
        this.atk = newAtk;
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
