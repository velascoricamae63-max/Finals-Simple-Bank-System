//Simple Bank System
// Group 3

//RICA
//JOption Dialogue Boxes
import javax.swing.JOptionPane;

// [ADDED] Imports for Java GUI components
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Mainbank extends JFrame {

    // [OLD] Class Bank Account
    static class BankAccount {

        // [OLD] Account information
        private String accountHolder;
        private double balance = 0.0;

        // [OLD] Constructor to initialize the account with the owner's name
        public BankAccount(String name) {
            this.accountHolder = name;
        }

        // [ADDED] Getter method to get the account holder's name
        public String getAccountHolder() {
            return accountHolder;
        }

        // [ADDED] Setter method to update the account holder's name
        public void setAccountHolder(String name) {
            this.accountHolder = name;
        }

        // [ADDED] Getter method to get the current balance
        public double getBalance() {
            return balance;
        }

        // [OLD] Method to check balance
        public void checkBalance() {
            JOptionPane.showMessageDialog(
                null,
                "Account Holder: " + accountHolder
                + "\nCurrent Balance: ₱" + String.format("%.2f", balance),
                "Check Balance",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        //Elgen Balila
        // [OLD] Method to catch invalid inputs
        public static boolean isValid(String str) {

            if (str == null || str.trim().isEmpty()) {
                return false;
            }

            // [ADDED] trim() removes extra spaces before checking the input
            str = str.trim();

            int decimal = 0;
            int digits = 0;

            for (int i = 0; i < str.length(); i++) {

                // [OLD] charAt() gets the character at the current position
                char c = str.charAt(i);

                if (c == '.') {
                    decimal++;

                    if (decimal > 1) {
                        return false;
                    }

                } else if (!Character.isDigit(c)) {
                    return false;

                } else {
                    // [ADDED] Counts the numbers in the input
                    digits++;
                }
            }

            // [ADDED] Prevents a period alone from being accepted
            return digits > 0;
        }

        //George Remoral
        // [OLD] Method to deposit money
        public void deposit() {

            // [ADDED] The account holder's name is included in the input prompt
            String amount = JOptionPane.showInputDialog(
                null,
                "Account Holder: " + accountHolder
                + "\nEnter amount to deposit:",
                "Deposit",
                JOptionPane.QUESTION_MESSAGE
            );

            // [OLD] Check if the input is not null
            if (amount != null) {

                if (isValid(amount)) {

                    try {
                        // [OLD] Converts the String input into a double
                        double deposit = Double.parseDouble(amount.trim());

                        if (deposit > 0) {

                            // [OLD] Adds the deposit to the balance
                            this.balance += deposit;

                            // [ADDED] Displays the account holder and Philippine peso
                            JOptionPane.showMessageDialog(
                                null,
                                "Account Holder: " + accountHolder
                                + "\nSuccessfully deposited: ₱" + String.format("%.2f", deposit)
                                + "\nNew Balance: ₱" + String.format("%.2f", this.balance),
                                "Deposit Successful",
                                JOptionPane.INFORMATION_MESSAGE
                            );

                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Deposit amount must be greater than zero."
                            );
                        }

                    } catch (NumberFormatException e) {
                        // [ADDED] Handles an input that cannot be converted into a number
                        JOptionPane.showMessageDialog(
                            null,
                            "Invalid amount. Please enter a valid number."
                        );
                    }

                } else {
                    // [OLD] Displays an error message for invalid input
                    JOptionPane.showMessageDialog(
                        null,
                        "Invalid input. Please enter numbers only."
                    );
                }
            }
        }

        //Mike Pangramuyen & Kaye
        // [OLD] Method to withdraw money
        public void withdraw() {

            // [ADDED] The account holder's name is included in the input prompt
            String amount = JOptionPane.showInputDialog(
                null,
                "Account Holder: " + accountHolder
                + "\nEnter amount to withdraw:",
                "Withdraw",
                JOptionPane.QUESTION_MESSAGE
            );

            // [OLD] Check if the input is not null
            if (amount != null) {

                if (isValid(amount)) {

                    try {
                        // [OLDM] Converts the String input into a double
                        double withdraw = Double.parseDouble(amount.trim());

                        if (withdraw <= 0) {

                            JOptionPane.showMessageDialog(
                                null,
                                "Withdraw amount must be greater than zero."
                            );

                        } else if (withdraw > balance) {

                            // [ADDED] Shows the account holder and current balance
                            JOptionPane.showMessageDialog(
                                null,
                                "Account Holder: " + accountHolder
                                + "\nInsufficient funds!"
                                + "\nCurrent Balance: ₱" + String.format("%.2f", balance)
                            );

                        } else {

                            // [OLD] Subtracts the withdrawal from the balance
                            this.balance -= withdraw;

                            // [ADDED] Displays the account holder and Philippine peso
                            JOptionPane.showMessageDialog(
                                null,
                                "Account Holder: " + accountHolder
                                + "\nSuccessfully withdrawn: ₱" + String.format("%.2f", withdraw)
                                + "\nRemaining Balance: ₱" + String.format("%.2f", this.balance),
                                "Withdrawal Successful",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }

                    } catch (NumberFormatException e) {
                        // [ADDED] Handles an input that cannot be converted into a number
                        JOptionPane.showMessageDialog(
                            null,
                            "Invalid amount. Please enter a valid number."
                        );
                    }

                } else {
                    // [OLD] Displays an error message for invalid input
                    JOptionPane.showMessageDialog(
                        null,
                        "Invalid input. Please enter numbers only."
                    );
                }
            }
        }
    }

    // [ADDED] Stores the account object for the GUI
    private BankAccount account;

    // [ADDED] Label that displays the account holder's name
    private JLabel nameLabel;

    // [ADDED] Label that displays the current balance
    private JLabel balanceLabel;

    // [ADDED] Constructor for the GUI window
    public Mainbank(BankAccount account) {

        this.account = account;

        // [ADDED] Sets the window title and size
        setTitle("Simple Bank System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // [ADDED] Creates the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // [ADDED] Displays the account holder's name
        nameLabel = new JLabel(
            "Account Holder: " + account.getAccountHolder(),
            JLabel.CENTER
        );

        // [ADDED] Displays the current balance
        balanceLabel = new JLabel(
            "Balance: ₱" + String.format("%.2f", account.getBalance()),
            JLabel.CENTER
        );

        // [ADDED] Panel for the buttons
        JPanel buttonPanel = new JPanel();

        // [ADDED] Arranges the buttons in two rows and two columns
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

        // [ADDED] Creates the GUI buttons
        JButton checkButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        // [ADDED] Adds the buttons to the button panel
        buttonPanel.add(checkButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(exitButton);

        // [ADDED] Adds the labels and buttons to the main panel
        mainPanel.add(nameLabel, BorderLayout.NORTH);
        mainPanel.add(balanceLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // [ADDED] Displays the panel inside the JFrame
        add(mainPanel);

        // [ADDED] Event handling for the Check Balance button
        checkButton.addActionListener(e -> {
            account.checkBalance();
            updateBalanceLabel();
        });

        // [ADDED] Event handling for the Deposit button
        depositButton.addActionListener(e -> {
            account.deposit();
            updateBalanceLabel();
        });

        // [ADDED] Event handling for the Withdraw button
        withdrawButton.addActionListener(e -> {
            account.withdraw();
            updateBalanceLabel();
        });

        // [ADDED] Event handling for the Exit button
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "Thank you for using the Bank System, "
                + account.getAccountHolder() + ". Goodbye!"
            );

            System.exit(0);
        });

        // [ADDED] Makes the GUI window visible
        setVisible(true);
    }

    // [ADDED] Method to refresh the balance shown in the GUI
    private void updateBalanceLabel() {
        balanceLabel.setText(
            "Balance: ₱" + String.format("%.2f", account.getBalance())
        );
    }

    //RICA
    // [OLD] main method
    public static void main(String[] args) {

        String accountName = "";

        // [OLD] Gets the account holder's name
        while (accountName == null || accountName.trim().isEmpty()) {

            accountName = JOptionPane.showInputDialog(
                null,
                "--- Welcome to the Simple Bank System ---\n\n"
                + "Enter Account Holder Name:"
            );

            // [ADDED] Stops the program if the user presses Cancel
            if (accountName == null) {
                return;
            }

            // [ADDED] Removes extra spaces from the account name
            accountName = accountName.trim();

            if (accountName.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Account holder name cannot be empty."
                );
            }
        }

        // [OLD] Creates one BankAccount object using the entered name
        BankAccount account = new BankAccount(accountName);

        // [ADDED] Opens the GUI and passes the same account object
        new Mainbank(account);
    }
}