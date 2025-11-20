/**
 * Current Account class that extends Account.
 * Provides cheque book facility but no interest.
 * Requires minimum balance; imposes service charge if balance falls below minimum.
 */
public class Curr_acct extends Account {
    private double minimumBalance;
    private double serviceCharge; // Penalty amount when balance falls below minimum
    
    /**
     * Initialize current account
     * @param name Customer name
     * @param accNumber Account number
     * @param initialBalance Initial balance
     * @param minBalance Minimum balance required
     * @param charge Service charge amount when balance falls below minimum
     */
    public void initializeCurrent(String name, String accNumber, double initialBalance, 
                                  double minBalance, double charge) {
        super.initialize(name, accNumber, "Current", initialBalance);
        this.minimumBalance = minBalance;
        this.serviceCharge = charge;
    }
    
    /**
     * Check for minimum balance, impose penalty if necessary, and update the balance
     */
    @Override
    public void checkMinimumBalance() {
        if (this.balance < this.minimumBalance) {
            System.out.println("Warning: Balance ৳" + this.balance + " is below minimum required balance of ৳" + 
                             this.minimumBalance);
            System.out.println("Service charge of ৳" + this.serviceCharge + " will be imposed.");
            
            if (this.balance >= this.serviceCharge) {
                this.balance -= this.serviceCharge;
                System.out.println("Service charge deducted. New balance: ৳" + this.balance);
            } else {
                System.out.println("Insufficient balance to pay service charge. Balance: ৳" + this.balance);
            }
        } else {
            System.out.println("Balance is above minimum required. No service charge.");
        }
    }
    
    /**
     * Permit withdrawal and update the balance
     * After withdrawal, checks minimum balance and imposes penalty if necessary
     * @param amount Withdrawal amount
     * @return true if withdrawal is successful, false otherwise
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal of ৳" + amount + " successful. New balance: ৳" + this.balance);
            
            // Check minimum balance after withdrawal
            checkMinimumBalance();
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
     * Override deposit to check minimum balance after deposit
     * @param amount Deposit amount
     */
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        // Check minimum balance after deposit (though it should be fine after deposit)
        if (this.balance < this.minimumBalance) {
            System.out.println("Note: Balance is still below minimum required balance of ৳" + this.minimumBalance);
        }
    }
    
    /**
     * Display current account balance with minimum balance information
     */
    @Override
    public void displayBalance() {
        super.displayBalance();
        System.out.println("Minimum Balance Required: ৳" + this.minimumBalance);
        System.out.println("Service Charge: ৳" + this.serviceCharge);
        if (this.balance < this.minimumBalance) {
            System.out.println("Status: Below minimum balance!");
        } else {
            System.out.println("Status: Above minimum balance");
        }
    }
    
    /**
     * Issue cheque book (facility available for current accounts)
     */
    public void issueChequeBook() {
        System.out.println("Cheque book facility is available for Current Account holders.");
        System.out.println("Cheque book request processed for account: " + this.accountNumber);
    }
    
    // Getters
    public double getMinimumBalance() {
        return minimumBalance;
    }
    
    public double getServiceCharge() {
        return serviceCharge;
    }
}

