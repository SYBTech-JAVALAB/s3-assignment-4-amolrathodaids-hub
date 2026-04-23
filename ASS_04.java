import java.util.Scanner;

// Abstract Class: bank_account
abstract class BankAccount {
    protected double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Abstract method to be implemented by subclasses
    public abstract void withdraw(double amount);

    public void displayBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }
}

// Savings Account: ₹1000 minimum balance restriction
class SavingsAccount extends BankAccount {
    private final double minBalance = 1000.0;

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Transaction Denied: Minimum balance of ₹1000 must be maintained.");
        }
    }
}

// Current Account: ₹5000 overdraft limit
class CurrentAccount extends BankAccount {
    private final double overdraftLimit = 5000.0;

    public CurrentAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        // Can withdraw up to (balance + overdraftLimit)
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Transaction Denied: Overdraft limit of ₹5000 exceeded.");
        }
    }
}

// Main class to demonstrate functionality
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Savings Account Demo ---");
        SavingsAccount savings = new SavingsAccount(2000);
        savings.displayBalance();
        savings.deposit(500);
        savings.withdraw(1200); // Allowed
        savings.withdraw(1000); // Denied (would leave 300, which is < 1000)
        savings.displayBalance();

        System.out.println("\n--- Current Account Demo ---");
        CurrentAccount current = new CurrentAccount(3000);
        current.displayBalance();
        current.withdraw(7000); // Allowed (uses 4000 of overdraft)
        current.displayBalance();
        current.withdraw(2000); // Denied (exceeds remaining overdraft)
        current.deposit(1000);
        current.displayBalance();
    }
}