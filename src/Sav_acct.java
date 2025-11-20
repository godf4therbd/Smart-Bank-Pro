/**
 * Savings Account class that extends Account.
 * Provides compound interest and withdrawal facilities but no cheque book facility.
 */
public class Sav_acct extends Account {
    private double interestRate; // Annual interest rate (e.g., 0.03 for 3%)
    private int compoundingPeriods; // Number of times interest is compounded per year
    
    /**
     * Initialize savings account
     * @param name Customer name
     * @param accNumber Account number
     * @param initialBalance Initial balance
     * @param rate Annual interest rate (as decimal, e.g., 0.03 for 3%)
     * @param periods Number of compounding periods per year (e.g., 12 for monthly)
     */
    public void initializeSavings(String name, String accNumber, double initialBalance, 
                                   double rate, int periods) {
        super.initialize(name, accNumber, "Savings", initialBalance);
        this.interestRate = rate;
        this.compoundingPeriods = periods;
    }
    
    /**
     * Compute and deposit compound interest
     * Formula: A = P(1 + r/n)^(nt) where:
     * P = principal, r = annual rate, n = compounding periods, t = time in years
     * For monthly compounding over 1 month: t = 1/12
     */
    @Override
    public void computeAndDepositInterest() {
        if (this.balance > 0 && this.interestRate > 0) {
            // Calculate interest for one compounding period
            // For monthly: t = 1/12, for quarterly: t = 1/4, etc.
            double timePeriod = 1.0 / this.compoundingPeriods;
            double compoundInterest = this.balance * (Math.pow(1 + (this.interestRate / this.compoundingPeriods), 
                                                               this.compoundingPeriods * timePeriod) - 1);
            
            this.balance += compoundInterest;
            System.out.println("Compound interest of ৳" + String.format("%.2f", compoundInterest) + 
                             " deposited. New balance: ৳" + String.format("%.2f", this.balance));
        } else {
            System.out.println("Cannot compute interest. Balance must be greater than zero.");
        }
    }
    
    /**
     * Permit withdrawal and update the balance
     * Savings account allows withdrawal if sufficient balance exists
     * @param amount Withdrawal amount
     * @return true if withdrawal is successful, false otherwise
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal of ৳" + amount + " successful. New balance: ৳" + this.balance);
            return true;
        } else if (amount > this.balance) {
            System.out.println("Insufficient funds. Available balance: ৳" + this.balance);
            return false;
        } else {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
    }
    
    /**
     * Display savings account balance with interest information
     */
    @Override
    public void displayBalance() {
        super.displayBalance();
        System.out.println("Interest Rate: " + (this.interestRate * 100) + "% per annum");
        System.out.println("Compounding Periods: " + this.compoundingPeriods + " per year");
    }
    
    // Getters
    public double getInterestRate() {
        return interestRate;
    }
    
    public int getCompoundingPeriods() {
        return compoundingPeriods;
    }
}

