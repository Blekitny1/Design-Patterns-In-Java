/*
// DIP - Dependency Inversion Principle

// A. high-level modules should not depend on low-level modules.
// Both should depend on abstraction

// B. Abstraction should not depend on details,
//  which should depend on abstraction

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Builder.Person {
    public String name;
    public Builder.Person(String name) {
        this.name = name;
    }
}

class Relationships implements RelationshipBrowser{ // low - level module, just list with data and single responsibility
    private List<Triplet<Builder.Person, Relationship, Builder.Person>> relations = new ArrayList<>();

    public List<Triplet<Builder.Person, Relationship, Builder.Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Builder.Person parent, Builder.Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Builder.Person> findAllChildrenOf(String name) {
        return null; // we would rather do implementation here and modify it here
    }
}

class Research { // high - level module, operates on low - level module, some business / users purpose
    public Research(Relationships relationships) { // we don't want this dependency
        List<Triplet<Builder.Person, Relationship, Builder.Person>> relations = relationships.getRelations();
        relations.stream().filter(x -> x.getValue0().name.equals("John"));
    } // if we want to change implementation this would be hard

    public Research(RelationshipBrowser browser) { // we depend on abstraction! this is good and we don't break dependency principle
        List<Builder.Person> children = browser.findAllChildrenOf("John");
        for (Builder.Person child : children)
            System.out.println("John has a child called " + child.name);
        // to make it work we just remove old version and add this one
    }
}

interface RelationshipBrowser{
    List<Builder.Person> findAllChildrenOf(String name);
}



public class DIP {
}
*/
