# Smart Bank Pro 💰

A comprehensive Java-based banking system application with multiple interface options - GUI, Console, and Web interfaces.

## 📋 Features

- **Savings Account Management**
  - Deposit and withdraw funds
  - Compound interest calculation
  - Configurable interest rates and compounding periods

- **Current Account Management**
  - Deposit and withdraw funds
  - Minimum balance enforcement
  - Service charge for accounts below minimum balance
  - Cheque book facility

- **Transaction History**
  - Track all transactions with timestamps
  - Filter transactions by type

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- (Optional) Apache Tomcat for web interface

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/godf4therbd/Smart-Bank-Pro.git
   cd Smart-Bank-Pro
   ```

2. Compile the Java files:
   ```bash
   javac -cp src src\*.java
   ```

## 💻 Running the Application

### Option 1: GUI Interface (Recommended)

Run the graphical user interface:

**Windows:**
```bash
run-gui.bat
```

**Manual:**
```bash
javac -cp src src\BankingGUI.java src\Account.java src\Sav_acct.java src\Curr_acct.java
java -cp src BankingGUI
```

### Option 2: Console Interface

Run the console test application:

**Windows:**
```bash
run-console.bat
```

**Manual:**
```bash
javac -cp src src\BankAccountTest.java src\Account.java src\Sav_acct.java src\Curr_acct.java
java -cp src BankAccountTest
```

### Option 3: Web Interface (Requires Setup)

The web interface requires a servlet container like Apache Tomcat. See the servlet files in `src/` for web implementation.

## 📁 Project Structure

```
Smart-Bank-Pro/
├── src/
│   ├── Account.java              # Base account class
│   ├── Sav_acct.java             # Savings account implementation
│   ├── Curr_acct.java            # Current account implementation
│   ├── BankAccountTest.java      # Console test application
│   ├── BankingGUI.java           # GUI application
│   └── *Servlet.java             # Web servlet implementations
├── css/                          # Stylesheets for web interface
├── *.html                        # HTML files for web interface
├── run-gui.bat                   # Windows script to run GUI
├── run-console.bat               # Windows script to run console app
└── README.md                     # This file
```

## 🎯 Usage

### GUI Application

1. Launch the GUI using `run-gui.bat`
2. Create an account from the `Account` menu:
   - **New Savings Account**: Requires customer name, account number, initial balance, interest rate, and compounding periods
   - **New Current Account**: Requires customer name, account number, initial balance, minimum balance, and service charge
3. Perform operations:
   - Enter amount in the "Amount (৳)" field
   - Click **Deposit**, **Withdraw**, **Calculate Interest**, or **Check Min Balance**
4. View transaction history in the right panel

### Console Application

The console application demonstrates all banking features with sample accounts:
- Savings account with interest calculation
- Current account with minimum balance checking
- Various deposit and withdrawal operations

## 🔧 Technical Details

- **Language**: Java
- **GUI Framework**: Java Swing
- **Web Framework**: Java Servlets (requires servlet container)
- **Design Pattern**: Object-Oriented Programming with inheritance

## 📝 Account Types

### Savings Account (`Sav_acct`)
- Inherits from `Account`
- Supports compound interest calculation
- Interest rate and compounding periods configurable
- No minimum balance requirement

### Current Account (`Curr_acct`)
- Inherits from `Account`
- Enforces minimum balance
- Applies service charge when below minimum
- Cheque book facility available
- No interest calculation

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is open source and available for educational purposes.

## 👤 Author

**godf4therbd**

- GitHub: [@godf4therbd](https://github.com/godf4therbd)

## 🙏 Acknowledgments

- Built with Java Swing for GUI
- Standard Java libraries used

---

⭐ If you found this project helpful, please consider giving it a star!

