package HW3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver
{
    
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Please Enter the Starting Balance For The Savings Account: $");
        double savingsStart = numInputSafe(in);
        System.out.print("Please Enter the Annual Interest Rate For The Savings Account: ");
        SavingsAccount savings = new SavingsAccount(savingsStart,numInputSafe(in));
        
        System.out.println();
        System.out.print("Please Enter # of Deposits For This Account: ");
        int derp = (int)numInputSafe(in);
        for(int i = 0; i < derp; i++)
        {
            System.out.print("Enter Amount for Deposit #" + (i+1) + ": $");
            savings.deposit(numInputSafe(in));
        }
        
        System.out.println();
        System.out.print("Please Enter # of Withdrawals For This Account: ");
        derp = (int)numInputSafe(in);
        for(int i = 0; i < derp; i++)
        {
            System.out.print("Enter Amount for Withdrawal #" + (i+1) + ": $");
            savings.withdraw(numInputSafe(in));
        }
        
        System.out.println();
        ///////////////////////////////////////////////////////////////////////////////////////////////
        
        System.out.print("Please Enter the Starting Balance For The Checking Account: $");
        double checkingStart = numInputSafe(in);
        System.out.print("Please Enter the Annual Interest Rate For The Checking Account: ");
        CheckingAccount checking = new CheckingAccount(checkingStart,numInputSafe(in));
        
        System.out.println();
        System.out.print("Please Enter # of Deposits For This Account: ");
        derp = (int)numInputSafe(in);
        for(int i = 0; i < derp; i++)
        {
            System.out.print("Enter Amount for Deposit #" + (i+1) + ": $");
            checking.deposit(numInputSafe(in));
        }
        
        System.out.println();
        System.out.print("Please Enter # of Withdrawals For This Account: ");
        derp = (int)numInputSafe(in);
        for(int i = 0; i < derp; i++)
        {
            System.out.print("Enter Amount for Withdrawal #" + (i+1) + ": $");
            checking.withdraw(numInputSafe(in));
        }
        
        System.out.println();
        in.close();
        ///////////////////////////////////////////////////////////////////////////////////////////////
        
        System.out.println("Savings Statistics:");
        System.out.printf("Beginning Balance: $%.2f%n", savingsStart);
        System.out.println("Total Deposits: " + savings.getDeposits());
        System.out.println("Total Withdrawals: " + savings.getWithdrawals());
        savings.monthlyProc();
        System.out.printf("Service Charges: $%.2f%n", savings.getCharges());
        System.out.printf("Ending Balance: $%.2f%n", savings.getBalance());
        System.out.println();
        System.out.println("Checking Statistics:");
        System.out.printf("Beginning Balance: $%.2f%n", checkingStart);
        System.out.println("Total Deposits: " + checking.getDeposits());
        System.out.println("Total Withdrawals: " + checking.getWithdrawals());
        checking.monthlyProc();
        System.out.printf("Service Charges: $%.2f%n", checking.getCharges());
        System.out.printf("Ending Balance: $%.2f%n", checking.getBalance());
    }
    
    private static double numInputSafe(Scanner in)
    {
        try{
            return in.nextDouble();
        }catch(InputMismatchException e)
        {
            System.out.println("ERROR");
            System.exit(0);
        }
        return 0;
    }
}
