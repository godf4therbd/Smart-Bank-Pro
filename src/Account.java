/**
 * Base class Account that stores customer name, account number and type of account.
 * This class provides basic account functionality.
 */
public class Account {
    protected String customerName;
    protected String accountNumber;
    protected String accountType;
    protected double balance;
    
    /**
     * Initialize account details
     * @param name Customer name
     * @param accNumber Account number
     * @param type Type of account (Savings/Current)
     * @param initialBalance Initial balance
     */
    public void initialize(String name, String accNumber, String type, double initialBalance) {
        this.customerName = name;
        this.accountNumber = accNumber;
        this.accountType = type;
        this.balance = initialBalance;
    }
    
    /**
     * Accept deposit from a customer and update the balance
     * @param amount Deposit amount
     */
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposit of ৳" + amount + " successful. New balance: ৳" + this.balance);
        } else {
            System.out.println("Invalid deposit amount. Amount must be greater than zero.");
        }
    }
    
    /**
     * Display the balance
     */
    public void displayBalance() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Customer Name: " + this.customerName);
        System.out.println("Account Type: " + this.accountType);
        System.out.println("Current Balance: ৳" + this.balance);
    }
    
    /**
     * Permit withdrawal and update the balance
     * This method should be overridden in derived classes for specific withdrawal rules
     * @param amount Withdrawal amount
     * @return true if withdrawal is successful, false otherwise
     */
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
     * Compute and deposit interest
     * This method should be overridden in derived classes that provide interest
     */
    public void computeAndDepositInterest() {
        System.out.println("Interest computation not available for this account type.");
    }
    
    /**
     * Check for minimum balance and impose penalty if necessary
     * This method should be overridden in derived classes that require minimum balance
     */
    public void checkMinimumBalance() {
        System.out.println("Minimum balance check not applicable for this account type.");
    }
    
    // Getters
    public String getCustomerName() {
        return customerName;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public double getBalance() {
        return balance;
    }
}

