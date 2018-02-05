package HW3;

public class CheckingAccount extends Account
{
    public CheckingAccount(double balance, double interest)
    {
        super(balance, interest);
    }
    
    @Override
    public void withdraw(double amount)
    {
        if(this.getBalance()-amount < 0)
            this.addCharges(15);
        super.withdraw(amount);
    }
    
    @Override
    public void monthlyProc()
    {
        this.addCharges(5+.1*this.getWithdrawals());
        super.monthlyProc();
    }
}
