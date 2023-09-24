package Singleton;

class InnerStaticSingleton{
    private InnerStaticSingleton(){};

    private static class Impl {
        private static final InnerStaticSingleton
            INSTANCE = new InnerStaticSingleton(); // secured only instance
    }

    public InnerStaticSingleton getInstance() { // avoids problem of synchronization
        return Impl.INSTANCE;
    }
}

public class InnerStaticSingletons {
}
