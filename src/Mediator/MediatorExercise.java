package Mediator;

import java.util.ArrayList;
import java.util.List;

class Participant
{

    public Mediator mediator;
    public int value = 0;

    public Participant(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public void say(int n)
    {
        mediator.broadcast(n, this);

    }

    public void recieve(int n) {
        this.value += n;
    }
}

class Mediator
{
    private List<Participant> people = new ArrayList<>();

    public void broadcast(int n, Participant sender) {
        for (Participant person : people)
            if (!person.equals(sender))
                person.recieve(n);
    }
}

public class MediatorExercise {
}
