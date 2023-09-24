package Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BankAccount { // we want to have a record of each deposit and withdrawal => command pattern
    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", balance is now " + balance );
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", balance is now " + balance );
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", overdraftLimit=" + overdraftLimit +
                '}';
    }


}

interface Command {
    void call();
    void undo();
}

class BankAccountCommand implements Command{
    private BankAccount account;
    private boolean succeeded;

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT:
                succeeded = true;
                account.deposit(amount);
                break;
            case WITHDRAW:
                succeeded = account.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() { // yes this is a bad idea
        if (!succeeded) return;
        switch (action) {
            case DEPOSIT:
                account.withdraw(amount);
                break;
            case WITHDRAW:
                account.deposit(amount);
                break;
        }
    }

    public enum Action {
        DEPOSIT, WITHDRAW
    }

    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }
}

public class AccountCommand {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        System.out.println(ba);

        List<BankAccountCommand> commandList = new ArrayList<BankAccountCommand>();
        commandList.add(new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100));
        commandList.add(new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000));

        for (BankAccountCommand c : commandList) {
            c.call();
            System.out.println(ba);
        }

        Collections.reverse(commandList);
        for (Command c : commandList) {
            c.undo();
            System.out.println(ba);
        }
    }
}
