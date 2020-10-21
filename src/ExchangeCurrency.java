import java.util.InputMismatchException;
import java.util.Scanner;

public class ExchangeCurrency {
    public static void main(String[] args) {

        ExChangeAPI ex = new ExChangeAPI();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("### Currency Exchange System ###");
            System.out.print("please, enter your current currency: ");
            String  currentCurrency = scanner.nextLine();
        if  (ex.getConnection(currentCurrency) == false)
            System.out.println("Sorry,we can't find this currency rate");
        else {
            System.out.println("Amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("enter new currency: ");
            String newCurrency = scanner.nextLine();

            double rate = ex.getEachRate(newCurrency);

            System.out.println("\nEnter"+currentCurrency+" to "+newCurrency);
            System.out.println("\t"+currentCurrency+": "+amount);
            System.out.println("\t"+newCurrency+": "+(amount*rate));
            }
        


        } catch (InputMismatchException e){
            e.printStackTrace();
        }






    }
}
