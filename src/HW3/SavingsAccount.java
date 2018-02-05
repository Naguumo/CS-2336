package HW3;

public class SavingsAccount extends Account
{
    private boolean status;
    
    public SavingsAccount(double balance, double interest)
    {
        super(balance, interest);
        status = balance >= 25.0;
    }
    
    @Override
    public void deposit(double amount)
    {
        if(status || (!status && this.getBalance() + amount > 25.0))
        {
            status = true;
            super.deposit(amount);
        }
    }
    
    @Override
    public void withdraw(double amount)
    {
        if(status)
        {
            super.withdraw(amount);
            status = this.getBalance() >= 25.0;
        }
    }
    
    @Override
    public void monthlyProc()
    {
        if(this.getWithdrawals() > 4)
        {
            this.addCharges(this.getWithdrawals()-4);
            super.monthlyProc();
            status = this.getBalance() >= 25.0;
        }
    }
}
