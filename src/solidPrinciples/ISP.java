package solidPrinciples;// solidPrinciples.ISP - Integration Segregation Principle

// when creating interfaces use bare minimum, so that we don't have to implement
// stuff that we don't want or will not use at all

class Document {

}

interface Machine {
    void print(Document d);
    void fax(Document d);
    void scan(Document d);
}

class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

class OldFashionedPrinter implements Machine {

    @Override
    public void print(Document d) {
        // can implement this
    }

    @Override
    public void fax(Document d) {
        // how does old printer fax?
        // maybe nothing, but it is bad too because this should not exist
        // maybe exception, but we have to add extension handling and throws Exception
    }

    @Override
    public void scan(Document d) {
        // we should not use this methods in solidPrinciples.Machine interface
    }
}

interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

class JustAPrinter implements Printer { // and now we can make ordinary printer

    @Override
    public void print(Document d) {

    }
}

class Photocopier implements Printer, Scanner { // and other machines easily too

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MultiFunctionDevice extends Printer, Scanner {} // we can mix the interfaces

class MultiFunctionMachine implements MultiFunctionDevice {

    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }


    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

public class ISP {
}
