/**
 * Test class to demonstrate the Account, Sav_acct, and Curr_acct classes
 */
public class BankAccountTest {
    public static void main(String[] args) {
        System.out.println("=== Banking System Test ===\n");
        
        // Test Savings Account
        System.out.println("--- Savings Account Test ---");
        Sav_acct savingsAccount = new Sav_acct();
        savingsAccount.initializeSavings("John Anderson", "SAV001", 5000.00, 0.03, 12);
        // 3% annual interest, compounded monthly (12 times per year)
        
        savingsAccount.displayBalance();
        System.out.println();
        
        savingsAccount.deposit(2000.00);
        System.out.println();
        
        savingsAccount.withdraw(1000.00);
        System.out.println();
        
        savingsAccount.computeAndDepositInterest();
        System.out.println();
        
        savingsAccount.displayBalance();
        System.out.println("\n");
        
        // Test Current Account
        System.out.println("--- Current Account Test ---");
        Curr_acct currentAccount = new Curr_acct();
        currentAccount.initializeCurrent("Jane Smith", "CUR001", 10000.00, 5000.00, 500.00);
        // Minimum balance: ৳5000, Service charge: ৳500
        
        currentAccount.displayBalance();
        System.out.println();
        
        currentAccount.deposit(3000.00);
        System.out.println();
        
        currentAccount.withdraw(4000.00);
        System.out.println();
        
        // This withdrawal will bring balance below minimum
        currentAccount.withdraw(5000.00);
        System.out.println();
        
        currentAccount.checkMinimumBalance();
        System.out.println();
        
        currentAccount.issueChequeBook();
        System.out.println();
        
        currentAccount.displayBalance();
        System.out.println();
        
        // Test interest computation on current account (should not work)
        System.out.println("--- Testing Interest on Current Account ---");
        currentAccount.computeAndDepositInterest();
    }
}

