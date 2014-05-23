import java.util.Scanner;
/**
 * Write a description of class instances here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class instances
{
    public static Scanner keyboard = new Scanner(System.in);
    public static void main(String[]ars) {
        instance();
    }

    // Initiates the "meeting" of each enemy
    public static void instance() {
        // Create an enemy, which scales to players current level.
        // Message: A wild "" has appeared!        
        // conduct battle: int result = fight(player, enemy);
        // fight will return -1 for loss, 0 for run, or 1 for victory. 
        // Return health back to normal, apply victory/loss effect, give player experience if victory
    }

    // Moves to fighting stage, and asks player for their decision
    public static int fight(Player h, Enemy c) {
        // Display status of both Player and Enemy
        System.out.println(h);
        System.out.println(c);
        // Message/Input: What would you like to do? 1) Attack 2) Run
        int n = keyboard.nextInt();
        int result = 0;
        boolean skipAttack = false;
        // if:  n = 2 send it to run method. Returns boolean in variable runResult.
        // else: send player, enemy, and skipAttack to attack method 
        // which returns -1 for loss, 1 for win, or 2 to continue fight (neither character has fainted).   
        if (n == 2) {    
            //if successful, return result (which equals 0).
            if (run()) {return result;}  
            //else: Message: "Failed to run". Players attacked will be skipped in attack method.
            skipAttack = true;
        }             
        else {
            result = attack(h,c,skipAttack);
        }
        // If result is 2, player has another move
        if (result == 2){fight(h,c);}
        return result;
    }

    // Damage is inflicted
    public static int attack(Player h, Enemy c, boolean skipAttack) {
        // Random numbers which indicates Player/Enemy attack array index number, selects how much damage will be done.
        int playerAttack = (int) (Math.random()* (h.atk.length-1));
        int enemyAttack  = (int) (Math.random()* (c.atk.length-1));
        // Player Attacks, if skipAttack is not true.
        if (skipAttack == false) {
            System.out.println("playerName did " +h.atk[playerAttack]+ " damage!");
            c.setHP(c.getHP() - h.atk[playerAttack]);
            // If enemy looses all hp, notify the player and return 1
            if (c.getHP() <= 0) {
                System.out.println("Enemy has fainted!");
                return 1;
            }
        }
        // Enemy Attacks
        System.out.println("Enemy did " +c.atk[enemyAttack]+ " damage!");
        h.setHP(h.getHP() - c.atk[enemyAttack]);
        // If player looses all hp, notify the player and return -1
        if (c.getHP() <= 0) {
            System.out.println("Player +playerName+ has fainted!");
            return -1;
        }
        // If neither the Player nor enemy has fainted, return 2.
        return 2;
    }

    public static Boolean run() {
        if ((Math.random()*100) < 50) {return false;}
        return true; 
    }
}
