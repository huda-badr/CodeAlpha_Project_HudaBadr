package codeAlpha;
import java.util.Scanner;
//Task 2: 
public class BankApp {
	
    private double balance;

    public BankApp() {
        balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankApp bank = new BankApp();

        System.out.println("- Initial account balance:");
        bank.checkBalance();

        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        bank.deposit(depositAmount);

        System.out.println("- Account balance after deposit:");
        bank.checkBalance();

        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        bank.withdraw(withdrawAmount);

        System.out.println("- Account balance after withdrawal:");
        bank.checkBalance();

    }
}
