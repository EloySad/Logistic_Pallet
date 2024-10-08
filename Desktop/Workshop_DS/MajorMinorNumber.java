import java.util.Scanner;

public class MajorMinorNumber {
    public static void main(String[] args ) {
        Scanner numScanner = new Scanner(System.in);
        System.out.println("Enter 5 numbers:");
        int[] number = new int[5];
        for(int i=0; i<number.length;i++){
            System.out.println("Enter a number: ");
            number[i] = numScanner.nextInt();

        }
        numScanner.close();
    }
    
}
