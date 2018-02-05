package HW3;

public class Account
{
    private double balance;
    private int deposits = 0, withdrawals = 0;
    private double interest;
    private double charges;
    
    public Account(double balance, double interest)
    {
        this.balance = balance;
        this.interest = interest;
    }
    
    public void deposit(double amount)
    {
        deposits++;
        balance += amount;
    }
    
    public void withdraw(double amount)
    {
        withdrawals++;
        balance -= amount;
    }
    
    public void calcInt()
    {
        balance += balance*(1+interest/12);
    }
    
    public void monthlyProc()
    {
        balance -= charges;
        calcInt();
        deposits = 0;
        withdrawals = 0;
    }
    
    public double getBalance(){return balance;}
    public int getDeposits(){return deposits;}
    public int getWithdrawals(){return withdrawals;}
    public double getInterest(){return interest;}
    public double getCharges(){return charges;}
    
    public void addCharges(double amount){charges += amount;}
}
