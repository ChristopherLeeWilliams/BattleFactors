
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
        System.out.println(bob);        

        for (int i = 0; i<5; i++){
            bob.victory(((int)(Math.random()*(10))) + 40);
        }
        System.out.println(bob);

        bob.loss();
        System.out.println(bob);

        Enemy a = new Enemy(4);
        System.out.println(a);

        Enemy b = new Enemy(5);
        System.out.println(b);

        Enemy c = new Enemy(6);
        System.out.println(c);
    }
}
