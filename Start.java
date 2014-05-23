import java.util.Scanner;
/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start
{
    public static Scanner keyboard = new Scanner(System.in);
    public static String playerName;
    public static Player user = new Player();
    public static int fullHP = (user.lv * 20) + 10;
    public static void main(String[]args){
        System.out.print("\f");  
        intro();
        instance();
    }

    // Displays opening message
    private static void intro(){
        print("What is your name great adventurer? ", 0);
        playerName = keyboard.nextLine();
        print(playerName + ", is that correct? (yes/no) ",0);
        String answer = keyboard.nextLine();
        if (checkAnswer(answer,"yes") == false){intro();}

        print("\f                                   Welcome to the game of Battle Factors!", 2);
        print("     In this game, you will be faced with many enemies. The enemies will be scaled to your current level, from 1 to 10." ,1);
        print("Your object is simple, fight your way to level 10 and defeat the evil rank 100! There will be many battles along the" ,1);
        print("way. Winning these battle will not only give you experience, but will also increase your attack power. Losing a battle",1);
        print("does not mean game failure, but the penalty will reside in your attack. A battle lost results in a random attack number",1);
        print("from your attack array being deleted. If a player losses all numbers in their attack array, then it is game over. Run",1);
        print("when necessary and fight your way to the top!",2);
        print("                                          Good luck " + playerName + "!",1);
        print("\nPress enter to continue ", 0);
        String enter = keyboard.nextLine();
        print("\f",0);
    }

    // Initiates "Meeting" between player and enemies
    private static void instance(){
        // Create an enemy, which scales to players current level.
        Enemy cpu = new Enemy(user.lv);
        // Message: A wild "" has appeared!  
        print("A wild Rank " + cpu.rank + " has appeared!",2);
        // conduct battle: int result = fight(player, enemy);
        // fight will return -1 for loss, 0 for run, or 1 for victory. 
        int result = fight(user,cpu);        
        if (result == 0) {
            print(playerName + " successfully ran!",1);
        }
        else if  (result == 1) {
            int winAtk = (int)(Math.random()*(cpu.atk.length - 1));
            user.victory(cpu.atk[winAtk]);
            user.setXP(user.getXP() + cpu.rank);
            user.print();
        }
        else if (result == -1) {
            user.loss();
        }
        user.setHP(fullHP);
        cpu = null; 
    }

    // Moves to fighting stage, and asks player for their decision
    public static int fight(Player h, Enemy c) {
        int result = 0;
        boolean skipAttack = false;
        // Display status of both Player and Enemy
        System.out.println(h);
        System.out.println(c);
        // Message/Input: What would you like to do? 1) Attack 2) Run
        print("What would you like to do?\n1) Attack \n2) Run ",2);
        int n = keyboard.nextInt();
        print("\f",0);
        if (n != 1 && n != 2) {
            print("Invalid move! ",2);
            fight(h,c);
        }
        // if:  n = 2 send it to run method. Returns boolean in variable runResult.
        // else: send player, enemy, and skipAttack to attack method 
        // which returns -1 for loss, 1 for win, or 2 to continue fight (neither character has fainted).   
        if (n == 2) {    
            //if successful, return result (which equals 0).
            if (run()) {return result;}  
            //else: Message: "Failed to run". Players attacked will be skipped in attack method.
            print("Failed to run! ",1);
            skipAttack = true;
        }          
        // Player and Enemy attack (failed to run or chose to attack).
        result = attack(h,c,skipAttack);        
        // If result is 2, player has another move
        if (result == 2){
            fight(h,c);
        }
        return result;
    }

    // Damage is inflicted
    public static int attack(Player h, Enemy c, boolean skipAttack) {
        // Random numbers which indicates Player/Enemy attack array index number, selects how much damage will be done.
        int playerAttack = (int) (Math.random()* (h.atk.length-1));
        int enemyAttack  = (int) (Math.random()* (c.atk.length-1));
        // Player Attacks, if skipAttack is not true.
        if (skipAttack == false) {
            print(playerName + " did " +h.atk[playerAttack]+ " damage!",1);
            c.setHP(c.getHP() - h.atk[playerAttack]);
            // If enemy looses all hp, notify the player and return 1
            if (c.getHP() <= 0) {
                print("Enemy has fainted!",2);
                return 1;
            }
        }
        // Enemy Attacks
        print("Enemy did " +c.atk[enemyAttack]+ " damage!",2);
        h.setHP(h.getHP() - c.atk[enemyAttack]);
        // If player looses all hp, notify the player and return -1
        if (c.getHP() <= 0) {
            print("Player " +playerName+ " has fainted!",2);
            return -1;
        }
        // If neither the Player nor enemy has fainted, return 2.
        return 2;
    }

    public static Boolean run() {
        if ((Math.random()*100) < 50) {return false;}
        return true;
    }

    public static Boolean checkAnswer(String a, String b){
        if (a.trim().equalsIgnoreCase(b)){return true;}
        return false;
    }

    private static void print(String s, int n){
        System.out.print(s);
        for (int i = 0; i<n;i++){
            System.out.println();
        }
    }
}
