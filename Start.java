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
    public static void main(String[]args){
        System.out.print("\f");  
        intro();
    }
    
    public static Boolean checkAnswer(String a, String b){
        if (a.trim().equalsIgnoreCase(b)){return true;}
        return false;
    }

    private static void intro(){
        print("What is your name great adventurer? ", 0);
        playerName = keyboard.nextLine();
        print(playerName + ", is that correct? ",0);
        String answer = keyboard.nextLine();
        if (checkAnswer(answer,"yes") == false){intro();}
        
        print("\f                                   Welcome to the game of Battle Factors!", 2);
        print("     In this game, you will be face with many enemies. The enemies will be scaled to your current level, from 1 to 10." ,1);
        print("Your object is simple, fight your way to level 10 and defeat the evil rank 100! There will be many battles along the" ,1);
        print("way. Winning these battle will not only give you experience, but will also increase your attack power. Losing a battle",1);
        print("does not mean game failure, but the penalty will reside in your attack. A battle lost results in a random attack number",1);
        print("to be deleted from your attack array. If a player losses all numbers in their attack array, then it is game over. Run",1);
        print("when necessary and fight your way to the top!",2);
        print("                                                 Good luck " + playerName + "!",1);
    }

    private static void print(String s, int n){
        System.out.print(s);
        for (int i = 0; i<n;i++){
            System.out.println();
        }
    }
}
