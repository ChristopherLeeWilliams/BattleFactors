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
    public static boolean bossWin = false;
    public static void main(String[]args){
        System.out.print("\f");  
        intro();
        while (user.getLV() < 10 && user.atk.length >0) {
            instance();
            print("\nPress enter to continue ", 0);
            String enter = keyboard.nextLine();
            enter = keyboard.nextLine();
            print("\f",0);
        }
        while (user.atk.length>0 && bossWin == false){
            finalBattle();
            print("\nPress enter to continue ", 0);
            String enter = keyboard.nextLine();
            enter = keyboard.nextLine();
            print("\f",0);
        }        
        if (user.atk.length < 1){print("Game over! :(",2);}
    }
    
    // Displays opening message
    private static void intro(){
        print("What is your name great adventurer? ", 0);
        playerName = keyboard.nextLine();
        print(playerName + ", is that correct? (yes/no) ",0);
        String answer = keyboard.nextLine();
        if (checkAnswer(answer,"yes") == false){intro();}
        user.setPlayerName(playerName);
        print("\f                                   Welcome to the game of Battle Factors!", 2);
        print("     In this game, you will be faced with many enemies. The enemies will be scaled to your current level, from 1 to 10." ,1);
        print("Your object is simple, fight your way to level 10 and defeat the evil rank 100! There will be many battles along the" ,1);
        print("way. Winning these battle will not only give you experience, but will also increase your attack power. Losing a battle",1);
        print("does not mean game failure, but the penalty will reside in your attack. A battle lost results in a random attack number",1);
        print("from your attack array being deleted. If a player losses all numbers in their attack array, then it is game over. Run",1);
        print("when necessary and fight your way to the top!",2);
        print("                                          Good luck " + user.playerName + "!",1);
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
        // conduct battle: int result = fight(player, enemy), will return -1 for loss, 0 for run, or 1 for victory.          
        // If result is 2, player has another move
        int result = 2;
        while(result == 2){
            result = fight(user,cpu);
        }
        if  (result == 1) {
            int iLv = user.getLV();
            print("You gained "+cpu.rank+" exp." ,2);            
            user.setXP(user.getXP() + cpu.rank);
            user.setLV(user.checkLV());
            if (user.checkLV()-iLv>0 ) {
                print("You leveled up and gained " +((user.getLV()+1) * 10)+ " to your attack array!",2);
                user.victory((user.getLV()+1) * 10);
            }
            user.fixHP();   
            user.print();
        }
        else if (result == 0) {
            print(user.playerName + " successfully ran!",1);
        }
        else {
            user.loss();
            print("You lost a random number from your attack array! ",2);
            user.print();
        }
        user.fixHP();        
        cpu = null; 
    }

    // Moves to fighting stage, and asks player for their decision
    public static int fight(Player h, Enemy c) {
        boolean skipAttack = false;
        // Display status of both Player and Enemy
        System.out.println(h);
        System.out.println(c);
        // Message/Input: What would you like to do?
        print("What would you like to do?\n1) Attack \n2) Run ",2);
        int n = keyboard.nextInt();
        print("\f",0);
        if (n != 1 && n != 2) {
            print("Invalid move X_X ",2);
            return 2;
        }
        // if:  n = 2 send it to run method.
        if (n == 2) {    
            if (c.rank == 100) {
                print("You can't run away!",2);
                return 2;
            }
            //if successful, return 0.
            if (run()) {return 0;}  
            //else: Message: "Failed to run". Players attacked will be skipped in attack method.
            print("Failed to run! ",1);
            skipAttack = true;
        }          
        // Player and Enemy attack (failed to run or chose to attack).
        return attack(h,c,skipAttack);
    }

    // Damage is inflicted
    public static int attack(Player h, Enemy c, boolean skipAttack) {
        // Random numbers which indicates Player/Enemy attack array index number, selects how much damage will be done. 
        // Has a chance to critically hit for twice damage and enemies have special effects.
        int p = h.atk[((int) (Math.random()* (h.atk.length-1)))];
        int e = c.atk[((int) (Math.random()* (c.atk.length-1)))];
        int playerAttack = critical(p);
        int enemyAttack = critical(e);
        // Player Attacks, if skipAttack is not true.
        if (skipAttack == false) {
            print(user.playerName + " did " +playerAttack+ " damage. ",0);
            if (playerAttack == 2*p) {print("A critical hit! ",0);}
            else if (playerAttack == 3*p) {print("You hit the enemy's weak spot!",0);}
            print("",2);
            c.setHP(c.getHP() - playerAttack);
            // If enemy looses all hp, notify the player and return 1
            if (c.getHP() <= 0) {
                print("Enemy has fainted!",2);
                return 1;
            }
        }
        // Enemy Attacks
        print("Enemy did " +enemyAttack+ " damage. ",0);
        if (enemyAttack > e){
            if (c.rank < 100){print("A critical hit! Enemy leeched some health!",0);}
            else {print("A critical hit! Enemy leeched a lot of health!",0);}
            if ((c.rank % 2) ==0 || c.rank == 100) { // Even ranked enemies or rank 100 have the special ability to leech half the damage they inflict on critical hits.
                c.setHP(c.getHP() + Math.round(enemyAttack/2));
            }
            else if((c.rank % 2) == 1 || c.rank == 100) { // Odd ranked enemies or rank 100 have the special ability to leech half the damage last inflicted on them on critical hits.                
                c.setHP(c.getHP() + Math.round(playerAttack/2));
            }
        }
        print("",2);
        h.setHP(h.getHP() -enemyAttack);
        // If player looses all hp, notify the player and return -1
        if (h.getHP() <= 0) {
            print("Player " +user.playerName+ " has fainted!",2);
            return -1;
        }
        // If neither the Player nor enemy has fainted return 2.
        return 2;
    }

    public static void finalBattle(){
        int[] bossAttack = {10,20,30,40,50,60,70,80,90,100};
        Enemy boss = new Enemy(10);
        boss.setRank(100);
        boss.setHP(300);
        boss.setAtk(bossAttack);

        print("Prepare yourself! The evil rank 100 has appeared!",2);
        int result = 2;
        while(result == 2){
            result = fight(user,boss);
        }
        if (result == 1) {
            print("Congratulations! You defeated all of the battle factors!",2);
            bossWin = true;
        }
        if (result == -1) {
            user.fixHP(); 
            user.loss();
            user.loss();
            print("You lost 2 random numbers from your attack array! ",2);
            user.print();
            boss = null; 
        }
    }

    public static Boolean checkAnswer(String a, String b){
        if (a.trim().equalsIgnoreCase(b)){return true;}
        return false;
    }

    public static int critical(int n){
        double a = (Math.random()*100);
        if (a>90) {return 2*n;}
        else if (a<=2) { return 3*n;}
        return n;
    }

    public static Boolean run() {
        if ((Math.random()*100) < 50) {return false;}
        return true;
    }

    private static void print(String s, int n){
        System.out.print(s);
        for (int i = 0; i<n;i++){
            System.out.println();
        }
    }
}
