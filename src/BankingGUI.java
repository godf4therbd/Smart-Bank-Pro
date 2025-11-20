import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI Application for Smart Bank Pro
 * Provides a graphical interface for banking operations
 */
public class BankingGUI extends JFrame {
    private Sav_acct currentSavingsAccount;
    private Curr_acct currentCurrentAccount;
    private String accountType;
    private ArrayList<String> transactionHistory;
    private DefaultListModel<String> historyModel;
    
    // Components
    private JTextField accountNumberField;
    private JTextField customerNameField;
    private JTextField balanceField;
    private JTextField amountField;
    private JTextArea infoArea;
    private JList<String> historyList;
    private JLabel balanceLabel;
    private JLabel accountTypeLabel;
    
    public BankingGUI() {
        transactionHistory = new ArrayList<>();
        historyModel = new DefaultListModel<>();
        
        setTitle("Smart Bank Pro - Banking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        createMenuBar();
        createMainPanel();
        
        pack();
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu accountMenu = new JMenu("Account");
        JMenuItem newSavingsItem = new JMenuItem("New Savings Account");
        JMenuItem newCurrentItem = new JMenuItem("New Current Account");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        newSavingsItem.addActionListener(e -> createSavingsAccount());
        newCurrentItem.addActionListener(e -> createCurrentAccount());
        exitItem.addActionListener(e -> System.exit(0));
        
        accountMenu.add(newSavingsItem);
        accountMenu.add(newCurrentItem);
        accountMenu.addSeparator();
        accountMenu.add(exitItem);
        
        menuBar.add(accountMenu);
        setJMenuBar(menuBar);
    }
    
    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left Panel - Account Info and Operations
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        
        // Account Info Panel
        JPanel infoPanel = createInfoPanel();
        leftPanel.add(infoPanel, BorderLayout.NORTH);
        
        // Operations Panel
        JPanel operationsPanel = createOperationsPanel();
        leftPanel.add(operationsPanel, BorderLayout.CENTER);
        
        // Right Panel - Transaction History
        JPanel rightPanel = createHistoryPanel();
        
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        add(mainPanel);
    }
    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Account Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Account Type:"), gbc);
        gbc.gridx = 1;
        accountTypeLabel = new JLabel("No account created");
        panel.add(accountTypeLabel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        accountNumberField = new JTextField(15);
        accountNumberField.setEditable(false);
        panel.add(accountNumberField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        customerNameField = new JTextField(15);
        customerNameField.setEditable(false);
        panel.add(customerNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Balance:"), gbc);
        gbc.gridx = 1;
        balanceLabel = new JLabel("৳0.00");
        balanceLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        balanceLabel.setForeground(new Color(0, 128, 0));
        panel.add(balanceLabel, gbc);
        
        return panel;
    }
    
    private JPanel createOperationsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Operations"));
        
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Amount (৳):"), gbc);
        gbc.gridx = 1;
        amountField = new JTextField(15);
        inputPanel.add(amountField, gbc);
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBackground(new Color(76, 175, 80));
        depositBtn.setForeground(Color.WHITE);
        depositBtn.addActionListener(e -> performDeposit());
        
        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBackground(new Color(244, 67, 54));
        withdrawBtn.setForeground(Color.WHITE);
        withdrawBtn.addActionListener(e -> performWithdraw());
        
        JButton interestBtn = new JButton("Calculate Interest");
        interestBtn.setBackground(new Color(33, 150, 243));
        interestBtn.setForeground(Color.WHITE);
        interestBtn.addActionListener(e -> calculateInterest());
        
        JButton checkBalanceBtn = new JButton("Check Min Balance");
        checkBalanceBtn.setBackground(new Color(255, 152, 0));
        checkBalanceBtn.setForeground(Color.WHITE);
        checkBalanceBtn.addActionListener(e -> checkMinimumBalance());
        
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(interestBtn);
        buttonPanel.add(checkBalanceBtn);
        
        // Info Area
        infoArea = new JTextArea(8, 30);
        infoArea.setEditable(false);
        infoArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        infoArea.setBorder(BorderFactory.createTitledBorder("Operation Messages"));
        JScrollPane scrollPane = new JScrollPane(infoArea);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Transaction History"));
        panel.setPreferredSize(new Dimension(300, 0));
        
        historyList = new JList<>(historyModel);
        historyList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(historyList);
        
        JButton clearBtn = new JButton("Clear History");
        clearBtn.addActionListener(e -> {
            historyModel.clear();
            transactionHistory.clear();
        });
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(clearBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void createSavingsAccount() {
        JDialog dialog = new JDialog(this, "Create Savings Account", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        JTextField nameField = new JTextField(20);
        JTextField accNumField = new JTextField(20);
        JTextField balanceField = new JTextField(20);
        JTextField interestField = new JTextField(20);
        JTextField periodsField = new JTextField(20);
        
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(new JLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        dialog.add(accNumField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        dialog.add(new JLabel("Initial Balance:"), gbc);
        gbc.gridx = 1;
        dialog.add(balanceField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        dialog.add(new JLabel("Interest Rate (%):"), gbc);
        gbc.gridx = 1;
        dialog.add(interestField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        dialog.add(new JLabel("Compounding Periods/Year:"), gbc);
        gbc.gridx = 1;
        periodsField.setText("12");
        dialog.add(periodsField, gbc);
        
        JButton createBtn = new JButton("Create Account");
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(createBtn, gbc);
        
        createBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String accNum = accNumField.getText();
                double balance = Double.parseDouble(balanceField.getText());
                double interest = Double.parseDouble(interestField.getText()) / 100.0;
                int periods = Integer.parseInt(periodsField.getText());
                
                if (name.isEmpty() || accNum.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all fields!");
                    return;
                }
                
                currentSavingsAccount = new Sav_acct();
                currentSavingsAccount.initializeSavings(name, accNum, balance, interest, periods);
                accountType = "Savings";
                currentCurrentAccount = null;
                
                updateDisplay();
                addToHistory("Account Created: " + name + " (" + accNum + ") - ৳" + balance);
                
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid number format!");
            }
        });
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void createCurrentAccount() {
        JDialog dialog = new JDialog(this, "Create Current Account", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        JTextField nameField = new JTextField(20);
        JTextField accNumField = new JTextField(20);
        JTextField balanceField = new JTextField(20);
        JTextField minBalanceField = new JTextField(20);
        JTextField serviceChargeField = new JTextField(20);
        
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(new JLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(new JLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        dialog.add(accNumField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        dialog.add(new JLabel("Initial Balance:"), gbc);
        gbc.gridx = 1;
        dialog.add(balanceField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        dialog.add(new JLabel("Minimum Balance:"), gbc);
        gbc.gridx = 1;
        dialog.add(minBalanceField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        dialog.add(new JLabel("Service Charge:"), gbc);
        gbc.gridx = 1;
        dialog.add(serviceChargeField, gbc);
        
        JButton createBtn = new JButton("Create Account");
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(createBtn, gbc);
        
        createBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String accNum = accNumField.getText();
                double balance = Double.parseDouble(balanceField.getText());
                double minBalance = Double.parseDouble(minBalanceField.getText());
                double serviceCharge = Double.parseDouble(serviceChargeField.getText());
                
                if (name.isEmpty() || accNum.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill all fields!");
                    return;
                }
                
                currentCurrentAccount = new Curr_acct();
                currentCurrentAccount.initializeCurrent(name, accNum, balance, minBalance, serviceCharge);
                accountType = "Current";
                currentSavingsAccount = null;
                
                updateDisplay();
                addToHistory("Account Created: " + name + " (" + accNum + ") - ৳" + balance);
                
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid number format!");
            }
        });
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void performDeposit() {
        if (currentSavingsAccount == null && currentCurrentAccount == null) {
            showMessage("Please create an account first!");
            return;
        }
        
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                showMessage("Amount must be greater than zero!");
                return;
            }
            
            if (currentSavingsAccount != null) {
                currentSavingsAccount.deposit(amount);
                addToHistory("Deposit: +৳" + String.format("%.2f", amount));
            } else {
                currentCurrentAccount.deposit(amount);
                addToHistory("Deposit: +৳" + String.format("%.2f", amount));
            }
            
            updateDisplay();
            amountField.setText("");
            showMessage("Deposit of ৳" + amount + " successful!");
        } catch (NumberFormatException ex) {
            showMessage("Invalid amount format!");
        }
    }
    
    private void performWithdraw() {
        if (currentSavingsAccount == null && currentCurrentAccount == null) {
            showMessage("Please create an account first!");
            return;
        }
        
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                showMessage("Amount must be greater than zero!");
                return;
            }
            
            boolean success;
            if (currentSavingsAccount != null) {
                success = currentSavingsAccount.withdraw(amount);
                if (success) {
                    addToHistory("Withdrawal: -৳" + String.format("%.2f", amount));
                }
            } else {
                success = currentCurrentAccount.withdraw(amount);
                if (success) {
                    addToHistory("Withdrawal: -৳" + String.format("%.2f", amount));
                    // Check minimum balance after withdrawal
                    checkMinimumBalance();
                }
            }
            
            updateDisplay();
            amountField.setText("");
        } catch (NumberFormatException ex) {
            showMessage("Invalid amount format!");
        }
    }
    
    private void calculateInterest() {
        if (currentSavingsAccount == null) {
            showMessage("Interest calculation is only available for Savings accounts!");
            return;
        }
        
        double oldBalance = currentSavingsAccount.getBalance();
        currentSavingsAccount.computeAndDepositInterest();
        double newBalance = currentSavingsAccount.getBalance();
        double interest = newBalance - oldBalance;
        
        addToHistory("Interest: +৳" + String.format("%.2f", interest));
        updateDisplay();
        showMessage("Interest of ৳" + String.format("%.2f", interest) + " deposited!");
    }
    
    private void checkMinimumBalance() {
        if (currentCurrentAccount == null) {
            showMessage("Minimum balance check is only available for Current accounts!");
            return;
        }
        
        double oldBalance = currentCurrentAccount.getBalance();
        currentCurrentAccount.checkMinimumBalance();
        double newBalance = currentCurrentAccount.getBalance();
        
        if (oldBalance != newBalance) {
            double penalty = oldBalance - newBalance;
            addToHistory("Service Charge: -৳" + String.format("%.2f", penalty));
            updateDisplay();
        }
    }
    
    private void updateDisplay() {
        if (currentSavingsAccount != null) {
            accountTypeLabel.setText("Savings Account");
            accountNumberField.setText(currentSavingsAccount.getAccountNumber());
            customerNameField.setText(currentSavingsAccount.getCustomerName());
            balanceLabel.setText("৳" + String.format("%.2f", currentSavingsAccount.getBalance()));
            
            infoArea.setText("Account Number: " + currentSavingsAccount.getAccountNumber() + "\n");
            infoArea.append("Customer Name: " + currentSavingsAccount.getCustomerName() + "\n");
            infoArea.append("Account Type: Savings\n");
            infoArea.append("Balance: ৳" + String.format("%.2f", currentSavingsAccount.getBalance()) + "\n");
            infoArea.append("Interest Rate: " + (currentSavingsAccount.getInterestRate() * 100) + "% per annum\n");
            infoArea.append("Compounding Periods: " + currentSavingsAccount.getCompoundingPeriods() + " per year\n");
        } else if (currentCurrentAccount != null) {
            accountTypeLabel.setText("Current Account");
            accountNumberField.setText(currentCurrentAccount.getAccountNumber());
            customerNameField.setText(currentCurrentAccount.getCustomerName());
            balanceLabel.setText("৳" + String.format("%.2f", currentCurrentAccount.getBalance()));
            
            infoArea.setText("Account Number: " + currentCurrentAccount.getAccountNumber() + "\n");
            infoArea.append("Customer Name: " + currentCurrentAccount.getCustomerName() + "\n");
            infoArea.append("Account Type: Current\n");
            infoArea.append("Balance: ৳" + String.format("%.2f", currentCurrentAccount.getBalance()) + "\n");
            infoArea.append("Minimum Balance: ৳" + String.format("%.2f", currentCurrentAccount.getMinimumBalance()) + "\n");
            infoArea.append("Service Charge: ৳" + String.format("%.2f", currentCurrentAccount.getServiceCharge()) + "\n");
            if (currentCurrentAccount.getBalance() < currentCurrentAccount.getMinimumBalance()) {
                infoArea.append("Status: Below minimum balance!\n");
            } else {
                infoArea.append("Status: Above minimum balance\n");
            }
        } else {
            accountTypeLabel.setText("No account created");
            accountNumberField.setText("");
            customerNameField.setText("");
            balanceLabel.setText("৳0.00");
            infoArea.setText("Please create an account from the Account menu.");
        }
    }
    
    private void showMessage(String message) {
        infoArea.append("\n" + message + "\n");
    }
    
    private void addToHistory(String transaction) {
        String timestamp = java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss"));
        String historyEntry = "[" + timestamp + "] " + transaction;
        transactionHistory.add(historyEntry);
        historyModel.addElement(historyEntry);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            BankingGUI gui = new BankingGUI();
            gui.setVisible(true);
        });
    }
}

