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
    public static void main(String[]args){
        System.out.print("\f");  
        intro();
        instance();
    }
    
    private static int attack(Player h, Enemy c, int n){
        int hSelect = (int)(Math.random() * (h.atk.length -1));
        int cSelect = (int)(Math.random() * (c.atk.length -1));
        if (n == 1){
            print("Player did " + h.atk[hSelect] + " damage!",2);
            c.setHP(c.getHP() - h.atk[hSelect]);
            if (c.getHP()<=0){
                print("The enemy fainted!",1);
                return 1;
            }
        }
        print("Enemy did " + c.atk[cSelect] + " damage!",2);
        h.setHP(h.getHP() - c.atk[cSelect]);
        if (h.getHP()<=0){
            print("Player " + playerName + " has fainted!",1);
            return -1;
        }
        else{ choice(h,c);}
        return 0;
    }

    public static Boolean checkAnswer(String a, String b){
        if (a.trim().equalsIgnoreCase(b)){return true;}
        return false;
    }

    public static int choice(Player h, Enemy c){
        int result = 0;
        h.print();
        c.print();
        print("What would you like to do?\n1) Attack \n2) Run ",2);
        int answer = keyboard.nextInt();
        print("\f",0);
        if (answer == 1){result = attack(h,c,1);}
        else if (answer == 2){
            double chance = Math.random()*10;               
            if (chance <= 5){
                print("Failed to run!",1);
                attack(h,c,0);
            }
            else {
                return result;
            }            
        }
        else {
            print("Not a valid selection!",2);
            choice(h,c);
        }
        return result;
    }

    private static void instance(){
        Enemy cpu = new Enemy(user.lv);
        print("A wild Rank " + cpu.rank + " has appeared!",2);
        int fullHP = user.lv * 20;
        int result = choice(user,cpu);
        int winAtk = (int)(Math.random()*(cpu.atk.length - 1));
        if (result == 0){print(playerName + " successfully ran!",1);}
        else if  (result == 1){
            user.victory(winAtk);
            user.setXP(user.getXP() + cpu.rank);
            user.print();
        }
        else if (result == -1){user.loss();}
        user.setHP(fullHP);
        cpu = null; 
    }

    private static void intro(){
        print("What is your name great adventurer? ", 0);
        playerName = keyboard.nextLine();
        print(playerName + ", is that correct? (yes/no) ",0);
        String answer = keyboard.nextLine();
        if (checkAnswer(answer,"yes") == false){intro();}

        print("\f                                   Welcome to the game of Battle Factors!", 2);
        print("     In this game, you will be face with many enemies. The enemies will be scaled to your current level, from 1 to 10." ,1);
        print("Your object is simple, fight your way to level 10 and defeat the evil rank 100! There will be many battles along the" ,1);
        print("way. Winning these battle will not only give you experience, but will also increase your attack power. Losing a battle",1);
        print("does not mean game failure, but the penalty will reside in your attack. A battle lost results in a random attack number",1);
        print("to be deleted from your attack array. If a player losses all numbers in their attack array, then it is game over. Run",1);
        print("when necessary and fight your way to the top!",2);
        print("                                            Good luck " + playerName + "!",1);
        print("\nPress enter to continue",1);
        String enter;
        enter = keyboard.nextLine();
        print("\f",0);
    }

    private static void print(String s, int n){
        System.out.print(s);
        for (int i = 0; i<n;i++){
            System.out.println();
        }
    }
}
