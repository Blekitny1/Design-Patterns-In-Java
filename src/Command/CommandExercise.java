package Command;

class Command2
{
    enum Action
    {
        DEPOSIT, WITHDRAW
    }

    public Action action;
    public int amount;
    public boolean success;

    public Command2(Action action, int amount)
    {
        this.action = action;
        this.amount = amount;
    }
}

class Account
{
    public int balance;

    public void process(Command2 c)
    {
        switch (c.action) {
            case DEPOSIT:
                c.success = true;
                balance += c.amount;
                break;
            case WITHDRAW:
                if (balance >= c.amount) {
                    balance -= c.amount;
                    c.success = true;
                }
                else {
                    c.success = false;
                }
                break;
        }
    }
}

public class CommandExercise {
}
