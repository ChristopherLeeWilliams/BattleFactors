
/**
 * Write a description of class Tests here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tests
{
    public static void main(String[]args){
        System.out.print("\f");

        Player bob = new Player(300,1);
        bob.setAtk(18);
        System.out.println("Atk should be factor array of 18:\n" + bob);        

        for (int i = 0; i<5; i++){
            bob.victory(((int)(Math.random()*(9))) + 40);
        }
        System.out.println("Player won 5 new numbers in atk array (40-49):\n" + bob);

        bob.loss();
        System.out.println("Player lost a random number in atk array:\n" + bob);

        Enemy a = new Enemy(4);
        System.out.println("Enemy level 4, with rank 30-39\n" + a);

        Enemy b = new Enemy(5);
        System.out.println("Enemy level 5, with rank 40-49\n" + b);

        Enemy c = new Enemy(6);
        System.out.println("Enemy level 6, with rank 50-59\n" + c);
    }
}
