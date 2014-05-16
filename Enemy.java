
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Character
{
    // Enemy Variables, scales to players current level
    // protected int rank; 
    // protected int hp;
    // protected int[] atk;
    /**
     * Constructor for objects of class Player
     */
    public Enemy()
    {
        this(1); // Default enemy stats, scales to player level 1
    }

    public Enemy(int n){
        int playerLv = n;
        this.lv = playerLv;
        this.rank = chooseRank(playerLv);      
        this.hp = (8 * playerLv) + rank;
        this.atk = factoring(rank);
    }

    public static int chooseRank(int playerLv){
        int n = ((playerLv -1) * 10) + (int)(Math.random()*10);
        int length = countFactors(n).length;
        if (length < 3){
            if ((int)(Math.random()*10*(20-playerLv)) != 5){
                n = n + 1;
            }
        }
        
        if (n == 0){
            n = 1;
        }
        return n; 
    }
    
    public void setAtk(int n){
        this.atk = factoring(n);
    }

    @Override
    public String toString(){
        String s = "Enemy:\nLevel:" + lv + "\nRank: " + rank + "\nHit Points: " + hp + "\nAttack: [ ";
        for (int n : atk){
            s = s + n + " ";
        }
        s = s + "]\n";
        return s;
        }
    }
