
/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character
{
    protected int lv;
    protected int hp;
    protected int exp;
    protected int[] atk;
    protected int rank;
    
    // Counts the size needed for the array
    public static int[] countFactors(int n){
        int counter = 0;
        for (int factors = 1; factors <= n; factors++){
            if (n % factors == 0){
                counter++;
            }
        }
        return new int[counter];
    }

    // Fills empty array with the number's factors 
    public static int[] factoring(int n){        
        int[] arr = countFactors(n);
        int counter = 0;
        for (int factors = 1; factors <= n; factors++){
            if (n % factors == 0){
                arr[counter] = factors;
                counter++;
            }
        }
        return arr;
    }

    public static void printAtk(int[] arr){
        System.out.print("Attack: [ ");
        for (int atk : arr){
            System.out.print(atk + " ");
        }
        System.out.println("]");
    }
}
