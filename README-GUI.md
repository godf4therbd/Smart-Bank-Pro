# Smart Bank Pro - GUI Application

## Running the Application

### Option 1: Using the Batch Script (Windows)
Double-click `run-gui.bat` or run it from the command prompt.

### Option 2: Manual Execution

1. **Compile the application:**
   ```
   javac -cp src src\BankingGUI.java src\Account.java src\Sav_acct.java src\Curr_acct.java
   ```

2. **Run the GUI application:**
   ```
   java -cp src BankingGUI
   ```

## Features

The GUI application provides a graphical interface for:

1. **Account Management:**
   - Create Savings Account (with interest rate and compounding periods)
   - Create Current Account (with minimum balance and service charge)

2. **Banking Operations:**
   - **Deposit:** Add money to your account
   - **Withdraw:** Remove money from your account
   - **Calculate Interest:** Apply compound interest (Savings accounts only)
   - **Check Minimum Balance:** Verify minimum balance and apply service charges (Current accounts only)

3. **Transaction History:**
   - View all transactions with timestamps
   - Filter transactions by type

## Usage Instructions

1. **Create an Account:**
   - Go to `Account` menu → Select "New Savings Account" or "New Current Account"
   - Fill in the required information:
     - Customer Name
     - Account Number
     - Initial Balance
     - For Savings: Interest Rate (%) and Compounding Periods per Year
     - For Current: Minimum Balance and Service Charge

2. **Perform Operations:**
   - Enter an amount in the "Amount (৳)" field
   - Click the appropriate button:
     - **Deposit:** Add money to account
     - **Withdraw:** Remove money from account
     - **Calculate Interest:** Compute and deposit interest (Savings only)
     - **Check Min Balance:** Check and apply penalties if below minimum (Current only)

3. **View Information:**
   - Account details are displayed in the left panel
   - Operation messages appear in the message area
   - Transaction history is shown in the right panel

## Console Mode

To run the console test application instead:
- Use `run-console.bat` or run:
  ```
  javac -cp src src\BankAccountTest.java src\Account.java src\Sav_acct.java src\Curr_acct.java
  java -cp src BankAccountTest
  ```

## Requirements

- Java Development Kit (JDK) 8 or higher
- No additional libraries required (uses standard Java Swing)

