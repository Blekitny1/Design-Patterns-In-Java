package Decorator;

class Bird
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon
{
    private int age;
    private Bird bird; // I am a decorator thus I have fields that I decorate
    private Lizard lizard; // Yes

    public void setAge(int age)
    {
        this.age = age;
        bird.age = age;
        lizard.age = age;
    }
    public String fly()
    {
        return bird.fly();
    }
    public String crawl()
    {
        return lizard.crawl();
    }
}

public class DecoratorExercise {
}
