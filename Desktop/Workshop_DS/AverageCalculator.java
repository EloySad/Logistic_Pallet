import java.util.Scanner;
public class AverageCalculator {    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double[] califications = new double[10];
        double average, sum = 0;

        for(int i = 0; i< 10; i++){
            System.out.println("Enter yours califications: ");
            califications[i] = scanner.nextDouble();

            System.out.println("Grade entered: " + califications[i]);
            sum += califications[i];
                       
        }
        average = sum/califications.length;           
        System.out.println("\nThe group average is: " + average );
        
        
        
        
        scanner.close();
    }


}